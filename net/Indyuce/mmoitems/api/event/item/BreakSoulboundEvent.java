/*    */ package net.Indyuce.mmoitems.api.event.item;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.api.event.PlayerDataEvent;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class BreakSoulboundEvent
/*    */   extends PlayerDataEvent {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final VolatileMMOItem consumable;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final NBTItem target;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BreakSoulboundEvent(PlayerData paramPlayerData, VolatileMMOItem paramVolatileMMOItem, NBTItem paramNBTItem) {
/* 27 */     super(paramPlayerData);
/*    */     
/* 29 */     this.consumable = paramVolatileMMOItem;
/* 30 */     this.target = paramNBTItem;
/*    */   }
/*    */   
/*    */   public VolatileMMOItem getConsumable() {
/* 34 */     return this.consumable;
/*    */   }
/*    */   
/*    */   public NBTItem getTargetItem() {
/* 38 */     return this.target;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 42 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 46 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\item\BreakSoulboundEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */