/*    */ package net.Indyuce.mmoitems.stat.data.random;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*    */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*    */ import net.Indyuce.mmoitems.stat.data.PotionEffectData;
/*    */ import net.Indyuce.mmoitems.stat.data.PotionEffectListData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class RandomPotionEffectListData implements RandomStatData<PotionEffectListData> {
/* 19 */   private final List<RandomPotionEffectData> effects = new ArrayList<>();
/*    */   
/*    */   public RandomPotionEffectListData(ConfigurationSection paramConfigurationSection) {
/* 22 */     Validate.notNull(paramConfigurationSection, "Config cannot be null");
/*    */ 
/*    */     
/* 25 */     for (String str1 : paramConfigurationSection.getKeys(false)) {
/* 26 */       ConfigurationSection configurationSection = paramConfigurationSection.getConfigurationSection(str1);
/*    */ 
/*    */       
/* 29 */       if (configurationSection != null) {
/*    */         
/* 31 */         this.effects.add(new RandomPotionEffectData(configurationSection));
/*    */ 
/*    */         
/*    */         continue;
/*    */       } 
/*    */       
/* 37 */       String str2 = paramConfigurationSection.getString(str1);
/* 38 */       if (str2 != null) {
/* 39 */         String[] arrayOfString = str2.split(",");
/* 40 */         if (arrayOfString.length >= 1) {
/*    */ 
/*    */           
/* 43 */           Double double_ = SilentNumbers.DoubleParse(arrayOfString[0]);
/* 44 */           Integer integer = SilentNumbers.IntegerParse(arrayOfString[1]);
/* 45 */           PotionEffectType potionEffectType = PotionEffectType.getByName(str1.toUpperCase().replace("-", "_").replace(" ", "_"));
/*    */ 
/*    */           
/* 48 */           if (double_ != null && integer != null && potionEffectType != null) {
/*    */ 
/*    */             
/* 51 */             this.effects.add(new RandomPotionEffectData(potionEffectType, new NumericStatFormula(double_), new NumericStatFormula(integer)));
/*    */             
/*    */             continue;
/*    */           } 
/* 55 */           throw new IllegalArgumentException(FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Incorrect format, expected $e{Effect}: {Duration},{Amplifier}$b instead of $i{0} {1}$b.", new String[] { str1, str2 }));
/*    */         } 
/*    */ 
/*    */ 
/*    */         
/* 60 */         throw new IllegalArgumentException(FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Incorrect format, expected $e{Effect}: {Duration},{Amplifier}$b instead of $i{0} {1}$b.", new String[] { str1, str2 }));
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 65 */       throw new IllegalArgumentException("Config cannot be null");
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RandomPotionEffectListData(RandomPotionEffectData... paramVarArgs) {
/* 72 */     add(paramVarArgs);
/*    */   }
/*    */   
/*    */   public void add(RandomPotionEffectData... paramVarArgs) {
/* 76 */     this.effects.addAll(Arrays.asList(paramVarArgs));
/*    */   }
/*    */   
/*    */   public List<RandomPotionEffectData> getEffects() {
/* 80 */     return this.effects;
/*    */   }
/*    */ 
/*    */   
/*    */   public PotionEffectListData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 85 */     PotionEffectListData potionEffectListData = new PotionEffectListData(new PotionEffectData[0]);
/* 86 */     this.effects.forEach(paramRandomPotionEffectData -> paramPotionEffectListData.add(new PotionEffectData[] { paramRandomPotionEffectData.randomize(paramMMOItemBuilder) }));
/* 87 */     return potionEffectListData;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\random\RandomPotionEffectListData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */