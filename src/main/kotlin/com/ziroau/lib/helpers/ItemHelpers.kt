package com.ziroau.lib.helpers

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand

object ItemHelpers {

    /**
     * Decrements the stack size of the item held by the player by 1.
     *
     * @return `true` if the item stack was decremented successfully, `false` otherwise.
     */
    fun decrementPlayerHeldItemStack(player: PlayerEntity, hand: Hand, amount: Int = 1): Boolean {
        val stack = player.getStackInHand(hand)
        if (!stack.isEmpty) {
            stack.decrementUnlessCreative(amount, player)
            if (stack.isEmpty) player.setStackInHand(hand, ItemStack.EMPTY)
            return true
        }
        return false
    }

    /**
     * Checks if the stack can be increased by the amount specified without exceeding it's maximum stack size.
     */
    fun canIncreaseStackBy(stack: ItemStack, by: Int): Boolean {
        return stack.isStackable && stack.count + by <= stack.maxCount
    }

    /**
     * Checks if the stack can be increased by '1' without exceeding it's maximum stack size.
     */
    fun canIncrease(stack: ItemStack): Boolean {
        return stack.isStackable && stack.count < stack.maxCount
    }
}
