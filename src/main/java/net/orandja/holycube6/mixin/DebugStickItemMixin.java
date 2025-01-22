package net.orandja.holycube6.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.orandja.holycube6.modules.DebugWrench;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collection;
import java.util.List;

@Mixin(net.minecraft.item.DebugStickItem.class)
public abstract class DebugStickItemMixin extends net.minecraft.item.Item {
    public DebugStickItemMixin(Settings settings) {
        super(settings);
    }

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isCreativeLevelTwoOp()Z"))
    public boolean bypass_creative(PlayerEntity player, PlayerEntity player1, BlockState state, WorldAccess world, BlockPos pos, boolean update, ItemStack stack) {
        return DebugWrench.Companion.allowWrench(player, stack);
    }

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/state/StateManager;getProperties()Ljava/util/Collection;"))
    public Collection<Property<?>> getProperties(net.minecraft.state.StateManager stateManager, PlayerEntity player1, BlockState state, WorldAccess world, BlockPos pos, boolean update, ItemStack stack) {
        return DebugWrench.Companion.getProperties(state, stack);
    }

    @Redirect(method = "cycle", at = @At(value = "INVOKE", target = "Lnet/minecraft/state/property/Property;getValues()Ljava/util/List;"))
    private static <T extends Comparable<T>> List<T> cycle(Property<T> property1, BlockState state, Property<T> property, boolean inverse) {
        return DebugWrench.Companion.getValues(state, property);
    }
}
