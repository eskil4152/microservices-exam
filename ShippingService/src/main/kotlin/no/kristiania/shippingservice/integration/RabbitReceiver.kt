package no.kristiania.shippingservice.integration

import no.kristiania.shippingservice.entity.Shipment
import no.kristiania.shippingservice.service.ShippingService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["shipping-queue"])
class RabbitReceiver(@Autowired private val shipmentService: ShippingService) {

    @RabbitHandler
    fun receive(message: String) {
        println("Received $message in shipping")

        val split = message.split(",")
        val orderNumber = split[0].toLong()
        val itemName = split[1]

        val shipment = Shipment(item = itemName)
        shipmentService.saveShipment(shipment)

        shipmentService.sendMessageToOrder("$orderNumber,shipped")
    }
}