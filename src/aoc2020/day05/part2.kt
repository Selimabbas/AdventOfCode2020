package aoc2020.day05

import java.io.File
import kotlin.math.ceil

fun main() {
    val file = File("src/aoc2020/day05/input.txt").readLines()
    val list = mutableMapOf<Int, MutableList<Int>>()
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

        list.getOrPut(minRow.toInt(), { mutableListOf() }).add(minSeat.toInt())
    }
    list.forEach {
        if (it.value.size < 8) {
            for (x in 0..7) {
                if (!it.value.contains(x)
                    && (x == 0 || it.value.contains(x - 1))
                    && (x == 7 || it.value.contains(x + 1))
                ) {
                    println(it.key * 8 + x)
                }
            }
        }
    }
}