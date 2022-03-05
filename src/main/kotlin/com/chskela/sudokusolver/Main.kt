@file:JvmName("Main")

package com.chskela.sudokusolver

import java.io.File

fun main() {
    val input = readInput()

    println(input)
}

private fun readInput(): Map<Pair<Int, Int>, Int> = File("input")
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
                val number = Character.getNumericValue(indexedChar.value)
                (xCoordinate to yCoordinate) to number
            }
    }
    .toMap()

