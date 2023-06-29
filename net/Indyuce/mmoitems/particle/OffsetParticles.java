/*    */ package net.Indyuce.mmoitems.particle;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.particle.api.ParticleRunnable;
/*    */ import net.Indyuce.mmoitems.stat.data.ParticleData;
/*    */ 
/*    */ public class OffsetParticles extends ParticleRunnable {
/*    */   private final float speed;
/*    */   private final float h_offset;
/*    */   
/*    */   public OffsetParticles(ParticleData paramParticleData, PlayerData paramPlayerData) {
/* 12 */     super(paramParticleData, paramPlayerData);
/*    */     
/* 14 */     this.speed = (float)paramParticleData.getModifier("speed");
/* 15 */     this.height = (float)paramParticleData.getModifier("height");
/* 16 */     this.h_offset = (float)paramParticleData.getModifier("horizontal-offset");
/* 17 */     this.v_offset = (float)paramParticleData.getModifier("vertical-offset");
/* 18 */     this.amount = (int)paramParticleData.getModifier("amount");
/*    */   }
/*    */   private final float v_offset; private final float height; private final int amount;
/*    */   
/*    */   public void createParticles() {
/* 23 */     this.particle.display(this.player.getPlayer().getLocation().add(0.0D, this.height, 0.0D), this.amount, this.h_offset, this.v_offset, this.h_offset, this.speed);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\particle\OffsetParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */