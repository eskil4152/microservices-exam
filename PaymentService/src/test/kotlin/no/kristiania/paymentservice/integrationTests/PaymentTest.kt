package no.kristiania.paymentservice.integrationTests

import org.hamcrest.Matchers
import org.junit.jupiter.api.MethodOrderer
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

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class PaymentTest(@Autowired private val mockMvc: MockMvc) {

    @Test
    fun shouldGetAllPaymentsOnPage1(){
        mockMvc.get("http://localhost:8082/api/payment/all?page=0") {
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isOk() } }
            .andExpect { jsonPath("$[0].itemName", Matchers.containsString("Test item")) }
            .andExpect { jsonPath("$[1].itemName", Matchers.containsString("Test item 2")) }
            .andExpect { jsonPath("$[2].itemName", Matchers.containsString("Test item 3")) }
    }

    @Test
    internal fun shouldReturn404() {
        mockMvc.get("http://localhosy:8082/api/payment/non")
            .andExpect { status { is4xxClientError() } }
    }
}