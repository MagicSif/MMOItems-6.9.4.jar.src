/*    */ package net.Indyuce.mmoitems.api.player.inventory;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryUpdateHandler
/*    */ {
/*    */   private final PlayerData player;
/* 23 */   private final List<EquippedItem> items = new ArrayList<>();
/*    */   @Deprecated
/* 25 */   public ItemStack helmet = null, chestplate = null, leggings = null, boots = null, hand = null, offhand = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InventoryUpdateHandler(PlayerData paramPlayerData) {
/* 32 */     this.player = paramPlayerData;
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
/*    */   public List<EquippedItem> getEquipped() {
/* 44 */     return this.items;
/*    */   }
/*    */   
/*    */   public void updateCheck() {
/* 48 */     PlayerInventory playerInventory = this.player.getPlayer().getInventory();
/* 49 */     if (isDifferent(this.helmet, playerInventory.getHelmet()) || isDifferent(this.chestplate, playerInventory.getChestplate()) || isDifferent(this.leggings, playerInventory.getLeggings()) || 
/* 50 */       isDifferent(this.boots, playerInventory.getBoots()) || isDifferent(this.hand, playerInventory.getItemInMainHand()) || isDifferent(this.offhand, playerInventory.getItemInOffHand())) {
/* 51 */       this.player.updateInventory();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void scheduleUpdate() {
/* 58 */     Objects.requireNonNull(this.player); Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MMOItems.plugin, this.player::updateInventory);
/*    */   }
/*    */   
/*    */   private boolean isDifferent(ItemStack paramItemStack1, ItemStack paramItemStack2) {
/* 62 */     if (paramItemStack1 == null && paramItemStack2 == null) {
/* 63 */       return false;
/*    */     }
/* 65 */     if ((paramItemStack1 == null && paramItemStack2 != null) || (paramItemStack1 != null && paramItemStack2 == null)) {
/* 66 */       return true;
/*    */     }
/*    */     
/* 69 */     return (paramItemStack1.hashCode() != paramItemStack2.hashCode() && !paramItemStack1.equals(paramItemStack2));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\player\inventory\InventoryUpdateHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */