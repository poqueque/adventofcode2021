package util

class GameConsole (val code: List<String>){

    var accumulator = 0
    var pointer = 0
    var visited = mutableListOf<Int>()

    fun run () : Pair<Boolean,Int> {
        do {
            val (instruction, valueStr) = code[pointer].splitrim(" ")
            val value = valueStr.toInt()
            when (instruction){
                "nop" -> pointer++
                "acc" -> { accumulator += value; pointer ++}
                "jmp" -> { pointer += value }
            }
            if (visited.contains(pointer)) return Pair(false,accumulator)
            visited.add(pointer)
        } while (pointer < code.size)
        return Pair(true,accumulator)
    }

}
