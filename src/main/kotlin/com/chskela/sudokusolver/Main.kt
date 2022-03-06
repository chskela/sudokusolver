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
): CheckOutcome = (0..8).fold(CheckOutcome.Ok) { _, i ->
    val checkedCoordinate = Coordinate(lineNumber, i)
    val checkedValue = input[checkedCoordinate] ?: solution[checkedCoordinate] ?: return@fold CheckOutcome.Incomplete

    (i + 1..8).fold(CheckOutcome.Ok) { _, j ->
        val internalCoordinate = Coordinate(lineNumber, j)
        val internalValue = input[internalCoordinate] ?: solution[internalCoordinate] ?: return CheckOutcome.Incomplete
        if (checkedValue == internalValue) return CheckOutcome.Failure
        CheckOutcome.Ok
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

