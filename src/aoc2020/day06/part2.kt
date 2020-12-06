package aoc2020.day06

import java.io.File

fun main() {
    var total = 0
    File("src/aoc2020/day06/input.txt")
        .readText()
        .split("\r\n\r\n")
        .forEach { line ->
            val list = line.split("\r\n")
            for (i in list[0].indices) {
                if (i == 0 || !list[0].substring(0, i).contains(list[0][i])) {
                    var everyone = true
                    for (j in 1 until list.size) {
                        if (!list[j].contains(list[0][i])) {
                            everyone = false
                            break
                        }
                    }
                    if (everyone) total++
                }
            }
        }
    println(total)
}