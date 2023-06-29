/*     */ package net.Indyuce.mmoitems.gui.edition.recipe.interpreter;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.ProvidedUIFilter;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
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
/*     */ public class RMGRI_Shaped
/*     */   implements RMG_RecipeInterpreter
/*     */ {
/*     */   @NotNull
/*     */   final ProvidedUIFilter[][] inputRecipe;
/*     */   @NotNull
/*     */   final ProvidedUIFilter[][] outputRecipe;
/*     */   @NotNull
/*     */   final ConfigurationSection section;
/*     */   public static final String emptyRow = "v AIR 0|v AIR 0|v AIR 0";
/*     */   
/*     */   @NotNull
/*     */   ProvidedUIFilter[][] buildIngredientsFromList(@NotNull List<String> paramList) {
/*  38 */     ProvidedUIFilter[][] arrayOfProvidedUIFilter = new ProvidedUIFilter[3][3];
/*     */ 
/*     */     
/*  41 */     for (byte b = 0; b < 3; b++) {
/*     */ 
/*     */       
/*  44 */       String str1 = (paramList.size() > b) ? paramList.get(b) : null;
/*     */ 
/*     */ 
/*     */       
/*  48 */       String str2 = updateRow(str1);
/*     */ 
/*     */ 
/*     */       
/*  52 */       String[] arrayOfString = str2.split("\\|");
/*     */ 
/*     */       
/*  55 */       for (byte b1 = 0; b1 < 3; b1++) {
/*     */         
/*  57 */         String str = (arrayOfString.length > b1) ? arrayOfString[b1] : null;
/*     */ 
/*     */ 
/*     */         
/*  61 */         ProvidedUIFilter providedUIFilter = ProvidedUIFilter.getFromString(str, null);
/*  62 */         if (providedUIFilter == null) providedUIFilter = RecipeMakerGUI.AIR.clone();
/*     */ 
/*     */         
/*  65 */         arrayOfProvidedUIFilter[b][b1] = providedUIFilter;
/*     */       } 
/*     */     } 
/*  68 */     return arrayOfProvidedUIFilter;
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
/*     */   @NotNull
/*     */   ArrayList<String> toYML(@NotNull ProvidedUIFilter[][] paramArrayOfProvidedUIFilter) {
/*  92 */     ArrayList<String> arrayList = new ArrayList();
/*     */     
/*  94 */     for (byte b = 0; b < 3; b++) {
/*     */ 
/*     */       
/*  97 */       ProvidedUIFilter[] arrayOfProvidedUIFilter = (paramArrayOfProvidedUIFilter.length > b) ? paramArrayOfProvidedUIFilter[b] : new ProvidedUIFilter[3];
/*     */ 
/*     */       
/* 100 */       StringBuilder stringBuilder = new StringBuilder();
/*     */ 
/*     */       
/* 103 */       for (ProvidedUIFilter providedUIFilter1 : arrayOfProvidedUIFilter) {
/* 104 */         ProvidedUIFilter providedUIFilter2 = providedUIFilter1;
/* 105 */         if (providedUIFilter2 == null) providedUIFilter2 = RecipeMakerGUI.AIR.clone();
/*     */ 
/*     */         
/* 108 */         if (stringBuilder.length() != 0) stringBuilder.append("|");
/*     */ 
/*     */         
/* 111 */         stringBuilder.append(providedUIFilter2);
/*     */       } 
/*     */       
/* 114 */       arrayList.add(stringBuilder.toString());
/*     */     } 
/*     */     
/* 117 */     return arrayList;
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
/* 128 */     if (paramInt < 0 || paramInt > 8)
/* 129 */       return;  this.inputRecipe[SilentNumbers.floor(paramInt / 3.0D)][paramInt - 3 * SilentNumbers.floor(paramInt / 3.0D)] = paramProvidedUIFilter;
/*     */   }
/*     */   @Nullable
/*     */   public ProvidedUIFilter getInput(int paramInt) {
/* 133 */     if (paramInt < 0 || paramInt > 8) return null; 
/* 134 */     return this.inputRecipe[SilentNumbers.floor(paramInt / 3.0D)][paramInt - 3 * SilentNumbers.floor(paramInt / 3.0D)];
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
/* 145 */     if (paramInt < 0 || paramInt > 8)
/* 146 */       return;  this.outputRecipe[SilentNumbers.floor(paramInt / 3.0D)][paramInt - 3 * SilentNumbers.floor(paramInt / 3.0D)] = paramProvidedUIFilter;
/*     */   }
/*     */   @Nullable
/*     */   public ProvidedUIFilter getOutput(int paramInt) {
/* 150 */     if (paramInt < 0 || paramInt > 8) return null; 
/* 151 */     return this.outputRecipe[SilentNumbers.floor(paramInt / 3.0D)][paramInt - 3 * SilentNumbers.floor(paramInt / 3.0D)];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConfigurationSection getSection() {
/* 160 */     return this.section;
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
/*     */   public RMGRI_Shaped(@NotNull ConfigurationSection paramConfigurationSection) {
/* 174 */     this.section = paramConfigurationSection;
/*     */ 
/*     */     
/* 177 */     this.inputRecipe = buildIngredientsFromList(this.section.getStringList("input"));
/* 178 */     this.outputRecipe = buildIngredientsFromList(this.section.getStringList("output"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void editInput(@NotNull ProvidedUIFilter paramProvidedUIFilter, int paramInt) {
/* 185 */     setInput(paramInt, paramProvidedUIFilter);
/*     */ 
/*     */     
/* 188 */     this.section.set("input", toYML(this.inputRecipe));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void editOutput(@NotNull ProvidedUIFilter paramProvidedUIFilter, int paramInt) {
/* 195 */     setOutput(paramInt, paramProvidedUIFilter);
/*     */ 
/*     */     
/* 198 */     this.section.set("output", toYML(this.outputRecipe));
/*     */   }
/*     */   public void deleteInput(int paramInt) {
/* 201 */     editInput(RecipeMakerGUI.AIR.clone(), paramInt);
/*     */   } public void deleteOutput(int paramInt) {
/* 203 */     editOutput(RecipeMakerGUI.AIR.clone(), paramInt);
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
/*     */   @NotNull
/*     */   public static String updateRow(@Nullable String paramString) {
/* 216 */     if (paramString == null || paramString.isEmpty()) return "v AIR 0|v AIR 0|v AIR 0";
/*     */ 
/*     */     
/* 219 */     if (paramString.contains("|")) {
/*     */ 
/*     */       
/* 222 */       String[] arrayOfString = paramString.split("\\|");
/*     */ 
/*     */       
/* 225 */       if (arrayOfString.length == 3)
/*     */       {
/*     */         
/* 228 */         return paramString;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 233 */       StringBuilder stringBuilder = new StringBuilder();
/*     */ 
/*     */       
/* 236 */       for (byte b = 0; b < 3; b++) {
/*     */ 
/*     */         
/* 239 */         if (b != 0) stringBuilder.append("|");
/*     */ 
/*     */         
/* 242 */         if (b < arrayOfString.length) { stringBuilder.append(RecipeMakerGUI.poofFromLegacy(arrayOfString[b])); } else { stringBuilder.append("v AIR 0"); }
/*     */       
/*     */       } 
/*     */       
/* 246 */       return stringBuilder.toString();
/*     */     } 
/*     */ 
/*     */     
/* 250 */     if (paramString.contains(" ")) {
/*     */ 
/*     */       
/* 253 */       StringBuilder stringBuilder = new StringBuilder();
/* 254 */       String[] arrayOfString = paramString.split(" ");
/*     */ 
/*     */       
/* 257 */       for (byte b = 0; b < 3; b++) {
/*     */ 
/*     */         
/* 260 */         if (b != 0) stringBuilder.append("|");
/*     */ 
/*     */         
/* 263 */         if (b < arrayOfString.length) { stringBuilder.append(RecipeMakerGUI.poofFromLegacy(arrayOfString[b])); } else { stringBuilder.append("v AIR 0"); }
/*     */       
/*     */       } 
/*     */       
/* 267 */       return stringBuilder.toString();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 273 */     return RecipeMakerGUI.poofFromLegacy(paramString) + "|v AIR 0|v AIR 0";
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\interpreter\RMGRI_Shaped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */