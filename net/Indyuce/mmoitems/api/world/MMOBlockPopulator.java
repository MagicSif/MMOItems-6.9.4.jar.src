/*    */ package net.Indyuce.mmoitems.api.world;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.Random;
/*    */ import net.Indyuce.mmoitems.api.block.CustomBlock;
/*    */ import net.Indyuce.mmoitems.api.block.WorldGenTemplate;
/*    */ import net.Indyuce.mmoitems.manager.WorldGenManager;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.generator.BlockPopulator;
/*    */ import org.bukkit.generator.LimitedRegion;
/*    */ import org.bukkit.generator.WorldInfo;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MMOBlockPopulator
/*    */   extends BlockPopulator
/*    */ {
/* 26 */   private static final BlockFace[] faces = new BlockFace[] { BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST, BlockFace.EAST, BlockFace.DOWN, BlockFace.UP };
/*    */   
/*    */   private final WorldGenManager manager;
/*    */   private final World world;
/*    */   
/*    */   public MMOBlockPopulator(World paramWorld, WorldGenManager paramWorldGenManager) {
/* 32 */     this.manager = paramWorldGenManager;
/* 33 */     this.world = paramWorld;
/*    */   }
/*    */ 
/*    */   
/*    */   public void populate(@NotNull WorldInfo paramWorldInfo, @NotNull Random paramRandom, int paramInt1, int paramInt2, @NotNull LimitedRegion paramLimitedRegion) {
/* 38 */     Map map = this.manager.assigned();
/* 39 */     map.entrySet()
/* 40 */       .stream()
/* 41 */       .filter(paramEntry -> ((WorldGenTemplate)paramEntry.getValue()).canGenerateInWorld(paramWorldInfo.getName()))
/* 42 */       .filter(paramEntry -> (((WorldGenTemplate)paramEntry.getValue()).getMinDepth() >= paramWorldInfo.getMinHeight()))
/* 43 */       .filter(paramEntry -> (((WorldGenTemplate)paramEntry.getValue()).getMaxDepth() <= paramWorldInfo.getMaxHeight()))
/* 44 */       .forEach(paramEntry -> {
/*    */           CustomBlock customBlock = (CustomBlock)paramEntry.getKey();
/*    */           WorldGenTemplate worldGenTemplate = (WorldGenTemplate)paramEntry.getValue();
/*    */           if (paramRandom.nextDouble() > worldGenTemplate.getChunkChance())
/*    */             return; 
/*    */           for (byte b = 0; b < worldGenTemplate.getVeinCount(); b++) {
/*    */             int i = paramInt1 * 16 + paramRandom.nextInt(16);
/*    */             int j = paramRandom.nextInt(worldGenTemplate.getMaxDepth() - worldGenTemplate.getMinDepth() + 1) + worldGenTemplate.getMinDepth();
/*    */             int k = paramInt2 * 16 + paramRandom.nextInt(16);
/*    */             Location location = new Location(this.world, i, j, k);
/*    */             if (worldGenTemplate.canGenerate(location) && location.getWorld() != null) {
/*    */               Block block = location.getWorld().getBlockAt(location);
/*    */               for (byte b1 = 0; b1 < worldGenTemplate.getVeinSize(); b1++) {
/*    */                 if (paramLimitedRegion.isInRegion(block.getLocation())) {
/*    */                   if (worldGenTemplate.canReplace(paramLimitedRegion.getType(block.getLocation()))) {
/*    */                     paramLimitedRegion.setType(block.getLocation(), customBlock.getState().getType());
/*    */                     paramLimitedRegion.setBlockData(block.getLocation(), customBlock.getState().getBlockData());
/*    */                   } 
/*    */                   BlockFace blockFace = faces[paramRandom.nextInt(faces.length)];
/*    */                   block = block.getRelative(blockFace);
/*    */                 } 
/*    */               } 
/*    */             } 
/*    */           } 
/*    */         });
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\world\MMOBlockPopulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */