package days

import Coor3
import kotlin.math.abs

class Day19 : Day(19) {

    class Scanner {
        var position: Coor3? = null
        var rotation: Int? = null
        val beacons = mutableListOf<Coor3>()
        fun add(c: Coor3) {
            beacons.add(c)
        }

        fun rotated(faced: Int): Scanner {
            val s = Scanner()
            for (b in beacons)
                s.add(face(b, faced))
            return s
        }

        fun move(c: Coor3): Scanner {
            val s = Scanner()
            for (b in beacons)
                s.add(b + c)
            return s
        }

        private fun face(c: Coor3, face: Int): Coor3 {
            if (face == 0) return Coor3(c.x, c.y, c.z)
            if (face == 1) return Coor3(-c.x, c.y, c.z)
            if (face == 2) return Coor3(c.x, -c.y, c.z)
            if (face == 3) return Coor3(c.x, c.y, -c.z)
            if (face == 4) return Coor3(-c.x, -c.y, c.z)
            if (face == 5) return Coor3(-c.x, c.y, -c.z)
            if (face == 6) return Coor3(c.x, -c.y, -c.z)
            if (face == 7) return Coor3(-c.x, -c.y, -c.z)

//            if (face >= 8) return rotate(face(c, face % 8), face / 8)
            if (face >= 8) return face(rotate(c, face / 8), face % 8)
            return Coor3(0, 0, 0)
        }

        private fun rotate(c: Coor3, rotated: Int): Coor3 {
            if (rotated == 0) return Coor3(c.x,c.y,c.z)
            if (rotated == 1) return Coor3(c.x,c.z,c.y)
            if (rotated == 2) return Coor3(c.y,c.x,c.z)
            if (rotated == 3) return Coor3(c.y,c.z,c.x)
            if (rotated == 4) return Coor3(c.z,c.x,c.y)
            if (rotated == 5) return Coor3(c.z,c.y,c.x)
            return rotate(c,rotated%6)
        }
    }

    private val scanners = mutableMapOf<Int, Scanner>()

    init {
        var s = 0
        inputList.forEach { line ->
            if (line.startsWith("---")) {
                s = line.split(" ")[2].toInt()
                if (scanners[s] == null) scanners[s] = Scanner()
            } else if (line.contains(",")) {
                val (a, b, c) = line.split(",").map { it.trim().toInt() }
                scanners[s]!!.add(Coor3(a, b, c))
            }
        }
        scanners[0]!!.position = Coor3(0, 0, 0)
        scanners[0]!!.rotation = 0
    }

    override fun partOne(): Any {
        val beacons = mutableListOf<Coor3>()
        beacons.addAll(scanners[0]!!.beacons)

        var bSize = 0
        while(beacons.size>bSize) {
            bSize = beacons.size
                for (j in scanners.keys.indices)
                        for (r in 0 until 48) {
                            val s = scanners[j]!!.rotated(r)
                            val difs = mutableListOf<Coor3>()
                            for (b1 in beacons)
                                for (b2 in s.beacons)
                                    difs.add(b2 - b1)
                            val coincidences: Map<Coor3, Int> =
                                difs.groupingBy { it }.eachCount().filter { it.value > 1 }
                            val difOk = coincidences.filter { it.value >= 4 }.keys.firstOrNull()
                            if (difOk != null) {
//                                println("FOUND: coincide ($j): $coincidences")
//                                println("Scanner $j on Scanner 0 coordinates")
                                for (b in s.beacons) {
                                    val c = b - difOk
                                    if (!beacons.contains(c)) {
                                        beacons.add(c)
//                                        println("Added $c from scanner $j")
                                    }
                                }
                                scanners[j]!!.position = Coor3(0,0,0) - difOk
                                scanners[j]!!.rotation = r
//                                println("Scanner $j abs POS: ${scanners[j]!!.position}")
                            }
                        }

        }
        beacons.sortBy { it.z }
        beacons.sortBy { it.y }
        beacons.sortBy { it.x }
//        println("${beacons.size} beacons found:")
//        for (b in beacons)
//            println(b)
        return beacons.size
    }

    override fun partTwo(): Any {
        var max = 0
        for (s1 in scanners.values)
            for (s2 in scanners.values) {
                var md = abs(s1.position!!.x - s2.position!!.x)
                md += abs(s1.position!!.y - s2.position!!.y)
                md += abs(s1.position!!.z - s2.position!!.z)
                if (md > max) max = md
            }

        return max
    }
}
