/*    */ package net.Indyuce.mmoitems.api.event.item;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.event.PlayerDataEvent;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.stat.data.UpgradeData;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class UpgradeItemEvent
/*    */   extends PlayerDataEvent {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */ 
/*    */ 
/*    */   
/*    */   private final VolatileMMOItem consumable;
/*    */ 
/*    */ 
/*    */   
/*    */   private final MMOItem target;
/*    */ 
/*    */ 
/*    */   
/*    */   private final UpgradeData consumableData;
/*    */ 
/*    */ 
/*    */   
/*    */   private final UpgradeData targetData;
/*    */ 
/*    */ 
/*    */   
/*    */   public UpgradeItemEvent(PlayerData paramPlayerData, VolatileMMOItem paramVolatileMMOItem, MMOItem paramMMOItem, UpgradeData paramUpgradeData1, UpgradeData paramUpgradeData2) {
/* 33 */     super(paramPlayerData);
/*    */     
/* 35 */     this.consumable = paramVolatileMMOItem;
/* 36 */     this.target = paramMMOItem;
/* 37 */     this.consumableData = paramUpgradeData1;
/* 38 */     this.targetData = paramUpgradeData2;
/*    */   }
/*    */   
/*    */   public VolatileMMOItem getConsumable() {
/* 42 */     return this.consumable;
/*    */   }
/*    */   
/*    */   public MMOItem getTargetItem() {
/* 46 */     return this.target;
/*    */   }
/*    */   
/*    */   public UpgradeData getConsumableData() {
/* 50 */     return this.consumableData;
/*    */   }
/*    */   
/*    */   public UpgradeData getTargetData() {
/* 54 */     return this.targetData;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 58 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 62 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\item\UpgradeItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */