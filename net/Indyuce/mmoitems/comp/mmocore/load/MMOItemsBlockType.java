/*    */ package net.Indyuce.mmoitems.comp.mmocore.load;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmocore.api.block.BlockInfo;
/*    */ import net.Indyuce.mmocore.api.block.BlockType;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.block.CustomBlock;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public class MMOItemsBlockType implements BlockType {
/*    */   private final int id;
/*    */   
/*    */   public MMOItemsBlockType(MMOLineConfig paramMMOLineConfig) {
/* 15 */     paramMMOLineConfig.validate(new String[] { "id" });
/*    */     
/* 17 */     this.id = paramMMOLineConfig.getInt("id");
/*    */   }
/*    */   
/*    */   public MMOItemsBlockType(CustomBlock paramCustomBlock) {
/* 21 */     this.id = paramCustomBlock.getId();
/*    */   }
/*    */   
/*    */   public int getBlockId() {
/* 25 */     return this.id;
/*    */   }
/*    */   
/*    */   public static boolean matches(Block paramBlock) {
/* 29 */     return MMOItems.plugin.getCustomBlocks().isMushroomBlock(paramBlock.getType());
/*    */   }
/*    */ 
/*    */   
/*    */   public void place(BlockInfo.RegeneratingBlock paramRegeneratingBlock) {
/* 34 */     Location location = paramRegeneratingBlock.getLocation();
/* 35 */     CustomBlock customBlock = MMOItems.plugin.getCustomBlocks().getBlock(this.id);
/*    */     
/* 37 */     location.getBlock().setType(customBlock.getState().getType());
/* 38 */     location.getBlock().setBlockData(customBlock.getState().getBlockData());
/*    */   }
/*    */ 
/*    */   
/*    */   public void regenerate(BlockInfo.RegeneratingBlock paramRegeneratingBlock) {
/* 43 */     place(paramRegeneratingBlock);
/*    */   }
/*    */ 
/*    */   
/*    */   public String generateKey() {
/* 48 */     return "mmoitems-custom-block-" + this.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean breakRestrictions(Block paramBlock) {
/* 53 */     return true;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\load\MMOItemsBlockType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */