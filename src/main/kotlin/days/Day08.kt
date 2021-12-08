package days

class Day08 : Day(8) {

    override fun partOne(): Any {
        val lengths = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0)

        inputList.forEach {
            val (_, output) = it.split(" | ")
            val outputs = output.split(" ")
            for (o in outputs) lengths[o.length]++
        }
        return lengths[2] + lengths[3] + lengths[4] + lengths[7]
    }

    override fun partTwo(): Any {
        var total = 0
        inputList.forEach {
            val (input, output) = it.split(" | ")
            val inputs = input.split(" ")
            val outputs = output.split(" ")
            val decoded = mutableMapOf(
                0 to "",
                1 to "",
                2 to "",
                3 to "",
                4 to "",
                5 to "",
                6 to "",
                9 to "",
                8 to "",
                0 to "",
            )
            for (i in inputs) {
                if (i.length == 2) decoded[1] = i.toSortedSet().joinToString("")
                if (i.length == 3) decoded[7] = i.toSortedSet().joinToString("")
                if (i.length == 4) decoded[4] = i.toSortedSet().joinToString("")
                if (i.length == 7) decoded[8] = i.toSortedSet().joinToString("")
            }
            for (i in inputs) {
                if (i.length == 6) {
                    if (i.contains(decoded[4]?.get(0) ?: 'z') &&
                        i.contains(decoded[4]?.get(1) ?: 'z') &&
                        i.contains(decoded[4]?.get(2) ?: 'z') &&
                        i.contains(decoded[4]?.get(3) ?: 'z')
                    ) decoded[9] = i.toSortedSet().joinToString("")
                    else if (i.contains(decoded[7]?.get(0) ?: 'z') &&
                        i.contains(decoded[7]?.get(1) ?: 'z') &&
                        i.contains(decoded[7]?.get(2) ?: 'z')
                    ) decoded[0] = i.toSortedSet().joinToString("")
                    else decoded[6] = i.toSortedSet().joinToString("")
                }
            }
            for (i in inputs) {
                if (i.length == 5) {
                    if (i.contains(decoded[1]?.get(0) ?: 'z') &&
                        i.contains(decoded[1]?.get(1) ?: 'z')
                    ) decoded[3] = i.toSortedSet().joinToString("")
                    else if (decoded[6]?.contains(i[0]) == true &&
                        decoded[6]?.contains(i[1]) == true &&
                        decoded[6]?.contains(i[2]) == true &&
                        decoded[6]?.contains(i[3]) == true &&
                        decoded[6]?.contains(i[4]) == true
                    ) decoded[5] = i.toSortedSet().joinToString("")
                    else decoded[2] = i.toSortedSet().joinToString("")
                }
            }
            var n = 0
            for (o in outputs) {
                n *= 10
                n += decoded.filter { it.value == o.toSortedSet().joinToString("") }.keys.first()
            }
            total += n
        }
        return total
    }
}
