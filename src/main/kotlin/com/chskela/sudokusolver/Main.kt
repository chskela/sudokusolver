@file:JvmName("Main")

package com.chskela.sudokusolver

import java.io.File

fun main() {
    val input = readInput("input")
    val solution = readInput("solution")

    println(input)
    println(solution)
    println(checkLine(1, input, solution))
}

private fun checkLine(
    lineNumber: Int,
    input: Map<Coordinate, Int>,
    solution: Map<Coordinate, Int>
): Boolean = (0..8).fold(true) { _, i ->
    val checkedCoordinate = Coordinate(lineNumber, i)
    val checkedValue = input[checkedCoordinate] ?: solution[checkedCoordinate] ?: return@fold false

    (i + 1..8).fold(true) { _, j ->
        val internalCoordinate = Coordinate(lineNumber, j)
        val internalValue = input[internalCoordinate] ?: solution[internalCoordinate] ?: return false
        if (checkedValue == internalValue) return false
        true
    }
}


private fun readInput(fileName: String): Map<Coordinate, Int> = File(fileName)
    .readLines()
    .withIndex()
    .flatMap { indexedValue ->
        val xCoordinate = indexedValue.index
        indexedValue.value
            .toCharArray()
            .withIndex()
            .filter { it.value != '.' }
            .map { indexedChar ->
                val yCoordinate = indexedChar.index
                val coordinate = Coordinate(xCoordinate, yCoordinate)
                val number = Character.getNumericValue(indexedChar.value)
                coordinate to number
            }
    }
    .toMap()

