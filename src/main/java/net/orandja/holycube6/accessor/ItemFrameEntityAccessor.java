package net.orandja.holycube6.accessor;

import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(ItemFrameEntity.class)
public interface ItemFrameEntityAccessor {

    @Accessor("fixed")
    void setFixed(boolean fixed);

    @Accessor
    boolean getFixed();

}
