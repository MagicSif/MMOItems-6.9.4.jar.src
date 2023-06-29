/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class PotionEffectData
/*    */ {
/*    */   private final PotionEffectType type;
/*    */   private final double duration;
/*    */   private final int level;
/*    */   
/*    */   public PotionEffectData(PotionEffectType paramPotionEffectType, int paramInt) {
/* 15 */     this(paramPotionEffectType, MMOUtils.getEffectDuration(paramPotionEffectType) / 20.0D, paramInt);
/*    */   }
/*    */   
/*    */   public PotionEffectData(PotionEffect paramPotionEffect) {
/* 19 */     this.type = paramPotionEffect.getType();
/* 20 */     this.duration = paramPotionEffect.getDuration() / 20.0D;
/* 21 */     this.level = paramPotionEffect.getAmplifier() + 1;
/*    */   }
/*    */   
/*    */   public PotionEffectData(PotionEffectType paramPotionEffectType, double paramDouble, int paramInt) {
/* 25 */     this.type = paramPotionEffectType;
/* 26 */     this.duration = paramDouble;
/* 27 */     this.level = paramInt;
/*    */   }
/*    */   
/*    */   public PotionEffectType getType() {
/* 31 */     return this.type;
/*    */   }
/*    */   
/*    */   public double getDuration() {
/* 35 */     return this.duration;
/*    */   }
/*    */   
/*    */   public int getLevel() {
/* 39 */     return this.level;
/*    */   }
/*    */   
/*    */   public PotionEffect toEffect() {
/* 43 */     return new PotionEffect(this.type, (int)(this.duration * 20.0D), this.level - 1, true, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 48 */     if (this == paramObject) return true; 
/* 49 */     if (paramObject == null || getClass() != paramObject.getClass()) return false; 
/* 50 */     PotionEffectData potionEffectData = (PotionEffectData)paramObject;
/* 51 */     return (Double.compare(potionEffectData.duration, this.duration) == 0 && this.level == potionEffectData.level && this.type.equals(potionEffectData.type));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 56 */     return Objects.hash(new Object[] { this.type, Double.valueOf(this.duration), Integer.valueOf(this.level) });
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\PotionEffectData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */