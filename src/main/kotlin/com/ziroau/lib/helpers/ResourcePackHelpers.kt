package com.ziroau.lib.helpers

import net.fabricmc.api.EnvType.CLIENT
import net.fabricmc.fabric.api.resource.ResourceManagerHelper.registerBuiltinResourcePack
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.text.Text

object ResourcePackHelpers {
    /**
     * Registers a built-in resource pack for the mod. This should be called during initialization.
     *
     * The path in which the resource pack is located is in the mod JAR file under the "`resourcepacks/<id path>`" directory. `<id path>` being the path specified
     * in the identifier of this built-in resource pack.
     *
     * @param modId The namespace of the resource pack, usually the mod id
     * @param resourcePackId The path id of the resource pack, usually a unique name for the resource pack
     * @param activationType The activation type of the resource pack - "NORMAL" by default.
     * @param displayName A display name for your pack - Defaults to a `Text.translatable` key, which should be left default for localization.
     * @return `true` if successfully registered the resource pack, else `false`
     */
    fun registerResourcePack(
        modId: String, resourcePackId: String,
        activationType: ResourcePackActivationType = ResourcePackActivationType.NORMAL,
        displayName: Text = TranslationHelpers.translation("resourcePack", modId, resourcePackId)
    ): Boolean {
        val instance = FabricLoader.getInstance()
        if (instance.environmentType == CLIENT) {
            val container = instance.getModContainer(modId).orElse(null)
            if (container != null) {
                return registerBuiltinResourcePack(
                    RegistryHelpers.makeId(modId, resourcePackId),
                    container,
                    displayName,
                    activationType
                )
            }
        }
        return false
    }
}
