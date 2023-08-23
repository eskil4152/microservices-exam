package no.kristiania.orderservice.integration

import no.kristiania.orderservice.services.OrderService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["order-queue"])
class RabbitReceiver(@Autowired private val orderService: OrderService) {

    @RabbitHandler
    fun receive(message: String) {
        println("Received $message in order")

        val split = message.split(",")
        val orderNumber = split[0].toLong()
        val action = split[1]

        if (action == "payed") {
            orderService.markOrderAsPaid(orderNumber)
            println("Order $orderNumber marked as paid")
        } else if (action == "shipped") {
            orderService.markOrderAsShipped(orderNumber)
            println("Order $orderNumber marked as shipped")
        } else {
            orderService.sendError(orderNumber, action)
        }
    }
}