package ru.netology
enum class CardType {
    MASTERCARD, MAESTRO, MIR, VISA, VKPAY
}

class TaxCalculator {

    private var tax = 0
    private val untaxLimit = 75000
    private val minTransferSumForUntax = 300

    fun calculateTax(cardType: CardType, previousTransfersSum: Int = 0, transferSum: Int) : Int {
        when (cardType) {
            CardType.MASTERCARD, CardType.MAESTRO -> calculateMasterCardAndMaestroTax(previousTransfersSum, transferSum)
            CardType.VISA, CardType.MIR -> calculateVisaAndMirTax(transferSum)
            CardType.VKPAY -> tax = 0
        }

        return tax
    }

    private fun calculateMasterCardAndMaestroTax(previousTransfersSum: Int, transferSum: Int) {
        if (previousTransfersSum < untaxLimit && transferSum > minTransferSumForUntax) {
            val remainOfLimit = untaxLimit - previousTransfersSum
            val taxableSum = transferSum - remainOfLimit
            tax = if (taxableSum > 0) {
                (taxableSum.toDouble() * 0.006 + 20).toInt()
            } else {
                0
            }
        } else {
            tax = (transferSum.toDouble() * 0.006 + 20).toInt()
        }
    }

    private fun calculateVisaAndMirTax(transferSum: Int) {
        val taxSum = (transferSum.toDouble() * 0.0075).toInt()
        tax = if (taxSum < 35) {
            35
        } else {
            taxSum
        }
    }
}
