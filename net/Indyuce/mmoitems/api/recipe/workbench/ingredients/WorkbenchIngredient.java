/*    */ package net.Indyuce.mmoitems.api.recipe.workbench.ingredients;
/*    */ 
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.RecipeChoice;
/*    */ 
/*    */ public abstract class WorkbenchIngredient {
/*    */   private final int amount;
/*    */   
/*    */   public WorkbenchIngredient(int paramInt) {
/* 10 */     this.amount = paramInt;
/*    */   }
/*    */   
/*    */   public int getAmount() {
/* 14 */     return this.amount;
/*    */   }
/*    */   
/*    */   public boolean matches(ItemStack paramItemStack) {
/* 18 */     return (paramItemStack != null && paramItemStack.getAmount() >= this.amount && corresponds(paramItemStack));
/*    */   }
/*    */   
/*    */   public abstract RecipeChoice toBukkit();
/*    */   
/*    */   public abstract ItemStack generateItem();
/*    */   
/*    */   protected abstract boolean corresponds(ItemStack paramItemStack);
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\recipe\workbench\ingredients\WorkbenchIngredient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */