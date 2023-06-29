/*     */ package net.Indyuce.mmoitems.api.event;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.Indyuce.mmoitems.api.crafting.CraftingStation;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.CheckedRecipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.CraftingRecipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.Recipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.UpgradingRecipe;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ public class PlayerUseCraftingStationEvent
/*     */   extends PlayerDataEvent
/*     */ {
/*     */   private final CraftingStation station;
/*     */   private final Recipe recipe;
/*     */   private final StationAction action;
/*     */   @Nullable
/*     */   private final CheckedRecipe recipeInfo;
/*     */   @Nullable
/*     */   private final ItemStack result;
/*  25 */   private static final HandlerList handlers = new HandlerList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlayerUseCraftingStationEvent(PlayerData paramPlayerData, CraftingStation paramCraftingStation, CheckedRecipe paramCheckedRecipe) {
/*  36 */     this(paramPlayerData, paramCraftingStation, paramCheckedRecipe, paramCheckedRecipe.getRecipe(), null, StationAction.INTERACT_WITH_RECIPE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlayerUseCraftingStationEvent(PlayerData paramPlayerData, CraftingStation paramCraftingStation, UpgradingRecipe.CheckedUpgradingRecipe paramCheckedUpgradingRecipe) {
/*  47 */     this(paramPlayerData, paramCraftingStation, (CheckedRecipe)paramCheckedUpgradingRecipe, paramCheckedUpgradingRecipe.getRecipe(), null, StationAction.UPGRADE_RECIPE);
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
/*     */   public PlayerUseCraftingStationEvent(PlayerData paramPlayerData, CraftingStation paramCraftingStation, CheckedRecipe paramCheckedRecipe, ItemStack paramItemStack) {
/*  59 */     this(paramPlayerData, paramCraftingStation, paramCheckedRecipe, paramCheckedRecipe.getRecipe(), paramItemStack, StationAction.INSTANT_RECIPE);
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
/*     */   public PlayerUseCraftingStationEvent(PlayerData paramPlayerData, CraftingStation paramCraftingStation, Recipe paramRecipe, ItemStack paramItemStack) {
/*  71 */     this(paramPlayerData, paramCraftingStation, null, paramRecipe, paramItemStack, StationAction.CRAFTING_QUEUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlayerUseCraftingStationEvent(PlayerData paramPlayerData, CraftingStation paramCraftingStation, Recipe paramRecipe) {
/*  82 */     this(paramPlayerData, paramCraftingStation, null, paramRecipe, null, StationAction.CANCEL_QUEUE);
/*     */   }
/*     */   
/*     */   private PlayerUseCraftingStationEvent(PlayerData paramPlayerData, CraftingStation paramCraftingStation, CheckedRecipe paramCheckedRecipe, Recipe paramRecipe, ItemStack paramItemStack, StationAction paramStationAction) {
/*  86 */     super(paramPlayerData);
/*     */     
/*  88 */     this.station = paramCraftingStation;
/*  89 */     this.recipe = paramRecipe;
/*  90 */     this.recipeInfo = paramCheckedRecipe;
/*  91 */     this.result = paramItemStack;
/*  92 */     this.action = paramStationAction;
/*     */   }
/*     */   
/*     */   public CraftingStation getStation() {
/*  96 */     return this.station;
/*     */   }
/*     */   
/*     */   public boolean hasRecipeInfo() {
/* 100 */     return (this.recipeInfo != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CheckedRecipe getRecipeInfo() {
/* 108 */     Validate.notNull(Boolean.valueOf((this.action == StationAction.INSTANT_RECIPE || this.action == StationAction.INTERACT_WITH_RECIPE)), "No recipe info is provided with " + this.action.name());
/* 109 */     return this.recipeInfo;
/*     */   }
/*     */   
/*     */   public boolean hasResult() {
/* 113 */     return (this.result != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getResult() {
/* 121 */     Validate.notNull(Boolean.valueOf((this.action == StationAction.INSTANT_RECIPE || this.action == StationAction.CRAFTING_QUEUE)), "No result item is provided with " + this.action.name());
/* 122 */     return this.result;
/*     */   }
/*     */ 
/*     */   
/*     */   public Recipe getRecipe() {
/* 127 */     return this.recipe;
/*     */   }
/*     */   
/*     */   public StationAction getInteraction() {
/* 131 */     return this.action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean isInstant() {
/* 140 */     return (this.recipe instanceof CraftingRecipe && ((CraftingRecipe)this.recipe).isInstant());
/*     */   }
/*     */   
/*     */   public HandlerList getHandlers() {
/* 144 */     return handlers;
/*     */   }
/*     */   
/*     */   public static HandlerList getHandlerList() {
/* 148 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum StationAction
/*     */   {
/* 156 */     UPGRADE_RECIPE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     INTERACT_WITH_RECIPE,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     INSTANT_RECIPE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     CRAFTING_QUEUE,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     CANCEL_QUEUE;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\PlayerUseCraftingStationEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */