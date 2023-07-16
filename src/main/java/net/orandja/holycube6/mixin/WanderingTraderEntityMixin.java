package net.orandja.holycube6.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.world.World;
import net.orandja.holycube6.modules.LootBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WanderingTraderEntity.class)
public abstract class WanderingTraderEntityMixin extends MerchantEntity {

    public WanderingTraderEntityMixin(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

// Disable LootBoxes
//    @Inject(method = "fillRecipes", at = @At("RETURN"))
//    public void fillRecipes(CallbackInfo info) {
//        if (this.offers != null) {
//            LootBox.Companion.hackOffers(this.offers);
//        }
//    }

}
