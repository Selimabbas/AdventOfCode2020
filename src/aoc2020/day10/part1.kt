package aoc2020.day10

import java.io.File

fun main() {
    val file = File("src/aoc2020/day10/input.txt")
        .readLines()
        .map { it.toInt() }
        .sorted()

    var oneJoltDiff = 1
    var threeJoltDiff = 1
    loop@ for (i in 1 until file.size) {
        val current = file[i]
        val previous = file[i - 1]
        val diff = current - previous
        when {
            diff == 1 -> oneJoltDiff++
            diff == 3 -> threeJoltDiff++
            diff > 3 -> break@loop
        }
    }
    println(oneJoltDiff * threeJoltDiff)
}