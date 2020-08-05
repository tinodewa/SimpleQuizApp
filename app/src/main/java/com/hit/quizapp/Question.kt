package com.hit.quizapp

data class Question(
    var question: Int,
    var quevalue: Boolean,
    var answer: Boolean) {
    constructor() : this(0,false, false)
}