package days

import Coor3
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day22 : Day(22) {

    data class Cube(val x: IntRange, val y: IntRange, val z: IntRange) {
        fun within(c: Cube): Boolean {
            return (x.first in c.x && x.last in c.x &&
                    y.first in c.y && y.last in c.y &&
                    z.first in c.z && z.last in c.z)
        }

        fun intersect(c: Cube): Cube? {
            val ax = max(x.first, c.x.first)
            val ay = max(y.first, c.y.first)
            val az = max(z.first, c.z.first)
            val aX = min(x.last, c.x.last)
            val aY = min(y.last, c.y.last)
            val aZ = min(z.last, c.z.last)
            if (ax <= aX && ay <= aY && az <= aZ) return Cube(ax..aX, ay..aY, az..aZ)
            return null
        }

        fun intersects(c: Cube): Boolean {
            val irx = (x.first..x.last).intersect(x)
            val iry = (y.first..y.last).intersect(y)
            val irz = (z.first..z.last).intersect(z)
            return irx.size * iry.size * irz.size > 0
        }

        fun size(): Long {
            return (1 + x.last.toLong() - x.first.toLong()) *
                    (1 + y.last.toLong() - y.first.toLong()) *
                    (1 + z.last.toLong() - z.first.toLong())
        }
    }

    private var ranges = mutableListOf<Cube>()
    private var counts = mutableMapOf<Cube, Int>()

    override fun partOne(): Any {

        var i = 0
        inputList.forEach { line ->
            i++
            val (a, b) = line.split(" ")
            val (c, d, e) = b.split(",")
            val (x1, x2) = c.split("=")[1].split("..").map { it.toInt() }
            val (y1, y2) = d.split("=")[1].split("..").map { it.toInt() }
            val (z1, z2) = e.split("=")[1].split("..").map { it.toInt() }
            if (abs(x1) <= 50 && abs(y1) <= 50 && abs(z1) <= 50) {
                val newCube = Cube(x1..x2, y1..y2, z1..z2)
                for (r in counts.keys.toList()) {
                    val inter = r.intersect(newCube)
                    if (inter != null) {
                        counts[inter] = (counts[inter] ?: 0) - (counts[r] ?: 0)
                    }
                }
                if (a == "on") counts[newCube] = (counts[newCube] ?: 0) + 1
            }
        }
        return counts.map { entry -> entry.value * entry.key.size() }.sum()
    }

    override fun partTwo(): Any {
        var i = 0
        inputList.forEach { line ->
            i++
            val (a, b) = line.split(" ")
            val (c, d, e) = b.split(",")
            val (x1, x2) = c.split("=")[1].split("..").map { it.toInt() }
            val (y1, y2) = d.split("=")[1].split("..").map { it.toInt() }
            val (z1, z2) = e.split("=")[1].split("..").map { it.toInt() }
            val newCube = Cube(x1..x2, y1..y2, z1..z2)
            for (r in counts.keys.toList()) {
                val inter = r.intersect(newCube)
                if (inter != null) {
                    counts[inter] = (counts[inter] ?: 0) - (counts[r] ?: 0)
                }
            }
            if (a == "on") counts[newCube] = (counts[newCube] ?: 0) + 1
        }
        return counts.map { entry -> entry.value * entry.key.size() }.sum()
    }
}

inline fun <T> Iterable<T>.sumByLong(selector: (T) -> Long): Long {
    var sum = 0L
    for (element in this) {
        sum += selector(element)
    }
    return sum
}
