@file:JvmName("Main")

package com.chskela.sudokusolver

import java.io.File

fun main() {
    val input = readInput()

    println(input)
}

private fun readInput(): Map<Coordinate, Int> = File("input")
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

