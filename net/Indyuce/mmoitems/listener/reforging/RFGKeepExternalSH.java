/*    */ package net.Indyuce.mmoitems.listener.reforging;
/*    */ 
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeEvent;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RFGKeepExternalSH
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onReforge(MMOItemReforgeEvent paramMMOItemReforgeEvent) {
/* 21 */     if (!paramMMOItemReforgeEvent.getOptions().shouldKeepExternalSH()) {
/*    */       return;
/*    */     }
/*    */     
/* 25 */     for (StatHistory statHistory1 : paramMMOItemReforgeEvent.getOldMMOItem().getStatHistories()) {
/*    */ 
/*    */       
/* 28 */       if (statHistory1.getItemStat() == ItemStats.ENCHANTS) {
/*    */         continue;
/*    */       }
/* 31 */       StatHistory statHistory2 = StatHistory.from(paramMMOItemReforgeEvent.getNewMMOItem(), statHistory1.getItemStat());
/*    */ 
/*    */       
/* 34 */       for (StatData statData : statHistory1.getExternalData())
/*    */       {
/*    */         
/* 37 */         statHistory2.registerExternalData(statData);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\reforging\RFGKeepExternalSH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */