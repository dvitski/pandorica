package cc.dvitski.pandorica.data.server

import cc.dvitski.pandorica.block.PandoricaBlocks
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import java.util.concurrent.CompletableFuture

class BlockTagProvider(output: FabricDataOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricTagProvider.BlockTagProvider(output, future) {
    override fun addTags(provider: HolderLookup.Provider) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
            .add(PandoricaBlocks.CRUMBLED_BASALT)
    }
}
