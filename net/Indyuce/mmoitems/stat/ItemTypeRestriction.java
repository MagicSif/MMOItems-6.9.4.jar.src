/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.TypeSet;
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
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemTypeRestriction
/*     */   extends ItemStat<StringListData, StringListData>
/*     */ {
/*     */   public ItemTypeRestriction() {
/*  36 */     super("ITEM_TYPE_RESTRICTION", Material.EMERALD, "Item Type Restriction", new String[] { "This option defines the item types", "on which your gem can be applied." }, new String[] { "gem_stone" }, new Material[0]);
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
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  50 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  51 */       (new StatEdition(paramEditionInventory, ItemStats.ITEM_TYPE_RESTRICTION, new Object[0])).enable(new String[] { "Write in the chat the item type you want your gem to support.", "Supported formats: WEAPON or BLUNT, PIERCING, SLASHING, OFFHAND, EXTRA." });
/*     */     }
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
/*  74 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/*  75 */       paramEditionInventory.getEditedSection().contains(getPath())) {
/*  76 */       List<String> list = paramEditionInventory.getEditedSection().getStringList("" + getPath());
/*  77 */       if (list.size() < 1) {
/*     */         return;
/*     */       }
/*  80 */       String str = list.get(list.size() - 1);
/*  81 */       list.remove(str);
/*  82 */       paramEditionInventory.getEditedSection().set("" + getPath(), (list.size() == 0) ? null : list);
/*  83 */       paramEditionInventory.registerTemplateEdition();
/*  84 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + str + ".");
/*     */     } 
/*     */   }
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
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 100 */     String str = paramString.toUpperCase().replace(" ", "_").replace("-", "_");
/* 101 */     Validate.isTrue(isValid(str), str + " is not a valid item type/set. You can enter WEAPON, BLUNT, PIERCING, SLASHING, OFFHAND, EXTRA, as well as other item types here: /mi list type.");
/*     */ 
/*     */     
/* 104 */     List<String> list = paramEditionInventory.getEditedSection().contains(getPath()) ? paramEditionInventory.getEditedSection().getStringList("" + getPath()) : new ArrayList();
/* 105 */     list.add(str);
/* 106 */     paramEditionInventory.getEditedSection().set(getPath(), list);
/* 107 */     paramEditionInventory.registerTemplateEdition();
/* 108 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Your gem now supports " + str + ".");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<StringListData> paramOptional) {
/* 114 */     if (paramOptional.isPresent()) {
/* 115 */       paramList.add(ChatColor.GRAY + "Current Value:");
/* 116 */       StringListData stringListData = paramOptional.get();
/* 117 */       stringListData.getList().forEach(paramString -> paramList.add(ChatColor.GRAY + "* " + ChatColor.GREEN + paramString));
/*     */     } else {
/* 119 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "Compatible with any type.");
/*     */     } 
/* 121 */     paramList.add("");
/* 122 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add a supported item type/set.");
/* 123 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last element.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringListData paramStringListData) {
/* 129 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramStringListData));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull StringListData paramStringListData) {
/* 137 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 140 */     String str = String.join(",", paramStringListData.getList());
/* 141 */     arrayList.add(new ItemTag(getNBTPath(), str));
/*     */ 
/*     */     
/* 144 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 150 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 153 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 154 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/* 157 */     StringListData stringListData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 160 */     if (stringListData != null)
/*     */     {
/*     */       
/* 163 */       paramReadMMOItem.setData(this, (StatData)stringListData);
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public StringListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 169 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 172 */     if (itemTag != null) {
/*     */ 
/*     */       
/* 175 */       String str = (String)itemTag.getValue();
/* 176 */       ArrayList<String> arrayList = new ArrayList();
/* 177 */       if (str.contains(","))
/* 178 */       { for (String str1 : str.split(","))
/* 179 */         { if (!str1.isEmpty()) arrayList.add(str1);  }  }
/* 180 */       else { arrayList.add(str); }
/*     */ 
/*     */       
/* 183 */       return new StringListData(arrayList);
/*     */     } 
/*     */ 
/*     */     
/* 187 */     return null;
/*     */   }
/*     */   
/*     */   private boolean isValid(String paramString) {
/* 191 */     if (paramString.equals("WEAPON")) {
/* 192 */       return true;
/*     */     }
/* 194 */     for (Type type : MMOItems.plugin.getTypes().getAll()) {
/* 195 */       if (type.getId().equals(paramString))
/* 196 */         return true; 
/*     */     } 
/* 198 */     for (TypeSet typeSet : TypeSet.values()) {
/* 199 */       if (typeSet.name().equals(paramString))
/* 200 */         return true; 
/*     */     } 
/* 202 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StringListData getClearStatData() {
/* 208 */     return new StringListData();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\ItemTypeRestriction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */