/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.ProjectileParticlesData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProjectileParticles
/*     */   extends ItemStat<ProjectileParticlesData, ProjectileParticlesData>
/*     */ {
/*     */   public ProjectileParticles() {
/*  39 */     super("PROJECTILE_PARTICLES", VersionMaterial.LIME_STAINED_GLASS.toMaterial(), "Projectile Particles", new String[] { "The projectile particle that your weapon shoots" }, new String[] { "lute" }, new org.bukkit.Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ProjectileParticlesData whenInitialized(Object paramObject) {
/*  45 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a valid config section");
/*  46 */     ConfigurationSection configurationSection = (ConfigurationSection)paramObject;
/*     */     
/*  48 */     Validate.isTrue(configurationSection.contains("particle"), "Could not find projectile particle");
/*  49 */     Particle particle = Particle.valueOf(configurationSection.getString("particle").toUpperCase().replace("-", "_").replace(" ", ""));
/*     */     
/*  51 */     return ProjectileParticlesData.isColorable(particle) ? 
/*  52 */       new ProjectileParticlesData(particle, configurationSection.getInt("color.red"), configurationSection.getInt("color.green"), configurationSection.getInt("color.blue")) : 
/*  53 */       new ProjectileParticlesData(particle);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  58 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/*  59 */       paramEditionInventory.getEditedSection().set("projectile-particles", null);
/*  60 */       paramEditionInventory.registerTemplateEdition();
/*  61 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed the projectile particle.");
/*     */     } else {
/*  63 */       (new StatEdition(paramEditionInventory, this, new Object[0])).enable(new String[] { "Write in the chat the particle you want along with the color if applicable.", ChatColor.AQUA + "Format: {Particle} {Color}", "All particles can be found here: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Particle.html" });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ProjectileParticlesData getClearStatData() {
/*  72 */     return new ProjectileParticlesData(Particle.FLAME);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<ProjectileParticlesData> paramOptional) {
/*  77 */     if (paramOptional.isPresent()) {
/*  78 */       ProjectileParticlesData projectileParticlesData = paramOptional.get();
/*  79 */       Particle particle = projectileParticlesData.getParticle();
/*     */       
/*  81 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.GREEN + particle);
/*     */       
/*  83 */       if (ProjectileParticlesData.isColorable(particle)) {
/*  84 */         String str = (particle == Particle.NOTE) ? String.valueOf(projectileParticlesData.getRed()) : (projectileParticlesData.getRed() + " " + projectileParticlesData.getGreen() + " " + projectileParticlesData.getBlue());
/*  85 */         paramList.add(ChatColor.GRAY + "Color: " + ChatColor.GREEN + str);
/*     */       } 
/*     */     } else {
/*  88 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*     */     } 
/*  90 */     paramList.add("");
/*  91 */     paramList.add(ChatColor.YELLOW + "►" + " Left click to change this value.");
/*  92 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove this value.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  97 */     String[] arrayOfString = paramString.replace(", ", " ").replace(",", " ").split(" ");
/*  98 */     Particle particle = Particle.valueOf(arrayOfString[0].toUpperCase().replace("-", "_").replace(" ", "_"));
/*  99 */     if (ProjectileParticlesData.isColorable(particle)) {
/* 100 */       Validate.isTrue((arrayOfString.length <= 4), "Too many arguments provided.");
/* 101 */       if (particle.equals(Particle.NOTE)) {
/* 102 */         Validate.isTrue((arrayOfString.length == 2), "You must provide a color for this particle.\n" + MMOItems.plugin
/* 103 */             .getPrefix() + "NOTE particle colors only take a single value between 1 and 24.\n" + MMOItems.plugin
/* 104 */             .getPrefix() + ChatColor.AQUA + "Format: {Particle} {Color}");
/* 105 */         int i = Math.min(24, Math.max(1, Integer.parseInt(arrayOfString[1])));
/* 106 */         paramEditionInventory.getEditedSection().set("projectile-particles.particle", particle.name());
/* 107 */         paramEditionInventory.getEditedSection().set("projectile-particles.color.red", Integer.valueOf(i));
/* 108 */         paramEditionInventory.getEditedSection().set("projectile-particles.color.green", Integer.valueOf(0));
/* 109 */         paramEditionInventory.getEditedSection().set("projectile-particles.color.blue", Integer.valueOf(0));
/* 110 */         paramEditionInventory.registerTemplateEdition();
/* 111 */         paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Particle successfully set to " + 
/* 112 */             MMOUtils.caseOnWords(particle.name().toLowerCase().replace("_", " ")) + " with color " + i);
/*     */       } else {
/* 114 */         Validate.isTrue((arrayOfString.length == 4), "You must provide a color for this particle.\n" + MMOItems.plugin
/* 115 */             .getPrefix() + ChatColor.AQUA + "Format: {Particle} {R G B}");
/* 116 */         boolean bool1 = (arrayOfString[1] != null) ? Math.min(255, Math.max(0, Integer.parseInt(arrayOfString[1]))) : false;
/* 117 */         boolean bool2 = (arrayOfString[2] != null) ? Math.min(255, Math.max(0, Integer.parseInt(arrayOfString[2]))) : false;
/* 118 */         boolean bool3 = (arrayOfString[3] != null) ? Math.min(255, Math.max(0, Integer.parseInt(arrayOfString[3]))) : false;
/* 119 */         paramEditionInventory.getEditedSection().set("projectile-particles.particle", particle.name());
/* 120 */         paramEditionInventory.getEditedSection().set("projectile-particles.color.red", Integer.valueOf(bool1));
/* 121 */         paramEditionInventory.getEditedSection().set("projectile-particles.color.green", Integer.valueOf(bool2));
/* 122 */         paramEditionInventory.getEditedSection().set("projectile-particles.color.blue", Integer.valueOf(bool3));
/* 123 */         paramEditionInventory.registerTemplateEdition();
/* 124 */         paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Particle successfully set to " + 
/* 125 */             MMOUtils.caseOnWords(particle.name().toLowerCase().replace("_", " ")) + " with RGB color " + bool1 + " " + bool2 + " " + bool3);
/*     */       } 
/*     */     } else {
/* 128 */       Validate.isTrue((arrayOfString.length == 1), "That particle cannot be assigned a color");
/* 129 */       paramEditionInventory.getEditedSection().set("projectile-particles.particle", particle.name());
/* 130 */       paramEditionInventory.getEditedSection().set("projectile-particles.color.red", Integer.valueOf(0));
/* 131 */       paramEditionInventory.getEditedSection().set("projectile-particles.color.green", Integer.valueOf(0));
/* 132 */       paramEditionInventory.getEditedSection().set("projectile-particles.color.blue", Integer.valueOf(0));
/* 133 */       paramEditionInventory.registerTemplateEdition();
/* 134 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Particle successfully set to " + 
/* 135 */           MMOUtils.caseOnWords(particle.name().toLowerCase().replace("_", " ")));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull ProjectileParticlesData paramProjectileParticlesData) {
/* 141 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramProjectileParticlesData));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull ProjectileParticlesData paramProjectileParticlesData) {
/* 147 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 148 */     arrayList.add(new ItemTag(getNBTPath(), paramProjectileParticlesData.toString()));
/* 149 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 154 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 155 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 156 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/* 158 */     ProjectileParticlesData projectileParticlesData = getLoadedNBT(arrayList);
/*     */     
/* 160 */     if (projectileParticlesData != null) {
/* 161 */       paramReadMMOItem.setData(this, (StatData)projectileParticlesData);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ProjectileParticlesData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 167 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */     
/* 169 */     if (itemTag != null) {
/*     */       try {
/* 171 */         JsonObject jsonObject = (new JsonParser()).parse((String)itemTag.getValue()).getAsJsonObject();
/* 172 */         Particle particle = Particle.valueOf(jsonObject.get("Particle").getAsString());
/*     */         
/* 174 */         if (ProjectileParticlesData.isColorable(particle)) {
/* 175 */           return new ProjectileParticlesData(particle, jsonObject.get("Red").getAsInt(), jsonObject.get("Green").getAsInt(), jsonObject.get("Blue").getAsInt());
/*     */         }
/* 177 */         return new ProjectileParticlesData(particle);
/*     */       
/*     */       }
/* 180 */       catch (JsonSyntaxException jsonSyntaxException) {
/* 181 */         jsonSyntaxException.printStackTrace();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 187 */     return null;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\ProjectileParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */