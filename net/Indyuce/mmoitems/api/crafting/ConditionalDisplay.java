/*    */ package net.Indyuce.mmoitems.api.crafting;
/*    */ 
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ 
/*    */ public class ConditionalDisplay {
/*    */   private final String positive;
/*    */   
/*    */   public ConditionalDisplay(String paramString1, String paramString2) {
/*  9 */     this.positive = paramString1;
/* 10 */     this.negative = paramString2;
/*    */   }
/*    */ 
/*    */   
/*    */   private final String negative;
/*    */   
/*    */   public ConditionalDisplay(ConfigurationSection paramConfigurationSection) {
/* 17 */     this(paramConfigurationSection.getString("positive"), paramConfigurationSection.getString("negative"));
/*    */   }
/*    */   
/*    */   public String format(boolean paramBoolean) {
/* 21 */     return paramBoolean ? this.positive : this.negative;
/*    */   }
/*    */   
/*    */   public void setup(ConfigurationSection paramConfigurationSection) {
/* 25 */     paramConfigurationSection.set("positive", this.positive);
/* 26 */     paramConfigurationSection.set("negative", this.negative);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\ConditionalDisplay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */