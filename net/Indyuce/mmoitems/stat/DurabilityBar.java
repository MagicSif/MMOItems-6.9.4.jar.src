/*   */ package net.Indyuce.mmoitems.stat;
/*   */ 
/*   */ import net.Indyuce.mmoitems.stat.type.BooleanStat;
/*   */ import org.bukkit.Material;
/*   */ 
/*   */ public class DurabilityBar extends BooleanStat {
/*   */   public DurabilityBar() {
/* 8 */     super("DURABILITY_BAR", Material.DAMAGED_ANVIL, "Hide Durability Bar", new String[] { "Enable this to have the green bar", "hidden when using custom durability." }, new String[] { "!block", "all" }, new Material[0]);
/*   */   }
/*   */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\DurabilityBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */