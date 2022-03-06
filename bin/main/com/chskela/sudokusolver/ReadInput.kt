package com.chskela.sudokusolver

import java.io.File

fun readInput(fileName: String): Map<Coordinate, Int> = File(fileName)
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