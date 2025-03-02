package com.github.command17.hammering.fabric.data;

import com.github.command17.hammering.Hammering;
import com.github.command17.hammering.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {
        simpleHammerRecipe(ModItems.IRON_HAMMER.get(), RecipeCategory.TOOLS, Items.IRON_INGOT, Items.IRON_BLOCK).save(recipeOutput);
        simpleHammerRecipe(ModItems.GOLDEN_HAMMER.get(), RecipeCategory.TOOLS, Items.GOLD_INGOT, Items.GOLD_BLOCK).save(recipeOutput);
        simpleHammerRecipe(ModItems.DIAMOND_HAMMER.get(), RecipeCategory.TOOLS, Items.DIAMOND, Items.DIAMOND_BLOCK).save(recipeOutput);

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.DIAMOND_HAMMER.get()),
                        Ingredient.of(Items.NETHERITE_BLOCK),
                        RecipeCategory.TOOLS, ModItems.NETHERITE_HAMMER.get()
                ).unlocks(getHasName(Items.NETHERITE_BLOCK), has(Items.NETHERITE_BLOCK))
                .save(recipeOutput, Hammering.resource("smithing_netherite_hammer"));

        simpleHammerSmeltBackToResourceRecipe(Items.IRON_INGOT, RecipeCategory.MISC, ModItems.IRON_HAMMER.get(), recipeOutput);
        simpleHammerSmeltBackToResourceRecipe(Items.GOLD_INGOT, RecipeCategory.MISC, ModItems.GOLDEN_HAMMER.get(), recipeOutput);
    }

    private void simpleHammerSmeltBackToResourceRecipe(Item output, RecipeCategory category, Item input, RecipeOutput recipeOutput) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), category, output, 0.8f, 13 * 20).unlockedBy(getHasName(input), has(input))
                .save(recipeOutput, Hammering.resource("smelt_" + getItemName(input) + "_back_to_resource"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), category, output, 0.8f, 13 * 20).unlockedBy(getHasName(input), has(input))
                .save(recipeOutput, Hammering.resource("blast_" + getItemName(input) + "_back_to_resource"));
    }

    private ShapedRecipeBuilder simpleHammerRecipe(Item output, RecipeCategory category, Item ingredient, Item ingredient2) {
        return ShapedRecipeBuilder.shaped(category, output)
                .pattern("XxX")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', ingredient2)
                .define('x', ingredient)
                .define('#', Items.STICK)
                .unlockedBy(getHasName(ingredient), has(ingredient));
    }
}
