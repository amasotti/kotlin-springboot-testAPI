/*
File: BankService
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 01.05.23
*/

package learning.antonio.thenewbostontutorial.service

import learning.antonio.thenewbostontutorial.datasource.BankDataSource
import learning.antonio.thenewbostontutorial.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {

    fun getBanks() = dataSource.retrieveBanks()

    fun getBank(accountNumber: Int): Bank =
        dataSource.retrieveBanks().firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")


}