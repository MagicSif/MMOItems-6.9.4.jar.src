/*     */ package net.Indyuce.mmoitems.stat.data;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GemSocketsData
/*     */   implements StatData, Mergeable<GemSocketsData>, RandomStatData<GemSocketsData>
/*     */ {
/*     */   @NotNull
/*  27 */   private final Set<GemstoneData> gems = new HashSet<>();
/*     */   
/*     */   @NotNull
/*     */   private final List<String> emptySlots;
/*     */   
/*     */   public GemSocketsData(@NotNull List<String> paramList) {
/*  33 */     this.emptySlots = paramList;
/*     */   }
/*     */   
/*     */   public GemSocketsData(@NotNull JsonArray paramJsonArray) {
/*  37 */     this.emptySlots = new ArrayList<>();
/*     */     
/*  39 */     paramJsonArray.forEach(paramJsonElement -> this.emptySlots.add(paramJsonElement.getAsString()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canReceive(@NotNull String paramString) {
/*  49 */     return (getEmptySocket(paramString) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getEmptySocket(@NotNull String paramString) {
/*  59 */     for (String str : this.emptySlots) {
/*  60 */       if (paramString.equals("") || str.equals(getUncoloredGemSlot()) || paramString.equals(str))
/*  61 */         return str; 
/*  62 */     }  return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static String getUncoloredGemSlot() {
/*  67 */     String str = MMOItems.plugin.getConfig().getString("gem-sockets.uncolored");
/*  68 */     return (str == null) ? "Uncolored" : str;
/*     */   }
/*     */   
/*     */   public void add(GemstoneData paramGemstoneData) {
/*  72 */     this.gems.add(paramGemstoneData);
/*     */   }
/*     */   
/*     */   public void apply(String paramString, GemstoneData paramGemstoneData) {
/*  76 */     this.emptySlots.remove(getEmptySocket(paramString));
/*  77 */     this.gems.add(paramGemstoneData);
/*     */   }
/*     */   
/*     */   public void addEmptySlot(@NotNull String paramString) {
/*  81 */     this.emptySlots.add(paramString);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public List<String> getEmptySlots() {
/*  86 */     return this.emptySlots;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Set<GemstoneData> getGemstones() {
/*  91 */     return this.gems;
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
/*     */   public boolean removeGem(@NotNull UUID paramUUID, @Nullable String paramString) {
/* 103 */     for (GemstoneData gemstoneData : getGemstones()) {
/* 104 */       if (gemstoneData.getHistoricUUID().equals(paramUUID)) {
/* 105 */         if (paramString != null)
/* 106 */           addEmptySlot(paramString); 
/* 107 */         this.gems.remove(gemstoneData);
/* 108 */         return true;
/*     */       } 
/*     */     } 
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   public JsonObject toJson() {
/* 115 */     JsonObject jsonObject = new JsonObject();
/*     */     
/* 117 */     JsonArray jsonArray1 = new JsonArray();
/* 118 */     Objects.requireNonNull(jsonArray1); getEmptySlots().forEach(jsonArray1::add);
/* 119 */     jsonObject.add("EmptySlots", (JsonElement)jsonArray1);
/*     */     
/* 121 */     JsonArray jsonArray2 = new JsonArray();
/* 122 */     this.gems.forEach(paramGemstoneData -> paramJsonArray.add((JsonElement)paramGemstoneData.toJson()));
/* 123 */     jsonObject.add("Gemstones", (JsonElement)jsonArray2);
/*     */     
/* 125 */     return jsonObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void merge(GemSocketsData paramGemSocketsData) {
/* 132 */     this.emptySlots.addAll(paramGemSocketsData.emptySlots);
/* 133 */     this.gems.addAll(paramGemSocketsData.gems);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GemSocketsData cloneData() {
/* 140 */     GemSocketsData gemSocketsData = new GemSocketsData(new ArrayList<>(this.emptySlots));
/*     */ 
/*     */     
/* 143 */     for (GemstoneData gemstoneData : getGemstones()) {
/* 144 */       gemSocketsData.add(gemstoneData.cloneGem());
/*     */     }
/* 146 */     return gemSocketsData;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 151 */     return (this.gems.isEmpty() && this.emptySlots.isEmpty());
/*     */   }
/*     */ 
/*     */   
/*     */   public GemSocketsData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 156 */     return new GemSocketsData(new ArrayList<>(this.emptySlots));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 161 */     return "Empty:§b " + getEmptySlots().size() + "§7, Gems:§b " + getGemstones().size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 166 */     if (!(paramObject instanceof GemSocketsData)) {
/* 167 */       return false;
/*     */     }
/* 169 */     if (((GemSocketsData)paramObject).getEmptySlots().size() != getEmptySlots().size()) {
/* 170 */       return false;
/*     */     }
/* 172 */     if (((GemSocketsData)paramObject).getGemstones().size() != getGemstones().size()) {
/* 173 */       return false;
/*     */     }
/* 175 */     if (!SilentNumbers.hasAll(((GemSocketsData)paramObject).getEmptySlots(), getEmptySlots())) {
/* 176 */       return false;
/*     */     }
/*     */     
/* 179 */     for (GemstoneData gemstoneData : ((GemSocketsData)paramObject).getGemstones()) {
/*     */       
/* 181 */       if (gemstoneData == null) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 186 */       boolean bool = true;
/* 187 */       for (GemstoneData gemstoneData1 : getGemstones()) {
/*     */ 
/*     */         
/* 190 */         if (gemstoneData.equals(gemstoneData1)) {
/* 191 */           bool = false;
/*     */           break;
/*     */         } 
/*     */       } 
/* 195 */       if (bool) {
/* 196 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 201 */     return true;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\GemSocketsData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */