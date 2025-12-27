package io.github.matoklimm.functions

import io.github.matoklimm.api.TemplateFunction
import io.github.matoklimm.core.ExecutionContext
import java.util.UUID

internal class RandomUuid: TemplateFunction {
    override val name = "randomUuid"

    override fun execute(args: MutableList<String>, ctx: ExecutionContext) = UUID.randomUUID().toString()
}