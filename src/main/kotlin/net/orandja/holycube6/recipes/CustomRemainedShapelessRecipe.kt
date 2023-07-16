package net.orandja.holycube6.recipes

import net.minecraft.inventory.CraftingInventory
import net.minecraft.inventory.RecipeInputInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.ShapelessRecipe
import net.minecraft.recipe.book.CraftingRecipeCategory
import net.minecraft.util.Identifier
import net.minecraft.util.collection.DefaultedList

class CustomRemainedShapelessRecipe(id: Identifier, group: String, category: CraftingRecipeCategory, output: ItemStack, input: DefaultedList<Ingredient>, val remains: Map<Item, Item>) : ShapelessRecipe(id, group, category, output, input) {

    override fun getRemainder(inventory: RecipeInputInventory): DefaultedList<ItemStack> {
        val defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY)
        for (i in defaultedList.indices) {
            val item = inventory.getStack(i).item
            if(remains.containsKey(item)) {
                defaultedList[i] = ItemStack(remains[item])
            } else if (item.hasRecipeRemainder()) {
                defaultedList[i] = ItemStack(item.recipeRemainder)
            }
        }
        return defaultedList
    }
}