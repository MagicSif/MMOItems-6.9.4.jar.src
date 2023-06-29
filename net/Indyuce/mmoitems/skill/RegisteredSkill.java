/*    */ package net.Indyuce.mmoitems.skill;
/*    */ 
/*    */ import io.lumine.mythic.lib.UtilityMethods;
/*    */ import io.lumine.mythic.lib.skill.handler.SkillHandler;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class RegisteredSkill {
/*    */   @NotNull
/*    */   private final SkillHandler<?> handler;
/*    */   @NotNull
/* 16 */   private final Map<String, String> parameterNames = new HashMap<>(); @NotNull private final String name; @NotNull
/* 17 */   private final Map<String, Double> defaultParameterValues = new HashMap<>();
/*    */   
/*    */   public RegisteredSkill(@NotNull SkillHandler<?> paramSkillHandler, @NotNull ConfigurationSection paramConfigurationSection) {
/* 20 */     this.handler = paramSkillHandler;
/*    */     
/* 22 */     this.name = Objects.<String>requireNonNull(paramConfigurationSection.getString("name"), "Could not fill skill name");
/* 23 */     for (String str : paramSkillHandler.getModifiers()) {
/* 24 */       this.parameterNames.put(str, paramConfigurationSection.getString("modifier." + str + ".name", UtilityMethods.caseOnWords(str.replace("_", " ").replace("-", " ").toLowerCase())));
/* 25 */       this.defaultParameterValues.put(str, Double.valueOf(paramConfigurationSection.getDouble("modifier." + str + ".default-value")));
/*    */     } 
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public RegisteredSkill(@NotNull SkillHandler<?> paramSkillHandler, @NotNull String paramString) {
/* 31 */     this.handler = paramSkillHandler;
/* 32 */     this.name = paramString;
/*    */   }
/*    */   @NotNull
/*    */   public SkillHandler<?> getHandler() {
/* 36 */     return this.handler;
/*    */   }
/*    */   @NotNull
/*    */   public String getName() {
/* 40 */     return this.name;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public void setDefaultValue(String paramString, double paramDouble) {
/* 45 */     this.defaultParameterValues.put(paramString, Double.valueOf(paramDouble));
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public void setName(String paramString1, String paramString2) {
/* 50 */     this.parameterNames.put(paramString1, paramString2);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getParameterName(String paramString) {
/* 55 */     return this.parameterNames.get(paramString);
/*    */   }
/*    */   
/*    */   public double getDefaultModifier(String paramString) {
/* 59 */     return ((Double)this.defaultParameterValues.get(paramString)).doubleValue();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\skill\RegisteredSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */