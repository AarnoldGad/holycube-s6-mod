package net.orandja.holycube6.mixin;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.orandja.holycube6.modules.DebugWrench;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

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

    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isCreativeLevelTwoOp()Z"))
    public boolean bypass_creative(PlayerEntity player, BlockState state, World world, BlockPos pos, PlayerEntity player1, BlockHitResult hit) {
        return true;
    }
}
