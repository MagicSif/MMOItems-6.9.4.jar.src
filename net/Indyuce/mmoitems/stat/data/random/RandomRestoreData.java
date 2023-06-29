/*    */ package net.Indyuce.mmoitems.stat.data.random;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.stat.data.RestoreData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ 
/*    */ public class RandomRestoreData
/*    */   implements RandomStatData<RestoreData> {
/*    */   private final NumericStatFormula health;
/*    */   
/*    */   public RandomRestoreData(ConfigurationSection paramConfigurationSection) {
/* 15 */     Validate.notNull(paramConfigurationSection, "Could not load restore config");
/*    */     
/* 17 */     this.health = paramConfigurationSection.contains("health") ? new NumericStatFormula(paramConfigurationSection) : NumericStatFormula.ZERO;
/* 18 */     this.food = paramConfigurationSection.contains("food") ? new NumericStatFormula(paramConfigurationSection) : NumericStatFormula.ZERO;
/* 19 */     this.saturation = paramConfigurationSection.contains("saturation") ? new NumericStatFormula(paramConfigurationSection) : NumericStatFormula.ZERO;
/*    */   }
/*    */   private final NumericStatFormula food; private final NumericStatFormula saturation;
/*    */   public NumericStatFormula getHealth() {
/* 23 */     return this.health;
/*    */   }
/*    */   
/*    */   public NumericStatFormula getFood() {
/* 27 */     return this.food;
/*    */   }
/*    */   
/*    */   public NumericStatFormula getSaturation() {
/* 31 */     return this.saturation;
/*    */   }
/*    */ 
/*    */   
/*    */   public RestoreData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 36 */     return new RestoreData(this.health.calculate(paramMMOItemBuilder.getLevel()), this.food.calculate(paramMMOItemBuilder.getLevel()), this.saturation.calculate(paramMMOItemBuilder.getLevel()));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\random\RandomRestoreData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */