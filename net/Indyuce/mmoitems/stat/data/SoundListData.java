/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.Set;
/*    */ import javax.annotation.Nullable;
/*    */ import net.Indyuce.mmoitems.api.CustomSound;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SoundListData implements StatData, Mergeable, RandomStatData<SoundListData> {
/*    */   private final Map<CustomSound, SoundData> sounds;
/*    */   
/*    */   public SoundListData() {
/* 20 */     this(new HashMap<>());
/*    */   }
/*    */   
/*    */   public SoundListData(Map<CustomSound, SoundData> paramMap) {
/* 24 */     this.sounds = paramMap;
/*    */   }
/*    */   
/*    */   public Set<CustomSound> getCustomSounds() {
/* 28 */     return this.sounds.keySet();
/*    */   }
/*    */   
/*    */   public Map<CustomSound, SoundData> mapData() {
/* 32 */     return this.sounds;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public SoundData get(CustomSound paramCustomSound) {
/* 40 */     return this.sounds.getOrDefault(paramCustomSound, null);
/*    */   }
/*    */   
/*    */   public void set(CustomSound paramCustomSound, SoundData paramSoundData) {
/* 44 */     this.sounds.put(paramCustomSound, paramSoundData);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 49 */     if (!(paramObject instanceof SoundListData)) return false; 
/* 50 */     if (((SoundListData)paramObject).getCustomSounds().size() != getCustomSounds().size()) return false;
/*    */     
/* 52 */     for (CustomSound customSound : ((SoundListData)paramObject).getCustomSounds()) {
/*    */       
/* 54 */       if (customSound == null)
/*    */         continue; 
/* 56 */       boolean bool = true;
/* 57 */       for (CustomSound customSound1 : getCustomSounds()) {
/*    */         
/* 59 */         if (customSound.equals(customSound1)) {
/* 60 */           bool = false; break;
/*    */         } 
/* 62 */       }  if (bool) return false;
/*    */     
/*    */     } 
/* 65 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void merge(StatData paramStatData) {
/* 70 */     Validate.isTrue(paramStatData instanceof SoundListData, "Cannot merge two different stat data types");
/* 71 */     SoundListData soundListData = (SoundListData)paramStatData;
/* 72 */     Objects.requireNonNull(this.sounds); soundListData.sounds.forEach(this.sounds::put);
/*    */   }
/*    */   @NotNull
/*    */   public StatData cloneData() {
/* 76 */     return new SoundListData(mapData());
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 80 */     return this.sounds.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public SoundListData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 85 */     return new SoundListData(new HashMap<>(this.sounds));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\SoundListData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */