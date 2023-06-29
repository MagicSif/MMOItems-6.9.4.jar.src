/*    */ package net.Indyuce.mmoitems.api.item.template.explorer;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IDFilter
/*    */   implements Predicate<MMOItemTemplate>
/*    */ {
/*    */   private final String id;
/*    */   
/*    */   public IDFilter(String paramString) {
/* 14 */     this.id = paramString;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(MMOItemTemplate paramMMOItemTemplate) {
/* 19 */     return paramMMOItemTemplate.getId().equalsIgnoreCase(this.id);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\template\explorer\IDFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */