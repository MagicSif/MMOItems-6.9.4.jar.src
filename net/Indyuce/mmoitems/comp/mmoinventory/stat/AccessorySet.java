/*    */ package net.Indyuce.mmoitems.comp.mmoinventory.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.stat.data.StringData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.StringStat;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class AccessorySet extends StringStat implements GemStoneStat {
/*    */   public AccessorySet() {
/* 18 */     super("ACCESSORY_SET", VersionMaterial.OAK_SIGN.toMaterial(), "Accessory Set (MMOInventory)", new String[] { "Used with MMOInventory's unique", "restriction to only allow one", "accessory to be equipped per set." }, new String[] { "!block", "all" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringData paramStringData) {
/* 23 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag(getNBTPath(), paramStringData.toString()) });
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 28 */     paramString = paramString.toLowerCase();
/* 29 */     paramEditionInventory.getEditedSection().set(getPath(), paramString);
/* 30 */     paramEditionInventory.registerTemplateEdition();
/* 31 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin
/* 32 */         .getPrefix() + getName() + " successfully changed to " + MythicLib.plugin.parseColors(paramString) + ChatColor.GRAY + ".");
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmoinventory\stat\AccessorySet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */