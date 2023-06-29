/*    */ package net.Indyuce.mmoitems.comp.mmocore.stat;
/*    */ 
/*    */ import net.Indyuce.mmocore.api.player.attribute.PlayerAttribute;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ public class ExtraAttribute extends DoubleStat {
/*    */   public ExtraAttribute(PlayerAttribute paramPlayerAttribute) {
/*  9 */     super("ADDITIONAL_" + paramPlayerAttribute.getId().toUpperCase().replace("-", "_"), Material.LIME_DYE, "Additional " + paramPlayerAttribute
/* 10 */         .getName() + " (MMOCore)", new String[] { "Amount of " + paramPlayerAttribute.getName() + " points the player", "gets when holding/wearing this item." }, new String[] { "!block", "all" }, new Material[0]);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\stat\ExtraAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */