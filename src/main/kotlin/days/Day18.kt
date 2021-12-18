package days

class Day18 : Day(18) {

    fun add(s1: String, s2: String): String {
        var s3 = "[$s1,$s2]"
        var s4 = reduce1("[$s1,$s2]")
//        println("Reduced -> $s4")
        while (s4 != s3) {
            s3 = s4
            s4 = reduce1(s4)
//            println("Reduced -> $s4")
        }
        return s4
    }

    fun reduce(initS: String): String {
        var s1 = initS
        var s2 = reduce1(s1)
//        println("Reduced -> $s2")
        while (s1 != s2) {
            s1 = s2
            s2 = reduce1(s1)
//            println("Reduced -> $s2")
        }
//        println("Reduce done")
        return s2
    }

    fun reduce1(initS: String): String {
        var s = explode(initS)
        if (s == initS) s = split(s)
        return s
    }

    fun explode(s1: String): String {
        var newS = ""
        var level = 0
        var hasReplaced = false
        var lastIndexN = -1
        var nextIndexN = -1
        var toExplode = ""
        var a = 0
        var b = 0
        var i = 0
        while (i in s1.indices) {
            when (s1[i]) {
                '[' -> level++
                ']' -> level--
                in '0'..'9' -> if (level <= 4) lastIndexN = i
            }
            if (level > 4 && !hasReplaced)
                toExplode += s1[i]
            else {
                if (toExplode != "") {
                    val (strA,strB) = toExplode.replace("[","").split(",")
                    a = strA.toInt()
                    b = strB.toInt()
                    if (lastIndexN > 0){
                        var last = newS[lastIndexN].toString().toInt()
                        if (newS[lastIndexN-1] in '0'..'9') last += newS[lastIndexN - 1].toString().toInt() * 10
                        newS = newS.substring(0, if (last >= 10) lastIndexN-1 else lastIndexN) +
                                (a + last) +
                                newS.substring(lastIndexN + 1)
                    }
                    newS += "0"
                    toExplode = ""
                    hasReplaced = true
                } else {
                    if (hasReplaced && nextIndexN < 0 && s1[i] in '0'..'9') {
                        var k = s1[i].toString().toInt()
                        if (s1[i+1] in '0'..'9') {
                            k = k*10 + s1[i+1].toString().toInt()
                            i++
                        }
                        newS += "${b + k}"
                        nextIndexN = i
                    } else
                        newS += s1[i]
                }
            }
            i++
        }
        return newS
    }

    fun split(s: String): String {
        var s1 = ""
        var i = 0
        var replaced = false
        while (i < s.length) {
            when (s[i]) {
                '[' -> s1 += s[i]
                ']' -> s1 += s[i]
                ',' -> s1 += s[i]
                in '0'..'9' -> {
                    if (s[i+1] in '0'..'9' && !replaced) {
                        val k = s[i].toString().toInt() * 10 + s[i + 1].toString().toInt()
                        val a = k/2
                        val b = k/2 + k%2
                        s1 += "[$a,$b]"
                        replaced = true
                        i++
                    } else {
                        s1 += s[i]
                    }
                }
            }
            i++
        }
        return s1
    }

    override fun partOne(): Any {
        var s = ""
        inputList.forEach {
//            val rit = reduce(it)
            s = if (s == "") it else add(s, it)
//            println("Added $it -> $s")
        }
        var s1 = s
        return magnitude(s1)
    }

    private fun magnitude(s1: String): Int {

        var values = listOf<Int>()
        val magnitudes = mutableMapOf<String, Int>()

        var s2 = s1
        while (s2.contains("[")) {
            var s = s2
            s = s.replace("]",",")
            s = s.replace("[",",")
            while (s.contains(",,")) s = s.replace(",,",",")
            values = s.split(",").filter { it != "" }.map { it.toInt() }.distinct()
            for (i in values)
                for (j in values)
                    magnitudes["[$i,$j]"] = 3 * i + 2 * j

            for (m in magnitudes.keys) {
                s2 = s2.replace(m, magnitudes[m].toString())
            }
//            println(s2)
        }
        return s2.toInt()
    }

    override fun partTwo(): Any {
        var s = ""
        var max = 0
        for (i in inputList.indices)
            for (j in inputList.indices)
                if (i!=j){
                    val m = magnitude(add(inputList[i], inputList[j]))
                    if (m > max) {
                        max = m
//                        println("$i,$j -> $max")
                    }
        }
        return max
    }
}
