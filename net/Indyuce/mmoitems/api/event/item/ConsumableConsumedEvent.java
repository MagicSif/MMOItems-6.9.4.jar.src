/*    */ package net.Indyuce.mmoitems.api.event.item;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.event.PlayerDataEvent;
/*    */ import net.Indyuce.mmoitems.api.interaction.Consumable;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConsumableConsumedEvent
/*    */   extends PlayerDataEvent
/*    */ {
/*    */   @NotNull
/*    */   private final VolatileMMOItem mmoitem;
/*    */   @NotNull
/*    */   private final Consumable useItem;
/*    */   @Nullable
/*    */   private boolean consumed = true;
/* 27 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   public ConsumableConsumedEvent(@NotNull PlayerData paramPlayerData, @NotNull VolatileMMOItem paramVolatileMMOItem, @NotNull Consumable paramConsumable) {
/* 30 */     super(paramPlayerData);
/*    */     
/* 32 */     this.mmoitem = paramVolatileMMOItem;
/* 33 */     this.useItem = paramConsumable;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public ConsumableConsumedEvent(@NotNull VolatileMMOItem paramVolatileMMOItem, @NotNull Player paramPlayer, @NotNull Consumable paramConsumable) {
/* 41 */     super(PlayerData.get((OfflinePlayer)paramPlayer));
/*    */     
/* 43 */     this.mmoitem = paramVolatileMMOItem;
/* 44 */     this.useItem = paramConsumable;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public VolatileMMOItem getMMOItem() {
/* 49 */     return this.mmoitem;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Consumable getUseItem() {
/* 54 */     return this.useItem;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Boolean isConsume() {
/* 62 */     return Boolean.valueOf(this.consumed);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isConsumed() {
/* 70 */     return this.consumed;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setConsume(@Nullable Boolean paramBoolean) {
/* 77 */     this.consumed = paramBoolean.booleanValue();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setConsumed(boolean paramBoolean) {
/* 89 */     this.consumed = paramBoolean;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 95 */     return handlers;
/*    */   }
/*    */   public static HandlerList getHandlerList() {
/* 98 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\item\ConsumableConsumedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */