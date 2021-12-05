package days

import util.Coor

class Day05 : Day(5) {

    override fun partOne(): Any {
        val data = mutableMapOf<Coor, Int>()
        inputList.forEach {
            val (start, stop) = it.split(" -> ").map { it.split(",") }
            val s = Coor(start[0].toInt(), start[1].toInt())
            val e = Coor(stop[0].toInt(), stop[1].toInt())
            if (s.x == e.x) {
                for (i in s.y..e.y) {
                    data[Coor(s.x, i)] = (data[Coor(s.x, i)] ?: 0) + 1
                }
                for (i in e.y..s.y) {
                    data[Coor(s.x, i)] = (data[Coor(s.x, i)] ?: 0) + 1
                }
            }
            if (s.y == e.y) {
                for (i in s.x..e.x) {
                    data[Coor(i, s.y)] = (data[Coor(i, s.y)] ?: 0) + 1
                }
                for (i in e.x..s.x) {
                    data[Coor(i, s.y)] = (data[Coor(i, s.y)] ?: 0) + 1
                }
            }
        }

        return data.values.count { it > 1 }
    }

    override fun partTwo(): Any {
        val data = mutableMapOf<Coor, Int>()
        inputList.forEach {
            val (start, stop) = it.split(" -> ").map { it.split(",") }
            val s = Coor(start[0].toInt(), start[1].toInt())
            val e = Coor(stop[0].toInt(), stop[1].toInt())
            if (s.x == e.x) {
                for (i in s.y..e.y) {
                    data[Coor(s.x, i)] = (data[Coor(s.x, i)] ?: 0) + 1
                }
                for (i in e.y..s.y) {
                    data[Coor(s.x, i)] = (data[Coor(s.x, i)] ?: 0) + 1
                }
            } else if (s.y == e.y) {
                for (i in s.x..e.x) {
                    data[Coor(i, s.y)] = (data[Coor(i, s.y)] ?: 0) + 1
                }
                for (i in e.x..s.x) {
                    data[Coor(i, s.y)] = (data[Coor(i, s.y)] ?: 0) + 1
                }
            } else {
                var i = Coor(s.x, s.y)
                var dir = Coor(0, 0)
                if (s.x < e.x && s.y < e.y) dir = Coor(1, 1)
                if (s.x > e.x && s.y < e.y) dir = Coor(-1, 1)
                if (s.x < e.x && s.y > e.y) dir = Coor(1, -1)
                if (s.x > e.x && s.y > e.y) dir = Coor(-1, -1)
                data[i] = (data[i] ?: 0) + 1
                while (i != e) {
                    i += dir
                    data[i] = (data[i] ?: 0) + 1
                }
            }
        }
/*        for (i in 0 .. data.keys.maxOf { it.y }) {
            for (j in 0 .. data.keys.maxOf { it.x })
                print (data[Coor(j,i)] ?: ".")
            println ()
        }
        println()*/
        return data.values.count { it > 1 }
    }
}
