/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.PotionEffectData;
/*     */ import net.Indyuce.mmoitems.stat.data.PotionEffectListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomPotionEffectData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomPotionEffectListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.NotImplementedException;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.meta.PotionMeta;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class PotionEffects extends ItemStat<RandomPotionEffectListData, PotionEffectListData> {
/*     */   public PotionEffects() {
/*  38 */     super("POTION_EFFECT", Material.POTION, "Potion Effects", new String[] { "The effects of your potion.", "(May have an impact on color).", "Does NOT support tipped arrows." }, new String[] { "all" }, new Material[] { Material.POTION, Material.SPLASH_POTION, Material.LINGERING_POTION });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomPotionEffectListData whenInitialized(Object paramObject) {
/*  44 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a config section");
/*  45 */     return new RandomPotionEffectListData((ConfigurationSection)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  50 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  51 */       (new StatEdition(paramEditionInventory, ItemStats.POTION_EFFECTS, new Object[0])).enable(new String[] { "Write in the chat the potion effect you want to add.", ChatColor.AQUA + "Format: {Effect Name} {Duration} {Amplifier}", ChatColor.AQUA + "Other Format: {Effect Name}|{Duration Numeric Formula}|{Amplifier Numeric Formula}" });
/*     */     }
/*     */ 
/*     */     
/*  55 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && paramEditionInventory.getEditedSection().contains("potion-effect")) {
/*  56 */       Set<? extends String> set = paramEditionInventory.getEditedSection().getConfigurationSection("potion-effect").getKeys(false);
/*  57 */       String str = (new ArrayList<>(set)).get(set.size() - 1);
/*  58 */       paramEditionInventory.getEditedSection().set("potion-effect." + str, null);
/*  59 */       if (set.size() <= 1)
/*  60 */         paramEditionInventory.getEditedSection().set("potion-effect", null); 
/*  61 */       paramEditionInventory.registerTemplateEdition();
/*  62 */       paramEditionInventory.getPlayer()
/*  63 */         .sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + ChatColor.GOLD + formatName(str) + ChatColor.GRAY + ".");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  69 */     if (paramString.contains("|")) {
/*  70 */       String[] arrayOfString1 = paramString.split("\\|");
/*     */       
/*  72 */       PotionEffectType potionEffectType1 = PotionEffectType.getByName(arrayOfString1[0].replace("-", "_"));
/*  73 */       Validate.notNull(potionEffectType1, arrayOfString1[0] + " is not a valid potion effect. All potion effects can be found here: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/potion/PotionEffectType.html");
/*     */ 
/*     */       
/*  76 */       NumericStatFormula numericStatFormula1 = new NumericStatFormula(arrayOfString1[1]), numericStatFormula2 = new NumericStatFormula(arrayOfString1[2]);
/*  77 */       numericStatFormula1.fillConfigurationSection(paramEditionInventory.getEditedSection(), "potion-effect." + potionEffectType1.getName() + ".duration");
/*  78 */       numericStatFormula2.fillConfigurationSection(paramEditionInventory.getEditedSection(), "potion-effect." + potionEffectType1.getName() + ".amplifier");
/*  79 */       paramEditionInventory.registerTemplateEdition();
/*  80 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + ChatColor.GOLD + formatName(potionEffectType1) + ChatColor.GRAY + " successfully added.");
/*     */       
/*     */       return;
/*     */     } 
/*  84 */     String[] arrayOfString = paramString.split(" ");
/*  85 */     Validate.isTrue((arrayOfString.length == 3), paramString + " is not a valid {Effect Name} {Duration} {Amplifier}. Example: 'FAST_DIGGING 30 3' stands for Haste 3 for 30 seconds.");
/*     */ 
/*     */     
/*  88 */     PotionEffectType potionEffectType = PotionEffectType.getByName(arrayOfString[0].replace("-", "_"));
/*  89 */     Validate.notNull(potionEffectType, arrayOfString[0] + " is not a valid potion effect. All potion effects can be found here: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/potion/PotionEffectType.html");
/*     */ 
/*     */     
/*  92 */     double d = MMOUtils.parseDouble(arrayOfString[1]);
/*  93 */     int i = (int)MMOUtils.parseDouble(arrayOfString[2]);
/*     */     
/*  95 */     paramEditionInventory.getEditedSection().set("potion-effect." + potionEffectType.getName() + ".duration", Double.valueOf(d));
/*  96 */     paramEditionInventory.getEditedSection().set("potion-effect." + potionEffectType.getName() + ".amplifier", Integer.valueOf(i));
/*  97 */     paramEditionInventory.registerTemplateEdition();
/*  98 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin
/*  99 */         .getPrefix() + ChatColor.GOLD + formatName(potionEffectType) + " " + i + ChatColor.GRAY + " successfully added.");
/*     */   }
/*     */   
/*     */   private String formatName(PotionEffectType paramPotionEffectType) {
/* 103 */     return formatName(paramPotionEffectType.getName());
/*     */   }
/*     */   
/*     */   private String formatName(String paramString) {
/* 107 */     return MMOUtils.caseOnWords(paramString.replace("_", " ").toLowerCase());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<RandomPotionEffectListData> paramOptional) {
/* 113 */     if (paramOptional.isPresent()) {
/* 114 */       paramList.add(ChatColor.GRAY + "Current Value:");
/* 115 */       RandomPotionEffectListData randomPotionEffectListData = paramOptional.get();
/* 116 */       for (RandomPotionEffectData randomPotionEffectData : randomPotionEffectListData.getEffects()) {
/* 117 */         paramList.add(ChatColor.GRAY + "* " + ChatColor.GREEN + MMOUtils.caseOnWords(randomPotionEffectData.getType().getName().toLowerCase().replace("_", " ")) + " " + randomPotionEffectData
/* 118 */             .getAmplifier().toString() + " " + ChatColor.GRAY + "(" + ChatColor.GREEN + randomPotionEffectData.getDuration().toString() + ChatColor.GRAY + "s)");
/*     */       }
/*     */     } else {
/* 121 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*     */     } 
/* 123 */     paramList.add("");
/* 124 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add an effect.");
/* 125 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last effect.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PotionEffectListData getClearStatData() {
/* 131 */     return new PotionEffectListData(new PotionEffectData[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull PotionEffectListData paramPotionEffectListData) {
/* 136 */     if (paramItemStackBuilder.getItemStack().getType().name().contains("POTION")) {
/* 137 */       for (PotionEffectData potionEffectData : paramPotionEffectListData.getEffects()) {
/* 138 */         ((PotionMeta)paramItemStackBuilder.getMeta()).addCustomEffect(potionEffectData.toEffect(), false);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull PotionEffectListData paramPotionEffectListData) {
/* 147 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 153 */     if (!(paramReadMMOItem.getNBT().getItem().getItemMeta() instanceof PotionMeta)) {
/*     */       return;
/*     */     }
/*     */     
/* 157 */     PotionEffectListData potionEffectListData = new PotionEffectListData(new PotionEffectData[0]);
/* 158 */     for (PotionEffect potionEffect : ((PotionMeta)paramReadMMOItem.getNBT().getItem().getItemMeta()).getCustomEffects()) {
/* 159 */       potionEffectListData.add(new PotionEffectData[] { new PotionEffectData(potionEffect) });
/*     */     } 
/* 161 */     paramReadMMOItem.setData(this, (StatData)potionEffectListData);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PotionEffectListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 167 */     throw new NotImplementedException();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\PotionEffects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */