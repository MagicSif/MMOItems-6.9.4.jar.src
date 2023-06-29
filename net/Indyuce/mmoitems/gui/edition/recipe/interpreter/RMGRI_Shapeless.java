/*     */ package net.Indyuce.mmoitems.gui.edition.recipe.interpreter;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.ProvidedUIFilter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RMGRI_Shapeless
/*     */   implements RMG_RecipeInterpreter
/*     */ {
/*     */   @NotNull
/*     */   final ProvidedUIFilter[] inputRecipe;
/*     */   @NotNull
/*     */   final ProvidedUIFilter[] outputRecipe;
/*     */   @NotNull
/*     */   final ConfigurationSection section;
/*     */   
/*     */   @NotNull
/*     */   ProvidedUIFilter[] buildIngredientsFromList(@NotNull List<String> paramList) {
/*  43 */     ProvidedUIFilter[] arrayOfProvidedUIFilter = new ProvidedUIFilter[9];
/*     */ 
/*     */     
/*  46 */     for (byte b = 0; b < 9; b++) {
/*     */ 
/*     */       
/*  49 */       String str1 = (paramList.size() > b) ? paramList.get(b) : null;
/*     */ 
/*     */       
/*  52 */       String str2 = RecipeMakerGUI.poofFromLegacy(str1);
/*     */ 
/*     */       
/*  55 */       ProvidedUIFilter providedUIFilter = ProvidedUIFilter.getFromString(str2, null);
/*  56 */       if (providedUIFilter == null) providedUIFilter = RecipeMakerGUI.AIR.clone();
/*     */ 
/*     */       
/*  59 */       arrayOfProvidedUIFilter[b] = providedUIFilter;
/*     */     } 
/*     */ 
/*     */     
/*  63 */     return arrayOfProvidedUIFilter;
/*     */   }
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
/*     */   @NotNull
/*     */   ArrayList<String> toYML(@NotNull ProvidedUIFilter[] paramArrayOfProvidedUIFilter) {
/*  91 */     ArrayList<String> arrayList = new ArrayList();
/*     */     
/*  93 */     for (byte b = 0; b < 9; b++) {
/*     */ 
/*     */       
/*  96 */       ProvidedUIFilter providedUIFilter = (paramArrayOfProvidedUIFilter.length > b) ? paramArrayOfProvidedUIFilter[b] : RecipeMakerGUI.AIR.clone();
/*     */ 
/*     */       
/*  99 */       arrayList.add(providedUIFilter.toString());
/*     */     } 
/*     */ 
/*     */     
/* 103 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInput(int paramInt, @NotNull ProvidedUIFilter paramProvidedUIFilter) {
/* 114 */     if (paramInt < 0 || paramInt > 8)
/* 115 */       return;  this.inputRecipe[paramInt] = paramProvidedUIFilter;
/*     */   }
/*     */   @Nullable
/*     */   public ProvidedUIFilter getInput(int paramInt) {
/* 119 */     if (paramInt < 0 || paramInt > 8) return null; 
/* 120 */     return this.inputRecipe[paramInt];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOutput(int paramInt, @NotNull ProvidedUIFilter paramProvidedUIFilter) {
/* 131 */     if (paramInt < 0 || paramInt > 8)
/* 132 */       return;  this.outputRecipe[paramInt] = paramProvidedUIFilter;
/*     */   }
/*     */   @Nullable
/*     */   public ProvidedUIFilter getOutput(int paramInt) {
/* 136 */     if (paramInt < 0 || paramInt > 8) return null; 
/* 137 */     return this.outputRecipe[paramInt];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConfigurationSection getSection() {
/* 146 */     return this.section;
/*     */   }
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
/*     */   public RMGRI_Shapeless(@NotNull ConfigurationSection paramConfigurationSection) {
/* 160 */     this.section = paramConfigurationSection;
/*     */ 
/*     */     
/* 163 */     this.inputRecipe = buildIngredientsFromList(this.section.getStringList("input"));
/* 164 */     this.outputRecipe = buildIngredientsFromList(this.section.getStringList("output"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void editInput(@NotNull ProvidedUIFilter paramProvidedUIFilter, int paramInt) {
/* 171 */     setInput(paramInt, paramProvidedUIFilter);
/*     */ 
/*     */     
/* 174 */     this.section.set("input", toYML(this.inputRecipe));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void editOutput(@NotNull ProvidedUIFilter paramProvidedUIFilter, int paramInt) {
/* 181 */     setOutput(paramInt, paramProvidedUIFilter);
/*     */ 
/*     */     
/* 184 */     this.section.set("output", toYML(this.outputRecipe));
/*     */   }
/*     */   public void deleteInput(int paramInt) {
/* 187 */     editInput(RecipeMakerGUI.AIR.clone(), paramInt);
/*     */   } public void deleteOutput(int paramInt) {
/* 189 */     editOutput(RecipeMakerGUI.AIR.clone(), paramInt);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\interpreter\RMGRI_Shapeless.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */