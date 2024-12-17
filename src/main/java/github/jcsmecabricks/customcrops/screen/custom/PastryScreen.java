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
        // Set shader color for rendering the GUI
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        // Calculate the x and y positions for centering the GUI
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        // Draw the main GUI texture
        context.drawGuiTexture(
                RenderLayer.getGuiTextured(GUI_TEXTURE), // RenderLayer for GUI textures
                GUI_TEXTURE,                            // Identifier of the texture
                x,                                      // X position on the screen
                y,                                      // Y position on the screen
                0,                                      // U coordinate in the texture
                0,                                      // V coordinate in the texture
                backgroundWidth,                        // Width of the texture to render
                backgroundHeight                        // Height of the texture to render
        );

        // Render progress indicators
        renderProgressArrow(context, x, y);
        renderProgressCrystal(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawGuiTexture(
                    RenderLayer.getGuiTextured(ARROW_TEXTURE),
                    ARROW_TEXTURE,
                    x + 73,                 // Position X
                    y + 35,                 // Position Y
                    0,                      // U coordinate in the texture
                    0,                      // V coordinate in the texture
                    handler.getScaledArrowProgress(), // Width of the progress bar
                    16                      // Height of the progress bar
            );
        }
    }

    private void renderProgressCrystal(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            int crystalProgressHeight = handler.getScaledCrystalProgress();
            context.drawGuiTexture(
                    RenderLayer.getGuiTextured(CRYSTAL_TEXTURE),
                    CRYSTAL_TEXTURE,
                    x + 104,                               // Position X
                    y + 13 + 16 - crystalProgressHeight,  // Position Y
                    0,                                    // U coordinate in the texture
                    16 - crystalProgressHeight,           // V coordinate in the texture
                    16,                                   // Width
                    crystalProgressHeight                 // Height
            );
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
