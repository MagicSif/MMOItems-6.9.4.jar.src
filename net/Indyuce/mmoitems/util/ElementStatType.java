/*    */ package net.Indyuce.mmoitems.util;
/*    */ 
/*    */ import io.lumine.mythic.lib.element.Element;
/*    */ 
/*    */ public enum ElementStatType {
/*  6 */   DAMAGE("Flat Damage"),
/*  7 */   DAMAGE_PERCENT("Extra Damage (%)"),
/*  8 */   WEAKNESS("Weakness (%)"),
/*  9 */   DEFENSE("Defense"),
/* 10 */   DEFENSE_PERCENT("Extra Defense (%)");
/*    */   
/*    */   private final String name;
/*    */   
/*    */   ElementStatType(String paramString1) {
/* 15 */     this.name = paramString1;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 19 */     return this.name;
/*    */   }
/*    */   
/*    */   public String lowerCaseName() {
/* 23 */     return name().toLowerCase().replace("_", "-");
/*    */   }
/*    */   
/*    */   public String getConcatenatedTagPath(Element paramElement) {
/* 27 */     return paramElement.getId() + "_" + name();
/*    */   }
/*    */   
/*    */   public String getConcatenatedConfigPath(Element paramElement) {
/* 31 */     return paramElement.getId().toLowerCase().replace("_", "-") + "." + name().toLowerCase().replace("_", "-");
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitem\\util\ElementStatType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */