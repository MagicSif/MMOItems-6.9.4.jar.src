/*    */ package net.Indyuce.mmoitems.comp.enchants;
/*    */ 
/*    */ import java.util.Map;
/*    */ import me.badbones69.crazyenchantments.api.objects.CEnchantment;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ 
/*    */ public class CrazyEnchantsData
/*    */   implements StatData {
/*    */   private final Map<CEnchantment, Integer> enchants;
/*    */   
/*    */   public CrazyEnchantsData(Map<CEnchantment, Integer> paramMap) {
/* 12 */     this.enchants = paramMap;
/*    */   }
/*    */   
/*    */   public Map<CEnchantment, Integer> getEnchants() {
/* 16 */     return this.enchants;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\enchants\CrazyEnchantsData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */