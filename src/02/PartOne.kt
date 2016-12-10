package scorr._02

import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

enum class TurnDirection{
    R, D, L, U
}

fun array2dOfInt(sizeOuter: Int, sizeInner: Int): Array<IntArray>
        = Array(sizeOuter) { IntArray(sizeInner) }

// Could reuse the one of day 1 but let's keep days encapsulated for now.
data class Point2D (var x: Int, var y : Int)

class PartOne {

    val inputLocation = "data/input02.txt"
    val solutionInput = File(inputLocation).readText()
    val keypad = array2dOfInt(3, 3)
    var currentPosition = Point2D(1, 1) // Start in center of keypad.
    var code = ArrayList<Int>()

    init {
        // Must be a better way of doing this.
        // Figure it out later.
        keypad[0][0] = 1
        keypad[1][0] = 2
        keypad[2][0] = 3
        keypad[0][1] = 4
        keypad[1][1] = 5
        keypad[2][1] = 6
        keypad[0][2] = 7
        keypad[1][2] = 8
        keypad[2][2] = 9
    }

    fun move(direction : TurnDirection) {
        when (direction) {
            TurnDirection.R -> {
                if (currentPosition.x < keypad[0].size - 1)
                    currentPosition.x++
            }
            TurnDirection.D -> {
                if (currentPosition.y < keypad[0].size - 1)
                    currentPosition.y++
            }
            TurnDirection.L -> {
                if (currentPosition.x > 0)
                    currentPosition.x--
            }
            TurnDirection.U -> {
                if (currentPosition.y > 0)
                    currentPosition.y--
            }
        }
    }

    @Test fun test_example() {
        val testInput = """ULL
RRDDD
LURDL
UUUUD
"""

        for (char : Char in testInput) {
            if (char == '\n') {
                code.add(keypad[currentPosition.x][currentPosition.y])
            }
            else {
                move(TurnDirection.valueOf(char.toString()))
            }
        }

        assert(code.size == 4)
        assert(code[0] == 1)
        assert(code[1] == 9)
        assert(code[2] == 8)
        assert(code[3] == 5)
    }

    @Test fun test_solution() {
        for (char : Char in solutionInput) {
            if (char == '\n') {
                code.add(keypad[currentPosition.x][currentPosition.y])
            }
            else {
                move(TurnDirection.valueOf(char.toString()))
            }
        }

        System.out.println(code)
    }
}