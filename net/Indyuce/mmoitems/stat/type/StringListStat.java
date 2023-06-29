/*     */ package net.Indyuce.mmoitems.stat.type;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.StringListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class StringListStat
/*     */   extends ItemStat<StringListData, StringListData> {
/*     */   public StringListStat(String paramString1, Material paramMaterial, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, Material... paramVarArgs) {
/*  31 */     super(paramString1, paramMaterial, paramString2, paramArrayOfString1, paramArrayOfString2, paramVarArgs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public StringListData whenInitialized(Object paramObject) {
/*  37 */     Validate.isTrue(paramObject instanceof List, "Must specify a string list");
/*  38 */     return new StringListData((List)paramObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringListData paramStringListData) {
/*  45 */     if (!(paramStringListData instanceof StringListData))
/*  46 */       return;  if (paramStringListData.getList().size() == 0) {
/*     */       return;
/*     */     }
/*  49 */     String str1 = String.join(", ", paramStringListData.getList());
/*  50 */     String str2 = MMOItems.plugin.getLanguage().getStatFormat(getPath());
/*  51 */     String str3 = str2.replace("{value}", str1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     paramItemStackBuilder.getLore().insert(getPath(), new String[] { str3 });
/* 132 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramStringListData));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull StringListData paramStringListData) {
/* 140 */     JsonArray jsonArray = new JsonArray();
/*     */ 
/*     */     
/* 143 */     for (String str : paramStringListData.getList())
/*     */     {
/*     */       
/* 146 */       jsonArray.add(str);
/*     */     }
/*     */ 
/*     */     
/* 150 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 153 */     arrayList.add(new ItemTag(getNBTPath(), jsonArray.toString()));
/*     */ 
/*     */     
/* 156 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/* 161 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 162 */       (new StatEdition(paramEditionInventory, this, new Object[0])).enable(new String[] { "Write in the chat the line you want to add." });
/*     */     }
/* 164 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && paramEditionInventory.getEditedSection().contains(getPath())) {
/* 165 */       List<String> list = paramEditionInventory.getEditedSection().getStringList(getPath());
/* 166 */       if (list.isEmpty()) {
/*     */         return;
/*     */       }
/* 169 */       String str = list.get(list.size() - 1);
/* 170 */       list.remove(str);
/* 171 */       paramEditionInventory.getEditedSection().set(getPath(), list.isEmpty() ? null : list);
/* 172 */       paramEditionInventory.registerTemplateEdition();
/* 173 */       paramEditionInventory.getPlayer()
/* 174 */         .sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed '" + MythicLib.plugin.parseColors(str) + ChatColor.GRAY + "'.");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 180 */     List<String> list = paramEditionInventory.getEditedSection().contains(getPath()) ? paramEditionInventory.getEditedSection().getStringList(getPath()) : new ArrayList();
/* 181 */     list.add(paramString);
/* 182 */     paramEditionInventory.getEditedSection().set(getPath(), list);
/* 183 */     paramEditionInventory.registerTemplateEdition();
/* 184 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + getName() + " Stat successfully added.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 191 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 192 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 193 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/* 196 */     StringListData stringListData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 199 */     if (stringListData != null) paramReadMMOItem.setData(this, (StatData)stringListData);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public StringListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 207 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 210 */     if (itemTag != null) {
/*     */       
/*     */       try {
/*     */         
/* 214 */         String[] arrayOfString = (String[])(new Gson()).fromJson((String)itemTag.getValue(), String[].class);
/*     */ 
/*     */         
/* 217 */         return new StringListData(arrayOfString);
/*     */       }
/* 219 */       catch (JsonSyntaxException|IllegalStateException jsonSyntaxException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<StringListData> paramOptional) {
/* 232 */     if (paramOptional.isPresent()) {
/* 233 */       paramList.add(ChatColor.GRAY + "Current Value:");
/* 234 */       StringListData stringListData = paramOptional.get();
/* 235 */       stringListData.getList().forEach(paramString -> paramList.add(ChatColor.GRAY + MythicLib.plugin.parseColors(paramString)));
/*     */     } else {
/*     */       
/* 238 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*     */     } 
/* 240 */     paramList.add("");
/* 241 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add a permission.");
/* 242 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last permission.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StringListData getClearStatData() {
/* 248 */     return new StringListData();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\StringListStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */