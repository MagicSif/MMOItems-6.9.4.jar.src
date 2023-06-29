/*     */ package net.Indyuce.mmoitems.gui.edition.recipe.rba.type;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ 
/*     */ public abstract class RBA_ChooseableButton
/*     */   extends RecipeButtonAction
/*     */ {
/*     */   public RBA_ChooseableButton(@NotNull RecipeMakerGUI paramRecipeMakerGUI) {
/*  26 */     super(paramRecipeMakerGUI);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean runPrimary() {
/*  36 */     String str1 = getCurrentChooseableValue();
/*     */ 
/*     */     
/*  39 */     int i = getChooseableList().indexOf(str1);
/*     */ 
/*     */     
/*  42 */     if (i == -1) return runSecondary();
/*     */ 
/*     */     
/*  45 */     i++;
/*  46 */     if (i >= getChooseableList().size()) i = 0;
/*     */ 
/*     */     
/*  49 */     String str2 = getChooseableList().get(i);
/*     */ 
/*     */     
/*  52 */     getInv().getNameSection().set(getChooseableConfigPath(), str2);
/*  53 */     clickSFX();
/*     */ 
/*     */     
/*  56 */     getInv().registerTemplateEdition();
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean runSecondary() {
/*  66 */     getInv().getNameSection().set(getChooseableConfigPath(), null);
/*  67 */     clickSFX();
/*     */ 
/*     */     
/*  70 */     getInv().registerTemplateEdition();
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public abstract ItemStack getChooseableButton();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getButton() {
/*  90 */     String str = getCurrentChooseableValue();
/*     */ 
/*     */     
/*  93 */     ArrayList<String> arrayList = new ArrayList();
/*  94 */     arrayList.add("");
/*  95 */     arrayList.add("§7Current Value:§3 " + str);
/*  96 */     arrayList.addAll(SilentNumbers.chop(getChooseableDefinition(str), 50, "  §b§o"));
/*  97 */     arrayList.add("");
/*  98 */     arrayList.add(ChatColor.YELLOW + "►" + " Right click to return to default value.");
/*  99 */     arrayList.add(ChatColor.YELLOW + "►" + " Left click to cycle through the options:");
/* 100 */     for (String str1 : getChooseableList()) {
/*     */ 
/*     */       
/* 103 */       String str2 = ChatColor.GOLD.toString();
/* 104 */       if (str1.equals(str)) str2 = ChatColor.RED.toString() + ChatColor.BOLD;
/*     */       
/* 106 */       arrayList.add(str2 + "  " + "▸" + " §7" + str1);
/*     */     } 
/*     */     
/* 109 */     return RecipeMakerGUI.addLore(getChooseableButton().clone(), arrayList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public abstract String getChooseableConfigPath();
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public abstract ArrayList<String> getChooseableList();
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getCurrentChooseableValue() {
/* 127 */     String str = getInv().getNameSection().getString(getChooseableConfigPath());
/* 128 */     return (str != null) ? str : getDefaultValue();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public abstract String getDefaultValue();
/*     */   
/*     */   @NotNull
/*     */   public abstract String getChooseableDefinition(@NotNull String paramString);
/*     */   
/*     */   public void secondaryProcessInput(@NotNull String paramString, Object... paramVarArgs) {}
/*     */   
/*     */   public void primaryProcessInput(@NotNull String paramString, Object... paramVarArgs) {}
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\rba\type\RBA_ChooseableButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */