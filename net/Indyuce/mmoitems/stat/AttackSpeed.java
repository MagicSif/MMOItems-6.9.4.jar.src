/*   */ package net.Indyuce.mmoitems.stat;
/*   */ 
/*   */ import io.lumine.mythic.lib.version.VersionMaterial;
/*   */ import net.Indyuce.mmoitems.stat.type.AttackWeaponStat;
/*   */ import org.bukkit.attribute.Attribute;
/*   */ 
/*   */ public class AttackSpeed extends AttackWeaponStat {
/*   */   public AttackSpeed() {
/* 9 */     super("ATTACK_SPEED", VersionMaterial.LIGHT_GRAY_DYE.toMaterial(), "Attack Speed", new String[] { "The speed at which your weapon strikes.", "In attacks/sec." }, Attribute.GENERIC_ATTACK_SPEED);
/*   */   }
/*   */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\AttackSpeed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */