package aoc2020.day11

import java.io.File

enum class Direction {
    LEFT,
    UP_LEFT,
    UP,
    UP_RIGHT,
    RIGHT,
    DOWN_RIGHT,
    DOWN,
    DOWN_LEFT
}

fun main() {
    var file = File("src/aoc2020/day11/input.txt")
        .readLines()
        .map { it.toList() }

    do {
        var foundDiff = false
        file = file.mapIndexed { line, list ->
            list.mapIndexed column@{ column, c ->
                if (c == '.') return@column c
                var count = 0
                direction@ for (direction in Direction.values()) {
                    var index = 0
                    var position = getPosition(direction, index++)
                    while (line + position.first in file.indices
                        && column + position.second in file[line].indices
                    ) {
                        val content = file[line + position.first][column + position.second]
                        if (content == '#') {
                            if (c == 'L') return@column c
                            count++
                            if (c == '#' && count == 5) {
                                foundDiff = true
                                return@column 'L'
                            }
                        }
                        if (content == '#' || content == 'L') continue@direction
                        position = getPosition(direction, index++)
                    }
                }
                if (c == 'L' && count == 0) {
                    foundDiff = true
                    return@column '#'
                } else return@column c
            }
        }
    } while (foundDiff)

    println(file.flatten().count { it == '#' })
}

fun getPosition(direction: Direction, index: Int) = when (direction) {
    Direction.UP -> -index - 1 to 0
    Direction.UP_RIGHT -> -index - 1 to index + 1
    Direction.RIGHT -> 0 to index + 1
    Direction.DOWN_RIGHT -> index + 1 to index + 1
    Direction.DOWN -> index + 1 to 0
    Direction.DOWN_LEFT -> index + 1 to -index - 1
    Direction.LEFT -> 0 to -index - 1
    Direction.UP_LEFT -> -index - 1 to -index - 1
}
