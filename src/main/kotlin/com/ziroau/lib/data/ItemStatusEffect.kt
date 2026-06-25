package com.ziroau.lib.data

import net.minecraft.entity.effect.StatusEffectInstance

/**
 * A container that holds a `StatusEffectInstance` and a chance float.
 *
 * Useful for creating lists of status effects for applying to items when used (like potions and special food).
 *
 * @property effect A built status effect instance
 * @property chance The "probability" float (should be between 0.0 & 1.0), defaults to `1.0f`
 */
data class ItemStatusEffect(
    val effect: StatusEffectInstance,
    val chance: Float = 1.0f
)