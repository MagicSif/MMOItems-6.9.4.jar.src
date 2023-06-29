/*    */ package net.Indyuce.mmoitems.api.interaction.util;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.EquipmentSlot;
/*    */ import org.bukkit.inventory.ItemStack;
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
/*    */ public class InteractItem
/*    */ {
/*    */   private final EquipmentSlot slot;
/*    */   private final ItemStack item;
/*    */   
/*    */   public InteractItem(Player paramPlayer, Material paramMaterial) {
/* 23 */     this
/* 24 */       .slot = hasItem(paramPlayer.getInventory().getItemInMainHand(), paramMaterial) ? EquipmentSlot.HAND : (hasItem(paramPlayer.getInventory().getItemInOffHand(), paramMaterial) ? EquipmentSlot.OFF_HAND : null);
/* 25 */     this.item = (this.slot == EquipmentSlot.HAND) ? paramPlayer.getInventory().getItemInMainHand() : paramPlayer.getInventory().getItemInOffHand();
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
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public InteractItem(Player paramPlayer, String paramString) {
/* 41 */     this
/* 42 */       .slot = hasItem(paramPlayer.getInventory().getItemInMainHand(), paramString) ? EquipmentSlot.HAND : (hasItem(paramPlayer.getInventory().getItemInOffHand(), paramString) ? EquipmentSlot.OFF_HAND : null);
/* 43 */     this.item = (this.slot == EquipmentSlot.HAND) ? paramPlayer.getInventory().getItemInMainHand() : paramPlayer.getInventory().getItemInOffHand();
/*    */   }
/*    */   
/*    */   public EquipmentSlot getSlot() {
/* 47 */     return this.slot;
/*    */   }
/*    */   
/*    */   public boolean hasItem() {
/* 51 */     return (this.slot != null);
/*    */   }
/*    */   
/*    */   public ItemStack getItem() {
/* 55 */     return this.item;
/*    */   }
/*    */   
/*    */   private boolean hasItem(ItemStack paramItemStack, Material paramMaterial) {
/* 59 */     return (paramItemStack != null && paramItemStack.getType() == paramMaterial);
/*    */   }
/*    */   
/*    */   private boolean hasItem(ItemStack paramItemStack, String paramString) {
/* 63 */     return (paramItemStack != null && paramItemStack.getType().name().endsWith(paramString));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interactio\\util\InteractItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */