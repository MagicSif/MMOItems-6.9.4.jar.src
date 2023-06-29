/*     */ package net.Indyuce.mmoitems.stat;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
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
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class Effects extends ItemStat<RandomPotionEffectListData, PotionEffectListData> implements PlayerConsumable {
/*     */   public Effects() {
/*  41 */     super("EFFECTS", Material.POTION, "Effects", new String[] { "The potion effects your", "consumable item grants." }, new String[] { "consumable" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomPotionEffectListData whenInitialized(Object paramObject) {
/*  47 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a config section");
/*  48 */     return new RandomPotionEffectListData((ConfigurationSection)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  53 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  54 */       (new StatEdition(paramEditionInventory, ItemStats.EFFECTS, new Object[0])).enable(new String[] { "Write in the chat the permanent potion effect you want to add.", ChatColor.AQUA + "Format: {Potion Effect Name}|{Duration Numeric Formula}|{Amplifier Numeric Formula}", ChatColor.DARK_RED + "Note: " + ChatColor.RED + "The '|' lines are literal." });
/*     */     }
/*     */     
/*  57 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/*  58 */       paramEditionInventory.getEditedSection().contains("effects")) {
/*  59 */       Set set = paramEditionInventory.getEditedSection().getConfigurationSection("effects").getKeys(false);
/*  60 */       String str = Arrays.<String>asList((String[])set.toArray((Object[])new String[0])).get(set.size() - 1);
/*  61 */       paramEditionInventory.getEditedSection().set("effects." + str, null);
/*  62 */       if (set.size() <= 1)
/*  63 */         paramEditionInventory.getEditedSection().set("effects", null); 
/*  64 */       paramEditionInventory.registerTemplateEdition();
/*  65 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + str.substring(0, 1).toUpperCase() + str
/*  66 */           .substring(1).toLowerCase() + ChatColor.GRAY + ".");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  73 */     String[] arrayOfString = paramString.split("\\|");
/*  74 */     Validate.isTrue((arrayOfString.length > 1), FriendlyFeedbackProvider.quickForConsole((FriendlyFeedbackPalette)FFPMMOItems.get(), "Use this format: $e{Potion Effect Name}|{Duration Numeric Formula}|{Amplifier Numeric Formula}$b.", new String[0]));
/*     */     
/*  76 */     PotionEffectType potionEffectType = PotionEffectType.getByName(arrayOfString[0].replace("-", "_").replace(" ", "_").toUpperCase());
/*  77 */     Validate.notNull(potionEffectType, arrayOfString[0] + FriendlyFeedbackProvider.quickForConsole((FriendlyFeedbackPalette)FFPMMOItems.get(), " is not a valid potion effect. All potion effects can be found here:$e https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/potion/PotionEffectType.html", new String[0]));
/*     */     
/*  79 */     NumericStatFormula numericStatFormula1 = new NumericStatFormula(arrayOfString[1]);
/*  80 */     NumericStatFormula numericStatFormula2 = (arrayOfString.length > 2) ? new NumericStatFormula(arrayOfString[2]) : new NumericStatFormula(1.0D, 0.0D, 0.0D, 0.0D);
/*     */     
/*  82 */     numericStatFormula1.fillConfigurationSection(paramEditionInventory.getEditedSection(), "effects." + potionEffectType.getName() + ".duration");
/*  83 */     numericStatFormula2.fillConfigurationSection(paramEditionInventory.getEditedSection(), "effects." + potionEffectType.getName() + ".amplifier");
/*  84 */     paramEditionInventory.registerTemplateEdition();
/*  85 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + potionEffectType.getName() + " " + numericStatFormula2 + " successfully added.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<RandomPotionEffectListData> paramOptional) {
/*  90 */     paramOptional.ifPresentOrElse(paramRandomPotionEffectListData -> {
/*     */           paramList.add(ChatColor.GRAY + "Current Value:");
/*     */           
/*     */           for (RandomPotionEffectData randomPotionEffectData : paramRandomPotionEffectListData.getEffects()) {
/*     */             paramList.add(ChatColor.GRAY + "* " + ChatColor.GREEN + MMOUtils.caseOnWords(randomPotionEffectData.getType().getName().toLowerCase().replace("_", " ")) + ChatColor.GRAY + " Level: " + ChatColor.GREEN + randomPotionEffectData.getAmplifier() + ChatColor.GRAY + " Duration: " + ChatColor.GREEN + randomPotionEffectData.getDuration());
/*     */           }
/*     */         }() -> paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None"));
/*  97 */     paramList.add("");
/*  98 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add an effect.");
/*  99 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last effect.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PotionEffectListData getClearStatData() {
/* 105 */     return new PotionEffectListData(new PotionEffectData[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull PotionEffectListData paramPotionEffectListData) {
/* 111 */     ArrayList arrayList = new ArrayList();
/* 112 */     String str = ItemStat.translate("effect");
/* 113 */     paramPotionEffectListData.getEffects().forEach(paramPotionEffectData -> paramList.add(paramString.replace("{effect}", MMOItems.plugin.getLanguage().getPotionEffectName(paramPotionEffectData.getType()) + ((paramPotionEffectData.getLevel() < 2) ? "" : (" " + MMOUtils.intToRoman(paramPotionEffectData.getLevel())))).replace("{duration}", (MythicLib.plugin.getMMOConfig()).decimal.format(paramPotionEffectData.getDuration()))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     paramItemStackBuilder.getLore().insert("effects", arrayList);
/*     */ 
/*     */     
/* 121 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramPotionEffectListData));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull PotionEffectListData paramPotionEffectListData) {
/* 129 */     JsonArray jsonArray = new JsonArray();
/*     */ 
/*     */     
/* 132 */     for (PotionEffectData potionEffectData : paramPotionEffectListData.getEffects()) {
/*     */       
/* 134 */       JsonObject jsonObject = new JsonObject();
/* 135 */       jsonObject.addProperty("Type", potionEffectData.getType().getName());
/* 136 */       jsonObject.addProperty("Duration", Double.valueOf(potionEffectData.getDuration()));
/* 137 */       jsonObject.addProperty("Level", Integer.valueOf(potionEffectData.getLevel()));
/* 138 */       jsonArray.add((JsonElement)jsonObject);
/*     */     } 
/*     */ 
/*     */     
/* 142 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 143 */     arrayList.add(new ItemTag(getNBTPath(), jsonArray.toString()));
/*     */ 
/*     */     
/* 146 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 152 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 153 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 154 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/* 157 */     PotionEffectListData potionEffectListData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 160 */     if (potionEffectListData != null) {
/* 161 */       paramReadMMOItem.setData(this, (StatData)potionEffectListData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PotionEffectListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 169 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 172 */     if (itemTag == null) {
/* 173 */       return null;
/*     */     }
/*     */     try {
/* 176 */       PotionEffectListData potionEffectListData = new PotionEffectListData(new PotionEffectData[0]);
/*     */ 
/*     */       
/* 179 */       JsonArray jsonArray = (new JsonParser()).parse((String)itemTag.getValue()).getAsJsonArray();
/*     */ 
/*     */       
/* 182 */       for (JsonElement jsonElement : jsonArray) {
/*     */ 
/*     */         
/* 185 */         if (jsonElement.isJsonObject()) {
/*     */ 
/*     */           
/* 188 */           JsonObject jsonObject = jsonElement.getAsJsonObject();
/*     */           
/* 190 */           potionEffectListData.add(new PotionEffectData[] { new PotionEffectData(PotionEffectType.getByName(jsonObject
/* 191 */                     .get("Type").getAsString()), jsonObject
/* 192 */                   .get("Duration").getAsDouble(), jsonObject
/* 193 */                   .get("Level").getAsInt()) });
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 198 */       return potionEffectListData;
/*     */     }
/* 200 */     catch (JsonSyntaxException|IllegalStateException jsonSyntaxException) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 205 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onConsume(@NotNull VolatileMMOItem paramVolatileMMOItem, @NotNull Player paramPlayer, boolean paramBoolean) {
/* 211 */     if (!paramVolatileMMOItem.hasData(ItemStats.EFFECTS)) {
/*     */       return;
/*     */     }
/*     */     
/* 215 */     PotionEffectListData potionEffectListData = (PotionEffectListData)paramVolatileMMOItem.getData(ItemStats.EFFECTS);
/*     */ 
/*     */     
/* 218 */     for (PotionEffectData potionEffectData : potionEffectListData.getEffects()) {
/* 219 */       if (potionEffectData == null)
/*     */         continue; 
/* 221 */       paramPlayer.removePotionEffect(potionEffectData.getType());
/* 222 */       paramPlayer.addPotionEffect(potionEffectData.toEffect());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\Effects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */