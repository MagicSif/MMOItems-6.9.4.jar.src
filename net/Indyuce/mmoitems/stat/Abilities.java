/*     */ package net.Indyuce.mmoitems.stat;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.skill.trigger.TriggerType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.gui.edition.AbilityListEdition;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.skill.RegisteredSkill;
/*     */ import net.Indyuce.mmoitems.stat.data.AbilityData;
/*     */ import net.Indyuce.mmoitems.stat.data.AbilityListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomAbilityData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomAbilityListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class Abilities extends ItemStat<RandomAbilityListData, AbilityListData> {
/*     */   public Abilities() {
/*  37 */     super("ABILITY", Material.BLAZE_POWDER, "Item Abilities", new String[] { "Make your item cast amazing abilities", "to kill monsters or buff yourself." }, new String[] { "!block", "all" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomAbilityListData whenInitialized(Object paramObject) {
/*  43 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a valid config section");
/*  44 */     ConfigurationSection configurationSection = (ConfigurationSection)paramObject;
/*  45 */     RandomAbilityListData randomAbilityListData = new RandomAbilityListData(new RandomAbilityData[0]);
/*     */     
/*  47 */     for (String str : configurationSection.getKeys(false)) {
/*  48 */       randomAbilityListData.add(new RandomAbilityData[] { new RandomAbilityData(configurationSection.getConfigurationSection(str)) });
/*     */     } 
/*  50 */     return randomAbilityListData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull AbilityListData paramAbilityListData) {
/*  57 */     ArrayList arrayList = new ArrayList();
/*  58 */     boolean bool = !(MMOItems.plugin.getLanguage()).abilitySplitter.equals("") ? true : false;
/*     */     
/*  60 */     String str1 = ItemStat.translate("ability-modifier"), str2 = ItemStat.translate("ability-format");
/*     */     
/*  62 */     paramAbilityListData.getAbilities().forEach(paramAbilityData -> {
/*     */           paramList.add(paramString1.replace("{trigger}", MMOItems.plugin.getLanguage().getCastingModeName(paramAbilityData.getTrigger())).replace("{ability}", paramAbilityData.getAbility().getName()));
/*     */           
/*     */           for (String str : paramAbilityData.getModifiers()) {
/*     */             paramItemStackBuilder.getLore().registerPlaceholder("ability_" + paramAbilityData.getAbility().getHandler().getId().toLowerCase() + "_" + str, (MythicLib.plugin.getMMOConfig()).decimals.format(paramAbilityData.getParameter(str)));
/*     */             
/*     */             paramList.add(paramString2.replace("{modifier}", paramAbilityData.getAbility().getParameterName(str)).replace("{value}", (MythicLib.plugin.getMMOConfig()).decimals.format(paramAbilityData.getParameter(str))));
/*     */           } 
/*     */           
/*     */           if (paramBoolean) {
/*     */             paramList.add((MMOItems.plugin.getLanguage()).abilitySplitter);
/*     */           }
/*     */         });
/*     */     
/*  76 */     if (bool && arrayList.size() > 0) {
/*  77 */       arrayList.remove(arrayList.size() - 1);
/*     */     }
/*     */     
/*  80 */     paramItemStackBuilder.getLore().insert("abilities", arrayList);
/*  81 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramAbilityListData));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull AbilityListData paramAbilityListData) {
/*  89 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/*  92 */     JsonArray jsonArray = new JsonArray();
/*  93 */     for (AbilityData abilityData : paramAbilityListData.getAbilities()) jsonArray.add((JsonElement)abilityData.toJson());
/*     */ 
/*     */     
/*  96 */     arrayList.add(new ItemTag(getNBTPath(), jsonArray.toString()));
/*     */     
/*  98 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/* 103 */     (new AbilityListEdition(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited())).open(paramEditionInventory.getPage());
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 108 */     String str1 = (String)paramVarArgs[0];
/* 109 */     String str2 = (String)paramVarArgs[1];
/*     */     
/* 111 */     String str3 = paramString.toUpperCase().replace("-", "_").replace(" ", "_").replaceAll("[^A-Z0-9_]", "");
/*     */     
/* 113 */     if (str2.equals("ability")) {
/* 114 */       Validate.isTrue(MMOItems.plugin.getSkills().hasSkill(str3), "format is not a valid ability! You may check the ability list using /mi list ability.");
/*     */       
/* 116 */       RegisteredSkill registeredSkill = MMOItems.plugin.getSkills().getSkill(str3);
/*     */       
/* 118 */       paramEditionInventory.getEditedSection().set("ability." + str1, null);
/* 119 */       paramEditionInventory.getEditedSection().set("ability." + str1 + ".type", str3);
/* 120 */       paramEditionInventory.registerTemplateEdition();
/* 121 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin
/* 122 */           .getPrefix() + "Successfully set the ability to " + ChatColor.GOLD + registeredSkill.getName() + ChatColor.GRAY + ".");
/*     */       
/*     */       return;
/*     */     } 
/* 126 */     if (str2.equals("mode")) {
/*     */       
/* 128 */       TriggerType triggerType = TriggerType.valueOf(str3);
/*     */       
/* 130 */       paramEditionInventory.getEditedSection().set("ability." + str1 + ".mode", triggerType.name());
/* 131 */       paramEditionInventory.registerTemplateEdition();
/* 132 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully set the trigger to " + ChatColor.GOLD + triggerType.getName() + ChatColor.GRAY + ".");
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 137 */     (new NumericStatFormula(paramString)).fillConfigurationSection(paramEditionInventory.getEditedSection(), "ability." + str1 + "." + str2, NumericStatFormula.FormulaSaveOption.NONE);
/*     */     
/* 139 */     paramEditionInventory.registerTemplateEdition();
/* 140 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + ChatColor.GOLD + MMOUtils.caseOnWords(str2.replace("-", " ")) + ChatColor.GRAY + " successfully added.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<RandomAbilityListData> paramOptional) {
/* 146 */     paramList.add(ChatColor.GRAY + "Current Abilities: " + ChatColor.GOLD + (
/* 147 */         paramOptional.isPresent() ? ((RandomAbilityListData)paramOptional.get()).getAbilities().size() : 0));
/* 148 */     paramList.add("");
/* 149 */     paramList.add(ChatColor.YELLOW + "►" + " Click to edit the item abilities.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public AbilityListData getClearStatData() {
/* 155 */     return new AbilityListData(new AbilityData[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 162 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */     
/* 164 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 165 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/* 167 */     AbilityListData abilityListData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 170 */     if (abilityListData != null)
/*     */     {
/*     */       
/* 173 */       paramReadMMOItem.setData(this, (StatData)abilityListData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AbilityListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 182 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 185 */     if (itemTag != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 191 */         AbilityListData abilityListData = new AbilityListData(new AbilityData[0]);
/* 192 */         JsonArray jsonArray = (new JsonParser()).parse((String)itemTag.getValue()).getAsJsonArray();
/*     */ 
/*     */         
/* 195 */         for (JsonElement jsonElement : jsonArray) {
/*     */ 
/*     */           
/* 198 */           if (jsonElement.isJsonObject()) {
/*     */ 
/*     */             
/* 201 */             JsonObject jsonObject = jsonElement.getAsJsonObject();
/*     */ 
/*     */             
/* 204 */             abilityListData.add(new AbilityData[] { new AbilityData(jsonObject) });
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 209 */         return abilityListData;
/*     */       }
/* 211 */       catch (JsonSyntaxException|IllegalStateException jsonSyntaxException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     return null;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\Abilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */