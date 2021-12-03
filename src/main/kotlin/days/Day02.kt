package days

class Day02 : Day(2) {

    override fun partOne(): Any {
        var depth = 0
        var pos = 0

        for (a in inputList) {
            val b = a.split(" ")
            when (b[0]) {
                "forward" -> pos += b[1].toInt()
                "down" -> depth += b[1].toInt()
                "up" -> depth -= b[1].toInt()
            }
        }

        return depth * pos
    }

    override fun partTwo(): Any {
        var depth = 0
        var pos = 0
        var aim = 0

        for (a in inputList) {
            val b = a.split(" ")
            when (b[0]) {
                "forward" -> {
                    pos += b[1].toInt()
                    depth += aim * b[1].toInt()
                }
                "down" -> {
                    aim += b[1].toInt()
                }
                "up" -> {
                    aim -= b[1].toInt()
                }
            }
        }

        return depth * pos
    }
}
