#!/bin/bash

echo "Pornesc Message ProcessorMicroservice..."

xterm -hold -e "export PATH=$PATH:/home/iedi/.jdks/corretto-1.8.0_442/bin; java -cp out/artifacts/MessageProcessorMicroservice_jar/MessageProcessorMicroservice.jar MessageProcessorMicroserviceKt" &

echo "Pornesc Bidding ProcessorMicroservice..."
xterm -hold -e "export PATH=$PATH:/home/iedi/.jdks/corretto-1.8.0_442/bin; java -cp out/artifacts/BiddingProcessorMicroservice_jar/BiddingProcessorMicroservice.jar BiddingProcessorMicroserviceKt" &


sleep 5

echo "Pornesc AuctioneerMicroservice..."
xterm -hold -e "export PATH=$PATH:/home/iedi/.jdks/corretto-1.8.0_442/bin; java -cp out/artifacts/AuctioneerMicroservice_jar/AuctioneerMicroservice.jar AuctioneerMicroserviceKt" &

echo "Pornesc bidders..."
xterm -hold -e "./bidders.sh"


echo "Toate microserviciile au fost pornite."

sleep 15

exit