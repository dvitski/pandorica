package cc.dvitski.pandorica.item

import cc.dvitski.pandorica.world.gen.PandoricaPlacedFeatures
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.registries.Registries
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.LevelEvent
import net.minecraft.world.phys.Vec3

class WithermealItem(properties: Properties) : Item(properties) {
    override fun useOn(context: UseOnContext): InteractionResult {
        val level = context.level
        val pos = context.clickedPos

        if (isValidTarget(level, pos)) {
            if (level is ServerLevel) {
                perform(level, pos, level.random)
            }

            context.itemInHand.shrink(1)
            return InteractionResult.SUCCESS
        }

        return InteractionResult.PASS
    }

    companion object {
        fun perform(level: ServerLevel, pos: BlockPos, random: RandomSource) {
            val passed = mutableSetOf<BlockPos>()
            val mutable = pos.mutable()
            val radius = 3
            val spread = 2
            for (x in -radius until radius) {
                for (y in -radius until radius) {
                    for (z in -radius until radius) {
                        mutable.setWithOffset(pos, x, y, z)
                        if (!passed.contains(mutable) && isValidTarget(level, mutable) && random.nextFloat() * radius >= mutable.distSqr(pos) / spread) {
                            passed.add(mutable.immutable())

                            val placedFeatures = level.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE)
                            val featureHolder = placedFeatures.getOrThrow(PandoricaPlacedFeatures.WITHERMEAL_DIRT)
                            val feature = featureHolder.value()
                            feature.place(level, level.chunkSource.generator, random, mutable)

                            val vec = Vec3.atBottomCenterOf(mutable.above())
                            level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, mutable, Block.getId(level.getBlockState(mutable)))
                            level.sendParticles(ParticleTypes.WHITE_ASH, vec.x, vec.y + 0.5, vec.z, 3, 0.2, 0.3, 0.2, 0.2)
                        }
                    }
                }
            }
        }

        fun isValidTarget(level: Level, pos: BlockPos): Boolean {
            val state = level.getBlockState(pos)
            val aboveState = level.getBlockState(pos.above())
            return state.`is`(BlockTags.DIRT) && aboveState.canBeReplaced()
        }
    }
}
