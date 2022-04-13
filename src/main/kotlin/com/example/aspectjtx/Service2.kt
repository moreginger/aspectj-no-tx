package com.example.aspectjtx

import org.jooq.DSLContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Service2(val dslContext: DSLContext) {

    @Transactional
    fun update() {
        dslContext.query("UPDATE stuff SET updated = true").execute()
        // Throw an exception, as this is transactional the above change shouldn't be committed.
        throw RuntimeException("oh no")
    }
}