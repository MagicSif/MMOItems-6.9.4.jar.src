/*    */ package net.Indyuce.mmoitems.api.player.inventory;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.player.modifier.ModifierSource;
/*    */ import java.util.Objects;
/*    */ import javax.annotation.Nullable;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import org.apache.commons.lang.Validate;
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
/*    */ public abstract class EquippedItem
/*    */ {
/*    */   private final NBTItem item;
/*    */   private final EquipmentSlot slot;
/*    */   private VolatileMMOItem cached;
/*    */   
/*    */   public EquippedItem(ItemStack paramItemStack, EquipmentSlot paramEquipmentSlot) {
/* 29 */     this(NBTItem.get(paramItemStack), paramEquipmentSlot);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EquippedItem(NBTItem paramNBTItem, EquipmentSlot paramEquipmentSlot) {
/* 39 */     this.item = paramNBTItem;
/* 40 */     this.slot = paramEquipmentSlot;
/*    */   }
/*    */   
/*    */   public VolatileMMOItem getCached() {
/* 44 */     return Objects.<VolatileMMOItem>requireNonNull(this.cached, "Item not cached yet");
/*    */   }
/*    */   
/*    */   public void cacheItem() {
/* 48 */     Validate.isTrue((this.cached == null), "MMOItem has already been cached");
/* 49 */     this.cached = new VolatileMMOItem(this.item);
/*    */   }
/*    */   
/*    */   public NBTItem getNBT() {
/* 53 */     return this.item;
/*    */   }
/*    */   
/*    */   public EquipmentSlot getSlot() {
/* 57 */     return this.slot;
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
/*    */   public boolean isPlacementLegal() {
/* 69 */     String str = this.item.getString("MMOITEMS_ITEM_TYPE");
/* 70 */     if (str == null) {
/* 71 */       return false;
/*    */     }
/* 73 */     Type type = MMOItems.plugin.getTypes().get(str);
/* 74 */     if (type == null) {
/* 75 */       return false;
/*    */     }
/* 77 */     ModifierSource modifierSource = type.getModifierSource();
/* 78 */     return (EquipmentSlot.OFF_HAND.isCompatible(modifierSource, this.slot) || EquipmentSlot.MAIN_HAND.isCompatible(modifierSource, this.slot));
/*    */   }
/*    */   
/*    */   public abstract void setItem(@Nullable ItemStack paramItemStack);
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\player\inventory\EquippedItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */