/*
File: BankController
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 01.05.23
*/

package learning.antonio.thenewbostontutorial.controller

import learning.antonio.thenewbostontutorial.model.Bank
import learning.antonio.thenewbostontutorial.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/banks")
class BankController(private val bankService: BankService) {

    @GetMapping
    fun getBanks(): Collection<Bank> = bankService.getBanks()

}