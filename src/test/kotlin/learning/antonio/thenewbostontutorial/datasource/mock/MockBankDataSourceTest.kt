package learning.antonio.thenewbostontutorial.datasource.mock

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {

    private val mockBankDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of banks`() {
        // when
        val banks = mockBankDataSource.getBanks()

        // thenfu
        assertNotEquals(0, banks.size)
        Assertions.assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }
    
    @Test
    fun `should provide some mock data`() {
    
    // given 
    val banks = mockBankDataSource.getBanks()

    //assert
    Assertions.assertThat(banks).allMatch { it.accountNumber > 0 }
    Assertions.assertThat(banks).anyMatch { it.officialName != "" }
    }
}