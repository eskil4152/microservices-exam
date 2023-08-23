package no.kristiania.orderservice.integration

import no.kristiania.orderservice.entity.OrderEntity
import no.kristiania.orderservice.services.OrderService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@RabbitListener(queues = ["item-receiver"])
class ItemReceiver(@Autowired private val orderService: OrderService) {

    @RabbitHandler
    fun receive(message: String) {
        println("Received item in order")

        val split = message.split(",")
        val itemName = split[0]
        val itemPrice = split[1].toDouble()
        val itemId = split[2].toLong()

        val order = OrderEntity(orderedItemName = itemName, orderSum = itemPrice, timeOfOrder = LocalDateTime.now())
        orderService.saveOrder(order)
        orderService.startOrderProcess(order)
    }
}