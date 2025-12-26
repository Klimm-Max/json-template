package core

import io.github.klimmmax.core.ExpressionParser
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExpressionParserTest {
    private val parser = ExpressionParser()

    @Test
    fun `parses function without arguments`() {
        val expr = parser.parse("count()")

        assertEquals("count", expr.functionName)
        assertEquals(emptyList<String>(), expr.args)
    }

    @Test
    fun `parses function with single argument`() {
        val expr = parser.parse("randomInt(10)")

        assertEquals("randomInt", expr.functionName)
        assertEquals(listOf("10"), expr.args)
    }

    @Test
    fun `parses function with multiple arguments`() {
        val expr = parser.parse("randomInt(10, 20)")

        assertEquals("randomInt", expr.functionName)
        assertEquals(listOf("10", "20"), expr.args)
    }

    @Test
    fun `parses arguments with whitespace`() {
        val expr = parser.parse(" echo(  a ,  b , c ) ")

        assertEquals("echo", expr.functionName)
        assertEquals(listOf("a", "b", "c"), expr.args)
    }

    @Test
    fun `function without parentheses is parsed as name only`() {
        val expr = parser.parse("count")

        assertEquals("count", expr.functionName)
        assertEquals(emptyList(), expr.args)
    }

    @Test
    fun `empty arguments are ignored`() {
        val expr = parser.parse("fn(1,,2,)")

        assertEquals("fn", expr.functionName)
        assertEquals(listOf("1", "2"), expr.args)
    }

    @Test
    fun `only parentheses results in empty args`() {
        val expr = parser.parse("fn()")

        assertEquals("fn", expr.functionName)
        assertEquals(emptyList<String>(), expr.args)
    }
}