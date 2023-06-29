/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SuccessRate
/*    */   extends DoubleStat
/*    */   implements GemStoneStat
/*    */ {
/*    */   public SuccessRate() {
/* 14 */     super("SUCCESS_RATE", Material.EMERALD, "Success Rate", new String[] { "The chance of your gem/skin to successfully", "apply onto an item. This value is 100%", "by default. If it is not successfully", "applied, the gem/skin will be lost." }, new String[] { "gem_stone", "skin" }, new Material[0]);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\SuccessRate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */