package core

import io.github.matoklimm.api.TemplateFunction
import io.github.matoklimm.core.ExecutionContext
import io.github.matoklimm.core.ExpressionParser
import io.github.matoklimm.core.FunctionRegistry
import io.github.matoklimm.core.TemplateRenderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach

class TemplateRendererTest {

    private lateinit var renderer: TemplateRenderer

    @BeforeEach
    fun setup() {
        val registry = FunctionRegistry().apply {
            register(EchoFunction())
        }

        renderer = TemplateRenderer(
            registry = registry,
            parser = ExpressionParser(),
            context = ExecutionContext()
        )
    }

    @Test
    fun `single expression is replaced`() {
        val input = "value=${'$'}{echo(1,2,3)}"

        val result = renderer.render(input)

        assertEquals("value=1,2,3", result)
    }

    @Test
    fun `multiple expressions are replaced`() {
        val input = "a=${'$'}{echo(A)} b=${'$'}{echo(B,C)}"

        val result = renderer.render(input)

        assertEquals("a=A b=B,C", result)
    }

    @Test
    fun `template without expressions is unchanged`() {
        val input = "plain text without template"

        val result = renderer.render(input)

        assertEquals(input, result)
    }
}

internal class EchoFunction : TemplateFunction {
    override val name = "echo"

    override fun execute(args: MutableList<String>, ctx: ExecutionContext): String {
        return args.joinToString(",")
    }
}