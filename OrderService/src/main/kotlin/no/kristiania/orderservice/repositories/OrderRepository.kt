package no.kristiania.orderservice.repositories

import no.kristiania.orderservice.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<OrderEntity, Long>