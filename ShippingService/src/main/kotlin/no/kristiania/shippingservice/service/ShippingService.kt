package no.kristiania.shippingservice.service

import no.kristiania.shippingservice.entity.Shipment
import no.kristiania.shippingservice.integration.RabbitSender
import no.kristiania.shippingservice.repository.ShippingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ShippingService(@Autowired private val shippingRepository: ShippingRepository, @Autowired private val rabbitSender: RabbitSender) {

    fun saveShipment(shipment: Shipment){
        shippingRepository.save(shipment)
    }

    fun sendMessageToOrder(message: String) {
        rabbitSender.sendMessageToOrder(message)
    }

    fun getAllShipments(page: Int): Page<Shipment> {
        return shippingRepository.findAll(Pageable.ofSize(5).withPage(page))
    }
}