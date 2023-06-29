/*    */ package net.Indyuce.mmoitems.api.event;
/*    */ 
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class ItemBuildEvent
/*    */   extends Event
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private ItemStack itemStack;
/*    */   
/*    */   public ItemBuildEvent(ItemStack paramItemStack) {
/* 18 */     this.itemStack = paramItemStack;
/*    */   }
/*    */   
/*    */   public ItemStack getItemStack() {
/* 22 */     return this.itemStack;
/*    */   }
/*    */   
/*    */   public ItemBuildEvent setItemStack(ItemStack paramItemStack) {
/* 26 */     this.itemStack = paramItemStack;
/* 27 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public boolean isCancelled() {
/* 35 */     return (this.itemStack == null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public void setCancelled(boolean paramBoolean) {
/* 44 */     if (paramBoolean)
/* 45 */       this.itemStack = null; 
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 49 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 53 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\ItemBuildEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */