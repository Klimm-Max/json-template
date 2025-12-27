package io.github.matoklimm.functions

import io.github.matoklimm.api.TemplateFunction
import io.github.matoklimm.core.ExecutionContext
import java.time.OffsetDateTime

internal class Timestamp : TemplateFunction {
    override val name = "timestamp"

    override fun execute(args: MutableList<String>, ctx: ExecutionContext): String {
        return when (args.size) {
            0 -> OffsetDateTime.now(ctx.clock).toString()
            else -> throw IllegalArgumentException("timestamp will always return 'now()', if you want to use interval borders, use 'randomTimestamp()' instead")
        }
    }
}