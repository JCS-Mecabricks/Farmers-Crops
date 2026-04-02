package github.jcsmecabricks.customcrops.screen.custom;

import github.jcsmecabricks.customcrops.CustomCrops;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class PastryScreen extends AbstractContainerScreen<PastryScreenHandler> {
    private static final Identifier GUI_TEXTURE =
            Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, "textures/gui/pastry_station/pastry_station_gui.png");
    private static final Identifier ARROW_TEXTURE =
            Identifier.fromNamespaceAndPath(CustomCrops.MOD_ID, "textures/gui/pastry_station/arrow_progress.png");
    private static final Identifier FIRE_TEXTURE =
            Identifier.parse("textures/gui/sprites/container/furnace/lit_progress.png");

    public PastryScreen(PastryScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelY = 1000;
        inventoryLabelY = 1000;
    }

    private void renderProgressFire(GuiGraphicsExtractor context, int x, int y) {
        if(menu.isCrafting()) {
            context.blit(RenderPipelines.GUI_TEXTURED, FIRE_TEXTURE,x + 107, y + 13 + 16 - menu.getScaledBurnProgress(), 0,
                    16 - menu.getScaledBurnProgress(), 16, menu.getScaledBurnProgress(),16, 16);
        }
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor context, int mouseX, int mouseY, float a) {
        super.extractBackground(context, mouseX, mouseY, a);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        context.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        renderProgressFire(context, x, y);
        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(GuiGraphicsExtractor context, int x, int y) {
        if(menu.isCrafting()) {
            context.blit(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 73, y + 35, 0, 0,
                    menu.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        extractTooltip(graphics, mouseX, mouseY);
    }
}