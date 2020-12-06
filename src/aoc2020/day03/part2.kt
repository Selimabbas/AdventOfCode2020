package aoc2020.day03

import java.io.File

fun main() {
    val file = File("src/aoc2020/day03/input.txt.txt").readLines()
    var total = 1L
    total *= traverseMap(file, 1, 1)
    total *= traverseMap(file, 3, 1)
    total *= traverseMap(file, 5, 1)
    total *= traverseMap(file, 7, 1)
    total *= traverseMap(file, 1, 2)

    println(total)
}

fun traverseMap(file: List<String>, right: Int, down: Int): Int {
    var x = 0
    var y = 0
    var trees = 0
    while (y < file.size) {
        x += right
        if (x >= file[0].length) x -= file[0].length
        y += down
        if (y < file.size )
        if (file[y][x] == '#') trees++
    }
    return trees
}