/*    */ package net.Indyuce.mmoitems.api.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class StringValue {
/*    */   private final String name;
/*    */   private final double value;
/*    */   
/*    */   public StringValue(String paramString, double paramDouble) {
/* 11 */     this.name = paramString;
/* 12 */     this.value = paramDouble;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   
/*    */   public double getValue() {
/* 20 */     return this.value;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public static Map<String, Double> readFromArray(StringValue... paramVarArgs) {
/* 25 */     HashMap<Object, Object> hashMap = new HashMap<>();
/* 26 */     for (StringValue stringValue : paramVarArgs)
/* 27 */       hashMap.put(stringValue.getName(), Double.valueOf(stringValue.getValue())); 
/* 28 */     return (Map)hashMap;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 33 */     return (paramObject instanceof StringValue && ((StringValue)paramObject).name.equals(this.name) && ((StringValue)paramObject).value == this.value);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\ap\\util\StringValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */