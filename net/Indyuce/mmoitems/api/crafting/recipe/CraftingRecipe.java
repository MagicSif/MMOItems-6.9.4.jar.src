/*     */ package net.Indyuce.mmoitems.api.crafting.recipe;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.ProvidedUIFilter;
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.UIFilterManager;
/*     */ import io.lumine.mythic.lib.api.util.SmartGive;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackCategory;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackMessage;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.crafting.ConfigMMOItem;
/*     */ import net.Indyuce.mmoitems.api.crafting.CraftingStation;
/*     */ import net.Indyuce.mmoitems.api.crafting.CraftingStatus;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.IngredientInventory;
/*     */ import net.Indyuce.mmoitems.api.crafting.trigger.Trigger;
/*     */ import net.Indyuce.mmoitems.api.event.PlayerUseCraftingStationEvent;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.item.util.ConfigItems;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class CraftingRecipe
/*     */   extends Recipe {
/*     */   public CraftingRecipe(@NotNull ConfigurationSection paramConfigurationSection) {
/*  37 */     super(paramConfigurationSection);
/*     */     
/*  39 */     this.craftingTime = paramConfigurationSection.getDouble("crafting-time");
/*     */ 
/*     */     
/*  42 */     String str1 = paramConfigurationSection.getString("output.item", "N/A");
/*  43 */     String str2 = paramConfigurationSection.getString("output.type", "N/A").toUpperCase().replace("-", "_").replace(" ", "_");
/*  44 */     String str3 = paramConfigurationSection.getString("output.id", "N/A").toUpperCase().replace("-", "_").replace(" ", "_");
/*     */ 
/*     */     
/*  47 */     FriendlyFeedbackProvider friendlyFeedbackProvider = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/*     */ 
/*     */     
/*  50 */     if (!"N/A".equals(str2) && !"N/A".equals(str3)) {
/*     */ 
/*     */       
/*  53 */       ProvidedUIFilter providedUIFilter = UIFilterManager.getUIFilter("m", str2, str3, paramConfigurationSection.getString("output.amount", "1"), friendlyFeedbackProvider);
/*     */ 
/*     */       
/*  56 */       if (providedUIFilter == null)
/*     */       {
/*     */         
/*  59 */         throw new IllegalArgumentException(SilentNumbers.collapseList(SilentNumbers.transcribeList(friendlyFeedbackProvider.getFeedbackOf(FriendlyFeedbackCategory.ERROR), paramObject -> (paramObject instanceof FriendlyFeedbackMessage) ? ((FriendlyFeedbackMessage)paramObject).forConsole((FriendlyFeedbackPalette)FFPMMOItems.get()) : ""), ""));
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  68 */       this.output = providedUIFilter;
/*     */     
/*     */     }
/*  71 */     else if (!"N/A".equals(str1)) {
/*     */ 
/*     */       
/*  74 */       ProvidedUIFilter providedUIFilter = UIFilterManager.getUIFilter(str1, friendlyFeedbackProvider);
/*     */ 
/*     */       
/*  77 */       if (providedUIFilter == null)
/*     */       {
/*     */         
/*  80 */         throw new IllegalArgumentException(SilentNumbers.collapseList(SilentNumbers.transcribeList(friendlyFeedbackProvider.getFeedbackOf(FriendlyFeedbackCategory.ERROR), paramObject -> (paramObject instanceof FriendlyFeedbackMessage) ? ((FriendlyFeedbackMessage)paramObject).forConsole((FriendlyFeedbackPalette)FFPMMOItems.get()) : ""), ""));
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  89 */       this.output = providedUIFilter;
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/*  95 */       throw new IllegalArgumentException(FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Config must contain a valid Type and ID, or a valid UIFilter. ", new String[0]));
/*     */     } 
/*     */ 
/*     */     
/*  99 */     if (!this.output.isValid(friendlyFeedbackProvider))
/*     */     {
/*     */       
/* 102 */       throw new IllegalArgumentException(SilentNumbers.collapseList(SilentNumbers.transcribeList(friendlyFeedbackProvider.getFeedbackOf(FriendlyFeedbackCategory.ERROR), paramObject -> (paramObject instanceof FriendlyFeedbackMessage) ? ((FriendlyFeedbackMessage)paramObject).forConsole((FriendlyFeedbackPalette)FFPMMOItems.get()) : ""), ""));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     if (this.output.getItemStack(friendlyFeedbackProvider) == null)
/*     */     {
/*     */       
/* 114 */       throw new IllegalArgumentException(SilentNumbers.collapseList(SilentNumbers.transcribeList(friendlyFeedbackProvider.getFeedbackOf(FriendlyFeedbackCategory.ERROR), paramObject -> (paramObject instanceof FriendlyFeedbackMessage) ? ((FriendlyFeedbackMessage)paramObject).forConsole((FriendlyFeedbackPalette)FFPMMOItems.get()) : ""), ""));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     if (this.output.getParent() instanceof net.Indyuce.mmoitems.api.crafting.MMOItemUIFilter) {
/*     */ 
/*     */       
/* 126 */       MMOItemTemplate mMOItemTemplate = MMOItems.plugin.getTemplates().getTemplate(MMOItems.plugin.getTypes().get(this.output.getArgument()), this.output.getData());
/*     */ 
/*     */       
/* 129 */       if (mMOItemTemplate == null)
/*     */       {
/*     */         
/* 132 */         throw new IllegalArgumentException(FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "This should be impossible, please contact $egunging$b: $fThe ProvidedUIFilter was flagged as 'valid' but clearly is not. $enet.Indyuce.mmoitems.api.crafting.recipe$b. ", new String[0]));
/*     */       }
/*     */ 
/*     */       
/* 136 */       this.identifiedMMO = new ConfigMMOItem(mMOItemTemplate, this.output.getAmount(1));
/*     */     } 
/*     */   }
/*     */   @NotNull
/*     */   public static final String UNSPECIFIED = "N/A"; private final double craftingTime;
/*     */   @NotNull
/*     */   private final ProvidedUIFilter output;
/*     */   @Nullable
/*     */   ConfigMMOItem identifiedMMO;
/*     */   
/*     */   public double getCraftingTime() {
/* 147 */     return this.craftingTime;
/*     */   }
/*     */   
/*     */   public boolean isInstant() {
/* 151 */     return (this.craftingTime <= 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ProvidedUIFilter getOutput() {
/* 159 */     return this.output;
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
/*     */   @NotNull
/*     */   public ItemStack getOutputItemStack(@Nullable RPGPlayer paramRPGPlayer) {
/* 176 */     if (this.identifiedMMO != null && paramRPGPlayer != null)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 183 */       return this.identifiedMMO.generate(paramRPGPlayer);
/*     */     }
/*     */ 
/*     */     
/* 187 */     return this.output.getItemStack(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getPreviewItemStack() {
/* 197 */     if (this.identifiedMMO != null)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 204 */       return this.identifiedMMO.getPreview();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     ItemStack itemStack = this.output.getParent().getDisplayStack(this.output.getArgument(), this.output.getData(), null);
/* 211 */     itemStack.setAmount(this.output.getAmount(1));
/* 212 */     ItemMeta itemMeta = itemStack.getItemMeta();
/* 213 */     if (itemMeta != null) {
/* 214 */       itemMeta.setDisplayName(SilentNumbers.getItemName(itemStack, false) + "§ʫ");
/* 215 */       itemStack.setItemMeta(itemMeta);
/*     */     } 
/* 217 */     return itemStack;
/*     */   }
/*     */   
/*     */   public int getOutputAmount() {
/* 221 */     return this.output.getAmount(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean whenUsed(PlayerData paramPlayerData, IngredientInventory paramIngredientInventory, CheckedRecipe paramCheckedRecipe, CraftingStation paramCraftingStation) {
/* 226 */     if (!paramPlayerData.isOnline()) {
/* 227 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 233 */     if (isInstant()) {
/* 234 */       ItemStack itemStack = hasOption(Recipe.RecipeOption.OUTPUT_ITEM) ? getOutputItemStack(paramPlayerData.getRPG()) : null;
/* 235 */       PlayerUseCraftingStationEvent playerUseCraftingStationEvent1 = new PlayerUseCraftingStationEvent(paramPlayerData, paramCraftingStation, paramCheckedRecipe, itemStack);
/* 236 */       Bukkit.getPluginManager().callEvent((Event)playerUseCraftingStationEvent1);
/* 237 */       if (playerUseCraftingStationEvent1.isCancelled()) {
/* 238 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 244 */       paramCheckedRecipe.getRecipe().whenClaimed().forEach(paramTrigger -> paramTrigger.whenCrafting(paramPlayerData));
/*     */       
/* 246 */       if (itemStack != null) {
/* 247 */         (new SmartGive(paramPlayerData.getPlayer())).give(new ItemStack[] { itemStack });
/*     */       }
/*     */       
/* 250 */       if (!hasOption(Recipe.RecipeOption.SILENT_CRAFT)) {
/* 251 */         paramPlayerData.getPlayer().playSound(paramPlayerData.getPlayer().getLocation(), paramCraftingStation.getSound(), 1.0F, 1.0F);
/*     */       }
/*     */       
/* 254 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 261 */     PlayerUseCraftingStationEvent playerUseCraftingStationEvent = new PlayerUseCraftingStationEvent(paramPlayerData, paramCraftingStation, paramCheckedRecipe);
/* 262 */     Bukkit.getPluginManager().callEvent((Event)playerUseCraftingStationEvent);
/* 263 */     if (playerUseCraftingStationEvent.isCancelled()) {
/* 264 */       return false;
/*     */     }
/*     */     
/* 267 */     if (!hasOption(Recipe.RecipeOption.SILENT_CRAFT)) {
/* 268 */       paramPlayerData.getPlayer().playSound(paramPlayerData.getPlayer().getLocation(), paramCraftingStation.getSound(), 1.0F, 1.0F);
/*     */     }
/* 270 */     paramPlayerData.getCrafting().getQueue(paramCraftingStation).add(this);
/*     */ 
/*     */     
/* 273 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(PlayerData paramPlayerData, IngredientInventory paramIngredientInventory, CheckedRecipe paramCheckedRecipe, CraftingStation paramCraftingStation) {
/* 278 */     if (isInstant()) {
/* 279 */       return true;
/*     */     }
/* 281 */     CraftingStatus.CraftingQueue craftingQueue = paramPlayerData.getCrafting().getQueue(paramCraftingStation);
/* 282 */     if (craftingQueue.isFull(paramCraftingStation)) {
/* 283 */       if (!paramPlayerData.isOnline()) {
/* 284 */         return false;
/*     */       }
/* 286 */       Message.CRAFTING_QUEUE_FULL.format(ChatColor.RED, new String[0]).send(paramPlayerData.getPlayer());
/* 287 */       paramPlayerData.getPlayer().playSound(paramPlayerData.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
/* 288 */       return false;
/*     */     } 
/* 290 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack display(CheckedRecipe paramCheckedRecipe) {
/* 295 */     return ConfigItems.CRAFTING_RECIPE_DISPLAY.newBuilder(paramCheckedRecipe).build();
/*     */   }
/*     */ 
/*     */   
/*     */   public CheckedRecipe evaluateRecipe(PlayerData paramPlayerData, IngredientInventory paramIngredientInventory) {
/* 300 */     return new CheckedRecipe(this, paramPlayerData, paramIngredientInventory);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\recipe\CraftingRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */