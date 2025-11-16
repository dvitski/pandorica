package cc.dvitski.pandorica.world.gen

import cc.dvitski.pandorica.entity.PandoricaEntityTypes
import cc.dvitski.pandorica.tag.PandoricaBiomeTags
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.levelgen.GenerationStep

object PandoricaWorldGen {
    fun initialize() {
        initializeFeatures()
        initializeSpawns()
    }

    private fun initializeFeatures() {
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

    private fun initializeSpawns() {
        BiomeModifications.addSpawn(
            BiomeSelectors.tag(PandoricaBiomeTags.SPAWNS_LIQUEFIED_SKELETON),
            MobCategory.MONSTER,
            PandoricaEntityTypes.LIQUFIED_SKELETON,
            3,
            2,
            3
        )
    }
}
