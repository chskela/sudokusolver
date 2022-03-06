package com.chskela.sudokusolver

typealias Generator = (Int) -> Coordinate

fun rowCoordinateGenerator(rowNumber: Int): Generator = { index -> Coordinate(rowNumber, index) }

fun colCoordinateGenerator(colNumber: Int): Generator = { index -> Coordinate(index, colNumber) }

fun squareCoordinateGenerator(squareNumber: Int): Generator = { index ->
    Coordinate(
        (squareNumber / 3) * 3 + index / 3,
        (squareNumber % 3) * 3 + index % 3
    )
}
