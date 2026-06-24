package com.ziroau.lib.classes

import com.ziroau.lib.data.Id
import net.minecraft.block.Block
import net.minecraft.item.BlockItem

/**
 * Literally a `BlockItem` class, except it also holds its full namespace/path in `id`,
 * purely for ease of access later.
 *
 * You can use this with `RegistryHelpers.registerSimpleBlock(this)` to automatically
 * register the block and its item form at the same time.
 */
open class BaseBlockItem(
    val id: Id,
    block: Block,
    val itemSettings: Settings
) : BlockItem(block, itemSettings)