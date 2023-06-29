/*    */ package net.Indyuce.mmoitems.stat.data.random;
/*    */ 
/*    */ import io.lumine.mythic.lib.skill.trigger.TriggerType;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.skill.RegisteredSkill;
/*    */ import net.Indyuce.mmoitems.stat.data.AbilityData;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ 
/*    */ 
/*    */ public class RandomAbilityData
/*    */ {
/*    */   private final RegisteredSkill ability;
/*    */   private final TriggerType triggerType;
/* 21 */   private final Map<String, NumericStatFormula> modifiers = new HashMap<>();
/*    */   
/*    */   public RandomAbilityData(ConfigurationSection paramConfigurationSection) {
/* 24 */     Validate.isTrue((paramConfigurationSection.contains("type") && paramConfigurationSection.contains("mode")), "Ability is missing type or mode");
/*    */     
/* 26 */     String str1 = paramConfigurationSection.getString("type").toUpperCase().replace("-", "_").replace(" ", "_");
/* 27 */     Validate.isTrue(MMOItems.plugin.getSkills().hasSkill(str1), "Could not find ability called '" + str1 + "'");
/* 28 */     this.ability = MMOItems.plugin.getSkills().getSkill(str1);
/*    */     
/* 30 */     String str2 = paramConfigurationSection.getString("mode").toUpperCase().replace("-", "_").replace(" ", "_");
/* 31 */     this.triggerType = MMOUtils.backwardsCompatibleTriggerType(str2);
/*    */     
/* 33 */     for (String str : paramConfigurationSection.getKeys(false)) {
/* 34 */       if (!str.equalsIgnoreCase("mode") && !str.equalsIgnoreCase("type") && this.ability.getHandler().getModifiers().contains(str))
/* 35 */         this.modifiers.put(str, new NumericStatFormula(paramConfigurationSection.get(str))); 
/*    */     } 
/*    */   }
/*    */   public RandomAbilityData(RegisteredSkill paramRegisteredSkill, TriggerType paramTriggerType) {
/* 39 */     this.ability = paramRegisteredSkill;
/* 40 */     this.triggerType = paramTriggerType;
/*    */   }
/*    */   
/*    */   public RegisteredSkill getAbility() {
/* 44 */     return this.ability;
/*    */   }
/*    */   
/*    */   public TriggerType getTriggerType() {
/* 48 */     return this.triggerType;
/*    */   }
/*    */   
/*    */   public Set<String> getModifiers() {
/* 52 */     return this.modifiers.keySet();
/*    */   }
/*    */   
/*    */   public void setModifier(String paramString, NumericStatFormula paramNumericStatFormula) {
/* 56 */     this.modifiers.put(paramString, paramNumericStatFormula);
/*    */   }
/*    */   
/*    */   public boolean hasModifier(String paramString) {
/* 60 */     return this.modifiers.containsKey(paramString);
/*    */   }
/*    */   
/*    */   public NumericStatFormula getModifier(String paramString) {
/* 64 */     return this.modifiers.get(paramString);
/*    */   }
/*    */   
/*    */   public AbilityData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 68 */     AbilityData abilityData = new AbilityData(this.ability, this.triggerType);
/* 69 */     this.modifiers.forEach((paramString, paramNumericStatFormula) -> paramAbilityData.setModifier(paramString, paramNumericStatFormula.calculate(paramMMOItemBuilder.getLevel())));
/* 70 */     return abilityData;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\random\RandomAbilityData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */