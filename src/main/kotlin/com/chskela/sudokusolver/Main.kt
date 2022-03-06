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
    val check = checkLine(i, input, solution)
    if (check != CheckOutcome.Ok) return@fold check
    acc
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


