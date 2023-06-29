/*     */ package net.Indyuce.mmoitems.api.event;
/*     */ 
/*     */ import net.Indyuce.mmoitems.api.ReforgeOptions;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.MMOItemReforger;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MMOItemReforgeEvent
/*     */   extends Event
/*     */   implements Cancellable
/*     */ {
/*     */   @NotNull
/*     */   final MMOItemReforger reforger;
/*     */   @NotNull
/*     */   final ReforgeOptions options;
/*     */   
/*     */   @NotNull
/*     */   public MMOItemReforger getReforger() {
/*  33 */     return this.reforger;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ReforgeOptions getOptions() {
/*  42 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MMOItemReforgeEvent(@NotNull MMOItemReforger paramMMOItemReforger, @NotNull ReforgeOptions paramReforgeOptions) {
/*  50 */     this.reforger = paramMMOItemReforger;
/*  51 */     this.options = paramReforgeOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Player getPlayer() {
/*  62 */     if (getReforger().getPlayer() == null) return null;
/*     */ 
/*     */     
/*  65 */     return getReforger().getPlayer().getPlayer();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Type getType() {
/*  71 */     return getReforger().getTemplate().getType();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getTypeName() {
/*  76 */     return getReforger().getTemplate().getType().getId();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getID() {
/*  81 */     return getReforger().getTemplate().getId();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public LiveMMOItem getOldMMOItem() {
/*  88 */     return getReforger().getOldMMOItem();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MMOItem getNewMMOItem() {
/*  98 */     return getReforger().getFreshMMOItem();
/*     */   }
/*     */   
/*     */   boolean cancelled;
/* 102 */   private static final HandlerList handlers = new HandlerList(); @NotNull
/* 103 */   public HandlerList getHandlers() { return handlers; } public static HandlerList getHandlerList() {
/* 104 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 110 */     return this.cancelled;
/*     */   }
/*     */   public void setCancelled(boolean paramBoolean) {
/* 113 */     this.cancelled = paramBoolean;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\MMOItemReforgeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */