package no.kristiania.controller

import no.kristiania.entity.Item
import no.kristiania.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
class ItemController(@Autowired private val itemService: ItemService) {

    @GetMapping("/api/item/all")
    fun getAllItems(@PathParam("page") page: Int): ResponseEntity<List<Item>> {
        return ResponseEntity.ok(itemService.getAllItems(page).toList())
    }

    @PostMapping("/api/item/new")
    fun saveItem(@RequestBody item: Item): ResponseEntity<Item> {
        return ResponseEntity.ok(itemService.saveItem(item))
    }

    @GetMapping("/api/item/{id}")
    fun getOneItem(@PathVariable id: Long): ResponseEntity<Item> {
        return ResponseEntity.ok(itemService.getItem(id))
    }
}