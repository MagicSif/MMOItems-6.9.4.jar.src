/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.StringStat;
/*    */ import net.Indyuce.mmoitems.stat.type.TemplateOption;
/*    */ 
/*    */ public class CraftingPermission extends StringStat implements TemplateOption, GemStoneStat {
/*    */   public CraftingPermission() {
/* 10 */     super("CRAFT_PERMISSION", VersionMaterial.OAK_SIGN.toMaterial(), "Crafting Recipe Permission", new String[] { "The permission needed to craft this item.", "Changing this value requires &o/mi reload recipes&7." }, new String[] { "all" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\CraftingPermission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */