/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ 
/*    */ public class DoubleData implements StatData, Mergeable<DoubleData> {
/*    */   private double value;
/*    */   
/*    */   public DoubleData(double paramDouble) {
/* 10 */     this.value = paramDouble;
/*    */   }
/*    */   
/*    */   public double getValue() {
/* 14 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(double paramDouble) {
/* 18 */     this.value = paramDouble;
/*    */   }
/*    */   
/*    */   public void add(double paramDouble) {
/* 22 */     this.value += paramDouble;
/*    */   }
/*    */   
/*    */   public void addRelative(double paramDouble) {
/* 26 */     this.value *= 1.0D + paramDouble;
/*    */   }
/*    */ 
/*    */   
/*    */   public void merge(DoubleData paramDoubleData) {
/* 31 */     this.value += paramDoubleData.value;
/*    */   }
/*    */   
/*    */   public DoubleData cloneData() {
/* 35 */     return new DoubleData(getValue());
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 39 */     return (this.value == 0.0D);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 43 */     return String.valueOf(getValue());
/*    */   }
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 47 */     if (!(paramObject instanceof DoubleData)) return false; 
/* 48 */     return (((DoubleData)paramObject).getValue() == getValue());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\DoubleData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */