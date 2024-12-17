package github.jcsmecabricks.customcrops.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import github.jcsmecabricks.customcrops.CustomCrops;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PastryScreen extends HandledScreen<PastryScreenHandler> {
    private static final Identifier GUI_TEXTURE =
            Identifier.of(CustomCrops.MOD_ID, "textures/gui/pastry_station/pastry_station_gui.png");
    private static final Identifier ARROW_TEXTURE =
            Identifier.of(CustomCrops.MOD_ID, "textures/gui/pastry_station/arrow_progress.png");
    private static final Identifier CRYSTAL_TEXTURE =
            Identifier.of(CustomCrops.MOD_ID, "textures/gui/pastry_station/crystal_progress.png");

    public PastryScreen(PastryScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(RenderLayer::getGuiTextured, GUI_TEXTURE, x, y, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 256, 256);
        renderProgressArrow(context, x, y);
    }


    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(RenderLayer::getGuiTextured, ARROW_TEXTURE, x + 73, y + 35, 0, 0,
                    handler.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
