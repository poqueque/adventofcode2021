package days

class Day03 : Day(3) {

    override fun partOne(): Any {
        val count0 = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        val count1 = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        for (s in inputList) {
            for (c in s.indices) {
                if (s[c] == '0') count0[c]++
                else count1[c]++
            }
        }
        var gamma = ""
        var epsilon = ""
        for (c in count0.indices) {
            gamma += if (count0[c] > count1[c]) "0"
            else "1"
            epsilon += if (count0[c] > count1[c]) "1"
            else "0"
        }

        return gamma.toInt(2) * epsilon.toInt(2)
    }

    override fun partTwo(): Any {

        var leftNumbers = inputList.toMutableList()
        var pointer = 0

        var oxygen = ""
        while (leftNumbers.size > 1) {
            var zero = 0
            var one = 0
            for (s in leftNumbers) {
                if (s[pointer] == '0') zero++ else one++
            }
            val maxChar = if (zero > one) '0' else '1'
            val minChar = if (zero > one) '1' else '0'
            oxygen += maxChar
            leftNumbers = leftNumbers.filter { it[pointer] == maxChar }.toMutableList()
            pointer++
        }
        oxygen = leftNumbers[0]

        leftNumbers = inputList.toMutableList()
        var co2 = ""
        pointer = 0
        while (leftNumbers.size > 1) {
            var zero = 0
            var one = 0
            for (s in leftNumbers) {
                if (s[pointer] == '0') zero++ else one++
            }
            val minChar = if (zero > one) '1' else '0'
            co2 += minChar
            leftNumbers = leftNumbers.filter { it[pointer] == minChar }.toMutableList()
            pointer++
        }
        co2 = leftNumbers[0]

        return oxygen.toInt(2) * co2.toInt(2)
    }
}
