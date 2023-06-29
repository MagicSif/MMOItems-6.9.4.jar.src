/*    */ package net.Indyuce.mmoitems.util;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class StatChoice {
/*    */   private final String id;
/*    */   private final String hint;
/*    */   
/*    */   public StatChoice(String paramString) {
/* 11 */     this(paramString, "- No Hint Available - ");
/*    */   }
/*    */   
/*    */   public StatChoice(String paramString1, @Nullable String paramString2) {
/* 15 */     this.id = paramString1;
/* 16 */     this.hint = paramString2;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 20 */     return this.id;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getHint() {
/* 25 */     return this.hint;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 30 */     return getId();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 35 */     if (this == paramObject) return true; 
/* 36 */     if (paramObject == null || getClass() != paramObject.getClass()) return false; 
/* 37 */     StatChoice statChoice = (StatChoice)paramObject;
/* 38 */     return this.id.equals(statChoice.id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 43 */     return Objects.hash(new Object[] { this.id });
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitem\\util\StatChoice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */