package net.orandja.holycube6.utils

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

interface NerfedEntity {

    var tickCooldown: Short
    var mobDead: Boolean
    var defaultCooldown: Short

    fun nerf(callbackInfo: CallbackInfo) {
        tickCooldown--
        if (tickCooldown > 0 && !this.mobDead) {
            callbackInfo.cancel()
            return
        }
        tickCooldown = defaultCooldown
    }

}