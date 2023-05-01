package learning.antonio.thenewbostontutorial.service

import io.mockk.*
import learning.antonio.thenewbostontutorial.datasource.BankDataSource
import org.junit.jupiter.api.Test

internal class BankServiceTest {

    private val bankDataSource: BankDataSource = mockk(relaxed = true)
    private val bankService = BankService(bankDataSource)

    @Test
    fun `should call its data source to retrieve data`() {

        // when
        bankService.getBanks()

        //assert
        verify(exactly = 1) { bankDataSource.retrieveBanks() }

    }
}