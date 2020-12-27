package aoc2020.day14

import java.io.File

fun main() {
    val map = mutableMapOf<Long, Long>()
    var mask = ""
    File("src/aoc2020/day14/input.txt")
        .readLines()
        .forEach {
            if (it.contains("mask")) {
                mask = "mask = ([01X]*)".toRegex().matchEntire(it)!!.destructured.component1()
            } else {
                val (index, value) = "mem\\[([0-9]*)\\] = ([0-9]*)".toRegex().matchEntire(it)!!.destructured
                var binary = index.toLong().toString(2)
                while (binary.length < mask.length) binary = "0$binary"
                binary = binary.mapIndexed { i, c ->
                    if (mask[i] == '0') c
                    else mask[i]
                }.joinToString("")
                changeMapAtAddresses(binary.toCharArray(), map, value.toLong())
            }
        }
    println(map.values.sum())
}

fun changeMapAtAddresses(binary: CharArray, map: MutableMap<Long, Long>, value: Long) {
    var count = 0
    val xs = mutableListOf<Int>()
    binary.forEachIndexed { i, c ->
        if (c == 'X') xs.add(i)
    }
    while (true) {
        var binaryCount = count.toString(2)
        if (binaryCount.length > xs.size) return
        while (binaryCount.length < xs.size) binaryCount = "0$binaryCount"
        binaryCount.forEachIndexed { index, c ->
            binary[xs[index]] = c
        }
        val index = String(binary).toDecimal()
        map[index] = value
        count++
    }

}
