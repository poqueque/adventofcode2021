package util

fun String.splitrim(separator: String) : List<String> {
    return this.split(separator).map { it.trim() }
}


fun CharSequence.indexesOf(c: Char) : List<Int>{
    var index: Int = this.indexOf(c)
    val retVal = mutableListOf<Int>()
    while (index >= 0) {
        retVal.add(index)
        index = this.indexOf(c,startIndex = index + 1)
    }
    return retVal
}