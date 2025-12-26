package core

import io.github.klimmmax.api.JsonTemplateEngineBuilder
import io.github.klimmmax.core.ExecutionContext
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.nio.charset.StandardCharsets
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset
import kotlin.test.assertFailsWith

class JsonTemplateEngineTest {

    private val ctx = ExecutionContext(
        kotlin.random.Random(123),
        Clock.fixed(Instant.parse("2025-12-24T13:37:00Z"), ZoneOffset.UTC)
    )

    val engine = JsonTemplateEngineBuilder()
        .withExecutionContext(ctx)
        .withDefaults()
        .build()

    @Test
    fun `template json is rendered deterministically`() {
        val template = readResource("scenario-1/template.json")
        val expected = readResource("scenario-1/expected.json")

        val result = engine.render(template)

        assertEquals(
            normalizeJson(expected),
            normalizeJson(result)
        )
    }

    @Test
    fun `parsing of incorrect argument causes whole rendering to stop`() {
        val template = readResource("scenario-2/template.json")
        assertFailsWith<IllegalArgumentException> {
            engine.render(template)
        }
    }

    private fun readResource(name: String): String {
        return javaClass.classLoader
            .getResourceAsStream(name)
            ?.bufferedReader(StandardCharsets.UTF_8)
            ?.readText()
            ?: error("Resource not found: $name")
    }

    private fun normalizeJson(json: String): String {
        return json
            .trim()
            .replace(Regex("\\s+"), "")
    }
}