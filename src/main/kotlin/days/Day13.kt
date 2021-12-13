package days

import util.Coor

class Day13 : Day(13) {

    override fun partOne(): Any {
        val map = mutableListOf<Coor>()
        inputList.forEach { data ->
            if (data.contains(",")) {
                val (x, y) = data.split(",").map { it.toInt() }
                map.add(Coor(x, y))
            } else if (data.contains("fold")) {
                val d = data.split(" ")[2].split("=")
                if (d[0] == "x") {
                    val v = d[1].toInt()
                    val newMap = mutableListOf<Coor>()
                    for (point in map.toList()) {
                        if (point.x > v) point.x = 2 * v - point.x
                        newMap.add(point)
                    }
                    return newMap.distinct().size
                }
            }
        }
        return 0
    }

    override fun partTwo(): Any {
        var map = mutableListOf<Coor>()
        inputList.forEach { data ->
            if (data.contains(",")) {
                val (x, y) = data.split(",").map { it.toInt() }
                map.add(Coor(x, y))
            } else if (data.contains("fold")) {
                val d = data.split(" ")[2].split("=")
                if (d[0] == "x") {
                    val v = d[1].toInt()
                    val newMap = mutableListOf<Coor>()
                    for (point in map.toList()) {
                        if (point.x > v) point.x = 2 * v - point.x
                        newMap.add(point)
                    }
                    map = newMap.distinct().toMutableList()
                }
                if (d[0] == "y") {
                    val v = d[1].toInt()
                    val newMap = mutableListOf<Coor>()
                    for (point in map.toList()) {
                        if (point.y > v) point.y = 2 * v - point.y
                        newMap.add(point)
                    }
                    map = newMap.distinct().toMutableList()
                }
            }
        }
        for (y in 0..map.maxOf { it.y }) {
            for (x in 0..map.maxOf { it.x })
                if (map.contains(Coor(x, y))) print("#") else print(" ")
            println()
        }
        println()
        return "PGHRKLKL"
    }
}
