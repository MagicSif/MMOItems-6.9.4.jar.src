/*    */ package net.Indyuce.mmoitems.comp.enchants;
/*    */ 
/*    */ import io.lumine.mythicenchants.MythicEnchants;
/*    */ import io.lumine.mythicenchants.enchants.MythicEnchant;
/*    */ import io.lumine.mythicenchants.util.LoreParser;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.inventory.ItemFlag;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class MythicEnchantsSupport
/*    */   implements EnchantPlugin<MythicEnchant> {
/*    */   public boolean isCustomEnchant(Enchantment paramEnchantment) {
/* 16 */     return paramEnchantment instanceof MythicEnchant;
/*    */   }
/*    */ 
/*    */   
/*    */   public NamespacedKey getNamespacedKey(String paramString) {
/* 21 */     return new NamespacedKey((Plugin)MythicEnchants.inst(), paramString);
/*    */   }
/*    */   
/*    */   public void handleEnchant(ItemStackBuilder paramItemStackBuilder, MythicEnchant paramMythicEnchant, int paramInt) {
/* 25 */     Validate.isTrue((paramInt > 0), "Level must be strictly positive");
/*    */ 
/*    */ 
/*    */     
/* 29 */     if (!paramItemStackBuilder.getMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS))
/* 30 */       paramItemStackBuilder.getLore().insert(0, LoreParser.formatEnchantment(paramMythicEnchant, paramInt)); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\enchants\MythicEnchantsSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */