/*    */ package net.Indyuce.mmoitems.api.recipe.workbench.ingredients;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.RecipeChoice;
/*    */ 
/*    */ public class AirIngredient extends WorkbenchIngredient {
/*    */   public AirIngredient() {
/*  9 */     super(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(ItemStack paramItemStack) {
/* 14 */     return (paramItemStack == null || paramItemStack.getType() == Material.AIR);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean corresponds(ItemStack paramItemStack) {
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack generateItem() {
/* 24 */     return new ItemStack(Material.AIR);
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeChoice toBukkit() {
/* 29 */     return (RecipeChoice)new RecipeChoice.MaterialChoice(Material.AIR);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\recipe\workbench\ingredients\AirIngredient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */