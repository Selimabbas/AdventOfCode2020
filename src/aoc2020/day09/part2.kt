package aoc2020.day09

import java.io.File

fun main() {
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

    val file = File("src/aoc2020/day09/input.txt").readLines().map { it.toLong() }
    val preamble = 25
    for (i in preamble until file.size) {
        if (!findSum(file[i], file.subList(i - preamble, i))) {
            findContiguousSum(i, file)
            return
        }
    }
}

fun findContiguousSum(index: Int, file: List<Long>) {
    for (i in file.indices) {
        if (index == i) continue
        val list = mutableListOf<Long>()
        for (j in i until file.size) {
            if (index == j || list.sum() > file[index]) {
                break
            }
            list.add(file[j])
            val sum = list.sum()
            if (sum == file[index]) {
                println(list.min()!! + list.max()!!)
                return
            }
        }
    }
}