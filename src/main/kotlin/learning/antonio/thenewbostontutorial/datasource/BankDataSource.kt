package learning.antonio.thenewbostontutorial.datasource

import learning.antonio.thenewbostontutorial.model.Bank

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
    fun addBank(bank: Bank): Bank
    fun updateBank(bank: Bank): Bank
    fun deleteBank(accountNumber: Int): Unit
}