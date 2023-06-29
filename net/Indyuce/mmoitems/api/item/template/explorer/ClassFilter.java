/*    */ package net.Indyuce.mmoitems.api.item.template.explorer;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import net.Indyuce.mmoitems.stat.data.StringListData;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassFilter
/*    */   implements Predicate<MMOItemTemplate>
/*    */ {
/*    */   private final String name;
/*    */   
/*    */   public ClassFilter(RPGPlayer paramRPGPlayer) {
/* 17 */     this(paramRPGPlayer.getClassName());
/*    */   }
/*    */   
/*    */   public ClassFilter(String paramString) {
/* 21 */     this.name = paramString;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(MMOItemTemplate paramMMOItemTemplate) {
/* 26 */     if (!paramMMOItemTemplate.getBaseItemData().containsKey(ItemStats.REQUIRED_CLASS)) {
/* 27 */       return true;
/*    */     }
/*    */     
/* 30 */     for (String str : ((StringListData)paramMMOItemTemplate.getBaseItemData().get(ItemStats.REQUIRED_CLASS)).getList()) {
/* 31 */       if (str.equalsIgnoreCase(this.name))
/* 32 */         return true; 
/*    */     } 
/* 34 */     return false;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\template\explorer\ClassFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */