package aoc2020.day07

import java.io.File

data class BagContent(val name: String, val count: Int)

fun main() {
    val file = File("src/aoc2020/day07/input.txt").readLines().map { line ->
        val (bag, other) = "([a-z]+ [a-z]+) bags contain (.*).".toRegex().matchEntire(line)!!.destructured
        bag to if (other == "no other bags") {
            emptyList()
        } else {
            other.split(", ").map {
                val (count, bagName) = "([0-9]+) ([a-z]+ [a-z]+) bag[s]?".toRegex().matchEntire(it)!!.destructured
                BagContent(bagName, count.toInt())
            }
        }
    }.toMap()

    var total = 0
    file.forEach {
        if (checkBag(file, it.value)) {
            total++
        }
    }
    println(total)
}

const val shinyBag = "shiny gold"

fun checkBag(file: Map<String, List<BagContent>>, bags: List<BagContent>): Boolean {
    bags.forEach {
        if (it.name == shinyBag) {
            return true
        } else if (checkBag(file, file[it.name]!!)) {
            return true
        }
    }
    return false
}