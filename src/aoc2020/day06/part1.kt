package aoc2020.day06

import java.io.File

fun main() {
    var total = 0
    File("src/aoc2020/day06/input.txt")
        .readText()
        .split("\r\n\r\n")
        .forEach { line ->
            line.replace("\r\n", "").let {
                for (i in it.indices) {
                    if (i == 0 || !it.substring(0, i).contains(it[i])) {
                        total++
                    }
                }
            }
        }
    println(total)
}