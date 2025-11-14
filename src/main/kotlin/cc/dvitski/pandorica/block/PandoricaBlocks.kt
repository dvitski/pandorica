package cc.dvitski.pandorica.block

import cc.dvitski.pandorica.Pandorica
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.ColorRGBA
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.ColoredFallingBlock
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.WallBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor
import java.util.function.Function
import java.util.function.UnaryOperator

object PandoricaBlocks {
    val MAGMA_TONGUE = register("magma_tongue", ::MagmaTongueBlock) {
        it.mapColor(MapColor.TERRACOTTA_ORANGE)
            .lightLevel(MagmaTongueBlock::getLight)
            .sound(SoundType.NETHER_SPROUTS)
            .noOcclusion()
            .noCollision()
    }

    val CRUMBLED_BASALT = register("crumbled_basalt", { ColoredFallingBlock(ColorRGBA(0x52555B), it) }) {
        it.strength(0.5f)
            .sound(SoundType.SAND)
    }

    private fun register(id: String, factory: (BlockBehaviour.Properties) -> Block, properties: BlockBehaviour.Properties): Block {
        val location = ResourceLocation.fromNamespaceAndPath(Pandorica.MOD_ID, id)
        val key = ResourceKey.create(Registries.BLOCK, location)
        return Blocks.register(key, factory, properties)
    }

    /* Basic */

    private fun register(id: String, properties: UnaryOperator<BlockBehaviour.Properties>): Block {
        return register(id, properties.apply(BlockBehaviour.Properties.of()))
    }

    private fun register(id: String, properties: BlockBehaviour.Properties): Block {
        return register(id, ::Block, properties)
    }

    private fun register(id: String, factory: (BlockBehaviour.Properties) -> Block, properties: UnaryOperator<BlockBehaviour.Properties>): Block {
        return register(id, factory, properties.apply(BlockBehaviour.Properties.of()))
    }

    /* Parented Blocks */

    private fun registerParent(id: String, parent: Block, factory: (BlockBehaviour.Properties) -> Block): Block {
        return register(id, factory, BlockBehaviour.Properties.ofFullCopy(parent))
    }

    private fun registerParent(id: String, parent: Block, factory: Function<BlockBehaviour.Properties, Block>, properties: UnaryOperator<BlockBehaviour.Properties>): Block {
        return registerParent(id, parent) { factory.apply(properties.apply(it)) }
    }

    private fun registerWall(id: String, parent: Block): Block {
        return registerParent(id, parent, ::WallBlock)
    }

    private fun registerStairs(id: String, parent: Block): Block {
        return registerParent(id, parent) { StairBlock(parent.defaultBlockState(), it) }
    }

    private fun registerSlab(id: String, parent: Block): Block {
        return registerParent(id, parent, ::SlabBlock)
    }
}
