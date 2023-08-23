package no.kristiania.paymentservice.controller

import no.kristiania.paymentservice.entity.Payment
import no.kristiania.paymentservice.service.PaymentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
class PaymentController(@Autowired private val paymentService: PaymentService) {

    @GetMapping("/api/payment/all")
    fun getAllPayments(@PathParam("page") page: Int): ResponseEntity<List<Payment>> {
        return ResponseEntity.ok(paymentService.getAllPayments(page).toList())
    }
}