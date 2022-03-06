package com.chskela.sudokusolver

class Board(
    private val input: GameBoard,
    private val solution: GameBoard,
) {

    fun checkSolution(): CheckOutcome = (0..8).fold(CheckOutcome.Ok) { acc, i ->
        val checkR = checkRow(i)
        val checkC = checkCol(i)
        val checkS = checkSquare(i)
        if (checkR != CheckOutcome.Ok) return@fold checkR
        if (checkC != CheckOutcome.Ok) return@fold checkC
        if (checkS != CheckOutcome.Ok) return@fold checkS
        acc
    }

    private fun checkRow(
        rowNumber: Int,
        coordinateGenerator: Generator = rowCoordinateGenerator(rowNumber)
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

    private fun checkCol(colNumber: Int) = checkRow(colNumber, colCoordinateGenerator(colNumber))

    private fun checkSquare(squareNumber: Int) = checkRow(squareNumber, squareCoordinateGenerator(squareNumber))
 }