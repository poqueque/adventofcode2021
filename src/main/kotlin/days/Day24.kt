package days

import kotlin.math.abs

class Day24 : Day(24) {

    val ins = mutableMapOf<Int, MutableList<String>>()

    //    data class State(val v: Map<String, Int>)
    val cache = MutableList(14) { mutableMapOf<Pair<Int, Int>, Int>() }

    init {
        var d = -1
        for (s in inputList) {
            if (s.startsWith("inp")) {
                d++
                ins[d] = mutableListOf()
            }
            ins[d]!!.add(s)
        }

        val k = 0
        for (z in 1..900)
            for (i in 1..9)
               if (f(z, k, i) != f0(z, i))
                    println("f($z,$k,$i) = ${f(z, k, i)} = ${f0(z, i)}")

        println("INIT OK")
    }

    /*
        override fun partOne(): Any {

            val max = "88888888888888".toLong(9)
            println(max)

            val res1 = mutableListOf<MutableMap<String,Int>>()
            val initState = State(mutableMapOf("w" to 0, "x" to 0, "y" to 0, "z" to 0))

            for (i in 0..13){

            }


        }
        */

    private fun f0(z: Int, inp: Int): Int = if (z % 26 + 10 == inp) z else 26*z + inp + 5
    private fun f1(z: Int, inp: Int): Int = z * 26 + inp + 9
    private fun f2(z: Int, inp: Int): Int = z * 26 + inp + 4
    private fun f3(z: Int, inp: Int): Int =
        if (z % 26 - 12 == inp) z/26 else 26 * (z / 26) + inp + 4
    private fun f4(z: Int, inp: Int): Int = z * 26 + inp + 10
    private fun f5(z: Int, inp: Int): Int =
        if (z % 26 - 13 == inp) z / 26 else 26*(z / 26) + inp + 14

    private fun f6(z: Int, inp: Int) =
        if (z % 26 - 9 == inp) z / 26 else 26 * (z / 26) + inp + 14

    private fun f7(z: Int, inp: Int) =
        if (z % 26 - 12 == inp) z / 26 else 26 * (z / 26) + inp + 12

    private fun f8(z: Int, inp: Int) =
        if (z % 26 + 14 == inp) z else 26 * z + inp + 14

    private fun f9(z: Int, inp: Int) =
        if (z % 26 - 9 == inp) z / 26 else 26 * (z / 26) + inp + 14

    private fun f10(z: Int, inp: Int) =
        if (z % 26 + 15 == inp) z else 26 * z + inp + 5

    private fun f11(z: Int, inp: Int) =
        if (z % 26 + 11 == inp) z else 26 * z + inp + 10

    private fun f12(z: Int, inp: Int) =
        if (z % 26 - 16 == inp) z / 26 else 26 * (z / 26) + inp + 8

    private fun f13(z: Int, inp: Int) =
        if (z % 26 - 2 == inp) z / 26 else 26 * (z / 26) + inp + 15

    private fun f(z: Int, step: Int, inp: Int): Int {
//        val v = mutableMapOf("w" to 0, "x" to 0, "y" to 0, "z" to 0)
        if (cache[step][Pair(z, inp)] != null) return cache[step][Pair(z, inp)]!!
        if (step == 0) return f0(z, inp)
        if (step == 1) return f1(z, inp)
        if (step == 2) return f2(z, inp)
        if (step == 3) return f3(z, inp)
        if (step == 4) return f4(z, inp)
        if (step == 5) return f5(z, inp)
        if (step == 6) return f6(z, inp)
        if (step == 7) return f7(z, inp)
        if (step == 8) return f8(z, inp)
        if (step == 9) return f9(z, inp)
        if (step == 10) return f10(z, inp)
        if (step == 11) return f11(z, inp)
        if (step == 12) return f12(z, inp)
        if (step == 13) return f13(z, inp)
        val v = mutableMapOf("w" to 0, "x" to 0, "y" to 0, "z" to z)
        for (s in ins[step]!!) {
            val d = s.split(" ")
            when (d[0]) {
                "inp" -> {
                    v[d[1]] = inp
                }
                "add" -> {
                    var a = 0
                    if (d[2] in "w".."z") a = v[d[2]]!!
                    else a = d[2].toInt()
                    v[d[1]] = v[d[1]]!! + a
                }
                "mul" -> {
                    var a = 0
                    if (d[2] in "w".."z") a = v[d[2]]!!
                    if (d[2] in "0".."9") a = d[2].toInt()
                    v[d[1]] = v[d[1]]!! * a
                }
                "div" -> {
                    var a = 0
                    if (d[2] in "w".."z") a = v[d[2]]!!
                    if (d[2] in "0".."9") a = d[2].toInt()
                    v[d[1]] = v[d[1]]!! / a
                }
                "mod" -> {
                    var a = 0
                    if (d[2] in "w".."z") a = v[d[2]]!!
                    if (d[2] in "0".."9") a = d[2].toInt()
                    v[d[1]] = v[d[1]]!! % a
                }
                "eql" -> {
                    var a = 0
                    if (d[2] in "w".."z") a = v[d[2]]!!
                    if (d[2] in "0".."9") a = d[2].toInt()
                    v[d[1]] = if (v[d[1]]!! == a) 1 else 0
                }
            }
        }
        cache[step][Pair(z, inp)] = v["z"]!!
        return v["z"]!!
    }

    override fun partOne(): Any {
       var valsOk=  mutableListOf(0)
        for (step in 13 downTo 0) {
            println("step $step: $valsOk")
            val nextValsOk = mutableListOf<Int>()
            for (z in -10000..10000)
                for (i in 1..9) {
                    val a = f(z, step, i)
                    if (valsOk.contains(a)) {
                        println("f($z,$step,$i) = $a")
                        if (!nextValsOk.contains(z)) nextValsOk.add(z)
                    }
                }
            valsOk = nextValsOk
        }

        return 99919692496939
    }

    private fun runStep(z: Int, data: String): Boolean {
        if (data.length == 14) {
            if (data.endsWith("111111111")) println(data)
            if (z == 0) println(data)
            return (z == 0)
        }
        for (i in 9 downTo 1) {
            val res = runStep(f(z, data.length, i), data + "$i")
            if (res) {
                println("FOUND: $data")
                return true
            }
        }
        return false
    }

    override fun partTwo(): Any {

        return 81914111161714
    }
}
