/*
File: BankController
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 01.05.23
*/

package learning.antonio.thenewbostontutorial.controller

import learning.antonio.thenewbostontutorial.model.Bank
import learning.antonio.thenewbostontutorial.service.BankService
import org.springframework.http.HttpStatus
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
            HttpStatus.NOT_FOUND
        )

    @ExceptionHandler
    fun handleException(e: Exception): ResponseEntity<String> {
        val errorMessage = when (e) {
            is MethodArgumentTypeMismatchException -> "Bad Request " + e.message
            is IllegalArgumentException -> e.message
            else -> "Something went wrong"
        }
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }


    @GetMapping
    fun getBanks(): Collection<Bank> = bankService.getBanks()

    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: Int): Bank? = bankService.getBank(accountNumber)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank): Bank = bankService.addBank(bank)

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun updateBank(@RequestBody bank: Bank): Bank = bankService.updateBank(bank)

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBank(@PathVariable accountNumber: Int): Unit = bankService.deleteBank(accountNumber)
}