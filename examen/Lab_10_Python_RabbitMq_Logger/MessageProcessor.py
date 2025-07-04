import time

from RabbitMqConnection import RabbitMqProducer, RabbitMqConsumer


class MessageProcessor:
    def __init__(self):
        self.producer = RabbitMqProducer(exchange="bidder.direct", routing_key="biddingprocessor.routingkey")
        self.consumer = RabbitMqConsumer(rabbit_queue="message_processor.queue")
        self.loggerProducer = RabbitMqProducer(exchange="bidder.direct", routing_key="bidding_logger.routingkey")

        # ofertele se pun in dictionar, sub forma de perechi <IDENTITATE_OFERTANT, MESAJ_OFERTA>
        self.bids = dict()

    def get_and_process_messages(self):
        # se asteapta notificarea de la Auctioneer pentru incheierea licitatiei
        print("[MessageProcessor] Astept notificare de la toate entitatile Auctioneer pentru incheierea licitatiei...")
        self.loggerProducer.send_message("info:[MessageProcessor] Astept notificare de la toate entitatile Auctioneer pentru incheierea licitatiei...")
        time.sleep(2)

        # a ajuns prima notificare, se asteapta si celelalte notificari timp de maxim 15 secunde
        
        while True:
            receiver_message = None
            try:
                receiver_message = self.consumer.receive_message()
            except Exception as e:
                self.loggerProducer.send_message("except:{}".format(e))
            
            # Se iese din loop datorita neprimirii de mesaje in timp util
            if receiver_message == None:
                break

            if receiver_message == "incheiat":
                print("[MessageProcessor] Licitatie incheiata. Procesez mesajele cu oferte...")
                self.loggerProducer.send_message("info:[MessageProcessor] Licitatie incheiata. Procesez mesajele cu oferte...")
                break
            
            split_message = receiver_message.split("_")
            self.bids[split_message[0].split(":")[1]] = split_message[1].split(":")[1]

        # sorter dupa valoare
        sorted_bids = {k: v for k, v in sorted(self.bids.items(), key=lambda item: item[1])}
        self.finish_processing(sorted_bids)

    def finish_processing(self, sorted_bids):
        print("[MessageProcessor] Procesarea s-a incheiat! Trimit urmatoarele oferte:")
        self.loggerProducer.send_message("[MessageProcessor] Procesarea s-a incheiat! Trimit urmatoarele oferte:")
        for IdBid in sorted_bids:
            identity = IdBid
            amount = sorted_bids[identity]
            
            print("[MessageProcessor] {} a licitat {}.".format(identity, amount))
            self.loggerProducer.send_message("info:[MessageProcessor] {} a licitat {}.".format(identity, amount))

            rabbit_msg = "id:{}_amount:{}".format(identity, amount)
            self.producer.send_message(rabbit_msg)
        
        # dupa ce terminam de procesat, anuntam ca MessageProcessor a incheiat munca
        self.producer.send_message("incheiat")

    def run(self):
        self.get_and_process_messages()


if __name__ == '__main__':
    message_processor = MessageProcessor()
    message_processor.run()
