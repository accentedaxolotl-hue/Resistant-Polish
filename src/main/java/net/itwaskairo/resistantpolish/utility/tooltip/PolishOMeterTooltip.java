package net.itwaskairo.resistantpolish.utility.tooltip;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class PolishOMeterTooltip {
    @OnlyIn(Dist.CLIENT)
    public static void addTooltip(
            ItemStack stack,
            @Nullable Level level,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        if (stack.hasTag() && stack.getTag().contains("PolishValue")) {
            float value = stack.getTag().getFloat("PolishValue");

            tooltip.add(
                    Component.translatable(
                            "tooltip.resistantpolish.stored_polish_value", value
                    ));
                    tooltip.add(
                    Component.translatable(
                            "tooltip.resistantpolish.polishometer"
                    ));
        }
    }
}