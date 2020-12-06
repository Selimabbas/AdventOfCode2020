package aoc2020.day02

import java.io.File

fun main() {
    val regex = "([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)".toRegex()
    val file = File("src/aoc2020/day02/input.txt.txt").readLines()
    val results = file.map {
        regex.find(it)
    }
    var validPasswords = 0
    results.forEach { result ->
        val (min, max, letter, password) = result!!.destructured
        if (password.count { it == letter[0] } in min.toInt()..max.toInt()) {
            validPasswords++
        }
    }
    println(validPasswords)
}