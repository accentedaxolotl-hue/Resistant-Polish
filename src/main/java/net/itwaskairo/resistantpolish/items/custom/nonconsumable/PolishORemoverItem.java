package net.itwaskairo.resistantpolish.items.custom.nonconsumable;

import net.itwaskairo.resistantpolish.events.data.ResistantPolishValueData;
import net.itwaskairo.resistantpolish.items.ModItems;
import net.itwaskairo.resistantpolish.utility.inventory.InventoryHelper;
import net.itwaskairo.resistantpolish.utility.tooltip.TooltipHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class PolishORemoverItem extends Item {
    public PolishORemoverItem(Properties properties) { super(properties); }

    @Override
    public void appendHoverText(
            ItemStack stack,
            @Nullable Level level,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        TooltipHelper.addTooltip(stack, level, tooltip, flag);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();


        if (player == null) {
            if (player.level() instanceof ServerLevel serverLevel) {

                float soundPitch = 1.0F + (float) Math.random() * 0.4F;

                serverLevel.playSound(
                        null,
                        player.blockPosition(),
                        SoundEvents.VILLAGER_NO,
                        SoundSource.PLAYERS,
                        1.3F,
                        soundPitch
                );
            }
            return InteractionResult.PASS;
        }

        if (!level.isClientSide) {
            float polishValue = ResistantPolishValueData.get(level)
                    .getResistance(context.getClickedPos());



            if (polishValue <= 0) { return InteractionResult.PASS; }

            context.getItemInHand().getOrCreateTag()
                    .putFloat("PolishRemoverValue", polishValue);

            player.sendSystemMessage(
                    Component.translatable(
                            "message.resistantpolish.removed_message", polishValue
                    ));

            ItemStack leftover = new ItemStack(ModItems.leftoverResistantPolish.get());

            leftover.getOrCreateTag().putFloat(
                    "StoredPolishValue",
                    polishValue
            );

            InventoryHelper.getOrDrop(player, leftover);

            ResistantPolishValueData.get(level)
                    .remove(context.getClickedPos());
        }

        if (player.level() instanceof ServerLevel serverLevel) {

            float soundPitch = 1.0F + (float)Math.random() * 0.4F;

            serverLevel.playSound(
                    null,
                    player.blockPosition(),
                    SoundEvents.SALMON_FLOP,
                    SoundSource.PLAYERS,
                    1.3F,
                    soundPitch
            );
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}