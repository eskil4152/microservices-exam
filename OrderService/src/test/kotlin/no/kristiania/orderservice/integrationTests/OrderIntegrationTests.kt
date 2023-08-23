package no.kristiania.orderservice.integrationTests

import no.kristiania.orderservice.extensions.TestContainerExtensions
import org.hamcrest.Matchers
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(TestContainerExtensions::class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class OrderIntegrationTests(@Autowired private val mockMvc: MockMvc) {

    val baseUrl = "http://localhost:8081/api/order"

    @Test
    fun shouldSendItemRequest(){
        mockMvc.get("$baseUrl/1")
            .andExpect { status { isOk() } }
    }

    @Test
    fun shouldFetchOrder(){
        mockMvc.get("$baseUrl/track/1") {
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { jsonPath("$.orderedItemName", Matchers.containsString("Test item")) }
    }

    @Test
    fun shouldFetchAllOrdersOnPage1(){
        mockMvc.get("$baseUrl/track/all?page=0") {
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { jsonPath("$[1].orderedItemName", Matchers.containsString("Test item 2")) }
            .andExpect { jsonPath("$[2].orderedItemName", Matchers.containsString("Test item 3")) }
    }

    @Test
    fun shouldReturnInvalidOrderNumberRequest(){
        mockMvc.get("$baseUrl/track/100")
            .andExpect { status { is4xxClientError() } }
            .andExpect { Matchers.containsString("Could not find order with order-number 100") }
    }

    @Test
    fun shouldReturn404(){
        mockMvc.get("$baseUrl/nonexistent")
            .andExpect { status { is4xxClientError() } }
    }

}