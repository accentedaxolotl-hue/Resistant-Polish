package net.itwaskairo.resistantpolish.items;

import net.itwaskairo.resistantpolish.ResistantPolish;
import net.itwaskairo.resistantpolish.items.custom.consumable.LeftoverResistantPolishItem;
import net.itwaskairo.resistantpolish.items.custom.consumable.ResistantPolishItem;
import net.itwaskairo.resistantpolish.items.custom.consumable.SlightlyResistantPolishItem;
import net.itwaskairo.resistantpolish.items.custom.consumable.VeryResistantPolishItem;
import net.itwaskairo.resistantpolish.items.custom.nonconsumable.PolishOMeterItem;
import net.itwaskairo.resistantpolish.items.custom.nonconsumable.PolishORemoverItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item>
            ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ResistantPolish.MODID);

    public static final RegistryObject<Item>
            resistantPolish = ITEMS.register("resistantpolish",
            () -> new ResistantPolishItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item>
            slightlyResistantPolish = ITEMS.register("slightlyresistantpolish",
            () -> new SlightlyResistantPolishItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item>
            veryResistantPolish = ITEMS.register("veryresistantpolish",
            () -> new VeryResistantPolishItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item>
            leftoverResistantPolish = ITEMS.register("leftoverresistantpolish",
            () -> new LeftoverResistantPolishItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>
            polishOMeter = ITEMS.register("polishometer",
            () -> new PolishOMeterItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>
            polishORemover = ITEMS.register("polishoremover",
            () -> new PolishORemoverItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }
}