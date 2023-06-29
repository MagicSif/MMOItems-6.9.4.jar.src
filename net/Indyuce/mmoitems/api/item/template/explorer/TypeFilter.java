/*    */ package net.Indyuce.mmoitems.api.item.template.explorer;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TypeFilter
/*    */   implements Predicate<MMOItemTemplate>
/*    */ {
/*    */   private final Type type;
/*    */   
/*    */   public TypeFilter(Type paramType) {
/* 15 */     this.type = paramType;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(MMOItemTemplate paramMMOItemTemplate) {
/* 20 */     return paramMMOItemTemplate.getType().equals(this.type);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\template\explorer\TypeFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */