/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ItemTier;
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
/*    */ public class ItemTierStat
/*    */   extends StringStat implements GemStoneStat {
/*    */   public ItemTierStat() {
/* 19 */     super("TIER", Material.DIAMOND, "Item Tier", new String[] { "The tier defines how rare your item is", "and what item is dropped when your", "item is deconstructed.", "&9Tiers can be configured in the tiers.yml file" }, new String[] { "all" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringData paramStringData) {
/* 25 */     String str = paramStringData.toString().toUpperCase().replace("-", "_").replace(" ", "_");
/* 26 */     Validate.isTrue(MMOItems.plugin.getTiers().has(str), "Could not find item tier with ID '" + str + "'");
/*    */     
/* 28 */     ItemTier itemTier = MMOItems.plugin.getTiers().get(str);
/* 29 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag("MMOITEMS_TIER", str) });
/* 30 */     paramItemStackBuilder.getLore().insert("tier", new String[] { MMOItems.plugin.getLanguage().getStatFormat(getPath()).replace("{value}", itemTier.getName()) });
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 35 */     String str = paramString.toUpperCase().replace(" ", "_").replace("-", "_");
/* 36 */     Validate.isTrue(MMOItems.plugin.getTiers().has(str), "Couldn't find the tier called '" + str + "'.");
/*    */     
/* 38 */     paramEditionInventory.getEditedSection().set("tier", str);
/* 39 */     paramEditionInventory.registerTemplateEdition();
/* 40 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Tier successfully changed to " + str + ".");
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\ItemTierStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */