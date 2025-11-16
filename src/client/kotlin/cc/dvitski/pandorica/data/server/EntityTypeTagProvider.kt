package cc.dvitski.pandorica.data.server

import cc.dvitski.pandorica.entity.PandoricaEntityTypes
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.EntityTypeTags
import java.util.concurrent.CompletableFuture

class EntityTypeTagProvider(output: FabricDataOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricTagProvider.EntityTypeTagProvider(output, future) {
    override fun addTags(lookup: HolderLookup.Provider) {
        valueLookupBuilder(EntityTypeTags.SKELETONS)
            .add(PandoricaEntityTypes.LIQUFIED_SKELETON)
    }
}
