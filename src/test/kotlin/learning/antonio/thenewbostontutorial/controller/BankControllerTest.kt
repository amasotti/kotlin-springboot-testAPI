package learning.antonio.thenewbostontutorial.controller

import com.fasterxml.jackson.databind.ObjectMapper
import learning.antonio.thenewbostontutorial.model.Bank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun `should return all banks`() {
            // when / then
            mockMvc.get("/api/banks")
                .andDo { print() }
                .andExpect {
                    status { is2xxSuccessful() }
                    content { contentType("application/json") }
                    jsonPath("$[0].accountNumber") { value("1") }
                }
        }

    }


    @Nested
    @DisplayName("GET /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `should get one bank by accountNumber`() {

            mockMvc.get("/api/banks/1")
                .andExpect {
                    status { is2xxSuccessful() }
                    content { contentType("application/json") }
                    jsonPath("$.trust") { value("3.14") }
                }

        }

        @Test
        fun `should return BAD REQUEST if the accountNumber cannot be converted to an Integer`() {

            mockMvc.get("/api/banks/not_existing")
                .andExpect {
                    status { isBadRequest() }
                }

        }


        @Test
        fun `should return NOT FOUND if the accountNumber does not exist`() {

            mockMvc.get("/api/banks/999999999")
                .andExpect {
                    status { isNotFound() }
                }

        }

    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewBank {

        @Test
        fun `should add the new bank`() {
            // given
            val newBank = Bank("test", 1234, 8.0, 4)

            val postReq = mockMvc.post("/api/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            postReq
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType("application/json") }
                    jsonPath("$.accountNumber") { value("1234") }
            }


        }

        @Test
        fun `should return BAD REQUEST if bank with given accountNumber already exists`() {
            // given
            val invalidBank = Bank("test", 1, 8.0, 4)

            val postReq = mockMvc.post("/api/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            postReq
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }

    }

}