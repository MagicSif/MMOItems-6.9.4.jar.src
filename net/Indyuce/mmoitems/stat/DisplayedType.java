/*   */ package net.Indyuce.mmoitems.stat;
/*   */ 
/*   */ import io.lumine.mythic.lib.version.VersionMaterial;
/*   */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*   */ import net.Indyuce.mmoitems.stat.type.StringStat;
/*   */ 
/*   */ public class DisplayedType extends StringStat implements GemStoneStat {
/*   */   public DisplayedType() {
/* 9 */     super("DISPLAYED_TYPE", VersionMaterial.OAK_SIGN.toMaterial(), "Displayed Type", new String[] { "This option will only affect the", "type displayed on the item lore." }, new String[] { "all" }, new org.bukkit.Material[0]);
/*   */   }
/*   */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\DisplayedType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */