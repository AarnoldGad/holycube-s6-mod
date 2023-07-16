package net.orandja.holycube6.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.orandja.holycube6.utils.SmoothBrainVillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntity.class)
public abstract class SmoothVillagerMixin extends MerchantEntity implements SmoothBrainVillager {


    @Shadow
    public abstract Brain<VillagerEntity> getBrain();

    public SmoothVillagerMixin(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    public boolean noAI = false;

    @Override
    public void setNoAI(boolean noAI) {
        this.noAI = noAI;
    }

    @Override
    public boolean getNoAI() {
        return this.noAI;
    }

    @Override
    public Brain<VillagerEntity> getSmoothBrain() {
        return this.getBrain();
    }

    @Inject(method = "interactMob", at = @At("HEAD"))
    public void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> info) {
        this.smoothBrainInteract(player, hand, info);
    }

    @Override
    public boolean handleAttack(Entity attacker) {
        return this.smoothBrainHandleAttack(this, attacker) || super.handleAttack(attacker);
    }

    @Redirect(method = "mobTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/brain/Brain;tick(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/LivingEntity;)V"))
    protected <E extends LivingEntity> void mobTick(Brain<E> instance, ServerWorld world, E entity) {
        if (!this.noAI) {
            instance.tick(world, entity);
        } else {
            this.tickSmoothBrain(instance, world, entity);
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo info) {
        nbt.putBoolean("HC_NoAI", this.noAI);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
        this.noAI = nbt.getBoolean("HC_NoAI");
    }

}