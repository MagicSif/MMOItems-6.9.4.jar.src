/*    */ package net.Indyuce.mmoitems.listener.reforging;
/*    */ 
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeEvent;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*    */ import net.Indyuce.mmoitems.stat.data.BooleanData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RFGKeepDurability
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onReforge(MMOItemReforgeEvent paramMMOItemReforgeEvent) {
/* 22 */     MMOItem mMOItem = paramMMOItemReforgeEvent.getNewMMOItem(); LiveMMOItem liveMMOItem = paramMMOItemReforgeEvent.getOldMMOItem();
/*    */ 
/*    */     
/* 25 */     if (paramMMOItemReforgeEvent.getNewMMOItem().hasData(ItemStats.UNBREAKABLE) && ((BooleanData)paramMMOItemReforgeEvent.getNewMMOItem().getData(ItemStats.UNBREAKABLE)).isEnabled()) {
/*    */       return;
/*    */     }
/*    */     
/* 29 */     StatData statData1 = liveMMOItem.getData(ItemStats.CUSTOM_DURABILITY);
/* 30 */     if (statData1 != null) {
/* 31 */       mMOItem.setData(ItemStats.CUSTOM_DURABILITY, statData1);
/*    */     }
/*    */     
/* 34 */     StatData statData2 = liveMMOItem.getData(ItemStats.ITEM_DAMAGE);
/* 35 */     if (statData2 != null)
/* 36 */       mMOItem.setData(ItemStats.ITEM_DAMAGE, statData2); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\reforging\RFGKeepDurability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */