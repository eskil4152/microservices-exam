package no.kristiania.paymentservice.integration

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RabbitSender(@Autowired private val rabbitTemplate: RabbitTemplate) {

    fun sendMessageToOrder(message: String) {
        rabbitTemplate.convertAndSend("order-queue", message)
        println("Sent message to order-queue")
    }
}