/*    */ package net.Indyuce.mmoitems.listener.reforging;
/*    */ 
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeEvent;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*    */ import net.Indyuce.mmoitems.stat.Enchants;
/*    */ import net.Indyuce.mmoitems.stat.data.EnchantListData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RFGKeepEnchantments
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onReforge(MMOItemReforgeEvent paramMMOItemReforgeEvent) {
/* 31 */     if (!paramMMOItemReforgeEvent.getOptions().shouldKeepEnchantments()) {
/*    */       return;
/*    */     }
/*    */     
/* 35 */     MMOItem mMOItem = paramMMOItemReforgeEvent.getOldMMOItem().clone();
/*    */ 
/*    */     
/* 38 */     if (!mMOItem.hasData(ItemStats.ENCHANTS))
/*    */     {
/*    */       
/* 41 */       mMOItem.setData(ItemStats.ENCHANTS, (StatData)new EnchantListData());
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 46 */     Enchants.separateEnchantments(mMOItem);
/*    */ 
/*    */     
/* 49 */     StatHistory statHistory1 = StatHistory.from(mMOItem, ItemStats.ENCHANTS);
/* 50 */     EnchantListData enchantListData = (EnchantListData)((Mergeable)statHistory1.getOriginalData()).cloneData();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 58 */     StatHistory statHistory2 = StatHistory.from(paramMMOItemReforgeEvent.getNewMMOItem(), ItemStats.ENCHANTS);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 63 */     for (StatData statData : statHistory1.getExternalData())
/*    */     {
/*    */ 
/*    */       
/* 67 */       statHistory2.registerExternalData(statData);
/*    */     }
/*    */ 
/*    */     
/* 71 */     enchantListData.identifyTrueOriginalEnchantments(paramMMOItemReforgeEvent.getNewMMOItem());
/*    */   }
/*    */ 
/*    */   
/*    */   void log(@NotNull EnchantListData paramEnchantListData, @NotNull String paramString) {
/* 76 */     MMOItems.print(null, "  §3> §7" + paramString + ":", null, new String[0]);
/* 77 */     for (Enchantment enchantment : paramEnchantListData.getEnchants()) MMOItems.print(null, "  §b * §7" + enchantment.getName() + " §f" + paramEnchantListData.getLevel(enchantment), null, new String[0]); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\reforging\RFGKeepEnchantments.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */