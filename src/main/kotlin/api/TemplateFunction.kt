package io.github.klimmmax.api

import io.github.klimmmax.core.ExecutionContext

interface TemplateFunction {
    val name: String
    fun execute(args: MutableList<String>, ctx: ExecutionContext): String
}