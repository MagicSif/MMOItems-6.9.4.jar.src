/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.stat.data.StringData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.StringStat;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Material;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class LoreFormat
/*    */   extends StringStat
/*    */   implements GemStoneStat {
/*    */   public LoreFormat() {
/* 19 */     super("LORE_FORMAT", Material.MAP, "Lore Format", new String[] { "The lore format decides", "where each stat goes.", "&9Formats can be configured in", "&9the lore-formats folder" }, new String[] { "all" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringData paramStringData) {
/* 26 */     String str = paramStringData.toString();
/* 27 */     Validate.isTrue(MMOItems.plugin.getFormats().hasFormat(str), "Could not find lore format with ID '" + str + "'");
/*    */     
/* 29 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag(getNBTPath(), str) });
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 34 */     Validate.isTrue(MMOItems.plugin.getFormats().hasFormat(paramString), "Couldn't find lore format with ID '" + paramString + "'.");
/*    */     
/* 36 */     paramEditionInventory.getEditedSection().set(getPath(), paramString);
/* 37 */     paramEditionInventory.registerTemplateEdition();
/* 38 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Lore Format successfully changed to " + paramString + ".");
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\LoreFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */