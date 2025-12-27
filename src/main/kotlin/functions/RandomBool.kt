package io.github.matoklimm.functions

import io.github.matoklimm.api.TemplateFunction
import io.github.matoklimm.core.ExecutionContext

internal class RandomBool : TemplateFunction {
    override val name = "randomBool"

    override fun execute(args: MutableList<String>, ctx: ExecutionContext): String = ctx.random.nextBoolean().toString()

}