/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.registry.burninglegacy;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.recipe.workbench.ingredients.WorkbenchIngredient;
/*    */ import net.Indyuce.mmoitems.manager.RecipeManager;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BurningRecipeInformation
/*    */ {
/*    */   private final WorkbenchIngredient choice;
/*    */   private final float exp;
/*    */   private final int burnTime;
/*    */   
/*    */   public BurningRecipeInformation(@NotNull ConfigurationSection paramConfigurationSection) {
/* 22 */     String str = paramConfigurationSection.getString("item");
/* 23 */     if (str == null) throw new IllegalArgumentException("Invalid input ingredient");
/*    */ 
/*    */     
/* 26 */     this.choice = RecipeManager.getWorkbenchIngredient(str);
/* 27 */     this.exp = (float)paramConfigurationSection.getDouble("exp", 0.35D);
/* 28 */     this.burnTime = paramConfigurationSection.getInt("time", 200);
/*    */   }
/*    */   
/*    */   public BurningRecipeInformation(@NotNull WorkbenchIngredient paramWorkbenchIngredient, float paramFloat, int paramInt) {
/* 32 */     this.choice = paramWorkbenchIngredient;
/* 33 */     this.exp = paramFloat;
/* 34 */     this.burnTime = paramInt;
/*    */   }
/*    */   
/*    */   public int getBurnTime() {
/* 38 */     return this.burnTime;
/*    */   }
/*    */   
/*    */   public WorkbenchIngredient getChoice() {
/* 42 */     return this.choice;
/*    */   }
/*    */   
/*    */   public float getExp() {
/* 46 */     return this.exp;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\registry\burninglegacy\BurningRecipeInformation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */