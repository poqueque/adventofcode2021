package days

import kotlin.math.min

class Day21 : Day(21) {

    var pos1 = 4
    var pos2 = 8

    override fun partOne(): Any {
        var dice = 0
        var p1 = 0
        var p2 = 0
        var turnP1 = true
        while (p1 < 1000 && p2 < 1000) {
            dice ++
            if (turnP1) pos1 += dice%100 else pos2 += dice%100
            if (dice%100 == 0) if (turnP1) pos1 += 100 else pos2 += 100
            dice ++
            if (turnP1) pos1 += dice%100 else pos2 += dice%100
            if (dice%100 == 0) if (turnP1) pos1 += 100 else pos2 += 100
            dice ++
            if (turnP1) pos1 += dice%100 else pos2 += dice%100
            if (dice%100 == 0) if (turnP1) pos1 += 100 else pos2 += 100
            if (turnP1) p1 += pos1%10 else p2 += pos2%10
            if (turnP1 && pos1%10 == 0) p1 += 10
            if (!turnP1 && pos2%10 == 0) p2 += 10
            turnP1 = !turnP1
        }
        return (min(p1,p2)*dice)
    }

    data class GameState(val p1: Int, val p2: Int, val pos1: Int, val pos2: Int, val turn: Int)
    //turn 0,1,2 -> P1 3,4,5->P6

    private val cache = mutableMapOf<Pair<GameState,Int>,Pair<Long,Long>>()

    fun play (gs: GameState, dice: Int) : Pair<Long,Long> {
        var nextPos1 = gs.pos1
        var nextP1 = gs.p1
        if (gs.turn in 0..2) nextPos1 = (gs.pos1 + dice) % 10
        if (nextPos1 == 0) nextPos1 = 10
        if (gs.turn == 2) nextP1 += nextPos1
        var nextPos2 = gs.pos2
        var nextP2 = gs.p2
        if (gs.turn in 3..5) nextPos2 = (gs.pos2 + dice) % 10
        if (nextPos2 == 0) nextPos2 = 10
        if (gs.turn == 5) nextP2 += nextPos2
        val nextTurn = (gs.turn+1) % 6

        if (nextP1 > 20) {
            return Pair(1,0)
        }
        if (nextP2 > 20) {
            return Pair(0,1)
        }
        val nextGs = GameState(nextP1,nextP2,nextPos1,nextPos2,nextTurn)
        val t1 = cache[Pair(nextGs,1)] ?: play(nextGs,1)
        val t2 = cache[Pair(nextGs,2)] ?: play(nextGs,2)
        val t3 = cache[Pair(nextGs,3)] ?: play(nextGs,3)
        cache[Pair(nextGs,1)] = t1
        cache[Pair(nextGs,2)] = t2
        cache[Pair(nextGs,3)] = t3
        return (Pair(t1.first+t2.first+t3.first,t1.second+t2.second+t3.second))
    }

    override fun partTwo(): Any {
        val t1 = play(GameState(0,0,4,2,0),1)
        val t2 = play(GameState(0,0,4,2,0),2)
        val t3 = play(GameState(0,0,4,2,0),3)
        return (Pair(t1.first+t2.first+t3.first,t1.second+t2.second+t3.second))
    }
}
