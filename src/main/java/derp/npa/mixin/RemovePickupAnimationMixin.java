package derp.npa.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class RemovePickupAnimationMixin {
    @Mutable
    @Final
    @Shadow
    private final MinecraftClient client;

    public RemovePickupAnimationMixin(MinecraftClient client) {
        this.client = client;
    }

    @Inject(method = "renderHotbarItem", at = @At("HEAD"), cancellable = true)
    private void renderHotbarItemMixin(int x, int y, float tickDelta, PlayerEntity player, ItemStack stack, int seed, CallbackInfo ci) {
        if (!stack.isEmpty()) {
            this.client.getItemRenderer().renderInGui(stack, x, y);
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            this.client.getItemRenderer().renderGuiItemOverlay(this.client.textRenderer, stack, x, y);
            ci.cancel();
        }
    }
}
