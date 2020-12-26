package aoc2020.day14

import java.io.File
import kotlin.math.pow

fun main() {
    val map = mutableMapOf<Int, Long>()
    var mask = ""
    File("src/aoc2020/day14/input.txt")
        .readLines()
        .forEach {
            if (it.contains("mask")) {
                mask = "mask = ([01X]*)".toRegex().matchEntire(it)!!.destructured.component1()
            } else {
                val (index, value) = "mem\\[([0-9]*)\\] = ([0-9]*)".toRegex().matchEntire(it)!!.destructured
                var binary = value.toLong().toString(2)
                while (binary.length < mask.length) binary = "0$binary"
                map[index.toInt()] = binary.mapIndexed { i, c ->
                    if (mask[i] != 'X') mask[i]
                    else c
                }.joinToString("").toDecimal()
            }
        }
    println(map.values.sum())
}

fun String.toDecimal(): Long {
    var sum = 0.0
    reversed().forEachIndexed { k, v ->
        sum += v.toString().toInt() * 2.0.pow(k)
    }
    return sum.toLong()
}


