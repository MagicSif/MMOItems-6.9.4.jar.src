/*     */ package net.Indyuce.mmoitems.api.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.CraftingRecipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.Recipe;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CraftingStatus
/*     */ {
/*  19 */   private final Map<String, CraftingQueue> queues = new HashMap<>();
/*     */   
/*     */   public void load(PlayerData paramPlayerData, ConfigurationSection paramConfigurationSection) {
/*  22 */     String str = paramPlayerData.isOnline() ? paramPlayerData.getPlayer().getName() : "Unknown Player";
/*     */     
/*  24 */     for (String str1 : paramConfigurationSection.getKeys(false)) {
/*  25 */       if (!MMOItems.plugin.getCrafting().hasStation(str1)) {
/*  26 */         MMOItems.plugin.getLogger().log(Level.WARNING, "An error occurred while trying to load crafting station recipe data of '" + str + "': could not find crafting station with ID '" + str1 + "', make sure you backup that player data file before the user logs off.");
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */ 
/*     */       
/*  33 */       CraftingStation craftingStation = MMOItems.plugin.getCrafting().getStation(str1);
/*  34 */       CraftingQueue craftingQueue = new CraftingQueue(craftingStation);
/*  35 */       this.queues.put(str1, craftingQueue);
/*     */       
/*  37 */       for (String str2 : paramConfigurationSection.getConfigurationSection(str1).getKeys(false)) {
/*  38 */         String str3 = paramConfigurationSection.getString(str1 + "." + str2 + ".recipe");
/*  39 */         if (str3 == null || !craftingStation.hasRecipe(str3)) {
/*  40 */           MMOItems.plugin.getLogger().log(Level.WARNING, "An error occurred while trying to load crafting station recipe data of '" + str + "': could not find recipe with ID '" + str3 + "', make sure you backup that player data file before the user logs off.");
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */         
/*  47 */         Recipe recipe = craftingStation.getRecipe(str3);
/*  48 */         if (!(recipe instanceof CraftingRecipe)) {
/*  49 */           MMOItems.plugin.getLogger().log(Level.WARNING, "An error occurred while trying to load crafting station recipe data of '" + str + "': recipe '" + recipe
/*  50 */               .getId() + "' is not a CRAFTING recipe.");
/*     */           
/*     */           continue;
/*     */         } 
/*  54 */         craftingQueue.add((CraftingRecipe)recipe, paramConfigurationSection.getLong(str1 + "." + str2 + ".started"), paramConfigurationSection
/*  55 */             .getLong(str1 + "." + str2 + ".delay"));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void save(ConfigurationSection paramConfigurationSection) {
/*  61 */     for (String str : this.queues.keySet()) {
/*  62 */       CraftingQueue craftingQueue = this.queues.get(str);
/*     */       
/*  64 */       for (CraftingQueue.CraftingInfo craftingInfo : craftingQueue.getCrafts()) {
/*  65 */         paramConfigurationSection.set(str + ".recipe-" + craftingInfo.getUniqueId().toString() + ".recipe", craftingInfo.getRecipe().getId());
/*  66 */         paramConfigurationSection.set(str + ".recipe-" + craftingInfo.getUniqueId().toString() + ".started", Long.valueOf(craftingInfo.started));
/*  67 */         paramConfigurationSection.set(str + ".recipe-" + craftingInfo.getUniqueId().toString() + ".delay", Long.valueOf(craftingInfo.delay));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public CraftingQueue getQueue(CraftingStation paramCraftingStation) {
/*  73 */     if (!this.queues.containsKey(paramCraftingStation.getId()))
/*  74 */       this.queues.put(paramCraftingStation.getId(), new CraftingQueue(paramCraftingStation)); 
/*  75 */     return this.queues.get(paramCraftingStation.getId());
/*     */   }
/*     */   
/*     */   public static class CraftingQueue {
/*     */     private final String station;
/*  80 */     private final List<CraftingInfo> crafts = new ArrayList<>();
/*     */     
/*     */     public CraftingQueue(CraftingStation param1CraftingStation) {
/*  83 */       this.station = param1CraftingStation.getId();
/*     */     }
/*     */     
/*     */     public List<CraftingInfo> getCrafts() {
/*  87 */       return this.crafts;
/*     */     }
/*     */     
/*     */     public boolean isFull(CraftingStation param1CraftingStation) {
/*  91 */       return (this.crafts.size() >= param1CraftingStation.getMaxQueueSize());
/*     */     }
/*     */     
/*     */     public void remove(CraftingInfo param1CraftingInfo) {
/*  95 */       int i = this.crafts.indexOf(param1CraftingInfo);
/*  96 */       if (i != -1)
/*  97 */         for (int j = i + 1; j < this.crafts.size(); j++) {
/*  98 */           CraftingInfo craftingInfo = this.crafts.get(j);
/*  99 */           craftingInfo.delay = Math.max(0L, craftingInfo.delay - param1CraftingInfo.getLeft());
/*     */         }  
/* 101 */       this.crafts.remove(param1CraftingInfo);
/*     */     }
/*     */     
/*     */     public CraftingInfo getCraft(UUID param1UUID) {
/* 105 */       for (CraftingInfo craftingInfo : this.crafts) {
/* 106 */         if (craftingInfo.getUniqueId().equals(param1UUID))
/* 107 */           return craftingInfo; 
/* 108 */       }  return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void add(CraftingRecipe param1CraftingRecipe) {
/* 116 */       add(param1CraftingRecipe, System.currentTimeMillis(), (
/* 117 */           (this.crafts.size() == 0) ? 0L : ((CraftingInfo)this.crafts.get(this.crafts.size() - 1)).getLeft()) + (long)param1CraftingRecipe.getCraftingTime() * 1000L);
/*     */     }
/*     */     
/*     */     private void add(CraftingRecipe param1CraftingRecipe, long param1Long1, long param1Long2) {
/* 121 */       this.crafts.add(new CraftingInfo(param1CraftingRecipe, param1Long1, param1Long2));
/*     */     }
/*     */     
/*     */     @Deprecated
/*     */     public CraftingStation getStation() {
/* 126 */       return MMOItems.plugin.getCrafting().getStation(this.station);
/*     */     }
/*     */     
/*     */     public class CraftingInfo {
/*     */       private final String recipe;
/* 131 */       private final UUID uuid = UUID.randomUUID();
/*     */       private final long started;
/*     */       private long delay;
/*     */       
/*     */       private CraftingInfo(CraftingRecipe param2CraftingRecipe, long param2Long1, long param2Long2) {
/* 136 */         this.recipe = param2CraftingRecipe.getId();
/* 137 */         this.started = param2Long1;
/* 138 */         this.delay = param2Long2;
/*     */       }
/*     */       
/*     */       public UUID getUniqueId() {
/* 142 */         return this.uuid;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       @Deprecated
/*     */       public CraftingRecipe getRecipe() {
/* 151 */         return (CraftingRecipe)CraftingStatus.CraftingQueue.this.getStation().getRecipe(this.recipe);
/*     */       }
/*     */       
/*     */       public boolean isReady() {
/* 155 */         return (getLeft() == 0L);
/*     */       }
/*     */       
/*     */       public void removeDelay(long param2Long) {
/* 159 */         this.delay -= param2Long;
/*     */       }
/*     */       
/*     */       public long getElapsed() {
/* 163 */         return Math.max((long)getRecipe().getCraftingTime() * 1000L, System.currentTimeMillis() - this.started);
/*     */       }
/*     */       
/*     */       public long getLeft() {
/* 167 */         return Math.max(0L, this.started + this.delay - System.currentTimeMillis());
/*     */       }
/*     */       
/*     */       public boolean equals(Object param2Object)
/*     */       {
/* 172 */         return (param2Object instanceof CraftingInfo && ((CraftingInfo)param2Object).uuid.equals(this.uuid)); } } } public class CraftingInfo { public boolean equals(Object param1Object) { return (param1Object instanceof CraftingInfo && ((CraftingInfo)param1Object).uuid.equals(this.uuid)); }
/*     */ 
/*     */     
/*     */     private final String recipe;
/*     */     private final UUID uuid = UUID.randomUUID();
/*     */     private final long started;
/*     */     private long delay;
/*     */     
/*     */     private CraftingInfo(CraftingRecipe param1CraftingRecipe, long param1Long1, long param1Long2) {
/*     */       this.recipe = param1CraftingRecipe.getId();
/*     */       this.started = param1Long1;
/*     */       this.delay = param1Long2;
/*     */     }
/*     */     
/*     */     public UUID getUniqueId() {
/*     */       return this.uuid;
/*     */     }
/*     */     
/*     */     @Deprecated
/*     */     public CraftingRecipe getRecipe() {
/*     */       return (CraftingRecipe)CraftingStatus.CraftingQueue.this.getStation().getRecipe(this.recipe);
/*     */     }
/*     */     
/*     */     public boolean isReady() {
/*     */       return (getLeft() == 0L);
/*     */     }
/*     */     
/*     */     public void removeDelay(long param1Long) {
/*     */       this.delay -= param1Long;
/*     */     }
/*     */     
/*     */     public long getElapsed() {
/*     */       return Math.max((long)getRecipe().getCraftingTime() * 1000L, System.currentTimeMillis() - this.started);
/*     */     }
/*     */     
/*     */     public long getLeft() {
/*     */       return Math.max(0L, this.started + this.delay - System.currentTimeMillis());
/*     */     } }
/*     */ 
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\CraftingStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */