/*     */ package net.Indyuce.mmoitems.api.crafting.recipe;
/*     */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.crafting.CraftingStation;
/*     */ import net.Indyuce.mmoitems.api.crafting.condition.Condition;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.Ingredient;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.IngredientInventory;
/*     */ import net.Indyuce.mmoitems.api.crafting.trigger.Trigger;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public abstract class Recipe {
/*  20 */   private final Map<RecipeOption, Boolean> options = new HashMap<>();
/*     */   private final String id;
/*  22 */   private final Set<Ingredient> ingredients = new LinkedHashSet<>();
/*  23 */   private final Set<Condition> conditions = new LinkedHashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   private final Set<Trigger> onUse = new LinkedHashSet<>(), onClaim = new LinkedHashSet<>(), onCancel = new LinkedHashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Recipe(ConfigurationSection paramConfigurationSection) {
/*  44 */     this(paramConfigurationSection.getName());
/*     */ 
/*     */     
/*  47 */     if (paramConfigurationSection.contains("options"))
/*  48 */       for (RecipeOption recipeOption : RecipeOption.values()) {
/*  49 */         if (paramConfigurationSection.getConfigurationSection("options").contains(recipeOption.getConfigPath())) {
/*  50 */           this.options.put(recipeOption, Boolean.valueOf(paramConfigurationSection.getBoolean("options." + recipeOption.getConfigPath())));
/*     */         }
/*     */       }  
/*  53 */     for (String str : paramConfigurationSection.getStringList("ingredients")) {
/*     */       try {
/*  55 */         Ingredient ingredient = MMOItems.plugin.getCrafting().getIngredient(new MMOLineConfig(str));
/*  56 */         this.ingredients.add(ingredient);
/*  57 */       } catch (IllegalArgumentException illegalArgumentException) {
/*  58 */         throw new IllegalArgumentException("Could not load ingredient '" + str + "': " + illegalArgumentException.getMessage());
/*     */       } 
/*     */     } 
/*     */     
/*  62 */     for (String str : paramConfigurationSection.getStringList("conditions")) {
/*     */       try {
/*  64 */         Condition condition = MMOItems.plugin.getCrafting().getCondition(new MMOLineConfig(str));
/*  65 */         this.conditions.add(condition);
/*  66 */       } catch (IllegalArgumentException illegalArgumentException) {
/*  67 */         throw new IllegalArgumentException("Could not load condition '" + str + "': " + illegalArgumentException.getMessage());
/*     */       } 
/*     */     } 
/*  70 */     if (this.conditions.isEmpty() && this.ingredients.isEmpty()) {
/*  71 */       throw new IllegalArgumentException("No conditions or ingredients set.");
/*     */     }
/*     */ 
/*     */     
/*  75 */     loadTriggerList(paramConfigurationSection.getStringList("on-use"), "on-use", this.onUse);
/*  76 */     loadTriggerList(paramConfigurationSection.getStringList("triggers"), "on-claim", this.onClaim);
/*  77 */     loadTriggerList(paramConfigurationSection.getStringList("on-cancel"), "on-cancel", this.onCancel);
/*     */   }
/*     */   
/*     */   private void loadTriggerList(List<String> paramList, String paramString, Set<Trigger> paramSet) {
/*  81 */     for (String str : paramList) {
/*     */       try {
/*  83 */         Trigger trigger = MMOItems.plugin.getCrafting().getTrigger(new MMOLineConfig(str));
/*  84 */         Validate.notNull(trigger, "Could not match trigger");
/*  85 */         paramSet.add(trigger);
/*  86 */       } catch (IllegalArgumentException illegalArgumentException) {
/*  87 */         throw new IllegalArgumentException("Could not load " + paramString + " trigger '" + str + "': " + illegalArgumentException.getMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private Recipe(String paramString) {
/*  92 */     Validate.notNull(paramString, "Recipe ID must not be null");
/*     */     
/*  94 */     this.id = paramString;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  98 */     return this.id;
/*     */   }
/*     */   
/*     */   public Set<Ingredient> getIngredients() {
/* 102 */     return this.ingredients;
/*     */   }
/*     */   
/*     */   public Set<Condition> getConditions() {
/* 106 */     return this.conditions;
/*     */   }
/*     */   
/*     */   public Set<Trigger> whenUsed() {
/* 110 */     return this.onUse;
/*     */   }
/*     */   
/*     */   public Set<Trigger> whenClaimed() {
/* 114 */     return this.onClaim;
/*     */   }
/*     */   
/*     */   public Set<Trigger> whenCanceled() {
/* 118 */     return this.onCancel;
/*     */   }
/*     */   
/*     */   public Condition getCondition(String paramString) {
/* 122 */     for (Condition condition : this.conditions) {
/* 123 */       if (condition.getId().equals(paramString))
/* 124 */         return condition; 
/* 125 */     }  return null;
/*     */   }
/*     */   
/*     */   public boolean hasOption(RecipeOption paramRecipeOption) {
/* 129 */     return ((Boolean)this.options.getOrDefault(paramRecipeOption, Boolean.valueOf(paramRecipeOption.getDefault()))).booleanValue();
/*     */   }
/*     */   
/*     */   public void addIngredient(Ingredient paramIngredient) {
/* 133 */     this.ingredients.add(paramIngredient);
/*     */   }
/*     */   
/*     */   public void registerCondition(Condition paramCondition) {
/* 137 */     this.conditions.add(paramCondition);
/*     */   }
/*     */   
/*     */   public void setOption(RecipeOption paramRecipeOption, boolean paramBoolean) {
/* 141 */     this.options.put(paramRecipeOption, Boolean.valueOf(paramBoolean));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract CheckedRecipe evaluateRecipe(PlayerData paramPlayerData, IngredientInventory paramIngredientInventory);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean whenUsed(PlayerData paramPlayerData, IngredientInventory paramIngredientInventory, CheckedRecipe paramCheckedRecipe, CraftingStation paramCraftingStation);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean canUse(PlayerData paramPlayerData, IngredientInventory paramIngredientInventory, CheckedRecipe paramCheckedRecipe, CraftingStation paramCraftingStation);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract ItemStack display(CheckedRecipe paramCheckedRecipe);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 188 */     return (paramObject instanceof Recipe && ((Recipe)paramObject).id.equals(this.id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum RecipeOption
/*     */   {
/* 196 */     HIDE_WHEN_LOCKED(false),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     HIDE_WHEN_NO_INGREDIENTS(false),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 209 */     OUTPUT_ITEM(true),
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 214 */     SILENT_CRAFT(false);
/*     */     
/*     */     private final boolean def;
/*     */     
/*     */     RecipeOption(boolean param1Boolean) {
/* 219 */       this.def = param1Boolean;
/*     */     }
/*     */     
/*     */     public boolean getDefault() {
/* 223 */       return this.def;
/*     */     }
/*     */     
/*     */     public String getConfigPath() {
/* 227 */       return name().toLowerCase().replace("_", "-");
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\recipe\Recipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */