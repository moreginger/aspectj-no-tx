package com.example.aspectjtx

import org.springframework.transaction.annotation.Transactional

@org.springframework.stereotype.Service
class Service1 {

    @Transactional
    fun doSomething() {
        println("woo")
    }
}
