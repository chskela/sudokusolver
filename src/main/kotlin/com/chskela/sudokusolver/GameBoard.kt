package com.chskela.sudokusolver


typealias GameBoard = Map<Coordinate, Int>

fun String.asGameBoard(): GameBoard = this
    .lines()
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