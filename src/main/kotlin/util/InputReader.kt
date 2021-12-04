package util

import java.io.File
import java.net.URL

object InputReader {

    fun getInputAsString(day: Int): String {
        return fromResources(day).readText()
    }

    fun getInputAsList(day: Int): List<String> {
        return fromResources(day).readLines()
    }

    fun getInputAsMap(day: Int): CoorMap {
        return CoorMap(getInputAsList(day))
    }

    fun getLeaderboard(): String {
        return leaderBoardFromResources().readText()
    }

    //Not working - Need to manage login
    fun savePuzzleInput(day: Int) {
        System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0")
        val data = URL("https://adventofcode.com/2020/day/$day/input").readText()
        fromResources(day).writeText(data)
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun fromResources(day: Int): File {
        return File(javaClass.classLoader.getResource("input_day_${day.toString().padStart(2, '0')}.txt").toURI())
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun leaderBoardFromResources(): File {
        return File(javaClass.classLoader.getResource("_leaderboard.txt").toURI())
    }
}
