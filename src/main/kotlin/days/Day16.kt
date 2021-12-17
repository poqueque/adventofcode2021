package days

import java.math.BigInteger

class Day16 : Day(16) {

    private val C = mapOf(
        '0' to "0000",
        '1' to "0001",
        '2' to "0010",
        '3' to "0011",
        '4' to "0100",
        '5' to "0101",
        '6' to "0110",
        '7' to "0111",
        '8' to "1000",
        '9' to "1001",
        'A' to "1010",
        'B' to "1011",
        'C' to "1100",
        'D' to "1101",
        'E' to "1110",
        'F' to "1111",
    )

    class Packet {
        var v = ""
        var t = ""
        var i = ""
        var l = ""
        var totalLengthInBits = ""
        var remainingLengthInBits = -1
        var numberOfPackets = ""
        var remainingPackets = -1
        var literal = ""
        var literalDigit = ""
        var waste = ""
        var lastLiteralControl = ' '
        var data = mutableListOf<Packet>()
        var currentPacket: Packet? = null
        var completed: Boolean = false

        fun length(): Int {
            var l =
                v.length + t.length + i.length + numberOfPackets.length + totalLengthInBits.length + l.length + literal.length + (literal.length / 4) + waste.length
            for (p in data)
                l += p.length()
            return l
        }

        fun feed(initialData: String): String {
            var d = initialData
            while (v.length < 3 && d.isNotEmpty()) {
                v += d[0]
                d = d.drop(1)
//                if (v.length == 3) println("Packet Version $v")
            }
            while (t.length < 3 && d.isNotEmpty()) {
                t += d[0]
                d = d.drop(1)
//                if (t.length == 3) println("Packet Type $v")
            }
            if (t == "100" && d.isNotEmpty()) {
                //Start reading literals
                if (lastLiteralControl == ' ') {
                    lastLiteralControl = d[0]
                    d = d.drop(1)
                }
                if (lastLiteralControl == '1') {
                    while (literalDigit.length < 4 && d.isNotEmpty()) {
                        literalDigit += d[0]
                        d = d.drop(1)
                    }
                    if (literalDigit.length == 4) {
                        literal += literalDigit
                        literalDigit = ""
                        lastLiteralControl = ' '
                    }
                }
                if (lastLiteralControl == '0') {
                    while (literalDigit.length < 4 && d.isNotEmpty()) {
                        literalDigit += d[0]
                        d = d.drop(1)
                    }
                    if (literalDigit.length == 4) {
                        literal += literalDigit
                        literalDigit = ""
                        lastLiteralControl = ' '
                        completed = true
//                        while (d.isNotEmpty() && d[0] == '0') {
//                            waste += d[0]
//                            d = d.drop(1)
//                        }
                    }
                }
            }
            if (t != "100" && d.isNotEmpty()) {
                if (i == "") {
                    i = d[0].toString()
                    d = d.drop(1)
                }
                if (i == "0") {
                    while (totalLengthInBits.length < 15 && d.isNotEmpty()) {
                        totalLengthInBits += d[0].toString()
                        d = d.drop(1)
                    }
                }
                if (i == "1") {
                    while (numberOfPackets.length < 11 && d.isNotEmpty()) {
                        numberOfPackets += d[0].toString()
                        d = d.drop(1)
                    }
                }
                if (totalLengthInBits.length == 15 && remainingLengthInBits == -1) {
                    remainingLengthInBits = totalLengthInBits.toInt(2)
                }
                if (numberOfPackets.length == 11 && remainingPackets == -1) {
                    remainingPackets = numberOfPackets.toInt(2)
                }
                if (remainingLengthInBits > 0 || remainingPackets > 0) {
                    //Start reading packets
                    if (currentPacket == null) {
                        currentPacket = Packet()
                    }
                    d = currentPacket!!.feed(d)
                    if (currentPacket!!.completed) {
                        data.add(currentPacket!!)
                        if (remainingLengthInBits > 0) {
                            remainingLengthInBits -= currentPacket!!.length()
                            if (remainingLengthInBits < 0)
                                println("ERROR")
                        }
                        if (remainingPackets > 0) {
                            remainingPackets -= 1
                            println(
                                "DATA: ${data.size} remainingPackets: $remainingPackets numberOfPackets: ${
                                    numberOfPackets.toInt(
                                        2
                                    )
                                }"
                            )
                        }
                        currentPacket = null
                    }
                }
                if (remainingLengthInBits == 0 || remainingPackets == 0) {
                    completed = true
                    println("Package $v - $t - [Subpackages: ${data.size}]")
                }
            }
            return d
        }

        fun sumOfVersions(): Int {
            var sum = v.toInt(2)
            for (p in data)
                sum += p.sumOfVersions()
            return sum
        }

        fun value(): BigInteger {
            val valueList = data.map { it.value() }.toList()
            if (valueList.size == 1) return valueList[0]
            val value: BigInteger = when (t) {
                "100" -> literal.toBigInteger(2)
                "000" -> {
                    val k = valueList.sumOf { it }
                    print("Suma")
                    print(" -> $k = ")
                    valueList.forEach { v ->
                        print(v)
                        print(" + ")
                    }
                    println()
                    k
                }
                "001" -> {
                    val k = valueList.reduce { acc, i -> acc * i }
                    print("Multiplicacion")
                    print(" -> $k = ")
                    valueList.forEach { v ->
                        print(v)
                        print(" * ")
                    }
                    println()
                    k
                }
                "010" -> {
                    val k = valueList.minOf { it }
                    print("Min")
                    print(" -> $k = ")
                    valueList.forEach { v ->
                        print(v)
                        print(",")
                    }
                    println()
                    k
                }
                "011" -> {
                    val k = valueList.maxOf { it }
                    print("Max")
                    print(" -> $k = ")
                    valueList.forEach { v ->
                        print(v)
                        print(",")
                    }
                    println()
                    k
                }
                "101" -> {
                    val k = if (valueList[0] > valueList[1]) BigInteger.valueOf(1) else BigInteger.valueOf(0)
                    print(" > ")
                    print(" -> $k = ")
                    valueList.forEach { v ->
                        print(v)
                        print(",")
                    }
                    println()
                    k
                }
                "110" -> {
                    val k = if (valueList[0] < valueList[1]) BigInteger.valueOf(1) else BigInteger.valueOf(0)
                    print(" < ")
                    print(" -> $k = ")
                    valueList.forEach { v ->
                        print(v)
                        print(",")
                    }
                    println()
                    k
                }
                "111" -> {
                    val k = if (valueList[0] == valueList[1]) BigInteger.valueOf(1) else BigInteger.valueOf(0)
                    print(" == ")
                    print(" -> $k = ")
                    valueList.forEach { v ->
                        print(v)
                        print(",")
                    }
                    println()
                    k
                }
                else -> {
                    println("ERROR")
                    BigInteger.valueOf(0)
                }
            }
            return value
        }
    }

    override fun partOne(): Any {

        var data = ""
        val packet = Packet()

        inputString.forEach {
            data += C[it]
            while (data.isNotEmpty() && !packet.completed)
                data = packet.feed(data)
            if (packet.completed)
                println("Done")
        }
        return packet.sumOfVersions()
    }

    override fun partTwo(): Any {

        var data = ""
        val packet = Packet()

        inputString.forEach {
            data += C[it]
            while (data.isNotEmpty() && !packet.completed) data = packet.feed(data)
        }
        println("Remaining: $data")
        return packet.value()
    }
}
