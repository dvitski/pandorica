package cc.dvitski.pandorica.data

import cc.dvitski.pandorica.data.client.LanguageProvider
import cc.dvitski.pandorica.data.client.ModelProvider
import cc.dvitski.pandorica.data.server.BlockLootTableProvider
import cc.dvitski.pandorica.data.server.BlockTagProvider
import cc.dvitski.pandorica.data.server.DynamicRegistryProvider
import cc.dvitski.pandorica.data.server.EnchantmentProvider
import cc.dvitski.pandorica.data.server.EnchantmentTagProvider
import cc.dvitski.pandorica.data.server.ItemTagProvider
import cc.dvitski.pandorica.data.server.RecipeProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.core.RegistrySetBuilder

object PandoricaDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()

        pack.addProvider(::EnchantmentProvider)
        pack.addProvider(::ItemTagProvider)
        pack.addProvider(::BlockTagProvider)
        pack.addProvider(::EnchantmentTagProvider)
        pack.addProvider(::BlockLootTableProvider)
        pack.addProvider(::LanguageProvider)
        pack.addProvider(::ModelProvider)
        pack.addProvider(::DynamicRegistryProvider)
        pack.addProvider(::RecipeProvider)
	}

    override fun buildRegistry(builder: RegistrySetBuilder) {
        DynamicRegistryProvider.buildRegistry(builder)
    }
}
