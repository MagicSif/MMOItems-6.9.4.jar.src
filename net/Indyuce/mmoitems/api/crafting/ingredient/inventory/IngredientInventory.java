/*     */ package net.Indyuce.mmoitems.api.crafting.ingredient.inventory;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.CheckedIngredient;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.Ingredient;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.IngredientType;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class IngredientInventory
/*     */ {
/*  22 */   private final Map<String, Set<PlayerIngredient>> ingredients = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IngredientInventory(Player paramPlayer) {
/*  28 */     this((Inventory)paramPlayer.getInventory());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IngredientInventory(Inventory paramInventory) {
/*  36 */     for (ItemStack itemStack : paramInventory.getContents()) {
/*  37 */       if (itemStack != null && itemStack.getType() != Material.AIR && itemStack.getAmount() > 0) {
/*  38 */         NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack);
/*  39 */         for (IngredientType ingredientType : MMOItems.plugin.getCrafting().getIngredients()) {
/*  40 */           if (ingredientType.check(nBTItem)) {
/*  41 */             addIngredient(nBTItem, ingredientType);
/*     */             break;
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
/*     */   public void addIngredient(NBTItem paramNBTItem, IngredientType paramIngredientType) {
/*  55 */     String str = paramIngredientType.getId();
/*     */ 
/*     */     
/*  58 */     if (this.ingredients.containsKey(str)) {
/*  59 */       ((Set<PlayerIngredient>)this.ingredients.get(str)).add(paramIngredientType.readPlayerIngredient(paramNBTItem));
/*     */     }
/*     */     else {
/*     */       
/*  63 */       HashSet<PlayerIngredient> hashSet = new HashSet();
/*  64 */       hashSet.add(paramIngredientType.readPlayerIngredient(paramNBTItem));
/*  65 */       this.ingredients.put(str, hashSet);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public CheckedIngredient findMatching(@NotNull Ingredient paramIngredient) {
/*  71 */     HashSet<PlayerIngredient> hashSet = new HashSet();
/*  72 */     if (!this.ingredients.containsKey(paramIngredient.getId())) {
/*  73 */       return new CheckedIngredient(paramIngredient, hashSet);
/*     */     }
/*  75 */     for (PlayerIngredient playerIngredient : this.ingredients.get(paramIngredient.getId())) {
/*  76 */       if (paramIngredient.matches(playerIngredient))
/*  77 */         hashSet.add(playerIngredient); 
/*     */     } 
/*  79 */     return new CheckedIngredient(paramIngredient, hashSet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean hasIngredient(Ingredient paramIngredient) {
/*  88 */     return findMatching(paramIngredient).isHad();
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
/*     */   @Deprecated
/*     */   public enum IngredientLookupMode
/*     */   {
/* 104 */     IGNORE_ITEM_LEVEL,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     BASIC;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\ingredient\inventory\IngredientInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */