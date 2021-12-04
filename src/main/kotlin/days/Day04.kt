package days

import util.Coor

class Day04 : Day(4) {

    override fun partOne(): Any {
        val data = inputList[0].split(",").map { it.toInt() }.toList()
        var i = 2
        val boards = mutableListOf<MutableMap<Coor,Int>>()
        while (i<inputList.size) {
            val map = mutableMapOf<Coor,Int>()
            for (j in 0..4){
                val n = inputList[i].split("[\\s]+".toRegex()).filter { it != ""}
                val nums = n.map{ it.toInt() }.toList()
                map[Coor(j,0)] = nums[0]
                map[Coor(j,1)] = nums[1]
                map[Coor(j,2)] = nums[2]
                map[Coor(j,3)] = nums[3]
                map[Coor(j,4)] = nums[4]
                i++
            }
            i++
            boards.add(map)
        }

        val appeared = mutableListOf<Int>()
        for (k in data.indices) {
            appeared.add(data[k])
            for (b in boards) {
                //columns
                for (row in 0..4) {
                    var a = 0
                    for (col in 0..4) {
                        if (appeared.contains(b[Coor(row,col)])) a++
                    }
                    if (a == 5) {
                        var sum = 0
                        for (d in b.values)
                            if (!appeared.contains(d)) sum += d
                        println("Column found in $k steps")
                        println(appeared)
                        for (col in 0..4)
                            print("${b[Coor(row,col)]} ")
                        println()
                        println(sum)
                        println(appeared.last())
                        return sum * appeared.last()
                    }
                }
                //rows
                for (col in 0..4) {
                    var a = 0
                    for (row in 0..4) {
                        if (appeared.contains(b[Coor(row,col)])) a++
                    }
                    if (a == 5) {
                        var sum = 0
                        for (d in b.values)
                            if (!appeared.contains(d)) sum += d
                        println("Row found in $k steps")
                        for (row in 0..4)
                            print("${b[Coor(row,col)]} ")
                        println()
                        return sum * appeared.last()
                    }
                }
            }
        }
        return 0
    }

    override fun partTwo(): Any {
        val data = inputList[0].split(",").map { it.toInt() }.toList()
        var i = 2
        val boards = mutableListOf<MutableMap<Coor,Int>>()
        while (i<inputList.size) {
            val map = mutableMapOf<Coor,Int>()
            for (j in 0..4){
                val n = inputList[i].split("[\\s]+".toRegex()).filter { it != ""}
                val nums = n.map{ it.toInt() }.toList()
                map[Coor(j,0)] = nums[0]
                map[Coor(j,1)] = nums[1]
                map[Coor(j,2)] = nums[2]
                map[Coor(j,3)] = nums[3]
                map[Coor(j,4)] = nums[4]
                i++
            }
            i++
            boards.add(map)
        }

        val appeared = mutableListOf<Int>()
        var score = 0
        for (k in data.indices) {
            appeared.add(data[k])
            for (b in boards.toList()) {
                //columns
                for (row in 0..4) {
                    var a = 0
                    for (col in 0..4) {
                        if (appeared.contains(b[Coor(row,col)])) a++
                    }
                    if (a == 5) {
                        var sum = 0
                        for (d in b.values)
                            if (!appeared.contains(d)) sum += d
                        println("Column found in $k steps")
                        println(appeared)
                        for (col in 0..4)
                            print("${b[Coor(row,col)]} ")
                        println()
                        println(sum)
                        println(appeared.last())
                        score =  sum * appeared.last()
                        boards.remove(b)
                    }
                }
                //rows
                for (col in 0..4) {
                    var a = 0
                    for (row in 0..4) {
                        if (appeared.contains(b[Coor(row,col)])) a++
                    }
                    if (a == 5) {
                        var sum = 0
                        for (d in b.values)
                            if (!appeared.contains(d)) sum += d
                        println("Row found in $k steps")
                        for (row in 0..4)
                            print("${b[Coor(row,col)]} ")
                        println()
                        score =  sum * appeared.last()
                        boards.remove(b)
                    }
                }
            }
        }
        return score
    }
}
