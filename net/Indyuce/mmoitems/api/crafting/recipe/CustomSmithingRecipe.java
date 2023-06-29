/*     */ package net.Indyuce.mmoitems.api.crafting.recipe;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.crafting.ingredients.MythicBlueprintInventory;
/*     */ import io.lumine.mythic.lib.api.crafting.ingredients.MythicIngredient;
/*     */ import io.lumine.mythic.lib.api.crafting.ingredients.MythicRecipeIngredient;
/*     */ import io.lumine.mythic.lib.api.crafting.ingredients.MythicRecipeInventory;
/*     */ import io.lumine.mythic.lib.api.crafting.ingredients.ShapedIngredient;
/*     */ import io.lumine.mythic.lib.api.crafting.outputs.MRORecipe;
/*     */ import io.lumine.mythic.lib.api.crafting.outputs.MythicRecipeOutput;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.MythicCachedResult;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.MythicRecipe;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.vmp.VanillaInventoryMapping;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.util.Ref;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackCategory;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.interaction.GemStone;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.stat.Enchants;
/*     */ import net.Indyuce.mmoitems.stat.data.EnchantListData;
/*     */ import net.Indyuce.mmoitems.stat.data.GemSocketsData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.util.Pair;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class CustomSmithingRecipe
/*     */   extends MythicRecipeOutput {
/*     */   @Nullable
/*     */   MythicRecipe mainInputConsumption;
/*     */   @Nullable
/*     */   MythicRecipe ingotInputConsumption;
/*     */   int outputAmount;
/*     */   
/*     */   public CustomSmithingRecipe(@NotNull MMOItemTemplate paramMMOItemTemplate, boolean paramBoolean, @NotNull SmithingCombinationType paramSmithingCombinationType1, @NotNull SmithingCombinationType paramSmithingCombinationType2, int paramInt) {
/*  54 */     this.outputItem = paramMMOItemTemplate;
/*  55 */     this.dropGemstones = paramBoolean;
/*  56 */     this.enchantmentTreatment = paramSmithingCombinationType1;
/*  57 */     this.upgradeTreatment = paramSmithingCombinationType2;
/*  58 */     this.outputAmount = paramInt;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   final MMOItemTemplate outputItem;
/*     */   
/*     */   final boolean dropGemstones;
/*     */   @NotNull
/*     */   final SmithingCombinationType enchantmentTreatment;
/*     */   @NotNull
/*     */   final SmithingCombinationType upgradeTreatment;
/*     */   
/*     */   @Nullable
/*     */   public MythicRecipe getMainInputConsumption() {
/*  73 */     return this.mainInputConsumption;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMainInputConsumption(@Nullable MythicRecipe paramMythicRecipe) {
/*  78 */     this.mainInputConsumption = nullifyIfEmpty(paramMythicRecipe);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasInputConsumption() {
/*  84 */     return (this.ingotInputConsumption != null || this.mainInputConsumption != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MythicRecipe nullifyIfEmpty(@Nullable MythicRecipe paramMythicRecipe) {
/*  95 */     if (paramMythicRecipe == null) return null;
/*     */ 
/*     */     
/*  98 */     for (MythicRecipeIngredient mythicRecipeIngredient : paramMythicRecipe.getIngredients()) {
/*  99 */       if (mythicRecipeIngredient != null && 
/* 100 */         mythicRecipeIngredient.getIngredient().isDefinesItem()) return paramMythicRecipe;
/*     */     
/*     */     } 
/* 103 */     return null;
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
/*     */   @Nullable
/*     */   public MythicRecipe getIngotInputConsumption() {
/* 116 */     return this.ingotInputConsumption;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIngotInputConsumption(@Nullable MythicRecipe paramMythicRecipe) {
/* 121 */     this.ingotInputConsumption = nullifyIfEmpty(paramMythicRecipe);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   MythicRecipeInventory generateResultOf(@NotNull MythicRecipe paramMythicRecipe) {
/* 133 */     HashMap<Object, Object> hashMap = new HashMap<>();
/*     */ 
/*     */     
/* 136 */     for (MythicRecipeIngredient mythicRecipeIngredient : paramMythicRecipe.getIngredients()) {
/*     */ 
/*     */       
/* 139 */       if (mythicRecipeIngredient == null) {
/*     */         continue;
/*     */       }
/* 142 */       ShapedIngredient shapedIngredient = (ShapedIngredient)mythicRecipeIngredient;
/* 143 */       MythicIngredient mythicIngredient = mythicRecipeIngredient.getIngredient();
/*     */ 
/*     */       
/* 146 */       if (!mythicIngredient.isDefinesItem()) {
/*     */         continue;
/*     */       }
/* 149 */       FriendlyFeedbackProvider friendlyFeedbackProvider = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/* 150 */       friendlyFeedbackProvider.activatePrefix(true, "Recipe of " + getOutputItem().getType().getId() + " " + getOutputItem().getId());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 162 */       ItemStack itemStack = mythicRecipeIngredient.getIngredient().getRandomSubstituteItem(friendlyFeedbackProvider);
/*     */ 
/*     */       
/* 165 */       if (itemStack != null) {
/*     */ 
/*     */         
/* 168 */         ItemStack[] arrayOfItemStack = (ItemStack[])hashMap.get(Integer.valueOf(-shapedIngredient.getVerticalOffset()));
/* 169 */         if (arrayOfItemStack == null) arrayOfItemStack = new ItemStack[shapedIngredient.getHorizontalOffset() + 1]; 
/* 170 */         if (arrayOfItemStack.length < shapedIngredient.getHorizontalOffset() + 1) {
/* 171 */           ItemStack[] arrayOfItemStack1 = new ItemStack[shapedIngredient.getHorizontalOffset() + 1];
/*     */           
/* 173 */           for (byte b = 0; b < arrayOfItemStack.length; ) { arrayOfItemStack1[b] = arrayOfItemStack[b]; b++; }
/* 174 */            arrayOfItemStack = arrayOfItemStack1;
/*     */         } 
/*     */ 
/*     */         
/* 178 */         arrayOfItemStack[shapedIngredient.getHorizontalOffset()] = itemStack;
/*     */ 
/*     */         
/* 181 */         hashMap.put(Integer.valueOf(-shapedIngredient.getVerticalOffset()), arrayOfItemStack);
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 187 */       friendlyFeedbackProvider.sendTo(FriendlyFeedbackCategory.ERROR, MythicLib.plugin.getServer().getConsoleSender());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 192 */     MythicRecipeInventory mythicRecipeInventory = new MythicRecipeInventory();
/* 193 */     for (Integer integer : hashMap.keySet()) mythicRecipeInventory.setRow(integer.intValue(), (ItemStack[])hashMap.get(integer));
/*     */ 
/*     */     
/* 196 */     return mythicRecipeInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOutputAmount() {
/* 207 */     return this.outputAmount;
/*     */   }
/*     */   
/*     */   public void setOutputAmount(int paramInt) {
/* 211 */     this.outputAmount = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MMOItemTemplate getOutputItem() {
/* 220 */     return this.outputItem;
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
/*     */   public SmithingCombinationType getEnchantmentTreatment() {
/* 233 */     return this.enchantmentTreatment;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDropGemstones() {
/* 238 */     return this.dropGemstones;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public SmithingCombinationType getUpgradeTreatment() {
/* 244 */     return this.upgradeTreatment;
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
/*     */   @Nullable
/*     */   MMOItem fromStack(@Nullable ItemStack paramItemStack) {
/* 258 */     if (paramItemStack == null) return null;
/*     */     
/* 260 */     NBTItem nBTItem = NBTItem.get(paramItemStack);
/* 261 */     if (MMOItems.getType(nBTItem) != null) return (MMOItem)new LiveMMOItem(paramItemStack);
/*     */     
/* 263 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   ItemStack firstFromFirstSide(@NotNull MythicBlueprintInventory paramMythicBlueprintInventory) {
/* 273 */     if (paramMythicBlueprintInventory.getSideInventoryNames().size() == 0) return null;
/*     */ 
/*     */     
/* 276 */     return paramMythicBlueprintInventory.getSideInventory(paramMythicBlueprintInventory.getSideInventoryNames().get(0)).getFirst();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MythicRecipeInventory applyDisplay(@NotNull MythicBlueprintInventory paramMythicBlueprintInventory, @NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull VanillaInventoryMapping paramVanillaInventoryMapping) {
/* 283 */     if (!(paramInventoryClickEvent.getWhoClicked() instanceof Player)) return paramMythicBlueprintInventory.getResultInventory();
/*     */ 
/*     */     
/* 286 */     MythicBlueprintInventory mythicBlueprintInventory = paramVanillaInventoryMapping.extractFrom(paramInventoryClickEvent.getView().getTopInventory());
/* 287 */     ItemStack itemStack1 = mythicBlueprintInventory.getMainInventory().getFirst();
/* 288 */     ItemStack itemStack2 = firstFromFirstSide(mythicBlueprintInventory);
/*     */ 
/*     */     
/* 291 */     MMOItem mMOItem = fromCombinationWith(itemStack1, itemStack2, (Player)paramInventoryClickEvent.getWhoClicked(), (Ref<ArrayList<ItemStack>>)null);
/*     */ 
/*     */     
/* 294 */     MythicRecipeInventory mythicRecipeInventory = paramMythicBlueprintInventory.getResultInventory().clone();
/* 295 */     mythicRecipeInventory.setItemAt(paramVanillaInventoryMapping.getResultWidth(paramVanillaInventoryMapping.getResultInventoryStart()), paramVanillaInventoryMapping.getResultHeight(paramVanillaInventoryMapping.getResultInventoryStart()), mMOItem.newBuilder().build());
/*     */ 
/*     */     
/* 298 */     return mythicRecipeInventory;
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
/*     */   public void applyResult(@NotNull MythicRecipeInventory paramMythicRecipeInventory, @NotNull MythicBlueprintInventory paramMythicBlueprintInventory, @NotNull MythicCachedResult paramMythicCachedResult, @NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull VanillaInventoryMapping paramVanillaInventoryMapping, int paramInt) {
/* 310 */     paramInventoryClickEvent.setCancelled(true);
/* 311 */     if (!(paramInventoryClickEvent.getWhoClicked() instanceof Player))
/* 312 */       return;  Player player = (Player)paramInventoryClickEvent.getWhoClicked();
/*     */ 
/*     */     
/* 315 */     ItemStack itemStack1 = paramMythicBlueprintInventory.getMainInventory().getFirst();
/* 316 */     ItemStack itemStack2 = firstFromFirstSide(paramMythicBlueprintInventory);
/*     */ 
/*     */     
/* 319 */     Ref<ArrayList<ItemStack>> ref = new Ref();
/* 320 */     MMOItem mMOItem = fromCombinationWith(itemStack1, itemStack2, player, ref);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 331 */     if (paramInt == 1 && paramInventoryClickEvent.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY) {
/*     */ 
/*     */       
/* 334 */       MythicRecipeInventory mythicRecipeInventory = paramMythicBlueprintInventory.getResultInventory().clone();
/* 335 */       mythicRecipeInventory.setItemAt(paramVanillaInventoryMapping.getResultWidth(paramVanillaInventoryMapping.getResultInventoryStart()), paramVanillaInventoryMapping.getResultHeight(paramVanillaInventoryMapping.getResultInventoryStart()), mMOItem.newBuilder().build());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 347 */       ItemStack itemStack3 = paramInventoryClickEvent.getCursor();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 357 */       processInventory(paramMythicRecipeInventory, mythicRecipeInventory, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 363 */       ItemStack itemStack4 = paramMythicRecipeInventory.getItemAt(paramVanillaInventoryMapping.getResultWidth(paramInventoryClickEvent.getSlot()), paramVanillaInventoryMapping.getResultHeight(paramInventoryClickEvent.getSlot()));
/* 364 */       if (itemStack4 == null) itemStack4 = new ItemStack(Material.AIR); 
/* 365 */       ItemStack itemStack5 = itemStack4.clone();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 370 */       if (!SilentNumbers.isAir(itemStack3))
/*     */       {
/*     */         
/* 373 */         if (itemStack3.isSimilar(itemStack5)) {
/*     */ 
/*     */           
/* 376 */           int i = itemStack3.getAmount();
/* 377 */           int j = itemStack5.getAmount();
/* 378 */           int k = itemStack5.getMaxStackSize();
/*     */ 
/*     */           
/* 381 */           if (i + j > k) {
/*     */             return;
/*     */           }
/* 384 */           itemStack5.setAmount(i + j);
/*     */         } else {
/*     */           return;
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 396 */       itemStack4.setAmount(0);
/*     */ 
/*     */ 
/*     */       
/* 400 */       paramVanillaInventoryMapping.applyToResultInventory(paramInventoryClickEvent.getInventory(), paramMythicRecipeInventory, false);
/*     */ 
/*     */       
/* 403 */       paramInventoryClickEvent.getView().setCursor(itemStack5);
/*     */ 
/*     */       
/* 406 */       consumeIngredients(paramMythicBlueprintInventory, paramMythicCachedResult, paramInventoryClickEvent.getInventory(), paramVanillaInventoryMapping, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 413 */       if (hasInputConsumption())
/*     */       {
/*     */         
/* 416 */         ArrayList arrayList = new ArrayList();
/*     */ 
/*     */         
/* 419 */         if (getMainInputConsumption() != null) {
/*     */ 
/*     */           
/* 422 */           MythicRecipeInventory mythicRecipeInventory1 = paramVanillaInventoryMapping.getMainMythicInventory(paramInventoryClickEvent.getInventory());
/*     */ 
/*     */           
/* 425 */           MythicRecipeInventory mythicRecipeInventory2 = generateResultOf(getMainInputConsumption());
/*     */ 
/*     */           
/* 428 */           arrayList.addAll(MRORecipe.stackWhatsPossible(mythicRecipeInventory1, mythicRecipeInventory2));
/*     */ 
/*     */           
/* 431 */           paramVanillaInventoryMapping.applyToMainInventory(paramInventoryClickEvent.getInventory(), mythicRecipeInventory1, false);
/*     */         } 
/*     */ 
/*     */         
/* 435 */         if (getIngotInputConsumption() != null) {
/*     */ 
/*     */           
/* 438 */           MythicRecipeInventory mythicRecipeInventory1 = paramVanillaInventoryMapping.getSideMythicInventory("ingot", paramInventoryClickEvent.getInventory());
/*     */ 
/*     */           
/* 441 */           MythicRecipeInventory mythicRecipeInventory2 = generateResultOf(getIngotInputConsumption());
/*     */ 
/*     */           
/* 444 */           arrayList.addAll(MRORecipe.stackWhatsPossible(mythicRecipeInventory1, mythicRecipeInventory2));
/*     */ 
/*     */           
/* 447 */           paramVanillaInventoryMapping.applyToSideInventory(paramInventoryClickEvent.getInventory(), mythicRecipeInventory1, "ingot", false);
/*     */         } 
/*     */ 
/*     */         
/* 451 */         MRORecipe.distributeInInventoryOrDrop((Inventory)paramInventoryClickEvent.getWhoClicked().getInventory(), arrayList, paramInventoryClickEvent.getWhoClicked().getLocation());
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 463 */       HashMap hashMap = null;
/* 464 */       PlayerInventory playerInventory = player.getInventory();
/* 465 */       byte b1 = 0;
/*     */ 
/*     */       
/* 468 */       for (byte b2 = 1; b2 <= paramInt; b2++) {
/*     */ 
/*     */ 
/*     */         
/* 472 */         MythicRecipeInventory mythicRecipeInventory = paramMythicBlueprintInventory.getResultInventory().clone();
/* 473 */         mythicRecipeInventory.setItemAt(paramVanillaInventoryMapping.getResultWidth(paramVanillaInventoryMapping.getResultInventoryStart()), paramVanillaInventoryMapping.getResultHeight(paramVanillaInventoryMapping.getResultInventoryStart()), mMOItem.newBuilder().build());
/* 474 */         ArrayList arrayList = MRORecipe.toItemsList(mythicRecipeInventory);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 481 */         if (hasInputConsumption()) {
/*     */ 
/*     */           
/* 484 */           if (getMainInputConsumption() != null) {
/*     */ 
/*     */             
/* 487 */             MythicRecipeInventory mythicRecipeInventory1 = generateResultOf(getMainInputConsumption());
/*     */ 
/*     */             
/* 490 */             arrayList.addAll(MRORecipe.toItemsList(mythicRecipeInventory1));
/*     */           } 
/*     */ 
/*     */           
/* 494 */           if (getIngotInputConsumption() != null) {
/*     */ 
/*     */             
/* 497 */             MythicRecipeInventory mythicRecipeInventory1 = generateResultOf(getIngotInputConsumption());
/*     */ 
/*     */             
/* 500 */             arrayList.addAll(MRORecipe.toItemsList(mythicRecipeInventory1));
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 505 */         HashMap hashMap1 = MRORecipe.distributeInInventory((Inventory)playerInventory, arrayList, hashMap);
/*     */ 
/*     */         
/* 508 */         if (hashMap1 == null) {
/*     */           break;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 518 */         hashMap = hashMap1;
/* 519 */         b1 = b2;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 524 */       if (b1 == 0) {
/*     */         return;
/*     */       }
/* 527 */       paramInt = b1;
/* 528 */       for (Integer integer : hashMap.keySet()) {
/*     */ 
/*     */         
/* 531 */         ItemStack itemStack = (ItemStack)hashMap.get(integer);
/*     */ 
/*     */ 
/*     */         
/* 535 */         playerInventory.setItem(integer.intValue(), itemStack);
/*     */       } 
/*     */       
/* 538 */       consumeIngredients(paramMythicBlueprintInventory, paramMythicCachedResult, paramInventoryClickEvent.getInventory(), paramVanillaInventoryMapping, paramInt);
/*     */     } 
/*     */ 
/*     */     
/* 542 */     if (isDropGemstones() && ref.getValue() != null && player.getLocation().getWorld() != null)
/*     */     {
/*     */       
/* 545 */       for (ItemStack itemStack : player.getInventory().addItem((ItemStack[])((ArrayList)ref
/* 546 */           .getValue()).toArray((Object[])new ItemStack[0])).values()) {
/*     */ 
/*     */         
/* 549 */         if (SilentNumbers.isAir(itemStack)) {
/*     */           continue;
/*     */         }
/* 552 */         player.getWorld().dropItem(player.getLocation(), itemStack);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   MMOItem fromCombinationWith(@Nullable ItemStack paramItemStack1, @Nullable ItemStack paramItemStack2, @NotNull Player paramPlayer, @Nullable Ref<ArrayList<ItemStack>> paramRef) {
/* 564 */     MMOItem mMOItem1 = fromStack(paramItemStack1);
/* 565 */     MMOItem mMOItem2 = fromStack(paramItemStack2);
/*     */ 
/*     */     
/* 568 */     MMOItem mMOItem3 = getOutputItem().newBuilder(0, null).build();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 581 */     ArrayList arrayList = new ArrayList();
/* 582 */     if (mMOItem1 != null)
/* 583 */       mMOItem1.extractGemstones().forEach(paramPair -> paramArrayList.add((MMOItem)paramPair.getValue())); 
/* 584 */     if (mMOItem2 != null) {
/* 585 */       mMOItem2.extractGemstones().forEach(paramPair -> paramArrayList.add((MMOItem)paramPair.getValue()));
/*     */     }
/*     */     
/* 588 */     ArrayList<ItemStack> arrayList1 = new ArrayList();
/* 589 */     for (MMOItem mMOItem : arrayList) {
/*     */ 
/*     */ 
/*     */       
/* 593 */       GemSocketsData gemSocketsData = (GemSocketsData)mMOItem3.getData(ItemStats.GEM_SOCKETS);
/*     */ 
/*     */       
/* 596 */       if (gemSocketsData == null || gemSocketsData.getEmptySlots().size() == 0) {
/*     */ 
/*     */ 
/*     */         
/* 600 */         arrayList1.add(mMOItem.newBuilder().build());
/*     */         
/*     */         continue;
/*     */       } 
/* 604 */       GemStone gemStone = new GemStone(paramPlayer, mMOItem.newBuilder().buildNBT());
/*     */ 
/*     */       
/* 607 */       GemStone.ApplyResult applyResult = gemStone.applyOntoItem(mMOItem3, mMOItem3.getType(), "", false, true);
/*     */ 
/*     */       
/* 610 */       if (applyResult.getType() == GemStone.ResultType.SUCCESS && applyResult.getResultAsMMOItem() != null) {
/*     */ 
/*     */         
/* 613 */         mMOItem3 = applyResult.getResultAsMMOItem();
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 619 */       arrayList1.add(mMOItem.newBuilder().build());
/*     */     } 
/*     */     
/* 622 */     Ref.setValue(paramRef, arrayList1);
/*     */ 
/*     */ 
/*     */     
/* 626 */     if (getEnchantmentTreatment() != SmithingCombinationType.NONE) {
/*     */ 
/*     */       
/* 629 */       EnchantListData enchantListData1 = (EnchantListData)mMOItem3.getData(ItemStats.ENCHANTS); if (enchantListData1 == null) enchantListData1 = (EnchantListData)ItemStats.ENCHANTS.getClearStatData(); 
/* 630 */       EnchantListData enchantListData2 = (mMOItem1 != null) ? (EnchantListData)mMOItem1.getData(ItemStats.ENCHANTS) : Enchants.fromVanilla(paramItemStack1);
/* 631 */       EnchantListData enchantListData3 = (mMOItem2 != null) ? (EnchantListData)mMOItem2.getData(ItemStats.ENCHANTS) : Enchants.fromVanilla(paramItemStack2);
/*     */ 
/*     */       
/* 634 */       for (Enchantment enchantment : Enchantment.values()) {
/*     */ 
/*     */         
/* 637 */         int m, i = enchantListData1.getLevel(enchantment);
/* 638 */         int j = enchantListData2.getLevel(enchantment);
/* 639 */         int k = enchantListData3.getLevel(enchantment);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 644 */         switch (getEnchantmentTreatment()) { case ADDITIVE:
/* 645 */             m = j + k + i; break;
/* 646 */           case MAXIMUM: if (i == 0) i = j;  m = Math.max(i, Math.max(j, k)); break;
/* 647 */           case MINIMUM: if (i == 0) i = j;  m = Math.max(i, Math.min(j, k)); break;
/* 648 */           default: if (i == 0) { m = SilentNumbers.ceil((j + k) / 2.0D); break; }  m = SilentNumbers.ceil((j + k + i) / 3.0D);
/*     */             break; }
/*     */         
/* 651 */         enchantListData1.addEnchant(enchantment, m);
/*     */       } 
/*     */ 
/*     */       
/* 655 */       mMOItem3.setData(ItemStats.ENCHANTS, (StatData)enchantListData1);
/*     */     } 
/*     */ 
/*     */     
/* 659 */     if (mMOItem3.hasUpgradeTemplate() && getUpgradeTreatment() != SmithingCombinationType.NONE) {
/*     */ 
/*     */       
/* 662 */       int k, i = 0; if (mMOItem1 != null) i = mMOItem1.getUpgradeLevel(); 
/* 663 */       int j = 0; if (mMOItem2 != null) j = mMOItem2.getUpgradeLevel();
/*     */ 
/*     */       
/* 666 */       switch (getUpgradeTreatment()) { case ADDITIVE:
/* 667 */           k = i + j; break;
/* 668 */         case MAXIMUM: k = Math.max(i, j); break;
/* 669 */         case MINIMUM: k = Math.min(i, j); break;
/* 670 */         default: k = SilentNumbers.ceil((i + j) / 2.0D);
/*     */           break; }
/*     */       
/* 673 */       mMOItem3.getUpgradeTemplate().upgradeTo(mMOItem3, Math.min(k, mMOItem3.getMaxUpgradeLevel()));
/*     */     } 
/*     */     
/* 676 */     return mMOItem3;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\recipe\CustomSmithingRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */