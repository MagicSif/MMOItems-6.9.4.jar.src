/*    */ package net.Indyuce.mmoitems.listener.reforging;
/*    */ 
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeEvent;
/*    */ import net.Indyuce.mmoitems.stat.type.NameData;
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
/*    */ 
/*    */ 
/*    */ public class RFGKeepName
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onReforge(MMOItemReforgeEvent paramMMOItemReforgeEvent) {
/*    */     NameData nameData;
/* 24 */     if (!paramMMOItemReforgeEvent.getOptions().shouldKeepName()) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 31 */     if (paramMMOItemReforgeEvent.getOldMMOItem().hasData(ItemStats.NAME)) {
/*    */ 
/*    */       
/* 34 */       NameData nameData1 = (NameData)paramMMOItemReforgeEvent.getOldMMOItem().getData(ItemStats.NAME);
/*    */ 
/*    */       
/* 37 */       nameData = new NameData(nameData1.getMainName());
/*    */     
/*    */     }
/* 40 */     else if (paramMMOItemReforgeEvent.getReforger().getStack().getItemMeta().hasDisplayName()) {
/*    */ 
/*    */       
/* 43 */       nameData = new NameData(paramMMOItemReforgeEvent.getReforger().getStack().getItemMeta().getDisplayName());
/*    */     } else {
/*    */       return;
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 53 */     StatHistory statHistory = StatHistory.from(paramMMOItemReforgeEvent.getNewMMOItem(), ItemStats.NAME);
/*    */ 
/*    */     
/* 56 */     ((NameData)statHistory.getOriginalData()).setString(nameData.getMainName());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\reforging\RFGKeepName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */