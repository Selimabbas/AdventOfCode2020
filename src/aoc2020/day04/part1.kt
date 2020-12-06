package aoc2020.day04

import java.io.File

fun main() {
    val file = File("src/aoc2020/day04/input.txt")
        .readText()
        .split("\r\n\r\n")
        .filter { it.isNotEmpty() }
        .map lines@{ line ->
            val map = mutableMapOf<String, String>()
            line.split(" ", "\r\n").forEach {
                val split = it.split(':')
                if (split.isNullOrEmpty()) return@lines null
                map[split[0]] = split[1]
            }
            return@lines map
        }.filterNotNull()

    var validPassports = 0
    file.forEach {
        if (it.size > 6
            && it.keys.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))
        ) validPassports++
    }
    println(validPassports)
}