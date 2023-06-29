/*    */ package net.Indyuce.mmoitems.comp;
/*    */ import io.lumine.mythic.lib.metrics.bukkit.Metrics;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class MMOItemsMetrics extends Metrics {
/*    */   public MMOItemsMetrics() {
/*  9 */     super((Plugin)MMOItems.plugin);
/*    */     
/* 11 */     addCustomChart((Metrics.CustomChart)new Metrics.SingleLineChart("items", () -> {
/*    */             int i = 0;
/*    */             for (Type type : MMOItems.plugin.getTypes().getAll())
/*    */               i += type.getConfigFile().getConfig().getKeys(false).size(); 
/*    */             return Integer.valueOf(i);
/*    */           }));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\MMOItemsMetrics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */