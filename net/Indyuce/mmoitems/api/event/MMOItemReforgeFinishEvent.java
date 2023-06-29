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
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class MMOItemReforgeFinishEvent extends Event implements Cancellable {
/*     */   @NotNull
/*     */   final MMOItemReforger reforger;
/*     */   @NotNull
/*     */   final ReforgeOptions options;
/*     */   @NotNull
/*     */   ItemStack finishedItem;
/*     */   
/*     */   @NotNull
/*     */   public MMOItemReforger getReforger() {
/*  26 */     return this.reforger;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ReforgeOptions getOptions() {
/*  35 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getFinishedItem() {
/*  44 */     return this.finishedItem;
/*     */   }
/*     */   
/*     */   public void setFinishedItem(@NotNull ItemStack paramItemStack) {
/*  48 */     this.finishedItem = paramItemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MMOItemReforgeFinishEvent(@NotNull ItemStack paramItemStack, @NotNull MMOItemReforger paramMMOItemReforger, @NotNull ReforgeOptions paramReforgeOptions) {
/*  56 */     this.finishedItem = paramItemStack;
/*  57 */     this.reforger = paramMMOItemReforger;
/*  58 */     this.options = paramReforgeOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Player getPlayer() {
/*  70 */     if (getReforger().getPlayer() == null) return null;
/*     */ 
/*     */     
/*  73 */     return getReforger().getPlayer().getPlayer();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Type getType() {
/*  79 */     return getReforger().getTemplate().getType();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getTypeName() {
/*  84 */     return getReforger().getTemplate().getType().getId();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getID() {
/*  89 */     return getReforger().getTemplate().getId();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public LiveMMOItem getOldMMOItem() {
/*  96 */     return getReforger().getOldMMOItem();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MMOItem getNewMMOItem() {
/* 106 */     return getReforger().getFreshMMOItem();
/*     */   }
/*     */   
/*     */   boolean cancelled;
/* 110 */   private static final HandlerList handlers = new HandlerList(); @NotNull
/* 111 */   public HandlerList getHandlers() { return handlers; } public static HandlerList getHandlerList() {
/* 112 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 118 */     return this.cancelled;
/*     */   }
/*     */   public void setCancelled(boolean paramBoolean) {
/* 121 */     this.cancelled = paramBoolean;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\MMOItemReforgeFinishEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */