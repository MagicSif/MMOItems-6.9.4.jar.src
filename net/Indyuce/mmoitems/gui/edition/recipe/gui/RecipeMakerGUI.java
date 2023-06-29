/*     */ package net.Indyuce.mmoitems.gui.edition.recipe.gui;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.ProvidedUIFilter;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import io.lumine.mythic.lib.api.util.ui.QuickNumberRange;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RecipeButtonAction;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public abstract class RecipeMakerGUI extends EditionInventory {
/*     */   @NotNull
/*     */   final ItemStack nextButtonPage;
/*     */   @NotNull
/*     */   final ItemStack prevButtonPage;
/*     */   @NotNull
/*     */   public final ItemStack noButton;
/*     */   @NotNull
/*     */   public final ItemStack emptySlot;
/*     */   @NotNull
/*     */   public final ItemStack airSlot;
/*     */   @NotNull
/*     */   final Inventory myInventory;
/*     */   @NotNull
/*     */   final ConfigurationSection craftingSection;
/*     */   @NotNull
/*     */   final ConfigurationSection typeSection;
/*     */   @NotNull
/*     */   final ConfigurationSection nameSection;
/*     */   @NotNull
/*     */   final RecipeRegistry recipeRegistry;
/*     */   @NotNull
/*     */   final RBA_AmountOutput amountButton;
/*     */   @NotNull
/*     */   final String recipeName;
/*     */   int buttonsPage;
/*     */   @NotNull
/*     */   final HashMap<Integer, RecipeButtonAction> buttonsMap;
/*     */   @NotNull
/*     */   final ArrayList<RecipeButtonAction> buttons;
/*     */   @NotNull
/*     */   public final String[] recipeLog;
/*     */   
/*     */   @NotNull
/*     */   public Inventory getMyInventory() {
/*     */     return this.myInventory;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ConfigurationSection getCraftingSection() {
/*     */     return this.craftingSection;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ConfigurationSection getTypeSection() {
/*     */     return this.typeSection;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ConfigurationSection getNameSection() {
/*     */     return this.nameSection;
/*     */   }
/*     */   
/*  71 */   public RecipeMakerGUI(@NotNull Player paramPlayer, @NotNull MMOItemTemplate paramMMOItemTemplate, @NotNull String paramString, @NotNull RecipeRegistry paramRecipeRegistry) { super(paramPlayer, paramMMOItemTemplate);
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
/*  94 */     this
/*  95 */       .nextButtonPage = ItemFactory.of(Material.SPECTRAL_ARROW).name("§eMore Options §c»").build();
/*  96 */     this
/*  97 */       .prevButtonPage = ItemFactory.of(Material.SPECTRAL_ARROW).name("§c« §eMore Options").build();
/*  98 */     this
/*  99 */       .noButton = ItemFactory.of(Material.IRON_BARS).name("§8---").build();
/*     */ 
/*     */     
/* 102 */     this
/* 103 */       .emptySlot = ItemFactory.of(Material.BARRIER).name("§7No Item").build();
/* 104 */     this
/* 105 */       .airSlot = ItemFactory.of(Material.STRUCTURE_VOID).name("§7No Item").build();
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
/* 211 */     this.buttonsMap = new HashMap<>();
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
/* 395 */     this.buttons = new ArrayList<>();
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
/* 407 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 413 */       .recipeLog = new String[] { FriendlyFeedbackProvider.quickForPlayer((FriendlyFeedbackPalette)FFPMMOItems.get(), "Write in the chat the item you want, follow any format:", new String[0]), FriendlyFeedbackProvider.quickForPlayer((FriendlyFeedbackPalette)FFPMMOItems.get(), "Vanilla: $e[MATERIAL] [AMOUNT] $bex $eDIAMOND 2..", new String[0]), FriendlyFeedbackProvider.quickForPlayer((FriendlyFeedbackPalette)FFPMMOItems.get(), "MMOItem: $e[TYPE].[ID] [AMOUNT] $bex $eSWORD.CUTLASS 1..", new String[0]), FriendlyFeedbackProvider.quickForPlayer((FriendlyFeedbackPalette)FFPMMOItems.get(), "Other: $e[KEY] [ARG] [DAT] [AMOUNT]$b (check wiki)", new String[0]), FriendlyFeedbackProvider.quickForPlayer((FriendlyFeedbackPalette)FFPMMOItems.get(), "§8Amount is in the range format, $e[min]..[max]§8, assumed to be $r1..§8 if unspecified.", new String[0]) }; this.recipeName = paramString; this.recipeRegistry = paramRecipeRegistry; this.myInventory = Bukkit.createInventory((InventoryHolder)this, 54, "Edit " + getRecipeRegistry().getRecipeTypeName() + " Recipe"); moveInput(); this.craftingSection = getSection(getEditedSection(), "crafting"); this.typeSection = getSection(this.craftingSection, getRecipeRegistry().getRecipeConfigPath()); this.nameSection = getSection(this.typeSection, paramString); addButton((RecipeButtonAction)(this.amountButton = new RBA_AmountOutput(this, getCachedItem().clone()))); }
/*     */   @NotNull public RecipeRegistry getRecipeRegistry() { return this.recipeRegistry; }
/*     */   @NotNull public RBA_AmountOutput getAmountButton() { return this.amountButton; }
/*     */   @NotNull public String getRecipeName() { return this.recipeName; }
/*     */   public void putButtons(@NotNull Inventory paramInventory) { if (getButtonsRow() < 0)
/*     */       return;  this.buttonsMap.clear(); if (this.buttonsPage > 0)
/*     */       this.myInventory.setItem(getButtonsRow() * 9 + 8, this.prevButtonPage);  if (this.buttonsMap.size() >= (this.buttonsPage + 1) * 7)
/*     */       this.myInventory.setItem(getButtonsRow() * 9, this.nextButtonPage);  for (int i = 7 * this.buttonsPage; i < 7 * (this.buttonsPage + 1); i++) {
/*     */       int j = buttonRowPageClamp(i); if (i >= this.buttons.size()) {
/*     */         paramInventory.setItem(j, this.noButton);
/*     */       } else {
/*     */         RecipeButtonAction recipeButtonAction = this.buttons.get(i); paramInventory.setItem(j, recipeButtonAction.getButton());
/*     */         this.buttonsMap.put(Integer.valueOf(j), recipeButtonAction);
/*     */       } 
/* 427 */     }  } @NotNull public Inventory getInventory() { refreshInventory();
/*     */ 
/*     */     
/* 430 */     return this.myInventory; } public int buttonRowPageClamp(int paramInt) { int i = SilentNumbers.floor(paramInt / 7.0D); paramInt -= i * 7; int j = getButtonsRow() * 9; int k = paramInt + 1; return j + k; } public abstract int getButtonsRow(); abstract int getInputSlot(int paramInt); public void refreshInventory() { addEditionInventoryItems(getMyInventory(), true); putButtons(getMyInventory()); putRecipe(getMyInventory()); } public abstract void putRecipe(@NotNull Inventory paramInventory);
/*     */   @NotNull public ItemStack getDisplay(boolean paramBoolean, int paramInt) { ProvidedUIFilter providedUIFilter = paramBoolean ? getInterpreter().getInput(paramInt) : getInterpreter().getOutput(paramInt);
/*     */     if (providedUIFilter == null || providedUIFilter.isAir())
/*     */       return isShowingInput() ? this.emptySlot : this.airSlot; 
/*     */     return providedUIFilter.getDisplayStack(null); }
/*     */   public void addButton(@NotNull RecipeButtonAction paramRecipeButtonAction) { this.buttons.add(paramRecipeButtonAction); }
/*     */   @NotNull public abstract RMG_RecipeInterpreter getInterpreter();
/* 437 */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) { if (paramInventoryClickEvent.getView().getTopInventory() != paramInventoryClickEvent.getClickedInventory()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 442 */     paramInventoryClickEvent.setCancelled(true);
/*     */ 
/*     */     
/* 445 */     int i = getInputSlot(paramInventoryClickEvent.getRawSlot());
/*     */ 
/*     */     
/* 448 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*     */ 
/*     */       
/* 451 */       if (i >= 0)
/*     */       {
/*     */         
/* 454 */         if (isShowingInput())
/*     */         {
/*     */           
/* 457 */           (new StatEdition(this, ItemStats.CRAFTING, new Object[] { Integer.valueOf(0), getInterpreter(), Integer.valueOf(i) })).enable(this.recipeLog);
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 462 */           (new StatEdition(this, ItemStats.CRAFTING, new Object[] { Integer.valueOf(1), getInterpreter(), Integer.valueOf(i) })).enable(this.recipeLog);
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 469 */         RecipeButtonAction recipeButtonAction = this.buttonsMap.get(Integer.valueOf(paramInventoryClickEvent.getRawSlot()));
/*     */ 
/*     */         
/* 472 */         if (recipeButtonAction != null) {
/* 473 */           recipeButtonAction.runPrimary();
/*     */         }
/*     */       }
/*     */     
/*     */     }
/* 478 */     else if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/*     */ 
/*     */ 
/*     */       
/* 482 */       if (i >= 0) {
/*     */ 
/*     */         
/* 485 */         if (isShowingInput()) {
/*     */ 
/*     */           
/* 488 */           getInterpreter().deleteInput(i);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 493 */           getInterpreter().deleteOutput(getInputSlot(paramInventoryClickEvent.getRawSlot()));
/*     */         } 
/*     */ 
/*     */         
/* 497 */         registerTemplateEdition();
/*     */ 
/*     */         
/* 500 */         refreshInventory();
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 506 */         RecipeButtonAction recipeButtonAction = this.buttonsMap.get(Integer.valueOf(paramInventoryClickEvent.getRawSlot()));
/*     */ 
/*     */         
/* 509 */         if (recipeButtonAction != null) {
/* 510 */           recipeButtonAction.runSecondary();
/*     */         }
/*     */       } 
/*     */     }  }
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
/* 525 */   static final HashMap<UUID, Boolean> showingInput = new HashMap<>();
/*     */   
/*     */   public static final int INPUT = 0;
/*     */   public static final int OUTPUT = 1;
/*     */   public static final int PRIMARY = 2;
/*     */   public static final int SECONDARY = 3;
/*     */   public static final String INPUT_INGREDIENTS = "input";
/*     */   public static final String OUTPUT_INGREDIENTS = "output";
/*     */   
/*     */   public static void switchInputFor(@NotNull UUID paramUUID) {
/* 535 */     showingInput.put(paramUUID, Boolean.valueOf(!isShowingInputFor(paramUUID)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isShowingInputFor(@NotNull UUID paramUUID) {
/* 545 */     return ((Boolean)showingInput.getOrDefault(paramUUID, Boolean.valueOf(true))).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isShowingInput() {
/* 552 */     return isShowingInputFor(getPlayer().getUniqueId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void switchInput() {
/* 559 */     switchInputFor(getPlayer().getUniqueId());
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
/* 576 */   public static final ProvidedUIFilter AIR = new ProvidedUIFilter((UIFilter)new VanillaUIFilter(), "AIR", "0");
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
/*     */   public static ItemStack rename(@NotNull ItemStack paramItemStack, @NotNull String paramString) {
/* 589 */     ItemMeta itemMeta = paramItemStack.getItemMeta();
/*     */     
/* 591 */     itemMeta.setDisplayName(MythicLib.plugin.parseColors(paramString));
/* 592 */     paramItemStack.setItemMeta(itemMeta);
/* 593 */     return paramItemStack;
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
/*     */   @NotNull
/*     */   public static ItemStack addLore(@NotNull ItemStack paramItemStack, @NotNull ArrayList<String> paramArrayList) {
/* 607 */     if (!paramItemStack.hasItemMeta()) {
/* 608 */       return paramItemStack;
/*     */     }
/*     */ 
/*     */     
/* 612 */     ItemMeta itemMeta = paramItemStack.getItemMeta();
/* 613 */     if (itemMeta == null) {
/* 614 */       return paramItemStack;
/*     */     }
/*     */     
/* 617 */     List<String> list = itemMeta.getLore();
/* 618 */     if (list == null) {
/* 619 */       list = new ArrayList();
/*     */     }
/*     */ 
/*     */     
/* 623 */     list.addAll(paramArrayList);
/* 624 */     AdventureUtils.setLore(itemMeta, list);
/*     */ 
/*     */     
/* 627 */     paramItemStack.setItemMeta(itemMeta);
/* 628 */     return paramItemStack;
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
/*     */   public static ConfigurationSection getSection(@NotNull ConfigurationSection paramConfigurationSection, @NotNull String paramString) {
/* 641 */     ConfigurationSection configurationSection = paramConfigurationSection.getConfigurationSection(paramString);
/* 642 */     if (configurationSection == null) {
/* 643 */       configurationSection = paramConfigurationSection.createSection(paramString);
/*     */     }
/* 645 */     return configurationSection;
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
/*     */   public void moveInput() {
/* 659 */     ConfigurationSection configurationSection1 = getSection(getEditedSection(), "crafting");
/* 660 */     ConfigurationSection configurationSection2 = getSection(configurationSection1, getRecipeRegistry().getRecipeConfigPath());
/*     */ 
/*     */     
/* 663 */     moveInput(configurationSection2, this.recipeName);
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
/*     */   public static void tripleDebug(@NotNull ConfigurationSection paramConfigurationSection) {
/* 678 */     MMOItems.print(null, "§d-§7 Section §5" + paramConfigurationSection.getCurrentPath(), null, new String[0]);
/* 679 */     for (String str : paramConfigurationSection.getKeys(false)) {
/* 680 */       MMOItems.print(null, "§d +§7 " + str, null, new String[0]);
/*     */       
/* 682 */       MMOItems.print(null, "§d-§e-§7 As List §5" + paramConfigurationSection.getCurrentPath() + "." + str + "§7 {§d" + paramConfigurationSection.getStringList(str).size() + "§7}", null, new String[0]);
/* 683 */       for (String str1 : paramConfigurationSection.getStringList(str)) {
/* 684 */         MMOItems.print(null, "§d +§e-§7" + str1, null, new String[0]);
/*     */       }
/*     */       
/* 687 */       ConfigurationSection configurationSection = getSection(paramConfigurationSection, str);
/* 688 */       MMOItems.print(null, "§8--§d-§7 Section §5" + configurationSection.getCurrentPath(), null, new String[0]);
/* 689 */       for (String str1 : configurationSection.getKeys(false)) {
/* 690 */         MMOItems.print(null, "§8--§d +§7 " + str1, null, new String[0]);
/*     */         
/* 692 */         MMOItems.print(null, "§8--§d-§e-§7 As List §5" + configurationSection.getCurrentPath() + "." + str1 + "§7 {§d" + configurationSection.getStringList(str1).size() + "§7}", null, new String[0]);
/* 693 */         for (String str2 : configurationSection.getStringList(str1)) {
/* 694 */           MMOItems.print(null, "§8--§d +§e-§7" + str2, null, new String[0]);
/*     */         }
/*     */         
/* 697 */         ConfigurationSection configurationSection1 = getSection(configurationSection, str1);
/* 698 */         MMOItems.print(null, "§0--§8--§d-§7 Section §5" + configurationSection1.getCurrentPath(), null, new String[0]);
/* 699 */         for (String str2 : configurationSection1.getKeys(false)) {
/* 700 */           MMOItems.print(null, "§0--§8--§d +§7 " + str2, null, new String[0]);
/*     */           
/* 702 */           MMOItems.print(null, "§0--§8--§d-§e-§7 As List §5" + configurationSection1.getCurrentPath() + "." + str2 + "§7 {§d" + configurationSection1.getStringList(str2).size() + "§7}", null, new String[0]);
/* 703 */           for (String str3 : configurationSection1.getStringList(str2)) {
/* 704 */             MMOItems.print(null, "§0--§8--§d +§e-§7" + str3, null, new String[0]);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
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
/*     */   public static ConfigurationSection moveInput(@NotNull ConfigurationSection paramConfigurationSection, @NotNull String paramString) {
/*     */     ConfigurationSection configurationSection;
/* 729 */     if (paramConfigurationSection.isConfigurationSection(paramString)) {
/*     */ 
/*     */ 
/*     */       
/* 733 */       configurationSection = getSection(paramConfigurationSection, paramString);
/*     */ 
/*     */       
/* 736 */       String str1 = configurationSection.getString("input1");
/* 737 */       String str2 = configurationSection.getString("input2");
/*     */ 
/*     */ 
/*     */       
/* 741 */       if (str1 != null && str2 != null)
/*     */       {
/*     */ 
/*     */         
/* 745 */         configurationSection.set("input1", null);
/* 746 */         configurationSection.set("input2", null);
/* 747 */         configurationSection.set("input", poofFromLegacy(str1) + "|" + poofFromLegacy(str2));
/* 748 */         configurationSection.set("output", "v AIR 0|v AIR 0");
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 755 */       List list = paramConfigurationSection.getStringList(paramString);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 761 */       paramConfigurationSection.set(paramString, null);
/*     */ 
/*     */       
/* 764 */       configurationSection = getSection(paramConfigurationSection, paramString);
/* 765 */       configurationSection.set("input", list);
/*     */     } 
/*     */ 
/*     */     
/* 769 */     return configurationSection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static String poofFromLegacy(@Nullable String paramString) {
/* 780 */     if (paramString == null || "[]".equals(paramString))
/*     */     {
/* 782 */       return "v AIR - 1..";
/*     */     }
/*     */ 
/*     */     
/* 786 */     if (paramString.contains(" "))
/*     */     {
/* 788 */       return paramString;
/*     */     }
/*     */ 
/*     */     
/* 792 */     int i = paramString.indexOf(':');
/* 793 */     QuickNumberRange quickNumberRange = new QuickNumberRange(Double.valueOf(1.0D), null);
/* 794 */     if (i > 0) {
/*     */       
/* 796 */       String str = paramString.substring(i + 1);
/* 797 */       paramString = paramString.substring(0, i);
/* 798 */       Integer integer = SilentNumbers.IntegerParse(str);
/* 799 */       if (integer == null) {
/* 800 */         integer = Integer.valueOf(1);
/*     */       }
/* 802 */       quickNumberRange = new QuickNumberRange(Double.valueOf(integer.intValue()), null);
/*     */     } 
/*     */     
/* 805 */     if (paramString.contains(".")) {
/*     */ 
/*     */       
/* 808 */       String[] arrayOfString = paramString.split("\\.");
/*     */ 
/*     */ 
/*     */       
/* 812 */       return "m " + arrayOfString[0] + " " + arrayOfString[1] + " " + quickNumberRange;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 818 */     return "v " + paramString + " - " + quickNumberRange;
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
/*     */   @NotNull
/*     */   public static ProvidedUIFilter readIngredientFrom(@NotNull String paramString, @NotNull FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/* 845 */     Material material = null;
/*     */     try {
/* 847 */       material = Material.valueOf(paramString.toUpperCase().replace(" ", "_").replace("-", "_"));
/* 848 */     } catch (IllegalArgumentException illegalArgumentException) {}
/*     */     
/* 850 */     if (material != null) {
/*     */ 
/*     */       
/* 853 */       if (material.isAir()) {
/*     */         
/* 855 */         ProvidedUIFilter providedUIFilter2 = new ProvidedUIFilter((UIFilter)VanillaUIFilter.get(), "AIR", "0");
/* 856 */         providedUIFilter2.setAmountRange(new QuickNumberRange(null, null));
/* 857 */         return providedUIFilter2;
/*     */       } 
/*     */ 
/*     */       
/* 861 */       if (!material.isItem()) {
/* 862 */         throw new IllegalArgumentException("Invalid Ingredient $u" + paramString + "$b ($fNot an Item$b).");
/*     */       }
/*     */ 
/*     */       
/* 866 */       ProvidedUIFilter providedUIFilter1 = UIFilterManager.getUIFilter("v", material.toString(), "", "1..", paramFriendlyFeedbackProvider);
/*     */ 
/*     */       
/* 869 */       if (providedUIFilter1 != null)
/*     */       {
/*     */         
/* 872 */         return providedUIFilter1;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 877 */       paramFriendlyFeedbackProvider.sendTo(FriendlyFeedbackCategory.ERROR, MMOItems.getConsole());
/* 878 */       paramFriendlyFeedbackProvider.sendTo(FriendlyFeedbackCategory.FAILURE, MMOItems.getConsole());
/*     */ 
/*     */       
/* 881 */       throw new IllegalArgumentException("Invalid Ingredient $u" + paramString);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 890 */     if (paramString.contains(".") && !paramString.contains(" ")) {
/*     */ 
/*     */       
/* 893 */       String[] arrayOfString = paramString.split("\\.");
/*     */ 
/*     */       
/* 896 */       if (arrayOfString.length == 2) {
/*     */ 
/*     */         
/* 899 */         String str1 = arrayOfString[0];
/* 900 */         String str2 = arrayOfString[1];
/*     */ 
/*     */         
/* 903 */         ProvidedUIFilter providedUIFilter1 = UIFilterManager.getUIFilter("m", str1, str2, "1..", paramFriendlyFeedbackProvider);
/*     */ 
/*     */         
/* 906 */         if (providedUIFilter1 != null)
/*     */         {
/*     */           
/* 909 */           return providedUIFilter1;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 914 */         paramFriendlyFeedbackProvider.sendAllTo(MMOItems.getConsole());
/*     */ 
/*     */         
/* 917 */         throw new IllegalArgumentException("Invalid Ingredient $u" + paramString);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 925 */     ProvidedUIFilter providedUIFilter = UIFilterManager.getUIFilter(paramString, paramFriendlyFeedbackProvider);
/*     */ 
/*     */     
/* 928 */     if (providedUIFilter != null)
/*     */     {
/*     */       
/* 931 */       return providedUIFilter;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 936 */     paramFriendlyFeedbackProvider.sendAllTo(MMOItems.getConsole());
/*     */ 
/*     */     
/* 939 */     throw new IllegalArgumentException("Invalid Ingredient $u" + paramString);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\gui\RecipeMakerGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */