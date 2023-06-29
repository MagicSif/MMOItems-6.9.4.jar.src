/*    */ package net.Indyuce.mmoitems.particle;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.particle.api.ParticleRunnable;
/*    */ import net.Indyuce.mmoitems.stat.data.ParticleData;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ public class HelixParticles
/*    */   extends ParticleRunnable
/*    */ {
/*    */   private final float speed;
/*    */   private final float height;
/*    */   private final float radius;
/*    */   private final float r_speed;
/*    */   private final float y_speed;
/*    */   private final int amount;
/* 17 */   private double j = 0.0D;
/*    */   
/*    */   public HelixParticles(ParticleData paramParticleData, PlayerData paramPlayerData) {
/* 20 */     super(paramParticleData, paramPlayerData);
/*    */     
/* 22 */     this.speed = (float)paramParticleData.getModifier("speed");
/* 23 */     this.height = (float)paramParticleData.getModifier("height");
/* 24 */     this.radius = (float)paramParticleData.getModifier("radius");
/* 25 */     this.r_speed = (float)paramParticleData.getModifier("rotation-speed");
/* 26 */     this.y_speed = (float)paramParticleData.getModifier("y-speed");
/* 27 */     this.amount = (int)paramParticleData.getModifier("amount");
/*    */   }
/*    */ 
/*    */   
/*    */   public void createParticles() {
/* 32 */     Location location = this.player.getPlayer().getLocation();
/* 33 */     for (double d = 0.0D; d < this.amount; d++) {
/* 34 */       double d1 = this.j + d * Math.PI * 2.0D / this.amount;
/* 35 */       this.particle.display(location.clone().add(Math.cos(d1) * Math.cos(this.j * this.y_speed) * this.radius, 1.0D + Math.sin(this.j * this.y_speed) * this.height, Math.sin(d1) * Math.cos(this.j * this.y_speed) * this.radius), this.speed);
/*    */     } 
/*    */     
/* 38 */     this.j += 0.1308996938995747D * this.r_speed;
/* 39 */     this.j -= (this.j > 6.283185307179586D / this.y_speed) ? (6.283185307179586D / this.y_speed) : 0.0D;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\particle\HelixParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */