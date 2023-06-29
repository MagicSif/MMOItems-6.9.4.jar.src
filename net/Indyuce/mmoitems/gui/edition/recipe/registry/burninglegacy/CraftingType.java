/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.registry.burninglegacy;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.manager.RecipeManager;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public enum CraftingType {
/* 12 */   SHAPED(21, "The C. Table Recipe (Shaped) for this item", VersionMaterial.CRAFTING_TABLE, null, new int[0]),
/* 13 */   SHAPELESS(22, "The C. Table Recipe (Shapeless) for this item", VersionMaterial.CRAFTING_TABLE, null, new int[0]),
/* 14 */   FURNACE(23, "The Furnace Recipe for this item", Material.FURNACE, RecipeManager.BurningRecipeType.FURNACE, new int[0]),
/* 15 */   BLAST(29, "The Blast Furnace Recipe for this item", VersionMaterial.BLAST_FURNACE, RecipeManager.BurningRecipeType.BLAST, new int[] { 1, 14 }),
/* 16 */   SMOKER(30, "The Smoker Recipe for this item", VersionMaterial.SMOKER, RecipeManager.BurningRecipeType.SMOKER, new int[] { 1, 14 }),
/* 17 */   CAMPFIRE(32, "The Campfire Recipe for this item", VersionMaterial.CAMPFIRE, RecipeManager.BurningRecipeType.CAMPFIRE, new int[] { 1, 14 }),
/* 18 */   SMITHING(33, "The Smithing Recipe for this item", VersionMaterial.SMITHING_TABLE, null, new int[] { 1, 15 });
/*    */   
/*    */   private final int slot;
/*    */   
/*    */   private final String lore;
/*    */   
/*    */   private final Material material;
/*    */   
/*    */   private final int[] mustBeHigher;
/*    */   
/*    */   private final RecipeManager.BurningRecipeType burning;
/*    */   
/*    */   CraftingType(int paramInt1, @Nullable String paramString1, Material paramMaterial, RecipeManager.BurningRecipeType paramBurningRecipeType, int... paramVarArgs) {
/* 31 */     this.slot = paramInt1;
/* 32 */     this.lore = paramString1;
/* 33 */     this.material = paramMaterial;
/* 34 */     this.mustBeHigher = paramVarArgs;
/* 35 */     this.burning = paramBurningRecipeType;
/*    */   }
/*    */   
/*    */   public ItemStack getItem() {
/* 39 */     return new ItemStack(this.material);
/*    */   }
/*    */   
/*    */   public int getSlot() {
/* 43 */     return this.slot;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 47 */     return MMOUtils.caseOnWords(name().toLowerCase());
/*    */   }
/*    */   
/*    */   public String getLore() {
/* 51 */     return this.lore;
/*    */   } public RecipeManager.BurningRecipeType getBurningType() {
/* 53 */     return this.burning;
/*    */   }
/*    */   public boolean shouldAdd() {
/* 56 */     return (this.mustBeHigher.length == 0 || MythicLib.plugin.getVersion().isStrictlyHigher(this.mustBeHigher));
/*    */   }
/*    */   
/*    */   public static CraftingType getBySlot(int paramInt) {
/* 60 */     for (CraftingType craftingType : values()) {
/* 61 */       if (craftingType.getSlot() == paramInt)
/* 62 */         return craftingType; 
/* 63 */     }  return null;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\registry\burninglegacy\CraftingType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */