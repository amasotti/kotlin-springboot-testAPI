/*
File: BankTests
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 29.04.23
*/

package learning.antonio.thenewbostontutorial.model

import org.junit.jupiter.api.Test

class BankTests {

    @Test
    fun check_equality() {
        val bank1 = Bank("Sparkasse", 123, 0.1, 1)
        val bank2 = Bank("Sparkasse", 123, 0.1, 1)
        assert(bank1 == bank2)


        val bank3 = Bank("Deutsche Bank", 123, 0.1, 1)
        assert(bank1 != bank3)
    }

    @Test
    fun check_hashcode() {
        val bank1 = Bank("Sparkasse", 123, 0.1, 1)
        val bank2 = Bank("Sparkasse", 123, 0.1, 1)
        val bank1HashCode = bank1.hashCode()
        val bank2HashCode = bank2.hashCode()
        assert(bank1HashCode == bank2HashCode)
    }

    @Test
    fun check_toString() {
        val bank1 = Bank("Sparkasse", 123, 0.1, 1)
        val bank2 = Bank("Sparkasse", 123, 0.1, 1)
        assert(bank1.toString() == bank2.toString())
        val expectedString = "Bank(officialName='Sparkasse', accountNumber=123, trust=0.1, transactionFee=1). Listening on port 8080"
        assert(bank1.toString() == expectedString)
    }

}