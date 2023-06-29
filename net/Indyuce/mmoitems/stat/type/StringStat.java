/*     */ package net.Indyuce.mmoitems.stat.type;
/*     */ 
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
/*     */ import net.Indyuce.mmoitems.stat.data.StringData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class StringStat extends ItemStat<StringData, StringData> {
/*     */   public StringStat(String paramString1, Material paramMaterial, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, Material... paramVarArgs) {
/*  26 */     super(paramString1, paramMaterial, paramString2, paramArrayOfString1, paramArrayOfString2, paramVarArgs);
/*     */   }
/*     */ 
/*     */   
/*     */   public StringData whenInitialized(Object paramObject) {
/*  31 */     return new StringData(paramObject.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringData paramStringData) {
/*  38 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramStringData));
/*     */ 
/*     */     
/*  41 */     paramItemStackBuilder.getLore().insert(getPath(), new String[] { paramStringData.toString() });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull StringData paramStringData) {
/*  49 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/*  52 */     arrayList.add(new ItemTag(getNBTPath(), paramStringData.toString()));
/*     */ 
/*     */     
/*  55 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  60 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/*  61 */       paramEditionInventory.getEditedSection().set(getPath(), null);
/*  62 */       paramEditionInventory.registerTemplateEdition();
/*  63 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + getName() + ".");
/*     */     } else {
/*  65 */       (new StatEdition(paramEditionInventory, this, new Object[0])).enable(new String[] { "Write in the chat the text you want." });
/*     */     } 
/*     */   }
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  70 */     paramEditionInventory.getEditedSection().set(getPath(), paramString);
/*  71 */     paramEditionInventory.registerTemplateEdition();
/*  72 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin
/*  73 */         .getPrefix() + getName() + " successfully changed to '" + MythicLib.plugin.parseColors(paramString) + ChatColor.GRAY + "'.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/*  80 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/*  83 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/*  84 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/*  87 */     StringData stringData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/*  90 */     if (stringData != null)
/*     */     {
/*     */       
/*  93 */       paramReadMMOItem.setData(this, (StatData)stringData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public StringData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 102 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 105 */     if (itemTag != null) {
/*     */ 
/*     */       
/* 108 */       String str = (String)itemTag.getValue();
/*     */ 
/*     */       
/* 111 */       return new StringData(str);
/*     */     } 
/*     */ 
/*     */     
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<StringData> paramOptional) {
/* 120 */     if (paramOptional.isPresent()) {
/* 121 */       String str = MythicLib.plugin.parseColors(((StringData)paramOptional.get()).toString());
/* 122 */       str = (str.length() > 40) ? (str.substring(0, 40) + "...") : str;
/* 123 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.GREEN + str);
/*     */     } else {
/*     */       
/* 126 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*     */     } 
/* 128 */     paramList.add("");
/* 129 */     paramList.add(ChatColor.YELLOW + "►" + " Left click to change this value.");
/* 130 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove this value.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StringData getClearStatData() {
/* 136 */     return new StringData("");
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\StringStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */