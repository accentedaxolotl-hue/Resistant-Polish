package net.itwaskairo.resistantpolish.utility.tooltip;

import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class TooltipHelper {
    @OnlyIn(Dist.CLIENT)
    public static void addTooltip(
            ItemStack stack,
            @Nullable Level level,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        if (net.minecraft.client.gui.screens.Screen.hasShiftDown()) {
            int line = 1;
            String itemId = stack.getItem()
                    .getDescriptionId()
                    .replace("item.resistantpolish.", "");

            String tooltipID = "tooltip.resistantpolish." + itemId + ".detail";

            while (Language.getInstance().has(tooltipID + ".line" + line))
            {
                tooltip.add(Component.translatable(
                        tooltipID + ".line" + line));
                line++;
            }
        }
        else {
            tooltip.add(Component.translatable(
                    "tooltip.resistantpolish.shift.held"
            ));
        }

        if (stack.hasTag() && stack.getTag().contains("StoredPolishValue")) {
            float value = stack.getTag().getFloat("StoredPolishValue");

            tooltip.add(
                    Component.translatable(
                            "tooltip.resistantpolish.removed_polish_value", value
                    ));
        }
        else if (stack.hasTag() && stack.getTag().contains("PolishValue")) {
            float value = stack.getTag().getFloat("PolishValue");

            tooltip.add(
                    Component.translatable(
                            "tooltip.resistantpolish.stored_polish_value", value
                    ));
        }
    }
}