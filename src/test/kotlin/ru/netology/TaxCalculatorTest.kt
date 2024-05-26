package ru.netology

import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test

class TaxCalculatorTest {

    var calculator: TaxCalculator? = null

    @Before
    fun setup() {
        calculator = TaxCalculator()
    }

    @Test
    fun calculateMaestroAndMasterCardTax() {
        val type = CardType.MAESTRO
        val previousTransactionSum = 40000
        val transactionSum = 20000
        val tax = calculator?.calculateTax(type, previousTransactionSum, transactionSum)
        assertEquals(0, tax)
    }

    @Test
    fun calculateMaestroAndMasterCardTaxInTaxLimit() {
        val type = CardType.MAESTRO
        val previousTransactionSum = 40000
        val transactionSum = 80000
        val tax = calculator?.calculateTax(type, previousTransactionSum, transactionSum)
        assertEquals(290, tax)
    }

    @Test
    fun calculateMaestroAndMasterCardTaxOutTaxLimit() {
        val type = CardType.MAESTRO
        val previousTransactionSum = 75000
        val transactionSum = 10000
        val tax = calculator?.calculateTax(type, previousTransactionSum, transactionSum)
        assertEquals(80, tax)
    }

    @Test
    fun calculateVisaAndMIRCardTax() {
        val type = CardType.VISA
        val previousTransactionSum = 40000
        val transactionSum = 20000
        val tax = calculator?.calculateTax(type, previousTransactionSum, transactionSum)
        assertEquals(150, tax)
    }

    @Test
    fun calculateVisaAndMIRCardTaxInMinSum() {
        val type = CardType.VISA
        val previousTransactionSum = 40000
        val transactionSum = 1000
        val tax = calculator?.calculateTax(type, previousTransactionSum, transactionSum)
        assertEquals(35, tax)
    }

    @Test
    fun calculateVKpayTax() {
        val type = CardType.VKPAY
        val previousTransactionSum = 40000
        val transactionSum = 20000
        val tax = calculator?.calculateTax(type, previousTransactionSum, transactionSum)
        assertEquals(0, tax)
    }

    @After
    fun tearDown() {
        calculator = null
    }
}