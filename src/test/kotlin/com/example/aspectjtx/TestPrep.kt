package com.example.aspectjtx

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

// To reproduce the issue we need this test to run first.
@SpringBootTest
class TestPrep(@Autowired val service1: Service1) {

    @Test
    fun prep() {
        service1.doSomething()
        Assertions.fail<String>("This test fails intentionally so that the order of test execution doesn't change.")
    }

}
