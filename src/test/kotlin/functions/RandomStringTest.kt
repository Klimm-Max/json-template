package functions

import io.github.klimmmax.core.ExecutionContext
import io.github.klimmmax.functions.RandomString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import kotlin.random.Random
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class RandomStringTest {

    private lateinit var ctx: ExecutionContext
    private lateinit var fn: RandomString

    @BeforeEach
    fun setup() {
        ctx = ExecutionContext(random = Random(123))
        fn = RandomString()
    }

    @Test
    fun `no args returns any string with length of 32`() {
        val result = fn.execute(emptyList(), ctx)
        // deterministic because of seed
        assertEquals(32, result.length)
        assertEquals("ON6DJSIc6Wms5O4NgmKbUVWXG5ck2LbH", result)
    }

    @Test
    fun `one arg is used as max length`() {
        val result = fn.execute(listOf("10"), ctx)
        assertEquals(result.length, 10)
        assertEquals("ON6DJSIc6W", result)
    }

    @Test
    fun `two args use max length and substring`() {
        val result = fn.execute(listOf("10", "prefixString-"), ctx)
        assertEquals(result.length, 23)
        assertEquals("prefixString-ON6DJSIc6W", result)
    }

    @Test
    fun `non integer arg or invalid value as max length throws`() {
        assertFailsWith<IllegalArgumentException> {
            fn.execute(listOf("abc"), ctx)
        }

        assertFailsWith<IllegalArgumentException> {
            fn.execute(listOf("256"), ctx)
        }

        assertFailsWith<IllegalArgumentException> {
            fn.execute(listOf("-12", "prefixString"), ctx)
        }
    }

    @Test
    fun `too many args throws`() {
        assertFailsWith<IllegalArgumentException> {
            fn.execute(listOf("1", "2", "3"), ctx)
        }
    }
}