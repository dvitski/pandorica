package cc.dvitski.pandorica.data.server

import cc.dvitski.pandorica.block.MagmaTongueBlock
import cc.dvitski.pandorica.block.PandoricaBlocks
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import java.util.concurrent.CompletableFuture
import java.util.stream.IntStream

class BlockLootTableProvider(output: FabricDataOutput, lookup: CompletableFuture<HolderLookup.Provider>) : FabricBlockLootTableProvider(output, lookup) {
    override fun generate() {
        add(PandoricaBlocks.MAGMA_TONGUE, ::createMagmaTongue)
    }

    fun createMagmaTongue(block: Block): LootTable.Builder {
        return LootTable.lootTable()
            .withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f))
                    .add(
                        applyExplosionDecay(
                            block,
                            LootItem.lootTableItem(block)
                                .apply(
                                    IntStream.rangeClosed(1, MagmaTongueBlock.TONGUES.max).boxed().toList()
                                ) { integer ->
                                    SetItemCountFunction.setCount(ConstantValue.exactly(integer.toFloat()))
                                        .`when`(
                                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(
                                                    StatePropertiesPredicate.Builder.properties().hasProperty(
                                                        MagmaTongueBlock.TONGUES,
                                                        integer
                                                    )
                                                )
                                        )
                                }
                        )
                    )
            )
    }
}
