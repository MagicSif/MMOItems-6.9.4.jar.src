/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.StringListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class CompatibleTypes
/*     */   extends ItemStat<StringListData, StringListData> {
/*     */   public CompatibleTypes() {
/*  32 */     super("COMPATIBLE_TYPES", VersionMaterial.COMMAND_BLOCK.toMaterial(), "Compatible Types", new String[] { "The item types this skin is", "compatible with." }, new String[] { "skin" }, new org.bukkit.Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringListData whenInitialized(Object paramObject) {
/*  39 */     Validate.isTrue(paramObject instanceof List, "Must specify a string list");
/*  40 */     return new StringListData((List)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  45 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  46 */       (new StatEdition(paramEditionInventory, ItemStats.COMPATIBLE_TYPES, new Object[0])).enable(new String[] { "Write in the chat the name of the type you want to add." });
/*     */     }
/*  48 */     if (paramInventoryClickEvent.getAction() != InventoryAction.PICKUP_HALF || !paramEditionInventory.getEditedSection().contains("compatible-types"))
/*     */       return; 
/*  50 */     List<String> list = paramEditionInventory.getEditedSection().getStringList("compatible-types");
/*  51 */     if (list.size() < 1) {
/*     */       return;
/*     */     }
/*  54 */     String str = list.get(list.size() - 1);
/*  55 */     list.remove(str);
/*  56 */     paramEditionInventory.getEditedSection().set("compatible-types", list);
/*  57 */     paramEditionInventory.registerTemplateEdition();
/*  58 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed '" + str + "'.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  64 */     List<String> list = paramEditionInventory.getEditedSection().contains("compatible-types") ? paramEditionInventory.getEditedSection().getStringList("compatible-types") : new ArrayList();
/*  65 */     list.add(paramString.toUpperCase());
/*  66 */     paramEditionInventory.getEditedSection().set("compatible-types", list);
/*  67 */     paramEditionInventory.registerTemplateEdition();
/*  68 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Compatible Types successfully added.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<StringListData> paramOptional) {
/*  73 */     if (paramOptional.isPresent()) {
/*  74 */       paramList.add(ChatColor.GRAY + "Current Value:");
/*  75 */       ((StringListData)paramOptional.get()).getList().forEach(paramString -> paramList.add(ChatColor.GRAY + paramString));
/*     */     } else {
/*  77 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "Compatible with any item.");
/*     */     } 
/*  79 */     paramList.add("");
/*  80 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add a new type.");
/*  81 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last type.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StringListData getClearStatData() {
/*  87 */     return new StringListData();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringListData paramStringListData) {
/*  93 */     ArrayList arrayList = new ArrayList(paramStringListData.getList());
/*  94 */     paramItemStackBuilder.getLore().insert("compatible-types", arrayList);
/*     */ 
/*     */     
/*  97 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramStringListData));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull StringListData paramStringListData) {
/* 104 */     JsonArray jsonArray = new JsonArray();
/*     */ 
/*     */     
/* 107 */     for (String str : paramStringListData.getList()) {
/* 108 */       jsonArray.add(str);
/*     */     }
/*     */ 
/*     */     
/* 112 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 115 */     arrayList.add(new ItemTag(getNBTPath(), jsonArray.toString()));
/*     */     
/* 117 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 123 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 124 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 125 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/* 128 */     StringListData stringListData = getLoadedNBT(arrayList);
/*     */     
/* 130 */     if (stringListData != null) {
/* 131 */       paramReadMMOItem.setData(this, (StatData)stringListData);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public StringListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 138 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 141 */     if (itemTag == null)
/*     */     {
/* 143 */       return null;
/*     */     }
/*     */     
/*     */     try {
/* 147 */       JsonArray jsonArray = (new JsonParser()).parse((String)itemTag.getValue()).getAsJsonArray();
/*     */ 
/*     */       
/* 150 */       return new StringListData(jsonArray);
/* 151 */     } catch (JsonSyntaxException|IllegalStateException jsonSyntaxException) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 156 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\CompatibleTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */