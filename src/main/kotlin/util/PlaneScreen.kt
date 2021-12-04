package util

class PlaneScreen(val code: List<String>, val preambleLen: Int) {

    var preamble = mutableListOf<Int>()
    var pointer = 0

    fun run(): Int {
        repeat(preambleLen) {
            preamble.add(code[pointer].toInt())
            pointer++
        }
        do {
            val nextNumber = code[pointer].toInt()
            var found = false
            for (i in preamble.indices) {
                if (preamble.contains(nextNumber - preamble[i])) found = true
            }
            if (!found) return nextNumber
            pointer++
            preamble = preamble.drop(1).toMutableList()
            preamble.add(nextNumber)
        } while (pointer < code.size)
        return -1
    }

    fun findWeakness(numberToCheck: Int): Int {
        for (i in code.indices) {
            var sum = 0
            var index = i
            var smallest = Int.MAX_VALUE
            var largest = 0
            while (sum < numberToCheck) {
                sum += code[index].toInt()
                if (code[index].toInt() > largest) largest = code[index].toInt()
                if (code[index].toInt() < smallest) smallest = code[index].toInt()
                index++
            }
            if (sum == numberToCheck) return (smallest + largest)
        }
        return -1
    }

}
