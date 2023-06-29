/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.bukkit.Particle;
/*    */ 
/*    */ public class ArrowParticlesData
/*    */   implements StatData, RandomStatData<ArrowParticlesData>
/*    */ {
/*    */   private final Particle particle;
/*    */   private final int amount;
/*    */   private final int red;
/*    */   private final int green;
/*    */   
/*    */   public ArrowParticlesData(Particle paramParticle, int paramInt, double paramDouble1, double paramDouble2) {
/* 18 */     this.particle = paramParticle;
/* 19 */     this.amount = paramInt;
/* 20 */     this.offset = paramDouble1;
/*    */     
/* 22 */     this.speed = paramDouble2;
/*    */     
/* 24 */     this.colored = false;
/* 25 */     this.red = 0;
/* 26 */     this.blue = 0;
/* 27 */     this.green = 0;
/*    */   }
/*    */   private final int blue; private final double speed; private final double offset; private final boolean colored;
/*    */   public ArrowParticlesData(Particle paramParticle, int paramInt1, double paramDouble, int paramInt2, int paramInt3, int paramInt4) {
/* 31 */     this.particle = paramParticle;
/* 32 */     this.amount = paramInt1;
/* 33 */     this.offset = paramDouble;
/*    */     
/* 35 */     this.speed = 0.0D;
/*    */     
/* 37 */     this.colored = true;
/* 38 */     this.red = paramInt2;
/* 39 */     this.green = paramInt3;
/* 40 */     this.blue = paramInt4;
/*    */   }
/*    */   
/*    */   public Particle getParticle() {
/* 44 */     return this.particle;
/*    */   }
/*    */   
/*    */   public boolean isColored() {
/* 48 */     return this.colored;
/*    */   }
/*    */   
/*    */   public int getAmount() {
/* 52 */     return this.amount;
/*    */   }
/*    */   
/*    */   public double getOffset() {
/* 56 */     return this.offset;
/*    */   }
/*    */   
/*    */   public double getSpeed() {
/* 60 */     return this.speed;
/*    */   }
/*    */   
/*    */   public int getRed() {
/* 64 */     return this.red;
/*    */   }
/*    */   
/*    */   public int getGreen() {
/* 68 */     return this.green;
/*    */   }
/*    */   
/*    */   public int getBlue() {
/* 72 */     return this.blue;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 77 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     JsonObject jsonObject = new JsonObject();
/* 83 */     jsonObject.addProperty("Particle", this.particle.name());
/* 84 */     jsonObject.addProperty("Amount", Integer.valueOf(this.amount));
/* 85 */     jsonObject.addProperty("Offset", Double.valueOf(this.offset));
/* 86 */     jsonObject.addProperty("Colored", Boolean.valueOf(this.colored));
/* 87 */     if (this.colored) {
/* 88 */       jsonObject.addProperty("Red", Integer.valueOf(this.red));
/* 89 */       jsonObject.addProperty("Green", Integer.valueOf(this.green));
/* 90 */       jsonObject.addProperty("Blue", Integer.valueOf(this.blue));
/*    */     } else {
/* 92 */       jsonObject.addProperty("Speed", Double.valueOf(this.speed));
/*    */     } 
/* 94 */     return jsonObject.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrowParticlesData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 99 */     return this;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\ArrowParticlesData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */