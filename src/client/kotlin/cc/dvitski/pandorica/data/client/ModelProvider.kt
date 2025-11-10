package cc.dvitski.pandorica.data.client

import cc.dvitski.pandorica.block.MagmaTongueBlock
import cc.dvitski.pandorica.block.PandoricaBlocks
import cc.dvitski.pandorica.item.PandoricaItems
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator
import net.minecraft.client.data.models.blockstates.PropertyDispatch
import net.minecraft.client.data.models.model.ModelLocationUtils
import net.minecraft.client.data.models.model.ModelTemplates
import net.minecraft.world.level.block.Block

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockModelGenerators) {
        generator.createMagmaTongue(PandoricaBlocks.MAGMA_TONGUE)
    }

    override fun generateItemModels(generator: ItemModelGenerators) {
        generator.generateFlatItem(PandoricaItems.BASALT_DUST, ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(PandoricaItems.CRUSTED_MAGMA, ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(PandoricaItems.LIQUEFIED_BONE, ModelTemplates.FLAT_ITEM)
    }

    companion object {
        fun BlockModelGenerators.createMagmaTongue(block: Block) {
            registerSimpleFlatItemModel(block.asItem())

            blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block)
                    .with(
                        PropertyDispatch.initial(MagmaTongueBlock.TONGUES).also { dispatch ->
                            for (i in 0 until MagmaTongueBlock.TONGUES.max) {
                                val v = i + 1
                                val variant = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "$v"))
                                dispatch.select(v, variant)
                            }
                        }
                    )
            )
        }
    }
}
