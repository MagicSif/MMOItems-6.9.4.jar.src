/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import io.lumine.mythic.lib.element.Element;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.gui.edition.ElementsEdition;
/*     */ import net.Indyuce.mmoitems.stat.data.ElementListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomElementListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.Previewable;
/*     */ import net.Indyuce.mmoitems.util.ElementStatType;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import net.Indyuce.mmoitems.util.Pair;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class Elements
/*     */   extends ItemStat<RandomElementListData, ElementListData> implements Previewable<RandomElementListData, ElementListData> {
/*     */   public Elements() {
/*  38 */     super("ELEMENT", Material.SLIME_BALL, "Elements", new String[] { "The elements of your item." }, new String[] { "slashing", "piercing", "blunt", "catalyst", "range", "tool", "armor", "gem_stone" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomElementListData whenInitialized(Object paramObject) {
/*  44 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a config section");
/*  45 */     return new RandomElementListData((ConfigurationSection)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  50 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  51 */       (new ElementsEdition(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited())).open(paramEditionInventory.getPage());
/*     */     }
/*  53 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/*  54 */       paramEditionInventory.getEditedSection().contains("element")) {
/*  55 */       paramEditionInventory.getEditedSection().set("element", null);
/*  56 */       paramEditionInventory.registerTemplateEdition();
/*  57 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Elements successfully removed.");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  63 */     String str1 = paramVarArgs[0].toString();
/*     */     
/*  65 */     NumericStatFormula numericStatFormula = new NumericStatFormula(paramString);
/*  66 */     numericStatFormula.fillConfigurationSection(paramEditionInventory.getEditedSection(), "element." + str1);
/*     */ 
/*     */     
/*  69 */     String str2 = str1.split("\\.")[0];
/*  70 */     if (paramEditionInventory.getEditedSection().contains("element")) {
/*  71 */       if (paramEditionInventory.getEditedSection().getConfigurationSection("element").contains(str2) && paramEditionInventory
/*  72 */         .getEditedSection().getConfigurationSection("element." + str2).getKeys(false).isEmpty())
/*  73 */         paramEditionInventory.getEditedSection().set("element." + str2, null); 
/*  74 */       if (paramEditionInventory.getEditedSection().getConfigurationSection("element").getKeys(false).isEmpty()) {
/*  75 */         paramEditionInventory.getEditedSection().set("element", null);
/*     */       }
/*     */     } 
/*  78 */     paramEditionInventory.registerTemplateEdition();
/*  79 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + MMOUtils.caseOnWords(str1.replace(".", " ")) + ChatColor.GRAY + " successfully changed to " + ChatColor.GOLD + numericStatFormula
/*  80 */         .toString() + ChatColor.GRAY + ".");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<RandomElementListData> paramOptional) {
/*  86 */     if (paramOptional.isPresent()) {
/*  87 */       paramList.add(ChatColor.GRAY + "Current Value:");
/*  88 */       RandomElementListData randomElementListData = paramOptional.get();
/*  89 */       randomElementListData.getKeys().forEach(paramPair -> paramList.add(ChatColor.GRAY + "* " + ((Element)paramPair.getKey()).getName() + " " + ((ElementStatType)paramPair.getValue()).getName() + ": " + ChatColor.RED + paramRandomElementListData.getStat((Element)paramPair.getKey(), (ElementStatType)paramPair.getValue())));
/*     */     } else {
/*     */       
/*  92 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*     */     } 
/*  94 */     paramList.add("");
/*  95 */     paramList.add(ChatColor.YELLOW + "►" + " Click to access the elements edition menu.");
/*  96 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove all the elements.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ElementListData getClearStatData() {
/* 102 */     return new ElementListData();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull ElementListData paramElementListData) {
/* 108 */     ArrayList<String> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 111 */     for (Pair pair : paramElementListData.getKeys()) {
/*     */ 
/*     */ 
/*     */       
/* 115 */       String str = ItemStat.translate("elemental-" + ((ElementStatType)pair.getValue()).lowerCaseName()).replace("{color}", ((Element)pair.getKey()).getColor()).replace("{icon}", ((Element)pair.getKey()).getLoreIcon()).replace("{element}", ((Element)pair.getKey()).getName());
/* 116 */       double d = paramElementListData.getStat((Element)pair.getKey(), (ElementStatType)pair.getValue());
/* 117 */       arrayList.add(DoubleStat.formatPath("ELEMENTAL_STAT", str, true, d));
/*     */     } 
/*     */ 
/*     */     
/* 121 */     if (!arrayList.isEmpty()) {
/* 122 */       paramItemStackBuilder.getLore().insert("elements", arrayList);
/*     */     }
/*     */     
/* 125 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramElementListData));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull ElementListData paramElementListData) {
/* 133 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 134 */     for (Pair pair : paramElementListData.getKeys()) {
/* 135 */       arrayList.add(new ItemTag("MMOITEMS_" + ((ElementStatType)pair.getValue()).getConcatenatedTagPath((Element)pair.getKey()), Double.valueOf(paramElementListData.getStat((Element)pair.getKey(), (ElementStatType)pair.getValue()))));
/*     */     }
/*     */     
/* 138 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 145 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 146 */     for (Element element : Element.values()) {
/* 147 */       for (ElementStatType elementStatType : ElementStatType.values()) {
/* 148 */         String str = "MMOITEMS_" + elementStatType.getConcatenatedTagPath(element);
/* 149 */         if (paramReadMMOItem.getNBT().hasTag(str)) {
/* 150 */           arrayList.add(ItemTag.getTagAtPath(str, paramReadMMOItem.getNBT(), SupportedNBTTagValues.DOUBLE));
/*     */         }
/*     */       } 
/*     */     } 
/* 154 */     ElementListData elementListData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 157 */     if (elementListData != null) {
/* 158 */       paramReadMMOItem.setData(this, (StatData)elementListData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ElementListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 167 */     ElementListData elementListData = new ElementListData();
/*     */ 
/*     */     
/* 170 */     for (Element element : Element.values()) {
/* 171 */       for (ElementStatType elementStatType : ElementStatType.values()) {
/* 172 */         String str = "MMOITEMS_" + elementStatType.getConcatenatedTagPath(element);
/* 173 */         ItemTag itemTag = ItemTag.getTagAtPath(str, paramArrayList);
/* 174 */         if (itemTag != null)
/* 175 */           elementListData.setStat(element, elementStatType, ((Double)itemTag.getValue()).doubleValue()); 
/*     */       } 
/*     */     } 
/* 178 */     return elementListData.isEmpty() ? null : elementListData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenPreviewed(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull ElementListData paramElementListData, @NotNull RandomElementListData paramRandomElementListData) {
/* 183 */     Validate.isTrue(paramElementListData instanceof ElementListData, "Current Data is not ElementListData");
/* 184 */     Validate.isTrue(paramRandomElementListData instanceof RandomElementListData, "Template Data is not RandomElementListData");
/*     */ 
/*     */     
/* 187 */     for (Element element : Element.values()) {
/* 188 */       for (ElementStatType elementStatType : ElementStatType.values()) {
/*     */         
/* 190 */         NumericStatFormula numericStatFormula = paramRandomElementListData.getStat(element, elementStatType);
/*     */ 
/*     */         
/* 193 */         double d1 = numericStatFormula.calculate(0.0D, -2.5D);
/* 194 */         double d2 = numericStatFormula.calculate(0.0D, 2.5D);
/*     */ 
/*     */         
/* 197 */         if (d1 != 0.0D || d2 != 0.0D) {
/*     */ 
/*     */           
/* 200 */           String str2, str1 = element.getId().toLowerCase() + "-" + elementStatType.name().toLowerCase().replace("_", "-");
/*     */ 
/*     */           
/* 203 */           if (SilentNumbers.round(d1, 2) == SilentNumbers.round(d2, 2)) {
/* 204 */             str2 = DoubleStat.formatPath(elementStatType.getConcatenatedTagPath(element), ItemStat.translate(str1), true, d1);
/*     */           } else {
/* 206 */             str2 = DoubleStat.formatPath(elementStatType.getConcatenatedTagPath(element), ItemStat.translate(str1), true, d1, d2);
/*     */           } 
/*     */ 
/*     */           
/* 210 */           paramItemStackBuilder.getLore().insert(str1, new String[] { str2 });
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 215 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramElementListData));
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\Elements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */