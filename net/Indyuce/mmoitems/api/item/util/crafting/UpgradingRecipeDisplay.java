/*    */ package net.Indyuce.mmoitems.api.item.util.crafting;
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.util.AdventureUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.ListIterator;
/*    */ import net.Indyuce.mmoitems.api.crafting.ConditionalDisplay;
/*    */ import net.Indyuce.mmoitems.api.crafting.condition.CheckedCondition;
/*    */ import net.Indyuce.mmoitems.api.crafting.ingredient.CheckedIngredient;
/*    */ import net.Indyuce.mmoitems.api.crafting.recipe.CheckedRecipe;
/*    */ import net.Indyuce.mmoitems.api.crafting.recipe.UpgradingRecipe;
/*    */ import net.Indyuce.mmoitems.api.item.util.ConfigItem;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemFlag;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class UpgradingRecipeDisplay extends ConfigItem {
/*    */   public UpgradingRecipeDisplay() {
/* 21 */     super("UPGRADING_RECIPE_DISPLAY", Material.BARRIER, "&e&lUpgrade&f #name#", new String[] { "{conditions}", "{conditions}&8Conditions:", "", "&8Ingredients:", "#ingredients#", "", "&eLeft-Click to craft!", "&eRight-Click to preview!" });
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemBuilder newBuilder(CheckedRecipe paramCheckedRecipe) {
/* 26 */     return new ItemBuilder(paramCheckedRecipe);
/*    */   }
/*    */ 
/*    */   
/*    */   public class ItemBuilder
/*    */   {
/*    */     private final CheckedRecipe recipe;
/*    */     
/*    */     private final UpgradingRecipe upgradingRecipe;
/*    */     
/* 36 */     private final String name = UpgradingRecipeDisplay.this.getName();
/* 37 */     private final List<String> lore = new ArrayList<>(UpgradingRecipeDisplay.this.getLore());
/*    */     
/*    */     public ItemBuilder(CheckedRecipe param1CheckedRecipe) {
/* 40 */       this.recipe = param1CheckedRecipe;
/* 41 */       this.upgradingRecipe = (UpgradingRecipe)param1CheckedRecipe.getRecipe();
/*    */     }
/*    */     
/*    */     public ItemStack build() {
/* 45 */       HashMap<Object, Object> hashMap = new HashMap<>();
/* 46 */       int i = -1;
/*    */       
/* 48 */       for (ListIterator<String> listIterator = this.lore.listIterator(); listIterator.hasNext(); ) {
/* 49 */         int k = listIterator.nextIndex();
/* 50 */         String str = listIterator.next();
/*    */         
/* 52 */         if (str.startsWith("{conditions}")) {
/* 53 */           i = k;
/* 54 */           if (this.recipe.getConditions().size() == 0) {
/* 55 */             listIterator.remove(); continue;
/*    */           } 
/* 57 */           hashMap.put(str, str.replace("{conditions}", ""));
/*    */         } 
/*    */       } 
/*    */       
/* 61 */       for (String str : hashMap.keySet()) {
/* 62 */         this.lore.set(this.lore.indexOf(str), (String)hashMap.get(str));
/*    */       }
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 68 */       int j = this.lore.indexOf("#ingredients#");
/* 69 */       this.lore.remove(j);
/* 70 */       this.recipe.getIngredients().forEach(param1CheckedIngredient -> this.lore.add(param1Int, param1CheckedIngredient.format()));
/*    */       
/* 72 */       if (i >= 0)
/* 73 */         for (CheckedCondition checkedCondition : this.recipe.getConditions()) {
/* 74 */           ConditionalDisplay conditionalDisplay = checkedCondition.getCondition().getDisplay();
/* 75 */           if (conditionalDisplay != null)
/*    */           {
/*    */             
/* 78 */             this.lore.add(i++, checkedCondition.format());
/*    */           }
/*    */         }  
/* 81 */       ItemStack itemStack = this.upgradingRecipe.getItem().getPreview();
/* 82 */       ItemMeta itemMeta = itemStack.getItemMeta();
/* 83 */       AdventureUtils.setDisplayName(itemMeta, this.name.replace("#name#", MMOUtils.getDisplayName(itemStack)));
/* 84 */       AdventureUtils.setLore(itemMeta, this.lore);
/* 85 */       itemMeta.addItemFlags(ItemFlag.values());
/* 86 */       itemStack.setItemMeta(itemMeta);
/*    */       
/* 88 */       return NBTItem.get(itemStack).addTag(new ItemTag[] { new ItemTag("recipeId", this.recipe.getRecipe().getId()) }).toItem();
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ite\\util\crafting\UpgradingRecipeDisplay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */