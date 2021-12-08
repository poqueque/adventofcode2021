package days

import kotlin.math.abs


class Day07 : Day(7) {

    override fun partOne(): Any {
        val pos = inputString.split(",").map {it.toInt()!!}
        var minD = 100000000
        var posOk = 0
        for (i in pos.minOrNull()!! .. pos.maxOrNull()!!) {
            var d = 0
            for (p in pos) d+= abs(i-p)
            if (d < minD) {
                minD= d
                posOk = i
            }
        }
        return minD
    }

    override fun partTwo(): Any {
        val pos = inputString.split(",").map {it.toInt()!!}
        var minD = 100000000000000
        var posOk = 0
        for (i in pos.minOrNull()!! .. pos.maxOrNull()!!) {
            var d = 0L
            for (p in pos) {
                val s = abs(i-p)
                d += (s+1)*s/2
            }
            if (d < minD) {
                minD= d
                posOk = i
            }
        }
        return minD
    }
}
