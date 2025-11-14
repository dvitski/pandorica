package cc.dvitski.pandorica.data.client

import cc.dvitski.pandorica.Pandorica
import cc.dvitski.pandorica.block.PandoricaBlocks
import cc.dvitski.pandorica.item.PandoricaItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class LanguageProvider(output: FabricDataOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricLanguageProvider(output, future) {
    override fun generateTranslations(provider: HolderLookup.Provider, builder: TranslationBuilder) {
        builder.add("itemGroup.${Pandorica.MOD_ID}.item_group", Pandorica.MOD_NAME)

        builder.add(PandoricaItems.BASALT_DUST, "Basalt Dust")
        builder.add(PandoricaItems.CRUSTED_MAGMA, "Crusted Magma")
        builder.add(PandoricaItems.LIQUEFIED_BONE, "Liquefied Bone")
        builder.add(PandoricaItems.WITHERED_BONE, "Withered Bone")
        builder.add(PandoricaItems.WITHERMEAL, "Withermeal")
        builder.add(PandoricaBlocks.MAGMA_TONGUE, "Magma Tongue")
        builder.add(PandoricaBlocks.CRUMBLED_BASALT, "Crumbled Basalt")
    }
}
