import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import kotlin.Exception
import kotlin.random.Random
import kotlin.system.exitProcess
import java.io.File
import java.io.IOException

class BidderMicroservice {
    private lateinit var auctioneerSocket: Socket
    private lateinit var auctionResultObservable: Observable<String>
    private var myIdentity: String = "[BIDDER_NECONECTAT]"
    private var nume: String = "[BIDDER_NECONECTAT]"
    private var telefon: String = "[BIDDER_NECONECTAT]"
    private var email: String = "[BIDDER_NECONECTAT]"

    private val logger: MutableList<String> = mutableListOf()

    companion object Constants {
        const val AUCTIONEER_HOST = "localhost"
        const val AUCTIONEER_PORT = 1500
        const val MAX_BID = 10_000
        const val MIN_BID = 1_000
    }

    init {
        while (logger.isNullOrEmpty()) {
            try {
                auctioneerSocket = Socket(AUCTIONEER_HOST, AUCTIONEER_PORT)

                nume = "Bidder${auctioneerSocket.localPort}"
                telefon = (1..8).joinToString("") { Random.nextInt(0, 10).toString() }
                email = "${nume}@${auctioneerSocket.localPort}.com"
                myIdentity = "[${nume}#${email}#${telefon}]"

                logger.add("Conectat la Auctioneer")
                println("M-am conectat la Auctioneer!")

                auctionResultObservable = Observable.create<String> { emitter ->
                    val bufferReader = BufferedReader(InputStreamReader(auctioneerSocket.inputStream))
                    val receivedMessage = bufferReader.readLine()

                    if (receivedMessage == null) {
                        bufferReader.close()
                        auctioneerSocket.close()
                        emitter.onError(Exception("AuctioneerMicroservice s-a deconectat."))
                        return@create
                    }

                    emitter.onNext(receivedMessage)
                    emitter.onComplete()
                    bufferReader.close()
                    auctioneerSocket.close()
                }

                break

            } catch (e: Exception) {
                logger.add("Eroare la conectare: ${e.message}")
                println("$myIdentity Nu ma pot conecta la Auctioneer! Reîncerc în 2 secunde...")

                Thread.sleep(2000)
            }
        }


    }


    private fun bid() {
        // se genereaza o oferta aleatorie din partea bidderului curent
        val pret = Random.nextInt(MIN_BID, MAX_BID)

        // se creeaza mesajul care incapsuleaza oferta
        val biddingMessage = Message.create("${myIdentity}", "licitez $pret")

        logger.add("Licitat $pret")

        // bidder-ul trimite pretul pentru care doreste sa liciteze
        val serializedMessage = biddingMessage.serialize()
        auctioneerSocket.getOutputStream().write(serializedMessage)

        // exista o sansa din 2 ca bidder-ul sa-si trimita oferta de 2 ori, eronat
        if (Random.nextBoolean()) {
            auctioneerSocket.getOutputStream().write(serializedMessage)
        }
    }

    private fun waitForResult() {
        println("$myIdentity Astept rezultatul licitatiei...")
        logger.add("Astept rezultat")
        // bidder-ul se inscrie pentru primirea unui raspuns la oferta trimisa de acesta
        val auctionResultSubscription = auctionResultObservable.subscribeBy(
            // cand se primeste un mesaj in flux, inseamna ca a sosit rezultatul licitatiei
            onNext = {
                val resultMessage: Message = Message.deserialize(it.toByteArray())
                println("$myIdentity Rezultat licitatie: ${resultMessage.body}")
                logger.add("Rezultat: ${resultMessage.body}")
            },
            onError = {
                println("$myIdentity Eroare: $it")

            }
        )

        // se elibereaza memoria obiectului Subscription
        auctionResultSubscription.dispose()
    }

    fun run() {
        bid()
        waitForResult()
    }

    fun scrieInFisier(text: String, path: String = "jurnal.txt") {
        try {
            val fisier = File(path)
            fisier.appendText("$text\n")
        } catch (e: IOException) {
            println("Eroare la scriere în fișier: ${e.message}")
        }
    }
}

fun main(args: Array<String>) {
    val bidderMicroservice = BidderMicroservice()
    bidderMicroservice.run()
}