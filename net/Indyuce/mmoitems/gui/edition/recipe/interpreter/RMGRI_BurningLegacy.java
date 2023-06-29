/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.interpreter;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.crafting.uimanager.ProvidedUIFilter;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RMGRI_BurningLegacy
/*    */   implements RMG_RecipeInterpreter
/*    */ {
/*    */   @NotNull
/*    */   ProvidedUIFilter input;
/*    */   @NotNull
/*    */   final ConfigurationSection section;
/*    */   public static final String ITEM = "item";
/*    */   public static final String TIME = "time";
/*    */   public static final String EXPERIENCE = "experience";
/*    */   
/*    */   @NotNull
/*    */   public ProvidedUIFilter getInput() {
/* 24 */     return this.input;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setInput(@Nullable ProvidedUIFilter paramProvidedUIFilter) {
/* 31 */     this.input = (paramProvidedUIFilter == null) ? RecipeMakerGUI.AIR : paramProvidedUIFilter;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ConfigurationSection getSection() {
/* 39 */     return this.section;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RMGRI_BurningLegacy(@NotNull ConfigurationSection paramConfigurationSection) {
/* 49 */     this.section = paramConfigurationSection;
/*    */ 
/*    */ 
/*    */     
/* 53 */     this.input = ProvidedUIFilter.getFromString(RecipeMakerGUI.poofFromLegacy(paramConfigurationSection.getString("item")), null);
/* 54 */     if (this.input == null) this.input = RecipeMakerGUI.AIR.clone();
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   public void editInput(@NotNull ProvidedUIFilter paramProvidedUIFilter, int paramInt) {
/* 60 */     if (paramInt != 0) {
/*    */       return;
/*    */     }
/* 63 */     setInput(paramProvidedUIFilter);
/*    */ 
/*    */     
/* 66 */     this.section.set("item", paramProvidedUIFilter.toString());
/*    */   }
/*    */   public void editOutput(@NotNull ProvidedUIFilter paramProvidedUIFilter, int paramInt) {}
/*    */   public void deleteOutput(int paramInt) {}
/*    */   public void deleteInput(int paramInt) {
/* 71 */     editInput(RecipeMakerGUI.AIR.clone(), paramInt);
/*    */   }
/*    */   @Nullable
/*    */   public ProvidedUIFilter getInput(int paramInt) {
/* 75 */     return (paramInt == 0) ? this.input : null; } @Nullable
/*    */   public ProvidedUIFilter getOutput(int paramInt) {
/* 77 */     return null;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\interpreter\RMGRI_BurningLegacy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */