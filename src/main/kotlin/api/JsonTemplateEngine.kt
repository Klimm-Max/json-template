/*
 * Copyright 2025 Maximilian Klimm
 *
 * Licensed under the Apache License, Version 2.0
 */
package io.github.matoklimm.api

import io.github.matoklimm.core.TemplateRenderer

class JsonTemplateEngine internal constructor(private val renderer: TemplateRenderer) {
    fun render(template: String): String = renderer.render(template)
}