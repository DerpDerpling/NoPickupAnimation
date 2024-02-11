package derp.npa.mixin;

import net.minecraft.client.particle.ItemPickupParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemPickupParticle.class)
public class ItemPickupParticleMixin extends Particle {

    protected ItemPickupParticleMixin(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);
    }
    @Overwrite
    public void tick() {
            this.markDead();
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        //nothing is needed here lol
    }

    @Shadow
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.CUSTOM;
    }
}

