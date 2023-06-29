/*    */ package net.Indyuce.mmoitems.api.item.template;
/*    */ 
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NameModifier
/*    */ {
/*    */   private final ModifierType type;
/*    */   private final String format;
/*    */   private final int priority;
/*    */   
/*    */   public NameModifier(ModifierType paramModifierType, Object paramObject) {
/* 21 */     Validate.notNull(paramObject, "Object cannot be null");
/* 22 */     this.type = paramModifierType;
/*    */     
/* 24 */     if (paramObject instanceof String) {
/* 25 */       this.format = (String)paramObject;
/* 26 */       this.priority = 0;
/*    */       
/*    */       return;
/*    */     } 
/* 30 */     if (paramObject instanceof ConfigurationSection) {
/* 31 */       ConfigurationSection configurationSection = (ConfigurationSection)paramObject;
/* 32 */       Validate.isTrue(configurationSection.contains("format"), MMOUtils.caseOnWords(paramModifierType.name().toLowerCase()) + " format cannot be null");
/* 33 */       this.format = configurationSection.get("format").toString();
/* 34 */       this.priority = configurationSection.getInt("priority");
/*    */       
/*    */       return;
/*    */     } 
/* 38 */     throw new IllegalArgumentException("Must specify a string or a config section");
/*    */   }
/*    */   
/*    */   public NameModifier(ModifierType paramModifierType, String paramString, int paramInt) {
/* 42 */     Validate.notNull(paramString, "Format cannot be null");
/* 43 */     this.type = paramModifierType;
/* 44 */     this.format = paramString;
/* 45 */     this.priority = paramInt;
/*    */     
/* 47 */     Validate.notNull(paramModifierType, "Type cannot be null");
/*    */   }
/*    */   
/*    */   public String getFormat() {
/* 51 */     return this.format;
/*    */   }
/*    */   
/*    */   public ModifierType getType() {
/* 55 */     return this.type;
/*    */   }
/*    */   
/*    */   public boolean hasPriority() {
/* 59 */     return (this.priority > 0);
/*    */   }
/*    */   
/*    */   public int getPriority() {
/* 63 */     return this.priority;
/*    */   }
/*    */   
/*    */   public enum ModifierType {
/* 67 */     PREFIX,
/* 68 */     SUFFIX;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\template\NameModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */