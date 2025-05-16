package kotlinexercises

import kotlin.math.PI
import kotlin.random.Random


/**
 * ## Exercise 1
 *
 * You have a list of "green" numbers and a list of "red" numbers.
 * Complete the code to print how many numbers there are in total.
 */
fun exercise1() {
    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)
    val totalCount = greenNumbers.count() + redNumbers.count()
    println("Total count of all numbers: $totalCount")
}

/**
 * ## Exercise 2
 *
 * You have a set of protocols supported by your server. A user requests to use
 * a particular protocol. Complete the program to check whether the requested protocol
 * is supported or not (isSupported must be a Boolean value).
 */
fun exercise2() {
    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    val isSupported = requested.uppercase() in SUPPORTED
    println("Support for $requested: $isSupported")
}

/**
 * ## Exercise 3
 *
 * Define a map that relates integer numbers from 1 to 3 to their corresponding spelling.
 * Use this map to spell the given number.
 */
fun exercise3() {
    val number2word = mapOf(1 to "one", 2 to "two", 3 to "three")
    val n = 2
    println("$n is spelt as '${number2word[n]}'")
}

/**
 * ## Exercise 4
 *
 * Create a simple game where you win if throwing two dice results in the same number.
 * Use it to print "You win :)" if the dice match or "You lose :(" otherwise.
 */
fun exercise4() {
    val firstDiceResult = Random.nextInt(1, 7)
    val secondDiceResult = Random.nextInt(1, 7)

    println("First dice: $firstDiceResult")
    println("Second dice: $secondDiceResult")

    if (firstDiceResult == secondDiceResult) {
        println("You win! :)")
    } else {
        println("You lose :(")
    }
}

/**
 * ## Exercise 5
 *
 * You have a program that counts pizza slices until there's a whole pizza with 8 slices.
 * Refactor this program in two ways:
 * 1. Use a while loop.
 * 2. Use a do-while loop.
 */
fun exercise5() {
    println("While loop version:")
    var pizzaSlices = 0
    while (pizzaSlices < 7) {
        pizzaSlices++
        println("There's only $pizzaSlices slice/s of pizza :(")
    }
    pizzaSlices++
    println("There are $pizzaSlices slices of pizza. We have a whole pizza!")

    println("\nDo-while loop version:")
    pizzaSlices = 0
    do {
        pizzaSlices++
        if (pizzaSlices < 8) {
            println("There's only $pizzaSlices slice/s of pizza :(")
        } else {
            println("There are $pizzaSlices slices of pizza. We have a whole pizza!")
        }
    } while (pizzaSlices < 8)
}

/**
 * ## Exercise 6
 *
 * Write a program that emulates the Fizz Buzz game. Your task is to print numbers
 * from 1 to 100 incrementally, replacing any number divisible by three with the word "fizz",
 * and any number divisible by five with the word "buzz". Any number divisible by both 3 and 5
 * must be replaced with the word "fizzbuzz".
 */
fun exercise6() {
    for (number in 1..100) {
        when {
            number % 15 == 0 -> println("fizzbuzz")
            number % 3 == 0 -> println("fizz")
            number % 5 == 0 -> println("buzz")
            else -> println(number)
        }
    }
}

/**
 * ## Exercise 7
 *
 * You have a list of words. Use for and if to print only the words that start with the letter L.
 */
fun exercise7() {
    val words = listOf("dinosaur", "limousine", "magazine", "language")

    println("Words starting with the letter 'l':")
    for (w in words) {
        if (w.startsWith("l")) {
            println(w)
        }
    }
}

/**
 * ## Exercise 8
 *
 * Write a function called circleArea that takes the radius of a circle in integer format
 * as a parameter and outputs the area of that circle.
 */
fun circleArea(radius: Int): Double {
    return PI * radius * radius
}

fun exercise8() {
    println("Area of circle with radius 2: ${circleArea(2)}")  // 12.566370614359172
}

/**
 * ## Exercise 9
 *
 * Rewrite the circleArea function from the previous exercise as a single-expression function.
 */
fun circleAreaSingleExpression(radius: Int): Double = PI * radius * radius

fun exercise9() {
    println("Area of circle with radius 2 (single expression): ${circleAreaSingleExpression(2)}")  // 12.566370614359172
}

/**
 * ## Exercise 10
 *
 * You have a list of actions supported by a web service, a common prefix for all requests,
 * and an ID of a particular resource. To request an action "title" over the resource with ID: 5,
 * you need to create the following URL: https://example.com/book-info/5/title.
 * Use a lambda expression to create a list of URLs from the list of actions.
 */
fun exercise10() {
    val actions = listOf("title", "year", "author")
    val prefix = "https://example.com/book-info"
    val id = 5
    val urls = actions.map { action -> "$prefix/$id/$action" }
    println(urls)
}

/**
 * ## Exercise 11
 *
 * Write a function that takes an Int value and an action (a function of type () -> Unit)
 * which then repeats the action the given number of times. Then use this function
 * to print "Hello" 5 times.
 */
fun repeat(n: Int, action: () -> Unit) {
    for (i in 1..n) {
        action()
    }
}

fun exercise11() {
    repeat(5) {
        println("Hello")
    }
}

/**
 * ## Exercise 12
 *
 * Define a data class Employee with two properties: one for a name, and another for a salary.
 * Make sure that the property for salary is mutable, otherwise you won't get a salary boost
 * at the end of the year! The main function demonstrates how you can use this data class.
 */
data class Employee(val name: String, var salary: Int)

fun exercise12() {
    val emp = Employee("Mary", 20)
    println(emp)
    emp.salary += 10
    println(emp)
}

/**
 * Main function demonstrating all exercises
 */
fun main() {
    println("=== Exercise 1 ===")
    exercise1()

    println("\n=== Exercise 2 ===")
    exercise2()

    println("\n=== Exercise 3 ===")
    exercise3()

    println("\n=== Exercise 4 ===")
    exercise4()

    println("\n=== Exercise 5 ===")
    exercise5()

    println("\n=== Exercise 6 ===")
    println("Printing only the first 100 results...")
    for (number in 1..10) {
        when {
            number % 15 == 0 -> print("fizzbuzz, ")
            number % 3 == 0 -> print("fizz, ")
            number % 5 == 0 -> print("buzz, ")
            else -> print("$number, ")
        }
    }
    println("...")

    println("\n=== Exercise 7 ===")
    exercise7()

    println("\n=== Exercise 8 ===")
    exercise8()

    println("\n=== Exercise 9 ===")
    exercise9()

    println("\n=== Exercise 10 ===")
    exercise10()

    println("\n=== Exercise 11 ===")
    exercise11()

    println("\n=== Exercise 12 ===")
    exercise12()
}