/*     */ package net.Indyuce.mmoitems.stat.data;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.UpgradeTemplate;
/*     */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class UpgradeData
/*     */   implements StatData, RandomStatData<UpgradeData>, Cloneable
/*     */ {
/*     */   @Nullable
/*     */   private final String reference;
/*     */   @Nullable
/*     */   private final String template;
/*     */   private final boolean workbench;
/*     */   private final boolean destroy;
/*     */   
/*     */   @Nullable
/*     */   public String getReference() {
/*  26 */     return this.reference;
/*     */   }
/*     */   private final double success; private final int max; private final int min;
/*     */   private int level;
/*     */   
/*     */   public boolean isWorkbench() {
/*  32 */     return this.workbench;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDestroy() {
/*  39 */     return this.destroy;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMax() {
/*  45 */     return this.max;
/*     */   }
/*     */   
/*     */   public int getMin() {
/*  49 */     return this.min;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UpgradeData(@Nullable String paramString1, @Nullable String paramString2, boolean paramBoolean1, boolean paramBoolean2, int paramInt, double paramDouble) {
/*  69 */     this(paramString1, paramString2, paramBoolean1, paramBoolean2, paramInt, 0, paramDouble);
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
/*     */ 
/*     */   
/*     */   public UpgradeData(@Nullable String paramString1, @Nullable String paramString2, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, double paramDouble) {
/*  84 */     this.reference = paramString1;
/*  85 */     this.template = paramString2;
/*  86 */     this.workbench = paramBoolean1;
/*  87 */     this.destroy = paramBoolean2;
/*  88 */     this.max = paramInt1;
/*  89 */     this.min = paramInt2;
/*  90 */     this.success = paramDouble;
/*     */   }
/*     */   
/*     */   public UpgradeData(ConfigurationSection paramConfigurationSection) {
/*  94 */     this.reference = paramConfigurationSection.getString("reference");
/*  95 */     this.template = paramConfigurationSection.getString("template");
/*  96 */     this.workbench = paramConfigurationSection.getBoolean("workbench");
/*  97 */     this.destroy = paramConfigurationSection.getBoolean("destroy");
/*  98 */     this.max = paramConfigurationSection.getInt("max");
/*  99 */     this.min = paramConfigurationSection.getInt("min", 0);
/* 100 */     this.success = paramConfigurationSection.getDouble("success") / 100.0D;
/*     */   }
/*     */   
/*     */   public UpgradeData(JsonObject paramJsonObject) {
/* 104 */     this.workbench = paramJsonObject.get("Workbench").getAsBoolean();
/* 105 */     this.destroy = paramJsonObject.get("Destroy").getAsBoolean();
/* 106 */     this.template = paramJsonObject.has("Template") ? paramJsonObject.get("Template").getAsString() : null;
/* 107 */     this.reference = paramJsonObject.has("Reference") ? paramJsonObject.get("Reference").getAsString() : null;
/* 108 */     this.level = paramJsonObject.get("Level").getAsInt();
/* 109 */     this.max = paramJsonObject.get("Max").getAsInt();
/* 110 */     this.min = paramJsonObject.has("Min") ? paramJsonObject.get("Min").getAsInt() : 0;
/* 111 */     this.success = paramJsonObject.get("Success").getAsDouble();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UpgradeTemplate getTemplate() {
/* 118 */     if (this.template == null) return null; 
/* 119 */     return MMOItems.plugin.getUpgrades().getTemplate(this.template);
/*     */   } @Nullable
/*     */   public String getTemplateName() {
/* 122 */     return this.template;
/*     */   } public int getLevel() {
/* 124 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLevel(int paramInt) {
/* 132 */     this.level = paramInt;
/*     */   } public int getMaxUpgrades() {
/* 134 */     return this.max;
/*     */   }
/*     */   public boolean canLevelUp() {
/* 137 */     return (this.max == 0 || this.level < this.max);
/*     */   }
/*     */   
/*     */   public boolean destroysOnFail() {
/* 141 */     return this.destroy;
/*     */   }
/*     */   
/*     */   public double getSuccess() {
/* 145 */     return (this.success == 0.0D) ? 1.0D : this.success;
/*     */   }
/*     */   
/*     */   public boolean matchesReference(UpgradeData paramUpgradeData) {
/* 149 */     return (this.reference == null || paramUpgradeData.reference == null || this.reference.isEmpty() || paramUpgradeData.reference.isEmpty() || this.reference.equals(paramUpgradeData.reference));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void upgrade(@NotNull MMOItem paramMMOItem) {
/* 160 */     if (getTemplate() == null) {
/* 161 */       MMOItems.plugin.getLogger().warning("Couldn't find upgrade template '" + this.template + "'. Does it exist?");
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 168 */     getTemplate().upgrade(paramMMOItem);
/*     */   }
/*     */   
/*     */   public JsonObject toJson() {
/* 172 */     JsonObject jsonObject = new JsonObject();
/*     */     
/* 174 */     if (this.reference != null && !this.reference.isEmpty())
/* 175 */       jsonObject.addProperty("Reference", this.reference); 
/* 176 */     if (this.template != null && !this.template.isEmpty())
/* 177 */       jsonObject.addProperty("Template", this.template); 
/* 178 */     jsonObject.addProperty("Workbench", Boolean.valueOf(this.workbench));
/* 179 */     jsonObject.addProperty("Destroy", Boolean.valueOf(this.destroy));
/* 180 */     jsonObject.addProperty("Level", Integer.valueOf(this.level));
/* 181 */     jsonObject.addProperty("Max", Integer.valueOf(this.max));
/* 182 */     jsonObject.addProperty("Min", Integer.valueOf(this.min));
/* 183 */     jsonObject.addProperty("Success", Double.valueOf(this.success));
/*     */     
/* 185 */     return jsonObject;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 190 */     return toJson().toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public UpgradeData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 195 */     return this;
/*     */   }
/*     */   
/*     */   public UpgradeData clone() {
/*     */     
/* 200 */     try { super.clone(); } catch (CloneNotSupportedException cloneNotSupportedException) {}
/*     */     
/* 202 */     return new UpgradeData(this.reference, this.template, this.workbench, this.destroy, this.max, this.min, this.success);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\UpgradeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */