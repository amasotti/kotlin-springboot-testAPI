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

    val banks = mutableListOf(
        Bank("Sparkasse", 1, 3.14, 1),
        Bank("Volksbank", 2, 2.85, 5),
        Bank("DeutscheBank", 3, 3.30, 2),
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun addBank(bank: Bank): Bank {
        failIfAccountNumberExists(bank.accountNumber)
        banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber }
            ?: throw NoSuchElementException("Bank with account number ${bank.accountNumber} not found")
        banks.remove(currentBank)
        banks.add(bank)
        return bank
    }

    override fun deleteBank(accountNumber: Int): Unit {
        val currentBank = banks.firstOrNull { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Bank with account number $accountNumber not found")
        banks.remove(currentBank)
    }

    private fun failIfAccountNumberExists(accountNumber: Int) {
        if (banks.any { it.accountNumber == accountNumber }) {
            throw IllegalArgumentException("Bank with account number $accountNumber already exists")
        }
    }
}
