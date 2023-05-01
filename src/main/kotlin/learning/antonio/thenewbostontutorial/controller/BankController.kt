/*
File: BankController
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 01.05.23
*/

package learning.antonio.thenewbostontutorial.controller

import learning.antonio.thenewbostontutorial.model.Bank
import learning.antonio.thenewbostontutorial.service.BankService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks")
class BankController(private val bankService: BankService) {

    @ExceptionHandler
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(
            null,
            org.springframework.http.HttpStatus.NOT_FOUND
        )

    @GetMapping
    fun getBanks(): Collection<Bank> = bankService.getBanks()

    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: Int): Bank? {
        return bankService.getBank(accountNumber)
    }

}