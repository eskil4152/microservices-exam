package no.kristiania.integration

import no.kristiania.entity.Item
import no.kristiania.service.ItemService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["item-queue"])
class RabbitReceiver(@Autowired private val itemService: ItemService) {
    @RabbitHandler
    fun receive(message: String) {
        println("Received $message in item")

        val item: Item? = itemService.getItem(message.toLong())

        if (item == null) {
            println("Item not found")
        } else {
            itemService.sendMessageToOrder("${item.name},${item.price},${item.id}")
            println("Send item to order")
        }
    }
}