/*    */ package net.Indyuce.mmoitems.api.event.item;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.interaction.util.DurabilityItem;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ItemCustomRepairEvent
/*    */   extends Event
/*    */   implements Cancellable
/*    */ {
/*    */   @NotNull
/*    */   private final DurabilityItem sourceItem;
/*    */   private final int durabilityIncrease;
/*    */   private boolean cancelled;
/* 18 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   public ItemCustomRepairEvent(@NotNull DurabilityItem paramDurabilityItem, int paramInt) {
/* 21 */     this.sourceItem = paramDurabilityItem;
/* 22 */     this.durabilityIncrease = paramInt;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasPlayer() {
/* 33 */     return (this.sourceItem.getPlayer() != null);
/*    */   }
/*    */   
/*    */   public Player getPlayer() {
/* 37 */     return this.sourceItem.getPlayer();
/*    */   }
/*    */   
/*    */   public int getDurabilityIncrease() {
/* 41 */     return this.durabilityIncrease;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public DurabilityItem getSourceItem() {
/* 46 */     return this.sourceItem;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 51 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean paramBoolean) {
/* 56 */     this.cancelled = paramBoolean;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 62 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 66 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\item\ItemCustomRepairEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */