package com.ziroau.lib.classes

import com.ziroau.lib.data.Id
import net.minecraft.item.Item

/**
 * Literally an `Item` class, except it also holds it's Identifier in `id`,
 * purely for ease of access later.
 *
 * You can use this with `RegistryHelpers.registerItem(this)` to automatically register the item.
 */
open class BaseItem(
    val id: Id,
    val settings: Settings
) : Item(settings)
