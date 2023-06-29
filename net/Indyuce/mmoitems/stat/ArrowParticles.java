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
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.gui.edition.ArrowParticlesEdition;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.ArrowParticlesData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArrowParticles
/*     */   extends ItemStat<ArrowParticlesData, ArrowParticlesData>
/*     */ {
/*     */   public ArrowParticles() {
/*  36 */     super("ARROW_PARTICLES", VersionMaterial.LIME_STAINED_GLASS.toMaterial(), "Arrow Particles", new String[] { "Particles that display around", "the arrows your bow fires." }, new String[] { "bow", "crossbow" }, new org.bukkit.Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrowParticlesData whenInitialized(Object paramObject) {
/*  42 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a valid config section");
/*  43 */     ConfigurationSection configurationSection = (ConfigurationSection)paramObject;
/*     */     
/*  45 */     Validate.isTrue(configurationSection.contains("particle"), "Could not find arrow particle");
/*     */     
/*  47 */     Particle particle = Particle.valueOf(configurationSection.getString("particle").toUpperCase().replace("-", "_").replace(" ", "_"));
/*  48 */     int i = configurationSection.getInt("amount");
/*  49 */     double d = configurationSection.getDouble("offset");
/*     */     
/*  51 */     return MMOUtils.isColorable(particle) ? 
/*  52 */       new ArrowParticlesData(particle, i, d, configurationSection.getInt("color.red"), configurationSection.getInt("color.green"), configurationSection
/*  53 */         .getInt("color.blue")) : 
/*  54 */       new ArrowParticlesData(particle, i, d, configurationSection.getDouble("speed"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull ArrowParticlesData paramArrowParticlesData) {
/*  59 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramArrowParticlesData));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull ArrowParticlesData paramArrowParticlesData) {
/*  65 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  66 */     arrayList.add(new ItemTag(getNBTPath(), paramArrowParticlesData.toString()));
/*  67 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/*  74 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  75 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/*  76 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/*  79 */     ArrowParticlesData arrowParticlesData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/*  82 */     if (arrowParticlesData != null)
/*     */     {
/*     */       
/*  85 */       paramReadMMOItem.setData(this, (StatData)arrowParticlesData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ArrowParticlesData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/*  94 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/*  97 */     if (itemTag != null) {
/*     */       
/*     */       try {
/* 100 */         JsonObject jsonObject = (new JsonParser()).parse((String)itemTag.getValue()).getAsJsonObject();
/*     */ 
/*     */         
/* 103 */         Particle particle = Particle.valueOf(jsonObject.get("Particle").getAsString());
/* 104 */         int i = jsonObject.get("Amount").getAsInt();
/* 105 */         double d = jsonObject.get("Offset").getAsDouble();
/*     */ 
/*     */         
/* 108 */         if (MMOUtils.isColorable(particle))
/*     */         {
/*     */           
/* 111 */           return new ArrowParticlesData(particle, i, d, jsonObject.get("Red").getAsInt(), jsonObject.get("Green").getAsInt(), jsonObject.get("Blue").getAsInt());
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 117 */         return new ArrowParticlesData(particle, i, d, jsonObject.get("Speed").getAsDouble());
/*     */       
/*     */       }
/* 120 */       catch (JsonSyntaxException|IllegalStateException jsonSyntaxException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/* 133 */     (new ArrowParticlesEdition(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited())).open(paramEditionInventory.getPage());
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 138 */     String str = (String)paramVarArgs[0];
/*     */     
/* 140 */     if (str.equals("color")) {
/* 141 */       String[] arrayOfString = paramString.split(" ");
/* 142 */       int i = Integer.parseInt(arrayOfString[0]), j = Integer.parseInt(arrayOfString[1]), k = Integer.parseInt(arrayOfString[2]);
/* 143 */       paramEditionInventory.getEditedSection().set("arrow-particles.color.red", Integer.valueOf(i));
/* 144 */       paramEditionInventory.getEditedSection().set("arrow-particles.color.green", Integer.valueOf(j));
/* 145 */       paramEditionInventory.getEditedSection().set("arrow-particles.color.blue", Integer.valueOf(k));
/* 146 */       paramEditionInventory.registerTemplateEdition();
/* 147 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Particle color successfully set to " + 
/* 148 */           ChatColor.translateAlternateColorCodes('&', "&c&l" + i + "&7 - &a&l" + j + "&7 - &9&l" + k));
/*     */       
/*     */       return;
/*     */     } 
/* 152 */     if (str.equals("particle")) {
/* 153 */       Particle particle = Particle.valueOf(paramString.toUpperCase().replace("-", "_").replace(" ", "_"));
/* 154 */       paramEditionInventory.getEditedSection().set("arrow-particles.particle", particle.name());
/* 155 */       paramEditionInventory.registerTemplateEdition();
/* 156 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Particle successfully set to " + ChatColor.GOLD + 
/* 157 */           MMOUtils.caseOnWords(particle.name().toLowerCase().replace("_", " ")) + ChatColor.GRAY + ".");
/*     */       
/*     */       return;
/*     */     } 
/* 161 */     if (str.equals("amount")) {
/* 162 */       int i = Integer.parseInt(paramString);
/* 163 */       paramEditionInventory.getEditedSection().set("arrow-particles.amount", Integer.valueOf(i));
/* 164 */       paramEditionInventory.registerTemplateEdition();
/* 165 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + ChatColor.GOLD + "Amount" + ChatColor.GRAY + " set to " + ChatColor.GOLD + i + ChatColor.GRAY + ".");
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 171 */     double d = MMOUtils.parseDouble(paramString);
/* 172 */     paramEditionInventory.getEditedSection().set("arrow-particles." + str, Double.valueOf(d));
/* 173 */     paramEditionInventory.registerTemplateEdition();
/* 174 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + ChatColor.GOLD + MMOUtils.caseOnWords(str.replace("-", " ")) + ChatColor.GRAY + " set to " + ChatColor.GOLD + d + ChatColor.GRAY + ".");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<ArrowParticlesData> paramOptional) {
/* 180 */     if (paramOptional.isPresent())
/* 181 */     { ArrowParticlesData arrowParticlesData = paramOptional.get();
/* 182 */       paramList.add(ChatColor.GRAY + "Current Value:");
/*     */       
/* 184 */       paramList.add(ChatColor.GRAY + "* Particle: " + ChatColor.GOLD + 
/* 185 */           MMOUtils.caseOnWords(arrowParticlesData.getParticle().name().replace("_", " ").toLowerCase()));
/* 186 */       paramList.add(ChatColor.GRAY + "* Amount: " + ChatColor.WHITE + arrowParticlesData.getAmount());
/* 187 */       paramList.add(ChatColor.GRAY + "* Offset: " + ChatColor.WHITE + arrowParticlesData.getOffset());
/* 188 */       paramList.add("");
/*     */       
/* 190 */       if (MMOUtils.isColorable(arrowParticlesData.getParticle())) {
/* 191 */         paramList.add(ChatColor.translateAlternateColorCodes('&', "&7* Color: &c&l" + arrowParticlesData
/* 192 */               .getRed() + "&7 - &a&l" + arrowParticlesData.getGreen() + "&7 - &9&l" + arrowParticlesData.getBlue()));
/*     */       } else {
/* 194 */         paramList.add(ChatColor.GRAY + "* Speed: " + ChatColor.WHITE + arrowParticlesData.getSpeed());
/*     */       }  }
/* 196 */     else { paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None"); }
/*     */     
/* 198 */     paramList.add("");
/* 199 */     paramList.add(ChatColor.YELLOW + "►" + " Click to edit.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrowParticlesData getClearStatData() {
/* 205 */     return new ArrowParticlesData(Particle.EXPLOSION_LARGE, 1, 0.0D, 1.0D);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\ArrowParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */