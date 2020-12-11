package aoc2020.day07

import java.io.File

fun main() {
    data class BagContent(val name: String, val count: Int)
    val shinyBag = "shiny gold"

    fun checkBag(file: Map<String, List<BagContent>>, bags: List<BagContent>): Int {
        var total = 0
        bags.forEach {
            total += it.count * (1 +  checkBag(file, file[it.name]!!))
        }
        return total
    }
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

    println(checkBag(file, file[shinyBag]!!))
}
