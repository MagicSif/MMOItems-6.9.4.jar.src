/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.ShieldPatternData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.DyeColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Banner;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.block.banner.Pattern;
/*     */ import org.bukkit.block.banner.PatternType;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.meta.BlockStateMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class ShieldPatternStat extends ItemStat<ShieldPatternData, ShieldPatternData> {
/*     */   public ShieldPatternStat() {
/*  37 */     super("SHIELD_PATTERN", Material.SHIELD, "Shield Pattern", new String[] { "The color & patterns", "of your shield." }, new String[] { "all" }, new Material[] { Material.SHIELD });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ShieldPatternData whenInitialized(Object paramObject) {
/*  43 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a config section");
/*  44 */     ConfigurationSection configurationSection = (ConfigurationSection)paramObject;
/*     */ 
/*     */     
/*  47 */     ShieldPatternData shieldPatternData = new ShieldPatternData(configurationSection.contains("color") ? DyeColor.valueOf(configurationSection.getString("color").toUpperCase().replace("-", "_").replace(" ", "_")) : null, new Pattern[0]);
/*     */ 
/*     */     
/*  50 */     for (String str : configurationSection.getKeys(false)) {
/*  51 */       if (!str.equalsIgnoreCase("color")) {
/*  52 */         String str1 = configurationSection.getString(str + ".pattern").toUpperCase().replace("-", "_").replace(" ", "_");
/*  53 */         PatternType patternType = PatternType.valueOf(str1);
/*     */         
/*  55 */         str1 = configurationSection.getString(str + ".color").toUpperCase().replace("-", "_").replace(" ", "_");
/*  56 */         DyeColor dyeColor = DyeColor.valueOf(str1);
/*     */         
/*  58 */         shieldPatternData.add(new Pattern(dyeColor, patternType));
/*     */       } 
/*     */     } 
/*  61 */     return shieldPatternData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull ShieldPatternData paramShieldPatternData) {
/*  66 */     BlockStateMeta blockStateMeta = (BlockStateMeta)paramItemStackBuilder.getMeta();
/*  67 */     Banner banner = (Banner)blockStateMeta.getBlockState();
/*  68 */     banner.setBaseColor(paramShieldPatternData.getBaseColor());
/*  69 */     banner.setPatterns(paramShieldPatternData.getPatterns());
/*  70 */     ((BlockStateMeta)paramItemStackBuilder.getMeta()).setBlockState((BlockState)banner);
/*  71 */     paramItemStackBuilder.getMeta().addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull ShieldPatternData paramShieldPatternData) {
/*  80 */     return new ArrayList<>();
/*     */   }
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  84 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  85 */       (new StatEdition(paramEditionInventory, ItemStats.SHIELD_PATTERN, new Object[] { Integer.valueOf(0) })).enable(new String[] { "Write in the chat the color of your shield." });
/*     */     }
/*  87 */     if (paramInventoryClickEvent.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
/*  88 */       paramEditionInventory.getEditedSection().set("shield-pattern.color", null);
/*     */       
/*  90 */       paramEditionInventory.registerTemplateEdition();
/*  91 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset the shield color.");
/*     */     } 
/*     */     
/*  94 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/*  95 */       (new StatEdition(paramEditionInventory, ItemStats.SHIELD_PATTERN, new Object[] { Integer.valueOf(1) })).enable(new String[] { "Write in the chat the pattern you want to add.", ChatColor.AQUA + "Format: [PATTERN_TYPE] [DYE_COLOR]" });
/*     */     }
/*     */     
/*  98 */     if (paramInventoryClickEvent.getAction() == InventoryAction.DROP_ONE_SLOT && paramEditionInventory.getEditedSection().contains("shield-pattern")) {
/*  99 */       Set<? extends String> set = paramEditionInventory.getEditedSection().getConfigurationSection("shield-pattern").getKeys(false);
/* 100 */       String str = (new ArrayList<>(set)).get(set.size() - 1);
/* 101 */       if (str.equalsIgnoreCase("color")) {
/*     */         return;
/*     */       }
/* 104 */       paramEditionInventory.getEditedSection().set("shield-pattern." + str, null);
/* 105 */       paramEditionInventory.registerTemplateEdition();
/* 106 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed the last pattern.");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 112 */     int i = ((Integer)paramVarArgs[0]).intValue();
/*     */     
/* 114 */     if (i == 1) {
/* 115 */       String[] arrayOfString = paramString.split(" ");
/* 116 */       Validate.isTrue((arrayOfString.length == 2), paramString + " is not a valid [PATTERN_TYPE] [DYE_COLOR].");
/*     */       
/* 118 */       PatternType patternType = PatternType.valueOf(arrayOfString[0].toUpperCase().replace("-", "_").replace(" ", "_"));
/* 119 */       DyeColor dyeColor1 = DyeColor.valueOf(arrayOfString[1].toUpperCase().replace("-", "_").replace(" ", "_"));
/*     */       
/* 121 */       int j = getNextAvailableKey(paramEditionInventory.getEditedSection().getConfigurationSection("shield-pattern"));
/* 122 */       Validate.isTrue((j >= 0), "You can have more than 100 shield patterns on a single item.");
/*     */       
/* 124 */       paramEditionInventory.getEditedSection().set("shield-pattern." + j + ".pattern", patternType.name());
/* 125 */       paramEditionInventory.getEditedSection().set("shield-pattern." + j + ".color", dyeColor1.name());
/* 126 */       paramEditionInventory.registerTemplateEdition();
/* 127 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin
/* 128 */           .getPrefix() + MMOUtils.caseOnWords(patternType.name().toLowerCase().replace("_", " ")) + " successfully added.");
/*     */       
/*     */       return;
/*     */     } 
/* 132 */     DyeColor dyeColor = DyeColor.valueOf(paramString.toUpperCase().replace("-", "_").replace(" ", "_"));
/* 133 */     paramEditionInventory.getEditedSection().set("shield-pattern.color", dyeColor.name());
/* 134 */     paramEditionInventory.registerTemplateEdition();
/* 135 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Shield color successfully changed.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<ShieldPatternData> paramOptional) {
/* 141 */     if (paramOptional.isPresent()) {
/* 142 */       paramList.add(ChatColor.GRAY + "Current Value:");
/* 143 */       ShieldPatternData shieldPatternData = paramOptional.get();
/* 144 */       paramList.add(ChatColor.GRAY + "* Base Color: " + (
/* 145 */           (shieldPatternData.getBaseColor() != null) ? (
/* 146 */           ChatColor.GREEN + MMOUtils.caseOnWords(shieldPatternData.getBaseColor().name().toLowerCase().replace("_", " "))) : (
/* 147 */           ChatColor.RED + "None")));
/* 148 */       shieldPatternData.getPatterns().forEach(paramPattern -> paramList.add(ChatColor.GRAY + "* " + ChatColor.GREEN + paramPattern.getPattern().name() + ChatColor.GRAY + " - " + ChatColor.GREEN + paramPattern.getColor().name()));
/*     */     }
/*     */     else {
/*     */       
/* 152 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*     */     } 
/* 154 */     paramList.add("");
/* 155 */     paramList.add(ChatColor.YELLOW + "►" + " Left Click to change the shield color.");
/* 156 */     paramList.add(ChatColor.YELLOW + "►" + " Shift Left Click to reset the shield color.");
/* 157 */     paramList.add(ChatColor.YELLOW + "►" + " Right Click to add a pattern.");
/* 158 */     paramList.add(ChatColor.YELLOW + "►" + " Drop to remove the last pattern.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 163 */     if (paramReadMMOItem.getNBT().getItem().getItemMeta() instanceof BlockStateMeta && ((BlockStateMeta)paramReadMMOItem
/* 164 */       .getNBT().getItem().getItemMeta()).hasBlockState() && ((BlockStateMeta)paramReadMMOItem
/* 165 */       .getNBT().getItem().getItemMeta()).getBlockState() instanceof Banner) {
/* 166 */       Banner banner = (Banner)((BlockStateMeta)paramReadMMOItem.getNBT().getItem().getItemMeta()).getBlockState();
/*     */       
/* 168 */       ShieldPatternData shieldPatternData = new ShieldPatternData(banner.getBaseColor(), new Pattern[0]);
/* 169 */       shieldPatternData.addAll(banner.getPatterns());
/* 170 */       paramReadMMOItem.setData(ItemStats.SHIELD_PATTERN, (StatData)shieldPatternData);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ShieldPatternData getClearStatData() {
/* 177 */     return new ShieldPatternData(DyeColor.YELLOW, new Pattern[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ShieldPatternData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 186 */     return null;
/*     */   }
/*     */   private int getNextAvailableKey(ConfigurationSection paramConfigurationSection) {
/* 189 */     for (byte b = 0; b < 100; b++) {
/* 190 */       if (!paramConfigurationSection.contains("" + b))
/* 191 */         return b; 
/* 192 */     }  return -1;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\ShieldPatternStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */