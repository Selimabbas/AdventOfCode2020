package aoc2020.day09

import java.io.File

fun main() {
    val file = File("src/aoc2020/day09/input.txt").readLines().map { it.toLong() }
    for (i in 25 until file.size) {
        if (!findSum(file[i], file.subList(i - 25, i))) {
            println(file[i])
            return
        }
    }
}

fun findSum(number: Long, subList: List<Long>): Boolean {
    for (i in subList.indices) {
        for (j in subList.indices) {
            if (i != j) {
                if (number == subList[i] + subList[j]) {
                    return true
                }
            }
        }
    }
    return false
}