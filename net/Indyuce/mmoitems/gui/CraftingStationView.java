/*     */ package net.Indyuce.mmoitems.gui;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.util.SmartGive;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.CustomSound;
/*     */ import net.Indyuce.mmoitems.api.crafting.CraftingStation;
/*     */ import net.Indyuce.mmoitems.api.crafting.CraftingStatus;
/*     */ import net.Indyuce.mmoitems.api.crafting.Layout;
/*     */ import net.Indyuce.mmoitems.api.crafting.condition.CheckedCondition;
/*     */ import net.Indyuce.mmoitems.api.crafting.condition.Condition;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.CheckedIngredient;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.Ingredient;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.IngredientInventory;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.CheckedRecipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.CraftingRecipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.Recipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.trigger.Trigger;
/*     */ import net.Indyuce.mmoitems.api.event.PlayerUseCraftingStationEvent;
/*     */ import net.Indyuce.mmoitems.api.item.util.ConfigItems;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.comp.eco.MoneyCondition;
/*     */ import net.Indyuce.mmoitems.listener.CustomSoundListener;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ public class CraftingStationView extends PluginInventory {
/*     */   private final CraftingStation station;
/*     */   private final Layout layout;
/*     */   
/*     */   public CraftingStationView(Player paramPlayer, CraftingStation paramCraftingStation, int paramInt) {
/*  46 */     super(paramPlayer);
/*     */     
/*  48 */     this.station = paramCraftingStation;
/*  49 */     this.layout = paramCraftingStation.getLayout();
/*  50 */     this.page = paramInt;
/*     */     
/*  52 */     updateData();
/*     */   }
/*     */   private List<CheckedRecipe> recipes; private IngredientInventory ingredients; private int queueOffset;
/*     */   public CraftingStation getStation() {
/*  56 */     return this.station;
/*     */   }
/*     */   
/*     */   void updateData() {
/*  60 */     this.ingredients = new IngredientInventory(this.player);
/*  61 */     this.recipes = this.station.getAvailableRecipes(this.playerData, this.ingredients);
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getInventory() {
/*  66 */     String str = MythicLib.plugin.parseColors(MythicLib.plugin.getPlaceholderParser().parse((OfflinePlayer)getPlayer(), this.station.getName().replace("#page#", "" + this.page).replace("#max#", "" + this.station.getMaxPage())));
/*  67 */     final Inventory inv = Bukkit.createInventory(this, this.layout.getSize(), str);
/*  68 */     int i = (this.page - 1) * this.layout.getRecipeSlots().size(), j = this.page * this.layout.getRecipeSlots().size();
/*  69 */     for (int k = i; k < j; k++) {
/*  70 */       if (k >= this.recipes.size()) {
/*  71 */         if (this.station.getItemOptions().hasNoRecipe()) {
/*  72 */           inventory.setItem(((Integer)this.layout.getRecipeSlots().get(k - i)).intValue(), this.station.getItemOptions().getNoRecipe());
/*     */         }
/*     */       } else {
/*     */         
/*  76 */         inventory.setItem(((Integer)this.layout.getRecipeSlots().get(k - i)).intValue(), ((CheckedRecipe)this.recipes.get(k)).display());
/*     */       } 
/*     */     } 
/*  79 */     if (j < this.recipes.size())
/*  80 */       inventory.setItem(this.layout.getRecipeNextSlot(), ConfigItems.NEXT_PAGE.getItem()); 
/*  81 */     if (this.page > 1) {
/*  82 */       inventory.setItem(this.layout.getRecipePreviousSlot(), ConfigItems.PREVIOUS_PAGE.getItem());
/*     */     }
/*  84 */     final CraftingStatus.CraftingQueue queue = this.playerData.getCrafting().getQueue(this.station); int m;
/*  85 */     for (m = this.queueOffset; m < this.queueOffset + this.layout.getQueueSlots().size(); m++) {
/*  86 */       if (m >= craftingQueue.getCrafts().size()) {
/*  87 */         if (this.station.getItemOptions().hasNoQueueItem()) {
/*  88 */           inventory.setItem(((Integer)this.layout.getQueueSlots().get(m - this.queueOffset)).intValue(), this.station.getItemOptions().getNoQueueItem());
/*     */         }
/*     */       } else {
/*     */         
/*  92 */         inventory.setItem(((Integer)this.layout.getQueueSlots().get(m - this.queueOffset)).intValue(), ConfigItems.QUEUE_ITEM_DISPLAY
/*  93 */             .newBuilder(craftingQueue.getCrafts().get(m), m + 1).build());
/*     */       } 
/*  95 */     }  if (this.queueOffset + this.layout.getQueueSlots().size() < craftingQueue.getCrafts().size())
/*  96 */       inventory.setItem(this.layout.getQueueNextSlot(), ConfigItems.NEXT_IN_QUEUE.getItem()); 
/*  97 */     if (this.queueOffset > 0) {
/*  98 */       inventory.setItem(this.layout.getQueuePreviousSlot(), ConfigItems.PREVIOUS_IN_QUEUE.getItem());
/*     */     }
/* 100 */     (new BukkitRunnable()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void run()
/*     */         {
/* 108 */           if (inv.getViewers().size() < 1) {
/* 109 */             cancel();
/*     */             
/*     */             return;
/*     */           } 
/* 113 */           for (int i = CraftingStationView.this.queueOffset; i < CraftingStationView.this.queueOffset + CraftingStationView.this.layout.getQueueSlots().size(); i++) {
/* 114 */             if (i >= queue.getCrafts().size()) {
/* 115 */               inv.setItem(((Integer)CraftingStationView.this.layout.getQueueSlots().get(i - CraftingStationView.this.queueOffset)).intValue(), 
/* 116 */                   CraftingStationView.this.station.getItemOptions().hasNoQueueItem() ? CraftingStationView.this.station.getItemOptions().getNoQueueItem() : null);
/*     */             } else {
/* 118 */               inv.setItem(((Integer)CraftingStationView.this.layout.getQueueSlots().get(i - CraftingStationView.this.queueOffset)).intValue(), ConfigItems.QUEUE_ITEM_DISPLAY
/* 119 */                   .newBuilder(queue.getCrafts().get(i), i + 1).build());
/*     */             } 
/* 121 */           }  } }).runTaskTimer((Plugin)MMOItems.plugin, 0L, 20L);
/*     */     
/* 123 */     if (this.station.getItemOptions().hasFill())
/* 124 */       for (m = 0; m < this.layout.getSize(); m++) {
/* 125 */         if (inventory.getItem(m) == null || inventory.getItem(m).getType() == Material.AIR)
/* 126 */           inventory.setItem(m, this.station.getItemOptions().getFill()); 
/*     */       }  
/* 128 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 133 */     if (!this.playerData.isOnline()) {
/*     */       return;
/*     */     }
/* 136 */     paramInventoryClickEvent.setCancelled(true);
/* 137 */     if (!MMOUtils.isMetaItem(paramInventoryClickEvent.getCurrentItem(), false)) {
/*     */       return;
/*     */     }
/* 140 */     NBTItem nBTItem1 = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramInventoryClickEvent.getCurrentItem());
/* 141 */     if (nBTItem1.getString("ItemId").equals("PREVIOUS_IN_QUEUE")) {
/* 142 */       this.queueOffset--;
/* 143 */       open();
/*     */       
/*     */       return;
/*     */     } 
/* 147 */     if (nBTItem1.getString("ItemId").equals("NEXT_IN_QUEUE")) {
/* 148 */       this.queueOffset++;
/* 149 */       open();
/*     */       
/*     */       return;
/*     */     } 
/* 153 */     if (nBTItem1.getString("ItemId").equals("NEXT_PAGE")) {
/* 154 */       this.page++;
/* 155 */       open();
/*     */       
/*     */       return;
/*     */     } 
/* 159 */     if (nBTItem1.getString("ItemId").equals("PREVIOUS_PAGE")) {
/* 160 */       this.page--;
/* 161 */       open();
/*     */       
/*     */       return;
/*     */     } 
/* 165 */     NBTItem nBTItem2 = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramInventoryClickEvent.getCurrentItem());
/* 166 */     String str = nBTItem2.getString("recipeId");
/* 167 */     if (!str.isEmpty()) {
/* 168 */       CheckedRecipe checkedRecipe = getRecipe(str);
/* 169 */       if (paramInventoryClickEvent.isRightClick()) {
/* 170 */         (new CraftingStationPreview(this, checkedRecipe)).open();
/*     */       } else {
/* 172 */         processRecipe(checkedRecipe);
/* 173 */         open();
/*     */       } 
/*     */     } 
/*     */     
/* 177 */     if (!(str = nBTItem2.getString("queueId")).isEmpty()) {
/* 178 */       CraftingStatus.CraftingQueue.CraftingInfo craftingInfo = this.playerData.getCrafting().getQueue(this.station).getCraft(UUID.fromString(str));
/* 179 */       CraftingRecipe craftingRecipe = craftingInfo.getRecipe();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 185 */       if (craftingInfo.isReady()) {
/* 186 */         ItemStack itemStack = craftingRecipe.hasOption(Recipe.RecipeOption.OUTPUT_ITEM) ? craftingRecipe.getOutputItemStack(this.playerData.getRPG()) : null;
/*     */         
/* 188 */         PlayerUseCraftingStationEvent playerUseCraftingStationEvent = new PlayerUseCraftingStationEvent(this.playerData, this.station, (Recipe)craftingRecipe, itemStack);
/* 189 */         Bukkit.getPluginManager().callEvent((Event)playerUseCraftingStationEvent);
/* 190 */         if (playerUseCraftingStationEvent.isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/* 194 */         this.playerData.getCrafting().getQueue(this.station).remove(craftingInfo);
/* 195 */         craftingRecipe.whenClaimed().forEach(paramTrigger -> paramTrigger.whenCrafting(this.playerData));
/*     */ 
/*     */         
/* 198 */         CustomSoundListener.playSound(itemStack, CustomSound.ON_CRAFT, this.player);
/* 199 */         if (!craftingRecipe.hasOption(Recipe.RecipeOption.SILENT_CRAFT)) {
/* 200 */           this.player.playSound(this.player.getLocation(), this.station.getSound(), 1.0F, 1.0F);
/*     */         }
/* 202 */         if (itemStack != null) {
/* 203 */           (new SmartGive(this.player)).give(new ItemStack[] { itemStack });
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 210 */         PlayerUseCraftingStationEvent playerUseCraftingStationEvent = new PlayerUseCraftingStationEvent(this.playerData, this.station, (Recipe)craftingRecipe);
/* 211 */         Bukkit.getPluginManager().callEvent((Event)playerUseCraftingStationEvent);
/* 212 */         if (playerUseCraftingStationEvent.isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/* 216 */         this.playerData.getCrafting().getQueue(this.station).remove(craftingInfo);
/* 217 */         craftingRecipe.whenCanceled().forEach(paramTrigger -> paramTrigger.whenCrafting(this.playerData));
/*     */ 
/*     */         
/* 220 */         if (!craftingRecipe.hasOption(Recipe.RecipeOption.SILENT_CRAFT)) {
/* 221 */           this.player.playSound(this.player.getLocation(), this.station.getSound(), 1.0F, 1.0F);
/*     */         }
/*     */         
/* 224 */         for (Ingredient ingredient : craftingInfo.getRecipe().getIngredients()) {
/* 225 */           (new SmartGive(this.player)).give(new ItemStack[] { ingredient.generateItemStack(this.playerData.getRPG(), false) });
/*     */         } 
/*     */         
/* 228 */         craftingRecipe.getConditions()
/* 229 */           .stream()
/* 230 */           .filter(paramCondition -> paramCondition instanceof MoneyCondition)
/* 231 */           .map(paramCondition -> (MoneyCondition)paramCondition)
/* 232 */           .forEach(paramMoneyCondition -> MMOItems.plugin.getVault().getEconomy().depositPlayer((OfflinePlayer)this.player, paramMoneyCondition.getAmount()));
/*     */       } 
/*     */       
/* 235 */       updateData();
/* 236 */       open();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void processRecipe(CheckedRecipe paramCheckedRecipe) {
/* 241 */     if (!paramCheckedRecipe.areConditionsMet()) {
/* 242 */       Message.CONDITIONS_NOT_MET.format(ChatColor.RED, new String[0]).send(this.player);
/* 243 */       this.player.playSound(this.player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
/*     */       
/*     */       return;
/*     */     } 
/* 247 */     if (!paramCheckedRecipe.allIngredientsHad()) {
/* 248 */       Message.NOT_ENOUGH_MATERIALS.format(ChatColor.RED, new String[0]).send(this.player);
/* 249 */       this.player.playSound(this.player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
/*     */       
/*     */       return;
/*     */     } 
/* 253 */     if (!paramCheckedRecipe.getRecipe().canUse(this.playerData, this.ingredients, paramCheckedRecipe, this.station)) {
/* 254 */       updateData();
/*     */       
/*     */       return;
/*     */     } 
/* 258 */     if (paramCheckedRecipe.getRecipe().whenUsed(this.playerData, this.ingredients, paramCheckedRecipe, this.station)) {
/* 259 */       paramCheckedRecipe.getIngredients().forEach(paramCheckedIngredient -> paramCheckedIngredient.takeAway());
/* 260 */       paramCheckedRecipe.getConditions().forEach(paramCheckedCondition -> paramCheckedCondition.getCondition().whenCrafting(this.playerData));
/* 261 */       paramCheckedRecipe.getRecipe().whenUsed().forEach(paramTrigger -> paramTrigger.whenCrafting(this.playerData));
/*     */       
/* 263 */       updateData();
/*     */     } 
/*     */   }
/*     */   
/*     */   private CheckedRecipe getRecipe(String paramString) {
/* 268 */     for (CheckedRecipe checkedRecipe : this.recipes) {
/* 269 */       if (checkedRecipe.getRecipe().getId().equals(paramString))
/* 270 */         return checkedRecipe; 
/* 271 */     }  return null;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\CraftingStationView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */