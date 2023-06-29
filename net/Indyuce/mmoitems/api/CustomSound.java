/*    */ package net.Indyuce.mmoitems.api;
/*    */ 
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public enum CustomSound
/*    */ {
/* 10 */   ON_ATTACK(Material.IRON_SWORD, 19, new String[] { "Plays when attacking an entity." }),
/* 11 */   ON_RIGHT_CLICK(Material.STONE_HOE, 22, new String[] { "Plays when item is right-clicked." }),
/* 12 */   ON_BLOCK_BREAK(Material.COBBLESTONE, 25, new String[] { "Plays when a block is broken with the item." }),
/* 13 */   ON_PICKUP(Material.IRON_INGOT, 28, new String[] { "Plays when you pickup the item from the ground." }),
/* 14 */   ON_LEFT_CLICK(Material.STONE_AXE, 31, new String[] { "Plays when item is left-clicked." }),
/* 15 */   ON_CRAFT(VersionMaterial.CRAFTING_TABLE.toMaterial(), 34, new String[] { "Plays when item is crafted in a crafting inventory", "or when smelted from someting in a furnace." }),
/* 16 */   ON_CONSUME(Material.APPLE, 37, new String[] { "Plays when item has been consumed.", "(After eating/drinking animation)" }),
/* 17 */   ON_ITEM_BREAK(Material.FLINT, 40, new String[] { "Plays when the item breaks." }),
/* 18 */   ON_CROSSBOW(Material.ARROW, 38, new String[] { "Plays when a crossbow shoots an arrow." }),
/* 19 */   ON_PLACED(Material.STONE, 43, new String[] { "Plays when the block is placed." });
/*    */   
/*    */   private final ItemStack item;
/*    */   private final String[] lore;
/*    */   private final int slot;
/*    */   
/*    */   CustomSound(Material paramMaterial, int paramInt1, String... paramVarArgs) {
/* 26 */     this.item = new ItemStack(paramMaterial);
/* 27 */     this.lore = paramVarArgs;
/* 28 */     this.slot = paramInt1;
/*    */   }
/*    */   
/*    */   public ItemStack getItem() {
/* 32 */     return this.item;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 36 */     return MMOUtils.caseOnWords(name().toLowerCase().replace('_', ' '));
/*    */   }
/*    */   
/*    */   public String[] getLore() {
/* 40 */     return this.lore;
/*    */   }
/*    */   
/*    */   public int getSlot() {
/* 44 */     return this.slot;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\CustomSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */