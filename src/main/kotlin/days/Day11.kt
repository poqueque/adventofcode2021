package days

import util.Coor

class Day11 : Day(11) {

    override fun partOne(): Any {

        val map = mutableMapOf<Coor, Int>()

        var i = 0
        inputList.forEach {
            for (x in it.indices) {
                map[Coor(x, i)] = it[x].toString().toInt()
            }
            i++
        }

//        printMap(map)

        var totalFlashes = 0

        for (step in 1..100) {
            val flashed = mutableListOf<Coor>()

            for (key in map.keys) map[key] = (map[key] ?: 0) + 1

            var prevLength = -1
            while (prevLength != flashed.size) {
                prevLength = flashed.size
                for (key in map.keys) {
                    if (map[key]!! > 9 && !flashed.contains(key)) {
                        flashed.add(key)
                        if (map[Coor(key.x - 1, key.y - 1)] != null) map[Coor(key.x - 1, key.y - 1)] =
                            map[Coor(key.x - 1, key.y - 1)]!! + 1
                        if (map[Coor(key.x - 1, key.y)] != null) map[Coor(key.x - 1, key.y)] =
                            map[Coor(key.x - 1, key.y)]!! + 1
                        if (map[Coor(key.x - 1, key.y + 1)] != null) map[Coor(key.x - 1, key.y + 1)] =
                            map[Coor(key.x - 1, key.y + 1)]!! + 1
                        if (map[Coor(key.x, key.y - 1)] != null) map[Coor(key.x, key.y - 1)] =
                            map[Coor(key.x, key.y - 1)]!! + 1
                        if (map[Coor(key.x, key.y + 1)] != null) map[Coor(key.x, key.y + 1)] =
                            map[Coor(key.x, key.y + 1)]!! + 1
                        if (map[Coor(key.x + 1, key.y - 1)] != null) map[Coor(key.x + 1, key.y - 1)] =
                            map[Coor(key.x + 1, key.y - 1)]!! + 1
                        if (map[Coor(key.x + 1, key.y)] != null) map[Coor(key.x + 1, key.y)] =
                            map[Coor(key.x + 1, key.y)]!! + 1
                        if (map[Coor(key.x + 1, key.y + 1)] != null) map[Coor(key.x + 1, key.y + 1)] =
                            map[Coor(key.x + 1, key.y + 1)]!! + 1
                    }
                }
            }
            for (key in flashed) {
                map[key] = 0
            }
//            println("After step $step:")
//            printMap(map)
            totalFlashes += flashed.size
        }

        return totalFlashes
    }

    private fun printMap(map: MutableMap<Coor, Int>) {
        for (y in 0 until 10) {
            for (x in 0 until 10)
                print(map[Coor(x, y)])
            println()
        }
        println()
    }

    override fun partTwo(): Any {


        val map = mutableMapOf<Coor, Int>()

        var i = 0
        inputList.forEach {
            for (x in it.indices) {
                map[Coor(x, i)] = it[x].toString().toInt()
            }
            i++
        }

//        printMap(map)

        for (step in 1 until 10000) {
            val flashed = mutableListOf<Coor>()

            for (key in map.keys) map[key] = (map[key] ?: 0) + 1

            var prevLength = -1
            while (prevLength != flashed.size) {
                prevLength = flashed.size
                for (key in map.keys) {
                    if (map[key]!! > 9 && !flashed.contains(key)) {
                        flashed.add(key)
                        if (map[Coor(key.x - 1, key.y - 1)] != null) map[Coor(key.x - 1, key.y - 1)] =
                            map[Coor(key.x - 1, key.y - 1)]!! + 1
                        if (map[Coor(key.x - 1, key.y)] != null) map[Coor(key.x - 1, key.y)] =
                            map[Coor(key.x - 1, key.y)]!! + 1
                        if (map[Coor(key.x - 1, key.y + 1)] != null) map[Coor(key.x - 1, key.y + 1)] =
                            map[Coor(key.x - 1, key.y + 1)]!! + 1
                        if (map[Coor(key.x, key.y - 1)] != null) map[Coor(key.x, key.y - 1)] =
                            map[Coor(key.x, key.y - 1)]!! + 1
                        if (map[Coor(key.x, key.y + 1)] != null) map[Coor(key.x, key.y + 1)] =
                            map[Coor(key.x, key.y + 1)]!! + 1
                        if (map[Coor(key.x + 1, key.y - 1)] != null) map[Coor(key.x + 1, key.y - 1)] =
                            map[Coor(key.x + 1, key.y - 1)]!! + 1
                        if (map[Coor(key.x + 1, key.y)] != null) map[Coor(key.x + 1, key.y)] =
                            map[Coor(key.x + 1, key.y)]!! + 1
                        if (map[Coor(key.x + 1, key.y + 1)] != null) map[Coor(key.x + 1, key.y + 1)] =
                            map[Coor(key.x + 1, key.y + 1)]!! + 1
                    }
                }
            }
            for (key in flashed) {
                map[key] = 0
            }
//            println("After step $step:")
//            printMap(map)
            if  (flashed.size == 100) return step
        }

        return 0
    }
}
