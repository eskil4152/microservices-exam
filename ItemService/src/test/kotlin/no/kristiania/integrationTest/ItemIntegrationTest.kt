package no.kristiania.integrationTest

import org.hamcrest.Matchers.*
import org.json.JSONObject
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ItemIntegrationTest(@Autowired private val mockMvc: MockMvc) {

    val baseUrl = "http://localhost:8084/api/item"

    @Test
    @Order(1)
    fun shouldCreateNewItem(){
        val item = JSONObject()
            .put("name", "Test item")
            .put("price", 10)
            .put("quantity", 5)

        mockMvc.post("$baseUrl/new") {
            contentType = MediaType.APPLICATION_JSON
            content = item
        }
            .andExpect { status { isOk() } }
            .andReturn()
    }

    @Test
    @Order(2)
    fun shouldFetchTestMadeItem(){
        mockMvc.get("$baseUrl/16") {
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { jsonPath("$.name", containsString("Test item")) }
    }

    @Test
    @Order(3)
    fun shouldFetchFlywayMadeItem(){
        mockMvc.get("$baseUrl/2") {
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { jsonPath("$.name", containsString("Pants")) }
    }

    @Test
    @Order(4)
    fun shouldFetchAllItemsOnPage1() {
        mockMvc.get("$baseUrl/all?page=0") {
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { jsonPath("$[0].name", containsString("Sweater")) }
            .andExpect { jsonPath("$[1].name", containsString("Pants")) }
            .andExpect { jsonPath("$[2].name", containsString("Shoes")) }
            .andExpect { jsonPath("$[3].name", containsString("Jacket")) }
            .andExpect { jsonPath("$[4].name", containsString("2pk T-Shirt")) }
    }

    @Test
    fun shouldReturn404(){
        mockMvc.get("$baseUrl/nonexistent")
            .andExpect { status { is4xxClientError() } }
    }

    @Test
    fun shouldReturnInvalidItemRequest(){
        mockMvc.get("$baseUrl/40")
            .andExpect { status { is4xxClientError() } }
            .andExpect { containsString("Item with id 40 not found") }
    }
}