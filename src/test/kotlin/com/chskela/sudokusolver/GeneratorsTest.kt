package com.chskela.sudokusolver

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GeneratorsTest {

    @Test
    fun `row generator should return coordinates in single row`() {
        val coordinate = rowCoordinateGenerator(0).invoke(1)
        assertEquals(Coordinate(0, 1), coordinate)
    }

    @Test
    fun `col generator should return coordinates in single col`() {
        val coordinate = colCoordinateGenerator(0).invoke(1)
        assertEquals(Coordinate(1, 0), coordinate)
    }

    @Test
    fun `square generator should return coordinates in single square`() {
        val coordinate = squareCoordinateGenerator(0).invoke(1)
        assertEquals(Coordinate(0, 1), coordinate)
    }

    @Test
    fun `correct solutionshould return an ok result`() {
        val input  = """
            .6..4537.
            ..567342.
            ..4.....1
            5..7.2..4
            6.9...25.
            8.7..9..3
            49.5178..
            21..36...
            .5..2.1..
        """.trimIndent().asGameBoard()
        val solution = """
            9.21....8
            18......9
            37.29856.
            .31.6.98.
            .4.381..7
            .2.45.61.
            ..6....32
            ..89..745
            7.38.4.96
        """.trimIndent().asGameBoard()
        assertEquals(CheckOutcome.Ok, Board(input, solution).checkSolution())
    }

    @Test
    fun `incorrect solutionshould return an failure result`() {
        val input  = """
            .6..4537.
            ..567342.
            ..4.....1
            5..7.2..4
            6.9...25.
            8.7..9..5
            49.5178..
            21..36...
            .5..2.1..
        """.trimIndent().asGameBoard()
        val solution = """
            9.21....8
            18......9
            37.29856.
            .31.6.98.
            .4.381..7
            .2.45.61.
            ..6....32
            ..89..745
            7.38.4.96
        """.trimIndent().asGameBoard()
        assertEquals(CheckOutcome.Failure, Board(input, solution).checkSolution())
    }

    @Test
    fun `incomplete solution should return an incomplete result`() {
        val input  = """
            .6..4537.
            ..567342.
            ..4.....1
            5..7.2..4
            6.9...25.
            8.7..9..5
            49.5178..
            21..36...
            .5..2.1..
        """.trimIndent().asGameBoard()
        val solution = """
            9.21....8
            18.......
            37.29856.
            .31.6.98.
            .4.381..7
            .2.45.61.
            ..6....32
            ..89..745
            7.38.4.96
        """.trimIndent().asGameBoard()
        assertEquals(CheckOutcome.Incomplete, Board(input, solution).checkSolution())
    }

}