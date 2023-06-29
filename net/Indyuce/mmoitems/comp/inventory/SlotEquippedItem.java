/*    */ package net.Indyuce.mmoitems.comp.inventory;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import net.Indyuce.mmoitems.api.player.inventory.EquippedItem;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class SlotEquippedItem extends EquippedItem {
/*    */   private final Player player;
/*    */   private final int slotNumber;
/*    */   
/*    */   public SlotEquippedItem(@NotNull Player paramPlayer, int paramInt, ItemStack paramItemStack, EquipmentSlot paramEquipmentSlot) {
/* 16 */     super(paramItemStack, paramEquipmentSlot);
/*    */     
/* 18 */     this.player = paramPlayer;
/* 19 */     this.slotNumber = paramInt;
/*    */   }
/*    */   
/*    */   public SlotEquippedItem(@NotNull Player paramPlayer, int paramInt, NBTItem paramNBTItem, EquipmentSlot paramEquipmentSlot) {
/* 23 */     super(paramNBTItem, paramEquipmentSlot);
/*    */     
/* 25 */     this.player = paramPlayer;
/* 26 */     this.slotNumber = paramInt;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Player getPlayer() {
/* 31 */     return this.player;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public int getSlotNumber() {
/* 36 */     return this.slotNumber;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setItem(@Nullable ItemStack paramItemStack) {
/* 42 */     switch (getSlotNumber()) {
/*    */       case -106:
/* 44 */         getPlayer().getInventory().setItemInOffHand(paramItemStack);
/*    */         return;
/*    */       case -7:
/* 47 */         getPlayer().getInventory().setItemInMainHand(paramItemStack);
/*    */         return;
/*    */       case 103:
/* 50 */         getPlayer().getInventory().setHelmet(paramItemStack);
/*    */         return;
/*    */       case 102:
/* 53 */         getPlayer().getInventory().setChestplate(paramItemStack);
/*    */         return;
/*    */       case 101:
/* 56 */         getPlayer().getInventory().setLeggings(paramItemStack);
/*    */         return;
/*    */       case 100:
/* 59 */         getPlayer().getInventory().setBoots(paramItemStack);
/*    */         return;
/*    */     } 
/* 62 */     getPlayer().getInventory().setItem(getSlotNumber(), paramItemStack);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\inventory\SlotEquippedItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */