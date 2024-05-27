package net.orandja.holycube6.accessor;

import net.minecraft.entity.decoration.ItemFrameEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemFrameEntity.class)
public interface ItemFrameEntityAccessor {
    @Accessor("fixed")
    void setFixed(boolean fixed);

    @Accessor
    boolean getFixed();
}
