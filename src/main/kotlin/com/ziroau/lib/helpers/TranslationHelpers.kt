package com.ziroau.lib.helpers

import com.ziroau.lib.data.Id
import net.minecraft.text.MutableText
import net.minecraft.text.Text.translatable

object TranslationHelpers {
    fun translation(type: String, namespace: String, path: String): MutableText {
        return translatable("$type.$namespace.$path")
    }

    fun translation(type: String, id: Id): MutableText {
        return translatable("$type.${id.namespace}.${id.path}")
    }
}
