/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PermanentEffects
/*     */   extends ItemStat<RandomPotionEffectListData, PotionEffectListData>
/*     */ {
/*     */   public PermanentEffects() {
/*  47 */     super("PERM_EFFECTS", Material.POTION, "Permanent Effects", new String[] { "The potion effects your", "item grants to the holder." }, new String[] { "!miscellaneous", "!block", "all" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomPotionEffectListData whenInitialized(Object paramObject) {
/*  53 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a config section");
/*  54 */     ConfigurationSection configurationSection = (ConfigurationSection)paramObject;
/*     */     
/*  56 */     RandomPotionEffectListData randomPotionEffectListData = new RandomPotionEffectListData(new RandomPotionEffectData[0]);
/*     */     
/*  58 */     for (String str : configurationSection.getKeys(false)) {
/*  59 */       PotionEffectType potionEffectType = PotionEffectType.getByName(str.toUpperCase().replace("-", "_").replace(" ", "_"));
/*  60 */       Validate.notNull(potionEffectType, "Could not find potion effect type named '" + str + "'");
/*  61 */       randomPotionEffectListData.add(new RandomPotionEffectData[] { new RandomPotionEffectData(potionEffectType, new NumericStatFormula(configurationSection.get(str))) });
/*     */     } 
/*     */     
/*  64 */     return randomPotionEffectListData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  69 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  70 */       (new StatEdition(paramEditionInventory, ItemStats.PERM_EFFECTS, new Object[0])).enable(new String[] { "Write in the chat the permanent potion effect you want to add.", ChatColor.AQUA + "Format: {Effect Name} {Amplifier Numeric Formula}" });
/*     */     }
/*     */     
/*  73 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/*  74 */       paramEditionInventory.getEditedSection().contains("perm-effects")) {
/*  75 */       Set<? extends String> set = paramEditionInventory.getEditedSection().getConfigurationSection("perm-effects").getKeys(false);
/*  76 */       String str = (new ArrayList<>(set)).get(set.size() - 1);
/*  77 */       paramEditionInventory.getEditedSection().set("perm-effects." + str, null);
/*  78 */       if (set.size() <= 1)
/*  79 */         paramEditionInventory.getEditedSection().set("perm-effects", null); 
/*  80 */       paramEditionInventory.registerTemplateEdition();
/*  81 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + str.substring(0, 1).toUpperCase() + str
/*  82 */           .substring(1).toLowerCase() + ".");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  89 */     String[] arrayOfString = paramString.split(" ");
/*  90 */     Validate.isTrue((arrayOfString.length >= 2), "Use this format: {Effect Name} {Effect Amplifier Numeric Formula}. Example: 'speed 1 0.3' stands for Speed 1, plus 0.3 level per item level (rounded up to lower int)");
/*     */ 
/*     */     
/*  93 */     PotionEffectType potionEffectType = PotionEffectType.getByName(arrayOfString[0].replace("-", "_"));
/*  94 */     Validate.notNull(potionEffectType, arrayOfString[0] + " is not a valid potion effect. All potion effects can be found here: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/potion/PotionEffectType.html");
/*     */ 
/*     */     
/*  97 */     NumericStatFormula numericStatFormula = new NumericStatFormula(paramString.substring(paramString.indexOf(" ") + 1));
/*  98 */     numericStatFormula.fillConfigurationSection(paramEditionInventory.getEditedSection(), "perm-effects." + potionEffectType.getName());
/*  99 */     paramEditionInventory.registerTemplateEdition();
/* 100 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin
/* 101 */         .getPrefix() + ChatColor.GOLD + potionEffectType.getName() + " " + numericStatFormula + ChatColor.GRAY + " successfully added.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<RandomPotionEffectListData> paramOptional) {
/* 106 */     if (paramOptional.isPresent()) {
/* 107 */       paramList.add(ChatColor.GRAY + "Current Value:");
/* 108 */       RandomPotionEffectListData randomPotionEffectListData = paramOptional.get();
/* 109 */       for (RandomPotionEffectData randomPotionEffectData : randomPotionEffectListData.getEffects()) {
/* 110 */         paramList.add(ChatColor.GRAY + "* " + ChatColor.GREEN + MMOUtils.caseOnWords(randomPotionEffectData.getType().getName().replace("_", " ").toLowerCase()) + " " + randomPotionEffectData
/* 111 */             .getAmplifier().toString());
/*     */       }
/*     */     } else {
/* 114 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*     */     } 
/* 116 */     paramList.add("");
/* 117 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add an effect.");
/* 118 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last effect.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PotionEffectListData getClearStatData() {
/* 124 */     return new PotionEffectListData(new PotionEffectData[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull PotionEffectListData paramPotionEffectListData) {
/* 129 */     ArrayList arrayList = new ArrayList();
/*     */     
/* 131 */     String str = ItemStat.translate("perm-effect");
/* 132 */     paramPotionEffectListData.getEffects().forEach(paramPotionEffectData -> paramList.add(paramString.replace("{effect}", MMOItems.plugin.getLanguage().getPotionEffectName(paramPotionEffectData.getType()) + " " + MMOUtils.intToRoman(paramPotionEffectData.getLevel()))));
/*     */ 
/*     */ 
/*     */     
/* 136 */     paramItemStackBuilder.getLore().insert("perm-effects", arrayList);
/*     */ 
/*     */     
/* 139 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramPotionEffectListData));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull PotionEffectListData paramPotionEffectListData) {
/* 147 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 148 */     JsonObject jsonObject = new JsonObject();
/*     */ 
/*     */     
/* 151 */     for (PotionEffectData potionEffectData : paramPotionEffectListData.getEffects())
/*     */     {
/*     */       
/* 154 */       jsonObject.addProperty(potionEffectData.getType().getName(), Integer.valueOf(potionEffectData.getLevel()));
/*     */     }
/*     */ 
/*     */     
/* 158 */     arrayList.add(new ItemTag(getNBTPath(), jsonObject.toString()));
/*     */     
/* 160 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 167 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 168 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 169 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/* 172 */     PotionEffectListData potionEffectListData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 175 */     if (potionEffectListData != null) paramReadMMOItem.setData(this, (StatData)potionEffectListData);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PotionEffectListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 183 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 186 */     if (itemTag != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 192 */         PotionEffectListData potionEffectListData = new PotionEffectListData(new PotionEffectData[0]);
/*     */         
/* 194 */         JsonElement jsonElement = (new JsonParser()).parse((String)itemTag.getValue());
/*     */         
/* 196 */         jsonElement.getAsJsonObject().entrySet().forEach(paramEntry -> paramPotionEffectListData.add(new PotionEffectData[] { new PotionEffectData(PotionEffectType.getByName((String)paramEntry.getKey()), ((JsonElement)paramEntry.getValue()).getAsInt()) }));
/*     */ 
/*     */ 
/*     */         
/* 200 */         return potionEffectListData;
/*     */       }
/* 202 */       catch (JsonSyntaxException|IllegalStateException jsonSyntaxException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     return null;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\PermanentEffects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */