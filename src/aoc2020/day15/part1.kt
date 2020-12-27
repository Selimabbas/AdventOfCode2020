package aoc2020.day15

import java.io.File

fun main() {
    val file = File("src/aoc2020/day15/input.txt")
        .readText().split(',').map { it.toLong() }.toMutableList()

    var index = file.size

    while (index < 2020) {
        val last = file.subList(0, index - 1).indexOfLast { it == file[index - 1] }
        val input = if (last == -1) 0 else (index - 1 - last)
        file.add(input.toLong())
        index++
    }
    println(file[index - 1])
}

