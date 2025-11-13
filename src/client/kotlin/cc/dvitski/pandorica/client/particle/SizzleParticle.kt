package cc.dvitski.pandorica.client.particle

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.BaseAshSmokeParticle
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.util.RandomSource

class SizzleParticle(
    level: ClientLevel,
    x: Double, y: Double, z: Double,
    velocityX: Double, velocityY: Double, velocityZ: Double,
    scale: Float, spriteSet: SpriteSet
) : BaseAshSmokeParticle(level, x, y, z, 0.1f, 0.1f, 0.1f, velocityX, velocityY, velocityZ, scale, spriteSet, 0.8f, 60, 0.006f, true) {
    override fun getQuadSize(tickDelta: Float): Float {
        val delta = (age.toFloat() + tickDelta) / (lifetime / 2.0f)
        return super.getQuadSize(tickDelta) * (1.0f - delta) * 1.4f
    }

    class Provider(val spriteSet: SpriteSet) : ParticleProvider<SimpleParticleType> {
        override fun createParticle(particleOptions: SimpleParticleType, level: ClientLevel, x: Double, y: Double, z: Double, velocityX: Double, velocityY: Double, velocityZ: Double, random: RandomSource): Particle {
            return SizzleParticle(level, x, y, z, velocityX, velocityY, velocityZ, 1.0f, spriteSet)
        }
    }
}
