package learning.antonio.thenewbostontutorial.controller

import com.fasterxml.jackson.databind.ObjectMapper
import learning.antonio.thenewbostontutorial.model.Bank
import learning.antonio.thenewbostontutorial.service.BankService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper,
    val bankService: BankService
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

    @Nested
    @DisplayName("PATCH /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchBanks {

        @Test
        fun `should update an existing bank`() {
            // given
            val updatedBank = Bank("Sparkasse", 1, 8.0, 4)

            val patchReq = mockMvc.patch("/api/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }

            patchReq
                .andDo { print() }
                .andExpect {
                    status { isAccepted() }
                    content {
                        contentType("application/json")
                        json(objectMapper.writeValueAsString(updatedBank))
                    }
                }
        }

        @Test
        fun `should return NotFound if no bank with given accountNumber exists`() {
            // given
            val invalidBank = Bank("test", 99999, 8.0, 4)

            val patchReq = mockMvc.patch("/api/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            patchReq
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }

    }

    @Nested
    @DisplayName("DELETE /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteBank {

        @Test
        @DirtiesContext // This annotation is needed to reset the mocked data after the test
        fun `should delete an existing bank`() {

            // given
            val accountNumber = 1

            val deleteReq = mockMvc.delete("/api/banks/$accountNumber")

            deleteReq
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }
        }

        @Test
        fun `should return NotFound if no bank with given accountNumber exists`() {
            // given
            val accountNumber = 99999

            val deleteReq = mockMvc.delete("/api/banks/$accountNumber")

            deleteReq
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }

    }

}