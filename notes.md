## Standard mocking with mockk

~~~kotlin
internal class BankServiceTest {

    private val bankDataSource: BankDataSource = mockk()
    private val bankService = BankService(bankDataSource)

    @Test
    fun `should call its data source to retrieve data`() {

        // given
        every { bankDataSource.retrieveBanks() } returns emptyList()


        // when
        val banks = bankService.getBanks()

        //assert
        verify(exactly = 1) { bankDataSource.retrieveBanks() }

    }
}
~~~


## Mocking with mockk relaxed

This will allow mockk to look at the return type and return the default value for that type (
e.g. empty list for a list, null for a nullable type, 0 for an int, etc.)

~~~kotlin
internal class BankServiceTest {

    private val bankDataSource: BankDataSource = mockk(relaxed = true) // <---- relaxed
    private val bankService = BankService(bankDataSource)

    @Test
    fun `should call its data source to retrieve data`() {

        // when
        bankService.getBanks()

        //assert
        verify(exactly = 1) { bankDataSource.retrieveBanks() }

    }
}
~~~