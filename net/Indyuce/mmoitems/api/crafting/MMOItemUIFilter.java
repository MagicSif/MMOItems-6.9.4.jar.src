/*     */ package net.Indyuce.mmoitems.api.crafting;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.crafting.uifilters.UIFilter;
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.UIFilterManager;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.util.ItemFactory;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackCategory;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import io.lumine.mythic.lib.api.util.ui.QuickNumberRange;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.stat.data.UpgradeData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MMOItemUIFilter
/*     */   implements UIFilter
/*     */ {
/*     */   ArrayList<String> validDataments;
/*     */   
/*     */   @NotNull
/*     */   public String getIdentifier() {
/*  35 */     return "m";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(@NotNull ItemStack paramItemStack, @NotNull String paramString1, @NotNull String paramString2, @Nullable FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/*  42 */     paramString1 = paramString1.replace(" ", "_").replace("-", "_").toUpperCase();
/*  43 */     paramString2 = paramString2.replace(" ", "_").replace("-", "_").toUpperCase();
/*     */ 
/*     */     
/*  46 */     if (!isValid(paramString1, paramString2, paramFriendlyFeedbackProvider)) return false;
/*     */ 
/*     */     
/*  49 */     if (cancelMatch(paramItemStack, paramFriendlyFeedbackProvider)) return false;
/*     */ 
/*     */     
/*  52 */     NBTItem nBTItem = NBTItem.get(paramItemStack);
/*  53 */     MMOItemTemplate mMOItemTemplate = MMOItems.plugin.getTemplates().getTemplate(nBTItem);
/*  54 */     if (mMOItemTemplate == null) {
/*     */ 
/*     */       
/*  57 */       FriendlyFeedbackProvider.log(paramFriendlyFeedbackProvider, FriendlyFeedbackCategory.FAILURE, "Item $r{0}$b is $fnot a MMOItem$b. ", new String[] {
/*  58 */             SilentNumbers.getItemName(paramItemStack)
/*     */           });
/*     */       
/*  61 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  65 */     String str = "";
/*  66 */     if (paramString2.contains("{")) {
/*     */ 
/*     */       
/*  69 */       str = paramString2.substring(paramString2.indexOf('{') + 1);
/*  70 */       paramString2 = paramString2.substring(0, paramString2.indexOf('{'));
/*  71 */       if (str.endsWith("}")) str = str.substring(0, str.length() - 1);
/*     */     
/*     */     } 
/*  74 */     if (!mMOItemTemplate.getType().getId().equals(paramString1) || !mMOItemTemplate.getId().equals(paramString2)) {
/*     */ 
/*     */       
/*  77 */       FriendlyFeedbackProvider.log(paramFriendlyFeedbackProvider, FriendlyFeedbackCategory.FAILURE, "MMOItem $r{0} {1}$b is not the expected $r{2} {3}$b. $fNo Match. ", new String[] { mMOItemTemplate
/*     */             
/*  79 */             .getType().getId(), mMOItemTemplate.getId(), paramString1, paramString2 });
/*     */ 
/*     */       
/*  82 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  86 */     if (!str.isEmpty()) {
/*  87 */       VolatileMMOItem volatileMMOItem = new VolatileMMOItem(nBTItem);
/*     */ 
/*     */       
/*  90 */       QuickNumberRange quickNumberRange = SilentNumbers.rangeFromBracketsTab(str, "LEVEL");
/*  91 */       if (quickNumberRange != null) {
/*     */ 
/*     */         
/*  94 */         int i = volatileMMOItem.getUpgradeLevel();
/*     */ 
/*     */         
/*  97 */         if (!quickNumberRange.inRange(i)) {
/*     */ 
/*     */           
/* 100 */           FriendlyFeedbackProvider.log(paramFriendlyFeedbackProvider, FriendlyFeedbackCategory.FAILURE, "MMOItem $r{0} {1}$b is of level $u{2}$b though $r{3}$b was expected. $fNo Match. ", new String[] { mMOItemTemplate
/*     */                 
/* 102 */                 .getType().getId(), mMOItemTemplate.getId(), String.valueOf(i), quickNumberRange.toString() });
/*     */ 
/*     */           
/* 105 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 111 */     FriendlyFeedbackProvider.log(paramFriendlyFeedbackProvider, FriendlyFeedbackCategory.SUCCESS, "Detected $r{0} {1} $sSuccessfully. ", new String[] { mMOItemTemplate
/* 112 */           .getType().getId(), mMOItemTemplate.getId() });
/*     */     
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValid(@NotNull String paramString1, @NotNull String paramString2, @Nullable FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/* 119 */     if (reg) return true; 
/* 120 */     paramString1 = paramString1.replace(" ", "_").replace("-", "_").toUpperCase();
/* 121 */     paramString2 = paramString2.replace(" ", "_").replace("-", "_").toUpperCase();
/*     */ 
/*     */     
/* 124 */     Type type = MMOItems.plugin.getTypes().get(paramString1);
/*     */ 
/*     */     
/* 127 */     if (type == null) {
/*     */ 
/*     */       
/* 130 */       FriendlyFeedbackProvider.log(paramFriendlyFeedbackProvider, FriendlyFeedbackCategory.ERROR, "$fInvalid MMOItem Type $r{0}$f. ", new String[] { paramString1 });
/*     */ 
/*     */       
/* 133 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 137 */     if (paramString2.contains("{") && paramString2.contains("}"))
/*     */     {
/*     */       
/* 140 */       paramString2 = paramString2.substring(0, paramString2.indexOf('{'));
/*     */     }
/*     */     
/* 143 */     if (MMOItems.plugin.getMMOItem(type, paramString2) == null) {
/*     */ 
/*     */ 
/*     */       
/* 147 */       FriendlyFeedbackProvider.log(paramFriendlyFeedbackProvider, FriendlyFeedbackCategory.ERROR, "$fInvalid MMOItem $r{0} {1}$f: $bNo such MMOItem for Type $e{0}$b. ", new String[] { paramString1, paramString2 });
/*     */ 
/*     */       
/* 150 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 154 */     FriendlyFeedbackProvider.log(paramFriendlyFeedbackProvider, FriendlyFeedbackCategory.SUCCESS, "Valid MMOItem $r{0} {1}$b. $snice. ", new String[] { paramString1, paramString2 });
/*     */     
/* 156 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<String> tabCompleteArgument(@NotNull String paramString) {
/* 164 */     return SilentNumbers.smartFilter(MMOItems.plugin.getTypes().getAllTypeNames(), paramString, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<String> tabCompleteData(@NotNull String paramString1, @NotNull String paramString2) {
/* 172 */     Type type = MMOItems.plugin.getTypes().get(paramString1);
/*     */     
/* 174 */     if (type != null) {
/*     */ 
/*     */       
/* 177 */       String str1 = "", str2 = "";
/* 178 */       if (paramString2.contains("{")) {
/*     */ 
/*     */         
/* 181 */         str1 = paramString2.substring(paramString2.indexOf('{') + 1);
/* 182 */         paramString2 = paramString2.substring(0, paramString2.indexOf('{'));
/*     */         
/* 184 */         int i = 0;
/* 185 */         if (str1.contains(",")) i = str1.lastIndexOf(',') + 1;
/*     */         
/* 187 */         str2 = str1.substring(i);
/* 188 */         str1 = str1.substring(0, i);
/*     */       } 
/*     */ 
/*     */       
/* 192 */       ArrayList<String> arrayList1 = SilentNumbers.smartFilter(MMOItems.plugin.getTemplates().getTemplateNames(type), paramString2, true);
/* 193 */       ArrayList<String> arrayList2 = arrayList1;
/*     */ 
/*     */       
/* 196 */       if (!str2.isEmpty()) {
/* 197 */         ArrayList<String> arrayList = new ArrayList();
/*     */ 
/*     */         
/* 200 */         if (str2.contains("=")) {
/*     */ 
/*     */           
/* 203 */           String str = str2.substring(0, str2.indexOf('='));
/* 204 */           switch (str.toLowerCase()) {
/*     */             case "level":
/* 206 */               SilentNumbers.addAll(arrayList, (Object[])new String[] { "level=1..", "level=2..4", "level=..6" });
/*     */               break;
/*     */             default:
/* 209 */               arrayList.add(str2);
/*     */               break;
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         } else {
/* 217 */           arrayList = SilentNumbers.smartFilter(getValidDataments(), str2, true);
/*     */         } 
/*     */ 
/*     */         
/* 221 */         for (String str : arrayList1) {
/* 222 */           for (String str3 : arrayList)
/*     */           {
/* 224 */             arrayList2.add(str + "{" + str1 + str3);
/*     */           }
/*     */         } 
/*     */       } 
/* 228 */       return arrayList2;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 233 */     return SilentNumbers.toArrayList((Object[])new String[] { "Type_not_found,_check_your_spelling" });
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<String> getValidDataments() {
/* 238 */     if (this.validDataments != null) return this.validDataments;  this.validDataments = SilentNumbers.toArrayList((Object[])new String[] { "level" }); return this.validDataments;
/*     */   }
/*     */   public boolean fullyDefinesItem() {
/* 241 */     return true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getItemStack(@NotNull String paramString1, @NotNull String paramString2, @Nullable FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/* 246 */     if (!isValid(paramString1, paramString2, paramFriendlyFeedbackProvider)) return null; 
/* 247 */     paramString1 = paramString1.replace(" ", "_").replace("-", "_").toUpperCase();
/* 248 */     paramString2 = paramString2.replace(" ", "_").replace("-", "_").toUpperCase();
/* 249 */     return MMOItems.plugin.getItem(paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getDisplayStack(@NotNull String paramString1, @NotNull String paramString2, @Nullable FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/* 255 */     if (!isValid(paramString1, paramString2, paramFriendlyFeedbackProvider)) return ItemFactory.of(Material.STRUCTURE_VOID).name("§cInvalid MMOItem §e" + paramString1 + " " + paramString2).build(); 
/* 256 */     paramString1 = paramString1.replace(" ", "_").replace("-", "_").toUpperCase();
/*     */ 
/*     */     
/* 259 */     String str = "";
/* 260 */     if (paramString2.contains("{")) {
/*     */ 
/*     */       
/* 263 */       str = paramString2.substring(paramString2.indexOf('{') + 1);
/* 264 */       paramString2 = paramString2.substring(0, paramString2.indexOf('{'));
/* 265 */       if (str.endsWith("}")) str = str.substring(0, str.length() - 1); 
/*     */     } 
/* 267 */     paramString2 = paramString2.replace(" ", "_").replace("-", "_").toUpperCase();
/* 268 */     MMOItem mMOItem = (new MMOItemBuilder(MMOItems.plugin.getTemplates().getTemplate(MMOItems.plugin.getTypes().get(paramString1), paramString2), 0, null, true)).build();
/*     */ 
/*     */     
/* 271 */     if (!str.isEmpty())
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 276 */       if (mMOItem.hasUpgradeTemplate()) {
/*     */ 
/*     */ 
/*     */         
/* 280 */         QuickNumberRange quickNumberRange = SilentNumbers.rangeFromBracketsTab(str, "level");
/* 281 */         if (quickNumberRange != null) {
/*     */           
/* 283 */           UpgradeData upgradeData = ((UpgradeData)mMOItem.getData(ItemStats.UPGRADE)).clone();
/* 284 */           upgradeData.setLevel(SilentNumbers.floor(quickNumberRange.getAsDouble(0.0D)));
/* 285 */           mMOItem.setData(ItemStats.UPGRADE, (StatData)upgradeData);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 291 */     return mMOItem.newBuilder().buildNBT(true).toItem();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<String> getDescription(@NotNull String paramString1, @NotNull String paramString2) {
/* 299 */     if (!isValid(paramString1, paramString2, (FriendlyFeedbackProvider)null)) return SilentNumbers.toArrayList((Object[])new String[] { "This MMOItem is $finvalid$b." }); 
/* 300 */     paramString1 = paramString1.replace(" ", "_").replace("-", "_").toUpperCase();
/* 301 */     paramString2 = paramString2.replace(" ", "_").replace("-", "_").toUpperCase();
/* 302 */     return SilentNumbers.toArrayList((Object[])new String[] { SilentNumbers.getItemName(MMOItems.plugin.getItem(paramString1, paramString2)) });
/*     */   }
/*     */   
/*     */   public boolean determinateGeneration() {
/* 306 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean partialDeterminateGeneration(@NotNull String paramString1, @NotNull String paramString2) {
/* 312 */     return !isValid(paramString1, paramString2, (FriendlyFeedbackProvider)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getSourcePlugin() {
/* 322 */     return "MMOItems";
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getFilterName() {
/* 328 */     return "MMOItem";
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String exampleArgument() {
/* 334 */     return "CONSUMABLE";
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String exampleData() {
/* 340 */     return "MANGO";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void register() {
/* 347 */     global = new MMOItemUIFilter();
/* 348 */     UIFilterManager.registerUIFilter(global);
/* 349 */     VanillaMMOItemCountermatch.enable();
/* 350 */     reg = false;
/*     */   }
/*     */   private static boolean reg = true;
/*     */   static MMOItemUIFilter global;
/*     */   
/*     */   @NotNull
/*     */   public static MMOItemUIFilter get() {
/* 357 */     return global;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\MMOItemUIFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */