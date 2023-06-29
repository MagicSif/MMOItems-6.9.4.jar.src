/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
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
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.gui.edition.ParticlesEdition;
/*     */ import net.Indyuce.mmoitems.particle.api.ParticleType;
/*     */ import net.Indyuce.mmoitems.stat.data.ParticleData;
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
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class ItemParticles
/*     */   extends ItemStat<ParticleData, ParticleData> {
/*     */   public ItemParticles() {
/*  34 */     super("ITEM_PARTICLES", VersionMaterial.PINK_STAINED_GLASS.toMaterial(), "Item Particles", new String[] { "The particles displayed when", "holding/wearing your item.", "", ChatColor.BLUE + "A tutorial is available on the wiki." }, new String[] { "all", "!block" }, new org.bukkit.Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  40 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/*  41 */       paramEditionInventory.getEditedSection().set(getPath(), null);
/*  42 */       paramEditionInventory.registerTemplateEdition();
/*  43 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + getName() + ChatColor.GRAY + ".");
/*     */       
/*     */       return;
/*     */     } 
/*  47 */     (new ParticlesEdition(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited())).open(paramEditionInventory.getPage());
/*     */   }
/*     */ 
/*     */   
/*     */   public ParticleData whenInitialized(Object paramObject) {
/*  52 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a config section");
/*  53 */     if (((ConfigurationSection)paramObject).getKeys(false).size() < 1) throw new IllegalArgumentException(""); 
/*  54 */     return new ParticleData((ConfigurationSection)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull ParticleData paramParticleData) {
/*  59 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramParticleData));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull ParticleData paramParticleData) {
/*  67 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/*  70 */     arrayList.add(new ItemTag(getNBTPath(), paramParticleData.toJson().toString()));
/*     */     
/*  72 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<ParticleData> paramOptional) {
/*  77 */     paramList.add(ChatColor.YELLOW + "►" + " Left click to setup the item particles.");
/*  78 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to clear.");
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ParticleData getClearStatData() {
/*  83 */     return new ParticleData(ParticleType.AURA, Particle.EXPLOSION_LARGE);
/*     */   }
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  87 */     String str1 = (String)paramVarArgs[0];
/*     */     
/*  89 */     String str2 = paramString.toUpperCase().replace("-", "_").replace(" ", "_");
/*  90 */     if (str1.equals("particle-type")) {
/*  91 */       ParticleType particleType = ParticleType.valueOf(str2);
/*     */       
/*  93 */       paramEditionInventory.getEditedSection().set("item-particles.type", particleType.name());
/*  94 */       paramEditionInventory.registerTemplateEdition();
/*  95 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Particle type successfully set to " + ChatColor.GOLD + particleType
/*  96 */           .getDefaultName() + ChatColor.GRAY + ".");
/*     */       
/*     */       return;
/*     */     } 
/* 100 */     if (str1.equals("particle-color")) {
/* 101 */       String[] arrayOfString = paramString.split(" ");
/* 102 */       int i = Integer.parseInt(arrayOfString[0]), j = Integer.parseInt(arrayOfString[1]), k = Integer.parseInt(arrayOfString[2]);
/*     */       
/* 104 */       paramEditionInventory.getEditedSection().set("item-particles.color.red", Integer.valueOf(i));
/* 105 */       paramEditionInventory.getEditedSection().set("item-particles.color.green", Integer.valueOf(j));
/* 106 */       paramEditionInventory.getEditedSection().set("item-particles.color.blue", Integer.valueOf(k));
/* 107 */       paramEditionInventory.registerTemplateEdition();
/* 108 */       paramEditionInventory.getPlayer()
/* 109 */         .sendMessage(MMOItems.plugin.getPrefix() + "Particle color successfully set to " + ChatColor.RED + ChatColor.BOLD + i + ChatColor.GRAY + " - " + ChatColor.GREEN + ChatColor.BOLD + j + ChatColor.GRAY + " - " + ChatColor.BLUE + ChatColor.BOLD + k + ChatColor.GRAY + ".");
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 115 */     if (str1.equals("particle")) {
/* 116 */       Particle particle = Particle.valueOf(str2);
/*     */       
/* 118 */       paramEditionInventory.getEditedSection().set("item-particles.particle", particle.name());
/* 119 */       paramEditionInventory.registerTemplateEdition();
/* 120 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Particle successfully set to " + ChatColor.GOLD + 
/* 121 */           MMOUtils.caseOnWords(particle.name().toLowerCase().replace("_", " ")) + ChatColor.GRAY + ".");
/*     */       
/*     */       return;
/*     */     } 
/* 125 */     double d = MMOUtils.parseDouble(paramString);
/*     */     
/* 127 */     paramEditionInventory.getEditedSection().set("item-particles." + str1, Double.valueOf(d));
/* 128 */     paramEditionInventory.registerTemplateEdition();
/* 129 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + ChatColor.GOLD + MMOUtils.caseOnWords(str1.replace("-", " ")) + ChatColor.GRAY + " set to " + ChatColor.GOLD + d + ChatColor.GRAY + ".");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 137 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 138 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 139 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/* 142 */     ParticleData particleData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 145 */     if (particleData != null) paramReadMMOItem.setData(this, (StatData)particleData);
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ParticleData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 152 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/* 153 */     if (itemTag != null) {
/*     */       try {
/* 155 */         return new ParticleData((new JsonParser()).parse((String)itemTag.getValue()).getAsJsonObject());
/*     */       }
/* 157 */       catch (JsonSyntaxException|IllegalStateException jsonSyntaxException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     return null;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\ItemParticles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */