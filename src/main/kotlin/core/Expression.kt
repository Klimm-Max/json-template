package io.github.matoklimm.core

internal data class Expression(
    val functionName: String,
    val args: MutableList<String>
)
