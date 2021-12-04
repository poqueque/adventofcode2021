package util
data class Coor4(val x: Int, val y: Int, val z: Int, val w: Int) {
    operator fun plus(d: Coor4): Coor4 {
        return Coor4(x + d.x, y + d.y, z + d.z, w + d.w)
    }

    operator fun plus(i: Int): Coor4 {
        return Coor4(x + i, y + i, z + i, w + i)
    }
    
    operator fun minus(d: Coor4): Coor4 {
        return Coor4(x - d.x, y - d.y, z - d.z, w - d.w)
    }
}