package com.ziroau.lib.helpers

import com.ziroau.lib.classes.BaseBlockItem
import com.ziroau.lib.classes.BaseItem
import com.ziroau.lib.data.Id
import net.fabricmc.api.EnvType.CLIENT
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.resource.ResourceManagerHelper.registerBuiltinResourcePack
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader.getInstance
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
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

    /**
     * Registers an Item Group (Creative Tab) under the provided namespace and path with some items.
     * @param tabId Identifier to set for the new item group
     * @param icon The icon for the item group
     * @param itemsToAdd The set of items to add to the item group
     * @return The registered `ItemGroup` object
     */
    fun registerSimpleItemGroup(tabId: Id, icon: ItemStack, itemsToAdd: Set<Item>): ItemGroup {
        return Registry.register(
            Registries.ITEM_GROUP,
            makeId(tabId),
            FabricItemGroup.builder()
                .displayName(TranslationHelpers.translation("itemGroup", tabId))
                .icon { icon }
                .entries { _, entries ->
                    itemsToAdd.forEach {
                        entries.add(it)
                    }
                }
                .build()
        )
    }
    fun registerSimpleItemGroup(namespace: String, path: String, icon: ItemStack, itemsToAdd: Set<Item>): ItemGroup {
        return registerSimpleItemGroup(Id(namespace, path), icon, itemsToAdd)
    }

    /**
     * Adds an item to an existing item group (creative tab) at runtime. Useful for adding items to vanilla tabs or other mod tabs.
     */
    fun addToItemGroup(tabId: Id, item: Item) {
        val targetGroupKey = RegistryKey.of(RegistryKeys.ITEM_GROUP, makeId(tabId))
        ItemGroupEvents.modifyEntriesEvent(targetGroupKey).register { entries ->
            entries.add(item)
        }
    }
    fun addToItemGroup(namespace: String, path: String, item: Item) {
        addToItemGroup(Id(namespace, path), item)
    }

    /**
     * Registers a built-in resource pack for the mod. This should be called during initialization.
     *
     * The path in which the resource pack is located is in the mod JAR file under the "`resourcepacks/<id path>`" directory. `<id path>` being the path specified
     * in the identifier of this built-in resource pack.
     *
     * @param resourcePackId The ID of the Resource Pack
     * @param activationType The activation type of the resource pack - "NORMAL" by default.
     * @param displayName A display name for your pack - Defaults to a `Text.translatable` key, which should be left default for localization.
     * @return `true` if successfully registered the resource pack, else `false`
     */
    fun registerResourcePack(
        resourcePackId: Id,
        activationType: ResourcePackActivationType = ResourcePackActivationType.NORMAL,
        displayName: Text = TranslationHelpers.translation("resourcePack", resourcePackId)
    ): Boolean {
        val instance = getInstance()
        if (instance.environmentType == CLIENT) {
            val container = instance.getModContainer(resourcePackId.namespace).orElse(null)
            if (container != null) {
                return registerBuiltinResourcePack(
                    RegistryHelpers.makeId(resourcePackId),
                    container,
                    displayName,
                    activationType
                )
            }
        }
        return false
    }
    fun registerResourcePack(
        namespace: String, path: String,
        activationType: ResourcePackActivationType = ResourcePackActivationType.NORMAL,
        displayName: Text = TranslationHelpers.translation("resourcePack", namespace, path)
    ): Boolean {
        return registerResourcePack(
            Id(namespace, path),
            activationType,
            displayName
        )
    }
}
