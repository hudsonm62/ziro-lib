package com.ziroau.lib.helpers

import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

object SoundHelpers {
    /**
     * Plays a sound event at the specified position in the world.
     *
     * @param world World to play in
     * @param pos Position to play the sound at
     * @param sound Sound event to play
     * @param category Sound category
     * @param volume Volume of the sound (defaults to 1.0f)
     * @param pitch Pitch of the sound (defaults to 1.0f)
     */
    fun playSoundInWorld(
        world: World, pos: BlockPos, sound: SoundEvent, category: SoundCategory,
        volume: Float = 1.0f, pitch: Float = 1.0f
    ) {
        world.playSound(null, pos, sound, category, volume, pitch)
    }

    /**
     * Plays a sound event at the specified position in the world.
     *
     * @param world World to play in
     * @param pos Position to play the sound at
     * @param sound Sound event to play
     * @param category Sound category
     * @param volume Volume of the sound (defaults to 1.0f)
     * @param pitch Pitch of the sound (defaults to 1.0f)
     */
    fun playSoundInWorld(
        world: World, pos: Vec3d, sound: SoundEvent, category: SoundCategory,
        volume: Float = 1.0f, pitch: Float = 1.0f
    ) {
        playSoundInWorld(world, BlockPos.ofFloored(pos), sound, category, volume, pitch)
    }
}
