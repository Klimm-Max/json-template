/*
 * Copyright 2025 Maximilian Klimm
 *
 * Licensed under the Apache License, Version 2.0
 */
package io.github.matoklimm.api

import io.github.matoklimm.core.ExecutionContext

/**
 * A template function that can be used inside `${...}` expressions.
 * The name must case-sensitive match the used function-name inside the expression.
 *
 *  @param args the comma-separated list or arguments within parenthesis functionName(arg1, arg2, ...)
 *
 * Implementations must be stateless or rely only on the provided [ExecutionContext].
 */
interface TemplateFunction {
    val name: String
    fun execute(args: MutableList<String>, ctx: ExecutionContext): String
}