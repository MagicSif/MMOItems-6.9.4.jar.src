/*    */ package net.Indyuce.mmoitems.api.crafting.recipe;
/*    */ 
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.Set;
/*    */ import java.util.stream.Collectors;
/*    */ import net.Indyuce.mmoitems.api.crafting.condition.CheckedCondition;
/*    */ import net.Indyuce.mmoitems.api.crafting.condition.Condition;
/*    */ import net.Indyuce.mmoitems.api.crafting.ingredient.CheckedIngredient;
/*    */ import net.Indyuce.mmoitems.api.crafting.ingredient.Ingredient;
/*    */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.IngredientInventory;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class CheckedRecipe
/*    */ {
/*    */   private final Recipe recipe;
/* 18 */   private final Set<CheckedCondition> conditions = new LinkedHashSet<>();
/* 19 */   private final Set<CheckedIngredient> ingredients = new LinkedHashSet<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean ingredientsHad = true, conditionsMet = true;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CheckedRecipe(Recipe paramRecipe, PlayerData paramPlayerData, IngredientInventory paramIngredientInventory) {
/* 35 */     this.recipe = paramRecipe;
/*    */     
/* 37 */     for (Ingredient ingredient : paramRecipe.getIngredients()) {
/* 38 */       CheckedIngredient checkedIngredient = ingredient.evaluateIngredient(paramIngredientInventory);
/* 39 */       this.ingredients.add(checkedIngredient);
/* 40 */       if (!checkedIngredient.isHad()) {
/* 41 */         this.ingredientsHad = false;
/*    */       }
/*    */     } 
/* 44 */     for (Condition condition : paramRecipe.getConditions()) {
/* 45 */       CheckedCondition checkedCondition = condition.evaluateCondition(paramPlayerData);
/* 46 */       this.conditions.add(checkedCondition);
/* 47 */       if (!checkedCondition.isMet())
/* 48 */         this.conditionsMet = false; 
/*    */     } 
/*    */   }
/*    */   
/*    */   public Recipe getRecipe() {
/* 53 */     return this.recipe;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public boolean isUnlocked() {
/* 62 */     return (this.ingredientsHad && this.conditionsMet);
/*    */   }
/*    */   
/*    */   public boolean areConditionsMet() {
/* 66 */     return this.conditionsMet;
/*    */   }
/*    */   
/*    */   public boolean allIngredientsHad() {
/* 70 */     return this.ingredientsHad;
/*    */   }
/*    */   
/*    */   public ItemStack display() {
/* 74 */     return this.recipe.display(this);
/*    */   }
/*    */   
/*    */   public Set<CheckedCondition> getConditions() {
/* 78 */     return this.conditions;
/*    */   }
/*    */   
/*    */   public Set<CheckedCondition> getDisplayableConditions() {
/* 82 */     return (Set<CheckedCondition>)this.conditions.stream().filter(paramCheckedCondition -> (paramCheckedCondition.getCondition().getDisplay() != null)).collect(Collectors.toSet());
/*    */   }
/*    */   
/*    */   public Set<CheckedIngredient> getIngredients() {
/* 86 */     return this.ingredients;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\recipe\CheckedRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */