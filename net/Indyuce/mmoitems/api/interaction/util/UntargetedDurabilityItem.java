/*    */ package net.Indyuce.mmoitems.api.interaction.util;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UntargetedDurabilityItem
/*    */   extends DurabilityItem
/*    */ {
/*    */   private final EquipmentSlot slot;
/*    */   
/*    */   public UntargetedDurabilityItem(Player paramPlayer, NBTItem paramNBTItem, EquipmentSlot paramEquipmentSlot) {
/* 17 */     super(paramPlayer, paramNBTItem);
/*    */     
/* 19 */     this.slot = paramEquipmentSlot;
/*    */   }
/*    */ 
/*    */   
/*    */   public UntargetedDurabilityItem decreaseDurability(int paramInt) {
/* 24 */     return (UntargetedDurabilityItem)super.decreaseDurability(paramInt);
/*    */   }
/*    */ 
/*    */   
/*    */   public void inventoryUpdate() {
/* 29 */     ItemStack itemStack = toItem();
/* 30 */     if (itemStack == null) {
/* 31 */       if (this.slot == EquipmentSlot.OFF_HAND) {
/* 32 */         getPlayer().getInventory().setItemInOffHand(null);
/*    */       } else {
/* 34 */         getPlayer().getInventory().setItemInMainHand(null);
/*    */       } 
/*    */       return;
/*    */     } 
/* 38 */     getNBTItem().getItem().setItemMeta(toItem().getItemMeta());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interactio\\util\UntargetedDurabilityItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */