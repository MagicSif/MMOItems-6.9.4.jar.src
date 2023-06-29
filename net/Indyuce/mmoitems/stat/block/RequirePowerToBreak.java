/*    */ package net.Indyuce.mmoitems.stat.block;
/*    */ 
/*    */ import net.Indyuce.mmoitems.stat.type.BooleanStat;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RequirePowerToBreak
/*    */   extends BooleanStat
/*    */ {
/*    */   public RequirePowerToBreak() {
/* 17 */     super("REQUIRE_POWER_TO_BREAK", Material.BEDROCK, "Require Power to Break", new String[] { "When enabled, the block will NOT break", "if the player doesn't have enough pickaxe", "power, unlike vanilla block behaviour." }, new String[] { "block" }, new Material[0]);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\block\RequirePowerToBreak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */