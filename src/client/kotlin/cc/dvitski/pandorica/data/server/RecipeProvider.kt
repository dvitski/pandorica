package cc.dvitski.pandorica.data.server

import cc.dvitski.pandorica.block.PandoricaBlocks
import cc.dvitski.pandorica.item.PandoricaItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import java.util.concurrent.CompletableFuture

class RecipeProvider(output: FabricDataOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(output, future) {
    override fun createRecipeProvider(lookup: HolderLookup.Provider, exporter: RecipeOutput): RecipeProvider {
        return object : RecipeProvider(lookup, exporter) {
            override fun buildRecipes() {
                shapeless(RecipeCategory.MISC, PandoricaItems.WITHERMEAL, 3)
                    .requires(PandoricaItems.WITHERED_BONE)
                    .group("bonemeal")
                    .unlockedBy("has_withered_bone", has(PandoricaItems.WITHERED_BONE))
                    .save(output)

                twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, PandoricaBlocks.CRUMBLED_BASALT, PandoricaItems.BASALT_DUST)
            }

        }
    }

    override fun getName(): String {
        return "Recipes"
    }
}
