/*    */ package net.Indyuce.mmoitems.api.interaction.weapon;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.Particle;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class Gauntlet extends Weapon {
/*    */   public Gauntlet(Player paramPlayer, NBTItem paramNBTItem) {
/* 15 */     super(paramPlayer, paramNBTItem);
/*    */   }
/*    */   
/*    */   public void specialAttack(LivingEntity paramLivingEntity) {
/* 19 */     if (!MMOItems.plugin.getConfig().getBoolean("item-ability.gauntlet.enabled")) {
/*    */       return;
/*    */     }
/* 22 */     if (!checkWeaponCosts(PlayerData.CooldownType.SPECIAL_ATTACK)) {
/*    */       return;
/*    */     }
/* 25 */     applyWeaponCosts(MMOItems.plugin.getConfig().getDouble("item-ability.gauntlet.cooldown"), PlayerData.CooldownType.SPECIAL_ATTACK);
/* 26 */     paramLivingEntity.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, paramLivingEntity.getLocation().add(0.0D, 1.0D, 0.0D), 0);
/* 27 */     paramLivingEntity.getWorld().playSound(paramLivingEntity.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 0.0F);
/* 28 */     paramLivingEntity.removePotionEffect(PotionEffectType.BLINDNESS);
/* 29 */     paramLivingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
/* 30 */     paramLivingEntity.setVelocity(getPlayer().getEyeLocation().getDirection().setY(0).normalize().setY(0.8D));
/* 31 */     paramLivingEntity.setVelocity(getPlayer().getEyeLocation().getDirection().setY(0).normalize().multiply(2).setY(0.3D));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapon\Gauntlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */