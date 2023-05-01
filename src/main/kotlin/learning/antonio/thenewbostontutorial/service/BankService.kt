/*
File: BankService
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 01.05.23
*/

package learning.antonio.thenewbostontutorial.service

import learning.antonio.thenewbostontutorial.datasource.BankDataSource
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {

    fun getBanks() = dataSource.retrieveBanks()


}