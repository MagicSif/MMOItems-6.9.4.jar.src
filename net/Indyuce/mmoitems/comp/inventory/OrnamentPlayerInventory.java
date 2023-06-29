/*    */ package net.Indyuce.mmoitems.comp.inventory;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.inventory.EquippedItem;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityPickupItemEvent;
/*    */ import org.bukkit.event.player.PlayerDropItemEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OrnamentPlayerInventory
/*    */   implements PlayerInventory, Listener
/*    */ {
/*    */   public List<EquippedItem> getInventory(Player paramPlayer) {
/* 32 */     ArrayList<SlotEquippedItem> arrayList = new ArrayList();
/*    */ 
/*    */     
/* 35 */     ItemStack[] arrayOfItemStack = paramPlayer.getInventory().getContents();
/* 36 */     for (byte b = 0; b < arrayOfItemStack.length; b++) {
/* 37 */       ItemStack itemStack = arrayOfItemStack[b];
/* 38 */       if (itemStack != null && itemStack.getType() != Material.AIR) {
/*    */ 
/*    */         
/* 41 */         NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack);
/* 42 */         Type type = Type.get(nBTItem.getType());
/* 43 */         if (type != null && type.getSupertype().equals(Type.ORNAMENT))
/* 44 */           arrayList.add(new SlotEquippedItem(paramPlayer, b, nBTItem, EquipmentSlot.OTHER)); 
/*    */       } 
/*    */     } 
/* 47 */     return (List)arrayList;
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
/*    */   public void updateOnItemPickup(EntityPickupItemEvent paramEntityPickupItemEvent) {
/* 52 */     if (paramEntityPickupItemEvent.getEntityType() == EntityType.PLAYER) {
/* 53 */       NBTItem nBTItem = NBTItem.get(paramEntityPickupItemEvent.getItem().getItemStack());
/* 54 */       if (nBTItem.hasType() && Type.get(nBTItem.getType()).getSupertype().equals(Type.ORNAMENT))
/* 55 */         PlayerData.get((OfflinePlayer)paramEntityPickupItemEvent.getEntity()).getInventory().scheduleUpdate(); 
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
/*    */   public void updateOnItemDrop(PlayerDropItemEvent paramPlayerDropItemEvent) {
/* 61 */     NBTItem nBTItem = NBTItem.get(paramPlayerDropItemEvent.getItemDrop().getItemStack());
/* 62 */     if (nBTItem.hasType() && Type.get(nBTItem.getType()).getSupertype().equals(Type.ORNAMENT))
/* 63 */       PlayerData.get((OfflinePlayer)paramPlayerDropItemEvent.getPlayer()).updateInventory(); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\inventory\OrnamentPlayerInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */