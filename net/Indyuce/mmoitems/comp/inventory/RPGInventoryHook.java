/*    */ package net.Indyuce.mmoitems.comp.inventory;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.inventory.EquippedItem;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ import ru.endlesscode.rpginventory.api.InventoryAPI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RPGInventoryHook
/*    */   implements PlayerInventory, Listener
/*    */ {
/*    */   public List<EquippedItem> getInventory(Player paramPlayer) {
/* 26 */     ArrayList<LegacyEquippedItem> arrayList = new ArrayList();
/*    */     
/* 28 */     for (ItemStack itemStack : InventoryAPI.getPassiveItems(paramPlayer)) {
/* 29 */       if (itemStack != null)
/* 30 */         arrayList.add(new LegacyEquippedItem(itemStack)); 
/*    */     } 
/* 32 */     return (List)arrayList;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void a(InventoryCloseEvent paramInventoryCloseEvent) {
/* 37 */     if (InventoryAPI.isRPGInventory(paramInventoryCloseEvent.getInventory()))
/* 38 */       PlayerData.get((OfflinePlayer)paramInventoryCloseEvent.getPlayer()).updateInventory(); 
/*    */   }
/*    */   
/*    */   public class LegacyEquippedItem extends EquippedItem {
/*    */     public LegacyEquippedItem(ItemStack param1ItemStack) {
/* 43 */       super(param1ItemStack, EquipmentSlot.ACCESSORY);
/*    */     }
/*    */ 
/*    */     
/*    */     public void setItem(@Nullable ItemStack param1ItemStack) {
/* 48 */       ItemStack itemStack = getNBT().getItem();
/* 49 */       itemStack.setType(param1ItemStack.getType());
/* 50 */       itemStack.setAmount(param1ItemStack.getAmount());
/* 51 */       itemStack.setItemMeta(itemStack.getItemMeta());
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\inventory\RPGInventoryHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */