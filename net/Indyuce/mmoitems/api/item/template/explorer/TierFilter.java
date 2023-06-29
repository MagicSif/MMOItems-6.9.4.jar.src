/*    */ package net.Indyuce.mmoitems.api.item.template.explorer;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TierFilter
/*    */   implements Predicate<MMOItemTemplate>
/*    */ {
/*    */   private final String id;
/*    */   
/*    */   public TierFilter(String paramString) {
/* 22 */     this.id = paramString;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(MMOItemTemplate paramMMOItemTemplate) {
/* 27 */     RandomStatData randomStatData = (RandomStatData)paramMMOItemTemplate.getBaseItemData().get(ItemStats.TIER);
/* 28 */     return (randomStatData != null && randomStatData.toString().equalsIgnoreCase(this.id));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\template\explorer\TierFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */