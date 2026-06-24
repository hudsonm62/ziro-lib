package com.ziroau.lib.classes

import com.ziroau.lib.data.Id
import net.minecraft.component.type.FoodComponent
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundEvent
import net.minecraft.util.UseAction

data class FoodEffect(
    val effect: StatusEffectInstance,
    val chance: Float = 1.0f
)

/**
 * Constructs an `Item` that can be "eaten" to restore hunger and saturation, and optionally apply status effects.
 *
 * You may also want this to be a drinkable liquid, in which case set `useAction` to `UseAction.DRINK`. This should also automatically set `eatSound` too, but you can override it if you need a custom sound.
 *
 * @param id The ID of this Class, purely used for metadata, but can be used in code later for identification or registration.
 * @param saturationModifier The multiplier of the nutritional value
 * @param isAlwaysEdible Whether the item can always be consumed, regardless of hunger level - Useful for status applying foods/drinks.
 * @param useAction The action performed when using the item (e.g., EAT or DRINK) - You probably don't want anything else for this.
 * @param useTicks Time (in ticks) it takes for the item to be consumed (or technically, used).
 * @param customEatSound Overridden sound to play on eat, you usually won't need it as this should be set automatically by `useAction`.
 */
open class EdibleItem(
    namespace: String,
    id: String,
    settings: Settings,
    nutrition: Int,
    saturationModifier: Float,
    isSnack: Boolean = false,
    isAlwaysEdible: Boolean = false,
    var statusEffects: List<FoodEffect> = emptyList(),
    var useAction: UseAction? = null,
    var useTicks: Int? = null,
    var customEatSound: SoundEvent? = null,
) : BaseItem(
    Id(namespace, id),
    settings.food(
        buildFoodComponent(
            nutrition,
            saturationModifier,
            isSnack,
            isAlwaysEdible,
            statusEffects
        )
    )
) {
    companion object {
        private fun buildFoodComponent(
            nutrition: Int,
            saturationModifier: Float,
            isSnack: Boolean,
            isAlwaysEdible: Boolean,
            statusEffects: List<FoodEffect>
        ): FoodComponent {
            val builder = FoodComponent.Builder()
            if (nutrition != 0) builder.nutrition(nutrition)
            if (saturationModifier != 0.0f) builder.saturationModifier(saturationModifier)
            if (isSnack) builder.snack()
            if (isAlwaysEdible) builder.alwaysEdible()
            if (statusEffects.isNotEmpty()) statusEffects.forEach { builder.statusEffect(it.effect, it.chance) }
            return builder.build()
        }
    }

    override fun getUseAction(stack: ItemStack): UseAction {
        return useAction ?: super.getUseAction(stack)
    }

    override fun getMaxUseTime(stack: ItemStack, user: LivingEntity): Int {
        return useTicks ?: super.getMaxUseTime(stack, user)
    }

    override fun getEatSound(): SoundEvent {
        return customEatSound ?: super.getEatSound()
    }
}
