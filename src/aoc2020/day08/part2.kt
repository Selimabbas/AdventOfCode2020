package aoc2020.day08

import java.io.File

fun main() {
    val file = File("src/aoc2020/day08/input.txt").readLines().map { line ->
        val (operation, value) = "([a-z]+) ([+-][0-9]+)".toRegex().matchEntire(line)!!.destructured
        return@map operation to value.toInt()
    }.toMutableList()

    for (i in file.indices) {
        val element = file[i]
        if (element.first == "nop") {
            file[i] = "jmp" to element.second
            if (execute(file)) return
            file[i] = "nop" to element.second
        } else if (element.first == "jmp") {
            file[i] = "nop" to element.second
            if (execute(file)) return
            file[i] = "jmp" to element.second
        }
    }
}

private fun execute(file: List<Pair<String, Int>>): Boolean {
    var i = 0
    var globalValue = 0
    val list = mutableListOf<Int>()
    while (!list.contains(i)) {
        val elem = file[i]
        list.add(i)
        if (elem.first == "acc") {
            globalValue += elem.second
        }
        if (elem.first == "jmp") {
            i += elem.second
        } else {
            i++
        }
        if (i == file.size - 1) {
            println(globalValue)
            return true
        }
    }
    return false
}