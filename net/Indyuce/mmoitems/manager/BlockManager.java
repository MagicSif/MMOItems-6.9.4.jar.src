/*     */ package net.Indyuce.mmoitems.manager;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.block.CustomBlock;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.util.MushroomState;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.block.data.MultipleFacing;
/*     */ 
/*     */ public class BlockManager
/*     */   implements Reloadable {
/*  26 */   private static final List<Integer> downIds = Arrays.asList(new Integer[] { Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(26), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29), Integer.valueOf(30), Integer.valueOf(31), Integer.valueOf(32), Integer.valueOf(33), Integer.valueOf(34), Integer.valueOf(35), Integer.valueOf(36), Integer.valueOf(37), Integer.valueOf(38), Integer.valueOf(39), Integer.valueOf(40), 
/*  27 */         Integer.valueOf(41), Integer.valueOf(42), Integer.valueOf(43), Integer.valueOf(44), Integer.valueOf(45), Integer.valueOf(46), Integer.valueOf(47), Integer.valueOf(48), Integer.valueOf(49), Integer.valueOf(50), Integer.valueOf(51), Integer.valueOf(52), Integer.valueOf(53), Integer.valueOf(69), Integer.valueOf(70), Integer.valueOf(71), Integer.valueOf(72), Integer.valueOf(73), Integer.valueOf(74), Integer.valueOf(75), Integer.valueOf(76), Integer.valueOf(77), Integer.valueOf(78), Integer.valueOf(79), Integer.valueOf(80), Integer.valueOf(81), Integer.valueOf(82), Integer.valueOf(83), Integer.valueOf(84), Integer.valueOf(85), Integer.valueOf(86), Integer.valueOf(87), Integer.valueOf(88), Integer.valueOf(89), 
/*  28 */         Integer.valueOf(90), Integer.valueOf(91), Integer.valueOf(92), Integer.valueOf(93), Integer.valueOf(94), Integer.valueOf(95), Integer.valueOf(96), Integer.valueOf(97), Integer.valueOf(98), Integer.valueOf(99), Integer.valueOf(130), Integer.valueOf(131), Integer.valueOf(132), Integer.valueOf(133), Integer.valueOf(134), Integer.valueOf(135), Integer.valueOf(136), Integer.valueOf(137), Integer.valueOf(138), Integer.valueOf(139), Integer.valueOf(140), Integer.valueOf(141), Integer.valueOf(142), Integer.valueOf(143), Integer.valueOf(144), Integer.valueOf(145), Integer.valueOf(146), Integer.valueOf(147), Integer.valueOf(148), 
/*  29 */         Integer.valueOf(149), Integer.valueOf(150), Integer.valueOf(151), Integer.valueOf(152), Integer.valueOf(153), Integer.valueOf(154), Integer.valueOf(155), Integer.valueOf(156), Integer.valueOf(157), Integer.valueOf(158), Integer.valueOf(159), Integer.valueOf(160) });
/*     */   
/*  31 */   private static final List<Integer> eastIds = Arrays.asList(new Integer[] { Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(39), Integer.valueOf(40), Integer.valueOf(41), Integer.valueOf(42), Integer.valueOf(43), Integer.valueOf(44), Integer.valueOf(45), Integer.valueOf(46), Integer.valueOf(47), Integer.valueOf(48), Integer.valueOf(49), Integer.valueOf(50), Integer.valueOf(51), Integer.valueOf(52), Integer.valueOf(53), 
/*  32 */         Integer.valueOf(59), Integer.valueOf(60), Integer.valueOf(61), Integer.valueOf(62), Integer.valueOf(63), Integer.valueOf(64), Integer.valueOf(65), Integer.valueOf(66), Integer.valueOf(67), Integer.valueOf(68), Integer.valueOf(85), Integer.valueOf(86), Integer.valueOf(87), Integer.valueOf(88), Integer.valueOf(89), Integer.valueOf(90), Integer.valueOf(91), Integer.valueOf(92), Integer.valueOf(93), Integer.valueOf(94), Integer.valueOf(95), Integer.valueOf(96), Integer.valueOf(97), Integer.valueOf(98), Integer.valueOf(99), Integer.valueOf(115), Integer.valueOf(116), Integer.valueOf(117), Integer.valueOf(118), Integer.valueOf(119), Integer.valueOf(120), 
/*  33 */         Integer.valueOf(121), Integer.valueOf(122), Integer.valueOf(123), Integer.valueOf(124), Integer.valueOf(125), Integer.valueOf(126), Integer.valueOf(127), Integer.valueOf(128), Integer.valueOf(129), Integer.valueOf(146), Integer.valueOf(147), Integer.valueOf(148), Integer.valueOf(149), Integer.valueOf(150), Integer.valueOf(151), Integer.valueOf(152), Integer.valueOf(153), Integer.valueOf(154), Integer.valueOf(155), Integer.valueOf(156), Integer.valueOf(157), Integer.valueOf(158), Integer.valueOf(159), Integer.valueOf(160) });
/*     */   
/*  35 */   private static final List<Integer> northIds = Arrays.asList(new Integer[] { Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(31), Integer.valueOf(32), Integer.valueOf(33), Integer.valueOf(34), Integer.valueOf(35), Integer.valueOf(36), Integer.valueOf(37), Integer.valueOf(38), Integer.valueOf(47), Integer.valueOf(48), Integer.valueOf(49), Integer.valueOf(50), Integer.valueOf(51), Integer.valueOf(52), Integer.valueOf(53), Integer.valueOf(55), Integer.valueOf(56), 
/*  36 */         Integer.valueOf(57), Integer.valueOf(58), Integer.valueOf(63), Integer.valueOf(64), Integer.valueOf(65), Integer.valueOf(66), Integer.valueOf(67), Integer.valueOf(68), Integer.valueOf(77), Integer.valueOf(78), Integer.valueOf(79), Integer.valueOf(80), Integer.valueOf(81), Integer.valueOf(82), Integer.valueOf(83), Integer.valueOf(84), Integer.valueOf(93), Integer.valueOf(94), Integer.valueOf(95), Integer.valueOf(96), Integer.valueOf(97), Integer.valueOf(98), Integer.valueOf(99), Integer.valueOf(107), Integer.valueOf(108), Integer.valueOf(109), Integer.valueOf(110), Integer.valueOf(111), Integer.valueOf(112), Integer.valueOf(113), 
/*  37 */         Integer.valueOf(114), Integer.valueOf(123), Integer.valueOf(124), Integer.valueOf(125), Integer.valueOf(126), Integer.valueOf(127), Integer.valueOf(128), Integer.valueOf(129), Integer.valueOf(138), Integer.valueOf(139), Integer.valueOf(140), Integer.valueOf(141), Integer.valueOf(142), Integer.valueOf(143), Integer.valueOf(144), Integer.valueOf(145), Integer.valueOf(154), Integer.valueOf(155), Integer.valueOf(156), Integer.valueOf(157), Integer.valueOf(158), Integer.valueOf(159), Integer.valueOf(160) });
/*     */   
/*  39 */   private static final List<Integer> southIds = Arrays.asList(new Integer[] { Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(29), Integer.valueOf(30), Integer.valueOf(35), Integer.valueOf(36), Integer.valueOf(37), Integer.valueOf(38), Integer.valueOf(43), Integer.valueOf(44), Integer.valueOf(45), Integer.valueOf(46), Integer.valueOf(51), Integer.valueOf(52), Integer.valueOf(53), Integer.valueOf(55), Integer.valueOf(56), 
/*  40 */         Integer.valueOf(57), Integer.valueOf(58), Integer.valueOf(61), Integer.valueOf(62), Integer.valueOf(65), Integer.valueOf(66), Integer.valueOf(67), Integer.valueOf(68), Integer.valueOf(73), Integer.valueOf(74), Integer.valueOf(75), Integer.valueOf(76), Integer.valueOf(81), Integer.valueOf(82), Integer.valueOf(83), Integer.valueOf(84), Integer.valueOf(89), Integer.valueOf(90), Integer.valueOf(91), Integer.valueOf(92), Integer.valueOf(97), Integer.valueOf(98), Integer.valueOf(99), Integer.valueOf(103), Integer.valueOf(104), Integer.valueOf(105), Integer.valueOf(106), Integer.valueOf(111), Integer.valueOf(112), Integer.valueOf(113), 
/*  41 */         Integer.valueOf(114), Integer.valueOf(119), Integer.valueOf(120), Integer.valueOf(121), Integer.valueOf(122), Integer.valueOf(127), Integer.valueOf(128), Integer.valueOf(129), Integer.valueOf(134), Integer.valueOf(135), Integer.valueOf(136), Integer.valueOf(137), Integer.valueOf(142), Integer.valueOf(143), Integer.valueOf(144), Integer.valueOf(145), Integer.valueOf(150), Integer.valueOf(151), Integer.valueOf(152), Integer.valueOf(153), Integer.valueOf(158), Integer.valueOf(159), Integer.valueOf(160) });
/*  42 */   private static final List<Integer> upIds = Arrays.asList(new Integer[] { Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(12), Integer.valueOf(15), Integer.valueOf(18), Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(25), Integer.valueOf(26), Integer.valueOf(29), Integer.valueOf(30), Integer.valueOf(33), Integer.valueOf(34), Integer.valueOf(37), Integer.valueOf(38), Integer.valueOf(41), Integer.valueOf(42), Integer.valueOf(45), Integer.valueOf(46), 
/*  43 */         Integer.valueOf(49), Integer.valueOf(50), Integer.valueOf(53), Integer.valueOf(57), Integer.valueOf(58), Integer.valueOf(60), Integer.valueOf(62), Integer.valueOf(64), Integer.valueOf(67), Integer.valueOf(68), Integer.valueOf(71), Integer.valueOf(72), Integer.valueOf(75), Integer.valueOf(76), Integer.valueOf(79), Integer.valueOf(80), Integer.valueOf(83), Integer.valueOf(84), Integer.valueOf(87), Integer.valueOf(88), Integer.valueOf(91), Integer.valueOf(92), Integer.valueOf(95), Integer.valueOf(96), Integer.valueOf(99), Integer.valueOf(101), Integer.valueOf(102), Integer.valueOf(105), Integer.valueOf(106), Integer.valueOf(109), Integer.valueOf(110), Integer.valueOf(113), 
/*  44 */         Integer.valueOf(114), Integer.valueOf(117), Integer.valueOf(118), Integer.valueOf(121), Integer.valueOf(122), Integer.valueOf(125), Integer.valueOf(126), Integer.valueOf(128), Integer.valueOf(129), Integer.valueOf(132), Integer.valueOf(133), Integer.valueOf(136), Integer.valueOf(137), Integer.valueOf(140), Integer.valueOf(141), Integer.valueOf(144), Integer.valueOf(145), Integer.valueOf(148), Integer.valueOf(149), Integer.valueOf(152), Integer.valueOf(153), Integer.valueOf(156), Integer.valueOf(157), Integer.valueOf(160) });
/*     */   
/*  46 */   private static final List<Integer> westIds = Arrays.asList(new Integer[] { Integer.valueOf(1), Integer.valueOf(3), Integer.valueOf(5), Integer.valueOf(7), Integer.valueOf(9), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(20), Integer.valueOf(22), Integer.valueOf(24), Integer.valueOf(26), Integer.valueOf(28), Integer.valueOf(30), Integer.valueOf(32), Integer.valueOf(34), Integer.valueOf(36), Integer.valueOf(38), Integer.valueOf(40), Integer.valueOf(42), Integer.valueOf(44), Integer.valueOf(46), Integer.valueOf(48), Integer.valueOf(50), Integer.valueOf(52), Integer.valueOf(56), Integer.valueOf(58), 
/*  47 */         Integer.valueOf(59), Integer.valueOf(60), Integer.valueOf(61), Integer.valueOf(62), Integer.valueOf(63), Integer.valueOf(64), Integer.valueOf(66), Integer.valueOf(68), Integer.valueOf(70), Integer.valueOf(72), Integer.valueOf(74), Integer.valueOf(76), Integer.valueOf(78), Integer.valueOf(80), Integer.valueOf(82), Integer.valueOf(84), Integer.valueOf(86), Integer.valueOf(88), Integer.valueOf(90), Integer.valueOf(92), Integer.valueOf(94), Integer.valueOf(96), Integer.valueOf(98), Integer.valueOf(100), Integer.valueOf(102), Integer.valueOf(104), Integer.valueOf(106), Integer.valueOf(108), Integer.valueOf(110), Integer.valueOf(112), 
/*  48 */         Integer.valueOf(114), Integer.valueOf(116), Integer.valueOf(118), Integer.valueOf(120), Integer.valueOf(122), Integer.valueOf(124), Integer.valueOf(126), Integer.valueOf(129), Integer.valueOf(131), Integer.valueOf(133), Integer.valueOf(135), Integer.valueOf(137), Integer.valueOf(139), Integer.valueOf(141), Integer.valueOf(143), Integer.valueOf(145), Integer.valueOf(147), Integer.valueOf(149), Integer.valueOf(151), Integer.valueOf(153), Integer.valueOf(155), Integer.valueOf(157), Integer.valueOf(159) });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private final Map<Integer, CustomBlock> customBlocks = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   private final Map<Integer, CustomBlock> mushroomStateValue = new HashMap<>();
/*     */   
/*     */   public BlockManager() {
/*  62 */     reload();
/*     */   }
/*     */   
/*     */   public void register(CustomBlock paramCustomBlock) {
/*  66 */     this.customBlocks.put(Integer.valueOf(paramCustomBlock.getId()), paramCustomBlock);
/*  67 */     this.mushroomStateValue.put(Integer.valueOf(paramCustomBlock.getState().getUniqueId()), paramCustomBlock);
/*  68 */     if (paramCustomBlock.hasGenTemplate())
/*  69 */       MMOItems.plugin.getWorldGen().assign(paramCustomBlock, paramCustomBlock.getGenTemplate()); 
/*     */   }
/*     */   
/*     */   public CustomBlock getBlock(int paramInt) {
/*  73 */     return (paramInt > 0 && paramInt < 161 && paramInt != 54) ? this.customBlocks.get(Integer.valueOf(paramInt)) : null;
/*     */   }
/*     */   
/*     */   public CustomBlock getBlock(MushroomState paramMushroomState) {
/*  77 */     return this.mushroomStateValue.get(Integer.valueOf(paramMushroomState.getUniqueId()));
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<CustomBlock> getFromBlock(BlockData paramBlockData) {
/*  82 */     if (!isMushroomBlock(paramBlockData.getMaterial()) || !(paramBlockData instanceof MultipleFacing)) {
/*  83 */       return Optional.empty();
/*     */     }
/*  85 */     MultipleFacing multipleFacing = (MultipleFacing)paramBlockData;
/*     */     
/*  87 */     MushroomState mushroomState = new MushroomState(paramBlockData.getMaterial(), multipleFacing.hasFace(BlockFace.UP), multipleFacing.hasFace(BlockFace.DOWN), multipleFacing.hasFace(BlockFace.WEST), multipleFacing.hasFace(BlockFace.EAST), multipleFacing.hasFace(BlockFace.SOUTH), multipleFacing.hasFace(BlockFace.NORTH));
/*     */     
/*  89 */     return isVanilla(mushroomState) ? Optional.<CustomBlock>empty() : Optional.<CustomBlock>of(getBlock(mushroomState));
/*     */   }
/*     */   
/*     */   public Collection<CustomBlock> getAll() {
/*  93 */     return this.customBlocks.values();
/*     */   }
/*     */   
/*     */   public Set<Integer> getBlockIds() {
/*  97 */     return this.customBlocks.keySet();
/*     */   }
/*     */   
/*     */   public boolean isVanilla(MushroomState paramMushroomState) {
/* 101 */     return !this.mushroomStateValue.containsKey(Integer.valueOf(paramMushroomState.getUniqueId()));
/*     */   }
/*     */   
/*     */   public boolean isMushroomBlock(Material paramMaterial) {
/* 105 */     return (paramMaterial == Material.BROWN_MUSHROOM_BLOCK || paramMaterial == Material.MUSHROOM_STEM || paramMaterial == Material.RED_MUSHROOM_BLOCK);
/*     */   }
/*     */   
/*     */   public void reload() {
/* 109 */     this.customBlocks.clear();
/* 110 */     this.mushroomStateValue.clear();
/*     */     
/* 112 */     for (MMOItemTemplate mMOItemTemplate : MMOItems.plugin.getTemplates().getTemplates(Type.BLOCK)) {
/* 113 */       MMOItem mMOItem = mMOItemTemplate.newBuilder(0, null).build();
/* 114 */       byte b = mMOItem.hasData(ItemStats.BLOCK_ID) ? (int)((DoubleData)mMOItem.getData(ItemStats.BLOCK_ID)).getValue() : 0;
/* 115 */       if (b && b < '¡' && b != 54)
/*     */         
/*     */         try {
/* 118 */           MushroomState mushroomState = new MushroomState(getType(b), upIds.contains(Integer.valueOf(b)), downIds.contains(Integer.valueOf(b)), westIds.contains(Integer.valueOf(b)), eastIds.contains(Integer.valueOf(b)), southIds.contains(Integer.valueOf(b)), northIds.contains(Integer.valueOf(b)));
/* 119 */           register(new CustomBlock(mushroomState, mMOItem));
/* 120 */         } catch (IllegalArgumentException illegalArgumentException) {
/* 121 */           MMOItems.plugin.getLogger().log(Level.WARNING, "Could not load custom block '" + b + "': " + illegalArgumentException.getMessage());
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   private Material getType(int paramInt) {
/* 127 */     return (paramInt < 54) ? Material.BROWN_MUSHROOM_BLOCK : ((paramInt > 99) ? Material.MUSHROOM_STEM : Material.RED_MUSHROOM_BLOCK);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\BlockManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */