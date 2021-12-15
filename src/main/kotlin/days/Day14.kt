package days

import kotlin.math.min

class Day14 : Day(14) {

    override fun partOne(): Any {
        var data = inputList[0]
        val rules = mutableMapOf<String,String>()

        inputList.forEach {
            if (it.contains("->")){
                val (a,b) = it.split(" -> ")
                rules[a] = b
            }
        }

        for (i in 1..10) {
            var newData = ""
            for  (c in data) {
                if (newData.isNotEmpty()) {
                    val r = ""+newData.last()+c
                    val c2 = rules[r]
                    if (c2 != null)
                        newData += c2

                }
                newData += c
            }
            data = newData
        }
        val totals = mutableMapOf<Char, Long>()
        for (c in data) {
            totals[c] = (totals[c] ?: 0) + 1
        }
        var maxC = ' '
        var minC = ' '
        var max = 0L
        var min = 100000000L
        for (t  in totals.keys) {
            if (totals[t]!! > max) {
                max = totals[t]!!
                maxC = t
            }
            if (totals[t]!! < min) {
                min = totals[t]!!
                minC = t
            }
        }
        return max - min
    }

    override fun partTwo(): Any {
        val data = inputList[0]
        val rules = mutableMapOf<String,String>()
        var pairs = mutableMapOf<String,Long>()
        var chars = mutableMapOf<Char,Long>()

        var pC = ' '
        for (d in data) {
            if (pC != ' ') {
                pairs[""+pC+d] = (pairs[""+pC+d] ?: 0) +1
            }
            pC = d
            chars[d] = (chars[d] ?: 0) +1
        }

        inputList.forEach {
            if (it.contains("->")){
                val (a,b) = it.split(" -> ")
                rules[a] = b
            }
        }

        for (i in 1..40) {
            val newPairs: MutableMap<String, Long> = mutableMapOf()
            pairs.forEach {
                val r = rules[it.key]!!
                newPairs[""+it.key[0]+r] = (newPairs[""+it.key[0]+r] ?: 0) + it.value
                newPairs[""+r+it.key[1]] = (newPairs[""+r+it.key[1]] ?: 0) + it.value
                chars[r[0]] = (chars[r[0]] ?: 0) + it.value
            }
            pairs = newPairs
        }

        var maxC = ' '
        var minC = ' '
        var max = 0L
        var min = 1000000000000L
        for (t  in chars.keys) {
            if (chars[t]!! > max) {
                max = chars[t]!!
                maxC = t
            }
            if (chars[t]!! < min) {
                min = chars[t]!!
                minC = t
            }
        }
        println("$maxC -> $max")
        println("$minC -> $min")
        return max - min
    }
}
