/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import net.Indyuce.mmoitems.stat.type.BooleanStat;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LostWhenBroken
/*    */   extends BooleanStat
/*    */ {
/*    */   public LostWhenBroken() {
/* 14 */     super("WILL_BREAK", Material.SHEARS, "Lost when Broken?", new String[] { "If set to true, the item will be lost", "once it reaches 0 durability." }, new String[] { "!block", "all" }, new Material[0]);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\LostWhenBroken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */