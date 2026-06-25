package com.ziroau.lib.helpers

import com.ziroau.lib.data.Id
import net.minecraft.text.MutableText
import net.minecraft.text.Text.translatable

object TranslationHelpers {
    fun translation(type: String, namespace: String, path: String): MutableText {
        return translatable(translationKey(type, namespace, path))
    }

    fun translation(type: String, id: Id): MutableText {
        return translatable(translationKey(type, id))
    }

    fun translationKey(type: String, id: Id): String {
        return "$type.${id.namespace}.${id.path}"
    }
    fun translationKey(type: String, namespace: String, path: String): String {
        return "$type.$namespace.$path"
    }
}
