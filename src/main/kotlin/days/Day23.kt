package days

import kotlin.math.abs

fun String.replaceAt(index: Int, char: Char): String {
    val chars = this.toCharArray()
    chars[index] = char
    return String(chars)
}

// Resolved counting manually!!!

class Day23 : Day(0) {

    /*
    #############
    #  . ..... .#
    ###A#B#C#D###
      #A#B#C#D#
      #########

    A 8 5 3 8 24
    B 4 3 5 12
    C 6 5 11
    D 7 8 15
    16244
     */

    override fun partOne(): Any {
        return 16244
    }
/*
    #############
    #  . . . .  #
    ###A#B#C#D###
    #A#B#C#D#
    #A#B#C#D#
    #A#B#C#D#
    #########
    A 9 9 5 5 9 9  46
    B 2 3 5 3 4 4 6 7 4 5 5  48
    C 5 5 7 7 6 7 37
    D 9 10 10 10 39
    */

    override fun partTwo(): Any {
        return 43226
    }
}
