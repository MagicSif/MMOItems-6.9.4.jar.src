/*    */ package net.Indyuce.mmoitems.comp.enchants.advanced_enchants;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeEvent;
/*    */ import net.Indyuce.mmoitems.comp.enchants.DisableAdvancedEnchantments;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import net.advancedplugins.ae.api.EnchantApplyEvent;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ public class AdvancedEnchantmentsHook implements Listener {
/* 14 */   public static final ItemStat ADVANCED_ENCHANTMENTS = new AdvancedEnchantsStat();
/* 15 */   public static final ItemStat DISABLE_ADVANCED_ENCHANTMENTS = (ItemStat)new DisableAdvancedEnchantments();
/*    */   
/*    */   @EventHandler
/*    */   public void onEnchantApply(EnchantApplyEvent paramEnchantApplyEvent) {
/* 19 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramEnchantApplyEvent.getItem());
/* 20 */     if (nBTItem.getBoolean("MMOITEMS_DISABLE_ADVANCED_ENCHANTS"))
/* 21 */       paramEnchantApplyEvent.setCancelled(true); 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onReforge(MMOItemReforgeEvent paramMMOItemReforgeEvent) {
/* 26 */     if (!paramMMOItemReforgeEvent.getOptions().shouldKeepAdvancedEnchants()) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 32 */     StatData statData = paramMMOItemReforgeEvent.getOldMMOItem().getData(ADVANCED_ENCHANTMENTS);
/*    */ 
/*    */     
/* 35 */     if (statData == null) {
/*    */       return;
/*    */     }
/*    */     
/* 39 */     paramMMOItemReforgeEvent.getNewMMOItem().setData(ADVANCED_ENCHANTMENTS, statData);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\enchants\advanced_enchants\AdvancedEnchantmentsHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */