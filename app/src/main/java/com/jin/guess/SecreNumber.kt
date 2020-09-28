package com.jin.guess

import java.util.*

class SecreNumber {
    var secret = Random().nextInt(10)+1
    var count = 0

    fun validate(number : Int) :Int {
        count++
        return number - secret
    }

    fun reset() {
        secret = Random().nextInt(10)+1
        count = 0
    }
}

fun main() {
    val secreNumber = SecreNumber()
    println(secreNumber.secret)
    println("${secreNumber.validate(2)}, count: ${secreNumber.count}")
}