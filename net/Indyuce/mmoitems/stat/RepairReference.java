/*   */ package net.Indyuce.mmoitems.stat;
/*   */ 
/*   */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*   */ import net.Indyuce.mmoitems.stat.type.StringStat;
/*   */ import org.bukkit.Material;
/*   */ 
/*   */ public class RepairReference extends StringStat implements GemStoneStat {
/*   */   public RepairReference() {
/* 9 */     super("REPAIR_TYPE", Material.ANVIL, "Repair Reference", new String[] { "If items have a repair reference, they can", "only be repaired by consumables", "with the same repair reference,", "and vice-versa." }, new String[] { "all" }, new Material[0]);
/*   */   }
/*   */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\RepairReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */