/*    */ package net.Indyuce.mmoitems.api.interaction.projectile;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.gson.JsonParser;
/*    */ import io.lumine.mythic.lib.player.particle.ParticleInformation;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import org.bukkit.entity.AbstractArrow;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class ArrowParticles extends BukkitRunnable {
/*    */   private final AbstractArrow arrow;
/*    */   
/*    */   public ArrowParticles(AbstractArrow paramAbstractArrow, NBTItem paramNBTItem) {
/* 15 */     this.arrow = paramAbstractArrow;
/* 16 */     this.particleInfo = new ParticleInformation(JsonParser.parseString(paramNBTItem.getString("MMOITEMS_ARROW_PARTICLES")).getAsJsonObject());
/*    */     
/* 18 */     runTaskTimer((Plugin)MMOItems.plugin, 0L, 1L);
/*    */   }
/*    */   private final ParticleInformation particleInfo;
/*    */   
/*    */   public void run() {
/* 23 */     if (this.arrow.isDead() || this.arrow.isOnGround()) {
/* 24 */       cancel();
/*    */       
/*    */       return;
/*    */     } 
/* 28 */     this.particleInfo.display(this.arrow.getLocation().add(0.0D, 0.0D, 0.0D));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\projectile\ArrowParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */