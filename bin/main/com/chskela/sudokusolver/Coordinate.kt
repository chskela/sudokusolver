package com.chskela.sudokusolver

data class Coordinate(val x: Int, val y: Int) {
    override fun toString(): String {
        return "[$x, $y]"
    }
}
