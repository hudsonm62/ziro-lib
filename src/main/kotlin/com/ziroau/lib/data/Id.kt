package com.ziroau.lib.data

/**
 * A unique identifier for a resource, consisting of a "namespace" & "path" to make a full "ID".
 *
 * This is purely for ease of use and to avoid having to split strings all the time, as well as to make it more clear when an ID is being used.
 *
 * @property namespace The namespace of the mod, package, datapack, etc. - Typically your mod ID though.
 * @property path The path of the resource. Whilst not technically true, do note that this in and of itself is sometimes referred to as an ID for resources (i.e. Item IDs, Block IDs, etc.).
 */
data class Id(
    val namespace: String,
    val path: String
)
