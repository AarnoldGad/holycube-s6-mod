package net.orandja.holycube6.mixin;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LightBlock.class)
public abstract class LightBlockMixin extends Block implements Waterloggable {
    public LightBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (!world.isClient) {
            if (player.getMainHandStack().getItem().equals(Items.LIGHT)) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
        super.onBlockBreakStart(state, world, pos, player);
    }
}
