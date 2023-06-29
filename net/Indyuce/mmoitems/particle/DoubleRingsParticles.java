/*    */ package net.Indyuce.mmoitems.particle;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.particle.api.ParticleRunnable;
/*    */ import net.Indyuce.mmoitems.stat.data.ParticleData;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ public class DoubleRingsParticles
/*    */   extends ParticleRunnable {
/*    */   private final float speed;
/*    */   private final float height;
/* 12 */   private double j = 0.0D; private final float radius; private final float r_speed; private final float y_offset;
/*    */   
/*    */   public DoubleRingsParticles(ParticleData paramParticleData, PlayerData paramPlayerData) {
/* 15 */     super(paramParticleData, paramPlayerData);
/*    */     
/* 17 */     this.speed = (float)paramParticleData.getModifier("speed");
/* 18 */     this.height = (float)paramParticleData.getModifier("height");
/* 19 */     this.radius = (float)paramParticleData.getModifier("radius");
/* 20 */     this.r_speed = (float)paramParticleData.getModifier("rotation-speed");
/* 21 */     this.y_offset = (float)paramParticleData.getModifier("y-offset");
/*    */   }
/*    */ 
/*    */   
/*    */   public void createParticles() {
/* 26 */     Location location = this.player.getPlayer().getLocation();
/* 27 */     for (double d = 0.0D; d < 2.0D; d++) {
/* 28 */       double d1 = this.j + d * Math.PI;
/* 29 */       this.particle.display(location.clone().add(this.radius * Math.cos(d1), this.height + Math.sin(this.j) * this.y_offset, this.radius * Math.sin(d1)), this.speed);
/*    */     } 
/*    */     
/* 32 */     this.j += 0.19634954084936207D * this.r_speed;
/* 33 */     this.j -= (this.j > 6.283185307179586D) ? 6.283185307179586D : 0.0D;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\particle\DoubleRingsParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */