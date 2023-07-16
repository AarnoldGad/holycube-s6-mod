package net.orandja.holycube6.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.orandja.holycube6.modules.DebugWrench;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntity.class)
public abstract class ItemFrameEntityMixin extends AbstractDecorationEntity {

    protected ItemFrameEntityMixin(EntityType<? extends AbstractDecorationEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "interact", at = @At("HEAD"), cancellable= true)
    public void interact(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> info) {
        DebugWrench.Companion.interactItemFrame(this, player, hand, info);
    }

    @Override
    public boolean handleAttack(Entity attacker) {
        return DebugWrench.Companion.attackItemFrame(this, attacker) || super.handleAttack(attacker);
    }
}
