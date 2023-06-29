/*    */ package net.Indyuce.mmoitems.listener.reforging;
/*    */ 
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeEvent;
/*    */ import net.Indyuce.mmoitems.stat.data.UpgradeData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RFGKeepUpgrades
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onReforge(MMOItemReforgeEvent paramMMOItemReforgeEvent) {
/* 19 */     UpgradeData upgradeData1 = (UpgradeData)paramMMOItemReforgeEvent.getOldMMOItem().getData(ItemStats.UPGRADE);
/* 20 */     UpgradeData upgradeData2 = (UpgradeData)paramMMOItemReforgeEvent.getNewMMOItem().getData(ItemStats.UPGRADE);
/*    */     
/* 22 */     if (!paramMMOItemReforgeEvent.getOptions().shouldKeepUpgrades() || upgradeData1 == null || upgradeData2 == null || upgradeData2
/*    */ 
/*    */       
/* 25 */       .getMaxUpgrades() <= 0) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 31 */     UpgradeData upgradeData3 = new UpgradeData(upgradeData2.getReference(), upgradeData2.getTemplateName(), upgradeData2.isWorkbench(), upgradeData2.isDestroy(), upgradeData2.getMax(), upgradeData2.getMin(), upgradeData2.getSuccess());
/*    */ 
/*    */     
/* 34 */     upgradeData3.setLevel(Math.min(upgradeData1.getLevel(), upgradeData2.getMaxUpgrades()));
/*    */ 
/*    */ 
/*    */     
/* 38 */     paramMMOItemReforgeEvent.getNewMMOItem().setData(ItemStats.UPGRADE, (StatData)upgradeData3);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\reforging\RFGKeepUpgrades.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */