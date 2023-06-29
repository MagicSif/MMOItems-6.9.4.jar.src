/*     */ package net.Indyuce.mmoitems.stat.data;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.particle.api.ParticleRunnable;
/*     */ import net.Indyuce.mmoitems.particle.api.ParticleType;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class ParticleData
/*     */   implements StatData, RandomStatData<ParticleData> {
/*     */   private final ParticleType type;
/*     */   private final Particle particle;
/*  27 */   private final Map<String, Double> modifiers = new HashMap<>();
/*     */   private final Color color;
/*     */   
/*     */   public ParticleData(JsonObject paramJsonObject) {
/*  31 */     this.particle = Particle.valueOf(paramJsonObject.get("Particle").getAsString());
/*  32 */     this.type = ParticleType.valueOf(paramJsonObject.get("Type").getAsString());
/*     */     
/*  34 */     if (paramJsonObject.has("Color")) {
/*  35 */       JsonObject jsonObject = paramJsonObject.getAsJsonObject("Color");
/*  36 */       this.color = Color.fromRGB(jsonObject.get("Red").getAsInt(), jsonObject.get("Green").getAsInt(), jsonObject.get("Blue").getAsInt());
/*     */     } else {
/*  38 */       this.color = Color.fromRGB(255, 0, 0);
/*     */     } 
/*  40 */     paramJsonObject.getAsJsonObject("Modifiers").entrySet().forEach(paramEntry -> setModifier((String)paramEntry.getKey(), ((JsonElement)paramEntry.getValue()).getAsDouble()));
/*     */   }
/*     */   
/*     */   public ParticleData(ConfigurationSection paramConfigurationSection) {
/*  44 */     Validate.isTrue((paramConfigurationSection.contains("type") && paramConfigurationSection.contains("particle")), "Particle is missing type or selected particle.");
/*     */     
/*  46 */     String str = paramConfigurationSection.getString("type").toUpperCase().replace("-", "_").replace(" ", "_");
/*  47 */     this.type = ParticleType.valueOf(str);
/*     */     
/*  49 */     str = paramConfigurationSection.getString("particle").toUpperCase().replace("-", "_").replace(" ", "_");
/*  50 */     this.particle = Particle.valueOf(str);
/*     */     
/*  52 */     this
/*  53 */       .color = paramConfigurationSection.contains("color") ? Color.fromRGB(paramConfigurationSection.getInt("color.red"), paramConfigurationSection.getInt("color.green"), paramConfigurationSection.getInt("color.blue")) : Color.fromRGB(255, 0, 0);
/*     */     
/*  55 */     for (String str1 : paramConfigurationSection.getKeys(false)) {
/*  56 */       if (!str1.equalsIgnoreCase("particle") && !str1.equalsIgnoreCase("type") && !str1.equalsIgnoreCase("color"))
/*  57 */         setModifier(str1, paramConfigurationSection.getDouble(str1)); 
/*     */     } 
/*     */   }
/*     */   public ParticleData(ParticleType paramParticleType, Particle paramParticle) {
/*  61 */     this.type = paramParticleType;
/*  62 */     this.particle = paramParticle;
/*  63 */     this.color = Color.fromRGB(255, 0, 0);
/*     */   }
/*     */   
/*     */   public ParticleType getType() {
/*  67 */     return this.type;
/*     */   }
/*     */   
/*     */   public Particle getParticle() {
/*  71 */     return this.particle;
/*     */   }
/*     */   
/*     */   public Color getColor() {
/*  75 */     return this.color;
/*     */   }
/*     */   
/*     */   public double getModifier(String paramString) {
/*  79 */     return this.modifiers.containsKey(paramString) ? ((Double)this.modifiers.get(paramString)).doubleValue() : this.type.getModifier(paramString);
/*     */   }
/*     */   
/*     */   public Set<String> getModifiers() {
/*  83 */     return this.modifiers.keySet();
/*     */   }
/*     */   
/*     */   public void setModifier(String paramString, double paramDouble) {
/*  87 */     this.modifiers.put(paramString, Double.valueOf(paramDouble));
/*     */   }
/*     */ 
/*     */   
/*     */   public void display(Location paramLocation, float paramFloat) {
/*  92 */     display(paramLocation, 1, 0.0F, 0.0F, 0.0F, paramFloat);
/*     */   }
/*     */   
/*     */   public void display(Location paramLocation, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/*  96 */     if (this.particle == Particle.REDSTONE) {
/*  97 */       paramLocation.getWorld().spawnParticle(this.particle, paramLocation, paramInt, paramFloat1, paramFloat2, paramFloat3, new Particle.DustOptions(this.color, 1.0F));
/*     */     }
/*  99 */     else if (this.particle == Particle.SPELL_MOB || this.particle == Particle.SPELL_MOB_AMBIENT) {
/*     */       
/* 101 */       for (byte b = 0; b < paramInt; b++) {
/* 102 */         paramLocation.getWorld().spawnParticle(this.particle, paramLocation, 0, (this.color.getRed() / 255.0F), (this.color.getGreen() / 255.0F), (this.color.getBlue() / 255.0F), 1.0D);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 108 */       paramLocation.getWorld().spawnParticle(this.particle, paramLocation, paramInt, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParticleRunnable start(PlayerData paramPlayerData) {
/* 113 */     ParticleRunnable particleRunnable = this.type.newRunnable(this, paramPlayerData);
/* 114 */     particleRunnable.runTaskTimer((Plugin)MMOItems.plugin, 0L, this.type.getTime());
/* 115 */     return particleRunnable;
/*     */   }
/*     */   
/*     */   public JsonObject toJson() {
/* 119 */     JsonObject jsonObject1 = new JsonObject();
/* 120 */     jsonObject1.addProperty("Particle", getParticle().name());
/* 121 */     jsonObject1.addProperty("Type", getType().name());
/*     */     
/* 123 */     if (MMOUtils.isColorable(this.particle)) {
/* 124 */       JsonObject jsonObject = new JsonObject();
/* 125 */       jsonObject.addProperty("Red", Integer.valueOf(getColor().getRed()));
/* 126 */       jsonObject.addProperty("Green", Integer.valueOf(getColor().getGreen()));
/* 127 */       jsonObject.addProperty("Blue", Integer.valueOf(getColor().getBlue()));
/* 128 */       jsonObject1.add("Color", (JsonElement)jsonObject);
/*     */     } 
/*     */     
/* 131 */     JsonObject jsonObject2 = new JsonObject();
/* 132 */     getModifiers().forEach(paramString -> paramJsonObject.addProperty(paramString, Double.valueOf(getModifier(paramString))));
/* 133 */     jsonObject1.add("Modifiers", (JsonElement)jsonObject2);
/* 134 */     return jsonObject1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 139 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ParticleData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 144 */     return this;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\ParticleData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */