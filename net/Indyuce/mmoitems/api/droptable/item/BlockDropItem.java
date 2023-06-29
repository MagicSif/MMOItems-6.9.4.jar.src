/*    */ package net.Indyuce.mmoitems.api.droptable.item;
/*    */ 
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class BlockDropItem
/*    */   extends DropItem {
/*    */   private final int blockId;
/*    */   
/*    */   public BlockDropItem(int paramInt, String paramString) {
/* 12 */     super(paramString);
/*    */     
/* 14 */     this.blockId = paramInt;
/*    */   }
/*    */   
/*    */   public int getBlockId() {
/* 18 */     return this.blockId;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItem(PlayerData paramPlayerData, int paramInt) {
/* 23 */     return MMOItems.plugin.getCustomBlocks().getBlock(this.blockId).getItem();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getKey() {
/* 28 */     return "block." + getBlockId();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\droptable\item\BlockDropItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */