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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestController
@RequestMapping("/api/banks")
class BankController(private val bankService: BankService) {

    @ExceptionHandler
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(
            e.message,
            org.springframework.http.HttpStatus.NOT_FOUND
        )

    @ExceptionHandler
    fun handleBadRequest(e: MethodArgumentTypeMismatchException): ResponseEntity<String> =
        ResponseEntity(
            "Bad Request ".plus(e.message),
            org.springframework.http.HttpStatus.BAD_REQUEST
        )

    @GetMapping
    fun getBanks(): Collection<Bank> = bankService.getBanks()

    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: Int): Bank? = bankService.getBank(accountNumber)
}