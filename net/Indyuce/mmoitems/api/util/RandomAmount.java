/*    */ package net.Indyuce.mmoitems.api.util;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class RandomAmount {
/*    */   private final int min;
/*    */   private final int max;
/*  8 */   private static final Random RANDOM = new Random();
/*    */   
/*    */   public RandomAmount(int paramInt1, int paramInt2) {
/* 11 */     this.min = paramInt1;
/* 12 */     this.max = paramInt2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RandomAmount(String paramString) {
/* 22 */     String[] arrayOfString = paramString.split("-");
/* 23 */     this.min = Integer.parseInt(arrayOfString[0]);
/* 24 */     this.max = (arrayOfString.length > 1) ? Integer.parseInt(arrayOfString[1]) : 0;
/*    */   }
/*    */   
/*    */   public int getMin() {
/* 28 */     return this.min;
/*    */   }
/*    */   
/*    */   public int getMax() {
/* 32 */     return this.max;
/*    */   }
/*    */   
/*    */   public int getRandomAmount() {
/* 36 */     return (this.max > 0) ? (this.min + RANDOM.nextInt(this.max - this.min + 1)) : this.min;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\ap\\util\RandomAmount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */