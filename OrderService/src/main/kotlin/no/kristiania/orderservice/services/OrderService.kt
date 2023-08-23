package no.kristiania.orderservice.services

import no.kristiania.orderservice.entity.OrderEntity
import no.kristiania.orderservice.exception.OrderNotFoundException
import no.kristiania.orderservice.integration.RabbitSender
import no.kristiania.orderservice.repositories.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@CacheConfig(cacheNames = ["orders"])
class OrderService(@Autowired private val orderRepository: OrderRepository, @Autowired private val rabbitSender: RabbitSender) {

    //Did not see a need for cache, but can be used like this:

    @Cacheable(key = "#orderNumber")
    fun getOrder(orderNumber: Long): OrderEntity? {
        val order = orderRepository.findByIdOrNull(orderNumber)

        if (order == null) {
            throw OrderNotFoundException("Could not find order with order-number $orderNumber")
        } else {
            return orderRepository.findById(orderNumber).orElse(null)
        }
    }

    fun getAllOrders(page: Int): Page<OrderEntity> {
        return orderRepository.findAll(Pageable.ofSize(5).withPage(page))
    }

    fun getItem(itemId: Long) {
        rabbitSender.sendMessageToItem("$itemId")
    }

    fun startOrderProcess(order: OrderEntity) {
        sendMessageToPayment("${order.orderedItemName},${order.orderSum},${order.orderNumber}")
    }

    @CacheEvict(allEntries = true)
    fun saveOrder(orderEntity: OrderEntity): OrderEntity {
        return orderRepository.save(orderEntity)
    }

    @CachePut(key = "#id")
    fun markOrderAsPaid(id: Long) {
        val order = orderRepository.findByIdOrNull(id)
        if (order != null) {
            order.payed = true
            orderRepository.save(order)
            sendMessageToShipping("${order.orderNumber},${order.orderedItemName}")
        }
    }

    @CachePut(key = "#id")
    fun markOrderAsShipped(id: Long) {
        val order = orderRepository.findByIdOrNull(id)
        if (order != null) {
            order.shipped = true
            orderRepository.save(order)
        }
    }

    fun sendError(orderNumber: Long, action: String?) {
        println("Error with $action with order $orderNumber")
    }

    fun sendMessageToPayment(message: String){
        rabbitSender.sendMessageToPayment(message)
    }

    fun sendMessageToShipping(message: String) {
        rabbitSender.sendMessageToShipping(message)
    }
}