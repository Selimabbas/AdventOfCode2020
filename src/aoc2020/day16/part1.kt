package aoc2020.day16

import java.io.File

fun main() {
    val file = File("src/aoc2020/day16/input.txt")
        .readLines()

    val ranges = file.subList(0, 20).flatMap {
        val destructured = "[a-z ]*: ([0-9]*)-([0-9]*) or ([0-9]*)-([0-9]*)".toRegex().matchEntire(it)!!.destructured
        listOf(
            destructured.component1().toInt()..destructured.component2().toInt(),
            destructured.component3().toInt()..destructured.component4().toInt()
        )
    }

    val otherTickets = file.subList(25, file.size).flatMap { it.split(',', '\n') }.map { it.toLong() }
    val notInRange = mutableListOf<Long>()
    otherTickets.forEach other@{
        ranges.forEach { range ->
            if (it in range) {
                return@other
            }
        }
        notInRange.add(it)
    }
    println(notInRange.sum())
}