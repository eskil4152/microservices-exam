package no.kristiania.shippingservice.repository

import no.kristiania.shippingservice.entity.Shipment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShippingRepository: JpaRepository<Shipment, Long> {
}