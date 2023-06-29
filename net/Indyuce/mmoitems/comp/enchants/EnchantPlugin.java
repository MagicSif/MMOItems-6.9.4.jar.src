package net.Indyuce.mmoitems.comp.enchants;

import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public interface EnchantPlugin<T extends Enchantment> {
  boolean isCustomEnchant(Enchantment paramEnchantment);
  
  void handleEnchant(ItemStackBuilder paramItemStackBuilder, T paramT, int paramInt);
  
  NamespacedKey getNamespacedKey(String paramString);
}


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\enchants\EnchantPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */