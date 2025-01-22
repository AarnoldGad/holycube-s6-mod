package net.orandja.holycube6.accessor;

import net.minecraft.block.CrafterBlock;
import net.minecraft.block.DecoratedPotBlock;
import net.minecraft.block.enums.Orientation;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CrafterBlock.class)
public interface CrafterBlockAccessor {
    @Accessor("ORIENTATION")
    public static EnumProperty<Orientation> getOrientation() {
        throw new AssertionError();
    }
}
