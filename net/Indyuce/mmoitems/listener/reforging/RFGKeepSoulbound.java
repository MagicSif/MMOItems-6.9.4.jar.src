/*    */ package net.Indyuce.mmoitems.listener.reforging;
/*    */ 
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeEvent;
/*    */ import net.Indyuce.mmoitems.stat.data.SoulboundData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RFGKeepSoulbound
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onReforge(MMOItemReforgeEvent paramMMOItemReforgeEvent) {
/* 19 */     SoulboundData soulboundData = (SoulboundData)paramMMOItemReforgeEvent.getOldMMOItem().getData(ItemStats.SOULBOUND);
/*    */ 
/*    */     
/* 22 */     if (soulboundData != null)
/*    */     {
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
/* 37 */       if (paramMMOItemReforgeEvent.getOptions().shouldKeepSoulBind())
/*    */       {
/*    */ 
/*    */         
/* 41 */         paramMMOItemReforgeEvent.getNewMMOItem().setData(ItemStats.SOULBOUND, (StatData)soulboundData);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\reforging\RFGKeepSoulbound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */