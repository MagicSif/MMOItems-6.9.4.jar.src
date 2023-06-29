/*     */ package net.Indyuce.mmoitems.api;
/*     */ 
/*     */ import io.lumine.mythic.lib.UtilityMethods;
/*     */ import io.lumine.mythic.lib.comp.interaction.InteractionType;
/*     */ import io.lumine.mythic.lib.damage.AttackMetadata;
/*     */ import io.lumine.mythic.lib.damage.DamageType;
/*     */ import io.lumine.mythic.lib.version.VersionSound;
/*     */ import java.util.Random;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.Weapon;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum TypeSet
/*     */ {
/* 121 */   RANGE,
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   CATALYST,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   EXTRA, SLASHING, PIERCING, BLUNT;
/*     */   static { SLASHING = new TypeSet("SLASHING", 0, (paramAttackMetadata, paramPlayerData, paramLivingEntity, paramWeapon) -> { if (!MMOItems.plugin.getConfig().getBoolean("item-ability.slashing.enabled") || paramPlayerData.isOnCooldown(PlayerData.CooldownType.SET_TYPE_ATTACK))
/*     */             return;  paramPlayerData.applyCooldown(PlayerData.CooldownType.SET_TYPE_ATTACK, MMOItems.plugin.getConfig().getDouble("item-ability.slashing.cooldown")); Location location = paramAttackMetadata.getPlayer().getLocation().clone().add(0.0D, 1.3D, 0.0D); double d1 = ((location.getYaw() + 90.0F) / 180.0F) * Math.PI; double d2 = (-location.getPitch() / 180.0F) * Math.PI; double d3; for (d3 = 1.0D; d3 < 5.0D; d3 += 0.3D) { double d; for (d = -0.5235987755982988D; d < 0.5235987755982988D; d += 0.39269908169872414D / d3)
/*     */               location.getWorld().spawnParticle(Particle.CRIT, location.clone().add(Math.cos(d + d1) * d3, Math.sin(d2) * d3, Math.sin(d + d1) * d3), 0);  }  for (Entity entity : MMOUtils.getNearbyChunkEntities(location)) { if (entity.getLocation().distanceSquared(location) < 40.0D && paramAttackMetadata.getPlayer().getEyeLocation().getDirection().angle(entity.getLocation().subtract(paramAttackMetadata.getPlayer().getLocation()).toVector()) < 1.0471975511965976D && UtilityMethods.canTarget(paramAttackMetadata.getPlayer(), entity, InteractionType.OFFENSE_ACTION) && !entity.equals(paramLivingEntity))
/*     */               paramAttackMetadata.attack((LivingEntity)entity, paramAttackMetadata.getDamage().getDamage() * 0.4D, new DamageType[] { DamageType.WEAPON, DamageType.PHYSICAL });  }  }); PIERCING = new TypeSet("PIERCING", 1, (paramAttackMetadata, paramPlayerData, paramLivingEntity, paramWeapon) -> { if (!MMOItems.plugin.getConfig().getBoolean("item-ability.piercing.enabled") || paramPlayerData.isOnCooldown(PlayerData.CooldownType.SET_TYPE_ATTACK))
/*     */             return;  paramPlayerData.applyCooldown(PlayerData.CooldownType.SET_TYPE_ATTACK, MMOItems.plugin.getConfig().getDouble("item-ability.piercing.cooldown")); Location location = paramAttackMetadata.getPlayer().getLocation().clone().add(0.0D, 1.3D, 0.0D); double d1 = ((location.getYaw() + 90.0F) / 180.0F) * Math.PI; double d2 = (-location.getPitch() / 180.0F) * Math.PI; double d3; for (d3 = 1.0D; d3 < 5.0D; d3 += 0.3D) { double d; for (d = -0.2617993877991494D; d < 0.2617993877991494D; d += 0.19634954084936207D / d3)
/*     */               location.getWorld().spawnParticle(Particle.CRIT, location.clone().add(Math.cos(d + d1) * d3, Math.sin(d2) * d3, Math.sin(d + d1) * d3), 0);  }  for (Entity entity : MMOUtils.getNearbyChunkEntities(location)) { if (!entity.equals(paramLivingEntity) && entity.getLocation().distanceSquared(paramAttackMetadata.getPlayer().getLocation()) < 40.0D && paramAttackMetadata.getPlayer().getEyeLocation().getDirection().angle(entity.getLocation().toVector().subtract(paramAttackMetadata.getPlayer().getLocation().toVector())) < 0.2617993877991494D && UtilityMethods.canTarget(paramAttackMetadata.getPlayer(), entity, InteractionType.OFFENSE_ACTION))
/*     */               paramAttackMetadata.attack((LivingEntity)entity, paramAttackMetadata.getDamage().getDamage() * 0.6D, new DamageType[] { DamageType.WEAPON, DamageType.PHYSICAL });  }  }); BLUNT = new TypeSet("BLUNT", 2, (paramAttackMetadata, paramPlayerData, paramLivingEntity, paramWeapon) -> { Random random = new Random(); float f = 0.7F + random.nextFloat() * 0.19999999F; if (MMOItems.plugin.getConfig().getBoolean("item-ability.blunt.aoe.enabled") && !paramPlayerData.isOnCooldown(PlayerData.CooldownType.SPECIAL_ATTACK)) { paramPlayerData.applyCooldown(PlayerData.CooldownType.SPECIAL_ATTACK, MMOItems.plugin.getConfig().getDouble("item-ability.blunt.aoe.cooldown")); paramLivingEntity.getWorld().playSound(paramLivingEntity.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.6F, f); paramLivingEntity.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, paramLivingEntity.getLocation().add(0.0D, 1.0D, 0.0D), 0); double d = paramAttackMetadata.getStat("BLUNT_POWER"); if (d > 0.0D) { double d1 = paramWeapon.requireNonZero(paramAttackMetadata.getStat("BLUNT_RATING"), MMOItems.plugin.getConfig().getDouble("default.blunt-rating")) / 100.0D; for (Entity entity : paramLivingEntity.getNearbyEntities(d, d, d)) { if (UtilityMethods.canTarget(paramAttackMetadata.getPlayer(), entity, InteractionType.OFFENSE_ACTION) && !entity.equals(paramLivingEntity))
/*     */                   paramAttackMetadata.attack((LivingEntity)entity, paramAttackMetadata.getDamage().getDamage() * d1, new DamageType[] { DamageType.WEAPON, DamageType.PHYSICAL });  }  }  }  if (MMOItems.plugin.getConfig().getBoolean("item-ability.blunt.stun.enabled") && !paramPlayerData.isOnCooldown(PlayerData.CooldownType.SPECIAL_ATTACK) && random.nextDouble() < MMOItems.plugin.getConfig().getDouble("item-ability.blunt.stun.chance") / 100.0D) { paramPlayerData.applyCooldown(PlayerData.CooldownType.SPECIAL_ATTACK, MMOItems.plugin.getConfig().getDouble("item-ability.blunt.stun.cooldown")); paramLivingEntity.getWorld().playSound(paramLivingEntity.getLocation(), VersionSound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR.toSound(), 1.0F, 2.0F); paramLivingEntity.removePotionEffect(PotionEffectType.SLOW); paramLivingEntity.removePotionEffect(PotionEffectType.BLINDNESS); paramLivingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0)); paramLivingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (int)(30.0D * MMOItems.plugin.getConfig().getDouble("item-ability.blunt.stun.power")), 1)); Location location = paramLivingEntity.getLocation(); location.setYaw((float)(location.getYaw() + 2.0D * (random.nextDouble() - 0.5D) * 90.0D)); location.setPitch((float)(location.getPitch() + 2.0D * (random.nextDouble() - 0.5D) * 30.0D)); }
/*     */         
/* 142 */         }); } TypeSet(SetAttackHandler paramSetAttackHandler) { this.attackHandler = paramSetAttackHandler;
/*     */     
/* 144 */     this.name = MMOUtils.caseOnWords(name().toLowerCase()); }
/*     */   
/*     */   private final SetAttackHandler attackHandler; private final String name;
/*     */   public boolean hasAttackEffect() {
/* 148 */     return (this.attackHandler != null);
/*     */   }
/*     */   
/*     */   public void applyAttackEffect(AttackMetadata paramAttackMetadata, PlayerData paramPlayerData, LivingEntity paramLivingEntity, Weapon paramWeapon) {
/* 152 */     this.attackHandler.apply(paramAttackMetadata, paramPlayerData, paramLivingEntity, paramWeapon);
/*     */   }
/*     */   
/*     */   public String getName() {
/* 156 */     return this.name;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   static interface SetAttackHandler {
/*     */     void apply(AttackMetadata param1AttackMetadata, PlayerData param1PlayerData, LivingEntity param1LivingEntity, Weapon param1Weapon);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\TypeSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */