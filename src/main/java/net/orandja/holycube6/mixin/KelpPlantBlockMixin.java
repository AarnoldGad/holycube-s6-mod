package net.orandja.holycube6.mixin;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.orandja.holycube6.utils.KelpPlantLogic;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(KelpPlantBlock.class)
public abstract class KelpPlantBlockMixin extends AbstractPlantBlock implements FluidFillable, KelpPlantLogic {
    protected KelpPlantBlockMixin(Settings settings, Direction direction, VoxelShape voxelShape, boolean bl) {
        super(settings, direction, voxelShape, bl);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {
        afterBreak(this, world, player, pos, state, blockEntity, stack);
    }

    public Block getKelpPlant() {
        return getPlant();
    }
}
