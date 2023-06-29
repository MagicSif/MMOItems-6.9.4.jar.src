/*    */ package net.Indyuce.mmoitems.api.crafting.ingredient.inventory;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class VanillaPlayerIngredient extends PlayerIngredient {
/*    */   @NotNull
/*    */   public ItemStack getSourceItem() {
/* 12 */     return this.sourceItem;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   final ItemStack sourceItem;
/*    */   
/*    */   public VanillaPlayerIngredient(NBTItem paramNBTItem) {
/* 19 */     super(paramNBTItem);
/*    */ 
/*    */     
/* 22 */     this.sourceItem = paramNBTItem.toItem();
/* 23 */     this.material = paramNBTItem.getItem().getType();
/*    */     
/* 25 */     ItemMeta itemMeta = paramNBTItem.getItem().getItemMeta();
/* 26 */     if (itemMeta != null) { this.displayName = itemMeta.hasDisplayName() ? itemMeta.getDisplayName() : null; } else { this.displayName = null; }
/*    */   
/*    */   } private final Material material; @Nullable
/*    */   private final String displayName; public Material getType() {
/* 30 */     return this.material;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getDisplayName() {
/* 35 */     return this.displayName;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\ingredient\inventory\VanillaPlayerIngredient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */