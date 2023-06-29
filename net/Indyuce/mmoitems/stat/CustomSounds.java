/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.CustomSound;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.gui.edition.SoundsEdition;
/*     */ import net.Indyuce.mmoitems.stat.data.SoundData;
/*     */ import net.Indyuce.mmoitems.stat.data.SoundListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.PlayerConsumable;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class CustomSounds extends ItemStat<SoundListData, SoundListData> implements GemStoneStat, PlayerConsumable {
/*     */   public CustomSounds() {
/*  37 */     super("SOUNDS", Material.JUKEBOX, "Custom Sounds", new String[] { "The custom sounds your item will use." }, new String[] { "all" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundListData whenInitialized(Object paramObject) {
/*  43 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a config section");
/*  44 */     ConfigurationSection configurationSection = (ConfigurationSection)paramObject;
/*     */     
/*  46 */     SoundListData soundListData = new SoundListData();
/*     */     
/*  48 */     for (CustomSound customSound : CustomSound.values()) {
/*  49 */       String str = customSound.name().replace("_", "-").toLowerCase();
/*  50 */       if (configurationSection.contains(str)) {
/*  51 */         soundListData.set(customSound, new SoundData(configurationSection.get(str)));
/*     */       }
/*     */     } 
/*  54 */     return soundListData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  59 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  60 */       (new SoundsEdition(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited())).open(paramEditionInventory.getPage());
/*     */     }
/*  62 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/*  63 */       paramEditionInventory.getEditedSection().contains("sounds")) {
/*  64 */       paramEditionInventory.getEditedSection().set("sounds", null);
/*  65 */       paramEditionInventory.registerTemplateEdition();
/*  66 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Custom Sounds successfully removed.");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  72 */     String str1 = (String)paramVarArgs[0];
/*  73 */     String[] arrayOfString = paramString.split(" ");
/*  74 */     Validate.isTrue((arrayOfString.length == 3), paramString + " is not a valid [SOUND NAME] [VOLUME] [PITCH].");
/*     */     
/*  76 */     String str2 = arrayOfString[0].replace("-", "_");
/*  77 */     double d1 = MMOUtils.parseDouble(arrayOfString[1]);
/*  78 */     double d2 = MMOUtils.parseDouble(arrayOfString[2]);
/*     */     
/*  80 */     paramEditionInventory.getEditedSection().set("sounds." + str1 + ".sound", str2);
/*  81 */     paramEditionInventory.getEditedSection().set("sounds." + str1 + ".volume", Double.valueOf(d1));
/*  82 */     paramEditionInventory.getEditedSection().set("sounds." + str1 + ".pitch", Double.valueOf(d2));
/*     */     
/*  84 */     paramEditionInventory.registerTemplateEdition();
/*  85 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + MMOUtils.caseOnWords(str1.replace(".", " ")) + ChatColor.GRAY + " successfully changed to '" + str2 + "'.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<SoundListData> paramOptional) {
/*  92 */     if (paramOptional.isPresent()) {
/*  93 */       paramList.add(ChatColor.GRAY + "Current Value:");
/*  94 */       SoundListData soundListData = paramOptional.get();
/*  95 */       soundListData.mapData()
/*  96 */         .forEach((paramCustomSound, paramSoundData) -> paramList.add(ChatColor.GRAY + "* " + ChatColor.GREEN + MMOUtils.caseOnWords(paramCustomSound.getName().toLowerCase().replace("-", " ").replace("_", " ")) + ChatColor.GRAY + ": " + ChatColor.RED + paramSoundData.getVolume() + " " + paramSoundData.getPitch()));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 101 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*     */     } 
/* 103 */     paramList.add("");
/* 104 */     paramList.add(ChatColor.YELLOW + "►" + " Click to access the sounds edition menu.");
/* 105 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove all custom sounds.");
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public SoundListData getClearStatData() {
/* 110 */     return new SoundListData();
/*     */   }
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull SoundListData paramSoundListData) {
/* 114 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramSoundListData));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull SoundListData paramSoundListData) {
/* 120 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 123 */     SoundListData soundListData = paramSoundListData;
/*     */ 
/*     */     
/* 126 */     soundListData.getCustomSounds().forEach(paramCustomSound -> {
/*     */           SoundData soundData = paramSoundListData.get(paramCustomSound);
/*     */           
/*     */           paramArrayList.add(new ItemTag("MMOITEMS_SOUND_" + paramCustomSound.name(), soundData.getSound()));
/*     */           
/*     */           paramArrayList.add(new ItemTag("MMOITEMS_SOUND_" + paramCustomSound.name() + "_VOL", Double.valueOf(soundData.getVolume())));
/*     */           
/*     */           paramArrayList.add(new ItemTag("MMOITEMS_SOUND_" + paramCustomSound.name() + "_PIT", Double.valueOf(soundData.getPitch())));
/*     */         });
/* 135 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public SoundListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 141 */     SoundListData soundListData = new SoundListData();
/*     */ 
/*     */     
/* 144 */     for (CustomSound customSound : CustomSound.values()) {
/*     */ 
/*     */       
/* 147 */       ItemTag itemTag1 = ItemTag.getTagAtPath("MMOITEMS_SOUND_" + customSound.name(), paramArrayList);
/* 148 */       ItemTag itemTag2 = ItemTag.getTagAtPath("MMOITEMS_SOUND_" + customSound.name() + "_VOL", paramArrayList);
/* 149 */       ItemTag itemTag3 = ItemTag.getTagAtPath("MMOITEMS_SOUND_" + customSound.name() + "_PIT", paramArrayList);
/*     */ 
/*     */       
/* 152 */       if (itemTag1 != null && itemTag2 != null && itemTag3 != null) {
/*     */ 
/*     */         
/* 155 */         String str = (String)itemTag1.getValue();
/* 156 */         Double double_1 = (Double)itemTag2.getValue();
/* 157 */         Double double_2 = (Double)itemTag3.getValue();
/*     */ 
/*     */         
/* 160 */         if (!str.isEmpty())
/*     */         {
/*     */           
/* 163 */           soundListData.set(customSound, new SoundData(str, double_1.doubleValue(), double_2.doubleValue()));
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 169 */     if (soundListData.getCustomSounds().size() > 0)
/*     */     {
/*     */       
/* 172 */       return soundListData;
/*     */     }
/*     */ 
/*     */     
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 183 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */     
/* 185 */     for (CustomSound customSound : CustomSound.values()) {
/*     */ 
/*     */       
/* 188 */       ItemTag itemTag1 = null;
/* 189 */       ItemTag itemTag2 = null;
/* 190 */       ItemTag itemTag3 = null;
/*     */       
/* 192 */       if (paramReadMMOItem.getNBT().hasTag("MMOITEMS_SOUND_" + customSound.name())) {
/* 193 */         itemTag1 = ItemTag.getTagAtPath("MMOITEMS_SOUND_" + customSound.name(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING);
/*     */       }
/* 195 */       if (paramReadMMOItem.getNBT().hasTag("MMOITEMS_SOUND_" + customSound.name() + "_VOL")) {
/* 196 */         itemTag2 = ItemTag.getTagAtPath("MMOITEMS_SOUND_" + customSound.name() + "_VOL", paramReadMMOItem.getNBT(), SupportedNBTTagValues.DOUBLE);
/*     */       }
/* 198 */       if (paramReadMMOItem.getNBT().hasTag("MMOITEMS_SOUND_" + customSound.name() + "_PIT")) {
/* 199 */         itemTag3 = ItemTag.getTagAtPath("MMOITEMS_SOUND_" + customSound.name() + "_PIT", paramReadMMOItem.getNBT(), SupportedNBTTagValues.DOUBLE);
/*     */       }
/*     */       
/* 202 */       if (itemTag1 != null && itemTag2 != null && itemTag3 != null) {
/*     */ 
/*     */         
/* 205 */         arrayList.add(itemTag1);
/* 206 */         arrayList.add(itemTag2);
/* 207 */         arrayList.add(itemTag3);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 212 */     SoundListData soundListData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 215 */     if (soundListData != null)
/*     */     {
/*     */       
/* 218 */       paramReadMMOItem.setData(ItemStats.CUSTOM_SOUNDS, (StatData)soundListData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onConsume(@NotNull VolatileMMOItem paramVolatileMMOItem, @NotNull Player paramPlayer, boolean paramBoolean) {
/* 226 */     if (!paramVolatileMMOItem.hasData(ItemStats.CUSTOM_SOUNDS)) {
/* 227 */       playDefaultSound(paramPlayer);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 232 */     SoundListData soundListData = (SoundListData)paramVolatileMMOItem.getData(ItemStats.CUSTOM_SOUNDS);
/* 233 */     SoundData soundData = soundListData.get(CustomSound.ON_CONSUME);
/*     */ 
/*     */     
/* 236 */     if (soundData == null) { playDefaultSound(paramPlayer); }
/*     */     
/*     */     else
/*     */     
/* 240 */     { String str = soundData.getSound().toLowerCase().replace("_", ".");
/* 241 */       paramPlayer.getWorld().playSound(paramPlayer.getLocation(), str, (float)soundData.getVolume(), (float)soundData.getPitch()); }
/*     */   
/*     */   }
/*     */   void playDefaultSound(@NotNull Player paramPlayer) {
/* 245 */     paramPlayer.getWorld().playSound(paramPlayer.getLocation(), Sound.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\CustomSounds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */