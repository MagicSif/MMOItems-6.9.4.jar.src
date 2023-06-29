/*     */ package net.Indyuce.mmoitems.stat;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.interaction.projectile.ArrowPotionEffectArrayItem;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
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
/*     */ public class ArrowPotionEffects extends ItemStat<RandomPotionEffectListData, PotionEffectListData> {
/*     */   public ArrowPotionEffects() {
/*  37 */     super("ARROW_POTION_EFFECTS", Material.TIPPED_ARROW, "Arrow Potion Effects", new String[] { "The effects to be applied when", "entities are shot by this bow" }, new String[] { "bow", "crossbow" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomPotionEffectListData whenInitialized(Object paramObject) {
/*  43 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a config section");
/*  44 */     return new RandomPotionEffectListData((ConfigurationSection)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  49 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  50 */       (new StatEdition(paramEditionInventory, ItemStats.ARROW_POTION_EFFECTS, new Object[0])).enable(new String[] { "Write in the chat the potion effect you want to add.", ChatColor.AQUA + "Format: [POTION_EFFECT] [DURATION] [AMPLIFIER]" });
/*     */     }
/*     */     
/*  53 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/*  54 */       paramEditionInventory.getEditedSection().contains("arrow-potion-effects")) {
/*  55 */       Set set = paramEditionInventory.getEditedSection().getConfigurationSection("arrow-potion-effects").getKeys(false);
/*  56 */       String str = Arrays.<String>asList((String[])set.toArray((Object[])new String[0])).get(set.size() - 1);
/*  57 */       paramEditionInventory.getEditedSection().set("arrow-potion-effects." + str, null);
/*  58 */       if (set.size() <= 1)
/*  59 */         paramEditionInventory.getEditedSection().set("arrow-potion-effects", null); 
/*  60 */       paramEditionInventory.registerTemplateEdition();
/*  61 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + str.substring(0, 1).toUpperCase() + str
/*  62 */           .substring(1).toLowerCase() + ChatColor.GRAY + ".");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  69 */     String[] arrayOfString = paramString.split(" ");
/*  70 */     Validate.isTrue((arrayOfString.length == 3), paramString + " is not a valid [POTION_EFFECT] [DURATION] [AMPLIFIER]. Example: 'FAST_DIGGING 30 3' stands for Haste 3 for 30 seconds.");
/*     */ 
/*     */     
/*  73 */     PotionEffectType potionEffectType = null;
/*  74 */     for (PotionEffectType potionEffectType1 : PotionEffectType.values()) {
/*  75 */       if (potionEffectType1 != null && 
/*  76 */         potionEffectType1.getName().equalsIgnoreCase(arrayOfString[0].replace("-", "_"))) {
/*  77 */         potionEffectType = potionEffectType1; break;
/*     */       } 
/*     */     } 
/*  80 */     Validate.notNull(potionEffectType, arrayOfString[0] + " is not a valid potion effect.");
/*     */     
/*  82 */     double d = MMOUtils.parseDouble(arrayOfString[1]);
/*  83 */     int i = (int)MMOUtils.parseDouble(arrayOfString[2]);
/*     */     
/*  85 */     ConfigurationSection configurationSection = paramEditionInventory.getEditedSection().createSection("arrow-potion-effects." + potionEffectType.getName());
/*  86 */     configurationSection.set("duration", Double.valueOf(d));
/*  87 */     configurationSection.set("amplifier", Integer.valueOf(i));
/*  88 */     paramEditionInventory.getEditedSection().set("arrow-potion-effects." + potionEffectType.getName(), configurationSection);
/*  89 */     paramEditionInventory.registerTemplateEdition();
/*  90 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + potionEffectType.getName() + " " + i + " successfully added.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<RandomPotionEffectListData> paramOptional) {
/*  95 */     if (paramOptional.isPresent()) {
/*  96 */       paramList.add(ChatColor.GRAY + "Current Value:");
/*  97 */       RandomPotionEffectListData randomPotionEffectListData = paramOptional.get();
/*  98 */       for (RandomPotionEffectData randomPotionEffectData : randomPotionEffectListData.getEffects())
/*  99 */         paramList.add(ChatColor.GRAY + "* " + ChatColor.GREEN + MMOUtils.caseOnWords(randomPotionEffectData.getType().getName().toLowerCase().replace("_", " ")) + ChatColor.GRAY + " Level: " + ChatColor.GREEN + randomPotionEffectData
/* 100 */             .getAmplifier() + ChatColor.GRAY + " Duration: " + ChatColor.GREEN + randomPotionEffectData
/* 101 */             .getDuration()); 
/*     */     } else {
/* 103 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*     */     } 
/* 105 */     paramList.add("");
/* 106 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add an effect.");
/* 107 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last effect.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PotionEffectListData getClearStatData() {
/* 113 */     return new PotionEffectListData(new PotionEffectData[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull PotionEffectListData paramPotionEffectListData) {
/* 119 */     ArrayList arrayList = new ArrayList();
/*     */     
/* 121 */     String str = ItemStat.translate("arrow-potion-effects");
/* 122 */     paramPotionEffectListData.getEffects().forEach(paramPotionEffectData -> paramList.add(paramString.replace("{effect}", MMOItems.plugin.getLanguage().getPotionEffectName(paramPotionEffectData.getType()) + " " + MMOUtils.intToRoman(paramPotionEffectData.getLevel()) + "(" + (MythicLib.plugin.getMMOConfig()).decimal.format(paramPotionEffectData.getDuration()) + "s)")));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     paramItemStackBuilder.getLore().insert("arrow-potion-effects", arrayList);
/*     */ 
/*     */     
/* 130 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramPotionEffectListData));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull PotionEffectListData paramPotionEffectListData) {
/* 138 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 141 */     JsonArray jsonArray = new JsonArray();
/*     */ 
/*     */     
/* 144 */     paramPotionEffectListData.getEffects().forEach(paramPotionEffectData -> {
/*     */           JsonObject jsonObject = new JsonObject();
/*     */           
/*     */           jsonObject.addProperty("type", paramPotionEffectData.getType().getName());
/*     */           
/*     */           jsonObject.addProperty("level", Integer.valueOf(paramPotionEffectData.getLevel()));
/*     */           
/*     */           jsonObject.addProperty("duration", Double.valueOf(paramPotionEffectData.getDuration()));
/*     */           
/*     */           paramJsonArray.add((JsonElement)jsonObject);
/*     */         });
/* 155 */     arrayList.add(new ItemTag(getNBTPath(), jsonArray.toString()));
/*     */ 
/*     */     
/* 158 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 165 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 166 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 167 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/* 170 */     PotionEffectListData potionEffectListData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 173 */     if (potionEffectListData != null) paramReadMMOItem.setData(this, (StatData)potionEffectListData);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PotionEffectListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 181 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 184 */     if (itemTag != null) {
/*     */ 
/*     */       
/* 187 */       PotionEffectListData potionEffectListData = new PotionEffectListData(new PotionEffectData[0]);
/*     */ 
/*     */       
/* 190 */       for (ArrowPotionEffectArrayItem arrowPotionEffectArrayItem : (ArrowPotionEffectArrayItem[])MythicLib.plugin.getJson().parse((String)itemTag.getValue(), ArrowPotionEffectArrayItem[].class)) {
/* 191 */         potionEffectListData.add(new PotionEffectData[] { new PotionEffectData(PotionEffectType.getByName(arrowPotionEffectArrayItem.type), arrowPotionEffectArrayItem.duration, arrowPotionEffectArrayItem.level) });
/*     */       } 
/*     */ 
/*     */       
/* 195 */       return potionEffectListData;
/*     */     } 
/*     */     
/* 198 */     return null;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\ArrowPotionEffects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */