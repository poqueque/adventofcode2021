package days

import util.Coor

// Using Dijkstra's algorithm
// https://es.wikipedia.org/wiki/Algoritmo_de_Dijkstra

class Day15 : Day(15) {

    val size = 100

    override fun partOne(): Any {
        val map = mutableMapOf<Coor, Int>()
        val shortest = mutableMapOf<Coor, Int>()
        val pending = mutableListOf<Coor>()

        var i = 0
        inputList.forEach {
            for (x in it.indices) {
                map[Coor(x, i)] = it[x].toString().toInt()
                shortest[Coor(x, i)] = 10000000
                pending.add(Coor(x,i))
            }
            i++
        }

        val start = Coor(0,0)
        val end = Coor(size -1,size -1)
        shortest[start] = 0
        while (pending.size > 0){
            val coor = pending[0]
            if (map[Coor(coor.x+1,coor.y)] != null && shortest[Coor(coor.x,coor.y)]!! + map[Coor(coor.x+1,coor.y)]!! < shortest[Coor(coor.x+1,coor.y)]!!) {
                shortest[Coor(coor.x+1,coor.y)] = shortest[Coor(coor.x,coor.y)]!! + map[Coor(coor.x+1,coor.y)]!!
            }
            if (map[Coor(coor.x,coor.y+1)] != null && shortest[Coor(coor.x,coor.y)]!! + map[Coor(coor.x,coor.y+1)]!! < shortest[Coor(coor.x,coor.y+1)]!!) {
                shortest[Coor(coor.x, coor.y+1)] = shortest[Coor(coor.x,coor.y)]!! + map[Coor(coor.x,coor.y+1)]!!
            }
            pending.removeAt(0)
        }
        return shortest[end]!!
    }

    override fun partTwo(): Any {
        val map = mutableMapOf<Coor, Int>()
        val shortest = mutableMapOf<Coor, Int>()
        val pending = mutableListOf<Coor>()
        val calculated = mutableListOf<Coor>()

        var i = 0
        inputList.forEach {
            for (x in it.indices) {
                for (dx in 0 .. 4)
                    for (dy in 0..4) {
                        var sum = it[x].toString().toInt()+(dx+dy)
                        while (sum > 9)
                            sum -= 9
                        if (map[Coor(x+size*dx, i+size*dy)] != null)
                            println(Coor(x+size*dx, i+size*dy))
                        map[Coor(x+size*dx, i+size*dy)] = sum
                        shortest[Coor(x+size*dx, i+size*dy)] = 1000000000
                        pending.add(Coor(x+size*dx, i+size*dy))
                    }
            }
            i++
        }

/*        for (x in 0 until size*5) {
            for (y in 0 until size * 5)
                print(map[Coor(y,x)])
            println()
        }*/

        val start = Coor(0,0)
        val end = Coor(size*5-1,size*5-1)
        shortest[start] = 0
        pending.sortBy { c -> c.x + c.y }
        calculated.add(start)
        while (pending.size > 0){
            val coor = calculated.minByOrNull { c -> shortest[c]!! } !!
            if (pending.size % 1000 == 0) {
                println("pending.size = ${pending.size}")
            }
            if (map[Coor(coor.x+1,coor.y)] != null && shortest[Coor(coor.x,coor.y)]!! + map[Coor(coor.x+1,coor.y)]!! < shortest[Coor(coor.x+1,coor.y)]!!) {
                shortest[Coor(coor.x+1,coor.y)] = shortest[Coor(coor.x,coor.y)]!! + map[Coor(coor.x+1,coor.y)]!!
                calculated.add(Coor(coor.x+1,coor.y))
            }
            if (map[Coor(coor.x,coor.y+1)] != null && shortest[Coor(coor.x,coor.y)]!! + map[Coor(coor.x,coor.y+1)]!! < shortest[Coor(coor.x,coor.y+1)]!!) {
                shortest[Coor(coor.x, coor.y+1)] = shortest[Coor(coor.x,coor.y)]!! + map[Coor(coor.x,coor.y+1)]!!
                calculated.add(Coor(coor.x, coor.y+1))
            }
            if (map[Coor(coor.x-1,coor.y)] != null && shortest[Coor(coor.x,coor.y)]!! + map[Coor(coor.x-1,coor.y)]!! < shortest[Coor(coor.x-1,coor.y)]!!) {
                shortest[Coor(coor.x-1, coor.y)] = shortest[Coor(coor.x,coor.y)]!! + map[Coor(coor.x-1,coor.y)]!!
                calculated.add(Coor(coor.x-1, coor.y))
            }
            if (map[Coor(coor.x,coor.y-1)] != null && shortest[Coor(coor.x,coor.y)]!! + map[Coor(coor.x,coor.y-1)]!! < shortest[Coor(coor.x,coor.y-1)]!!) {
                shortest[Coor(coor.x, coor.y-1)] = shortest[Coor(coor.x,coor.y)]!! + map[Coor(coor.x,coor.y-1)]!!
                calculated.add(Coor(coor.x, coor.y-1))
            }
            pending.remove(coor)
            calculated.remove(coor)
        }
        return shortest[end]!!

    }
}
