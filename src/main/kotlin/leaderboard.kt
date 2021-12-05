import util.Parser
import java.io.File
import java.util.*
import kotlin.math.min
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun main() {
    println("Advent of Code 2021")
    var textData: String? = null
    val file =File("_leaderboard.txt")
    if (file.exists()) {
        if (System.currentTimeMillis() - file.lastModified() < 900000)
            textData = file.readText()
    }
    if (textData == null) {
        val data = khttp.get(
            "https://adventofcode.com/2021/leaderboard/private/view/637864.json",
            cookies = mapOf(
                //            "_ga" to "GA1.2.703038879.1637396152",
                "session" to "53616c7465645f5fa49840d8dd3f656358929e03174acdc6204cac68982223259738154174dd3392268d716a2a6accae",
                //            "gid" to "GA1.2.594376790.1638268259"
            )
        )
        textData = data.text
        File("_leaderboard.txt").writeText(data.text)
    }
    val leaderboard = Parser.parse(textData)
    val completedDays = mutableMapOf<String, Int>()
    val totalTime = mutableMapOf<String, Long>()
    var maxDays = 0
    if (leaderboard != null) {
        val year = leaderboard.event.toInt()
        val members = leaderboard.members.count()
        val standings = mutableMapOf<String, Int>()
        var lastDay = mutableMapOf<String, Int>()
        println("Leaderboard $year")
        (1..25).forEach { day ->
            val calendar = GregorianCalendar()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, 11)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            calendar.set(Calendar.HOUR, 6)
            calendar.set(Calendar.MINUTE, 0)
            val millis = calendar.timeInMillis

            val dayPoints = mutableMapOf<String, Int>()
            val clasif1 = mutableMapOf<String, Long>()
            val clasif2 = mutableMapOf<String, Long>()
            leaderboard.members.values.forEach {
                if (it.completion_day_level.containsKey(day.toString())) {
                    val name = it.name ?: "<Unknown ${it.id}>"
                    val value1 = it.completion_day_level[day.toString()]?.`1`?.get_star_ts?.toLong() ?: 0L
                    val value2 = it.completion_day_level[day.toString()]?.`2`?.get_star_ts?.toLong() ?: 0L
                    if (value1 != 0L) clasif1[name] = min(value1 - millis/1000,86400)
                    if (value2 != 0L) clasif2[name] = min(value2 - millis/1000,86400)
                }
            }

            val sortedClasif1 = clasif1.toList().sortedBy { (_, value) -> value }.toMap()
            val sortedClasif2 = clasif2.toList().sortedBy { (_, value) -> value }.toMap()

            if (sortedClasif1.isNotEmpty() || sortedClasif2.isNotEmpty()) {
                var points = members
                sortedClasif1.entries.forEach {
                    standings[it.key] = (standings[it.key] ?: 0) + points
                    dayPoints[it.key] = points
                    points--
                }
                points = members
                sortedClasif2.entries.forEach {
                    standings[it.key] = (standings[it.key] ?: 0) + points
                    dayPoints[it.key] = (dayPoints[it.key] ?: 0) +points
                    points--
                }
                lastDay = dayPoints
            }

            if (sortedClasif1.isNotEmpty() || sortedClasif2.isNotEmpty()) {
                println("Day $day")
                println("======")
                println()
                println("[First Star]")
                sortedClasif1.entries.forEach {
                    println("${it.key} - ${humanReadable(it.value)}")
                    val cd = (completedDays[it.key] ?: 0) + 1
                    completedDays[it.key] = cd
                    if (cd > maxDays) maxDays = cd
                    totalTime[it.key] = (totalTime[it.key] ?: 0L) + it.value
                }
                println()
                println("[Second Star]")
                sortedClasif2.entries.forEach {
                    println("${it.key} - ${humanReadable(it.value)}")
                    val cd = (completedDays[it.key] ?: 0) + 1
                    completedDays[it.key] = cd
                    if (cd > maxDays) maxDays = cd
                    totalTime[it.key] = (totalTime[it.key] ?: 0L) + it.value
                }
                println()
            }
        }

        //Official
        val sortedOfficial = standings.toList().sortedByDescending { (_, value) -> value }.toMap()
        println("OFFICIAL")
        sortedOfficial.entries.forEach {
            if (completedDays[it.key] == maxDays)
                println("${it.key} - ${it.value} (${lastDay[it.key] ?: 0})")
            else
                println("${it.key} - ${it.value} [-${maxDays - (completedDays[it.key] ?: 0)}]")
        }

        println()

        //General
        val sortedGeneral = totalTime.toList().sortedBy { (_, value) -> value }.toMap()
        println("GENERAL")
        sortedGeneral.entries.forEach {
            if (completedDays[it.key] == maxDays)
                println("${it.key} - ${humanReadable(it.value)}")
            if (completedDays[it.key] == maxDays - 1)
                println("${it.key} - ${humanReadable(it.value)} [-1]")
            if (completedDays[it.key] == maxDays - 2)
                println("${it.key} - ${humanReadable(it.value)} [-2]")
        }
    }
}

fun humanReadable(spent: Long): String {
    var sec = spent
    var min = spent / 60
    sec -= min * 60
    val hour = min / 60
    min -= hour * 60
    var retVal = ""
    if (hour > 0) retVal += "$hour:"
    retVal += "${min.toString().padStart(2, '0')}:"
    retVal += sec.toString().padStart(2, '0')
    return retVal
}
