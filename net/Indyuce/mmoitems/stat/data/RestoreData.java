/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class RestoreData implements StatData, Mergeable {
/*    */   private double health;
/*    */   
/*    */   public RestoreData(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 12 */     this.health = paramDouble1;
/* 13 */     this.food = paramDouble2;
/* 14 */     this.saturation = paramDouble3;
/*    */   }
/*    */   private double food; private double saturation;
/*    */   public double getHealth() {
/* 18 */     return this.health;
/*    */   }
/*    */   
/*    */   public double getFood() {
/* 22 */     return this.food;
/*    */   }
/*    */   
/*    */   public double getSaturation() {
/* 26 */     return this.saturation;
/*    */   }
/*    */   
/*    */   public void setHealth(double paramDouble) {
/* 30 */     this.health = paramDouble;
/*    */   }
/*    */   
/*    */   public void setFood(double paramDouble) {
/* 34 */     this.food = paramDouble;
/*    */   }
/*    */   
/*    */   public void setSaturation(double paramDouble) {
/* 38 */     this.saturation = paramDouble;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 43 */     return (this.food == 0.0D && this.health == 0.0D && this.saturation == 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void merge(StatData paramStatData) {
/* 48 */     Validate.isTrue(paramStatData instanceof RestoreData, "Cannot merge two different stat data types");
/* 49 */     this.health += ((RestoreData)paramStatData).health;
/* 50 */     this.food += ((RestoreData)paramStatData).food;
/* 51 */     this.saturation += ((RestoreData)paramStatData).saturation;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public StatData cloneData() {
/* 57 */     return new RestoreData(getHealth(), getFood(), getSaturation());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 62 */     if (!(paramObject instanceof RestoreData)) {
/* 63 */       return false;
/*    */     }
/* 65 */     return (((RestoreData)paramObject).getFood() == getFood() && ((RestoreData)paramObject).getHealth() == getHealth() && ((RestoreData)paramObject).getSaturation() == getSaturation());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\RestoreData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */