package com.example.aspectjtx

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Service2(val jdbcTemplate: JdbcTemplate) {

    @Transactional
    fun update() {
        jdbcTemplate.execute("UPDATE stuff SET updated = true")
        // Throw an exception, as this is transactional the above change shouldn't be committed.
        throw RuntimeException("oh no")
    }
}