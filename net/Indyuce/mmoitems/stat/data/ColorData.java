/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Color;
/*    */ 
/*    */ public class ColorData
/*    */   implements StatData, RandomStatData<ColorData> {
/*    */   private final int red;
/*    */   
/*    */   public ColorData(String paramString) {
/* 14 */     String[] arrayOfString = paramString.split(" ");
/* 15 */     Validate.isTrue((arrayOfString.length > 2), "Must specify 3 numbers for red, green and blue");
/*    */     
/* 17 */     this.red = Math.min(255, Math.max(0, Integer.parseInt(arrayOfString[0])));
/* 18 */     this.green = Math.min(255, Math.max(0, Integer.parseInt(arrayOfString[1])));
/* 19 */     this.blue = Math.min(255, Math.max(0, Integer.parseInt(arrayOfString[2])));
/*    */   }
/*    */   private final int green; private final int blue;
/*    */   public ColorData(Color paramColor) {
/* 23 */     this(paramColor.getRed(), paramColor.getGreen(), paramColor.getBlue());
/*    */   }
/*    */   
/*    */   public ColorData(int paramInt1, int paramInt2, int paramInt3) {
/* 27 */     this.red = paramInt1;
/* 28 */     this.green = paramInt2;
/* 29 */     this.blue = paramInt3;
/*    */   }
/*    */   
/*    */   public int getRed() {
/* 33 */     return this.red;
/*    */   }
/*    */   
/*    */   public int getGreen() {
/* 37 */     return this.green;
/*    */   }
/*    */   
/*    */   public int getBlue() {
/* 41 */     return this.blue;
/*    */   }
/*    */   
/*    */   public Color getColor() {
/* 45 */     return Color.fromRGB(this.red, this.green, this.blue);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     return "{Red=" + this.red + ",Green=" + this.green + ",Blue=" + this.blue + "}";
/*    */   }
/*    */ 
/*    */   
/*    */   public ColorData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 60 */     return this;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\ColorData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */