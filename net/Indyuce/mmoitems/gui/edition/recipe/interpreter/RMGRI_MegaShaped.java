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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RMGRI_MegaShaped
/*     */   implements RMG_RecipeInterpreter
/*     */ {
/*     */   @NotNull
/*     */   final ProvidedUIFilter[][] inputRecipe;
/*     */   @NotNull
/*     */   final ProvidedUIFilter[][] outputRecipe;
/*     */   @NotNull
/*     */   final ConfigurationSection section;
/*     */   public static final String emptyRow = "v AIR 0|v AIR 0|v AIR 0|v AIR 0|v AIR 0|v AIR 0";
/*     */   
/*     */   @NotNull
/*     */   ProvidedUIFilter[][] buildIngredientsFromList(@NotNull List<String> paramList) {
/*  43 */     ProvidedUIFilter[][] arrayOfProvidedUIFilter = new ProvidedUIFilter[6][6];
/*     */ 
/*     */     
/*  46 */     for (byte b = 0; b < 6; b++) {
/*     */ 
/*     */       
/*  49 */       String str1 = (paramList.size() > b) ? paramList.get(b) : null;
/*     */ 
/*     */ 
/*     */       
/*  53 */       String str2 = updateRow(str1);
/*     */ 
/*     */ 
/*     */       
/*  57 */       String[] arrayOfString = str2.split("\\|");
/*     */ 
/*     */       
/*  60 */       for (byte b1 = 0; b1 < 6; b1++) {
/*     */         
/*  62 */         String str = (arrayOfString.length > b1) ? arrayOfString[b1] : null;
/*     */ 
/*     */ 
/*     */         
/*  66 */         ProvidedUIFilter providedUIFilter = ProvidedUIFilter.getFromString(str, null);
/*  67 */         if (providedUIFilter == null) providedUIFilter = RecipeMakerGUI.AIR.clone();
/*     */ 
/*     */         
/*  70 */         arrayOfProvidedUIFilter[b][b1] = providedUIFilter;
/*     */       } 
/*     */     } 
/*  73 */     return arrayOfProvidedUIFilter;
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
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   ArrayList<String> toYML(@NotNull ProvidedUIFilter[][] paramArrayOfProvidedUIFilter) {
/* 104 */     ArrayList<String> arrayList = new ArrayList();
/*     */     
/* 106 */     for (byte b = 0; b < 6; b++) {
/*     */ 
/*     */       
/* 109 */       ProvidedUIFilter[] arrayOfProvidedUIFilter = (paramArrayOfProvidedUIFilter.length > b) ? paramArrayOfProvidedUIFilter[b] : new ProvidedUIFilter[5];
/*     */ 
/*     */       
/* 112 */       StringBuilder stringBuilder = new StringBuilder();
/*     */ 
/*     */       
/* 115 */       for (ProvidedUIFilter providedUIFilter1 : arrayOfProvidedUIFilter) {
/* 116 */         ProvidedUIFilter providedUIFilter2 = providedUIFilter1;
/* 117 */         if (providedUIFilter2 == null) providedUIFilter2 = RecipeMakerGUI.AIR.clone();
/*     */ 
/*     */         
/* 120 */         if (stringBuilder.length() != 0) stringBuilder.append("|");
/*     */ 
/*     */         
/* 123 */         stringBuilder.append(providedUIFilter2);
/*     */       } 
/*     */       
/* 126 */       arrayList.add(stringBuilder.toString());
/*     */     } 
/*     */     
/* 129 */     return arrayList;
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
/* 140 */     if (paramInt < 0 || paramInt > 35)
/* 141 */       return;  this.inputRecipe[SilentNumbers.floor(paramInt / 6.0D)][paramInt - 6 * SilentNumbers.floor(paramInt / 6.0D)] = paramProvidedUIFilter;
/*     */   }
/*     */   @Nullable
/*     */   public ProvidedUIFilter getInput(int paramInt) {
/* 145 */     if (paramInt < 0 || paramInt > 35) return null; 
/* 146 */     return this.inputRecipe[SilentNumbers.floor(paramInt / 6.0D)][paramInt - 6 * SilentNumbers.floor(paramInt / 6.0D)];
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
/* 157 */     if (paramInt < 0 || paramInt > 35)
/* 158 */       return;  this.outputRecipe[SilentNumbers.floor(paramInt / 6.0D)][paramInt - 6 * SilentNumbers.floor(paramInt / 6.0D)] = paramProvidedUIFilter;
/*     */   }
/*     */   @Nullable
/*     */   public ProvidedUIFilter getOutput(int paramInt) {
/* 162 */     if (paramInt < 0 || paramInt > 35) return null; 
/* 163 */     return this.outputRecipe[SilentNumbers.floor(paramInt / 6.0D)][paramInt - 6 * SilentNumbers.floor(paramInt / 6.0D)];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConfigurationSection getSection() {
/* 172 */     return this.section;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RMGRI_MegaShaped(@NotNull ConfigurationSection paramConfigurationSection) {
/* 182 */     this.section = paramConfigurationSection;
/*     */ 
/*     */     
/* 185 */     this.inputRecipe = buildIngredientsFromList(this.section.getStringList("input"));
/* 186 */     this.outputRecipe = buildIngredientsFromList(this.section.getStringList("output"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void editInput(@NotNull ProvidedUIFilter paramProvidedUIFilter, int paramInt) {
/* 193 */     setInput(paramInt, paramProvidedUIFilter);
/*     */ 
/*     */     
/* 196 */     this.section.set("input", toYML(this.inputRecipe));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void editOutput(@NotNull ProvidedUIFilter paramProvidedUIFilter, int paramInt) {
/* 203 */     setOutput(paramInt, paramProvidedUIFilter);
/*     */ 
/*     */     
/* 206 */     this.section.set("output", toYML(this.outputRecipe));
/*     */   }
/*     */   public void deleteInput(int paramInt) {
/* 209 */     editInput(RecipeMakerGUI.AIR.clone(), paramInt);
/*     */   } public void deleteOutput(int paramInt) {
/* 211 */     editOutput(RecipeMakerGUI.AIR.clone(), paramInt);
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
/* 224 */     if (paramString == null || paramString.isEmpty()) return "v AIR 0|v AIR 0|v AIR 0|v AIR 0|v AIR 0|v AIR 0";
/*     */ 
/*     */     
/* 227 */     if (paramString.contains("|")) {
/*     */ 
/*     */       
/* 230 */       String[] arrayOfString = paramString.split("\\|");
/*     */ 
/*     */       
/* 233 */       if (arrayOfString.length == 6)
/*     */       {
/*     */         
/* 236 */         return paramString;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 241 */       StringBuilder stringBuilder = new StringBuilder();
/*     */ 
/*     */       
/* 244 */       for (byte b = 0; b < 6; b++) {
/*     */ 
/*     */         
/* 247 */         if (b != 0) stringBuilder.append("|");
/*     */ 
/*     */         
/* 250 */         if (b < arrayOfString.length) { stringBuilder.append(RecipeMakerGUI.poofFromLegacy(arrayOfString[b])); } else { stringBuilder.append("v AIR 0"); }
/*     */       
/*     */       } 
/*     */       
/* 254 */       return stringBuilder.toString();
/*     */     } 
/*     */ 
/*     */     
/* 258 */     if (paramString.contains(" ")) {
/*     */ 
/*     */       
/* 261 */       StringBuilder stringBuilder = new StringBuilder();
/* 262 */       String[] arrayOfString = paramString.split(" ");
/*     */ 
/*     */       
/* 265 */       for (byte b = 0; b < 6; b++) {
/*     */ 
/*     */         
/* 268 */         if (b != 0) stringBuilder.append("|");
/*     */ 
/*     */         
/* 271 */         if (b < arrayOfString.length) { stringBuilder.append(RecipeMakerGUI.poofFromLegacy(arrayOfString[b])); } else { stringBuilder.append("v AIR 0"); }
/*     */       
/*     */       } 
/*     */       
/* 275 */       return stringBuilder.toString();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 281 */     return RecipeMakerGUI.poofFromLegacy(paramString) + "|v AIR 0|v AIR 0|v AIR 0|v AIR 0|v AIR 0";
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\interpreter\RMGRI_MegaShaped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */