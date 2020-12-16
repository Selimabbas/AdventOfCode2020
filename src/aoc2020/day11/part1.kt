package aoc2020.day11

import java.io.File

fun main() {
    var file = File("src/aoc2020/day11/input.txt")
        .readLines()
        .map { it.toList() }

    var total = 0
    do {
        println(++total)
        var foundDiff = false
        file = file.mapIndexed { line, list ->
            list.mapIndexed column@{ column, c ->
                if (c == '.') return@column c
                var count = 0
                for (i in -1..1) {
                    for (j in -1..1) {
                        if (line + i in file.indices
                            && column + j in file[line].indices
                            && (i != 0 || j != 0)
                        ) {
                            val content = file[line + i][column + j]
                            if (content == '#') {
                                if (c == 'L') return@column c
                                count++
                                if (c == '#' && count == 4) {
                                    foundDiff = true
                                    return@column 'L'
                                }
                            }
                        }
                    }
                }
                if (c == 'L' && count == 0) {
                    foundDiff = true
                    return@column '#'
                } else return@column c
            }
        }.also { line ->
            line.forEach {
                it.forEach { print(it) }
                println()
            }
        }
        println()
    } while (foundDiff)

    println(file.flatten().count { it == '#' })
}