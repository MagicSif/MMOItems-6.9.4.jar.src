/*    */ package net.Indyuce.mmoitems.api;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import java.util.WeakHashMap;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class DeathItemsHandler {
/* 17 */   private final List<ItemStack> items = new ArrayList<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final Player player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   private static final Map<UUID, DeathItemsHandler> INFO = new WeakHashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DeathItemsHandler(@NotNull Player paramPlayer) {
/* 40 */     this.player = paramPlayer;
/*    */   }
/*    */   
/*    */   public void registerItem(@NotNull ItemStack paramItemStack) {
/* 44 */     this.items.add(paramItemStack);
/*    */   }
/*    */   
/*    */   public void registerIfNecessary() {
/* 48 */     if (!this.items.isEmpty()) INFO.put(this.player.getUniqueId(), this);
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void giveItems(boolean paramBoolean) {
/* 57 */     if (paramBoolean) { for (ItemStack itemStack : this.items) {
/* 58 */         this.player.getWorld().dropItem(this.player.getLocation(), itemStack);
/*    */       } }
/*    */     else
/*    */     
/* 62 */     { ItemStack[] arrayOfItemStack = this.items.<ItemStack>toArray(new ItemStack[0]);
/* 63 */       for (ItemStack itemStack : this.player.getInventory().addItem(arrayOfItemStack).values()) {
/* 64 */         this.player.getWorld().dropItem(this.player.getLocation(), itemStack);
/*    */       } }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void readAndRemove(@NotNull Player paramPlayer) {
/* 76 */     DeathItemsHandler deathItemsHandler = INFO.remove(paramPlayer.getUniqueId());
/* 77 */     if (deathItemsHandler != null) Bukkit.getScheduler().runTaskLater((Plugin)MMOItems.plugin, () -> paramDeathItemsHandler.giveItems(false), 10L);
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Collection<DeathItemsHandler> getActive() {
/* 85 */     return INFO.values();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\DeathItemsHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */