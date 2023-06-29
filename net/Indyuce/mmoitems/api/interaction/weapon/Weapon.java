/*     */ package net.Indyuce.mmoitems.api.interaction.weapon;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.comp.flags.CustomFlag;
/*     */ import io.lumine.mythic.lib.damage.AttackMetadata;
/*     */ import javax.annotation.Nullable;
/*     */ import net.Indyuce.mmoitems.api.interaction.UseItem;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class Weapon
/*     */   extends UseItem {
/*     */   public Weapon(Player paramPlayer, NBTItem paramNBTItem) {
/*  20 */     this(PlayerData.get((OfflinePlayer)paramPlayer), paramNBTItem);
/*     */   }
/*     */   
/*     */   public Weapon(PlayerData paramPlayerData, NBTItem paramNBTItem) {
/*  24 */     super(paramPlayerData, paramNBTItem);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkItemRequirements() {
/*  29 */     if (this.playerData.isEncumbered()) {
/*  30 */       Message.HANDS_TOO_CHARGED.format(ChatColor.RED, new String[0]).send(getPlayer());
/*  31 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  35 */     return (this.playerData.getRPG().canUse(getNBTItem(), true) && MythicLib.plugin.getFlags().isFlagAllowed(getPlayer(), CustomFlag.MI_WEAPONS));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkAndApplyWeaponCosts() {
/*  44 */     if (checkWeaponCosts((PlayerData.CooldownType)null)) {
/*  45 */       applyWeaponCosts(0.0D, (PlayerData.CooldownType)null);
/*  46 */       return true;
/*     */     } 
/*     */     
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkWeaponCosts(@Nullable PlayerData.CooldownType paramCooldownType) {
/*  58 */     if (paramCooldownType != null && getPlayerData().isOnCooldown(paramCooldownType)) {
/*  59 */       return false;
/*     */     }
/*  61 */     double d1 = getNBTItem().getStat("MANA_COST");
/*  62 */     if (d1 > 0.0D && this.playerData.getRPG().getMana() < d1) {
/*  63 */       Message.NOT_ENOUGH_MANA.format(ChatColor.RED, new String[0]).send(getPlayer());
/*  64 */       return false;
/*     */     } 
/*     */     
/*  67 */     double d2 = getNBTItem().getStat("STAMINA_COST");
/*  68 */     if (d2 > 0.0D && this.playerData.getRPG().getStamina() < d2) {
/*  69 */       Message.NOT_ENOUGH_STAMINA.format(ChatColor.RED, new String[0]).send(getPlayer());
/*  70 */       return false;
/*     */     } 
/*     */     
/*  73 */     return true;
/*     */   }
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
/*     */   public void applyWeaponCosts(double paramDouble, @Nullable PlayerData.CooldownType paramCooldownType) {
/*  86 */     double d1 = getNBTItem().getStat("MANA_COST");
/*  87 */     if (d1 > 0.0D) {
/*  88 */       this.playerData.getRPG().giveMana(-d1);
/*     */     }
/*  90 */     double d2 = getNBTItem().getStat("STAMINA_COST");
/*  91 */     if (d2 > 0.0D) {
/*  92 */       this.playerData.getRPG().giveStamina(-d2);
/*     */     }
/*  94 */     if (paramCooldownType != null) {
/*  95 */       getPlayerData().applyCooldown(paramCooldownType, paramDouble);
/*     */     }
/*     */   }
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
/*     */   public boolean handleTargetedAttack(AttackMetadata paramAttackMetadata, LivingEntity paramLivingEntity) {
/* 109 */     if (!checkAndApplyWeaponCosts()) {
/* 110 */       return false;
/*     */     }
/*     */     
/* 113 */     if (getMMOItem().getType().getItemSet().hasAttackEffect() && !getNBTItem().getBoolean("MMOITEMS_DISABLE_ATTACK_PASSIVE")) {
/* 114 */       getMMOItem().getType().getItemSet().applyAttackEffect(paramAttackMetadata, this.playerData, paramLivingEntity, this);
/*     */     }
/* 116 */     return true;
/*     */   }
/*     */   
/*     */   protected Location getGround(Location paramLocation) {
/* 120 */     for (byte b = 0; b < 20; b++) {
/* 121 */       if (paramLocation.getBlock().getType().isSolid())
/* 122 */         return paramLocation; 
/* 123 */       paramLocation.add(0.0D, -1.0D, 0.0D);
/*     */     } 
/* 125 */     return paramLocation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double requireNonZero(double paramDouble1, double paramDouble2) {
/* 132 */     return (paramDouble1 <= 0.0D) ? paramDouble2 : paramDouble1;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapon\Weapon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */