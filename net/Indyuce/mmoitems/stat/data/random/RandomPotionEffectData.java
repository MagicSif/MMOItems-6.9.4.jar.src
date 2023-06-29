/*    */ package net.Indyuce.mmoitems.stat.data.random;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.stat.data.PotionEffectData;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class RandomPotionEffectData {
/*    */   private final PotionEffectType type;
/*    */   private final NumericStatFormula duration;
/*    */   private final NumericStatFormula amplifier;
/*    */   
/*    */   public RandomPotionEffectData(ConfigurationSection paramConfigurationSection) {
/* 17 */     Validate.notNull(paramConfigurationSection, "Potion effect config cannot be null");
/*    */     
/* 19 */     this.type = PotionEffectType.getByName(paramConfigurationSection.getName().toUpperCase().replace("-", "_").replace(" ", "_"));
/* 20 */     Validate.notNull(this.type, "Could not find potion effect with name '" + paramConfigurationSection.getName() + "'");
/*    */     
/* 22 */     this.duration = new NumericStatFormula(paramConfigurationSection.get("duration"));
/* 23 */     this.amplifier = new NumericStatFormula(paramConfigurationSection.get("amplifier"));
/*    */   }
/*    */   
/*    */   public RandomPotionEffectData(PotionEffectType paramPotionEffectType, NumericStatFormula paramNumericStatFormula) {
/* 27 */     this(paramPotionEffectType, new NumericStatFormula(MMOUtils.getEffectDuration(paramPotionEffectType) / 20.0D, 0.0D, 0.0D, 0.0D), paramNumericStatFormula);
/*    */   }
/*    */   
/*    */   public RandomPotionEffectData(PotionEffectType paramPotionEffectType, NumericStatFormula paramNumericStatFormula1, NumericStatFormula paramNumericStatFormula2) {
/* 31 */     this.type = paramPotionEffectType;
/* 32 */     this.duration = paramNumericStatFormula1;
/* 33 */     this.amplifier = paramNumericStatFormula2;
/*    */   }
/*    */   
/*    */   public PotionEffectType getType() {
/* 37 */     return this.type;
/*    */   }
/*    */   
/*    */   public NumericStatFormula getDuration() {
/* 41 */     return this.duration;
/*    */   }
/*    */   
/*    */   public NumericStatFormula getAmplifier() {
/* 45 */     return this.amplifier;
/*    */   }
/*    */   
/*    */   public PotionEffectData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 49 */     return new PotionEffectData(this.type, this.duration.calculate(paramMMOItemBuilder.getLevel()), (int)this.amplifier.calculate(paramMMOItemBuilder.getLevel()));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\random\RandomPotionEffectData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */