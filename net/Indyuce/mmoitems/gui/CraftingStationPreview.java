/*     */ package net.Indyuce.mmoitems.gui;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.CheckedIngredient;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.CheckedRecipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.CraftingRecipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.UpgradingRecipe;
/*     */ import net.Indyuce.mmoitems.api.item.util.ConfigItems;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class CraftingStationPreview
/*     */   extends PluginInventory {
/*     */   private final CraftingStationView previous;
/*     */   private final CheckedRecipe recipe;
/*  30 */   private final List<ItemStack> ingredients = new ArrayList<>();
/*     */   
/*  32 */   private static final int[] slots = new int[] { 12, 13, 14, 21, 22, 23, 30, 31, 32 };
/*  33 */   private static final int[] fill = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 17, 18, 19, 25, 26, 27, 29, 33, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44 };
/*     */   
/*     */   public CraftingStationPreview(CraftingStationView paramCraftingStationView, CheckedRecipe paramCheckedRecipe) {
/*  36 */     super(paramCraftingStationView.getPlayer());
/*     */     
/*  38 */     this.previous = paramCraftingStationView;
/*  39 */     this.recipe = paramCheckedRecipe;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Inventory getInventory() {
/*  47 */     Inventory inventory = Bukkit.createInventory(this, 45, MythicLib.plugin.getPlaceholderParser().parse((OfflinePlayer)getPlayer(), Message.RECIPE_PREVIEW.format(ChatColor.RESET, new String[0]).toString()));
/*  48 */     this.ingredients.clear();
/*     */ 
/*     */     
/*  51 */     for (CheckedIngredient checkedIngredient : this.recipe.getIngredients()) {
/*     */ 
/*     */       
/*  54 */       if (checkedIngredient.getIngredient().getAmount() > 64) {
/*     */ 
/*     */         
/*  57 */         ItemStack itemStack1 = checkedIngredient.getIngredient().generateItemStack(this.playerData.getRPG(), true);
/*  58 */         itemStack1.setAmount(64);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  63 */         int m = checkedIngredient.getIngredient().getAmount();
/*  64 */         int n = SilentNumbers.floor(m / 64.0D);
/*     */ 
/*     */         
/*  67 */         while (m > 0) {
/*     */ 
/*     */           
/*  70 */           if (m > 64) {
/*     */ 
/*     */             
/*  73 */             this.ingredients.add(itemStack1.clone());
/*     */ 
/*     */             
/*  76 */             m -= 64;
/*     */ 
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/*  82 */           itemStack1.setAmount(m);
/*  83 */           this.ingredients.add(itemStack1.clone());
/*     */ 
/*     */           
/*  86 */           m -= m;
/*     */         } 
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/*  93 */       this.ingredients.add(checkedIngredient.getIngredient().generateItemStack(this.playerData.getRPG(), true));
/*     */     } 
/*     */ 
/*     */     
/*  97 */     int i = (this.page - 1) * slots.length, j = this.page * slots.length;
/*  98 */     for (int k = i; k < j && 
/*  99 */       k < this.ingredients.size(); k++)
/*     */     {
/* 101 */       inventory.setItem(slots[k - i], this.ingredients.get(k));
/*     */     }
/*     */     
/* 104 */     for (int m : fill) {
/* 105 */       inventory.setItem(m, ConfigItems.FILL.getItem());
/*     */     }
/* 107 */     if (this.recipe.getRecipe() instanceof CraftingRecipe) {
/* 108 */       ItemStack itemStack1 = ((CraftingRecipe)this.recipe.getRecipe()).getPreviewItemStack();
/* 109 */       itemStack1.setAmount(((CraftingRecipe)this.recipe.getRecipe()).getOutputAmount());
/* 110 */       inventory.setItem(16, itemStack1);
/*     */     } 
/*     */     
/* 113 */     if (this.recipe.getRecipe() instanceof UpgradingRecipe) {
/* 114 */       ItemStack itemStack1 = ((UpgradingRecipe)this.recipe.getRecipe()).getItem().getPreview();
/* 115 */       ItemMeta itemMeta1 = itemStack1.getItemMeta();
/* 116 */       itemMeta1.setDisplayName(itemStack1.getItemMeta().getDisplayName() + ChatColor.GREEN + "+1!");
/* 117 */       itemStack1.setItemMeta(itemMeta1);
/*     */       
/* 119 */       inventory.setItem(16, itemStack1);
/*     */     } 
/*     */     
/* 122 */     inventory.setItem(10, ConfigItems.BACK.getItem());
/* 123 */     inventory.setItem(34, ConfigItems.CONFIRM.getItem());
/* 124 */     ItemStack itemStack = this.recipe.display();
/*     */     
/* 126 */     itemStack.setType(Material.KNOWLEDGE_BOOK);
/* 127 */     itemStack.setAmount(1);
/*     */     
/* 129 */     ItemMeta itemMeta = itemStack.getItemMeta();
/* 130 */     for (Enchantment enchantment : itemMeta.getEnchants().keySet())
/* 131 */       itemMeta.removeEnchant(enchantment); 
/* 132 */     itemMeta.setLore(itemMeta.getLore().subList(0, itemMeta.getLore().size() - 3));
/* 133 */     itemStack.setItemMeta(itemMeta);
/*     */     
/* 135 */     inventory.setItem(28, itemStack);
/*     */     
/* 137 */     inventory.setItem(20, (this.page > 1) ? ConfigItems.PREVIOUS_PAGE.getItem() : ConfigItems.FILL.getItem());
/* 138 */     inventory.setItem(24, (j < this.ingredients.size()) ? ConfigItems.NEXT_PAGE.getItem() : ConfigItems.FILL.getItem());
/*     */     
/* 140 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 145 */     paramInventoryClickEvent.setCancelled(true);
/* 146 */     if (!MMOUtils.isMetaItem(paramInventoryClickEvent.getCurrentItem(), false)) {
/*     */       return;
/*     */     }
/* 149 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramInventoryClickEvent.getCurrentItem());
/* 150 */     switch (nBTItem.getString("ItemId")) {
/*     */       case "CONFIRM":
/* 152 */         this.previous.processRecipe(this.recipe);
/* 153 */         this.previous.open();
/*     */         return;
/*     */       case "PREVIOUS_PAGE":
/* 156 */         this.page--;
/* 157 */         open();
/*     */         return;
/*     */       case "NEXT_PAGE":
/* 160 */         this.page++;
/* 161 */         open();
/*     */         return;
/*     */       case "BACK":
/* 164 */         this.previous.open();
/*     */         return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\CraftingStationPreview.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */