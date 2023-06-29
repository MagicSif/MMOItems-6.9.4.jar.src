/*    */ package net.Indyuce.mmoitems.api.interaction.projectile;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import net.Indyuce.mmoitems.stat.data.PotionEffectData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProjectileData
/*    */ {
/*    */   private final NBTItem sourceItem;
/*    */   private final PlayerMetadata shooter;
/*    */   private final double damageMultiplier;
/*    */   
/*    */   @Deprecated
/*    */   public ProjectileData(PlayerMetadata paramPlayerMetadata, NBTItem paramNBTItem, boolean paramBoolean, double paramDouble) {
/* 24 */     this(paramPlayerMetadata, paramNBTItem, paramDouble);
/*    */   }
/*    */   
/*    */   public ProjectileData(PlayerMetadata paramPlayerMetadata, NBTItem paramNBTItem, double paramDouble) {
/* 28 */     this.shooter = paramPlayerMetadata;
/* 29 */     this.sourceItem = paramNBTItem;
/* 30 */     this.damageMultiplier = paramDouble;
/*    */   }
/*    */   
/*    */   public NBTItem getSourceItem() {
/* 34 */     return this.sourceItem;
/*    */   }
/*    */   
/*    */   public PlayerMetadata getShooter() {
/* 38 */     return this.shooter;
/*    */   }
/*    */   
/*    */   public double getDamageMultiplier() {
/* 42 */     return this.damageMultiplier;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public boolean isCustomWeapon() {
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double getDamage() {
/* 63 */     Validate.isTrue(isCustomWeapon(), "Not a custom bow");
/* 64 */     return this.shooter.getStat("ATTACK_DAMAGE") * this.damageMultiplier;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public void setDamage(double paramDouble) {
/* 72 */     Validate.isTrue(isCustomWeapon(), "Not a custom bow");
/* 73 */     this.shooter.setStat("ATTACK_DAMAGE", paramDouble);
/*    */   }
/*    */   
/*    */   public void applyPotionEffects(LivingEntity paramLivingEntity) {
/* 77 */     if (this.sourceItem.hasTag("MMOITEMS_ARROW_POTION_EFFECTS"))
/* 78 */       for (ArrowPotionEffectArrayItem arrowPotionEffectArrayItem : (ArrowPotionEffectArrayItem[])MythicLib.plugin.getJson().parse(this.sourceItem.getString("MMOITEMS_ARROW_POTION_EFFECTS"), ArrowPotionEffectArrayItem[].class))
/* 79 */         paramLivingEntity.addPotionEffect((new PotionEffectData(PotionEffectType.getByName(arrowPotionEffectArrayItem.type), arrowPotionEffectArrayItem.duration, arrowPotionEffectArrayItem.level)).toEffect());  
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\projectile\ProjectileData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */