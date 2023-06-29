/*    */ package net.Indyuce.mmoitems.comp;
/*    */ 
/*    */ import com.sk89q.worldedit.WorldEdit;
/*    */ import com.sk89q.worldedit.extension.input.ParserContext;
/*    */ import com.sk89q.worldedit.internal.registry.InputParser;
/*    */ import com.sk89q.worldedit.world.block.BaseBlock;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldEditSupport
/*    */ {
/*    */   public WorldEditSupport() {
/* 17 */     WorldEdit.getInstance().getBlockFactory().register(new WECustomBlockInputParser());
/*    */   }
/*    */   
/*    */   public class WECustomBlockInputParser extends InputParser<BaseBlock> {
/*    */     public WECustomBlockInputParser() {
/* 22 */       super(WorldEdit.getInstance());
/*    */     }
/*    */     
/*    */     public BaseBlock parseFromInput(String param1String, ParserContext param1ParserContext) {
/*    */       // Byte code:
/*    */       //   0: aload_1
/*    */       //   1: invokevirtual toLowerCase : ()Ljava/lang/String;
/*    */       //   4: astore_1
/*    */       //   5: aload_1
/*    */       //   6: ldc 'mmoitems-'
/*    */       //   8: invokevirtual startsWith : (Ljava/lang/String;)Z
/*    */       //   11: ifne -> 16
/*    */       //   14: aconst_null
/*    */       //   15: areturn
/*    */       //   16: aload_1
/*    */       //   17: ldc '-'
/*    */       //   19: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
/*    */       //   22: iconst_1
/*    */       //   23: aaload
/*    */       //   24: invokestatic parseInt : (Ljava/lang/String;)I
/*    */       //   27: istore #4
/*    */       //   29: goto -> 36
/*    */       //   32: astore #5
/*    */       //   34: aconst_null
/*    */       //   35: areturn
/*    */       //   36: getstatic net/Indyuce/mmoitems/MMOItems.plugin : Lnet/Indyuce/mmoitems/MMOItems;
/*    */       //   39: invokevirtual getCustomBlocks : ()Lnet/Indyuce/mmoitems/manager/BlockManager;
/*    */       //   42: iload #4
/*    */       //   44: invokevirtual getBlock : (I)Lnet/Indyuce/mmoitems/api/block/CustomBlock;
/*    */       //   47: astore #5
/*    */       //   49: aload #5
/*    */       //   51: ifnonnull -> 56
/*    */       //   54: aconst_null
/*    */       //   55: areturn
/*    */       //   56: aload #5
/*    */       //   58: invokevirtual getState : ()Lnet/Indyuce/mmoitems/api/util/MushroomState;
/*    */       //   61: astore #6
/*    */       //   63: getstatic net/Indyuce/mmoitems/comp/WorldEditSupport$1.$SwitchMap$org$bukkit$Material : [I
/*    */       //   66: aload #6
/*    */       //   68: invokevirtual getType : ()Lorg/bukkit/Material;
/*    */       //   71: invokevirtual ordinal : ()I
/*    */       //   74: iaload
/*    */       //   75: tableswitch default -> 121, 1 -> 100, 2 -> 107, 3 -> 114
/*    */       //   100: getstatic com/sk89q/worldedit/world/block/BlockTypes.MUSHROOM_STEM : Lcom/sk89q/worldedit/world/block/BlockType;
/*    */       //   103: astore_3
/*    */       //   104: goto -> 123
/*    */       //   107: getstatic com/sk89q/worldedit/world/block/BlockTypes.BROWN_MUSHROOM_BLOCK : Lcom/sk89q/worldedit/world/block/BlockType;
/*    */       //   110: astore_3
/*    */       //   111: goto -> 123
/*    */       //   114: getstatic com/sk89q/worldedit/world/block/BlockTypes.RED_MUSHROOM_BLOCK : Lcom/sk89q/worldedit/world/block/BlockType;
/*    */       //   117: astore_3
/*    */       //   118: goto -> 123
/*    */       //   121: aconst_null
/*    */       //   122: areturn
/*    */       //   123: aload_3
/*    */       //   124: invokevirtual getDefaultState : ()Lcom/sk89q/worldedit/world/block/BlockState;
/*    */       //   127: aload_3
/*    */       //   128: invokevirtual getPropertyMap : ()Ljava/util/Map;
/*    */       //   131: ldc 'up'
/*    */       //   133: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
/*    */       //   138: checkcast com/sk89q/worldedit/registry/state/Property
/*    */       //   141: aload #6
/*    */       //   143: ldc 'up'
/*    */       //   145: invokevirtual getSide : (Ljava/lang/String;)Z
/*    */       //   148: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*    */       //   151: invokevirtual with : (Lcom/sk89q/worldedit/registry/state/Property;Ljava/lang/Object;)Lcom/sk89q/worldedit/world/block/BlockState;
/*    */       //   154: aload_3
/*    */       //   155: invokevirtual getPropertyMap : ()Ljava/util/Map;
/*    */       //   158: ldc 'down'
/*    */       //   160: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
/*    */       //   165: checkcast com/sk89q/worldedit/registry/state/Property
/*    */       //   168: aload #6
/*    */       //   170: ldc 'down'
/*    */       //   172: invokevirtual getSide : (Ljava/lang/String;)Z
/*    */       //   175: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*    */       //   178: invokevirtual with : (Lcom/sk89q/worldedit/registry/state/Property;Ljava/lang/Object;)Lcom/sk89q/worldedit/world/block/BlockState;
/*    */       //   181: aload_3
/*    */       //   182: invokevirtual getPropertyMap : ()Ljava/util/Map;
/*    */       //   185: ldc 'north'
/*    */       //   187: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
/*    */       //   192: checkcast com/sk89q/worldedit/registry/state/Property
/*    */       //   195: aload #6
/*    */       //   197: ldc 'north'
/*    */       //   199: invokevirtual getSide : (Ljava/lang/String;)Z
/*    */       //   202: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*    */       //   205: invokevirtual with : (Lcom/sk89q/worldedit/registry/state/Property;Ljava/lang/Object;)Lcom/sk89q/worldedit/world/block/BlockState;
/*    */       //   208: aload_3
/*    */       //   209: invokevirtual getPropertyMap : ()Ljava/util/Map;
/*    */       //   212: ldc 'south'
/*    */       //   214: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
/*    */       //   219: checkcast com/sk89q/worldedit/registry/state/Property
/*    */       //   222: aload #6
/*    */       //   224: ldc 'south'
/*    */       //   226: invokevirtual getSide : (Ljava/lang/String;)Z
/*    */       //   229: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*    */       //   232: invokevirtual with : (Lcom/sk89q/worldedit/registry/state/Property;Ljava/lang/Object;)Lcom/sk89q/worldedit/world/block/BlockState;
/*    */       //   235: aload_3
/*    */       //   236: invokevirtual getPropertyMap : ()Ljava/util/Map;
/*    */       //   239: ldc 'east'
/*    */       //   241: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
/*    */       //   246: checkcast com/sk89q/worldedit/registry/state/Property
/*    */       //   249: aload #6
/*    */       //   251: ldc 'east'
/*    */       //   253: invokevirtual getSide : (Ljava/lang/String;)Z
/*    */       //   256: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*    */       //   259: invokevirtual with : (Lcom/sk89q/worldedit/registry/state/Property;Ljava/lang/Object;)Lcom/sk89q/worldedit/world/block/BlockState;
/*    */       //   262: aload_3
/*    */       //   263: invokevirtual getPropertyMap : ()Ljava/util/Map;
/*    */       //   266: ldc 'west'
/*    */       //   268: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
/*    */       //   273: checkcast com/sk89q/worldedit/registry/state/Property
/*    */       //   276: aload #6
/*    */       //   278: ldc 'west'
/*    */       //   280: invokevirtual getSide : (Ljava/lang/String;)Z
/*    */       //   283: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*    */       //   286: invokevirtual with : (Lcom/sk89q/worldedit/registry/state/Property;Ljava/lang/Object;)Lcom/sk89q/worldedit/world/block/BlockState;
/*    */       //   289: astore #7
/*    */       //   291: aload #7
/*    */       //   293: invokevirtual toBaseBlock : ()Lcom/sk89q/worldedit/world/block/BaseBlock;
/*    */       //   296: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #28	-> 0
/*    */       //   #30	-> 5
/*    */       //   #31	-> 14
/*    */       //   #35	-> 16
/*    */       //   #38	-> 29
/*    */       //   #36	-> 32
/*    */       //   #37	-> 34
/*    */       //   #40	-> 36
/*    */       //   #41	-> 49
/*    */       //   #42	-> 54
/*    */       //   #43	-> 56
/*    */       //   #45	-> 63
/*    */       //   #47	-> 100
/*    */       //   #48	-> 104
/*    */       //   #50	-> 107
/*    */       //   #51	-> 111
/*    */       //   #53	-> 114
/*    */       //   #54	-> 118
/*    */       //   #56	-> 121
/*    */       //   #62	-> 123
/*    */       //   #63	-> 155
/*    */       //   #64	-> 182
/*    */       //   #65	-> 209
/*    */       //   #66	-> 236
/*    */       //   #67	-> 263
/*    */       //   #68	-> 291
/*    */       // Exception table:
/*    */       //   from	to	target	type
/*    */       //   16	29	32	java/lang/NumberFormatException
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\WorldEditSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */