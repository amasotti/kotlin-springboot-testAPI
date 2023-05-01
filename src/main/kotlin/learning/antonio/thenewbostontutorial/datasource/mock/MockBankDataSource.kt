/*
File: MockBankDataSource
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 30.04.23
*/

package learning.antonio.thenewbostontutorial.datasource.mock

import learning.antonio.thenewbostontutorial.datasource.BankDataSource
import learning.antonio.thenewbostontutorial.model.Bank
import org.springframework.stereotype.Repository


@Repository
class MockBankDataSource: BankDataSource {

    val banks = listOf(
        Bank("Mockito", 1, 1.0, 1),
        Bank("Mockito", 2, 1.0, 5),
        Bank("Mockito", 3, 1.0, 2),
    )

    override fun getBanks(): Collection<Bank> {
        return banks
    }
}