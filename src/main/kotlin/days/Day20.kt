package days

import printMap
import util.Coor

class Day20 : Day(20) {

    val algorithm: String = inputList[0]
    val input = mutableMapOf<Coor, Int>()
    val C = listOf(".", "#")

    init {
        var height = 0
        var width = 0
        inputList.forEachIndexed() { i, it ->
            if (i > 1) {
                //input[Coor(-1,i)] = 0
                //input[Coor(-2,i)] = 0
                it.forEachIndexed { x, c ->
                    input[Coor(x, i)] = if (c == '#') 1 else 0
                }
                if (it.length > width) width = it.length
                //input[Coor(width,i)] = 0
                //input[Coor(width+1,i)] = 0
            }
            height = i
        }
        //printMap(input.mapValues { C[it.value] })
    }

    override fun partOne(): Any {
        val output = applyAlgorithm(input, 1)
        val output1 = applyAlgorithm(output, 2)
        return output1.values.count { it == 1 }
    }

    private fun applyAlgorithm(input: MutableMap<Coor, Int>, step: Int): MutableMap<Coor, Int> {
        val output = mutableMapOf<Coor, Int>()
        val minX = input.keys.minOf { it.x }
        val minY = input.keys.minOf { it.y }
        val maxX = input.keys.maxOf { it.x }
        val maxY = input.keys.maxOf { it.y }
        val dv = if (step % 2 == 0 && algorithm[0] == '#') 1 else 0
        for (x in minX - 1..maxX + 1) {
            for (y in minY - 1..maxY + 1) {
                var binData = (input[Coor(x - 1, y - 1)] ?: dv).toString()
                binData += (input[Coor(x, y - 1)] ?: dv).toString()
                binData += (input[Coor(x + 1, y - 1)] ?: dv).toString()
                binData += (input[Coor(x - 1, y)] ?: dv).toString()
                binData += (input[Coor(x, y)] ?: dv).toString()
                binData += (input[Coor(x + 1, y)] ?: dv).toString()
                binData += (input[Coor(x - 1, y + 1)] ?: dv).toString()
                binData += (input[Coor(x, y + 1)] ?: dv).toString()
                binData += (input[Coor(x + 1, y + 1)] ?: dv).toString()
                val num = binData.toInt(2)
                output[Coor(x, y)] = if (algorithm[num] == '#') 1 else 0
            }
        }
        //printMap(output.mapValues { it -> C[it.value] })
        return output
    }

    override fun partTwo(): Any {
        var output = input
        for(i in 1..50) {
            output = applyAlgorithm(output, i)
        }
        return output.values.count { it == 1 }
    }
}
