package aoc2020.day12

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val file = File("src/aoc2020/day12/input.txt")
        .readLines()
        .map { it[0] to it.substring(1).toInt() }

    var x = 0f
    var y = 0f
    var direction = 3
    val directions = listOf('S', 'W', 'N', 'E')
    file.forEach {
        when (it.first) {
            'N' -> y += it.second
            'S' -> y -= it.second
            'E' -> x += it.second
            'W' -> x -= it.second
            'L' -> {
                direction -= it.second / 90
                if (direction < 0) direction += 4
            }
            'R' -> {
                direction += it.second / 90
                if (direction > 3) direction -= 4
            }
            'F' -> {
                when (directions[direction]) {
                    'N' -> y += it.second
                    'S' -> y -= it.second
                    'E' -> x += it.second
                    'W' -> x -= it.second
                }
            }
        }
    }
    println(x.absoluteValue + y.absoluteValue)
}

