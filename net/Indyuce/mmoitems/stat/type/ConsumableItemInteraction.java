package net.Indyuce.mmoitems.stat.type;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.api.Type;
import net.Indyuce.mmoitems.api.interaction.Consumable;
import net.Indyuce.mmoitems.api.player.PlayerData;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ConsumableItemInteraction {
  boolean handleConsumableEffect(@NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull PlayerData paramPlayerData, @NotNull Consumable paramConsumable, @NotNull NBTItem paramNBTItem, @Nullable Type paramType);
}


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\ConsumableItemInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */