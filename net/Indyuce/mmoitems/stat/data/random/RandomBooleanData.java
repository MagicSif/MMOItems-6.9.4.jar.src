/*    */ package net.Indyuce.mmoitems.stat.data.random;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.BooleanData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ 
/*    */ public class RandomBooleanData
/*    */   implements RandomStatData<BooleanData>
/*    */ {
/*    */   private final double chance;
/* 12 */   private static final Random random = new Random();
/*    */   
/*    */   public RandomBooleanData(boolean paramBoolean) {
/* 15 */     this.chance = paramBoolean ? 1.0D : 0.0D;
/*    */   }
/*    */   
/*    */   public RandomBooleanData(double paramDouble) {
/* 19 */     this.chance = paramDouble;
/*    */   }
/*    */   
/*    */   public double getChance() {
/* 23 */     return this.chance;
/*    */   }
/*    */ 
/*    */   
/*    */   public BooleanData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 28 */     return new BooleanData((random.nextDouble() < this.chance));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\random\RandomBooleanData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */