package scorr._01

import org.junit.jupiter.api.Test
import java.lang.Math.abs
import java.util.*

class PartTwo : PartOne() {

    val locations : ArrayList<Point2D> = arrayListOf(Point2D(0, 0)) // Start at pos 0, 0.
    var firstLocationVisitedTwice : Point2D? = null

    override fun moveForward(blocks: Int) {
        var blocksRemaining = blocks
        while (blocksRemaining > 0) {
            when (facing) {
                FaceDirection.E -> {
                    x++
                }
                FaceDirection.N -> {
                    y++
                }
                FaceDirection.S -> {
                    y--
                }
                FaceDirection.W -> {
                    x--
                }
            }
            blocksRemaining--
            compareLocation(x, y)
        }
    }

    fun compareLocation(x : Int, y : Int) : Boolean {
        var visit = locations.find({ it -> it.x == x && it.y == y})
        if (visit != null && firstLocationVisitedTwice == null) {
            firstLocationVisitedTwice = visit
            return true
        }
        else {
            locations.add(Point2D(x, y))
            return false
        }
    }

    @Test fun test_example() {
        walk(TurnDirection.R, 8)
        walk(TurnDirection.R, 4)
        walk(TurnDirection.R, 4)
        walk(TurnDirection.R, 8)
        var twiceDistance = 0
        if (firstLocationVisitedTwice != null) {
            twiceDistance = abs(firstLocationVisitedTwice!!.x) + abs(firstLocationVisitedTwice!!.y)
        }

        assert(twiceDistance == 4)
    }

    @Test override fun test_solution() {
        val commands = text.split(",").map(String::trim)
        for (command in commands) {
            val direction = TurnDirection.valueOf(command[0].toString())
            walk(direction, command.substring(1).toInt())
        }

        var twiceDistance = 0
        if (firstLocationVisitedTwice != null) {
            twiceDistance = abs(firstLocationVisitedTwice!!.x) + abs(firstLocationVisitedTwice!!.y)
        }

        System.out.print(twiceDistance)
    }
}

data class Point2D (val x: Int, val y : Int)