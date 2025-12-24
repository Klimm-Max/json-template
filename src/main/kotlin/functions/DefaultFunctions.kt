package io.github.klimmmax.functions

import io.github.klimmmax.core.FunctionRegistry

internal object DefaultFunctions {

    fun registerInto(registry: FunctionRegistry) {
        registry.register(RandomInt())
    }

}