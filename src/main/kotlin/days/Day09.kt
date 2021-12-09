package days

import util.Coor

class Day09 : Day(9) {

    override fun partOne(): Any {

        val map = mutableMapOf<Coor,Int>()

        var i = 0
        inputList.forEach {
            for (x in it.indices) {
                map[Coor(x,i)]=it[x].toString().toInt()
            }
            i++
        }
        var sum = 0
        for (y in 0 ..map.keys.map { it.y }.toList().maxOrNull()!!)
            for (x in 0 ..map.keys.map { it.x }.toList().maxOrNull()!!)
                if ((map[Coor(x-1,y)] ?: 10) > map[Coor(x,y)]!! &&
                    (map[Coor(x+1,y)] ?: 10) > map[Coor(x,y)]!! &&
                    (map[Coor(x,y-1)] ?: 10) > map[Coor(x,y)]!! &&
                    (map[Coor(x,y+1)] ?: 10) > map[Coor(x,y)]!!) sum+=1+map[Coor(x,y)]!!
        return sum
    }

    override fun partTwo(): Any {
        val map = mutableMapOf<Coor,Int>()

        var i = 0
        inputList.forEach {
            for (x in it.indices) {
                map[Coor(x,i)]=it[x].toString().toInt()
            }
            i++
        }

        val bottoms = mutableListOf<Coor>()
        for (y in 0 ..map.keys.map { it.y }.toList().maxOrNull()!!)
            for (x in 0 ..map.keys.map { it.x }.toList().maxOrNull()!!)
                if ((map[Coor(x-1,y)] ?: 10) > map[Coor(x,y)]!! &&
                    (map[Coor(x+1,y)] ?: 10) > map[Coor(x,y)]!! &&
                    (map[Coor(x,y-1)] ?: 10) > map[Coor(x,y)]!! &&
                    (map[Coor(x,y+1)] ?: 10) > map[Coor(x,y)]!!) bottoms.add(Coor(x,y))

        val basinSizes = mutableListOf<Int>()
        for (b in bottoms) {
            val basin = mutableListOf(b)
            var prevSize = 0
            while (basin.size > prevSize) {
                prevSize = basin.size
                for (bs in basin.toList()){
                    if ((map[Coor(bs.x-1,bs.y)] ?: 10)<9 && (map[Coor(bs.x-1,bs.y)] ?: 10) > map[bs]!! && !basin.contains(Coor(bs.x-1,bs.y))) basin.add(Coor(bs.x-1,bs.y))
                    if ((map[Coor(bs.x+1,bs.y)] ?: 10)<9 && (map[Coor(bs.x+1,bs.y)] ?: 10) > map[bs]!! && !basin.contains(Coor(bs.x+1,bs.y))) basin.add(Coor(bs.x+1,bs.y))
                    if ((map[Coor(bs.x,bs.y+1)] ?: 10)<9 && (map[Coor(bs.x,bs.y+1)] ?: 10) > map[bs]!! && !basin.contains(Coor(bs.x,bs.y+1))) basin.add(Coor(bs.x,bs.y+1))
                    if ((map[Coor(bs.x,bs.y-1)] ?: 10)<9 && (map[Coor(bs.x,bs.y-1)] ?: 10) > map[bs]!! && !basin.contains(Coor(bs.x,bs.y-1))) basin.add(Coor(bs.x,bs.y-1))
                }
            }
            basinSizes.add(basin.size)
        }
        val s = basinSizes.sortedDescending()
        return s[0]*s[1]*s[2]
    }
}
