/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.StringData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.StringStat;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class GemColor extends StringStat implements GemStoneStat {
/*    */   public GemColor() {
/* 14 */     super("GEM_COLOR", VersionMaterial.LIGHT_BLUE_DYE.toMaterial(), "Gem Color", new String[] { "Defines the color of the socket in", "which the gem can be applied." }, new String[] { "gem_stone" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringData paramStringData) {
/* 19 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag(getNBTPath(), paramStringData.toString()) });
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\GemColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */