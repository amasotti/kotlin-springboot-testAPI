package learning.antonio.thenewbostontutorial.datasource

import learning.antonio.thenewbostontutorial.model.Bank

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
}