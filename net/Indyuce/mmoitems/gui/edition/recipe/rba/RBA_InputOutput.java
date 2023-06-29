/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.rba;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.util.ItemFactory;
/*    */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
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
/*    */ public class RBA_InputOutput
/*    */   extends RecipeButtonAction
/*    */ {
/*    */   boolean showingInput;
/*    */   @NotNull
/*    */   final ItemStack button;
/*    */   
/*    */   public RBA_InputOutput(@NotNull RecipeMakerGUI paramRecipeMakerGUI) {
/* 27 */     super(paramRecipeMakerGUI);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 67 */     this.button = RecipeMakerGUI.addLore(ItemFactory.of(Material.CRAFTING_TABLE).name("§cSwitch to Output Mode").lore(SilentNumbers.chop("INPUT is the ingredients of the recipe, but (like milk buckets when crafting a cake) these ingredients may not be entirely consumed. In such cases, use the OUTPUT mode to specify what the ingredients will turn into.", 63, "§7"))
/*    */         
/* 69 */         .build(), SilentNumbers.toArrayList((Object[])new String[] { "" }));
/*    */     this.showingInput = true;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getButton() {
/* 76 */     String str = getInv().isShowingInput() ? "§6INPUT" : "§3OUTPUT";
/*    */ 
/*    */     
/* 79 */     return RecipeMakerGUI.addLore(this.button.clone(), SilentNumbers.toArrayList((Object[])new String[] { "§7Currently Showing: " + str, "", ChatColor.YELLOW + "►" + " Left click to switch mode." }));
/*    */   }
/*    */   
/*    */   public boolean runPrimary() {
/*    */     getInv().switchInput();
/*    */     getInv().refreshInventory();
/*    */     clickSFX();
/*    */     return true;
/*    */   }
/*    */   
/*    */   public void primaryProcessInput(@NotNull String paramString, Object... paramVarArgs) {}
/*    */   
/*    */   public boolean runSecondary() {
/*    */     return false;
/*    */   }
/*    */   
/*    */   public void secondaryProcessInput(@NotNull String paramString, Object... paramVarArgs) {}
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\rba\RBA_InputOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */