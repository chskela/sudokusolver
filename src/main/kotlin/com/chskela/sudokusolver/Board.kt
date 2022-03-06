package com.chskela.sudokusolver

class Board(
    private val input: Map<Coordinate, Int>,
    private val solution: Map<Coordinate, Int>
) {

    fun checkSolution(): CheckOutcome = (0..8).fold(CheckOutcome.Ok) { acc, i ->
        val checkL = checkLine(i)
        val checkR = checkRow(i)
        val checkS = checkSquare(i)
        if (checkL != CheckOutcome.Ok) return@fold checkL
        if (checkR != CheckOutcome.Ok) return@fold checkR
        if (checkS != CheckOutcome.Ok) return@fold checkS
        acc
    }

    private fun checkLine(
        lineNumber: Int,
        coordinateGenerator: (Int) -> Coordinate = { index -> Coordinate(lineNumber, index) }
    ): CheckOutcome = (0..7).fold(CheckOutcome.Ok) { _, i ->
        val checkedCoordinate = coordinateGenerator(i)
        val checkedValue =
            input[checkedCoordinate] ?: solution[checkedCoordinate] ?: return@fold CheckOutcome.Incomplete

        (i + 1..8).fold(CheckOutcome.Ok) { _, j ->
            val internalCoordinate = coordinateGenerator(j)
            val internalValue =
                input[internalCoordinate] ?: solution[internalCoordinate] ?: return CheckOutcome.Incomplete
            if (checkedValue == internalValue) return CheckOutcome.Failure
            CheckOutcome.Ok
        }
    }

    private fun checkRow(lineNumber: Int) = checkLine(lineNumber) { index -> Coordinate(index, lineNumber) }

    private fun checkSquare(lineNumber: Int) = checkLine(lineNumber) { index ->
        Coordinate(
            (lineNumber / 3) * 3 + index / 3,
            (lineNumber % 3) * 3 + index % 3
        )
    }
}