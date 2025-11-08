package cc.dvitski.pandorica.data

import cc.dvitski.pandorica.data.client.LanguageProvider
import cc.dvitski.pandorica.data.server.EnchantmentProvider
import cc.dvitski.pandorica.data.server.EnchantmentTagProvider
import cc.dvitski.pandorica.data.server.ItemTagProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object PandoricaDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()

        pack.addProvider(::EnchantmentProvider)

        pack.addProvider(::ItemTagProvider)
        pack.addProvider(::EnchantmentTagProvider)

        pack.addProvider(::LanguageProvider)
	}
}
