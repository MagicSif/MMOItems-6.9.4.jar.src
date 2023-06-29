/*     */ package net.Indyuce.mmoitems.api.crafting.recipe;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import java.util.Random;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.api.crafting.ConfigMMOItem;
/*     */ import net.Indyuce.mmoitems.api.crafting.CraftingStation;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.CheckedIngredient;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.Ingredient;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.MMOItemIngredient;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.IngredientInventory;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.PlayerIngredient;
/*     */ import net.Indyuce.mmoitems.api.event.PlayerUseCraftingStationEvent;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.util.ConfigItems;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.stat.data.UpgradeData;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class UpgradingRecipe extends Recipe {
/*     */   private final ConfigMMOItem item;
/*  30 */   private static final Random random = new Random(); private final Ingredient ingredient;
/*     */   
/*     */   public UpgradingRecipe(ConfigurationSection paramConfigurationSection) {
/*  33 */     super(paramConfigurationSection);
/*     */ 
/*     */     
/*  36 */     this.item = new ConfigMMOItem(paramConfigurationSection.getConfigurationSection("item"));
/*  37 */     this.ingredient = (Ingredient)new MMOItemIngredient(this.item);
/*     */   }
/*     */   
/*     */   public ConfigMMOItem getItem() {
/*  41 */     return this.item;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean whenUsed(PlayerData paramPlayerData, IngredientInventory paramIngredientInventory, CheckedRecipe paramCheckedRecipe, CraftingStation paramCraftingStation) {
/*  46 */     if (!paramPlayerData.isOnline()) {
/*  47 */       return false;
/*     */     }
/*  49 */     CheckedUpgradingRecipe checkedUpgradingRecipe = (CheckedUpgradingRecipe)paramCheckedRecipe;
/*  50 */     PlayerUseCraftingStationEvent playerUseCraftingStationEvent = new PlayerUseCraftingStationEvent(paramPlayerData, paramCraftingStation, checkedUpgradingRecipe);
/*  51 */     Bukkit.getPluginManager().callEvent((Event)playerUseCraftingStationEvent);
/*  52 */     if (playerUseCraftingStationEvent.isCancelled()) {
/*  53 */       return false;
/*     */     }
/*     */     
/*  56 */     checkedUpgradingRecipe.getUpgradeData().upgrade((MMOItem)checkedUpgradingRecipe.getMMOItem());
/*  57 */     checkedUpgradingRecipe.getUpgraded().setItemMeta(checkedUpgradingRecipe.getMMOItem().newBuilder().build().getItemMeta());
/*     */     
/*  59 */     Message.UPGRADE_SUCCESS.format(ChatColor.YELLOW, new String[] { "#item#", MMOUtils.getDisplayName(checkedUpgradingRecipe.getUpgraded()) }).send(paramPlayerData.getPlayer());
/*     */ 
/*     */     
/*  62 */     if (!hasOption(Recipe.RecipeOption.SILENT_CRAFT)) {
/*  63 */       paramPlayerData.getPlayer().playSound(paramPlayerData.getPlayer().getLocation(), paramCraftingStation.getSound(), 1.0F, 1.0F);
/*     */     }
/*     */     
/*  66 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUse(PlayerData paramPlayerData, IngredientInventory paramIngredientInventory, CheckedRecipe paramCheckedRecipe, CraftingStation paramCraftingStation) {
/*  73 */     CheckedIngredient checkedIngredient = paramIngredientInventory.findMatching(this.ingredient);
/*  74 */     if (!checkedIngredient.isHad()) {
/*  75 */       if (!paramPlayerData.isOnline()) {
/*  76 */         return false;
/*     */       }
/*  78 */       Message.NOT_HAVE_ITEM_UPGRADE.format(ChatColor.RED, new String[0]).send(paramPlayerData.getPlayer());
/*  79 */       paramPlayerData.getPlayer().playSound(paramPlayerData.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 2.0F);
/*  80 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  84 */     NBTItem nBTItem = NBTItem.get(((PlayerIngredient)checkedIngredient.getFound().stream().findFirst().get()).getItem());
/*     */ 
/*     */     
/*  87 */     CheckedUpgradingRecipe checkedUpgradingRecipe = (CheckedUpgradingRecipe)paramCheckedRecipe;
/*  88 */     if (!(checkedUpgradingRecipe.mmoitem = new LiveMMOItem(nBTItem)).hasData(ItemStats.UPGRADE)) {
/*  89 */       return false;
/*     */     }
/*     */     
/*  92 */     if (!(checkedUpgradingRecipe.upgradeData = (UpgradeData)checkedUpgradingRecipe.getMMOItem().getData(ItemStats.UPGRADE)).canLevelUp()) {
/*  93 */       if (!paramPlayerData.isOnline()) {
/*  94 */         return false;
/*     */       }
/*  96 */       Message.MAX_UPGRADES_HIT.format(ChatColor.RED, new String[0]).send(paramPlayerData.getPlayer());
/*  97 */       paramPlayerData.getPlayer().playSound(paramPlayerData.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 2.0F);
/*  98 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 102 */     if (random.nextDouble() > checkedUpgradingRecipe.getUpgradeData().getSuccess()) {
/*     */ 
/*     */       
/* 105 */       if (checkedUpgradingRecipe.getUpgradeData().destroysOnFail()) {
/* 106 */         checkedUpgradingRecipe.getUpgraded().setAmount(checkedUpgradingRecipe.getUpgraded().getAmount() - 1);
/*     */       }
/*     */       
/* 109 */       checkedUpgradingRecipe.getIngredients().forEach(paramCheckedIngredient -> paramCheckedIngredient.takeAway());
/*     */       
/* 111 */       if (!paramPlayerData.isOnline()) {
/* 112 */         return false;
/*     */       }
/* 114 */       Message.UPGRADE_FAIL_STATION.format(ChatColor.RED, new String[0]).send(paramPlayerData.getPlayer());
/* 115 */       paramPlayerData.getPlayer().playSound(paramPlayerData.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 2.0F);
/* 116 */       return false;
/*     */     } 
/*     */     
/* 119 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack display(CheckedRecipe paramCheckedRecipe) {
/* 124 */     return ConfigItems.UPGRADING_RECIPE_DISPLAY.newBuilder(paramCheckedRecipe).build();
/*     */   }
/*     */ 
/*     */   
/*     */   public CheckedRecipe evaluateRecipe(PlayerData paramPlayerData, IngredientInventory paramIngredientInventory) {
/* 129 */     return new CheckedUpgradingRecipe(this, paramPlayerData, paramIngredientInventory);
/*     */   }
/*     */ 
/*     */   
/*     */   public class CheckedUpgradingRecipe
/*     */     extends CheckedRecipe
/*     */   {
/*     */     private LiveMMOItem mmoitem;
/*     */     
/*     */     private UpgradeData upgradeData;
/*     */     
/*     */     public CheckedUpgradingRecipe(Recipe param1Recipe, PlayerData param1PlayerData, IngredientInventory param1IngredientInventory) {
/* 141 */       super(param1Recipe, param1PlayerData, param1IngredientInventory);
/*     */     }
/*     */     
/*     */     public UpgradeData getUpgradeData() {
/* 145 */       return this.upgradeData;
/*     */     }
/*     */     
/*     */     public LiveMMOItem getMMOItem() {
/* 149 */       return this.mmoitem;
/*     */     }
/*     */     
/*     */     public ItemStack getUpgraded() {
/* 153 */       return this.mmoitem.getNBT().getItem();
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\recipe\UpgradingRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */