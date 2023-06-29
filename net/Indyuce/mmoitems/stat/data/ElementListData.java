/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import io.lumine.mythic.lib.element.Element;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.Set;
/*    */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.util.ElementStatType;
/*    */ import net.Indyuce.mmoitems.util.Pair;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ElementListData
/*    */   implements Mergeable {
/* 17 */   private final Map<Pair<Element, ElementStatType>, Double> stats = new LinkedHashMap<>();
/*    */   
/*    */   public double getStat(Element paramElement, ElementStatType paramElementStatType) {
/* 20 */     Double double_ = this.stats.get(Pair.of(paramElement, paramElementStatType));
/* 21 */     return (double_ == null) ? 0.0D : double_.doubleValue();
/*    */   }
/*    */   
/*    */   public Set<Pair<Element, ElementStatType>> getKeys() {
/* 25 */     return this.stats.keySet();
/*    */   }
/*    */   
/*    */   public void setStat(Element paramElement, ElementStatType paramElementStatType, double paramDouble) {
/* 29 */     this.stats.put(Pair.of(paramElement, paramElementStatType), Double.valueOf(paramDouble));
/*    */   }
/*    */ 
/*    */   
/*    */   public void merge(StatData paramStatData) {
/* 34 */     Validate.isTrue(paramStatData instanceof ElementListData, "Cannot merge two different stat data types");
/* 35 */     ElementListData elementListData = (ElementListData)paramStatData;
/*    */     
/* 37 */     elementListData.stats.forEach((paramPair, paramDouble) -> this.stats.put(paramPair, Double.valueOf(paramDouble.doubleValue() + ((Double)this.stats.getOrDefault(paramPair, Double.valueOf(0.0D))).doubleValue())));
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public StatData cloneData() {
/* 43 */     ElementListData elementListData = new ElementListData();
/* 44 */     for (Map.Entry<Pair<Element, ElementStatType>, Double> entry : this.stats.entrySet()) {
/* 45 */       Pair<Element, ElementStatType> pair = (Pair)entry.getKey();
/* 46 */       Double double_ = (Double)entry.getValue();
/* 47 */       if (double_.doubleValue() != 0.0D) {
/* 48 */         elementListData.stats.put(pair, double_);
/*    */       }
/*    */     } 
/* 51 */     return (StatData)elementListData;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 56 */     return this.stats.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 61 */     if (this == paramObject) return true; 
/* 62 */     if (paramObject == null || getClass() != paramObject.getClass()) return false; 
/* 63 */     ElementListData elementListData = (ElementListData)paramObject;
/* 64 */     return this.stats.equals(elementListData.stats);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 69 */     return Objects.hash(new Object[] { this.stats });
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\ElementListData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */