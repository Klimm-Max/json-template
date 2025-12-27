package io.github.matoklimm.functions

import io.github.matoklimm.api.TemplateFunction
import io.github.matoklimm.core.ExecutionContext

internal class RandomFloat : TemplateFunction {
    override val name = "randomFloat"

    override fun execute(args: MutableList<String>, ctx: ExecutionContext): String {
        var decimals = 2
        when (args.size) {
            0 -> return ctx.random.nextFloat().round(decimals)
            1 -> args.add("0")  // adding 0 so whatever the user inputs can be taken as valid min or max border
            2 -> {}
            3 -> {
                decimals = args[2].toIntOrNull()
                    ?: throw IllegalArgumentException("randomFloat(min,max,decimals): decimals must be an integer, got '${args[2]}'")
            }

            else -> throw IllegalArgumentException("randomFloat expects 0, 1, 2 or 3 arguments but got ${args.size}")
        }

        return getRandomValueFromMinMax(args, ctx).round(decimals)
    }

    private fun getRandomValueFromMinMax(args: List<String>, ctx: ExecutionContext): Float {
        val firstArg = args[0].toIntOrNull()
            ?: throw IllegalArgumentException("randomFloat(min,max): min must be an integer, got '${args[0]}'")
        val secondArg = args[1].toIntOrNull()
            ?: throw IllegalArgumentException("randomFloat(min,max): max must be an integer, got '${args[1]}'")

        val min = minOf(firstArg, secondArg)
        val max = maxOf(firstArg, secondArg)

        return (ctx.random.nextFloat() * (max.toFloat() - min.toFloat()) + min)
    }

    fun Float.round(decimals: Int = 2): String = "%.${decimals}f".format(this).replace(",", ".")
}