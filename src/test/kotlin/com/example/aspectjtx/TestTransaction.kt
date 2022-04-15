package com.example.aspectjtx

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(properties = ["foo=bar"])
class TestTransaction(@Autowired val jdbcTemplate: JdbcTemplate, @Autowired val service: Service2) {

    @Test
    fun testTransaction() {
        jdbcTemplate.execute("DELETE FROM stuff")
        jdbcTemplate.execute("INSERT INTO stuff (id, updated) VALUES (11, false)")
        try {
            service.update()
            Assertions.fail("Should throw")
        } catch (e: Exception) {
            // Expected
        }
        val mapper = RowMapper<Pair<Int, Boolean>> { rs, rn -> Pair(rs.getInt("id"), rs.getBoolean("updated")) }
        val theStuff = jdbcTemplate.queryForObject("SELECT * FROM stuff", mapper)!!
        Assertions.assertEquals(11, theStuff.first)
        Assertions.assertEquals(false, theStuff.second, "The 'updated' field should not have been updated.")
    }

}
