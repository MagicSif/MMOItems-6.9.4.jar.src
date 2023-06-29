/*    */ package net.Indyuce.mmoitems.comp.inventory;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.player.inventory.EquippedItem;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultPlayerInventory
/*    */   implements PlayerInventory
/*    */ {
/*    */   @NotNull
/*    */   public List<EquippedItem> getInventory(Player paramPlayer) {
/* 21 */     ArrayList<EquippedItem> arrayList = new ArrayList();
/*    */     
/* 23 */     if (paramPlayer.getEquipment() == null) return arrayList;
/*    */ 
/*    */     
/* 26 */     arrayList.add(new SlotEquippedItem(paramPlayer, -7, paramPlayer.getEquipment().getItemInMainHand(), EquipmentSlot.MAIN_HAND));
/*    */ 
/*    */     
/* 29 */     arrayList.add(new SlotEquippedItem(paramPlayer, -106, paramPlayer.getEquipment().getItemInOffHand(), EquipmentSlot.OFF_HAND));
/*    */ 
/*    */     
/* 32 */     arrayList.add(new SlotEquippedItem(paramPlayer, 103, paramPlayer.getEquipment().getHelmet(), EquipmentSlot.ARMOR));
/* 33 */     arrayList.add(new SlotEquippedItem(paramPlayer, 102, paramPlayer.getEquipment().getChestplate(), EquipmentSlot.ARMOR));
/* 34 */     arrayList.add(new SlotEquippedItem(paramPlayer, 101, paramPlayer.getEquipment().getLeggings(), EquipmentSlot.ARMOR));
/* 35 */     arrayList.add(new SlotEquippedItem(paramPlayer, 100, paramPlayer.getEquipment().getBoots(), EquipmentSlot.ARMOR));
/*    */     
/* 37 */     return arrayList;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\inventory\DefaultPlayerInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */