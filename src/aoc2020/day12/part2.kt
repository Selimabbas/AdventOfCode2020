package aoc2020.day12

import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

data class Position(var x: Int, var y: Int)

fun main() {
    val file = File("src/aoc2020/day12/input.txt")
        .readLines()
        .map { it[0] to it.substring(1).toInt() }

    val position = Position(0, 0)
    var waypoint = Position(10, 1)
    file.forEach {
        when (it.first) {
            'N' -> waypoint.y += it.second
            'S' -> waypoint.y -= it.second
            'E' -> waypoint.x += it.second
            'W' -> waypoint.x -= it.second
            'L' -> {
                waypoint = rotatePoint(it.second.toDouble(), waypoint)
            }
            'R' -> {
                waypoint = rotatePoint(-it.second.toDouble(), waypoint)
            }
            'F' -> {
                position.x += it.second * waypoint.x
                position.y += it.second * waypoint.y
            }
        }
    }
    println(position.x.absoluteValue + position.y.absoluteValue)
}

fun rotatePoint(angle: Double, waypoint: Position): Position {
    val sin = sin(Math.toRadians(angle))
    val cos = cos(Math.toRadians(angle))

    val xnew = waypoint.x * cos - waypoint.y * sin
    val ynew = waypoint.x * sin + waypoint.y * cos

    waypoint.x = xnew.roundToInt()
    waypoint.y = ynew.roundToInt()
    return waypoint
}

