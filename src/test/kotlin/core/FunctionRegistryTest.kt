package core

import io.github.klimmmax.api.TemplateFunction
import io.github.klimmmax.core.ExecutionContext
import io.github.klimmmax.core.FunctionRegistry
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertSame

class FunctionRegistryTest {

    private val registry = FunctionRegistry()

    private class TestFunction(override val name: String) : TemplateFunction {
        override fun execute(args: MutableList<String>, ctx: ExecutionContext): String = "ok"
    }

    @Test
    fun `register and get function`() {
        val fn = TestFunction("test")

        registry.register(fn)
        val resolved = registry.get("test")

        assertSame(fn, resolved)
    }

    @Test
    fun `registering same function twice throws`() {
        val fn = TestFunction("dup")

        registry.register(fn)

        val ex = assertThrows(IllegalArgumentException::class.java) {
            registry.register(fn)
        }

        assertTrue(ex.message!!.contains("already registered"))
    }

    @Test
    fun `getting unknown function throws`() {
        val ex = assertThrows(IllegalArgumentException::class.java) {
            registry.get("doesNotExist")
        }

        assertTrue(ex.message!!.contains("Unknown function"))
    }
}