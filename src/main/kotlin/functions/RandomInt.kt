package io.github.matoklimm.functions

import io.github.matoklimm.api.TemplateFunction
import io.github.matoklimm.core.ExecutionContext

internal class RandomInt : TemplateFunction {
    override val name = "randomInt"

    override fun execute(args: MutableList<String>, ctx: ExecutionContext): String {
        return when (args.size) {
            0 -> ctx.random.nextInt().toString()

            1 -> {
                val max = args[0].toIntOrNull()
                    ?: throw IllegalArgumentException("randomInt(max): max must be an integer, got '${args[0]}'")

                return if (max < 0) {
                    ctx.random.nextInt(max, 0).toString()
                } else {
                    ctx.random.nextInt(max + 1).toString()
                }
            }

            2 -> {
                val min = args[0].toIntOrNull()
                    ?: throw IllegalArgumentException("randomInt(min,max): min must be an integer, got '${args[0]}'")
                val max = args[1].toIntOrNull()
                    ?: throw IllegalArgumentException("randomInt(min,max): max must be an integer, got '${args[1]}'")

                require(min <= max) {
                    "randomInt(min,max): min ($min) must be <= max ($max)"
                }

                ctx.random.nextInt(min, max + 1).toString()
            }

            else -> throw IllegalArgumentException(
                "randomInt expects 0, 1 or 2 arguments but got ${args.size}"
            )
        }
    }
}