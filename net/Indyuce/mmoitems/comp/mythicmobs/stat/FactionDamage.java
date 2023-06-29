/*   */ package net.Indyuce.mmoitems.comp.mythicmobs.stat;
/*   */ 
/*   */ import io.lumine.mythic.lib.version.VersionMaterial;
/*   */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*   */ 
/*   */ public class FactionDamage
/*   */   extends DoubleStat {
/*   */   public FactionDamage(String paramString) {
/* 9 */     super("FACTION_DAMAGE_" + paramString.toUpperCase(), VersionMaterial.RED_DYE.toMaterial(), paramString + " Faction Damage", new String[] { "Deals additional damage to mobs", "from the " + paramString + " faction in %." }, new String[] { "!block", "all" }, new org.bukkit.Material[0]);
/*   */   }
/*   */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mythicmobs\stat\FactionDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */