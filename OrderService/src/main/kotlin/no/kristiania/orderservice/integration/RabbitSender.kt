package no.kristiania.orderservice.integration

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RabbitSender(@Autowired private val rabbitTemplate: RabbitTemplate) {

    fun sendMessageToPayment(message: String) {
        rabbitTemplate.convertAndSend("payment-queue", message)
        println("Sent message to payment-queue")
    }

    fun sendMessageToShipping(message: String) {
        rabbitTemplate.convertAndSend("shipping-queue", message)
        println("Sent message to shipping-queue")
    }

    fun sendMessageToItem(message: String) {
        rabbitTemplate.convertAndSend("item-queue", message)

        println("Sent message to payment-queue")
    }
}