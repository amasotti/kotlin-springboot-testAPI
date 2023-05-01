package learning.antonio.thenewbostontutorial.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Nested
    @DisplayName("Get multiple banks")
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
    @DisplayName("Single Bank operations")
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
        fun `should return NOT FOUND if the accountNumber does not exist`() {

            mockMvc.get("/api/banks/99999")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }

        }

    }

}