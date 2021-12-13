package days

class Day12 : Day(12) {

    private val connections = mutableListOf<Pair<String, String>>()

    init {
        inputList.forEach {
            connections.add(Pair(it.split("-")[0], it.split("-")[1]))
        }
    }

    override fun partOne(): Any {
        val path = mutableListOf("start")

        val validPaths = getValidPaths(path)

        return validPaths.size
    }

    private fun getValidPaths(path: MutableList<String>): MutableList<MutableList<String>> {
        val pointer = path.last()
        val allPaths = mutableListOf<MutableList<String>>()
        if (pointer == "end") return mutableListOf(path)
        for (c in connections) {
            if (c.first == pointer && (c.second != c.second.toLowerCase() || !path.contains(c.second))) {
                val p = path.toMutableList()
                p.add(c.second)
                allPaths.addAll(getValidPaths(p))
            }
            if (c.second == pointer && (c.first != c.first.toLowerCase() || !path.contains(c.first))) {
                val p = path.toMutableList()
                p.add(c.first)
                allPaths.addAll(getValidPaths(p))
            }
        }
        return allPaths
    }

    override fun partTwo(): Any {
        val path = mutableListOf("start")

        val validPaths = getValidPaths2(path)

        return validPaths.size
    }

    private fun getValidPaths2(path: MutableList<String>): MutableList<MutableList<String>> {
        val pointer = path.last()
        val allPaths = mutableListOf<MutableList<String>>()
        if (pointer == "end") return mutableListOf(path)
        for (c in connections) {
            val small = path.filter { it == it.toLowerCase() }
            var allowed = 1
            if (small.size == small.distinct().size) allowed = 2
            if (c.first == pointer && (c.second != c.second.toLowerCase() || path.count { it == c.second } < allowed) && c.second != "start") {
                val p = path.toMutableList()
                p.add(c.second)
                allPaths.addAll(getValidPaths2(p))
            }
            if (c.second == pointer && (c.first != c.first.toLowerCase() || path.count { it == c.first } < allowed) && c.first != "start") {
                val p = path.toMutableList()
                p.add(c.first)
                allPaths.addAll(getValidPaths2(p))
            }
        }
        return allPaths
    }
}
