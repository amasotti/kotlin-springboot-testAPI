/*
File: Bank
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 29.04.23
*/

package learning.antonio.thenewbostontutorial.model

/**
 * This is a class that represents a bank.
 * @property officialName The official name of the bank.
 * @property accountNumber The account number of the bank.
 * @property trust The trust of the bank.
 * @property transactionFee The transaction fee of the bank.
 *
 *
 */
data class Bank(
    val officialName: String,
    val accountNumber: Int,
    private val trust: Double,
    private val transactionFee: Int,
)