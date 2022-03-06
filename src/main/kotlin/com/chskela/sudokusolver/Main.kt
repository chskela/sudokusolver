@file:JvmName("Main")

package com.chskela.sudokusolver

fun main() {
    val input = readInput("input")
    val solution = readInput("solution")

    println(input)
    println(solution)
    println(Board(input, solution).checkSolution())
}







