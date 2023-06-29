/*    */ package net.Indyuce.mmoitems.listener.reforging;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeEvent;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.random.UpdatableRandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
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
/*    */ 
/*    */ public class RFGKeepRNG
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onReforge(MMOItemReforgeEvent paramMMOItemReforgeEvent) {
/* 37 */     if (paramMMOItemReforgeEvent.getOptions().shouldReRoll()) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 44 */     paramMMOItemReforgeEvent.getOldMMOItem().getStats()
/* 45 */       .forEach(paramItemStat -> {
/*    */           RandomStatData randomStatData = (RandomStatData)paramMMOItemReforgeEvent.getReforger().getTemplate().getBaseItemData().get(paramItemStat);
/*    */           if (randomStatData == null || !(randomStatData instanceof UpdatableRandomStatData))
/*    */             return; 
/*    */           StatHistory statHistory1 = StatHistory.from((MMOItem)paramMMOItemReforgeEvent.getOldMMOItem(), paramItemStat);
/*    */           StatData statData = ((UpdatableRandomStatData)randomStatData).reroll(paramItemStat, statHistory1.getOriginalData(), paramMMOItemReforgeEvent.getReforger().getGenerationItemLevel());
/*    */           if (statData == null)
/*    */             return; 
/*    */           StatHistory statHistory2 = StatHistory.from(paramMMOItemReforgeEvent.getNewMMOItem(), paramItemStat);
/*    */           statHistory2.setOriginalData(statData);
/*    */         });
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\reforging\RFGKeepRNG.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */