/*
File: Bank
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 29.04.23
*/

package learning.antonio.thenewbostontutorial.model

/**
 * Model for the Bank object, works as DTO for the BankController
 */
class Bank(
    val officialName: String,
    private val accountNumber: Int,
    private val trust: Double,
    private val transactionFee: Int,
    private val port: Int = 8080
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bank

        if (officialName != other.officialName) return false
        if (accountNumber != other.accountNumber) return false
        if (trust != other.trust) return false
        return transactionFee == other.transactionFee
    }

    override fun hashCode(): Int {
        var result = officialName.hashCode()
        result = 31 * result + accountNumber
        result = 31 * result + trust.hashCode()
        return result
    }

    override fun toString(): String {
        return "Bank(officialName='$officialName', accountNumber=$accountNumber, trust=$trust, transactionFee=$transactionFee). Listening on port $port"
    }

}