/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SoulboundLevel
/*    */   extends DoubleStat {
/*    */   public SoulboundLevel() {
/* 17 */     super("SOULBOUND_LEVEL", VersionMaterial.ENDER_EYE.toMaterial(), "Soulbinding Level", new String[] { "The soulbound level defines how much", "damage players will take when trying", "to use a soulbound item. It also determines", "how hard it is to break the binding." }, new String[] { "consumable" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/* 23 */     int i = (int)paramDoubleData.getValue();
/* 24 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag("MMOITEMS_SOULBOUND_LEVEL", Integer.valueOf(i)) });
/* 25 */     paramItemStackBuilder.getLore().insert("soulbound-level", new String[] { formatNumericStat(i, new String[] { "{value}", MMOUtils.intToRoman(i) }) });
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenPreviewed(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData, @NotNull NumericStatFormula paramNumericStatFormula) {
/* 30 */     whenApplied(paramItemStackBuilder, paramDoubleData);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\SoulboundLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */