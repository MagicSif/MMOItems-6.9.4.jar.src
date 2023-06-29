/*     */ package net.Indyuce.mmoitems.api.crafting.ingredient;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*     */ import io.lumine.mythic.lib.api.util.ui.QuickNumberRange;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.crafting.ConfigMMOItem;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.MMOItemPlayerIngredient;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.PlayerIngredient;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*     */ import net.Indyuce.mmoitems.stat.DisplayName;
/*     */ import net.Indyuce.mmoitems.stat.data.MaterialData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class MMOItemIngredient
/*     */   extends Ingredient<MMOItemPlayerIngredient> {
/*     */   private final MMOItemTemplate template;
/*     */   
/*     */   public MMOItemIngredient(MMOLineConfig paramMMOLineConfig) {
/*  29 */     super("mmoitem", paramMMOLineConfig);
/*     */ 
/*     */     
/*  32 */     paramMMOLineConfig.validate(new String[] { "type", "id" });
/*  33 */     Type type = MMOItems.plugin.getTypes().getOrThrow(paramMMOLineConfig.getString("type").toUpperCase().replace("-", "_").replace(" ", "_"));
/*  34 */     this.template = MMOItems.plugin.getTemplates().getTemplateOrThrow(type, paramMMOLineConfig.getString("id"));
/*     */ 
/*     */     
/*  37 */     QuickNumberRange quickNumberRange = QuickNumberRange.getFromString(paramMMOLineConfig.getString("level", ".."));
/*  38 */     if (quickNumberRange != null) { this.level = quickNumberRange; } else { this.level = new QuickNumberRange(null, null); }
/*     */ 
/*     */     
/*  41 */     this.display = paramMMOLineConfig.contains("display") ? paramMMOLineConfig.getString("display") : findName();
/*     */   } @NotNull
/*     */   private final QuickNumberRange level; private final String display;
/*     */   public MMOItemIngredient(ConfigMMOItem paramConfigMMOItem) {
/*  45 */     super("mmoitem", paramConfigMMOItem.getAmount());
/*     */ 
/*     */     
/*  48 */     this.template = paramConfigMMOItem.getTemplate();
/*  49 */     this.level = new QuickNumberRange(null, null);
/*  50 */     this.display = findName();
/*     */   }
/*     */   
/*     */   public MMOItemTemplate getTemplate() {
/*  54 */     return this.template;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKey() {
/*  59 */     return "mmoitem:" + this.template.getType().getId().toLowerCase() + ((this.level.hasMax() || this.level.hasMax()) ? ("-" + this.level.toString()) : "") + "_" + this.template.getId().toLowerCase();
/*     */   }
/*     */ 
/*     */   
/*     */   public String formatDisplay(String paramString) {
/*  64 */     return paramString.replace("#item#", this.display).replace("#level#", (this.level.hasMax() || this.level.hasMax()) ? ("lvl." + this.level.toString() + " ") : "").replace("#amount#", String.valueOf(getAmount()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(MMOItemPlayerIngredient paramMMOItemPlayerIngredient) {
/*  71 */     if (!paramMMOItemPlayerIngredient.getType().equals(this.template.getType().getId())) {
/*  72 */       return false;
/*     */     }
/*     */     
/*  75 */     if (!paramMMOItemPlayerIngredient.getId().equals(this.template.getId())) {
/*  76 */       return false;
/*     */     }
/*     */     
/*  79 */     if (SilentNumbers.floor(this.level.getAsDouble(0.0D)) != 0 && !this.level.inRange(paramMMOItemPlayerIngredient.getUpgradeLevel())) {
/*  80 */       return false;
/*     */     }
/*     */     
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack generateItemStack(@NotNull RPGPlayer paramRPGPlayer, boolean paramBoolean) {
/*  91 */     MMOItem mMOItem = this.template.newBuilder(paramRPGPlayer, paramBoolean).build();
/*     */ 
/*     */     
/*  94 */     ItemStack itemStack = mMOItem.newBuilder().build(paramBoolean);
/*     */ 
/*     */     
/*  97 */     int i = SilentNumbers.floor(this.level.getAsDouble(0.0D));
/*  98 */     if (i != 0 && itemStack.getItemMeta() != null) {
/*     */       
/* 100 */       ItemMeta itemMeta = itemStack.getItemMeta();
/* 101 */       itemMeta.setDisplayName(MythicLib.plugin.parseColors(findName()));
/* 102 */       itemStack.setItemMeta(itemMeta);
/*     */     } 
/*     */     
/* 105 */     itemStack.setAmount(getAmount());
/*     */ 
/*     */     
/* 108 */     return itemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 113 */     return getKey();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String findName() {
/*     */     String str;
/* 120 */     if (this.template.getBaseItemData().containsKey(ItemStats.NAME)) {
/* 121 */       str = ((RandomStatData)this.template.getBaseItemData().get(ItemStats.NAME)).toString().replace("<tier-color>", "").replace("<tier-name>", "").replace("<tier-color-cleaned>", "");
/*     */     
/*     */     }
/* 124 */     else if (this.template.getBaseItemData().containsKey(ItemStats.MATERIAL)) {
/* 125 */       str = MMOUtils.caseOnWords(((MaterialData)this.template.getBaseItemData().get(ItemStats.MATERIAL)).getMaterial().name().toLowerCase().replace("_", " "));
/*     */     } else {
/*     */       
/* 128 */       str = "Unrecognized Item";
/*     */     } 
/*     */     
/* 131 */     if (SilentNumbers.floor(this.level.getAsDouble(0.0D)) != 0)
/* 132 */       return DisplayName.appendUpgradeLevel(str, SilentNumbers.floor(this.level.getAsDouble(0.0D))); 
/* 133 */     return str;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\ingredient\MMOItemIngredient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */