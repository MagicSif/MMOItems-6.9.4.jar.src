/*   */ package net.Indyuce.mmoitems.comp.mmoinventory;
/*   */ import net.Indyuce.mmoitems.MMOItems;
/*   */ import net.Indyuce.mmoitems.comp.mmoinventory.stat.AccessorySet;
/*   */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*   */ 
/*   */ public class MMOInventorySupport {
/*   */   public MMOInventorySupport() {
/* 8 */     MMOItems.plugin.getStats().register((ItemStat)new AccessorySet());
/*   */   }
/*   */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmoinventory\MMOInventorySupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */