/*    */ package net.Indyuce.mmoitems.api.event;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class CraftMMOItemEvent
/*    */   extends PlayerDataEvent
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private ItemStack result;
/*    */   
/*    */   public CraftMMOItemEvent(PlayerData paramPlayerData, ItemStack paramItemStack) {
/* 17 */     super(paramPlayerData);
/*    */     
/* 19 */     this.result = paramItemStack;
/*    */   }
/*    */   
/*    */   public ItemStack getResult() {
/* 23 */     return this.result;
/*    */   }
/*    */   
/*    */   public void setResult(ItemStack paramItemStack) {
/* 27 */     this.result = paramItemStack;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 31 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 35 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\CraftMMOItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */