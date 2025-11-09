package cc.dvitski.pandorica.data.client

import cc.dvitski.pandorica.item.PandoricaItems
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.model.ModelTemplates

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockModelGenerators) {
    }

    override fun generateItemModels(generator: ItemModelGenerators) {
        generator.generateFlatItem(PandoricaItems.BASALT_DUST, ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(PandoricaItems.CRUSTED_MAGMA, ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(PandoricaItems.LIQUEFIED_BONE, ModelTemplates.FLAT_ITEM)
    }
}
