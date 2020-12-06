package aoc2020.day01

import java.io.File

fun main() {
    val file = File("src/aoc2020/day01/input.txt.txt").readLines().map { it.toInt() }
    file.forEachIndexed { index, it ->
        for (i in file.indices) {
            for (j in file.indices) {
                if (i != index && i != j && index != j) {
                    if (file[i] + file[j] + it == 2020) {
                        println(it * file[i] * file[j])
                        return
                    }
                }
            }
        }
    }

}