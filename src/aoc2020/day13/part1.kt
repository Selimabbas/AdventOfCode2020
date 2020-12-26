package aoc2020.day13

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val file = File("src/aoc2020/day13/input.txt")
        .readLines()
    val time = file[0].toInt()
    val busIds = file[1].split(',')
        .filterNot { it == "x" }
        .map { it.toInt() }

    var timeToWait = 0
    var id = 0
    busIds.forEach {
        var maxTime = it
        while (maxTime < time) maxTime += it
        if (timeToWait == 0 || maxTime < timeToWait) {
            timeToWait = maxTime
            id = it
        }
    }
    println(id * (timeToWait - time))
}

