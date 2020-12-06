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
    val map = mapOf<String, (String) -> Boolean>(
        "byr" to { it -> it.toInt() in 1920..2002 },
        "iyr" to { it -> it.toInt() in 2010..2020 },
        "eyr" to { it -> it.toInt() in 2020..2030 },
        "hgt" to { it ->
            val result = "([0-9]+)([a-z]{2})".toRegex().find(it)
            result?.let {
                val (height, mesure) = it.destructured
                (mesure == "cm" && height.toInt() in 150..193)
                        || (mesure == "in" && height.toInt() in 59..76)
            } ?: false
        },
        "hcl" to { it ->
            "#[a-f0-9]{6}".toRegex().matches(it)
        },
        "ecl" to { it ->
            listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(it)
        },
        "pid" to { it ->
            "[0-9]{9}".toRegex().matches(it)
        }
    )
    file.forEachIndexed passports@{ index, passport ->
        if (passport.size > 6 && passport.keys.containsAll(map.keys)) {
            map.forEach {
                if (!it.value.invoke(passport[it.key]!!)) {
                    return@passports
                }
            }
            validPassports++
        }
    }
    println(validPassports)
}