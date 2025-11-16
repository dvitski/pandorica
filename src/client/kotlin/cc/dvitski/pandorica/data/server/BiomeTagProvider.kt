package cc.dvitski.pandorica.data.server

import cc.dvitski.pandorica.tag.PandoricaBiomeTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import java.util.concurrent.CompletableFuture

class BiomeTagProvider(output: FabricDataOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricTagProvider<Biome>(output, Registries.BIOME, future) {
    override fun addTags(provider: HolderLookup.Provider) {
        builder(PandoricaBiomeTags.SPAWNS_LIQUEFIED_SKELETON)
            .add(Biomes.NETHER_WASTES)
            .add(Biomes.CRIMSON_FOREST)
    }
}
