/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.ProvidedUIFilter;
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.UIFilterManager;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.util.ui.QuickNumberRange;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.RecipeBrowserGUI;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMG_RecipeInterpreter;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RecipeButtonAction;
/*     */ import net.Indyuce.mmoitems.stat.data.StringData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class Crafting
/*     */   extends ItemStat<RandomStatData<StatData>, StatData>
/*     */ {
/*     */   public Crafting() {
/*  36 */     super("CRAFTING", VersionMaterial.CRAFTING_TABLE.toMaterial(), "Crafting", new String[] { "The crafting recipes of your item.", "Changing a recipe requires &o/mi reload recipes&7." }, new String[] { "all" }, new org.bukkit.Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  42 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  43 */       (new RecipeBrowserGUI(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited())).open(paramEditionInventory.getPage());
/*     */     }
/*  45 */     else if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && paramEditionInventory.getEditedSection().contains("crafting")) {
/*  46 */       paramEditionInventory.getEditedSection().set("crafting", null);
/*  47 */       paramEditionInventory.registerTemplateEdition();
/*  48 */       paramEditionInventory.getPlayer()
/*  49 */         .sendMessage(MMOItems.plugin.getPrefix() + "Crafting recipes successfully removed. Make sure you reload active recipes using " + ChatColor.RED + "/mi reload recipes" + ChatColor.GRAY + ".");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<RandomStatData<StatData>> paramOptional) {
/*  56 */     paramList.add(ChatColor.YELLOW + "►" + " Click to access the crafting edition menu.");
/*  57 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove all crafting recipes.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StatData getClearStatData() {
/*  67 */     return (StatData)new StringData(null);
/*     */   }
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*     */     int j;
/*     */     QuickNumberRange quickNumberRange;
/*     */     RMG_RecipeInterpreter rMG_RecipeInterpreter;
/*     */     int k;
/*     */     ProvidedUIFilter providedUIFilter;
/*     */     ConfigurationSection configurationSection;
/*  76 */     int i = ((Integer)paramVarArgs[0]).intValue();
/*     */     
/*  78 */     switch (i) {
/*     */ 
/*     */       
/*     */       case 0:
/*     */       case 1:
/*  83 */         j = paramString.indexOf(' ');
/*  84 */         quickNumberRange = null;
/*  85 */         if (j > 0) {
/*     */ 
/*     */           
/*  88 */           String str = paramString.substring(j + 1);
/*     */ 
/*     */           
/*  91 */           if (SilentNumbers.DoubleTryParse(str))
/*     */           {
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
/* 104 */             str = str + "..";
/*     */           }
/*     */ 
/*     */           
/* 108 */           quickNumberRange = QuickNumberRange.getFromString(str);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 114 */         if (j <= 0 || quickNumberRange != null) {
/*     */ 
/*     */           
/* 117 */           if (quickNumberRange == null) {
/*     */ 
/*     */             
/* 120 */             quickNumberRange = new QuickNumberRange(Double.valueOf(1.0D), null);
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */             
/* 126 */             paramString = paramString.substring(0, j);
/*     */           } 
/*     */ 
/*     */           
/* 130 */           if (paramString.contains(".")) {
/*     */ 
/*     */             
/* 133 */             String[] arrayOfString = paramString.split("\\.");
/*     */ 
/*     */             
/* 136 */             paramString = "m " + arrayOfString[0] + " " + arrayOfString[1] + " " + quickNumberRange;
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */             
/* 142 */             paramString = "v " + paramString + " - " + quickNumberRange;
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 151 */         rMG_RecipeInterpreter = (RMG_RecipeInterpreter)paramVarArgs[1];
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 156 */         k = ((Integer)paramVarArgs[2]).intValue();
/*     */ 
/*     */         
/* 159 */         providedUIFilter = UIFilterManager.getUIFilter(paramString, paramEditionInventory.getFFP());
/*     */ 
/*     */         
/* 162 */         if (providedUIFilter == null) throw new IllegalArgumentException(""); 
/* 163 */         if (!providedUIFilter.isValid(paramEditionInventory.getFFP())) throw new IllegalArgumentException("");
/*     */ 
/*     */         
/* 166 */         configurationSection = RecipeMakerGUI.getSection(paramEditionInventory.getEditedSection(), "crafting");
/* 167 */         configurationSection = RecipeMakerGUI.getSection(configurationSection, ((RecipeMakerGUI)paramEditionInventory).getRecipeRegistry().getRecipeConfigPath());
/* 168 */         configurationSection = RecipeMakerGUI.getSection(configurationSection, ((RecipeMakerGUI)paramEditionInventory).getRecipeName());
/*     */ 
/*     */         
/* 171 */         if (i == 0) {
/* 172 */           rMG_RecipeInterpreter.editInput(providedUIFilter, k);
/*     */         }
/*     */         else {
/*     */           
/* 176 */           rMG_RecipeInterpreter.editOutput(providedUIFilter, k);
/*     */         } 
/*     */         
/* 179 */         paramEditionInventory.registerTemplateEdition();
/*     */         return;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 190 */         if (paramVarArgs.length < 2)
/* 191 */           return;  if (!(paramVarArgs[1] instanceof RecipeButtonAction)) {
/*     */           return;
/*     */         }
/*     */         
/* 195 */         if (i == 2) {
/* 196 */           ((RecipeButtonAction)paramVarArgs[1]).primaryProcessInput(paramString, paramVarArgs);
/*     */         } else {
/*     */           
/* 199 */           ((RecipeButtonAction)paramVarArgs[1]).secondaryProcessInput(paramString, paramVarArgs);
/*     */         } 
/*     */         
/* 202 */         paramEditionInventory.registerTemplateEdition();
/*     */         return;
/*     */     } 
/* 205 */     paramEditionInventory.registerTemplateEdition();
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
/*     */   @Nullable
/*     */   public RandomStatData whenInitialized(Object paramObject) {
/* 232 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StatData paramStatData) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull StatData paramStatData) {
/* 246 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public StatData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 259 */     return null;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\Crafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */