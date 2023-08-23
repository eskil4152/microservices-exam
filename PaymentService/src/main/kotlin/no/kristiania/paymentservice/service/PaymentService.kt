package no.kristiania.paymentservice.service

import no.kristiania.paymentservice.entity.Payment
import no.kristiania.paymentservice.integration.RabbitSender
import no.kristiania.paymentservice.repository.PaymentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PaymentService(
    @Autowired private val paymentRepository: PaymentRepository,
    @Autowired private val rabbitSender: RabbitSender
    ) {

    fun sendMessageToOrder(message: String) {
        rabbitSender.sendMessageToOrder(message)
    }

    fun savePayment(payment: Payment, orderId: Long) {
        paymentRepository.save(payment)
        sendMessageToOrder("${orderId},payed")
    }

    fun getAllPayments(page: Int): Page<Payment> {
        return paymentRepository.findAll(Pageable.ofSize(3).withPage(page))
    }
}