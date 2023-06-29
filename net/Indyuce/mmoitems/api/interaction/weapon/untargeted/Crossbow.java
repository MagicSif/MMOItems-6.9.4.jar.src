/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import io.lumine.mythic.lib.skill.trigger.TriggerType;
/*    */ import io.lumine.mythic.lib.util.CustomProjectile;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.CustomSound;
/*    */ import net.Indyuce.mmoitems.listener.CustomSoundListener;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Arrow;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class Crossbow
/*    */   extends UntargetedWeapon {
/*    */   public Crossbow(Player paramPlayer, NBTItem paramNBTItem) {
/* 22 */     super(paramPlayer, paramNBTItem, UntargetedWeaponType.RIGHT_CLICK);
/*    */   }
/*    */   private boolean consumesArrow;
/*    */   
/*    */   public boolean canAttack(EquipmentSlot paramEquipmentSlot) {
/* 27 */     this.consumesArrow = !getNBTItem().getBoolean("MMOITEMS_DISABLE_ARROW_CONSUMPTION");
/* 28 */     return (this.player.getGameMode() == GameMode.CREATIVE || !this.consumesArrow || getPlayer().getInventory().containsAtLeast(new ItemStack(Material.ARROW), 1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void applyAttackEffect(PlayerMetadata paramPlayerMetadata, EquipmentSlot paramEquipmentSlot) {
/* 35 */     if (getPlayer().getGameMode() != GameMode.CREATIVE && this.consumesArrow) {
/* 36 */       getPlayer().getInventory().removeItem(new ItemStack[] { new ItemStack(Material.ARROW) });
/*    */     }
/* 38 */     Arrow arrow = (Arrow)getPlayer().launchProjectile(Arrow.class);
/* 39 */     arrow.setVelocity(getPlayer().getEyeLocation().getDirection().multiply(3.0D * requireNonZero(paramPlayerMetadata.getStat("ARROW_VELOCITY"), 1.0D)));
/* 40 */     getPlayer().setVelocity(getPlayer().getVelocity().setX(0).setZ(0));
/*    */ 
/*    */     
/* 43 */     CustomSoundListener.playSound(getNBTItem().getItem(), CustomSound.ON_CROSSBOW, this.player, Sound.ENTITY_ARROW_SHOOT);
/*    */ 
/*    */     
/* 46 */     MMOItems.plugin.getEntities().registerCustomProjectile(getNBTItem(), paramPlayerMetadata, (Entity)arrow, 1.0D);
/*    */ 
/*    */     
/* 49 */     paramPlayerMetadata.getData().triggerSkills(TriggerType.SHOOT_BOW, paramEquipmentSlot, (Entity)arrow);
/* 50 */     new CustomProjectile(paramPlayerMetadata.getData(), CustomProjectile.ProjectileType.ARROW, (Entity)arrow, paramEquipmentSlot);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\Crossbow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */