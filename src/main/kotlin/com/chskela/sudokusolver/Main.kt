@file:JvmName("Main")

package com.chskela.sudokusolver

import java.io.File

fun main() {
    val input = readInput("input").asGameBoard()
    val solution = readInput("solution").asGameBoard()

    println(input)
    println(solution)
    println(Board(input, solution).checkSolution())
}

fun readInput(fileName: String) = File(fileName).readText()






