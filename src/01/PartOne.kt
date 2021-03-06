package scorr._01

import org.junit.jupiter.api.Test
import java.io.File
import java.lang.Math.abs

enum class FaceDirection {
    N, E, S, W
}

enum class TurnDirection{
    L, R
}

open class PartOne {
    val inputLocation = "data/input01.txt"
    var x = 0
    var y = 0
    var facing : FaceDirection = FaceDirection.N
    val distance : Int
        get() = abs(x) + abs(y)

    val solutionInput = File(inputLocation).readText()

    fun walk(direction : TurnDirection, blocks : Int) {
        turn(direction)
        moveForward(blocks)
    }

    fun turn(direction : TurnDirection) {
        when (direction) {
            TurnDirection.L -> {
                // Make the enum wrap around.
                val newOrdinal : Int
                if (facing.ordinal == 0)
                    newOrdinal = FaceDirection.values().size - 1
                else
                    newOrdinal = facing.ordinal - 1

                facing = FaceDirection.values()[newOrdinal]
            }
            TurnDirection.R -> {
                val newOrdinal : Int
                if (facing.ordinal == FaceDirection.values().size - 1)
                    newOrdinal = 0
                else
                    newOrdinal = facing.ordinal + 1

                facing = FaceDirection.values()[newOrdinal]
            }
        }
    }

    open fun moveForward(blocks : Int) {
        when (facing) {
            FaceDirection.E -> {
                x += blocks
            }
            FaceDirection.N -> {
                y += blocks
            }
            FaceDirection.S -> {
                y -= blocks
            }
            FaceDirection.W -> {
                x -= blocks
            }
        }
    }

    @Test fun test_example1() {
        walk(TurnDirection.R, 2)
        walk(TurnDirection.L, 3)
        assert(x == 2 && y == 3)
        assert(distance == 5)
    }

    @Test fun test_example2() {
        walk(TurnDirection.R, 2)
        walk(TurnDirection.R, 2)
        walk(TurnDirection.R, 2)
        assert(x == 0 && y == -2)
        assert(distance == 2)
    }

    @Test fun test_example3() {
        walk(TurnDirection.R, 5)
        walk(TurnDirection.L, 5)
        walk(TurnDirection.R, 5)
        walk(TurnDirection.R, 3)
        assert(distance == 12)
    }

    @Test open fun test_solution() {
        val commands = solutionInput.split(",").map(String::trim)
        for (command in commands) {
            val direction = TurnDirection.valueOf(command[0].toString())
            walk(direction, command.substring(1).toInt())
        }

        System.out.print(distance)
    }
}
