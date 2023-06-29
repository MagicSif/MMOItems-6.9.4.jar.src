/*     */ package net.Indyuce.mmoitems.stat.data;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class GemstoneData {
/*  17 */   private final Set<AbilityData> abilities = new HashSet<>();
/*  18 */   private final List<PotionEffectData> effects = new ArrayList<>();
/*  19 */   private final Map<ItemStat, Double> stats = new HashMap<>();
/*     */   
/*     */   private final String name;
/*     */   
/*     */   @NotNull
/*     */   private final UUID historicUUID;
/*     */   @Nullable
/*     */   private final String mmoitemType;
/*     */   @Nullable
/*     */   private final String mmoitemID;
/*     */   @Nullable
/*     */   private String socketColor;
/*     */   @Nullable
/*     */   private Integer levelPut;
/*     */   
/*     */   public GemstoneData cloneGem() {
/*  35 */     GemstoneData gemstoneData = new GemstoneData(getName(), getMMOItemType(), getMMOItemID(), getSocketColor(), getHistoricUUID());
/*  36 */     for (AbilityData abilityData : this.abilities)
/*  37 */       gemstoneData.addAbility(abilityData); 
/*  38 */     for (PotionEffectData potionEffectData : this.effects)
/*  39 */       gemstoneData.addPermanentEffect(potionEffectData); 
/*  40 */     for (ItemStat itemStat : this.stats.keySet())
/*  41 */       gemstoneData.setStat(itemStat, ((Double)this.stats.get(itemStat)).doubleValue()); 
/*  42 */     gemstoneData.setLevel(getLevel());
/*     */     
/*  44 */     return gemstoneData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/*  56 */     if (!(paramObject instanceof GemstoneData)) {
/*  57 */       return false;
/*     */     }
/*     */     
/*  60 */     return ((GemstoneData)paramObject).getHistoricUUID().equals(getHistoricUUID());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public String getMMOItemType() {
/*  65 */     return this.mmoitemType;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public String getMMOItemID() {
/*  70 */     return this.mmoitemID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getSocketColor() {
/*  78 */     return this.socketColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GemstoneData(@NotNull JsonObject paramJsonObject) {
/*  88 */     this.name = paramJsonObject.get("Name").getAsString();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     JsonElement jsonElement = paramJsonObject.get("History");
/* 101 */     if (jsonElement != null) {
/*     */ 
/*     */       
/* 104 */       String str = jsonElement.getAsString();
/* 105 */       UUID uUID = MMOUtils.UUIDFromString(str);
/* 106 */       if (uUID != null) {
/* 107 */         this.historicUUID = uUID;
/*     */       } else {
/* 109 */         this.historicUUID = UUID.randomUUID();
/*     */       } 
/*     */ 
/*     */       
/* 113 */       JsonElement jsonElement1 = paramJsonObject.get("Type");
/* 114 */       JsonElement jsonElement2 = paramJsonObject.get("Id");
/* 115 */       if (jsonElement1 != null) {
/* 116 */         this.mmoitemType = jsonElement1.getAsString();
/*     */       } else {
/* 118 */         this.mmoitemType = null;
/*     */       } 
/* 120 */       if (jsonElement2 != null) {
/* 121 */         this.mmoitemID = jsonElement2.getAsString();
/*     */       } else {
/* 123 */         this.mmoitemID = null;
/*     */       } 
/*     */       
/* 126 */       JsonElement jsonElement3 = paramJsonObject.get("Level");
/* 127 */       if (jsonElement3 != null && jsonElement3.isJsonPrimitive()) {
/* 128 */         this.levelPut = Integer.valueOf(jsonElement3.getAsJsonPrimitive().getAsInt());
/*     */       } else {
/* 130 */         this.levelPut = null;
/*     */       } 
/*     */ 
/*     */       
/* 134 */       JsonElement jsonElement4 = paramJsonObject.get("Color");
/* 135 */       if (jsonElement4 != null && jsonElement4.isJsonPrimitive()) {
/* 136 */         this.socketColor = jsonElement4.getAsJsonPrimitive().getAsString();
/*     */       } else {
/* 138 */         this.socketColor = null;
/*     */       } 
/*     */     } else {
/*     */       
/* 142 */       this.historicUUID = UUID.randomUUID();
/* 143 */       this.mmoitemID = null;
/* 144 */       this.mmoitemType = null;
/* 145 */       this.socketColor = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GemstoneData(@NotNull LiveMMOItem paramLiveMMOItem, @Nullable String paramString) {
/* 156 */     this(paramLiveMMOItem, paramString, UUID.randomUUID());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GemstoneData(@NotNull LiveMMOItem paramLiveMMOItem, @Nullable String paramString, @NotNull UUID paramUUID) {
/* 168 */     this.name = MMOUtils.getDisplayName(paramLiveMMOItem.getNBT().getItem());
/*     */ 
/*     */     
/* 171 */     if (paramLiveMMOItem.hasData(ItemStats.ABILITIES)) {
/* 172 */       this.abilities.addAll(((AbilityListData)paramLiveMMOItem.getData(ItemStats.ABILITIES)).getAbilities());
/*     */     }
/*     */ 
/*     */     
/* 176 */     if (paramLiveMMOItem.hasData(ItemStats.PERM_EFFECTS)) {
/* 177 */       this.effects.addAll(((PotionEffectListData)paramLiveMMOItem.getData(ItemStats.PERM_EFFECTS)).getEffects());
/*     */     }
/*     */ 
/*     */     
/* 181 */     this.historicUUID = paramUUID;
/* 182 */     this.mmoitemID = paramLiveMMOItem.getId();
/* 183 */     this.mmoitemType = paramLiveMMOItem.getType().getId();
/* 184 */     this.socketColor = paramString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GemstoneData(@NotNull String paramString) {
/* 197 */     this.name = paramString;
/* 198 */     this.mmoitemID = null;
/* 199 */     this.mmoitemType = null;
/* 200 */     this.socketColor = null;
/* 201 */     this.historicUUID = UUID.randomUUID();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLevel(@Nullable Integer paramInteger) {
/* 212 */     this.levelPut = paramInteger;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Integer getLevel() {
/* 223 */     return this.levelPut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isScaling() {
/* 230 */     return (this.levelPut != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GemstoneData(@NotNull String paramString1, @Nullable String paramString2, @Nullable String paramString3, @Nullable String paramString4) {
/* 242 */     this.name = paramString1;
/* 243 */     this.mmoitemID = paramString3;
/* 244 */     this.mmoitemType = paramString2;
/* 245 */     this.socketColor = paramString4;
/* 246 */     this.historicUUID = UUID.randomUUID();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GemstoneData(@NotNull String paramString1, @Nullable String paramString2, @Nullable String paramString3, @Nullable String paramString4, @NotNull UUID paramUUID) {
/* 258 */     this.name = paramString1;
/* 259 */     this.mmoitemID = paramString3;
/* 260 */     this.mmoitemType = paramString2;
/* 261 */     this.socketColor = paramString4;
/* 262 */     this.historicUUID = paramUUID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAbility(@NotNull AbilityData paramAbilityData) {
/* 269 */     this.abilities.add(paramAbilityData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPermanentEffect(@NotNull PotionEffectData paramPotionEffectData) {
/* 276 */     this.effects.add(paramPotionEffectData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStat(@NotNull ItemStat paramItemStat, double paramDouble) {
/* 283 */     this.stats.put(paramItemStat, Double.valueOf(paramDouble));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/* 291 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColour(@Nullable String paramString) {
/* 298 */     this.socketColor = paramString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public UUID getHistoricUUID() {
/* 306 */     return this.historicUUID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public JsonObject toJson() {
/* 314 */     JsonObject jsonObject = new JsonObject();
/* 315 */     jsonObject.addProperty("Name", this.name);
/* 316 */     jsonObject.addProperty("History", this.historicUUID.toString());
/* 317 */     if (this.mmoitemID != null) {
/* 318 */       jsonObject.addProperty("Id", this.mmoitemID);
/*     */     }
/* 320 */     if (this.mmoitemType != null) {
/* 321 */       jsonObject.addProperty("Type", this.mmoitemType);
/*     */     }
/* 323 */     if (this.levelPut != null)
/*     */     {
/* 325 */       jsonObject.addProperty("Level", this.levelPut);
/*     */     }
/* 327 */     jsonObject.addProperty("Color", this.socketColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 349 */     return jsonObject;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\GemstoneData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */