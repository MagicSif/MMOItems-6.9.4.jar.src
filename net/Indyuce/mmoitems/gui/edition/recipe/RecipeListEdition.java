/*    */ package net.Indyuce.mmoitems.gui.edition.recipe;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.burninglegacy.CraftingType;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.InventoryAction;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryHolder;
/*    */ import org.bukkit.inventory.ItemFlag;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ public class RecipeListEdition
/*    */   extends EditionInventory
/*    */ {
/*    */   public RecipeListEdition(Player paramPlayer, MMOItemTemplate paramMMOItemTemplate) {
/* 27 */     super(paramPlayer, paramMMOItemTemplate);
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getInventory() {
/* 32 */     Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Crafting Recipes: " + this.template.getId());
/*    */     
/* 34 */     for (CraftingType craftingType : CraftingType.values()) {
/* 35 */       if (craftingType.shouldAdd()) {
/* 36 */         ItemStack itemStack = craftingType.getItem();
/* 37 */         ItemMeta itemMeta = itemStack.getItemMeta();
/* 38 */         itemMeta.addItemFlags(ItemFlag.values());
/* 39 */         itemMeta.setDisplayName(ChatColor.GREEN + craftingType.getName());
/* 40 */         ArrayList<String> arrayList = new ArrayList();
/* 41 */         arrayList.add(ChatColor.GRAY + craftingType.getLore());
/* 42 */         arrayList.add("");
/* 43 */         arrayList.add(getEditedSection().contains("crafting." + craftingType.name().toLowerCase()) ? (ChatColor.GREEN + "Found one or more recipe(s).") : (
/* 44 */             ChatColor.RED + "No recipes found."));
/* 45 */         arrayList.add("");
/* 46 */         arrayList.add(ChatColor.YELLOW + "►" + " Click to change this recipe.");
/* 47 */         arrayList.add(ChatColor.YELLOW + "►" + " Right click to remove recipe.");
/* 48 */         itemMeta.setLore(arrayList);
/* 49 */         itemStack.setItemMeta(itemMeta);
/*    */         
/* 51 */         inventory.setItem(craftingType.getSlot(), itemStack);
/*    */       } 
/*    */     } 
/* 54 */     addEditionInventoryItems(inventory, true);
/*    */     
/* 56 */     return inventory;
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 61 */     ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
/*    */     
/* 63 */     paramInventoryClickEvent.setCancelled(true);
/* 64 */     if (paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory() || !MMOUtils.isMetaItem(itemStack, false)) {
/*    */       return;
/*    */     }
/* 67 */     CraftingType craftingType = CraftingType.getBySlot(paramInventoryClickEvent.getSlot());
/* 68 */     if (craftingType == null) {
/*    */       return;
/*    */     }
/* 71 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 72 */       if (craftingType == CraftingType.SHAPELESS || craftingType == CraftingType.SHAPED) {
/* 73 */         (new RecipeEdition(this.player, this.template, (craftingType == CraftingType.SHAPELESS))).open(getPreviousPage());
/* 74 */       } else if (craftingType == CraftingType.SMITHING) {
/* 75 */         (new StatEdition(this, ItemStats.CRAFTING, new Object[] { "smithing" })).enable(new String[] { "Write in the chat the items required to craft this.", "Format: '[ITEM] [ITEM]'", "[ITEM] = '[MATERIAL]' or '[MATERIAL]:[DURABILITY]' or '[TYPE].[ID]'" });
/*    */       } else {
/*    */         
/* 78 */         (new StatEdition(this, ItemStats.CRAFTING, new Object[] { "item", craftingType.name().toLowerCase() })).enable(new String[] { "Write in the chat the item, tickspeed and exp you want.", "Format: '[ITEM] [TICKS] [EXP]'", "[ITEM] = '[MATERIAL]' or '[MATERIAL]:[DURABILITY]' or '[TYPE].[ID]'" });
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/* 83 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && getEditedSection().contains("crafting." + craftingType.name().toLowerCase())) {
/* 84 */       getEditedSection().set("crafting." + craftingType.name().toLowerCase(), null);
/* 85 */       this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + craftingType.getName() + " recipe.");
/*    */       
/* 87 */       if (getEditedSection().getConfigurationSection("crafting").getKeys(false).size() == 0) {
/* 88 */         getEditedSection().set("crafting", null);
/*    */       }
/* 90 */       registerTemplateEdition();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\RecipeListEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */