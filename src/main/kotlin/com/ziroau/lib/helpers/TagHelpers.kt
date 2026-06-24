package com.ziroau.lib.helpers

import com.ziroau.lib.helpers.RegistryHelpers.makeId
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object TagHelpers {
    fun itemTag(modId: String, name: String): TagKey<Item> {
        return TagKey.of(RegistryKeys.ITEM, makeId(modId, name))
    }

    fun blockTag(modId: String, name: String): TagKey<Block> {
        return TagKey.of(RegistryKeys.BLOCK, makeId(modId, name))
    }

    fun entityTag(modId: String, name: String): TagKey<EntityType<*>> {
        return TagKey.of(RegistryKeys.ENTITY_TYPE, makeId(modId, name))
    }

    fun isIn(stack: ItemStack, tag: TagKey<Item>): Boolean {
        return stack.isIn(tag)
    }

    fun isIn(item: Item, tag: TagKey<Item>): Boolean {
        return item.defaultStack.isIn(tag)
    }

    fun isIn(world: World, pos: BlockPos, tag: TagKey<Block>): Boolean {
        return world.getBlockState(pos).isIn(tag)
    }

    fun hasAny(world: World, pos: BlockPos, vararg tags: TagKey<Block>): Boolean {
        val state = world.getBlockState(pos)
        return tags.any { state.isIn(it) }
    }

    fun hasAny(stack: ItemStack, vararg tags: TagKey<Item>): Boolean {
        return tags.any { stack.isIn(it) }
    }

    fun hasAll(stack: ItemStack, vararg tags: TagKey<Item>): Boolean {
        return tags.all { stack.isIn(it) }
    }

    fun hasAll(world: World, pos: BlockPos, vararg tags: TagKey<Block>): Boolean {
        val state = world.getBlockState(pos)
        return tags.all { state.isIn(it) }
    }
}
