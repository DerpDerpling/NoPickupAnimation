package derp.npa.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
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
    @Mutable
    @Final
    @Shadow
    private final ItemRenderer itemRenderer;

    public RemovePickupAnimationMixin(MinecraftClient client, ItemRenderer itemRenderer) {
        this.client = client;
        this.itemRenderer = itemRenderer;
    }

    @Inject(method = "renderHotbarItem", at = @At("HEAD"), cancellable = true)
    private void renderHotbarItemMixin(MatrixStack matrixStack, int i, int j, float f, PlayerEntity playerEntity, ItemStack itemStack, int k, CallbackInfo ci) {
        if (!itemStack.isEmpty()) {
            this.itemRenderer.renderInGuiWithOverrides(matrixStack, playerEntity, itemStack, i, j, k);
            this.itemRenderer.renderGuiItemOverlay(matrixStack, this.client.textRenderer, itemStack, i, j);
            ci.cancel();
        }
    }
}
