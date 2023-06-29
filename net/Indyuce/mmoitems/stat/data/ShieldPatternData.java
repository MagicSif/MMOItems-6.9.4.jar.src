/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.block.banner.Pattern;
/*    */ 
/*    */ public class ShieldPatternData
/*    */   implements StatData, RandomStatData<ShieldPatternData>
/*    */ {
/*    */   private final DyeColor base;
/* 16 */   private final List<Pattern> patterns = new ArrayList<>();
/*    */   
/*    */   public ShieldPatternData(DyeColor paramDyeColor, Pattern... paramVarArgs) {
/* 19 */     this.base = paramDyeColor;
/* 20 */     this.patterns.addAll(Arrays.asList(paramVarArgs));
/*    */   }
/*    */   
/*    */   public DyeColor getBaseColor() {
/* 24 */     return this.base;
/*    */   }
/*    */   
/*    */   public List<Pattern> getPatterns() {
/* 28 */     return this.patterns;
/*    */   }
/*    */   
/*    */   public void add(Pattern paramPattern) {
/* 32 */     this.patterns.add(paramPattern);
/*    */   }
/*    */   
/*    */   public void addAll(List<Pattern> paramList) {
/* 36 */     this.patterns.addAll(paramList);
/*    */   }
/*    */ 
/*    */   
/*    */   public ShieldPatternData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 41 */     return this;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\ShieldPatternData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */