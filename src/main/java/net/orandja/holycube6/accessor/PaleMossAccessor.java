package net.orandja.holycube6.accessor;

import net.minecraft.block.CrafterBlock;
import net.minecraft.block.PaleMossCarpetBlock;
import net.minecraft.block.enums.Orientation;
import net.minecraft.block.enums.WallShape;
import net.minecraft.state.property.EnumProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PaleMossCarpetBlock.class)
public interface PaleMossAccessor {
    @Accessor("NORTH")
    public static EnumProperty<WallShape> getNorth() {
        throw new AssertionError();
    }
    @Accessor("SOUTH")
    public static EnumProperty<WallShape> getSouth() {
        throw new AssertionError();
    }
    @Accessor("EAST")
    public static EnumProperty<WallShape> getEast() {
        throw new AssertionError();
    }
    @Accessor("WEST")
    public static EnumProperty<WallShape> getWest() {
        throw new AssertionError();
    }
}
