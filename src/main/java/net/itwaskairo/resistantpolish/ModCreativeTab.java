package net.itwaskairo.resistantpolish;

import net.itwaskairo.resistantpolish.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ResistantPolish.MODID);

    public static final RegistryObject<CreativeModeTab>
            resistantPolishTab = TABS.register("customitemstab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("title.resistantpolish.customitemstab"))
                    .icon(() -> new ItemStack(ModItems.resistantPolish.get()))
                    .displayItems((parameters, output) -> {

                        output.accept(ModItems.resistantPolish.get());
                        output.accept(ModItems.slightlyResistantPolish.get());
                        output.accept(ModItems.veryResistantPolish.get());

                        output.accept(ModItems.polishOMeter.get());
                        output.accept(ModItems.polishORemover.get());

                    })
                    .build()
    );
}