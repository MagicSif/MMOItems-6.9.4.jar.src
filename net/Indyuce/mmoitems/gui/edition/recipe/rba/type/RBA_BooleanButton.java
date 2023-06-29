/*     */ package net.Indyuce.mmoitems.gui.edition.recipe.rba.type;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RecipeButtonAction;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RBA_BooleanButton
/*     */   extends RecipeButtonAction
/*     */ {
/*     */   public RBA_BooleanButton(@NotNull RecipeMakerGUI paramRecipeMakerGUI) {
/*  23 */     super(paramRecipeMakerGUI);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/*  29 */     return getInv().getNameSection().getBoolean(getBooleanConfigPath(), false);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public abstract String getBooleanConfigPath();
/*     */ 
/*     */   
/*     */   public boolean runPrimary() {
/*  38 */     getInv().getNameSection().set(getBooleanConfigPath(), Boolean.valueOf(!isEnabled()));
/*  39 */     clickSFX();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  45 */     getInv().registerTemplateEdition();
/*     */ 
/*     */     
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void primaryProcessInput(@NotNull String paramString, Object... paramVarArgs) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean runSecondary() {
/*  63 */     getInv().getNameSection().set(getBooleanConfigPath(), null);
/*  64 */     clickSFX();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     getInv().registerTemplateEdition();
/*     */ 
/*     */     
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void secondaryProcessInput(@NotNull String paramString, Object... paramVarArgs) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public abstract ItemStack getBooleanButton();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getButton() {
/*  97 */     String str = isEnabled() ? "§aTRUE" : "§cFALSE";
/*     */ 
/*     */     
/* 100 */     return RecipeMakerGUI.addLore(getBooleanButton().clone(), 
/* 101 */         SilentNumbers.toArrayList((Object[])new String[] { "", "§7Current Value: " + str, "", ChatColor.YELLOW + "►" + " Right click to reset §8(to§4 FALSE§8)§e.", ChatColor.YELLOW + "►" + " Left click to toggle this option." }));
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\rba\type\RBA_BooleanButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */