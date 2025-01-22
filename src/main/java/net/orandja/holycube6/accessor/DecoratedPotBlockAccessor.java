package net.orandja.holycube6.accessor;

import net.minecraft.block.DecoratedPotBlock;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DecoratedPotBlock.class)
public interface DecoratedPotBlockAccessor {
    @Accessor("FACING")
    public static EnumProperty<Direction> getFacing() {
        throw new AssertionError();
    }
}
