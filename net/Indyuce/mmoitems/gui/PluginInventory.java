/*    */ package net.Indyuce.mmoitems.gui;
/*    */ 
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryHolder;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public abstract class PluginInventory implements InventoryHolder {
/*    */   protected final PlayerData playerData;
/* 16 */   protected int page = 1; protected final Player player;
/*    */   
/*    */   public PluginInventory(@NotNull Player paramPlayer) {
/* 19 */     this(PlayerData.get((OfflinePlayer)paramPlayer));
/*    */   }
/*    */   
/*    */   public PluginInventory(PlayerData paramPlayerData) {
/* 23 */     this.playerData = paramPlayerData;
/* 24 */     this.player = paramPlayerData.getPlayer();
/*    */   }
/*    */   
/*    */   public int getPage() {
/* 28 */     return this.page;
/*    */   }
/*    */   
/*    */   public Player getPlayer() {
/* 32 */     return this.player;
/*    */   }
/*    */   
/*    */   public PlayerData getPlayerData() {
/* 36 */     return this.playerData;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract Inventory getInventory();
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void whenClicked(InventoryClickEvent paramInventoryClickEvent);
/*    */ 
/*    */   
/*    */   public void open() {
/* 49 */     if (Bukkit.isPrimaryThread()) {
/* 50 */       this.player.openInventory(getInventory());
/*    */     } else {
/* 52 */       Bukkit.getScheduler().runTask((Plugin)MMOItems.plugin, () -> this.player.openInventory(getInventory()));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\PluginInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */