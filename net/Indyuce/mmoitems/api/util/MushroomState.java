/*    */ package net.Indyuce.mmoitems.api.util;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.BlockData;
/*    */ import org.bukkit.block.data.MultipleFacing;
/*    */ 
/*    */ public class MushroomState {
/*    */   private final Material material;
/*    */   private final boolean up;
/*    */   private final boolean down;
/*    */   private final boolean west;
/*    */   private final boolean east;
/*    */   private final boolean south;
/*    */   private final boolean north;
/*    */   
/*    */   public MushroomState(Material paramMaterial, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6) {
/* 18 */     this.material = paramMaterial;
/* 19 */     this.up = paramBoolean1;
/* 20 */     this.down = paramBoolean2;
/* 21 */     this.west = paramBoolean3;
/* 22 */     this.east = paramBoolean4;
/* 23 */     this.south = paramBoolean5;
/* 24 */     this.north = paramBoolean6;
/*    */   }
/*    */   
/*    */   public int getUniqueId() {
/* 28 */     String str = (this.material == Material.BROWN_MUSHROOM_BLOCK) ? "0" : ((this.material == Material.RED_MUSHROOM_BLOCK) ? "1" : "2");
/* 29 */     return Integer.parseInt(str + (
/* 30 */         this.up ? "1" : "0") + (this.down ? "1" : "0") + (this.west ? "1" : "0") + (this.east ? "1" : "0") + (this.south ? "1" : "0") + (this.north ? "1" : "0"));
/*    */   }
/*    */   
/*    */   public boolean matches(MushroomState paramMushroomState) {
/* 34 */     return (this.up == paramMushroomState.up && this.down == paramMushroomState.down && this.west == paramMushroomState.west && this.east == paramMushroomState.east && this.south == paramMushroomState.south && this.north == paramMushroomState.north);
/*    */   }
/*    */   
/*    */   public Material getType() {
/* 38 */     return this.material;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockData getBlockData() {
/* 44 */     MultipleFacing multipleFacing = (MultipleFacing)this.material.createBlockData();
/*    */     
/* 46 */     multipleFacing.setFace(BlockFace.UP, this.up);
/* 47 */     multipleFacing.setFace(BlockFace.DOWN, this.down);
/* 48 */     multipleFacing.setFace(BlockFace.NORTH, this.north);
/* 49 */     multipleFacing.setFace(BlockFace.SOUTH, this.south);
/* 50 */     multipleFacing.setFace(BlockFace.EAST, this.east);
/* 51 */     multipleFacing.setFace(BlockFace.WEST, this.west);
/*    */     
/* 53 */     return (BlockData)multipleFacing;
/*    */   }
/*    */   
/*    */   public boolean getSide(String paramString) {
/* 57 */     paramString = paramString.toLowerCase();
/* 58 */     switch (paramString) {
/*    */       case "up":
/* 60 */         return this.up;
/*    */       case "down":
/* 62 */         return this.down;
/*    */       case "north":
/* 64 */         return this.north;
/*    */       case "south":
/* 66 */         return this.south;
/*    */       case "east":
/* 68 */         return this.east;
/*    */       case "west":
/* 70 */         return this.west;
/*    */     } 
/* 72 */     return false;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\ap\\util\MushroomState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */