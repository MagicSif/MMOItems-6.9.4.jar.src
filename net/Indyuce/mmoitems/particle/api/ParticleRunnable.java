/*    */ package net.Indyuce.mmoitems.particle.api;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.stat.data.ParticleData;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public abstract class ParticleRunnable
/*    */   extends BukkitRunnable {
/*    */   protected final ParticleData particle;
/*    */   protected final PlayerData player;
/*    */   
/*    */   public ParticleRunnable(ParticleData paramParticleData, PlayerData paramPlayerData) {
/* 13 */     this.particle = paramParticleData;
/* 14 */     this.player = paramPlayerData;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 19 */     if (!this.player.isOnline())
/* 20 */       return;  createParticles();
/*    */   }
/*    */   
/*    */   public abstract void createParticles();
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\particle\api\ParticleRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */