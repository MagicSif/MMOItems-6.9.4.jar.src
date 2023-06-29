/*    */ package net.Indyuce.mmoitems.stat.data.random;
/*    */ 
/*    */ import io.lumine.mythic.lib.element.Element;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.data.ElementListData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import net.Indyuce.mmoitems.util.ElementStatType;
/*    */ import net.Indyuce.mmoitems.util.Pair;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class RandomElementListData
/*    */   implements RandomStatData<ElementListData>, UpdatableRandomStatData<ElementListData> {
/* 21 */   private final Map<Pair<Element, ElementStatType>, NumericStatFormula> stats = new LinkedHashMap<>();
/*    */   
/*    */   public RandomElementListData(ConfigurationSection paramConfigurationSection) {
/* 24 */     Validate.notNull(paramConfigurationSection, "Config cannot be null");
/*    */     
/* 26 */     for (Element element : Element.values()) {
/* 27 */       for (ElementStatType elementStatType : ElementStatType.values()) {
/* 28 */         String str = elementStatType.getConcatenatedConfigPath(element);
/* 29 */         if (paramConfigurationSection.contains(str))
/* 30 */           this.stats.put(Pair.of(element, elementStatType), new NumericStatFormula(paramConfigurationSection.get(str))); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   public boolean hasStat(Element paramElement, ElementStatType paramElementStatType) {
/* 35 */     return this.stats.containsKey(Pair.of(paramElement, paramElementStatType));
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public NumericStatFormula getStat(Element paramElement, ElementStatType paramElementStatType) {
/* 40 */     return this.stats.getOrDefault(Pair.of(paramElement, paramElementStatType), NumericStatFormula.ZERO);
/*    */   }
/*    */   
/*    */   public Set<Pair<Element, ElementStatType>> getKeys() {
/* 44 */     return this.stats.keySet();
/*    */   }
/*    */   
/*    */   public void setStat(Element paramElement, ElementStatType paramElementStatType, NumericStatFormula paramNumericStatFormula) {
/* 48 */     this.stats.put(Pair.of(paramElement, paramElementStatType), paramNumericStatFormula);
/*    */   }
/*    */ 
/*    */   
/*    */   public ElementListData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 53 */     ElementListData elementListData = new ElementListData();
/* 54 */     this.stats.forEach((paramPair, paramNumericStatFormula) -> paramElementListData.setStat((Element)paramPair.getKey(), (ElementStatType)paramPair.getValue(), paramNumericStatFormula.calculate(paramMMOItemBuilder.getLevel())));
/* 55 */     return elementListData;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ElementListData reroll(@NotNull ItemStat paramItemStat, @NotNull ElementListData paramElementListData, int paramInt) {
/* 64 */     ElementListData elementListData1 = new ElementListData();
/* 65 */     ElementListData elementListData2 = paramElementListData;
/*    */ 
/*    */     
/* 68 */     for (Element element : Element.values()) {
/* 69 */       for (ElementStatType elementStatType : ElementStatType.values()) {
/*    */ 
/*    */         
/* 72 */         NumericStatFormula numericStatFormula = getStat(element, elementStatType);
/* 73 */         DoubleData doubleData1 = new DoubleData(elementListData2.getStat(element, elementStatType));
/*    */ 
/*    */         
/* 76 */         DoubleData doubleData2 = numericStatFormula.reroll(paramItemStat, doubleData1, paramInt);
/*    */ 
/*    */         
/* 79 */         elementListData1.setStat(element, elementStatType, doubleData2.getValue());
/*    */       } 
/*    */     } 
/*    */     
/* 83 */     return elementListData1;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\random\RandomElementListData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */