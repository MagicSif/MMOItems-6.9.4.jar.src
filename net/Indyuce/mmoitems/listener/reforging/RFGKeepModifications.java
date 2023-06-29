/*    */ package net.Indyuce.mmoitems.listener.reforging;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeEvent;
/*    */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
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
/*    */ public class RFGKeepModifications
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onReforge(MMOItemReforgeEvent paramMMOItemReforgeEvent) {
/* 22 */     if (!paramMMOItemReforgeEvent.getOptions().shouldKeepMods()) {
/*    */       return;
/*    */     }
/*    */     
/* 26 */     for (StatHistory statHistory : paramMMOItemReforgeEvent.getNewMMOItem().getStatHistories())
/*    */     {
/*    */       
/* 29 */       statHistory.clearModifiersBonus();
/*    */     }
/*    */ 
/*    */     
/* 33 */     for (StatHistory statHistory1 : paramMMOItemReforgeEvent.getOldMMOItem().getStatHistories()) {
/*    */ 
/*    */       
/* 36 */       StatHistory statHistory2 = StatHistory.from(paramMMOItemReforgeEvent.getNewMMOItem(), statHistory1.getItemStat());
/*    */ 
/*    */       
/* 39 */       for (UUID uUID : statHistory1.getAllModifiers()) {
/*    */ 
/*    */         
/* 42 */         StatData statData = statHistory1.getModifiersBonus(uUID);
/*    */ 
/*    */         
/* 45 */         if (statData == null) {
/*    */           continue;
/*    */         }
/* 48 */         statHistory2.registerModifierBonus(uUID, ((Mergeable)statData).cloneData());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\reforging\RFGKeepModifications.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */