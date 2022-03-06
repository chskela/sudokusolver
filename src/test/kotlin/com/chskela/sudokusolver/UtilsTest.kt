package com.chskela.sudokusolver

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class UtilsTest {

    @Test
    fun `row generator should return coordinates in single row`() {
        val coordinate = Utils.rowCoordinateGenerator(0).invoke(1)
        assertEquals(Coordinate(0, 1), coordinate)
    }

    @Test
    fun `col generator should return coordinates in single col`() {
        val coordinate = Utils.colCoordinateGenerator(0).invoke(1)
        assertEquals(Coordinate(1, 0), coordinate)
    }

    @Test
    fun `square generator should return coordinates in single square`() {
        val coordinate = Utils.squareCoordinateGenerator(0).invoke(1)
        assertEquals(Coordinate(0, 1), coordinate)
    }
}