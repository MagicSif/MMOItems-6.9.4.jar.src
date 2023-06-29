/*    */ package net.Indyuce.mmoitems.comp.inventory;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.player.inventory.EquippedItem;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerInventoryHandler
/*    */ {
/*    */   @NotNull
/* 28 */   private final List<PlayerInventory> registeredInventories = new ArrayList<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void register(@NotNull PlayerInventory paramPlayerInventory) {
/* 42 */     this.registeredInventories.add(paramPlayerInventory);
/* 43 */     if (paramPlayerInventory instanceof Listener)
/* 44 */       Bukkit.getPluginManager().registerEvents((Listener)paramPlayerInventory, (Plugin)MMOItems.plugin); 
/*    */   }
/*    */   
/*    */   public void unregisterIf(Predicate<PlayerInventory> paramPredicate) {
/* 48 */     Iterator<PlayerInventory> iterator = this.registeredInventories.iterator();
/* 49 */     while (iterator.hasNext()) {
/* 50 */       PlayerInventory playerInventory = iterator.next();
/* 51 */       if (paramPredicate.test(playerInventory)) {
/* 52 */         if (playerInventory instanceof Listener)
/* 53 */           HandlerList.unregisterAll((Listener)playerInventory); 
/* 54 */         iterator.remove();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void unregisterAll() {
/* 67 */     for (PlayerInventory playerInventory : this.registeredInventories) {
/* 68 */       if (playerInventory instanceof Listener)
/* 69 */         HandlerList.unregisterAll((Listener)playerInventory); 
/*    */     } 
/* 71 */     this.registeredInventories.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<PlayerInventory> getAll() {
/* 78 */     return new ArrayList<>(this.registeredInventories);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<EquippedItem> getInventory(@NotNull Player paramPlayer) {
/* 88 */     ArrayList<EquippedItem> arrayList = new ArrayList();
/*    */ 
/*    */     
/* 91 */     for (PlayerInventory playerInventory : this.registeredInventories)
/*    */     {
/*    */       
/* 94 */       arrayList.addAll(playerInventory.getInventory(paramPlayer));
/*    */     }
/*    */ 
/*    */     
/* 98 */     return arrayList;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\inventory\PlayerInventoryHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */