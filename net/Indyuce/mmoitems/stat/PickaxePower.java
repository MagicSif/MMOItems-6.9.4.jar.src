/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Material;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PickaxePower
/*    */   extends DoubleStat {
/*    */   public PickaxePower() {
/* 19 */     super("PICKAXE_POWER", Material.IRON_PICKAXE, "Pickaxe Power", new String[] { "The breaking strength of the", "item when mining custom blocks." }, new String[] { "tool" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/* 25 */     int i = (int)paramDoubleData.getValue();
/*    */     
/* 27 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag("MMOITEMS_PICKAXE_POWER", Integer.valueOf(i)) });
/* 28 */     paramItemStackBuilder.getLore().insert("pickaxe-power", new String[] { formatNumericStat(i, new String[] { "{value}", String.valueOf(i) }) });
/*    */   }
/*    */   
/*    */   public void whenPreviewed(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData, @NotNull NumericStatFormula paramNumericStatFormula) {
/* 32 */     Validate.isTrue(paramDoubleData instanceof DoubleData, "Current Data is not Double Data");
/* 33 */     Validate.isTrue(paramNumericStatFormula instanceof NumericStatFormula, "Template Data is not Numeric Stat Formula");
/*    */ 
/*    */     
/* 36 */     double d1 = paramNumericStatFormula.calculate(0.0D, -2.5D);
/* 37 */     double d2 = paramNumericStatFormula.calculate(0.0D, 2.5D);
/*    */ 
/*    */     
/* 40 */     if (d2 < 0.0D && !handleNegativeStats()) {
/*    */       return;
/*    */     }
/* 43 */     if (d1 < 0.0D && !handleNegativeStats()) {
/* 44 */       d1 = 0.0D;
/*    */     }
/* 46 */     if (d1 < paramNumericStatFormula.getBase() - paramNumericStatFormula.getMaxSpread()) {
/* 47 */       d1 = paramNumericStatFormula.getBase() - paramNumericStatFormula.getMaxSpread();
/*    */     }
/* 49 */     if (d2 > paramNumericStatFormula.getBase() + paramNumericStatFormula.getMaxSpread()) {
/* 50 */       d2 = paramNumericStatFormula.getBase() + paramNumericStatFormula.getMaxSpread();
/*    */     }
/*    */ 
/*    */     
/* 54 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag("MMOITEMS_PICKAXE_POWER", Double.valueOf(paramDoubleData.getValue())) });
/*    */ 
/*    */     
/* 57 */     if (d1 != 0.0D || d2 != 0.0D) {
/*    */       String str;
/*    */       
/* 60 */       if (SilentNumbers.round(d1, 2) == SilentNumbers.round(d2, 2)) { str = (MythicLib.plugin.getMMOConfig()).decimals.format(d1); }
/* 61 */       else { str = (MythicLib.plugin.getMMOConfig()).decimals.format(d1) + "-" + (MythicLib.plugin.getMMOConfig()).decimals.format(d2); }
/*    */ 
/*    */       
/* 64 */       paramItemStackBuilder.getLore().insert("pickaxe-power", new String[] { formatNumericStat(d1, new String[] { "{value}", str }) });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\PickaxePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */