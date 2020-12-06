package aoc2020.day03

import java.io.File

fun main() {
    val file = File("src/aoc2020/day03/input.txt.txt").readLines()
    var x = 0
    var y = 0
    var trees = 0
    while (y < file.size - 1) {
        x+=3
        if (x >= file[0].length) x -= file[0].length
        y++
        if (file[y][x] == '#') trees++
    }
    println(trees)
}