/*     */ package net.Indyuce.mmoitems.gui.edition.recipe.interpreter;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.ProvidedUIFilter;
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
/*     */ public class RMGRI_Smithing
/*     */   implements RMG_RecipeInterpreter
/*     */ {
/*     */   @NotNull
/*     */   ProvidedUIFilter inputItem;
/*     */   @NotNull
/*     */   ProvidedUIFilter outputItem;
/*     */   @NotNull
/*     */   ProvidedUIFilter inputIngot;
/*     */   @NotNull
/*     */   ProvidedUIFilter outputIngot;
/*     */   @NotNull
/*     */   final ConfigurationSection section;
/*     */   public static final String emptyIngredients = "v AIR -|v AIR -";
/*     */   
/*     */   @NotNull
/*     */   String toYML(@NotNull ProvidedUIFilter paramProvidedUIFilter1, @NotNull ProvidedUIFilter paramProvidedUIFilter2) {
/*  40 */     return paramProvidedUIFilter1 + "|" + paramProvidedUIFilter2;
/*     */   }
/*     */   
/*     */   @NotNull
/*  44 */   public ProvidedUIFilter getInputItem() { return this.inputItem; } public void setInputItem(@NotNull ProvidedUIFilter paramProvidedUIFilter) {
/*  45 */     this.inputItem = paramProvidedUIFilter;
/*     */   }
/*     */   @NotNull
/*  48 */   public ProvidedUIFilter getOutputItem() { return this.outputItem; } public void setOutputItem(@NotNull ProvidedUIFilter paramProvidedUIFilter) {
/*  49 */     this.outputItem = paramProvidedUIFilter;
/*     */   }
/*     */   @NotNull
/*  52 */   public ProvidedUIFilter getInputIngot() { return this.inputIngot; } public void setInputIngot(@NotNull ProvidedUIFilter paramProvidedUIFilter) {
/*  53 */     this.inputIngot = paramProvidedUIFilter;
/*     */   }
/*     */   @NotNull
/*  56 */   public ProvidedUIFilter getOutputIngot() { return this.outputIngot; } public void setOutputIngot(@NotNull ProvidedUIFilter paramProvidedUIFilter) {
/*  57 */     this.outputIngot = paramProvidedUIFilter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ConfigurationSection getSection() {
/*  66 */     return this.section;
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
/*     */   public RMGRI_Smithing(@NotNull ConfigurationSection paramConfigurationSection) {
/*  80 */     this.section = paramConfigurationSection;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     String str1 = updateIngredients(this.section.getString("input"));
/*  86 */     String str2 = updateIngredients(this.section.getString("output"));
/*     */ 
/*     */     
/*  89 */     String[] arrayOfString1 = str1.split("\\|");
/*  90 */     String[] arrayOfString2 = str2.split("\\|");
/*     */     
/*  92 */     ProvidedUIFilter providedUIFilter1 = ProvidedUIFilter.getFromString(arrayOfString1[0], null);
/*  93 */     ProvidedUIFilter providedUIFilter2 = ProvidedUIFilter.getFromString(arrayOfString2[0], null);
/*  94 */     ProvidedUIFilter providedUIFilter3 = ProvidedUIFilter.getFromString(arrayOfString1[1], null);
/*  95 */     ProvidedUIFilter providedUIFilter4 = ProvidedUIFilter.getFromString(arrayOfString2[1], null);
/*     */ 
/*     */     
/*  98 */     this.inputItem = (providedUIFilter1 != null) ? providedUIFilter1 : RecipeMakerGUI.AIR.clone();
/*  99 */     this.inputIngot = (providedUIFilter3 != null) ? providedUIFilter3 : RecipeMakerGUI.AIR.clone();
/* 100 */     this.outputItem = (providedUIFilter2 != null) ? providedUIFilter2 : RecipeMakerGUI.AIR.clone();
/* 101 */     this.outputIngot = (providedUIFilter4 != null) ? providedUIFilter4 : RecipeMakerGUI.AIR.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInput(int paramInt, @NotNull ProvidedUIFilter paramProvidedUIFilter) {
/* 109 */     if (paramInt == 0) { setInputItem(paramProvidedUIFilter); } else if (paramInt == 1) { setInputIngot(paramProvidedUIFilter); }
/*     */   
/*     */   } @Nullable
/*     */   public ProvidedUIFilter getInput(int paramInt) {
/* 113 */     if (paramInt == 0) return getInputItem();  if (paramInt == 1) return getInputIngot(); 
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOutput(int paramInt, @NotNull ProvidedUIFilter paramProvidedUIFilter) {
/* 122 */     if (paramInt == 0) { setOutputItem(paramProvidedUIFilter); } else if (paramInt == 1) { setOutputIngot(paramProvidedUIFilter); }
/*     */   
/*     */   } @Nullable
/*     */   public ProvidedUIFilter getOutput(int paramInt) {
/* 126 */     if (paramInt == 0) return getOutputItem();  if (paramInt == 1) return getOutputIngot(); 
/* 127 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void editInput(@NotNull ProvidedUIFilter paramProvidedUIFilter, int paramInt) {
/* 134 */     setInput(paramInt, paramProvidedUIFilter);
/*     */ 
/*     */     
/* 137 */     this.section.set("input", toYML(getInputItem(), getInputIngot()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void editOutput(@NotNull ProvidedUIFilter paramProvidedUIFilter, int paramInt) {
/* 144 */     setOutput(paramInt, paramProvidedUIFilter);
/*     */ 
/*     */     
/* 147 */     this.section.set("output", toYML(getOutputItem(), getOutputIngot()));
/*     */   }
/*     */   public void deleteInput(int paramInt) {
/* 150 */     editInput(RecipeMakerGUI.AIR.clone(), paramInt);
/*     */   } public void deleteOutput(int paramInt) {
/* 152 */     editOutput(RecipeMakerGUI.AIR.clone(), paramInt);
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
/*     */   public static String updateIngredients(@Nullable String paramString) {
/* 165 */     if (paramString == null || paramString.isEmpty()) return "v AIR -|v AIR -";
/*     */ 
/*     */     
/* 168 */     if (paramString.contains("|")) {
/*     */ 
/*     */       
/* 171 */       String[] arrayOfString = paramString.split("\\|");
/*     */ 
/*     */       
/* 174 */       if (arrayOfString.length == 2)
/*     */       {
/*     */         
/* 177 */         return paramString;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 182 */       StringBuilder stringBuilder = new StringBuilder();
/*     */ 
/*     */       
/* 185 */       for (byte b = 0; b < 2; b++) {
/*     */ 
/*     */         
/* 188 */         if (b != 0) stringBuilder.append("|");
/*     */ 
/*     */         
/* 191 */         if (b < arrayOfString.length) { stringBuilder.append(RecipeMakerGUI.poofFromLegacy(arrayOfString[b])); } else { stringBuilder.append("v AIR -"); }
/*     */       
/*     */       } 
/*     */       
/* 195 */       return stringBuilder.toString();
/*     */     } 
/*     */ 
/*     */     
/* 199 */     if (paramString.contains(" ")) {
/*     */ 
/*     */       
/* 202 */       StringBuilder stringBuilder = new StringBuilder();
/* 203 */       String[] arrayOfString = paramString.split(" ");
/*     */ 
/*     */       
/* 206 */       for (byte b = 0; b < 2; b++) {
/*     */ 
/*     */         
/* 209 */         if (b != 0) stringBuilder.append("|");
/*     */ 
/*     */         
/* 212 */         if (b < arrayOfString.length) { stringBuilder.append(RecipeMakerGUI.poofFromLegacy(arrayOfString[b])); } else { stringBuilder.append("v AIR -"); }
/*     */       
/*     */       } 
/*     */       
/* 216 */       return stringBuilder.toString();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 222 */     return RecipeMakerGUI.poofFromLegacy(paramString) + "|v AIR 0";
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\interpreter\RMGRI_Smithing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */