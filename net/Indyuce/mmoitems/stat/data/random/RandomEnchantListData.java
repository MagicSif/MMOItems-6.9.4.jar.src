/*    */ package net.Indyuce.mmoitems.stat.data.random;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.stat.Enchants;
/*    */ import net.Indyuce.mmoitems.stat.data.EnchantListData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ 
/*    */ public class RandomEnchantListData
/*    */   implements RandomStatData<EnchantListData> {
/* 17 */   private final Map<Enchantment, NumericStatFormula> enchants = new HashMap<>();
/*    */   
/*    */   public RandomEnchantListData(ConfigurationSection paramConfigurationSection) {
/* 20 */     Validate.notNull(paramConfigurationSection, "Config cannot be null");
/*    */     
/* 22 */     for (String str : paramConfigurationSection.getKeys(false)) {
/* 23 */       Enchantment enchantment = Enchants.getEnchant(str);
/* 24 */       Validate.notNull(enchantment, "Could not find enchant with key '" + str + "'");
/* 25 */       addEnchant(enchantment, new NumericStatFormula(paramConfigurationSection.get(str)));
/*    */     } 
/*    */   }
/*    */   
/*    */   public Set<Enchantment> getEnchants() {
/* 30 */     return this.enchants.keySet();
/*    */   }
/*    */   
/*    */   public NumericStatFormula getLevel(Enchantment paramEnchantment) {
/* 34 */     return this.enchants.get(paramEnchantment);
/*    */   }
/*    */   
/*    */   public void addEnchant(Enchantment paramEnchantment, NumericStatFormula paramNumericStatFormula) {
/* 38 */     this.enchants.put(paramEnchantment, paramNumericStatFormula);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnchantListData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 43 */     EnchantListData enchantListData = new EnchantListData();
/* 44 */     this.enchants.forEach((paramEnchantment, paramNumericStatFormula) -> paramEnchantListData.addEnchant(paramEnchantment, (int)Math.max(paramNumericStatFormula.calculate(paramMMOItemBuilder.getLevel()), paramEnchantment.getStartLevel())));
/* 45 */     return enchantListData;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\random\RandomEnchantListData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */