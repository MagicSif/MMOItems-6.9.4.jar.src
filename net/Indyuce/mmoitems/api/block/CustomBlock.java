/*    */ package net.Indyuce.mmoitems.api.block;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*    */ import net.Indyuce.mmoitems.api.util.MushroomState;
/*    */ import net.Indyuce.mmoitems.stat.data.BooleanData;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class CustomBlock
/*    */ {
/*    */   private final int id;
/*    */   private final MushroomState state;
/*    */   private final MMOItem mmoitem;
/*    */   @Nullable
/*    */   private final WorldGenTemplate template;
/*    */   private final int minExp;
/*    */   private final int maxExp;
/*    */   private final int requiredPower;
/*    */   private final boolean requirePowerToBreak;
/* 25 */   private static final Random RANDOM = new Random();
/*    */   
/*    */   public CustomBlock(MushroomState paramMushroomState, MMOItem paramMMOItem) {
/* 28 */     this.mmoitem = paramMMOItem;
/*    */     
/* 30 */     this.id = paramMMOItem.hasData(ItemStats.BLOCK_ID) ? (int)((DoubleData)paramMMOItem.getData(ItemStats.BLOCK_ID)).getValue() : 0;
/* 31 */     this.state = paramMushroomState;
/*    */     
/* 33 */     this.minExp = paramMMOItem.hasData(ItemStats.MIN_XP) ? (int)((DoubleData)paramMMOItem.getData(ItemStats.MIN_XP)).getValue() : 0;
/* 34 */     this.maxExp = paramMMOItem.hasData(ItemStats.MAX_XP) ? (int)((DoubleData)paramMMOItem.getData(ItemStats.MAX_XP)).getValue() : 0;
/* 35 */     this.requiredPower = paramMMOItem.hasData(ItemStats.REQUIRED_POWER) ? (int)((DoubleData)paramMMOItem.getData(ItemStats.REQUIRED_POWER)).getValue() : 0;
/* 36 */     this.requirePowerToBreak = (paramMMOItem.hasData(ItemStats.REQUIRE_POWER_TO_BREAK) && ((BooleanData)paramMMOItem.getData(ItemStats.REQUIRE_POWER_TO_BREAK)).isEnabled());
/* 37 */     this.template = paramMMOItem.hasData(ItemStats.GEN_TEMPLATE) ? MMOItems.plugin.getWorldGen().getOrThrow(paramMMOItem.getData(ItemStats.GEN_TEMPLATE).toString()) : null;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 41 */     return this.id;
/*    */   }
/*    */   
/*    */   public MushroomState getState() {
/* 45 */     return this.state;
/*    */   }
/*    */   
/*    */   public boolean hasGenTemplate() {
/* 49 */     return (this.template != null);
/*    */   }
/*    */   
/*    */   public WorldGenTemplate getGenTemplate() {
/* 53 */     return this.template;
/*    */   }
/*    */   
/*    */   public int getMinExpDrop() {
/* 57 */     return this.minExp;
/*    */   }
/*    */   
/*    */   public int getMaxExpDrop() {
/* 61 */     return this.maxExp;
/*    */   }
/*    */   
/*    */   public int rollExperience() {
/* 65 */     return Math.max(0, (this.maxExp < this.minExp) ? this.minExp : (RANDOM.nextInt(this.maxExp - this.minExp + 1) + this.minExp));
/*    */   }
/*    */   
/*    */   public int getRequiredPower() {
/* 69 */     return this.requiredPower;
/*    */   }
/*    */   
/*    */   public ItemStack getItem() {
/* 73 */     return this.mmoitem.newBuilder().build();
/*    */   }
/*    */   
/*    */   public boolean requirePowerToBreak() {
/* 77 */     return this.requirePowerToBreak;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\block\CustomBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */