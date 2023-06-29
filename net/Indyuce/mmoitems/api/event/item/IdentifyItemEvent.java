/*    */ package net.Indyuce.mmoitems.api.event.item;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.api.event.PlayerDataEvent;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class IdentifyItemEvent extends PlayerDataEvent {
/* 10 */   private static final HandlerList handlers = new HandlerList();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final VolatileMMOItem consumable;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final NBTItem unidentified;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IdentifyItemEvent(PlayerData paramPlayerData, VolatileMMOItem paramVolatileMMOItem, NBTItem paramNBTItem) {
/* 26 */     super(paramPlayerData);
/*    */     
/* 28 */     this.consumable = paramVolatileMMOItem;
/* 29 */     this.unidentified = paramNBTItem;
/*    */   }
/*    */   
/*    */   public VolatileMMOItem getConsumable() {
/* 33 */     return this.consumable;
/*    */   }
/*    */   
/*    */   public NBTItem getUnidentifiedItem() {
/* 37 */     return this.unidentified;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 41 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 45 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\item\IdentifyItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */