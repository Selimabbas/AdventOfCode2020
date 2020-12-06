package aoc2020.day05

import java.io.File
import kotlin.math.ceil

fun main() {
    val file = File("src/aoc2020/day05/input.txt").readLines()
    var highest = 0
    file.forEach { line ->
        var minRow = 0f
        var maxRow = 127f
        var minSeat = 0f
        var maxSeat = 7f
        line.forEach {
            when (it) {
                'F' -> maxRow -= ceil((maxRow - minRow) / 2f)
                'B' -> minRow += ceil((maxRow - minRow) / 2f)
                'L' -> maxSeat -= ceil((maxSeat - minSeat) / 2f)
                'R' -> minSeat += ceil((maxSeat - minSeat) / 2f)
            }
        }
        val id = minRow.toInt() * 8 + minSeat.toInt()
        if (id > highest) highest = id
    }
    println(highest)
}