package util

import com.beust.klaxon.Klaxon

object Parser {
    fun parse(json: String) : Leaderboard? {
        try {
            return Klaxon().parse<Leaderboard>(json)
        } catch (e: Exception){
            print (e.localizedMessage)
        }
        return null
    }
}

data class Leaderboard(
    val event: String,
    val members: Map<String,Member> = mapOf(),
    val owner_id: String
)

data class Member(
    val completion_day_level: Map<String,Day?>,
    val global_score: Int?,
    val id: String,
//    val last_star_ts: String? = null,
    val local_score: Int,
    val name: String?,
    val stars: Int
)

data class Day(
    val `1`: StarData?,
    val `2`: StarData? = null,
)

data class StarData(
    val get_star_ts: Int?
)
