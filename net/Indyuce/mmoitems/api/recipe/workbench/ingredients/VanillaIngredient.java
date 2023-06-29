/*    */ package net.Indyuce.mmoitems.api.recipe.workbench.ingredients;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.RecipeChoice;
/*    */ 
/*    */ public class VanillaIngredient
/*    */   extends WorkbenchIngredient {
/*    */   private final Material material;
/*    */   
/*    */   public VanillaIngredient(Material paramMaterial, int paramInt) {
/* 13 */     super(paramInt);
/*    */     
/* 15 */     this.material = paramMaterial;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean corresponds(ItemStack paramItemStack) {
/* 20 */     return (!NBTItem.get(paramItemStack).hasType() && paramItemStack.getType() == this.material);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack generateItem() {
/* 25 */     return new ItemStack(this.material);
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeChoice toBukkit() {
/* 30 */     return (RecipeChoice)new RecipeChoice.MaterialChoice(this.material);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\recipe\workbench\ingredients\VanillaIngredient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */