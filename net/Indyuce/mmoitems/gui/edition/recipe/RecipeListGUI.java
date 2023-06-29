/*     */ package net.Indyuce.mmoitems.gui.edition.recipe;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.util.ItemFactory;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.RecipeRegistry;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public class RecipeListGUI
/*     */   extends EditionInventory
/*     */ {
/*     */   @NotNull
/*  37 */   final ItemStack nextPage = ItemFactory.of(Material.ARROW).name("§7Next Page").build(); @NotNull
/*  38 */   final ItemStack prevPage = ItemFactory.of(Material.ARROW).name("§7Previous Page").build(); @NotNull
/*  39 */   final ItemStack noRecipe = ItemFactory.of(Material.BLACK_STAINED_GLASS_PANE).name("§7No Recipe").build(); @NotNull
/*     */   final RecipeRegistry recipeType; @NotNull
/*     */   final ItemStack listedItem; @NotNull
/*  42 */   public RecipeRegistry getRecipeRegistry() { return this.recipeType; }
/*     */    @NotNull
/*     */   public ItemStack getListedItem() {
/*  45 */     return this.listedItem;
/*     */   } @NotNull
/*  47 */   final ArrayList<String> recipeNames = new ArrayList<>(); boolean invalidRecipe; int currentPage; int createSlot; @NotNull final HashMap<Integer, String> recipeMap; @NotNull
/*  48 */   public ArrayList<String> getRecipeNames() { return this.recipeNames; }
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeListGUI(@NotNull Player paramPlayer, @NotNull MMOItemTemplate paramMMOItemTemplate, @NotNull RecipeRegistry paramRecipeRegistry) {
/*  53 */     super(paramPlayer, paramMMOItemTemplate);
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
/*  76 */     this.createSlot = -1;
/*  77 */     this.recipeMap = new HashMap<>(); this.recipeType = paramRecipeRegistry; this.listedItem = getRecipeRegistry().getDisplayListItem(); ConfigurationSection configurationSection1 = RecipeMakerGUI.getSection(getEditedSection(), "crafting"); ConfigurationSection configurationSection2 = RecipeMakerGUI.getSection(configurationSection1, paramRecipeRegistry.getRecipeConfigPath());
/*     */     for (String str : configurationSection2.getValues(false).keySet()) {
/*     */       if (str == null || str.isEmpty())
/*     */         continue; 
/*     */       this.recipeNames.add(str);
/*  82 */     }  } @NotNull public Inventory getInventory() { Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Choose " + getRecipeRegistry().getRecipeTypeName() + " Recipe");
/*     */ 
/*     */     
/*  85 */     addEditionInventoryItems(inventory, true);
/*     */ 
/*     */     
/*  88 */     arrangeInventory(inventory);
/*     */ 
/*     */     
/*  91 */     return inventory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void arrangeInventory(@NotNull Inventory paramInventory) {
/* 101 */     this.recipeMap.clear();
/* 102 */     this.createSlot = -1;
/*     */ 
/*     */     
/* 105 */     if (this.currentPage > 0) paramInventory.setItem(27, this.prevPage); 
/* 106 */     if (this.recipeNames.size() >= (this.currentPage + 1) * 21) paramInventory.setItem(36, this.nextPage);
/*     */ 
/*     */     
/* 109 */     for (int i = 21 * this.currentPage; i < 21 * (this.currentPage + 1); i++) {
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
/* 122 */       int j = page(i);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 131 */       if (i == this.recipeNames.size()) {
/*     */ 
/*     */         
/* 134 */         paramInventory.setItem(j, RecipeMakerGUI.rename(new ItemStack(Material.NETHER_STAR), FFPMMOItems.get().getBodyFormat() + "Create new " + SilentNumbers.getItemName(getListedItem(), false)));
/*     */ 
/*     */         
/* 137 */         this.createSlot = j;
/*     */       
/*     */       }
/* 140 */       else if (i > this.recipeNames.size()) {
/*     */ 
/*     */         
/* 143 */         paramInventory.setItem(j, this.noRecipe);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 149 */         paramInventory.setItem(j, RecipeMakerGUI.rename(getListedItem().clone(), FFPMMOItems.get().getBodyFormat() + "Edit " + FFPMMOItems.get().getInputFormat() + (String)this.recipeNames.get(i)));
/*     */ 
/*     */         
/* 152 */         this.recipeMap.put(Integer.valueOf(j), this.recipeNames.get(i));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int page(int paramInt) {
/* 159 */     int i = SilentNumbers.floor(paramInt / 21.0D);
/* 160 */     paramInt -= i * 21;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 169 */     int j = SilentNumbers.floor(paramInt / 7.0D);
/* 170 */     int k = paramInt - 7 * j;
/*     */ 
/*     */     
/* 173 */     int m = 18 + j * 9;
/* 174 */     int n = k + 1;
/*     */ 
/*     */     
/* 177 */     return m + n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 184 */     if (paramInventoryClickEvent.getView().getTopInventory() != paramInventoryClickEvent.getClickedInventory()) {
/*     */       return;
/*     */     }
/* 187 */     paramInventoryClickEvent.setCancelled(true);
/*     */     
/* 189 */     if (this.invalidRecipe) {
/*     */       return;
/*     */     }
/* 192 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*     */ 
/*     */       
/* 195 */       if (paramInventoryClickEvent.getSlot() == 27)
/*     */       {
/*     */         
/* 198 */         this.currentPage--;
/* 199 */         arrangeInventory(paramInventoryClickEvent.getView().getTopInventory());
/*     */       
/*     */       }
/* 202 */       else if (paramInventoryClickEvent.getSlot() == 36)
/*     */       {
/*     */         
/* 205 */         this.currentPage++;
/* 206 */         arrangeInventory(paramInventoryClickEvent.getView().getTopInventory());
/*     */       
/*     */       }
/* 209 */       else if (paramInventoryClickEvent.getSlot() == this.createSlot)
/*     */       {
/*     */         
/* 212 */         String str = String.valueOf(this.recipeMap.size() + 1);
/* 213 */         if (this.recipeMap.containsValue(str)) str = str + "_" + UUID.randomUUID();
/*     */ 
/*     */         
/* 216 */         getRecipeRegistry().openForPlayer(this, str, new Object[0]);
/*     */       
/*     */       }
/* 219 */       else if (paramInventoryClickEvent.getSlot() > 18)
/*     */       {
/*     */         
/* 222 */         String str = this.recipeMap.get(Integer.valueOf(paramInventoryClickEvent.getSlot()));
/*     */ 
/*     */         
/* 225 */         if (str != null)
/*     */         {
/*     */           
/* 228 */           getRecipeRegistry().openForPlayer(this, str, new Object[0]);
/*     */         }
/*     */       }
/*     */     
/*     */     }
/* 233 */     else if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/*     */ 
/*     */       
/* 236 */       String str = this.recipeMap.get(Integer.valueOf(paramInventoryClickEvent.getSlot()));
/*     */ 
/*     */       
/* 239 */       if (str != null) {
/*     */ 
/*     */         
/* 242 */         ConfigurationSection configurationSection1 = RecipeMakerGUI.getSection(getEditedSection(), "crafting");
/* 243 */         ConfigurationSection configurationSection2 = RecipeMakerGUI.getSection(configurationSection1, getRecipeRegistry().getRecipeConfigPath());
/* 244 */         this.recipeNames.remove(str);
/* 245 */         configurationSection2.set(str, null);
/*     */ 
/*     */         
/* 248 */         arrangeInventory(paramInventoryClickEvent.getView().getTopInventory());
/*     */ 
/*     */         
/* 251 */         registerTemplateEdition();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\RecipeListGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */