/*     */ package net.Indyuce.mmoitems.api.block;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Biome;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ 
/*     */ public class WorldGenTemplate
/*     */ {
/*     */   private final String id;
/*     */   private final double chunkChance;
/*     */   private final int minDepth;
/*  20 */   private final List<Material> replaceable = new ArrayList<>(); private final int maxDepth; private final int veinSize; private final int veinCount;
/*  21 */   private final List<Material> bordering = new ArrayList<>();
/*  22 */   private final List<Material> notBordering = new ArrayList<>();
/*  23 */   private final List<String> worldWhitelist = new ArrayList<>(), worldBlacklist = new ArrayList<>();
/*  24 */   private final List<String> biomeWhitelist = new ArrayList<>(); private final List<String> biomeBlacklist = new ArrayList<>();
/*     */   private final boolean slimeChunk;
/*     */   
/*     */   public WorldGenTemplate(ConfigurationSection paramConfigurationSection) {
/*  28 */     Validate.notNull(paramConfigurationSection, "Could not read gen template config");
/*     */     
/*  30 */     this.id = paramConfigurationSection.getName().toLowerCase().replace(" ", "-").replace("_", "-");
/*  31 */     paramConfigurationSection.getStringList("replace").forEach(paramString -> this.replaceable.add(Material.valueOf(paramString.toUpperCase().replace("-", "_").replace(" ", "_"))));
/*  32 */     paramConfigurationSection.getStringList("bordering").forEach(paramString -> this.bordering.add(Material.valueOf(paramString.toUpperCase().replace("-", "_").replace(" ", "_"))));
/*  33 */     paramConfigurationSection.getStringList("not-bordering").forEach(paramString -> this.notBordering.add(Material.valueOf(paramString.toUpperCase().replace("-", "_").replace(" ", "_"))));
/*     */     
/*  35 */     for (String str : paramConfigurationSection.getStringList("worlds")) {
/*  36 */       (str.startsWith("!") ? this.worldBlacklist : this.worldWhitelist).add(str.toLowerCase().replace("_", "-"));
/*     */     }
/*  38 */     for (String str : paramConfigurationSection.getStringList("biomes")) {
/*  39 */       (str.startsWith("!") ? this.biomeBlacklist : this.biomeWhitelist).add(str.toUpperCase().replace("-", "_").replace(" ", "_"));
/*     */     }
/*  41 */     this.chunkChance = paramConfigurationSection.getDouble("chunk-chance");
/*  42 */     this.slimeChunk = paramConfigurationSection.getBoolean("slime-chunk", false);
/*     */     
/*  44 */     String[] arrayOfString = paramConfigurationSection.getString("depth").split("=");
/*  45 */     this.minDepth = Integer.parseInt(arrayOfString[0]);
/*  46 */     this.maxDepth = Integer.parseInt(arrayOfString[1]);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  51 */     this.veinSize = paramConfigurationSection.getInt("vein-size");
/*  52 */     this.veinCount = paramConfigurationSection.getInt("vein-count");
/*     */     
/*  54 */     Validate.isTrue((this.veinSize > 0 && this.veinCount > 0), "Vein size and count must be at least 1");
/*     */   }
/*     */   
/*     */   public String getId() {
/*  58 */     return this.id;
/*     */   }
/*     */   
/*     */   public double getChunkChance() {
/*  62 */     return this.chunkChance;
/*     */   }
/*     */   
/*     */   public int getVeinSize() {
/*  66 */     return this.veinSize;
/*     */   }
/*     */   
/*     */   public int getVeinCount() {
/*  70 */     return this.veinCount;
/*     */   }
/*     */   
/*     */   public int getMinDepth() {
/*  74 */     return this.minDepth;
/*     */   }
/*     */   
/*     */   public int getMaxDepth() {
/*  78 */     return this.maxDepth;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canGenerateInWorld(String paramString) {
/*  83 */     String str = paramString.toLowerCase().replace("_", "-");
/*  84 */     if (!this.worldWhitelist.isEmpty() && !this.worldWhitelist.contains(str))
/*  85 */       return false; 
/*  86 */     return (this.worldBlacklist.isEmpty() || !this.worldBlacklist.contains(str));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canGenerate(Location paramLocation) {
/*  92 */     Biome biome = paramLocation.getWorld().getBiome(paramLocation.getBlockX(), paramLocation.getBlockY(), paramLocation.getBlockZ());
/*  93 */     if ((!this.biomeWhitelist.isEmpty() && !this.biomeWhitelist.contains(biome.name())) || (
/*  94 */       !this.biomeBlacklist.isEmpty() && this.biomeBlacklist.contains(biome.name()))) {
/*  95 */       return false;
/*     */     }
/*     */     
/*  98 */     if (this.slimeChunk && !paramLocation.getChunk().isSlimeChunk()) {
/*  99 */       return false;
/*     */     }
/* 101 */     if (!this.bordering.isEmpty() && 
/* 102 */       !checkIfBorderingBlocks(paramLocation)) {
/* 103 */       return false;
/*     */     }
/*     */     
/* 106 */     if (!this.notBordering.isEmpty()) {
/* 107 */       return checkIfNotBorderingBlocks(paramLocation);
/*     */     }
/*     */     
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canReplace(Material paramMaterial) {
/* 114 */     return (this.replaceable.isEmpty() || this.replaceable.contains(paramMaterial));
/*     */   }
/*     */   
/*     */   public boolean canBorder(Material paramMaterial) {
/* 118 */     return (this.bordering.isEmpty() || this.bordering.contains(paramMaterial));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkIfBorderingBlocks(Location paramLocation) {
/* 123 */     Objects.requireNonNull(paramLocation.getBlock()); return Arrays.<BlockFace>stream(BlockFace.values()).map(paramLocation.getBlock()::getRelative)
/* 124 */       .map(Block::getType)
/* 125 */       .allMatch(this::canBorder);
/*     */   }
/*     */   
/*     */   public boolean canNotBorder(Material paramMaterial) {
/* 129 */     return (!this.notBordering.isEmpty() && this.notBordering.contains(paramMaterial));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkIfNotBorderingBlocks(Location paramLocation) {
/* 134 */     Objects.requireNonNull(paramLocation.getBlock()); return Arrays.<BlockFace>stream(BlockFace.values()).map(paramLocation.getBlock()::getRelative)
/* 135 */       .map(Block::getType)
/* 136 */       .allMatch(this::canNotBorder);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\block\WorldGenTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */