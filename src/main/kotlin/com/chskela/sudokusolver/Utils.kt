package com.chskela.sudokusolver

object Utils {

    fun rowCoordinateGenerator(rowNumber: Int): (Int) -> Coordinate = { index -> Coordinate(rowNumber, index) }
    fun colCoordinateGenerator(colNumber: Int): (Int) -> Coordinate = { index -> Coordinate(index, colNumber) }
    fun squareCoordinateGenerator(squareNumber: Int): (Int) -> Coordinate = { index ->
        Coordinate(
            (squareNumber / 3) * 3 + index / 3,
            (squareNumber % 3) * 3 + index % 3
        )
    }
}