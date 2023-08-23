package no.kristiania.orderservice.controller

import no.kristiania.orderservice.entity.OrderEntity
import no.kristiania.orderservice.services.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.websocket.server.PathParam

@RestController
class OrderController(@Autowired private val orderService: OrderService) {

    @GetMapping("/api/order/track/{orderNumber}")
    fun getOrder(@PathVariable orderNumber: Long): ResponseEntity<OrderEntity> {
        return ResponseEntity.ok(orderService.getOrder(orderNumber))
    }

    //Was just a post mapping, but then it did not work with browser. So I made both
    @GetMapping("/api/order/{itemId}")
    fun placeOrder(@PathVariable itemId: Long): ResponseEntity<String> {
        orderService.getItem(itemId)
        return ResponseEntity.ok("Order placed for item with id $itemId")
    }

    @PostMapping("/api/order/{itemId}")
    fun placeOrderPost(@PathVariable itemId: Long): ResponseEntity<String> {
        orderService.getItem(itemId)
        return ResponseEntity.ok("Order places for item with id $itemId")
    }

    @GetMapping("/api/order/track/all")
    fun getAllOrders(@PathParam("page") page: Int): ResponseEntity<List<OrderEntity>>{
        return ResponseEntity.ok(orderService.getAllOrders(page).toList())
    }
}