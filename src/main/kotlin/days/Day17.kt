package days

import util.Coor

class Day17 : Day(17) {

//    val targetAreaX = 20..30
//    val targetAreaY = -10 .. -5

    private val targetAreaX = 240..292
    private val targetAreaY = -90 .. -57

    override fun partOne(): Any {
        var maxHeight = 0
        for (x in 6..100)
            for (y in 9..100) {
                val height = findHeight(x, y)
                val hits = hitsTarget(x, y)
                if (hits && height > maxHeight)
                    maxHeight = height
            }
        return maxHeight
    }

    private fun findHeight(x: Int, y: Int): Int {
        val pos = Coor(0,0)
        val velocity = Coor(x,y)
        var height = 0
        var step = 0
        while (step < 1000) {
            pos.x = pos.x+velocity.x
            pos.y = pos.y+velocity.y
            if (velocity.x > 0) velocity.x = velocity.x -1
            if (velocity.x < 0) velocity.x = velocity.x +1
            velocity.y = velocity.y -1
            if (pos.y > height)
                height = pos.y
            else
                return height
            step ++
        }
        return -1
    }

    private fun hitsTarget(x: Int, y: Int): Boolean {
        val pos = Coor(0,0)
        val velocity = Coor(x,y)
        var step = 0
        while (step < 1000) {
            pos.x = pos.x+velocity.x
            pos.y = pos.y+velocity.y
            if (velocity.x > 0) velocity.x = velocity.x -1
            if (velocity.x < 0) velocity.x = velocity.x +1
            velocity.y = velocity.y -1
            if (pos.x in targetAreaX && pos.y in targetAreaY) return true
            if (pos.x > targetAreaX.last) return false
            step ++
        }
        return false
    }

    override fun partTwo(): Any {
        var totalHits = 0
        for (x in 1..2000)
            for (y in -2000..2000) {
                val hits = hitsTarget(x, y)
                if (hits)
                    totalHits++
            }
        return totalHits
    }
}
