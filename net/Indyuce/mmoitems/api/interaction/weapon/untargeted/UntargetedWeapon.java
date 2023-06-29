/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.event.item.UntargetedWeaponUseEvent;
/*    */ import net.Indyuce.mmoitems.api.interaction.util.UntargetedDurabilityItem;
/*    */ import net.Indyuce.mmoitems.api.interaction.weapon.Weapon;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ 
/*    */ public abstract class UntargetedWeapon
/*    */   extends Weapon {
/*    */   public UntargetedWeapon(Player paramPlayer, NBTItem paramNBTItem, UntargetedWeaponType paramUntargetedWeaponType) {
/* 18 */     super(paramPlayer, paramNBTItem);
/*    */     
/* 20 */     this.weaponType = paramUntargetedWeaponType;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected final UntargetedWeaponType weaponType;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleTargetFreeAttack(EquipmentSlot paramEquipmentSlot) {
/* 32 */     if (!canAttack(paramEquipmentSlot)) {
/*    */       return;
/*    */     }
/*    */     
/* 36 */     UntargetedDurabilityItem untargetedDurabilityItem = new UntargetedDurabilityItem(getPlayer(), getNBTItem(), paramEquipmentSlot);
/* 37 */     if (untargetedDurabilityItem.isBroken()) {
/*    */       return;
/*    */     }
/*    */     
/* 41 */     PlayerMetadata playerMetadata = getPlayerData().getStats().newTemporary(paramEquipmentSlot);
/* 42 */     if (!checkWeaponCosts(PlayerData.CooldownType.BASIC_ATTACK)) {
/*    */       return;
/*    */     }
/*    */     
/* 46 */     UntargetedWeaponUseEvent untargetedWeaponUseEvent = new UntargetedWeaponUseEvent(this.playerData, this);
/* 47 */     Bukkit.getPluginManager().callEvent((Event)untargetedWeaponUseEvent);
/* 48 */     if (untargetedWeaponUseEvent.isCancelled()) {
/*    */       return;
/*    */     }
/*    */     
/* 52 */     double d = 1.0D / requireNonZero(playerMetadata.getStat("ATTACK_SPEED"), MMOItems.plugin.getConfig().getDouble("default.attack-speed"));
/* 53 */     applyWeaponCosts(d, PlayerData.CooldownType.BASIC_ATTACK);
/*    */ 
/*    */     
/* 56 */     applyAttackEffect(playerMetadata, paramEquipmentSlot);
/*    */ 
/*    */     
/* 59 */     if (untargetedDurabilityItem.isValid()) {
/* 60 */       untargetedDurabilityItem.decreaseDurability(1).inventoryUpdate();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract boolean canAttack(EquipmentSlot paramEquipmentSlot);
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void applyAttackEffect(PlayerMetadata paramPlayerMetadata, EquipmentSlot paramEquipmentSlot);
/*    */ 
/*    */ 
/*    */   
/*    */   public UntargetedWeaponType getWeaponType() {
/* 76 */     return this.weaponType;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\UntargetedWeapon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */