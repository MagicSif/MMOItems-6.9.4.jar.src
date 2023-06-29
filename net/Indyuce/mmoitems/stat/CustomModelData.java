/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*    */ import java.util.ArrayList;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import org.bukkit.Material;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class CustomModelData extends DoubleStat implements GemStoneStat {
/*    */   public CustomModelData() {
/* 21 */     super("CUSTOM_MODEL_DATA", Material.PAINTING, "Custom Model Data", new String[] { "Your 1.14+ model data." }, new String[] { "!block", "all" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/* 28 */     paramItemStackBuilder.getMeta().setCustomModelData(Integer.valueOf((int)paramDoubleData.getValue()));
/*    */ 
/*    */     
/* 31 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramDoubleData));
/*    */   }
/*    */   
/*    */   public void whenPreviewed(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData, @NotNull NumericStatFormula paramNumericStatFormula) {
/* 35 */     whenApplied(paramItemStackBuilder, paramDoubleData);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ArrayList<ItemTag> getAppliedNBT(@NotNull DoubleData paramDoubleData) {
/* 41 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*    */ 
/*    */     
/* 44 */     arrayList.add(new ItemTag(getNBTPath(), Integer.valueOf((int)paramDoubleData.getValue())));
/*    */ 
/*    */     
/* 47 */     return arrayList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 54 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 55 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 56 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.INTEGER));
/*    */     }
/*    */     
/* 59 */     DoubleData doubleData = getLoadedNBT(arrayList);
/*    */ 
/*    */     
/* 62 */     if (doubleData != null) paramReadMMOItem.setData((ItemStat)this, (StatData)doubleData);
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public DoubleData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 70 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*    */ 
/*    */     
/* 73 */     if (itemTag != null)
/*    */     {
/*    */       
/* 76 */       return new DoubleData(((Integer)itemTag.getValue()).intValue());
/*    */     }
/*    */     
/* 79 */     return null;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\CustomModelData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */