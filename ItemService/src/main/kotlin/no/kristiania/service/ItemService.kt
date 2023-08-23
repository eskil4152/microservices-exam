package no.kristiania.service

import no.kristiania.entity.Item
import no.kristiania.exception.ItemNotFoundException
import no.kristiania.integration.RabbitSender
import no.kristiania.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ItemService(@Autowired private val itemRepository: ItemRepository, @Autowired private val rabbitSender: RabbitSender) {

    fun getAllItems(page: Int): Page<Item> {
        return itemRepository.findAll(Pageable.ofSize(5).withPage(page))
    }

    fun getItem(itemId: Long): Item? {
        val item = itemRepository.findByIdOrNull(itemId)

        if (item == null) {
            throw ItemNotFoundException(message = "Item with id $itemId not found")
        } else {
            return item
        }
    }

    fun saveItem(entity: Item): Item {
        return itemRepository.save(entity)
    }

    fun sendMessageToOrder(message: String) {
        rabbitSender.sendMessageToOrder(message)
    }
}