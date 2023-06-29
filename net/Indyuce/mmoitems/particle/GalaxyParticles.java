/*    */ package net.Indyuce.mmoitems.particle;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.particle.api.ParticleRunnable;
/*    */ import net.Indyuce.mmoitems.stat.data.ParticleData;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ public class GalaxyParticles
/*    */   extends ParticleRunnable
/*    */ {
/*    */   private final float speed;
/*    */   private final float height;
/* 13 */   private double j = 0.0D; private final float r_speed; private final float y_coord; private final int amount;
/*    */   
/*    */   public GalaxyParticles(ParticleData paramParticleData, PlayerData paramPlayerData) {
/* 16 */     super(paramParticleData, paramPlayerData);
/*    */     
/* 18 */     this.speed = (float)paramParticleData.getModifier("speed") * 0.2F;
/* 19 */     this.height = (float)paramParticleData.getModifier("height");
/* 20 */     this.r_speed = (float)paramParticleData.getModifier("rotation-speed");
/* 21 */     this.y_coord = (float)paramParticleData.getModifier("y-coord");
/* 22 */     this.amount = (int)paramParticleData.getModifier("amount");
/*    */   }
/*    */ 
/*    */   
/*    */   public void createParticles() {
/* 27 */     Location location = this.player.getPlayer().getLocation();
/* 28 */     for (byte b = 0; b < this.amount; b++) {
/* 29 */       double d = this.j + 6.283185307179586D * b / this.amount;
/* 30 */       this.particle.display(location.clone().add(0.0D, this.height, 0.0D), 0, (float)Math.cos(d), this.y_coord, (float)Math.sin(d), this.speed);
/*    */     } 
/*    */     
/* 33 */     this.j += 0.1308996938995747D * this.r_speed;
/* 34 */     this.j -= (this.j > 6.283185307179586D) ? 6.283185307179586D : 0.0D;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\particle\GalaxyParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */