package cc.dvitski.pandorica.world.gen

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.levelgen.GenerationStep

object PandoricaWorldGen {
    fun initialize() {
        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(Biomes.CRIMSON_FOREST),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            PandoricaPlacedFeatures.PATCH_MAGMA_TONGUE
        )

        BiomeModifications.addFeature(
            BiomeSelectors.foundInTheNether(),
            GenerationStep.Decoration.UNDERGROUND_DECORATION,
            PandoricaPlacedFeatures.ORE_CRUMBLED_BASALT
        )
    }
}
