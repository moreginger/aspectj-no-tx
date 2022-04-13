package com.example.aspectjtx

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.AdviceMode
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
class AspectjTxApplication

fun main(args: Array<String>) {
    runApplication<AspectjTxApplication>(*args)
}
