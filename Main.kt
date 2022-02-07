package tictactoe
const val A = 3 // variable that is equal to the length of the string game field
/** printField this is the function that prints the game board to the screen */
fun printField(a: CharArray) {
    println("---------")
    println("| ${a[0]} ${a[1]} ${a[2]} |")
    println("| ${a[A]} ${a[A + 1]} ${a[A + 2]} |")
    println("| ${a[A * 2]} ${a[A * 2 + 1]} ${a[A * 2 + 2]} |")
    println("---------")
}
/** testInput is a function that counts and checks the correctness of the entered data */
fun testInput(a: CharArray): CharArray {
    var inpU = inputPerson()
    while (true) {
        inpU = when {
            !inpU[0].isDigit() || !inpU[2].isDigit() -> {
                println("You should enter numbers!")
                inputPerson()
            }
            inpU[0].digitToInt() !in 1..A || inpU[2].digitToInt() !in 1..A -> {
                println("Coordinates should be from 1 to 3!")
                inputPerson()
            }
            a[(inpU[0].digitToInt() - 1) * A + inpU[2].digitToInt() - 1] != '_' -> {
                println("This cell is occupied! Choose another one!")
                inputPerson()
            }
            else -> break
        }
    }
    return inpU
}
/** inputPerson is a small function that combines displaying a message and reading from the console  */
fun inputPerson(): CharArray {
    print("Enter the coordinates: ")
    return readln().toCharArray()
}
/** ticInput is a function that reads X */
fun ticInput(a: CharArray): CharArray {
    val b = testInput(a)
    a[(b[0].digitToInt() - 1) * A + b[2].digitToInt() - 1] = 'X'
    return a
}

/** ticInput is a function that reads O moves */
fun tacInput(a: CharArray): CharArray {
    val c = testInput(a)
    a[(c[0].digitToInt() - 1) * A + c[2].digitToInt() - 1] = 'O'
    return a
}
/** testWinner is the function that checks the win conditions */
fun testWinner(a: CharArray): Int {
    var fist = 0
    var winy = 0
    val tic = a.count { it == 'X' }
    val tack = a.count { it == 'O' }
    for (i in 0 until a.lastIndex step A) {
        when {
            a[i] == a[i + 1] && a[i] == a[i + 2] && a[i] == 'X' -> fist++
            a[i] == a[i + 1] && a[i] == a[i + 2] && a[i] == 'O' -> winy++
        }
    }
    for (i in 0..2) {
        when {
            a[i] == a[i + A] && a[i] == a[i + A * 2] && a[i] == 'X' -> fist++
            a[i] == a[i + A] && a[i] == a[i + A * 2] && a[i] == 'O' -> winy++
        }
    }
    if (a[0] == a[A + 1] && a[0] == a[A * 2 + 2] && a[0] == 'X' ||
        a[2] == a[A + 1] && a[2] == a[A * 2] && a[2] == 'X') fist++
    if (a[0] == a[A + 1] && a[0] == a[A * 2 + 2] && a[0] == 'O' ||
        a[2] == a[A + 1] && a[2] == a[A * 2] && a[2] == 'O') winy++
    when {
        fist >= 1 -> println("X wins")
        winy >= 1 -> println("O wins")
        fist + winy == 0 && tic + tack == A * A -> println("Draw")
        fist + winy == 0 && tic + tack != A * A -> return 1
    }
    return 0
}
fun main() {
    var a = CharArray(9) {'_'} // game field initialization
    printField(a) // calling the field output function
    while (true) {
        a = ticInput(a) // call function for Tic Tac
        printField(a)
        if (testWinner(a) != 1) break
        a = tacInput(a)
        printField(a)
        if (testWinner(a) != 1) break
    }
}