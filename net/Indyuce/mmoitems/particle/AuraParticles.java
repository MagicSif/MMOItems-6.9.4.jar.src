/*    */ package net.Indyuce.mmoitems.particle;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.particle.api.ParticleRunnable;
/*    */ import net.Indyuce.mmoitems.stat.data.ParticleData;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ public class AuraParticles
/*    */   extends ParticleRunnable {
/*    */   private final float speed;
/*    */   private final float height;
/*    */   private final float radius;
/* 13 */   private double j = 0.0D; private final float r_speed; private final float y_offset; private final float y_speed; private final int amount;
/*    */   
/*    */   public AuraParticles(ParticleData paramParticleData, PlayerData paramPlayerData) {
/* 16 */     super(paramParticleData, paramPlayerData);
/*    */     
/* 18 */     this.speed = (float)paramParticleData.getModifier("speed");
/* 19 */     this.height = (float)paramParticleData.getModifier("height");
/* 20 */     this.radius = (float)paramParticleData.getModifier("radius");
/* 21 */     this.r_speed = (float)paramParticleData.getModifier("rotation-speed");
/* 22 */     this.y_speed = (float)paramParticleData.getModifier("y-speed");
/* 23 */     this.y_offset = (float)paramParticleData.getModifier("y-offset");
/* 24 */     this.amount = (int)paramParticleData.getModifier("amount");
/*    */   }
/*    */ 
/*    */   
/*    */   public void createParticles() {
/* 29 */     Location location = this.player.getPlayer().getLocation();
/* 30 */     for (byte b = 0; b < this.amount; b++) {
/* 31 */       double d = this.j + 6.283185307179586D * b / this.amount;
/* 32 */       this.particle.display(location.clone().add(Math.cos(d) * this.radius, Math.sin(this.j * this.y_speed * 3.0D) * this.y_offset + this.height, Math.sin(d) * this.radius), this.speed);
/*    */     } 
/*    */     
/* 35 */     this.j += 0.06544984694978735D * this.r_speed;
/* 36 */     this.j -= (this.j > 6.283185307179586D / this.y_speed) ? (6.283185307179586D / this.y_speed) : 0.0D;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\particle\AuraParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */