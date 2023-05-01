
# Mocking with mockk

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

# Autowiring

## Autowiring single beans

~~~kotlin
@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest {

@Autowired
private val mockMvc: MockMvc

@Autowired
private val objectMapper: ObjectMapper

// Code omitted for brevity
}
~~~

Here we are autowiring the `MockMvc` and `ObjectMapper` beans into our test class, each
of which independently.


## Autowiring single beans in the constructor directly

~~~kotlin
@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {

// Code omitted for brevity
}
~~~

Here we are autowiring the `MockMvc` and `ObjectMapper` beans into our test class, each
of which independently, but this time we are autowiring them directly into the constructor
of the test class.


## Autowiring all the beans in the constructor

~~~kotlin
@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

// Code omitted for brevity
}

~~~

Here we are autowiring all the beans into our test class.
