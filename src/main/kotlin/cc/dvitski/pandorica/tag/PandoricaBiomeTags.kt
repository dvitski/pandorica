package cc.dvitski.pandorica.tag

import cc.dvitski.pandorica.Pandorica
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome

object PandoricaBiomeTags {
    val SPAWNS_LIQUEFIED_SKELETON = create("spawns/liquefied_skeleton")

    private fun create(id: String): TagKey<Biome> {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id))
    }
}
