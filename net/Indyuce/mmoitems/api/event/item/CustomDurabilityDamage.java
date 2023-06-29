/*    */ package net.Indyuce.mmoitems.api.event.item;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.interaction.util.DurabilityItem;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class CustomDurabilityDamage
/*    */   extends Event implements Cancellable {
/*    */   @NotNull
/*    */   DurabilityItem sourceItem;
/*    */   int durabilityDecrease;
/*    */   boolean cancelled = false;
/*    */   
/*    */   public CustomDurabilityDamage(@NotNull DurabilityItem paramDurabilityItem, int paramInt) {
/* 17 */     this.sourceItem = paramDurabilityItem;
/* 18 */     this.durabilityDecrease = paramInt;
/*    */   }
/* 20 */   public int getDurabilityDecrease() { return this.durabilityDecrease; } @NotNull
/* 21 */   public DurabilityItem getSourceItem() { return this.sourceItem; }
/*    */   
/* 23 */   public boolean isCancelled() { return this.cancelled; } public void setCancelled(boolean paramBoolean) {
/* 24 */     this.cancelled = paramBoolean;
/*    */   }
/* 26 */   private static final HandlerList handlers = new HandlerList(); @NotNull
/* 27 */   public HandlerList getHandlers() { return handlers; }
/*    */    public static HandlerList getHandlerList() {
/* 29 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\item\CustomDurabilityDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */