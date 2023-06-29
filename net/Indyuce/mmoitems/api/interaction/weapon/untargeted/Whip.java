/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted;
/*    */ import io.lumine.mythic.lib.UtilityMethods;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.comp.interaction.InteractionType;
/*    */ import io.lumine.mythic.lib.damage.DamageType;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import io.lumine.mythic.lib.util.RayTrace;
/*    */ import io.lumine.mythic.lib.version.VersionSound;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Particle;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class Whip extends UntargetedWeapon {
/*    */   public Whip(Player paramPlayer, NBTItem paramNBTItem) {
/* 18 */     super(paramPlayer, paramNBTItem, UntargetedWeaponType.LEFT_CLICK);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canAttack(EquipmentSlot paramEquipmentSlot) {
/* 23 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void applyAttackEffect(PlayerMetadata paramPlayerMetadata, EquipmentSlot paramEquipmentSlot) {
/* 29 */     double d1 = requireNonZero(paramPlayerMetadata.getStat("ATTACK_DAMAGE"), 7.0D);
/* 30 */     double d2 = requireNonZero(getNBTItem().getStat(ItemStats.RANGE.getId()), MMOItems.plugin.getConfig().getDouble("default.range"));
/*    */     
/* 32 */     RayTrace rayTrace = new RayTrace(getPlayer(), paramEquipmentSlot, d2, paramEntity -> UtilityMethods.canTarget(paramPlayerMetadata.getPlayer(), paramEntity, InteractionType.OFFENSE_ACTION));
/* 33 */     if (rayTrace.hasHit())
/* 34 */       paramPlayerMetadata.attack(rayTrace.getHit(), d1, new DamageType[] { DamageType.WEAPON, DamageType.PROJECTILE, DamageType.PHYSICAL }); 
/* 35 */     rayTrace.draw(0.5D, paramLocation -> paramLocation.getWorld().spawnParticle(Particle.CRIT, paramLocation, 0, 0.1D, 0.1D, 0.1D, 0.0D));
/* 36 */     getPlayer().getWorld().playSound(getPlayer().getLocation(), VersionSound.ENTITY_FIREWORK_ROCKET_BLAST.toSound(), 1.0F, 2.0F);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\Whip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */