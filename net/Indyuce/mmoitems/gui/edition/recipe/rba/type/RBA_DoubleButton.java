/*     */ package net.Indyuce.mmoitems.gui.edition.recipe.rba.type;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import io.lumine.mythic.lib.api.util.ui.QuickNumberRange;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RecipeButtonAction;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RBA_DoubleButton
/*     */   extends RecipeButtonAction
/*     */ {
/*     */   @NotNull
/*     */   public final String[] amountLog;
/*     */   @NotNull
/*     */   public final String[] integerLog;
/*     */   
/*     */   public RBA_DoubleButton(@NotNull RecipeMakerGUI paramRecipeMakerGUI) {
/*  29 */     super(paramRecipeMakerGUI);
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
/*  40 */     this
/*  41 */       .amountLog = new String[] { FriendlyFeedbackProvider.quickForPlayer((FriendlyFeedbackPalette)FFPMMOItems.get(), "Write in the chat a number, ex $e2.5$b.", new String[0]) };
/*     */     
/*  43 */     this
/*  44 */       .integerLog = new String[] { FriendlyFeedbackProvider.quickForPlayer((FriendlyFeedbackPalette)FFPMMOItems.get(), "Write in the chat an integer number, ex $e8$b.", new String[0]) };
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean runPrimary() {
/*  49 */     (new StatEdition((EditionInventory)getInv(), ItemStats.CRAFTING, new Object[] { Integer.valueOf(2), this })).enable(requireInteger() ? this.integerLog : this.amountLog);
/*     */ 
/*     */     
/*  52 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getValue() {
/*     */     return getInv().getNameSection().getDouble(getDoubleConfigPath(), getDefaultValue());
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public abstract String getDoubleConfigPath();
/*     */   
/*     */   public void primaryProcessInput(@NotNull String paramString, Object... paramVarArgs) {
/*     */     Double double_;
/*  66 */     if (requireInteger()) {
/*     */ 
/*     */       
/*  69 */       Integer integer = SilentNumbers.IntegerParse(paramString);
/*  70 */       if (integer == null) throw new IllegalArgumentException("Expected integer number instead of $u" + paramString);
/*     */ 
/*     */       
/*  73 */       double_ = Double.valueOf(integer.intValue());
/*     */     }
/*     */     else {
/*     */       
/*  77 */       double_ = SilentNumbers.DoubleParse(paramString);
/*  78 */       if (double_ == null) throw new IllegalArgumentException("Expected a number instead of $u" + paramString);
/*     */     
/*     */     } 
/*     */     
/*  82 */     if (getRange() != null)
/*     */     {
/*     */       
/*  85 */       if (!getRange().inRange(double_.doubleValue()))
/*     */       {
/*  87 */         throw new IllegalArgumentException("Number $r" + double_ + "$b is out of range. Expected " + getRange().toStringColored());
/*     */       }
/*     */     }
/*     */     
/*  91 */     getInv().getNameSection().set(getDoubleConfigPath(), double_);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public abstract QuickNumberRange getRange();
/*     */   
/*     */   public abstract boolean requireInteger();
/*     */   
/*     */   public boolean runSecondary() {
/* 100 */     getInv().getNameSection().set(getDoubleConfigPath(), null);
/* 101 */     clickSFX();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     getInv().registerTemplateEdition();
/*     */ 
/*     */     
/* 110 */     return true;
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
/*     */   public abstract double getDefaultValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public abstract ItemStack getDoubleButton();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getButton() {
/* 139 */     return RecipeMakerGUI.addLore(getDoubleButton().clone(), 
/* 140 */         SilentNumbers.toArrayList((Object[])new String[] {
/* 141 */             "", "§7Current Value: " + getValue(), "", ChatColor.YELLOW + "►" + " Right click to reset §8(to§4 " + 
/* 142 */             getDefaultValue() + "§8)§e.", ChatColor.YELLOW + "►" + " Left click to toggle this option."
/*     */           }));
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\rba\type\RBA_DoubleButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */