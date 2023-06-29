/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.GemSocketsData;
/*     */ import net.Indyuce.mmoitems.stat.data.GemstoneData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class GemSockets
/*     */   extends ItemStat<GemSocketsData, GemSocketsData>
/*     */ {
/*     */   public GemSockets() {
/*  36 */     super("GEM_SOCKETS", Material.EMERALD, "Gem Sockets", new String[] { "The amount of gem", "sockets your weapon has." }, new String[] { "piercing", "slashing", "blunt", "catalyst", "range", "tool", "armor", "accessory", "!gem_stone" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GemSocketsData whenInitialized(Object paramObject) {
/*  43 */     Validate.isTrue(paramObject instanceof List, "Must specify a string list");
/*  44 */     return new GemSocketsData((List)paramObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull GemSocketsData paramGemSocketsData) {
/*  51 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramGemSocketsData));
/*     */ 
/*     */     
/*  54 */     String str1 = ItemStat.translate("empty-gem-socket"), str2 = ItemStat.translate("filled-gem-socket");
/*  55 */     ArrayList<String> arrayList = new ArrayList();
/*  56 */     for (GemstoneData gemstoneData : paramGemSocketsData.getGemstones()) {
/*  57 */       String str = gemstoneData.getName();
/*     */ 
/*     */       
/*  60 */       if (paramItemStackBuilder.getMMOItem().hasUpgradeTemplate()) {
/*     */         
/*  62 */         int i = paramItemStackBuilder.getMMOItem().getUpgradeLevel();
/*  63 */         if (i != 0) {
/*     */           
/*  65 */           Integer integer = gemstoneData.getLevel();
/*     */           
/*  67 */           if (integer != null) {
/*     */             
/*  69 */             int j = i - integer.intValue();
/*     */             
/*  71 */             str = DisplayName.appendUpgradeLevel(str, j);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  76 */       arrayList.add(str2.replace("{name}", str));
/*     */     } 
/*  78 */     paramGemSocketsData.getEmptySlots().forEach(paramString2 -> paramList.add(paramString1.replace("{name}", paramString2)));
/*  79 */     paramItemStackBuilder.getLore().insert("gem-stones", arrayList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull GemSocketsData paramGemSocketsData) {
/*  87 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  88 */     arrayList.add(new ItemTag(getNBTPath(), paramGemSocketsData.toJson().toString()));
/*     */ 
/*     */     
/*  91 */     return arrayList;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getNBTPath() {
/*  96 */     return "MMOITEMS_GEM_STONES";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 104 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 105 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 106 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/* 109 */     GemSocketsData gemSocketsData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 112 */     if (gemSocketsData != null) paramReadMMOItem.setData(this, (StatData)gemSocketsData);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GemSocketsData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 120 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 123 */     if (itemTag != null) {
/*     */       
/*     */       try {
/*     */         
/* 127 */         JsonObject jsonObject = (new JsonParser()).parse((String)itemTag.getValue()).getAsJsonObject();
/* 128 */         GemSocketsData gemSocketsData = new GemSocketsData(jsonObject.getAsJsonArray("EmptySlots"));
/*     */         
/* 130 */         JsonArray jsonArray = jsonObject.getAsJsonArray("Gemstones");
/* 131 */         jsonArray.forEach(paramJsonElement -> paramGemSocketsData.add(new GemstoneData(paramJsonElement.getAsJsonObject())));
/*     */ 
/*     */         
/* 134 */         return gemSocketsData;
/*     */       }
/* 136 */       catch (JsonSyntaxException|IllegalStateException jsonSyntaxException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/* 149 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 150 */       (new StatEdition(paramEditionInventory, ItemStats.GEM_SOCKETS, new Object[0])).enable(new String[] { "Write in the chat the COLOR of the gem socket you want to add." });
/*     */     }
/* 152 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/* 153 */       paramEditionInventory.getEditedSection().contains(getPath())) {
/* 154 */       List<String> list = paramEditionInventory.getEditedSection().getStringList("" + getPath());
/* 155 */       if (list.size() < 1) {
/*     */         return;
/*     */       }
/* 158 */       String str = list.get(list.size() - 1);
/* 159 */       list.remove(str);
/* 160 */       paramEditionInventory.getEditedSection().set("" + getPath(), list);
/* 161 */       paramEditionInventory.registerTemplateEdition();
/* 162 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed '" + str + ChatColor.GRAY + "'.");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 169 */     List<String> list = paramEditionInventory.getEditedSection().contains(getPath()) ? paramEditionInventory.getEditedSection().getStringList("" + getPath()) : new ArrayList();
/* 170 */     list.add(paramString);
/* 171 */     paramEditionInventory.getEditedSection().set("" + getPath(), list);
/* 172 */     paramEditionInventory.registerTemplateEdition();
/* 173 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + paramString + " successfully added.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<GemSocketsData> paramOptional) {
/* 179 */     if (paramOptional.isPresent()) {
/* 180 */       paramList.add(ChatColor.GRAY + "Current Value:");
/* 181 */       GemSocketsData gemSocketsData = paramOptional.get();
/* 182 */       gemSocketsData.getEmptySlots().forEach(paramString -> paramList.add(ChatColor.GRAY + "* " + ChatColor.GREEN + paramString + " Gem Socket"));
/*     */     } else {
/*     */       
/* 185 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "No Sockets");
/*     */     } 
/* 187 */     paramList.add("");
/* 188 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add a gem socket.");
/* 189 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the socket.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public GemSocketsData getClearStatData() {
/* 195 */     return new GemSocketsData(new ArrayList());
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\GemSockets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */