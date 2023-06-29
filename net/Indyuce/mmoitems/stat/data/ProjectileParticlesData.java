/*     */ package net.Indyuce.mmoitems.stat.data;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.gson.JsonObject;
/*     */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class ProjectileParticlesData implements StatData, RandomStatData<ProjectileParticlesData> {
/*     */   private final Particle particle;
/*     */   private final int red;
/*     */   private final int green;
/*     */   private final int blue;
/*     */   private final boolean colored;
/*  20 */   public static final ProjectileParticlesData DEFAULT = new ProjectileParticlesData(Particle.NOTE);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProjectileParticlesData(Particle paramParticle) {
/*  26 */     this.particle = paramParticle;
/*  27 */     this.red = 0;
/*  28 */     this.green = 0;
/*  29 */     this.blue = 0;
/*  30 */     this.colored = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProjectileParticlesData(Particle paramParticle, int paramInt1, int paramInt2, int paramInt3) {
/*  37 */     this.particle = paramParticle;
/*  38 */     this.red = paramInt1;
/*  39 */     this.green = paramInt2;
/*  40 */     this.blue = paramInt3;
/*  41 */     this.colored = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProjectileParticlesData(String paramString) {
/*  48 */     JsonObject jsonObject = (JsonObject)MythicLib.plugin.getGson().fromJson(paramString, JsonObject.class);
/*  49 */     this.particle = Particle.valueOf(jsonObject.get("Particle").getAsString());
/*     */     
/*  51 */     if (isColorable(this.particle)) {
/*  52 */       this.colored = true;
/*  53 */       this.red = jsonObject.get("Red").getAsInt();
/*  54 */       this.green = jsonObject.get("Green").getAsInt();
/*  55 */       this.blue = jsonObject.get("Blue").getAsInt();
/*     */     } else {
/*  57 */       this.colored = false;
/*  58 */       this.red = 0;
/*  59 */       this.green = 0;
/*  60 */       this.blue = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Particle getParticle() {
/*  65 */     return this.particle;
/*     */   }
/*     */   
/*     */   public int getRed() {
/*  69 */     return this.red;
/*     */   }
/*     */   
/*     */   public int getGreen() {
/*  73 */     return this.green;
/*     */   }
/*     */   
/*     */   public int getBlue() {
/*  77 */     return this.blue;
/*     */   }
/*     */   
/*     */   public static boolean isColorable(Particle paramParticle) {
/*  81 */     return (paramParticle == Particle.REDSTONE || paramParticle == Particle.SPELL_MOB || paramParticle == Particle.SPELL_MOB_AMBIENT || paramParticle == Particle.NOTE);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  86 */     JsonObject jsonObject = new JsonObject();
/*  87 */     jsonObject.addProperty("Particle", this.particle.name());
/*  88 */     if (this.colored) {
/*  89 */       jsonObject.addProperty("Red", Integer.valueOf(this.red));
/*  90 */       jsonObject.addProperty("Green", Integer.valueOf(this.green));
/*  91 */       jsonObject.addProperty("Blue", Integer.valueOf(this.blue));
/*     */     } 
/*     */     
/*  94 */     return jsonObject.toString();
/*     */   }
/*     */   
/*     */   public void shootParticle(Location paramLocation) {
/*  98 */     shootParticle(paramLocation, 1, 0.0D);
/*     */   }
/*     */   
/*     */   public void shootParticle(Location paramLocation, int paramInt, double paramDouble) {
/* 102 */     if (isColorable(this.particle)) {
/* 103 */       Particle.DustOptions dustOptions; double d1; switch (this.particle) {
/*     */         
/*     */         case REDSTONE:
/* 106 */           dustOptions = new Particle.DustOptions(Color.fromRGB(this.red, this.green, this.blue), 1.0F);
/* 107 */           paramLocation.getWorld().spawnParticle(Particle.REDSTONE, paramLocation, paramInt, paramDouble, paramDouble, paramDouble, 0.0D, dustOptions);
/*     */           return;
/*     */         
/*     */         case NOTE:
/* 111 */           d1 = this.red / 24.0D;
/* 112 */           paramLocation.getWorld().spawnParticle(Particle.NOTE, paramLocation, 0, d1, 0.0D, 0.0D, 1.0D);
/*     */           return;
/*     */       } 
/*     */       
/* 116 */       double d2 = this.red / 255.0D;
/* 117 */       double d3 = this.green / 255.0D;
/* 118 */       double d4 = this.blue / 255.0D;
/* 119 */       paramLocation.getWorld().spawnParticle(this.particle, paramLocation, 0, d2, d3, d4, 1.0D);
/*     */     
/*     */     }
/* 122 */     else if (this.particle == Particle.ITEM_CRACK) {
/*     */       
/* 124 */       ItemStack itemStack = new ItemStack(Material.STONE);
/* 125 */       paramLocation.getWorld().spawnParticle(this.particle, paramLocation, paramInt, paramDouble, paramDouble, paramDouble, 0.0D, itemStack);
/*     */     }
/* 127 */     else if (this.particle == Particle.ITEM_CRACK || this.particle == Particle.BLOCK_CRACK || this.particle == Particle.BLOCK_DUST || this.particle == Particle.FALLING_DUST) {
/* 128 */       BlockData blockData = Material.STONE.createBlockData();
/* 129 */       paramLocation.getWorld().spawnParticle(this.particle, paramLocation, paramInt, paramDouble, paramDouble, paramDouble, 0.0D, blockData);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 134 */       paramLocation.getWorld().spawnParticle(this.particle, paramLocation, paramInt, paramDouble, paramDouble, paramDouble, 0.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ProjectileParticlesData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 139 */     return this;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\ProjectileParticlesData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */