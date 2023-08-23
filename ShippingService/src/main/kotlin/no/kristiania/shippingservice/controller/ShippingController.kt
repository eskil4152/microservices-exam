package no.kristiania.shippingservice.controller

import no.kristiania.shippingservice.entity.Shipment
import no.kristiania.shippingservice.service.ShippingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
class ShippingController(@Autowired private val shippingService: ShippingService) {

    @GetMapping("/api/shipping/all")
    fun getAllShipments(@PathParam("page") page: Int): ResponseEntity<List<Shipment>> {
        return ResponseEntity.ok(shippingService.getAllShipments(page).toList())
    }
}