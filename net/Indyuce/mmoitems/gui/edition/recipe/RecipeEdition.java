/*     */ package net.Indyuce.mmoitems.gui.edition.recipe;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.recipe.workbench.ingredients.WorkbenchIngredient;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.manager.RecipeManager;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class RecipeEdition extends EditionInventory {
/*     */   private final boolean shapeless;
/*     */   
/*     */   public RecipeEdition(Player paramPlayer, MMOItemTemplate paramMMOItemTemplate, boolean paramBoolean) {
/*  29 */     super(paramPlayer, paramMMOItemTemplate);
/*     */     
/*  31 */     this.shapeless = paramBoolean;
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getInventory() {
/*  36 */     return this.shapeless ? setupShapelessInventory() : setupShapedInventory();
/*     */   }
/*     */   
/*     */   private Inventory setupShapedInventory() {
/*  40 */     Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Recipe Editor: " + this.template.getId());
/*     */     
/*  42 */     if (!getEditedSection().contains("crafting.shaped.1")) {
/*  43 */       getEditedSection().set("crafting.shaped.1", new String[] { "AIR AIR AIR", "AIR AIR AIR", "AIR AIR AIR" });
/*  44 */       registerTemplateEdition();
/*     */     } 
/*  46 */     List<String> list = getEditedSection().getStringList("crafting.shaped.1");
/*  47 */     if (list.size() < 3) {
/*  48 */       while (list.size() < 3) {
/*  49 */         list.add("AIR AIR AIR");
/*     */       }
/*  51 */       getEditedSection().set("crafting.shaped.1", list);
/*  52 */       registerTemplateEdition();
/*     */     } 
/*  54 */     for (byte b = 0; b < 9; b++) {
/*  55 */       ItemStack itemStack; int i = intToSlot(b);
/*  56 */       List<String> list1 = Arrays.asList(((String)list.get(b / 3)).split(" "));
/*  57 */       while (list1.size() < 3) {
/*  58 */         list1.add("AIR");
/*     */       }
/*     */       
/*     */       try {
/*  62 */         MMOItems.plugin.getRecipes(); WorkbenchIngredient workbenchIngredient = RecipeManager.getWorkbenchIngredient(list1.get(b % 3));
/*  63 */         itemStack = workbenchIngredient.generateItem();
/*  64 */         itemStack.setAmount(workbenchIngredient.getAmount());
/*  65 */         Validate.isTrue((itemStack != null && itemStack.getType() != Material.AIR));
/*  66 */       } catch (IllegalArgumentException illegalArgumentException) {
/*  67 */         itemStack = new ItemStack(Material.BARRIER);
/*     */       } 
/*  69 */       ItemMeta itemMeta = itemStack.getItemMeta();
/*  70 */       if (itemStack.getType() == Material.BARRIER)
/*  71 */         itemMeta.setDisplayName(ChatColor.RED + "Empty"); 
/*  72 */       ArrayList<String> arrayList = new ArrayList();
/*  73 */       arrayList.add("");
/*  74 */       arrayList.add(ChatColor.YELLOW + "►" + " Click to change this ingredient.");
/*  75 */       arrayList.add(ChatColor.YELLOW + "►" + " Right click to remove this ingredient.");
/*  76 */       itemMeta.setLore(arrayList);
/*  77 */       itemStack.setItemMeta(itemMeta);
/*     */       
/*  79 */       inventory.setItem(i, itemStack);
/*     */     } 
/*     */     
/*  82 */     addEditionInventoryItems(inventory, true);
/*  83 */     return inventory;
/*     */   }
/*     */   
/*     */   private Inventory setupShapelessInventory() {
/*  87 */     Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Recipe Editor: " + this.template.getId());
/*  88 */     if (!getEditedSection().contains("crafting.shapeless.1")) {
/*  89 */       getEditedSection().set("crafting.shapeless.1", Arrays.asList(new String[] { "AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR", "AIR" }));
/*  90 */       registerTemplateEdition();
/*     */     } 
/*  92 */     List<String> list = getEditedSection().getStringList("crafting.shapeless.1");
/*  93 */     if (list.size() == 9) {
/*  94 */       for (byte b = 0; b < 9; b++) {
/*  95 */         ItemStack itemStack; int i = intToSlot(b);
/*     */ 
/*     */         
/*     */         try {
/*  99 */           MMOItems.plugin.getRecipes(); WorkbenchIngredient workbenchIngredient = RecipeManager.getWorkbenchIngredient(list.get(b));
/* 100 */           itemStack = workbenchIngredient.generateItem();
/* 101 */           itemStack.setAmount(workbenchIngredient.getAmount());
/* 102 */           Validate.isTrue((itemStack != null && itemStack.getType() != Material.AIR));
/* 103 */         } catch (IllegalArgumentException illegalArgumentException) {
/* 104 */           itemStack = new ItemStack(Material.BARRIER);
/*     */         } 
/* 106 */         ItemMeta itemMeta = itemStack.getItemMeta();
/* 107 */         if (itemStack.getType() == Material.BARRIER)
/* 108 */           itemMeta.setDisplayName(ChatColor.RED + "Empty"); 
/* 109 */         ArrayList<String> arrayList = new ArrayList();
/* 110 */         arrayList.add("");
/* 111 */         arrayList.add(ChatColor.YELLOW + "►" + " Click to change this ingredient.");
/* 112 */         arrayList.add(ChatColor.YELLOW + "►" + " Right click to remove this ingredient.");
/* 113 */         itemMeta.setLore(arrayList);
/* 114 */         itemStack.setItemMeta(itemMeta);
/*     */         
/* 116 */         inventory.setItem(i, itemStack);
/*     */       } 
/*     */     } else {
/* 119 */       MMOItems.plugin.getLogger().warning("Couldn't load shapeless recipe for '" + this.template.getId() + "'!");
/*     */     } 
/* 121 */     addEditionInventoryItems(inventory, true);
/* 122 */     return inventory;
/*     */   }
/*     */   
/*     */   private int intToSlot(int paramInt) {
/* 126 */     return (paramInt >= 0 && paramInt <= 2) ? (21 + paramInt) : ((paramInt >= 3 && paramInt <= 5) ? (27 + paramInt) : ((paramInt >= 6 && paramInt <= 8) ? (33 + paramInt) : 0));
/*     */   }
/*     */   
/*     */   private int slotToInt(int paramInt) {
/* 130 */     return (paramInt >= 21 && paramInt <= 23) ? (paramInt - 21) : ((paramInt >= 30 && paramInt <= 32) ? (paramInt - 27) : ((paramInt >= 39 && paramInt <= 41) ? (paramInt - 33) : -1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 136 */     paramInventoryClickEvent.setCancelled(true);
/* 137 */     if (paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory()) {
/*     */       return;
/*     */     }
/* 140 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 141 */       if (slotToInt(paramInventoryClickEvent.getRawSlot()) >= 0) {
/* 142 */         (new StatEdition(this, ItemStats.CRAFTING, new Object[] { "recipe", this.shapeless ? "shapeless" : "shaped", Integer.valueOf(slotToInt(paramInventoryClickEvent.getRawSlot()))
/* 143 */             })).enable(new String[] { "Write in the chat the item you want.", "Format: '[MATERIAL]' or '[TYPE].[ID]'" });
/*     */       }
/* 145 */     } else if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/* 146 */       if (this.shapeless) {
/* 147 */         deleteShapeless(slotToInt(paramInventoryClickEvent.getRawSlot()));
/*     */       } else {
/* 149 */         deleteShaped(slotToInt(paramInventoryClickEvent.getRawSlot()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void deleteShaped(int paramInt) {
/* 154 */     List<String> list = getEditedSection().getStringList("crafting.shaped.1");
/* 155 */     String[] arrayOfString = ((String)list.get(paramInt / 3)).split(" ");
/* 156 */     arrayOfString[paramInt % 3] = "AIR";
/* 157 */     list.set(paramInt / 3, arrayOfString[0] + " " + arrayOfString[1] + " " + arrayOfString[2]);
/*     */     
/* 159 */     getEditedSection().set("crafting.shaped.1", list);
/* 160 */     registerTemplateEdition();
/*     */   }
/*     */   
/*     */   private void deleteShapeless(int paramInt) {
/* 164 */     if (getEditedSection().contains("crafting.shapeless.1")) {
/* 165 */       List<String> list = getEditedSection().getStringList("crafting.shapeless.1");
/* 166 */       list.set(paramInt, "AIR");
/* 167 */       getEditedSection().set("crafting.shapeless.1", list);
/* 168 */       registerTemplateEdition();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\RecipeEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */