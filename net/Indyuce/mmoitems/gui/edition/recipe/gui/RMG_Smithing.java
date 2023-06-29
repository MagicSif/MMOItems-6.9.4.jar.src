/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.gui;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMGRI_Smithing;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMG_RecipeInterpreter;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RBA_DropGems;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RBA_InputOutput;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RBA_SmithingEnchantments;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RBA_SmithingUpgrades;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RecipeButtonAction;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.RecipeRegistry;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public class RMG_Smithing
/*    */   extends RecipeMakerGUI
/*    */ {
/*    */   @NotNull
/* 31 */   final HashMap<Integer, Integer> inputLinks = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   final RMGRI_Smithing interpreter;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RMG_Smithing(@NotNull Player paramPlayer, @NotNull MMOItemTemplate paramMMOItemTemplate, @NotNull String paramString, @NotNull RecipeRegistry paramRecipeRegistry) {
/* 43 */     super(paramPlayer, paramMMOItemTemplate, paramString, paramRecipeRegistry);
/*    */ 
/*    */     
/* 46 */     ConfigurationSection configurationSection1 = RecipeMakerGUI.getSection(getEditedSection(), "crafting");
/* 47 */     ConfigurationSection configurationSection2 = RecipeMakerGUI.getSection(configurationSection1, getRecipeRegistry().getRecipeConfigPath());
/* 48 */     ConfigurationSection configurationSection3 = RecipeMakerGUI.getSection(configurationSection2, getRecipeName());
/* 49 */     this.interpreter = new RMGRI_Smithing(configurationSection3);
/*    */ 
/*    */     
/* 52 */     this.inputLinks.put(Integer.valueOf(39), Integer.valueOf(0));
/* 53 */     this.inputLinks.put(Integer.valueOf(41), Integer.valueOf(1));
/*    */ 
/*    */     
/* 56 */     addButton((RecipeButtonAction)new RBA_InputOutput(this));
/* 57 */     addButton((RecipeButtonAction)new RBA_SmithingUpgrades(this));
/* 58 */     addButton((RecipeButtonAction)new RBA_SmithingEnchantments(this));
/* 59 */     addButton((RecipeButtonAction)new RBA_DropGems(this));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void putRecipe(@NotNull Inventory paramInventory) {
/* 67 */     for (Integer integer : this.inputLinks.keySet()) paramInventory.setItem(integer.intValue(), getDisplay(isShowingInput(), ((Integer)this.inputLinks.get(integer)).intValue()));
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   int getInputSlot(int paramInt) {
/* 74 */     Integer integer = this.inputLinks.get(Integer.valueOf(paramInt));
/*    */ 
/*    */     
/* 77 */     return (integer != null) ? integer.intValue() : -1;
/*    */   } public int getButtonsRow() {
/* 79 */     return 1;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public RMG_RecipeInterpreter getInterpreter() {
/* 84 */     return (RMG_RecipeInterpreter)this.interpreter;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\gui\RMG_Smithing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */