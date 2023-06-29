/*     */ package net.Indyuce.mmoitems.api;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.stat.data.AbilityData;
/*     */ import net.Indyuce.mmoitems.stat.data.ParticleData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class ItemSet {
/*  18 */   private final Map<Integer, SetBonuses> bonuses = new HashMap<>();
/*     */ 
/*     */   
/*     */   private final List<String> loreTag;
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private final String id;
/*     */   
/*     */   private static final int itemLimit = 10;
/*     */ 
/*     */   
/*     */   public ItemSet(ConfigurationSection paramConfigurationSection) {
/*  31 */     this.id = paramConfigurationSection.getName().toUpperCase().replace("-", "_");
/*  32 */     this.loreTag = paramConfigurationSection.getStringList("lore-tag");
/*  33 */     this.name = paramConfigurationSection.getString("name");
/*     */     
/*  35 */     Validate.isTrue(paramConfigurationSection.isConfigurationSection("bonuses"), "Could not find item set bonuses");
/*     */     
/*  37 */     for (byte b = 2; b <= 10; b++) {
/*  38 */       if (paramConfigurationSection.getConfigurationSection("bonuses").contains(String.valueOf(b))) {
/*  39 */         String str = "bonuses.%d".formatted(new Object[] { Integer.valueOf(b) });
/*  40 */         SetBonuses setBonuses = new SetBonuses();
/*  41 */         ConfigurationSection configurationSection = paramConfigurationSection.getConfigurationSection(str);
/*  42 */         Validate.notNull(configurationSection, "Item set '%s' is not a valid configuration section.".formatted(new Object[] { this.id }));
/*     */ 
/*     */         
/*  45 */         for (String str1 : configurationSection.getStringList("granted-permissions")) {
/*  46 */           setBonuses.addPermission(str1);
/*     */         }
/*  48 */         for (String str1 : configurationSection.getKeys(false)) {
/*  49 */           if (!str1.equals("granted-permissions")) {
/*     */             try {
/*  51 */               String str2 = str1.toUpperCase().replace("-", "_").replace(" ", "_");
/*     */ 
/*     */               
/*  54 */               if (str1.startsWith("ability-")) {
/*  55 */                 ConfigurationSection configurationSection1 = paramConfigurationSection.getConfigurationSection("%s.%s".formatted(new Object[] { str, str1 }));
/*  56 */                 Validate.notNull(configurationSection1, "Ability '%s' is not a valid configuration section.".formatted(new Object[] { str1 }));
/*  57 */                 setBonuses.addAbility(new AbilityData(configurationSection1));
/*     */                 
/*     */                 continue;
/*     */               } 
/*     */               
/*  62 */               if (str1.startsWith("potion-")) {
/*  63 */                 PotionEffectType potionEffectType = PotionEffectType.getByName(str2.substring("potion-".length()));
/*  64 */                 Validate.notNull(potionEffectType, "Could not load potion effect type from '%s'".formatted(new Object[] { str2 }));
/*  65 */                 setBonuses.addPotionEffect(new PotionEffect(potionEffectType, MMOUtils.getEffectDuration(potionEffectType), paramConfigurationSection
/*  66 */                       .getInt("%s.%s".formatted(new Object[] { str, str1 }, )) - 1, true, false));
/*     */                 
/*     */                 continue;
/*     */               } 
/*     */               
/*  71 */               if (str1.startsWith("particle-")) {
/*  72 */                 ConfigurationSection configurationSection1 = paramConfigurationSection.getConfigurationSection("bonuses.%d.%s".formatted(new Object[] { Integer.valueOf(b), str1 }));
/*  73 */                 Validate.notNull(configurationSection1, "Particle effect '%s' is not a valid configuration section.".formatted(new Object[] { str1 }));
/*  74 */                 setBonuses.addParticle(new ParticleData(configurationSection1));
/*     */                 
/*     */                 continue;
/*     */               } 
/*     */               
/*  79 */               ItemStat<?, ?> itemStat = MMOItems.plugin.getStats().get(str2);
/*  80 */               Validate.notNull(itemStat, "Could not find stat called '%s'".formatted(new Object[] { str2 }));
/*  81 */               setBonuses.addStat(itemStat, paramConfigurationSection.getDouble("bonuses.%d.%s".formatted(new Object[] { Integer.valueOf(b), str1 })));
/*  82 */             } catch (IllegalArgumentException illegalArgumentException) {
/*  83 */               throw new IllegalArgumentException("Could not load set bonus '%s': %s".formatted(new Object[] { str1, illegalArgumentException.getMessage() }));
/*     */             } 
/*     */           }
/*     */         } 
/*  87 */         this.bonuses.put(Integer.valueOf(b), setBonuses);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public String getName() {
/*  92 */     return this.name;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  96 */     return this.id;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public SetBonuses getBonuses(int paramInt) {
/* 101 */     SetBonuses setBonuses = new SetBonuses();
/* 102 */     for (byte b = 2; b <= Math.min(paramInt, 10); b++) {
/* 103 */       SetBonuses setBonuses1 = this.bonuses.get(Integer.valueOf(b));
/* 104 */       if (setBonuses1 != null)
/* 105 */         setBonuses.merge(setBonuses1); 
/*     */     } 
/* 107 */     return setBonuses;
/*     */   }
/*     */   
/*     */   public List<String> getLoreTag() {
/* 111 */     return this.loreTag;
/*     */   }
/*     */   
/*     */   public static class SetBonuses {
/* 115 */     private final Map<ItemStat<?, ?>, Double> stats = new HashMap<>();
/* 116 */     private final Map<PotionEffectType, PotionEffect> permEffects = new HashMap<>();
/* 117 */     private final List<AbilityData> abilities = new ArrayList<>();
/* 118 */     private final List<ParticleData> particles = new ArrayList<>();
/* 119 */     private final ArrayList<String> permissions = new ArrayList<>();
/*     */     
/*     */     public void addStat(ItemStat<?, ?> param1ItemStat, double param1Double) {
/* 122 */       this.stats.put(param1ItemStat, Double.valueOf(param1Double));
/*     */     }
/*     */     
/*     */     public void addPotionEffect(PotionEffect param1PotionEffect) {
/* 126 */       this.permEffects.put(param1PotionEffect.getType(), param1PotionEffect);
/*     */     }
/*     */     
/*     */     public void addAbility(AbilityData param1AbilityData) {
/* 130 */       this.abilities.add(param1AbilityData);
/*     */     }
/*     */     
/*     */     public void addParticle(ParticleData param1ParticleData) {
/* 134 */       this.particles.add(param1ParticleData);
/*     */     }
/*     */     
/*     */     public void addPermission(@NotNull String param1String) {
/* 138 */       this.permissions.add(param1String);
/*     */     }
/*     */     
/*     */     public boolean hasStat(ItemStat<?, ?> param1ItemStat) {
/* 142 */       return this.stats.containsKey(param1ItemStat);
/*     */     }
/*     */     
/*     */     public double getStat(ItemStat<?, ?> param1ItemStat) {
/* 146 */       return ((Double)this.stats.get(param1ItemStat)).doubleValue();
/*     */     }
/*     */     
/*     */     public Map<ItemStat<?, ?>, Double> getStats() {
/* 150 */       return this.stats;
/*     */     }
/*     */     
/*     */     public Collection<PotionEffect> getPotionEffects() {
/* 154 */       return this.permEffects.values();
/*     */     }
/*     */     
/*     */     public List<ParticleData> getParticles() {
/* 158 */       return this.particles;
/*     */     }
/*     */     
/*     */     public List<AbilityData> getAbilities() {
/* 162 */       return this.abilities;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public ArrayList<String> getPermissions() {
/* 167 */       return this.permissions;
/*     */     }
/*     */     
/*     */     public void merge(SetBonuses param1SetBonuses) {
/* 171 */       param1SetBonuses.getStats().forEach((param1ItemStat, param1Double) -> this.stats.put(param1ItemStat, Double.valueOf((this.stats.containsKey(param1ItemStat) ? ((Double)this.stats.get(param1ItemStat)).doubleValue() : 0.0D) + param1Double.doubleValue())));
/*     */       
/* 173 */       for (PotionEffect potionEffect : param1SetBonuses.getPotionEffects()) {
/* 174 */         if (!this.permEffects.containsKey(potionEffect.getType()) || ((PotionEffect)this.permEffects.get(potionEffect.getType())).getAmplifier() < potionEffect.getAmplifier())
/* 175 */           this.permEffects.put(potionEffect.getType(), potionEffect); 
/*     */       } 
/* 177 */       this.abilities.addAll(param1SetBonuses.abilities);
/* 178 */       this.particles.addAll(param1SetBonuses.particles);
/* 179 */       this.permissions.addAll(param1SetBonuses.permissions);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 185 */     if (this == paramObject) return true; 
/* 186 */     if (paramObject == null || getClass() != paramObject.getClass()) return false; 
/* 187 */     ItemSet itemSet = (ItemSet)paramObject;
/* 188 */     return this.id.equals(itemSet.id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 193 */     return Objects.hash(new Object[] { this.id });
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ItemSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */