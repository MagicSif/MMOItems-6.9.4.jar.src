/*     */ package net.Indyuce.mmoitems.gui.edition.recipe;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.util.ItemFactory;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.RMGRR_MegaShaped;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.RMGRR_Shaped;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.RMGRR_Shapeless;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.RMGRR_Smithing;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.RMGRR_SuperShaped;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.RecipeRegistry;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.burninglegacy.RMGRR_LBBlast;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.burninglegacy.RMGRR_LBCampfire;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.burninglegacy.RMGRR_LBFurnace;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.burninglegacy.RMGRR_LBSmoker;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
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
/*     */ public class RecipeBrowserGUI
/*     */   extends EditionInventory
/*     */ {
/*     */   @NotNull
/*  42 */   final ItemStack nextPage = ItemFactory.of(Material.ARROW).name(FFPMMOItems.get().getExampleFormat() + "Next Page").build(); @NotNull
/*  43 */   final ItemStack prevPage = ItemFactory.of(Material.ARROW).name(FFPMMOItems.get().getExampleFormat() + "Previous Page").build(); @NotNull
/*  44 */   final ItemStack noRecipe = ItemFactory.of(Material.LIGHT_GRAY_STAINED_GLASS_PANE).name("").build();
/*     */ 
/*     */ 
/*     */   
/*     */   int currentPage;
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   final HashMap<Integer, RecipeRegistry> recipeTypeMap;
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeBrowserGUI(@NotNull Player paramPlayer, @NotNull MMOItemTemplate paramMMOItemTemplate) {
/*  57 */     super(paramPlayer, paramMMOItemTemplate);
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     this.recipeTypeMap = new HashMap<>();
/*     */     this.currentPage = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void arrangeInventory(@NotNull Inventory paramInventory) {
/*  94 */     this.recipeTypeMap.clear();
/*     */ 
/*     */     
/*  97 */     if (this.currentPage > 0) paramInventory.setItem(27, this.prevPage); 
/*  98 */     if (registeredRecipes.size() >= (this.currentPage + 1) * 21) paramInventory.setItem(36, this.nextPage);
/*     */ 
/*     */     
/* 101 */     HashMap<Object, Object> hashMap = new HashMap<>(); byte b = 0;
/* 102 */     for (RecipeRegistry recipeRegistry : registeredRecipes.values()) { hashMap.put(Integer.valueOf(b), recipeRegistry); b++; }
/*     */ 
/*     */     
/* 105 */     for (int i = 21 * this.currentPage; i < 21 * (this.currentPage + 1); i++) {
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
/* 120 */       int j = page(i);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 129 */       if (i >= registeredRecipes.size()) {
/*     */ 
/*     */         
/* 132 */         paramInventory.setItem(j, this.noRecipe);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 138 */         RecipeRegistry recipeRegistry = (RecipeRegistry)hashMap.get(Integer.valueOf(i));
/*     */ 
/*     */         
/* 141 */         paramInventory.setItem(j, recipeRegistry.getDisplayListItem());
/*     */ 
/*     */         
/* 144 */         this.recipeTypeMap.put(Integer.valueOf(j), recipeRegistry);
/*     */       } 
/*     */     } 
/*     */   } @NotNull
/*     */   public Inventory getInventory() { Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Choose Recipe Type");
/*     */     addEditionInventoryItems(inventory, true);
/*     */     arrangeInventory(inventory);
/* 151 */     return inventory; } public static int page(int paramInt) { int i = SilentNumbers.floor(paramInt / 21.0D);
/* 152 */     paramInt -= i * 21;
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
/* 163 */     int j = SilentNumbers.floor(paramInt / 7.0D);
/* 164 */     int k = paramInt - 7 * j;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 169 */     int m = 18 + j * 9;
/* 170 */     int n = k + 1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     return m + n; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 182 */     if (paramInventoryClickEvent.getView().getTopInventory() != paramInventoryClickEvent.getClickedInventory()) {
/*     */       return;
/*     */     }
/* 185 */     paramInventoryClickEvent.setCancelled(true);
/*     */ 
/*     */     
/* 188 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL)
/*     */     {
/*     */       
/* 191 */       if (paramInventoryClickEvent.getSlot() == 27) {
/*     */ 
/*     */         
/* 194 */         this.currentPage--;
/* 195 */         arrangeInventory(paramInventoryClickEvent.getView().getTopInventory());
/*     */       
/*     */       }
/* 198 */       else if (paramInventoryClickEvent.getSlot() == 36) {
/*     */ 
/*     */         
/* 201 */         this.currentPage++;
/* 202 */         arrangeInventory(paramInventoryClickEvent.getView().getTopInventory());
/*     */       
/*     */       }
/* 205 */       else if (paramInventoryClickEvent.getSlot() > 18) {
/*     */ 
/*     */         
/* 208 */         RecipeRegistry recipeRegistry = this.recipeTypeMap.get(Integer.valueOf(paramInventoryClickEvent.getSlot()));
/*     */ 
/*     */         
/* 211 */         if (recipeRegistry != null)
/*     */         {
/*     */           
/* 214 */           (new RecipeListGUI(this.player, this.template, recipeRegistry)).open(getPreviousPage());
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerNativeRecipes() {
/* 230 */     registerRecipe((RecipeRegistry)new RMGRR_Smithing());
/* 231 */     registerRecipe((RecipeRegistry)new RMGRR_Shapeless());
/* 232 */     registerRecipe((RecipeRegistry)new RMGRR_Shaped());
/* 233 */     registerRecipe((RecipeRegistry)new RMGRR_SuperShaped());
/* 234 */     registerRecipe((RecipeRegistry)new RMGRR_MegaShaped());
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
/* 245 */     registerRecipe((RecipeRegistry)new RMGRR_LBFurnace());
/* 246 */     registerRecipe((RecipeRegistry)new RMGRR_LBBlast());
/* 247 */     registerRecipe((RecipeRegistry)new RMGRR_LBSmoker());
/* 248 */     registerRecipe((RecipeRegistry)new RMGRR_LBCampfire());
/*     */   }
/*     */   
/*     */   @NotNull
/* 252 */   static final HashMap<String, RecipeRegistry> registeredRecipes = new HashMap<>();
/*     */ 
/*     */   
/*     */   public static void registerRecipe(@NotNull RecipeRegistry paramRecipeRegistry) {
/* 256 */     registeredRecipes.put(paramRecipeRegistry.getRecipeConfigPath(), paramRecipeRegistry);
/*     */   } @NotNull
/*     */   public static Set<String> getRegisteredRecipes() {
/* 259 */     return registeredRecipes.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static RecipeRegistry getRegisteredRecipe(@NotNull String paramString) {
/* 266 */     return registeredRecipes.get(paramString);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\RecipeBrowserGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */