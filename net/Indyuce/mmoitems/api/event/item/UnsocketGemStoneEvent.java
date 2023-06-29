/*    */ package net.Indyuce.mmoitems.api.event.item;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.event.PlayerDataEvent;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class UnsocketGemStoneEvent extends PlayerDataEvent {
/* 10 */   private static final HandlerList handlers = new HandlerList();
/*    */ 
/*    */ 
/*    */   
/*    */   private final VolatileMMOItem consumable;
/*    */ 
/*    */ 
/*    */   
/*    */   private final MMOItem targetItem;
/*    */ 
/*    */ 
/*    */   
/*    */   public UnsocketGemStoneEvent(PlayerData paramPlayerData, VolatileMMOItem paramVolatileMMOItem, MMOItem paramMMOItem) {
/* 23 */     super(paramPlayerData);
/*    */     
/* 25 */     this.consumable = paramVolatileMMOItem;
/* 26 */     this.targetItem = paramMMOItem;
/*    */   }
/*    */   
/*    */   public VolatileMMOItem getConsumable() {
/* 30 */     return this.consumable;
/*    */   }
/*    */   
/*    */   public MMOItem getTargetItem() {
/* 34 */     return this.targetItem;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 38 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 42 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\item\UnsocketGemStoneEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */