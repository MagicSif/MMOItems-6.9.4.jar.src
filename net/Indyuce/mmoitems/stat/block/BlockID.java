/*    */ package net.Indyuce.mmoitems.stat.block;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import org.bukkit.Material;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class BlockID
/*    */   extends DoubleStat {
/*    */   public BlockID() {
/* 16 */     super("BLOCK_ID", Material.STONE, "Block ID", new String[] { "This value determines which", "custom block will get placed." }, new String[] { "block" }, new Material[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/* 21 */     super.whenApplied(paramItemStackBuilder, paramDoubleData);
/* 22 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag("CustomModelData", Integer.valueOf((int)paramDoubleData.getValue() + 1000)) });
/*    */   }
/*    */   
/*    */   public void whenPreviewed(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData, @NotNull NumericStatFormula paramNumericStatFormula) {
/* 26 */     whenApplied(paramItemStackBuilder, paramDoubleData);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\block\BlockID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */