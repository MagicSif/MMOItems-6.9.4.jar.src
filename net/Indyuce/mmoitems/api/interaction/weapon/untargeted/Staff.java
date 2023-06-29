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
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.stat.StaffSpiritStat;
/*    */ import org.bukkit.EntityEffect;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Particle;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class Staff extends UntargetedWeapon {
/*    */   public Staff(Player paramPlayer, NBTItem paramNBTItem) {
/* 24 */     super(paramPlayer, paramNBTItem, UntargetedWeaponType.LEFT_CLICK);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canAttack(EquipmentSlot paramEquipmentSlot) {
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void applyAttackEffect(PlayerMetadata paramPlayerMetadata, EquipmentSlot paramEquipmentSlot) {
/* 35 */     double d1 = requireNonZero(paramPlayerMetadata.getStat("ATTACK_DAMAGE"), 1.0D);
/* 36 */     double d2 = requireNonZero(paramPlayerMetadata.getStat("RANGE"), MMOItems.plugin.getConfig().getDouble("default.range"));
/*    */     
/* 38 */     StaffSpiritStat.StaffSpirit staffSpirit = StaffSpiritStat.StaffSpirit.get(getNBTItem());
/* 39 */     if (staffSpirit != null) {
/* 40 */       staffSpirit.getAttack().handle(paramPlayerMetadata, d1, getNBTItem(), paramEquipmentSlot, d2);
/*    */       
/*    */       return;
/*    */     } 
/* 44 */     RayTrace rayTrace = new RayTrace(paramPlayerMetadata.getPlayer(), paramEquipmentSlot, d2, paramEntity -> UtilityMethods.canTarget(paramPlayerMetadata.getPlayer(), paramEntity, InteractionType.OFFENSE_ACTION));
/* 45 */     if (rayTrace.hasHit())
/* 46 */       paramPlayerMetadata.attack(rayTrace.getHit(), d1, new DamageType[] { DamageType.WEAPON, DamageType.MAGIC, DamageType.PROJECTILE }); 
/* 47 */     rayTrace.draw(0.5D, paramLocation -> paramLocation.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, paramLocation, 0, 0.1D, 0.1D, 0.1D, 0.0D));
/* 48 */     getPlayer().getWorld().playSound(getPlayer().getLocation(), VersionSound.ENTITY_FIREWORK_ROCKET_TWINKLE.toSound(), 2.0F, 2.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void specialAttack(LivingEntity paramLivingEntity) {
/* 53 */     if (!MMOItems.plugin.getConfig().getBoolean("item-ability.staff.enabled")) {
/*    */       return;
/*    */     }
/* 56 */     if (!checkWeaponCosts(PlayerData.CooldownType.SPECIAL_ATTACK)) {
/*    */       return;
/*    */     }
/* 59 */     applyWeaponCosts(MMOItems.plugin.getConfig().getDouble("item-ability.staff.cooldown"), PlayerData.CooldownType.SPECIAL_ATTACK);
/* 60 */     double d = MMOItems.plugin.getConfig().getDouble("item-ability.staff.power");
/*    */     
/*    */     try {
/* 63 */       Vector vector = paramLivingEntity.getLocation().toVector().subtract(getPlayer().getLocation().toVector()).setY(0).normalize().multiply(1.75D * d).setY(0.65D * d);
/* 64 */       paramLivingEntity.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, paramLivingEntity.getLocation().add(0.0D, 1.0D, 0.0D), 0);
/* 65 */       paramLivingEntity.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, paramLivingEntity.getLocation().add(0.0D, 1.0D, 0.0D), 16, 0.0D, 0.0D, 0.0D, 0.1D);
/* 66 */       paramLivingEntity.setVelocity(vector);
/* 67 */       paramLivingEntity.playEffect(EntityEffect.HURT);
/* 68 */       paramLivingEntity.getWorld().playSound(paramLivingEntity.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 2.0F);
/* 69 */     } catch (IllegalArgumentException illegalArgumentException) {}
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\Staff.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */