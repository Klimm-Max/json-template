package io.github.klimmmax.functions

import io.github.klimmmax.api.TemplateFunction
import io.github.klimmmax.core.ExecutionContext

internal class RandomBool : TemplateFunction {
    override val name = "randomBool"

    override fun execute(args: MutableList<String>, ctx: ExecutionContext): String = ctx.random.nextBoolean().toString()

}