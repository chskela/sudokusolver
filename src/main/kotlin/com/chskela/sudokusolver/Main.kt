@file:JvmName("Main")

package com.chskela.sudokusolver

fun main() {
    val input = readInput("input")
    val solution = readInput("solution")

    println(input)
    println(solution)
    println(checkSolution(input, solution))
}

private fun checkSolution(
    input: Map<Coordinate, Int>,
    solution: Map<Coordinate, Int>
): CheckOutcome = (0..8).fold(CheckOutcome.Ok) { acc, i ->
    val checkL = checkLine(i, input, solution)
    val checkR = checkRow(i, input, solution)
    if (checkL != CheckOutcome.Ok) return@fold checkL
    if (checkR != CheckOutcome.Ok) return@fold checkR
    acc
}

private fun checkLine(
    lineNumber: Int,
    input: Map<Coordinate, Int>,
    solution: Map<Coordinate, Int>,
    coordinateGenerator: (Int) -> Coordinate = { index -> Coordinate(lineNumber, index) }
): CheckOutcome = (0..8).fold(CheckOutcome.Ok) { _, i ->
    val checkedCoordinate = coordinateGenerator(i)
    val checkedValue = input[checkedCoordinate] ?: solution[checkedCoordinate] ?: return@fold CheckOutcome.Incomplete

    (i + 1..8).fold(CheckOutcome.Ok) { _, j ->
        val internalCoordinate = coordinateGenerator(j)
        val internalValue = input[internalCoordinate] ?: solution[internalCoordinate] ?: return CheckOutcome.Incomplete
        if (checkedValue == internalValue) return CheckOutcome.Failure
        CheckOutcome.Ok
    }
}

private fun checkRow(
    lineNumber: Int,
    input: Map<Coordinate, Int>,
    solution: Map<Coordinate, Int>,
    coordinateGenerator: (Int) -> Coordinate = { index -> Coordinate(index, lineNumber) }
) = checkLine(
    lineNumber,
    input,
    solution,
    coordinateGenerator
)


