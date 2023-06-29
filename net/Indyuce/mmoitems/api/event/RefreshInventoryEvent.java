/*    */ package net.Indyuce.mmoitems.api.event;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.inventory.EquippedItem;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class RefreshInventoryEvent extends Event {
/*    */   @NotNull
/*    */   final List<EquippedItem> itemsToEquip;
/*    */   
/*    */   @NotNull
/* 15 */   public List<EquippedItem> getItemsToEquip() { return this.itemsToEquip; } @NotNull
/*    */   final Player player; @NotNull
/*    */   final PlayerData playerData; @NotNull
/*    */   public Player getPlayer() {
/* 19 */     return this.player;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PlayerData getPlayerData() {
/* 24 */     return this.playerData;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RefreshInventoryEvent(@NotNull List<EquippedItem> paramList, @NotNull Player paramPlayer, @NotNull PlayerData paramPlayerData) {
/* 31 */     this.itemsToEquip = paramList;
/* 32 */     this.player = paramPlayer;
/* 33 */     this.playerData = paramPlayerData;
/*    */   }
/*    */   @NotNull
/* 36 */   static final HandlerList handlers = new HandlerList(); @NotNull
/* 37 */   public HandlerList getHandlers() { return handlers; } @NotNull
/* 38 */   public static HandlerList getHandlerList() { return handlers; }
/*    */ 
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\RefreshInventoryEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */