package no.kristiania.paymentservice.integration

import no.kristiania.paymentservice.entity.Payment
import no.kristiania.paymentservice.service.PaymentService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["payment-queue"])
class RabbitReceiver(@Autowired private val paymentService: PaymentService) {

    @RabbitHandler
    fun receive(message: String) {
        println("Received $message in payment")

        val splitter = message.split(",")
        val name = splitter[0]
        val amount = splitter[1].toDouble()
        val orderNumber = splitter[2].toLong()

        val payment = Payment(itemName = name, amount = amount)
        paymentService.savePayment(payment, orderNumber)

        println(name)
        println(amount)
    }
}