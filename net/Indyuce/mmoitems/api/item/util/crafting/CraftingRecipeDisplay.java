/*     */ package net.Indyuce.mmoitems.api.item.util.crafting;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.util.AdventureUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.ListIterator;
/*     */ import net.Indyuce.mmoitems.api.crafting.ConditionalDisplay;
/*     */ import net.Indyuce.mmoitems.api.crafting.condition.CheckedCondition;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.CheckedIngredient;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.CheckedRecipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.CraftingRecipe;
/*     */ import net.Indyuce.mmoitems.api.item.util.ConfigItem;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class CraftingRecipeDisplay extends ConfigItem {
/*     */   public CraftingRecipeDisplay() {
/*  24 */     super("CRAFTING_RECIPE_DISPLAY", Material.BARRIER, "&a&lCraft&f #name#", new String[] { "{conditions}", "{conditions}&8Conditions:", "{crafting_time}", "{crafting_time}&7Crafting Time: &c#crafting-time#&7s", "", "&8Ingredients:", "#ingredients#", "", "&eLeft-Click to craft!", "&eRight-Click to preview!" });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemBuilder newBuilder(CheckedRecipe paramCheckedRecipe) {
/*  30 */     return new ItemBuilder(paramCheckedRecipe);
/*     */   }
/*     */ 
/*     */   
/*     */   public class ItemBuilder
/*     */   {
/*     */     private final CheckedRecipe recipe;
/*     */     
/*     */     private final CraftingRecipe craftingRecipe;
/*     */     
/*  40 */     private final String name = CraftingRecipeDisplay.this.getName();
/*  41 */     private final List<String> lore = new ArrayList<>(CraftingRecipeDisplay.this.getLore());
/*     */     
/*     */     public ItemBuilder(CheckedRecipe param1CheckedRecipe) {
/*  44 */       this.recipe = param1CheckedRecipe;
/*  45 */       this.craftingRecipe = (CraftingRecipe)param1CheckedRecipe.getRecipe();
/*     */     }
/*     */     
/*     */     public ItemStack build() {
/*  49 */       HashMap<Object, Object> hashMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  56 */       int i = -1;
/*     */       
/*  58 */       for (ListIterator<String> listIterator = this.lore.listIterator(); listIterator.hasNext(); ) {
/*  59 */         int m = listIterator.nextIndex();
/*  60 */         String str = listIterator.next();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  65 */         if (str.startsWith("{crafting_time}")) {
/*  66 */           if (this.craftingRecipe.getCraftingTime() <= 0.0D) {
/*  67 */             listIterator.remove();
/*     */             
/*     */             continue;
/*     */           } 
/*  71 */           hashMap.put(str, str.replace("{crafting_time}", "").replace("#crafting-time#", 
/*  72 */                 (MythicLib.plugin.getMMOConfig()).decimal.format(this.craftingRecipe.getCraftingTime())));
/*     */         } 
/*     */         
/*  75 */         if (str.startsWith("{conditions}")) {
/*  76 */           i = m + 1;
/*  77 */           if (this.recipe.getConditions().size() == 0) {
/*  78 */             listIterator.remove(); continue;
/*     */           } 
/*  80 */           hashMap.put(str, str.replace("{conditions}", ""));
/*     */         } 
/*     */       } 
/*     */       
/*  84 */       for (String str : hashMap.keySet()) {
/*  85 */         this.lore.set(this.lore.indexOf(str), (String)hashMap.get(str));
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  91 */       int j = this.lore.indexOf("#ingredients#");
/*  92 */       this.lore.remove(j);
/*  93 */       this.recipe.getIngredients().forEach(param1CheckedIngredient -> this.lore.add(param1Int, param1CheckedIngredient.format()));
/*     */       
/*  95 */       if (i >= 0)
/*  96 */         for (CheckedCondition checkedCondition : this.recipe.getConditions()) {
/*  97 */           ConditionalDisplay conditionalDisplay = checkedCondition.getCondition().getDisplay();
/*  98 */           if (conditionalDisplay != null)
/*     */           {
/*     */             
/* 101 */             this.lore.add(i++, checkedCondition.format());
/*     */           }
/*     */         }  
/* 104 */       ItemStack itemStack = this.craftingRecipe.getPreviewItemStack();
/* 105 */       int k = this.craftingRecipe.getOutputAmount();
/*     */       
/* 107 */       if (k > 64) {
/* 108 */         this.lore.add(0, Message.STATION_BIG_STACK.format(ChatColor.GOLD, new String[] { "#size#", String.valueOf(k) }).toString());
/*     */       } else {
/* 110 */         itemStack.setAmount(k);
/*     */       } 
/* 112 */       ItemMeta itemMeta = itemStack.getItemMeta();
/* 113 */       itemMeta.addItemFlags(ItemFlag.values());
/* 114 */       AdventureUtils.setDisplayName(itemMeta, this.name.replace("#name#", ((k > 1) ? (ChatColor.WHITE + "" + k + " x ") : "") + MMOUtils.getDisplayName(itemStack)));
/* 115 */       AdventureUtils.setLore(itemMeta, this.lore);
/* 116 */       itemStack.setItemMeta(itemMeta);
/*     */       
/* 118 */       return NBTItem.get(itemStack).addTag(new ItemTag[] { new ItemTag("recipeId", this.craftingRecipe.getId()) }).toItem();
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ite\\util\crafting\CraftingRecipeDisplay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */