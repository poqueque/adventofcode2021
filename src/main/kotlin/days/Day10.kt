package days

class Day10 : Day(10) {

    override fun partOne(): Any {
        var total = 0
        inputList.forEach {
            total += process(it)
        }
        return total
    }

    private fun process(it: String): Int {
        var opening = ""
        it.forEach {
            when (it) {
                '{' -> opening += it
                '(' -> opening += it
                '[' -> opening += it
                '<' -> opening += it
                '}' -> {
                    if (opening.last() == '{') opening = opening.dropLast(1)
                    else return 1197
                }
                ')' -> {
                    if (opening.last() == '(') opening = opening.dropLast(1)
                    else return 3
                }
                ']' -> {
                    if (opening.last() == '[') opening = opening.dropLast(1)
                    else return 57
                }
                '>' -> {
                    if (opening.last() == '<') opening = opening.dropLast(1)
                    else return 25137
                }
            }
        }
        return 0
    }


    private fun processIncomplete(it: String): Long {
        var opening = ""
        it.forEach {
            when (it) {
                '{' -> opening += it
                '(' -> opening += it
                '[' -> opening += it
                '<' -> opening += it
                '}' -> {
                    if (opening.last() == '{') opening = opening.dropLast(1)
                    else return 0
                }
                ')' -> {
                    if (opening.last() == '(') opening = opening.dropLast(1)
                    else return 0
                }
                ']' -> {
                    if (opening.last() == '[') opening = opening.dropLast(1)
                    else return 0
                }
                '>' -> {
                    if (opening.last() == '<') opening = opening.dropLast(1)
                    else return 0
                }
            }
        }
        var total = 0L
        while (opening.isNotEmpty()) {
            total *= 5
            total += when (opening.last()) {
                '(' -> 1
                '[' -> 2
                '{' -> 3
                '<' -> 4
                else -> 0
            }
            opening = opening.dropLast(1)
        }
        return total
    }

    override fun partTwo(): Any {
        val total = mutableListOf<Long>()
        inputList.forEach {
            val t = processIncomplete(it)
            if (t > 0L) total.add(t)
        }
        println(total)
        println(total.sorted())
        println(total.size)
        return total.sorted()[(total.size - 1) / 2]
    }
}
