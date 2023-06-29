/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
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
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class CompatibleMaterials
/*     */   extends ItemStat<StringListData, StringListData>
/*     */ {
/*     */   public CompatibleMaterials() {
/*  36 */     super("COMPATIBLE_MATERIALS", VersionMaterial.COMMAND_BLOCK.toMaterial(), "Compatible Materials", new String[] { "The item materials this skin is", "compatible with." }, new String[] { "skin" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringListData whenInitialized(Object paramObject) {
/*  43 */     Validate.isTrue(paramObject instanceof List, "Must specify a string list");
/*  44 */     return new StringListData((List)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  49 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  50 */       (new StatEdition(paramEditionInventory, ItemStats.COMPATIBLE_MATERIALS, new Object[0])).enable(new String[] { "Write in the chat the name of the material you want to add." });
/*     */     }
/*  52 */     if (paramInventoryClickEvent.getAction() != InventoryAction.PICKUP_HALF || !paramEditionInventory.getEditedSection().contains("compatible-materials"))
/*     */       return; 
/*  54 */     List<String> list = paramEditionInventory.getEditedSection().getStringList("compatible-materials");
/*  55 */     if (list.size() < 1) {
/*     */       return;
/*     */     }
/*  58 */     String str = list.get(list.size() - 1);
/*  59 */     list.remove(str);
/*  60 */     paramEditionInventory.getEditedSection().set("compatible-materials", list);
/*  61 */     paramEditionInventory.registerTemplateEdition();
/*  62 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed '" + str + "'.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  67 */     Player player = paramEditionInventory.getPlayer();
/*     */     
/*  69 */     if (Arrays.<Material>stream(Material.values()).noneMatch(paramMaterial -> paramMaterial.name().equalsIgnoreCase(paramString))) {
/*  70 */       player.sendMessage(MMOItems.plugin.getPrefix() + "Invalid material name.");
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  75 */     List<String> list = paramEditionInventory.getEditedSection().contains("compatible-materials") ? paramEditionInventory.getEditedSection().getStringList("compatible-materials") : new ArrayList();
/*  76 */     list.add(paramString.toUpperCase());
/*  77 */     paramEditionInventory.getEditedSection().set("compatible-materials", list);
/*  78 */     paramEditionInventory.registerTemplateEdition();
/*  79 */     player.sendMessage(MMOItems.plugin.getPrefix() + "Compatible Materials successfully added.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<StringListData> paramOptional) {
/*  84 */     if (paramOptional.isPresent()) {
/*  85 */       paramList.add(ChatColor.GRAY + "Current Value:");
/*  86 */       ((StringListData)paramOptional.get()).getList().forEach(paramString -> paramList.add(ChatColor.GRAY + paramString));
/*     */     } else {
/*  88 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "Compatible with any material.");
/*     */     } 
/*  90 */     paramList.add("");
/*  91 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add a new material.");
/*  92 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last material.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StringListData getClearStatData() {
/*  98 */     return new StringListData();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringListData paramStringListData) {
/* 104 */     ArrayList arrayList = new ArrayList(paramStringListData.getList());
/* 105 */     paramItemStackBuilder.getLore().insert("compatible-materials", arrayList);
/*     */ 
/*     */     
/* 108 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramStringListData));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull StringListData paramStringListData) {
/* 115 */     JsonArray jsonArray = new JsonArray();
/*     */ 
/*     */     
/* 118 */     for (String str : paramStringListData.getList()) {
/* 119 */       jsonArray.add(str);
/*     */     }
/*     */ 
/*     */     
/* 123 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 126 */     arrayList.add(new ItemTag(getNBTPath(), jsonArray.toString()));
/*     */     
/* 128 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 134 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 135 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 136 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/* 139 */     StringListData stringListData = getLoadedNBT(arrayList);
/*     */     
/* 141 */     if (stringListData != null) {
/* 142 */       paramReadMMOItem.setData(this, (StatData)stringListData);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public StringListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 149 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 152 */     if (itemTag == null)
/*     */     {
/* 154 */       return null;
/*     */     }
/*     */     
/*     */     try {
/* 158 */       JsonArray jsonArray = (new JsonParser()).parse((String)itemTag.getValue()).getAsJsonArray();
/*     */ 
/*     */       
/* 161 */       return new StringListData(jsonArray);
/* 162 */     } catch (JsonSyntaxException|IllegalStateException jsonSyntaxException) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 167 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\CompatibleMaterials.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */