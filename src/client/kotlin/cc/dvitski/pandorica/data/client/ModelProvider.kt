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
        generator.createTrivialCube(PandoricaBlocks.CRUMBLED_BASALT)
    }

    override fun generateItemModels(generator: ItemModelGenerators) {
        generator.generateFlatItem(PandoricaItems.BASALT_DUST, ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(PandoricaItems.CRUSTED_MAGMA, ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(PandoricaItems.LIQUEFIED_BONE, ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(PandoricaItems.WITHERED_BONE, ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(PandoricaItems.WITHERMEAL, ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(PandoricaItems.LIQUEFIED_SKELETON_SPAWN_EGG, ModelTemplates.FLAT_ITEM)
    }

    companion object {
        fun BlockModelGenerators.createMagmaTongue(block: Block) {
            registerSimpleFlatItemModel(block.asItem())

            blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block)
                    .with(
                        PropertyDispatch.initial(MagmaTongueBlock.TONGUES).also { dispatch ->
                            MagmaTongueBlock.TONGUES.possibleValues.forEach { i ->
                                val variant = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "$i"))
                                dispatch.select(i, variant)
                            }
                        }
                    )
            )
        }
    }
}
