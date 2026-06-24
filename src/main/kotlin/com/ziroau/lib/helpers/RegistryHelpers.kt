package com.ziroau.lib.helpers

import com.ziroau.lib.classes.BaseBlockItem
import com.ziroau.lib.classes.BaseItem
import com.ziroau.lib.data.Id
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object RegistryHelpers {
    fun makeId(id: Id): Identifier {
        return Identifier.of(id.namespace, id.path)
    }

    fun makeId(namespace: String, path: String): Identifier {
        return Identifier.of(namespace, path)
    }

    /**
     * Registers an Item under its ID, which is made up of the provided namespace and path.
     * @param id The ID (namespace + path) to register the item under
     * @param item The item object to register
     * @return The registered `Item` object
     */
    fun registerItem(
        id: Id,
        item: Item
    ): Item {
        return Registry.register(
            Registries.ITEM,
            makeId(id),
            item
        )
    }

    fun registerItem(
        item: BaseItem
    ): Item {
        return Registry.register(
            Registries.ITEM,
            makeId(item.id),
            item
        )
    }

    fun registerItem(
        baseBlockItem: BaseBlockItem
    ): Item {
        return Registry.register(
            Registries.ITEM,
            makeId(baseBlockItem.id),
            baseBlockItem
        )
    }

    /**
     * Registers a block and its corresponding BlockItem with the same ID.
     * @param id The ID (namespace + path) to register the block+item under
     * @param block The block object to register
     * @param itemSettings `Item.Settings` for the blocks' item form, default is just `Item.Settings()`.
     * @return The Registered `Block` object
     */
    fun registerSimpleBlock(
        id: Id,
        block: Block,
        itemSettings: Item.Settings = Item.Settings()
    ): Block {
        val r = Registry.register(
            Registries.BLOCK,
            makeId(id),
            block
        )
        registerItem(id, BlockItem(block, itemSettings))
        return r
    }

    fun registerSimpleBlock(
        baseBlockItem: BaseBlockItem
    ): Block {
        val r = Registry.register(
            Registries.BLOCK,
            makeId(baseBlockItem.id),
            baseBlockItem.block
        )
        registerItem(baseBlockItem)
        return r
    }
}
