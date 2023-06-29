/*     */ package net.Indyuce.mmoitems.stat;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackCategory;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import io.lumine.mythic.lib.api.util.ui.PlusMinusPercent;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.comp.enchants.EnchantPlugin;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.EnchantListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomEnchantListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.UpgradeInfo;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.EnchantmentStorageMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class Enchants extends ItemStat<RandomEnchantListData, EnchantListData> implements Upgradable {
/*     */   public Enchants() {
/*  46 */     super("ENCHANTS", Material.ENCHANTED_BOOK, "Enchantments", new String[] { "The item enchants." }, new String[] { "all" }, new Material[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public RandomEnchantListData whenInitialized(Object paramObject) {
/*  51 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a config section");
/*  52 */     return new RandomEnchantListData((ConfigurationSection)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  57 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  58 */       (new StatEdition(paramEditionInventory, ItemStats.ENCHANTS, new Object[0])).enable(new String[] { "Write in the chat the enchant you want to add.", ChatColor.AQUA + "Format: {Enchant Name} {Enchant Level Numeric Formula}" });
/*     */     }
/*     */     
/*  61 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/*  62 */       paramEditionInventory.getEditedSection().contains("enchants")) {
/*  63 */       Set set = paramEditionInventory.getEditedSection().getConfigurationSection("enchants").getKeys(false);
/*  64 */       String str = Arrays.<String>asList((String[])set.toArray((Object[])new String[0])).get(set.size() - 1);
/*  65 */       paramEditionInventory.getEditedSection().set("enchants." + str, null);
/*  66 */       if (set.size() <= 1)
/*  67 */         paramEditionInventory.getEditedSection().set("enchants", null); 
/*  68 */       paramEditionInventory.registerTemplateEdition();
/*  69 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + str.substring(0, 1).toUpperCase() + str
/*  70 */           .substring(1).toLowerCase().replace("_", " ") + ".");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  81 */     String[] arrayOfString = paramString.split(" ");
/*  82 */     Validate.isTrue((arrayOfString.length >= 2), "Use this format: {Enchant Name} {Enchant Level Numeric Formula}. Example: 'sharpness 5 0.3' stands for Sharpness 5, plus 0.3 level per item level (rounded up to lower integer)");
/*     */ 
/*     */     
/*  85 */     Enchantment enchantment = getEnchant(arrayOfString[0]);
/*  86 */     Validate.notNull(enchantment, arrayOfString[0] + " is not a valid enchantment! All enchants can be found here: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/enchantments/Enchantment.html");
/*     */ 
/*     */     
/*  89 */     NumericStatFormula numericStatFormula = new NumericStatFormula(paramString.substring(paramString.indexOf(" ") + 1));
/*  90 */     numericStatFormula.fillConfigurationSection(paramEditionInventory.getEditedSection(), "enchants." + enchantment.getKey().getKey());
/*  91 */     paramEditionInventory.registerTemplateEdition();
/*  92 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + enchantment.getKey().getKey() + " " + numericStatFormula.toString() + " successfully added.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<RandomEnchantListData> paramOptional) {
/*  98 */     if (paramOptional.isPresent()) {
/*  99 */       paramList.add(ChatColor.GRAY + "Current Value:");
/* 100 */       RandomEnchantListData randomEnchantListData = paramOptional.get();
/* 101 */       randomEnchantListData.getEnchants().forEach(paramEnchantment -> paramList.add(ChatColor.GRAY + "* " + MMOUtils.caseOnWords(paramEnchantment.getKey().getKey().replace("_", " ")) + " " + paramRandomEnchantListData.getLevel(paramEnchantment).toString()));
/*     */     }
/*     */     else {
/*     */       
/* 105 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*     */     } 
/* 107 */     paramList.add("");
/* 108 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add an enchant.");
/* 109 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last enchant.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public EnchantListData getClearStatData() {
/* 115 */     return new EnchantListData();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 122 */     EnchantListData enchantListData = new EnchantListData();
/*     */ 
/*     */     
/* 125 */     ItemStack itemStack = paramReadMMOItem.getNBT().getItem();
/* 126 */     if (itemStack.hasItemMeta()) {
/*     */ 
/*     */       
/* 129 */       ItemMeta itemMeta = itemStack.getItemMeta();
/* 130 */       if (itemMeta != null) {
/*     */ 
/*     */         
/* 133 */         for (Enchantment enchantment : itemMeta.getEnchants().keySet())
/*     */         {
/*     */           
/* 136 */           enchantListData.addEnchant(enchantment, itemMeta.getEnchantLevel(enchantment));
/*     */         }
/*     */ 
/*     */         
/* 140 */         if (itemMeta instanceof EnchantmentStorageMeta)
/*     */         {
/*     */           
/* 143 */           for (Enchantment enchantment : ((EnchantmentStorageMeta)itemMeta).getStoredEnchants().keySet())
/*     */           {
/*     */             
/* 146 */             enchantListData.addEnchant(enchantment, ((EnchantmentStorageMeta)itemMeta).getStoredEnchantLevel(enchantment));
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 153 */     paramReadMMOItem.setData(ItemStats.ENCHANTS, (StatData)enchantListData);
/*     */ 
/*     */     
/* 156 */     ItemTag itemTag = ItemTag.getTagAtPath("HSTRY_" + getId(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING);
/* 157 */     if (itemTag == null)
/*     */     {
/*     */       
/* 160 */       if (!paramReadMMOItem.getNBT().getBoolean(ItemStats.DISABLE_ENCHANTING.getNBTPath()) || 
/*     */ 
/*     */         
/* 163 */         !paramReadMMOItem.getNBT().getBoolean(ItemStats.DISABLE_REPAIRING.getNBTPath()))
/*     */       {
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
/* 177 */         StatHistory.from((MMOItem)paramReadMMOItem, ItemStats.ENCHANTS);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static EnchantListData fromVanilla(@Nullable ItemStack paramItemStack) {
/* 190 */     EnchantListData enchantListData = new EnchantListData();
/*     */ 
/*     */     
/* 193 */     if (paramItemStack == null) return enchantListData;
/*     */ 
/*     */     
/* 196 */     for (Enchantment enchantment : paramItemStack.getEnchantments().keySet()) {
/*     */ 
/*     */       
/* 199 */       int i = paramItemStack.getEnchantmentLevel(enchantment);
/*     */ 
/*     */       
/* 202 */       if (i != 0) enchantListData.addEnchant(enchantment, i);
/*     */     
/*     */     } 
/* 205 */     return enchantListData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EnchantListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 217 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 220 */     if (itemTag != null) {
/*     */ 
/*     */       
/* 223 */       ArrayList arrayList = ItemTag.getStringListFromTag(itemTag);
/*     */ 
/*     */       
/* 226 */       EnchantListData enchantListData = new EnchantListData();
/*     */ 
/*     */       
/* 229 */       for (String str : arrayList) {
/*     */ 
/*     */         
/* 232 */         String[] arrayOfString = str.split(" ");
/* 233 */         if (arrayOfString.length >= 2) {
/*     */ 
/*     */           
/* 236 */           String str1 = arrayOfString[0];
/* 237 */           String str2 = arrayOfString[1];
/*     */ 
/*     */           
/* 240 */           Enchantment enchantment = null;
/*     */           try {
/* 242 */             enchantment = getEnchant(str1);
/* 243 */           } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */           
/* 247 */           Integer integer = SilentNumbers.IntegerParse(str2);
/*     */ 
/*     */           
/* 250 */           if (enchantment != null && integer != null)
/*     */           {
/*     */             
/* 253 */             enchantListData.addEnchant(enchantment, integer.intValue());
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 259 */       return enchantListData;
/*     */     } 
/*     */     
/* 262 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull EnchantListData paramEnchantListData) {
/* 268 */     for (Enchantment enchantment : paramEnchantListData.getEnchants()) {
/* 269 */       int i = paramEnchantListData.getLevel(enchantment);
/*     */ 
/*     */       
/* 272 */       if (paramItemStackBuilder.getItemStack().getType() == Material.ENCHANTED_BOOK) {
/*     */ 
/*     */         
/* 275 */         ((EnchantmentStorageMeta)paramItemStackBuilder.getMeta()).addStoredEnchant(enchantment, i, true);
/*     */       } else {
/*     */         
/* 278 */         paramItemStackBuilder.getMeta().addEnchant(enchantment, i, true);
/*     */       } 
/*     */       
/* 281 */       for (EnchantPlugin enchantPlugin : MMOItems.plugin.getEnchantPlugins()) {
/* 282 */         if (enchantPlugin.isCustomEnchant(enchantment)) {
/* 283 */           enchantPlugin.handleEnchant(paramItemStackBuilder, enchantment, i);
/*     */         }
/*     */       } 
/*     */     } 
/* 287 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramEnchantListData));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull EnchantListData paramEnchantListData) {
/* 296 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 299 */     ArrayList<String> arrayList1 = new ArrayList();
/* 300 */     for (Enchantment enchantment : paramEnchantListData.getEnchants()) {
/* 301 */       arrayList1.add(enchantment.getKey().getKey() + " " + paramEnchantListData.getLevel(enchantment));
/*     */     }
/*     */ 
/*     */     
/* 305 */     arrayList.add(ItemTag.fromStringList(getNBTPath(), arrayList1));
/*     */     
/* 307 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public UpgradeInfo loadUpgradeInfo(@Nullable Object paramObject) {
/* 314 */     return EnchantUpgradeInfo.GetFrom(paramObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StatData apply(@NotNull StatData paramStatData, @NotNull UpgradeInfo paramUpgradeInfo, int paramInt) {
/* 323 */     if (paramStatData instanceof EnchantListData && paramUpgradeInfo instanceof EnchantUpgradeInfo) {
/*     */ 
/*     */ 
/*     */       
/* 327 */       EnchantListData enchantListData = (EnchantListData)paramStatData;
/* 328 */       EnchantUpgradeInfo enchantUpgradeInfo = (EnchantUpgradeInfo)paramUpgradeInfo;
/*     */ 
/*     */       
/* 331 */       for (Enchantment enchantment : enchantUpgradeInfo.getAffectedEnchantments()) {
/* 332 */         int i = paramInt;
/*     */ 
/*     */ 
/*     */         
/* 336 */         double d = enchantListData.getLevel(enchantment);
/*     */         
/* 338 */         PlusMinusPercent plusMinusPercent = enchantUpgradeInfo.getPMP(enchantment);
/* 339 */         if (plusMinusPercent == null) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 345 */         if (i > 0) {
/*     */ 
/*     */           
/* 348 */           while (i > 0)
/*     */           {
/*     */ 
/*     */ 
/*     */             
/* 353 */             d = plusMinusPercent.apply(d);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 358 */             i--;
/*     */           }
/*     */         
/*     */         }
/* 362 */         else if (i < 0) {
/*     */ 
/*     */           
/* 365 */           while (i < 0) {
/*     */ 
/*     */ 
/*     */             
/* 369 */             d = plusMinusPercent.reverse(d);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 374 */             i++;
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 380 */         enchantListData.addEnchant(enchantment, SilentNumbers.floor(d));
/*     */       } 
/*     */ 
/*     */       
/* 384 */       return (StatData)enchantListData;
/*     */     } 
/*     */ 
/*     */     
/* 388 */     return paramStatData;
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
/*     */   public static void separateEnchantments(@NotNull MMOItem paramMMOItem) {
/* 403 */     if (paramMMOItem.hasData(ItemStats.DISABLE_REPAIRING) && paramMMOItem.hasData(ItemStats.DISABLE_ENCHANTING)) {
/*     */       return;
/*     */     }
/* 406 */     boolean bool = MMOItems.plugin.getConfig().getBoolean("stat-merging.additive-enchantments", false);
/*     */ 
/*     */ 
/*     */     
/* 410 */     if (paramMMOItem.hasData(ItemStats.ENCHANTS)) {
/*     */ 
/*     */       
/* 413 */       EnchantListData enchantListData1 = (EnchantListData)paramMMOItem.getData(ItemStats.ENCHANTS);
/* 414 */       StatHistory statHistory = StatHistory.from(paramMMOItem, ItemStats.ENCHANTS);
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
/* 430 */       EnchantListData enchantListData2 = (EnchantListData)statHistory.recalculate(paramMMOItem.getUpgradeLevel());
/*     */ 
/*     */       
/* 433 */       HashMap<Object, Object> hashMap = new HashMap<>();
/*     */ 
/*     */       
/* 436 */       for (Enchantment enchantment : Enchantment.values()) {
/*     */ 
/*     */         
/* 439 */         int i = enchantListData1.getLevel(enchantment);
/* 440 */         int j = enchantListData2.getLevel(enchantment);
/*     */ 
/*     */         
/* 443 */         if (bool) {
/*     */ 
/*     */           
/* 446 */           int k = i - j;
/* 447 */           if (k != 0) {
/* 448 */             hashMap.put(enchantment, Integer.valueOf(k));
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/* 456 */         else if (i > j) {
/* 457 */           hashMap.put(enchantment, Integer.valueOf(i));
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 464 */       if (hashMap.size() > 0) {
/*     */         
/* 466 */         EnchantListData enchantListData = new EnchantListData();
/* 467 */         for (Enchantment enchantment : hashMap.keySet())
/*     */         {
/*     */           
/* 470 */           enchantListData.addEnchant(enchantment, ((Integer)hashMap.get(enchantment)).intValue());
/*     */         }
/*     */ 
/*     */         
/* 474 */         statHistory.registerExternalData((StatData)enchantListData);
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Enchantment getEnchant(String paramString) {
/* 499 */     paramString = paramString.toLowerCase().replace("-", "_");
/* 500 */     Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(paramString));
/*     */ 
/*     */     
/* 503 */     if (enchantment != null) {
/* 504 */       return enchantment;
/*     */     }
/*     */     
/* 507 */     for (EnchantPlugin enchantPlugin : MMOItems.plugin.getEnchantPlugins()) {
/* 508 */       Enchantment enchantment1 = Enchantment.getByKey(enchantPlugin.getNamespacedKey(paramString));
/* 509 */       if (enchantment1 != null) {
/* 510 */         return enchantment1;
/*     */       }
/*     */     } 
/*     */     
/* 514 */     return Enchantment.getByName(paramString);
/*     */   }
/*     */   
/*     */   public static class EnchantUpgradeInfo implements UpgradeInfo { @NotNull
/* 518 */     HashMap<Enchantment, PlusMinusPercent> perEnchantmentOperations = new HashMap<>();
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
/*     */     @NotNull
/*     */     public static EnchantUpgradeInfo GetFrom(@Nullable Object param1Object) {
/* 539 */       Validate.notNull(param1Object, FriendlyFeedbackProvider.quickForConsole((FriendlyFeedbackPalette)FFPMMOItems.get(), "Upgrade operation list must not be null", new String[0]));
/*     */ 
/*     */       
/* 542 */       if (!(param1Object instanceof List))
/*     */       {
/*     */         
/* 545 */         throw new IllegalArgumentException(
/* 546 */             FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Expected a list of strings instead of $i{0}", new String[] { param1Object.toString() }));
/*     */       }
/*     */       
/* 549 */       ArrayList<String> arrayList = new ArrayList();
/* 550 */       boolean bool = false;
/* 551 */       StringBuilder stringBuilder = new StringBuilder();
/* 552 */       for (String str : param1Object) {
/*     */ 
/*     */         
/* 555 */         if (str instanceof String) {
/* 556 */           arrayList.add(str);
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 561 */         bool = true;
/*     */ 
/*     */         
/* 564 */         stringBuilder.append(FriendlyFeedbackProvider.quickForConsole((FriendlyFeedbackPalette)FFPMMOItems.get(), " Invalid list entry $i{0}$b;", new String[] { param1Object.toString() }));
/*     */       } 
/*     */       
/* 567 */       if (bool)
/*     */       {
/* 569 */         throw new IllegalArgumentException(
/* 570 */             FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Could not read enchantment list:", new String[0]) + stringBuilder.toString());
/*     */       }
/*     */ 
/*     */       
/* 574 */       if (arrayList.isEmpty()) {
/* 575 */         throw new IllegalArgumentException(
/* 576 */             FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Upgrade operation list is empty", new String[0]));
/*     */       }
/*     */ 
/*     */       
/* 580 */       EnchantUpgradeInfo enchantUpgradeInfo = new EnchantUpgradeInfo();
/*     */       
/* 582 */       for (String str : arrayList) {
/*     */         
/* 584 */         String[] arrayOfString = str.split(" ");
/*     */ 
/*     */         
/* 587 */         if (arrayOfString.length >= 2) {
/*     */ 
/*     */           
/* 590 */           String str1 = arrayOfString[0];
/* 591 */           String str2 = arrayOfString[1];
/*     */ 
/*     */           
/* 594 */           char c = str2.charAt(0);
/* 595 */           if (c == 's') {
/* 596 */             str2 = str2.substring(1);
/* 597 */           } else if (c != '+' && c != '-' && c != 'n') {
/* 598 */             str2 = '+' + str2;
/*     */           } 
/*     */ 
/*     */           
/* 602 */           FriendlyFeedbackProvider friendlyFeedbackProvider = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/* 603 */           PlusMinusPercent plusMinusPercent = PlusMinusPercent.getFromString(str2, friendlyFeedbackProvider);
/*     */           
/* 605 */           Enchantment enchantment = null;
/*     */           try {
/* 607 */             enchantment = Enchants.getEnchant(str1);
/* 608 */           } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */           
/* 612 */           if (plusMinusPercent == null) {
/* 613 */             stringBuilder.append(' ').append(((FriendlyFeedbackMessage)friendlyFeedbackProvider.getFeedbackOf(FriendlyFeedbackCategory.ERROR).get(0)).forConsole(friendlyFeedbackProvider.getPalette()));
/* 614 */             bool = true;
/*     */           } 
/* 616 */           if (enchantment == null) {
/* 617 */             stringBuilder.append(FriendlyFeedbackProvider.quickForConsole((FriendlyFeedbackPalette)FFPMMOItems.get(), " Invalid Enchantment $i{0}$b.", new String[] { str1 }));
/* 618 */             bool = true;
/*     */           } 
/*     */ 
/*     */           
/* 622 */           if (plusMinusPercent != null && enchantment != null)
/*     */           {
/* 624 */             enchantUpgradeInfo.addEnchantmentOperation(enchantment, plusMinusPercent);
/*     */           }
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 631 */         bool = true;
/* 632 */         stringBuilder.append(FriendlyFeedbackProvider.quickForConsole((FriendlyFeedbackPalette)FFPMMOItems.get(), " Invalid list entry $i{0}$b. List entries are of the format 'esharpness +1$b'.", new String[] { str }));
/*     */       } 
/*     */ 
/*     */       
/* 636 */       if (bool)
/*     */       {
/* 638 */         throw new IllegalArgumentException(
/* 639 */             FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Could not read enchantment list:", new String[0]) + stringBuilder.toString());
/*     */       }
/*     */ 
/*     */       
/* 643 */       return enchantUpgradeInfo;
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
/*     */     @Nullable
/*     */     public PlusMinusPercent getPMP(@NotNull Enchantment param1Enchantment) {
/* 656 */       return this.perEnchantmentOperations.get(param1Enchantment);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addEnchantmentOperation(@NotNull Enchantment param1Enchantment, @NotNull PlusMinusPercent param1PlusMinusPercent) {
/* 663 */       this.perEnchantmentOperations.put(param1Enchantment, param1PlusMinusPercent);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Set<Enchantment> getAffectedEnchantments() {
/* 671 */       return this.perEnchantmentOperations.keySet();
/*     */     } }
/*     */ 
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\Enchants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */