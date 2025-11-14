package cc.dvitski.pandorica.data.server

import cc.dvitski.pandorica.Pandorica
import cc.dvitski.pandorica.world.gen.PandoricaConfiguredFeatures
import cc.dvitski.pandorica.world.gen.PandoricaPlacedFeatures
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import java.util.concurrent.CompletableFuture

class DynamicRegistryProvider(output: FabricDataOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricDynamicRegistryProvider(output, future) {
    override fun configure(registries: HolderLookup.Provider, entries: Entries) {
        addAll(entries, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE))
        addAll(entries, registries.lookupOrThrow(Registries.PLACED_FEATURE))
    }

    fun <T> addAll(entries: Entries, registry: HolderLookup.RegistryLookup<T>) {
        registry
            .listElementIds()
            .filter { it.location().namespace == Pandorica.MOD_ID }
            .forEach { entries.add(registry, it) }
    }

    override fun getName(): String {
        return "Dynamic Registries"
    }

    companion object {
        fun buildRegistry(builder: RegistrySetBuilder) {
            builder.add(Registries.CONFIGURED_FEATURE, PandoricaConfiguredFeatures::bootstrap)
            builder.add(Registries.PLACED_FEATURE, PandoricaPlacedFeatures::bootstrap)
        }
    }
}
