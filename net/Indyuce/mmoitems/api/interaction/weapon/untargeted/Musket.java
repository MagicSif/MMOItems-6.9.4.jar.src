/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted;
/*    */ 
/*    */ import io.lumine.mythic.lib.UtilityMethods;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.comp.interaction.InteractionType;
/*    */ import io.lumine.mythic.lib.damage.DamageType;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import io.lumine.mythic.lib.util.RayTrace;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import org.bukkit.Color;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class Musket extends UntargetedWeapon {
/*    */   public Musket(Player paramPlayer, NBTItem paramNBTItem) {
/* 19 */     super(paramPlayer, paramNBTItem, UntargetedWeaponType.RIGHT_CLICK);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canAttack(EquipmentSlot paramEquipmentSlot) {
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyAttackEffect(PlayerMetadata paramPlayerMetadata, EquipmentSlot paramEquipmentSlot) {
/* 29 */     double d1 = requireNonZero(paramPlayerMetadata.getStat("RANGE"), MMOItems.plugin.getConfig().getDouble("default.range"));
/* 30 */     double d2 = requireNonZero(paramPlayerMetadata.getStat("RECOIL"), MMOItems.plugin.getConfig().getDouble("default.recoil"));
/*    */ 
/*    */     
/* 33 */     double d3 = paramPlayerMetadata.getStat("KNOCKBACK");
/* 34 */     if (d3 > 0.0D) {
/* 35 */       getPlayer().setVelocity(getPlayer().getVelocity()
/* 36 */           .add(getPlayer().getEyeLocation().getDirection().setY(0).normalize().multiply(-1.0D * d3).setY(-0.2D)));
/*    */     }
/* 38 */     double d4 = Math.toRadians((getPlayer().getEyeLocation().getYaw() + 90.0F + (45 * ((paramEquipmentSlot == EquipmentSlot.MAIN_HAND) ? 1 : -1))));
/* 39 */     Location location = getPlayer().getLocation().add(Math.cos(d4) * 0.5D, 1.5D, Math.sin(d4) * 0.5D);
/* 40 */     location.setPitch((float)(location.getPitch() + (RANDOM.nextDouble() - 0.5D) * 2.0D * d2));
/* 41 */     location.setYaw((float)(location.getYaw() + (RANDOM.nextDouble() - 0.5D) * 2.0D * d2));
/*    */     
/* 43 */     RayTrace rayTrace = new RayTrace(location, location.getDirection(), d1, paramEntity -> UtilityMethods.canTarget(paramPlayerMetadata.getPlayer(), paramEntity, InteractionType.OFFENSE_ACTION));
/* 44 */     if (rayTrace.hasHit()) {
/* 45 */       paramPlayerMetadata.attack(rayTrace.getHit(), paramPlayerMetadata.getStat("ATTACK_DAMAGE"), new DamageType[] { DamageType.WEAPON, DamageType.PHYSICAL, DamageType.PROJECTILE });
/*    */     }
/* 47 */     rayTrace.draw(0.5D, Color.BLACK);
/* 48 */     getPlayer().getWorld().playSound(getPlayer().getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 2.0F, 2.0F);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\Musket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */