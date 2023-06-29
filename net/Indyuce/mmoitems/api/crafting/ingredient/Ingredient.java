/*    */ package net.Indyuce.mmoitems.api.crafting.ingredient;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.crafting.ConditionalDisplay;
/*    */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.IngredientInventory;
/*    */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.PlayerIngredient;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Ingredient<C extends PlayerIngredient>
/*    */ {
/*    */   private final String id;
/*    */   private int amount;
/*    */   
/*    */   public Ingredient(String paramString, MMOLineConfig paramMMOLineConfig) {
/* 22 */     this(paramString, paramMMOLineConfig.getInt("amount", 1));
/*    */   }
/*    */   
/*    */   public Ingredient(String paramString, int paramInt) {
/* 26 */     this.id = paramString;
/* 27 */     this.amount = paramInt;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getId() {
/* 35 */     return this.id;
/*    */   }
/*    */   public void setAmount(int paramInt) {
/* 38 */     this.amount = paramInt;
/*    */   } public int getAmount() {
/* 40 */     return this.amount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ConditionalDisplay getDisplay() {
/* 48 */     return ((IngredientType)MMOItems.plugin.getCrafting().getIngredients().stream().filter(paramIngredientType -> paramIngredientType.getId().equals(this.id)).findAny().orElseThrow()).getDisplay();
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CheckedIngredient evaluateIngredient(@NotNull IngredientInventory paramIngredientInventory) {
/* 81 */     return paramIngredientInventory.findMatching(this);
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public abstract String getKey();
/*    */   
/*    */   public abstract String formatDisplay(String paramString);
/*    */   
/*    */   public abstract boolean matches(C paramC);
/*    */   
/*    */   @NotNull
/*    */   public abstract ItemStack generateItemStack(@NotNull RPGPlayer paramRPGPlayer, boolean paramBoolean);
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\ingredient\Ingredient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */