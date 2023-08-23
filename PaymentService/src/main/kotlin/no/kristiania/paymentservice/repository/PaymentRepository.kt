package no.kristiania.paymentservice.repository

import no.kristiania.paymentservice.entity.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository: JpaRepository<Payment, Long>