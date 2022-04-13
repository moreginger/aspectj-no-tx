package com.example.aspectjtx

import org.jooq.DSLContext
import org.jooq.impl.DSL.field
import org.jooq.impl.DSL.table
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(properties = [ "foo=bar" ])
class TestTransaction(@Autowired val dslContext: DSLContext, @Autowired val service: Service2) {

    @Test
    fun testTransaction() {
        dslContext.query("DELETE FROM stuff").execute()
        dslContext.query("INSERT INTO stuff (id, updated) VALUES ('thestuff', false)").execute()
        try {
            service.update()
            Assertions.fail("Should throw")
        }
        catch (e: Exception) {
            // Expected
        }
        val isUpdated = dslContext.select(field("id"), field("updated")).from(table("stuff"));
        val record = isUpdated.fetchSingle()!!
        Assertions.assertEquals("thestuff", record.value1())
        Assertions.assertEquals(false, record.value2(), "The updated field should not have been updated.")
    }

}
