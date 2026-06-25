package com.ziroau.lib.helpers

import net.fabricmc.loader.api.FabricLoader

object CompatHelpers {
    /**
     * Literally just a shorthand for `FabricLoader.getInstance().isModLoaded(modId)`
     * @return `true`/`false`
     * @sample isLoaded
     */
    fun isLoaded(modId: String): Boolean {
        return FabricLoader.getInstance().isModLoaded(modId)
    }

    /**
     * Runs the given action if the specified mod is loaded. Optionally, runs another action if the mod is not loaded.
     *
     * @param modId The ID of the mod to check
     * @param action The action to run if the mod is loaded
     * @param otherwise The action to run if the mod is not loaded (optional)
     */
    fun runIfLoaded(
        modId: String,
        action: () -> Unit, otherwise: (() -> Unit)? = null
    ) {
        if (isLoaded(modId)) {
            action()
        } else {
            otherwise?.invoke()
        }
    }
}
