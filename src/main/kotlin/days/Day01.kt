package days

class Day01 : Day(1) {

    override fun partOne(): Any {
        var previous = 0
        var increases = 0
        for (measure in inputList) {
            val m = measure.toInt()
            if (m > previous) increases++
            previous = m
        }
        return increases
    }

    override fun partTwo(): Any {
        var previous = mutableListOf<Int>()
        var prevVal = 1000000
        var increases = 0
        for (measure in inputList) {
            val m = measure.toInt()
            previous.add(m)
            while (previous.size > 3) {
                previous = previous.drop(1).toMutableList()
            }
            if (previous.size == 3) {
                val total = previous.sum()
                if (total > prevVal) increases++
                prevVal = total
            }
        }
        return increases
    }
}
