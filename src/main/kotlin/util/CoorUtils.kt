import util.Coor

enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Coor3(val x: Int, val y: Int, val z: Int) {
    operator fun plus(d: Coor3): Coor3 {
        return Coor3(x + d.x, y + d.y, z + d.z)
    }

    operator fun plus(i: Int): Coor3 {
        return Coor3(x + i, y + i, z + i)
    }

    operator fun minus(d: Coor3): Coor3 {
        return Coor3(x - d.x, y - d.y, z - d.z)
    }
}

fun printMap(input: Map<Coor,String>) {
    val minX = input.keys.minOf { it.x }
    val minY = input.keys.minOf { it.y }
    val maxX = input.keys.maxOf { it.x }
    val maxY = input.keys.maxOf { it.y }
    for (y in minY ..maxY ) {
        for (x in minX ..maxX ) {
            print(input[Coor(x, y)])
        }
        println()
    }
    println()
}
