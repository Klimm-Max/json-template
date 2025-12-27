package io.github.matoklimm.core

import io.github.matoklimm.api.TemplateFunction

internal class FunctionRegistry {

    private val functions = mutableMapOf<String, TemplateFunction>()

    fun register(templateFunction: TemplateFunction) {
        check(!functions.containsKey(templateFunction.name)) {
            throw IllegalArgumentException("Function '${templateFunction.name}' already registered!")
        }

        functions[templateFunction.name] = templateFunction
    }

    fun get(name: String) = functions[name] ?: throw IllegalArgumentException("Unknown function '$name'")
}