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
/*     */ import net.Indyuce.mmoitems.stat.data.BooleanData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomBooleanData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class BooleanStat
/*     */   extends ItemStat<RandomBooleanData, BooleanData> {
/*     */   public BooleanStat(String paramString1, Material paramMaterial, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, Material... paramVarArgs) {
/*  30 */     super(paramString1, paramMaterial, paramString2, paramArrayOfString1, paramArrayOfString2, paramVarArgs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomBooleanData whenInitialized(Object paramObject) {
/*  36 */     if (paramObject instanceof Boolean) {
/*  37 */       return new RandomBooleanData(((Boolean)paramObject).booleanValue());
/*     */     }
/*  39 */     if (paramObject instanceof Number) {
/*  40 */       return new RandomBooleanData(Double.parseDouble(paramObject.toString()));
/*     */     }
/*  42 */     throw new IllegalArgumentException("Must specify a number (chance) or true/false");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull BooleanData paramBooleanData) {
/*  49 */     if (paramBooleanData.isEnabled()) {
/*     */ 
/*     */       
/*  52 */       paramItemStackBuilder.addItemTag(getAppliedNBT(paramBooleanData));
/*     */ 
/*     */       
/*  55 */       paramItemStackBuilder.getLore().insert(getPath(), new String[] { MMOItems.plugin.getLanguage().getStatFormat(getPath()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull BooleanData paramBooleanData) {
/*  64 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */     
/*  66 */     if (paramBooleanData.isEnabled())
/*     */     {
/*     */       
/*  69 */       arrayList.add(new ItemTag(getNBTPath(), Boolean.valueOf(true)));
/*     */     }
/*     */ 
/*     */     
/*  73 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  78 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  79 */       paramEditionInventory.getEditedSection().set(getPath(), paramEditionInventory.getEditedSection().getBoolean(getPath()) ? null : Boolean.valueOf(true));
/*  80 */       paramEditionInventory.registerTemplateEdition();
/*     */     
/*     */     }
/*  83 */     else if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/*  84 */       (new StatEdition(paramEditionInventory, this, new Object[0])).enable(new String[] { "Write in the chat the probability you want (a percentage)" });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  90 */     double d = MMOUtils.parseDouble(paramString);
/*  91 */     Validate.isTrue((d >= 0.0D && d <= 100.0D), "Chance must be between 0 and 100");
/*     */     
/*  93 */     paramEditionInventory.getEditedSection().set(getPath(), Double.valueOf(d / 100.0D));
/*  94 */     paramEditionInventory.registerTemplateEdition();
/*  95 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + getName() + " successfully changed to " + ChatColor.GREEN + 
/*  96 */         (MythicLib.plugin.getMMOConfig()).decimal.format(d) + "% Chance" + ChatColor.GRAY + ".");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 103 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 106 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 107 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.BOOLEAN));
/*     */     }
/* 109 */     BooleanData booleanData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 112 */     if (booleanData != null)
/*     */     {
/*     */       
/* 115 */       paramReadMMOItem.setData(this, (StatData)booleanData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BooleanData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 124 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 127 */     if (itemTag != null)
/*     */     {
/*     */       
/* 130 */       return new BooleanData(((Boolean)itemTag.getValue()).booleanValue());
/*     */     }
/*     */     
/* 133 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<RandomBooleanData> paramOptional) {
/* 139 */     if (paramOptional.isPresent()) {
/* 140 */       double d = ((RandomBooleanData)paramOptional.get()).getChance();
/* 141 */       paramList.add(ChatColor.GRAY + "Current Value: " + ((d >= 1.0D) ? (ChatColor.GREEN + "True") : (
/* 142 */           (d <= 0.0D) ? (ChatColor.RED + "False") : (ChatColor.GREEN + (MythicLib.plugin.getMMOConfig()).decimal.format(d * 100.0D) + "% Chance"))));
/*     */     } else {
/*     */       
/* 145 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "False");
/*     */     } 
/* 147 */     paramList.add("");
/* 148 */     paramList.add(ChatColor.YELLOW + "►" + " Left click to switch this value.");
/* 149 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to choose a probability to have this option.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public BooleanData getClearStatData() {
/* 155 */     return new BooleanData(false);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\BooleanStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */