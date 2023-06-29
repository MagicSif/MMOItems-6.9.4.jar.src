/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.gui;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMGRI_Shapeless;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMG_RecipeInterpreter;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RBA_HideFromBook;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RBA_InputOutput;
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
/*    */ public class RMG_Shapeless
/*    */   extends RecipeMakerGUI
/*    */ {
/*    */   @NotNull
/* 29 */   final HashMap<Integer, Integer> inputLinks = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   final RMGRI_Shapeless interpreter;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RMG_Shapeless(@NotNull Player paramPlayer, @NotNull MMOItemTemplate paramMMOItemTemplate, @NotNull String paramString, @NotNull RecipeRegistry paramRecipeRegistry) {
/* 41 */     super(paramPlayer, paramMMOItemTemplate, paramString, paramRecipeRegistry);
/* 42 */     addButton((RecipeButtonAction)new RBA_InputOutput(this));
/* 43 */     addButton((RecipeButtonAction)new RBA_HideFromBook(this));
/*    */ 
/*    */     
/* 46 */     ConfigurationSection configurationSection1 = RecipeMakerGUI.getSection(getEditedSection(), "crafting");
/* 47 */     ConfigurationSection configurationSection2 = RecipeMakerGUI.getSection(configurationSection1, getRecipeRegistry().getRecipeConfigPath());
/* 48 */     ConfigurationSection configurationSection3 = RecipeMakerGUI.getSection(configurationSection2, getRecipeName());
/* 49 */     this.interpreter = new RMGRI_Shapeless(configurationSection3);
/*    */ 
/*    */     
/* 52 */     this.inputLinks.put(Integer.valueOf(30), Integer.valueOf(0));
/* 53 */     this.inputLinks.put(Integer.valueOf(31), Integer.valueOf(1));
/* 54 */     this.inputLinks.put(Integer.valueOf(32), Integer.valueOf(2));
/*    */     
/* 56 */     this.inputLinks.put(Integer.valueOf(39), Integer.valueOf(3));
/* 57 */     this.inputLinks.put(Integer.valueOf(40), Integer.valueOf(4));
/* 58 */     this.inputLinks.put(Integer.valueOf(41), Integer.valueOf(5));
/*    */     
/* 60 */     this.inputLinks.put(Integer.valueOf(48), Integer.valueOf(6));
/* 61 */     this.inputLinks.put(Integer.valueOf(49), Integer.valueOf(7));
/* 62 */     this.inputLinks.put(Integer.valueOf(50), Integer.valueOf(8));
/*    */   } public int getButtonsRow() {
/* 64 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void putRecipe(@NotNull Inventory paramInventory) {
/* 70 */     for (Integer integer : this.inputLinks.keySet()) paramInventory.setItem(integer.intValue(), getDisplay(isShowingInput(), ((Integer)this.inputLinks.get(integer)).intValue()));
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   int getInputSlot(int paramInt) {
/* 77 */     Integer integer = this.inputLinks.get(Integer.valueOf(paramInt));
/*    */ 
/*    */     
/* 80 */     return (integer != null) ? integer.intValue() : -1;
/*    */   }
/*    */   @NotNull
/*    */   public RMG_RecipeInterpreter getInterpreter() {
/* 84 */     return (RMG_RecipeInterpreter)this.interpreter;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\gui\RMG_Shapeless.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */