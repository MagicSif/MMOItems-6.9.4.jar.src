/*     */ package net.Indyuce.mmoitems.comp.placeholders;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.UtilityMethods;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.player.MMOPlayerData;
/*     */ import io.lumine.mythic.lib.manager.StatManager;
/*     */ import io.lumine.mythic.lib.util.DefenseFormula;
/*     */ import me.clip.placeholderapi.expansion.PlaceholderExpansion;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class MMOItemsPlaceholders
/*     */   extends PlaceholderExpansion
/*     */ {
/*     */   public String getAuthor() {
/*  26 */     return "Indyuce";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIdentifier() {
/*  31 */     return "mmoitems";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getVersion() {
/*  36 */     return MMOItems.plugin.getDescription().getVersion();
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
/*     */   public boolean persist() {
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String onRequest(@Nullable OfflinePlayer paramOfflinePlayer, @NotNull String paramString) {
/*  56 */     if (paramString.equals("stat_defense_percent")) {
/*  57 */       double d1 = MMOPlayerData.get(paramOfflinePlayer).getStatMap().getStat("DEFENSE");
/*  58 */       double d2 = 100.0D - (new DefenseFormula()).getAppliedDamage(d1, 100.0D);
/*  59 */       return (MythicLib.plugin.getMMOConfig()).decimal.format(d2);
/*     */     } 
/*     */     
/*  62 */     if (paramString.startsWith("stat_")) {
/*  63 */       String str = UtilityMethods.enumName(paramString.substring(5));
/*  64 */       return StatManager.format(str, MMOPlayerData.get(paramOfflinePlayer));
/*     */     } 
/*     */     
/*  67 */     if (paramString.startsWith("ability_cd_")) {
/*  68 */       return (MythicLib.plugin.getMMOConfig()).decimal.format(MMOPlayerData.get(paramOfflinePlayer).getCooldownMap().getCooldown("skill_" + paramString.substring(11)));
/*     */     }
/*  70 */     if (paramString.startsWith("type_")) {
/*  71 */       String str1 = paramString.substring(5, paramString.lastIndexOf("_")).toUpperCase();
/*  72 */       if (!MMOItems.plugin.getTypes().has(str1)) return "Invalid type"; 
/*  73 */       Type type = Type.get(str1);
/*  74 */       String str2 = paramString.substring(6 + str1.length()).toLowerCase();
/*  75 */       if ("total".equals(str2))
/*  76 */         return "" + MMOItems.plugin.getTemplates().getTemplates(type).size(); 
/*  77 */       return type.getName();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     if (paramString.startsWith("tier_")) {
/*  87 */       String str = paramString.substring(5).toUpperCase();
/*  88 */       if (!MMOItems.plugin.getTiers().has(str)) return "Invalid tier"; 
/*  89 */       return MMOItems.plugin.getTiers().get(str).getName();
/*     */     } 
/*     */     
/*  92 */     if (!paramOfflinePlayer.isOnline()) {
/*  93 */       return null;
/*     */     }
/*  95 */     if (paramString.equals("durability")) {
/*  96 */       NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramOfflinePlayer.getPlayer().getInventory().getItemInMainHand());
/*  97 */       return String.valueOf(nBTItem.hasTag("MMOITEMS_DURABILITY") ? nBTItem.getInteger("MMOITEMS_DURABILITY") : nBTItem.getInteger("MMOITEMS_MAX_DURABILITY"));
/*     */     } 
/*     */     
/* 100 */     if (paramString.equals("durability_max")) {
/* 101 */       return "" + 
/* 102 */         (int)MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramOfflinePlayer.getPlayer().getInventory().getItemInMainHand()).getDouble("MMOITEMS_MAX_DURABILITY");
/*     */     }
/* 104 */     if (paramString.equals("durability_ratio")) {
/* 105 */       NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramOfflinePlayer.getPlayer().getInventory().getItemInMainHand());
/* 106 */       double d1 = nBTItem.getDouble("MMOITEMS_DURABILITY");
/* 107 */       double d2 = nBTItem.getDouble("MMOITEMS_MAX_DURABILITY");
/* 108 */       return (MythicLib.plugin.getMMOConfig()).decimal.format(d1 / d2 * 100.0D);
/*     */     } 
/*     */     
/* 111 */     if (paramString.equals("durability_bar_square")) {
/* 112 */       return getCurrentDurabilityBar(paramOfflinePlayer.getPlayer().getInventory().getItemInMainHand(), "█", 10);
/*     */     }
/* 114 */     if (paramString.equals("durability_bar_diamond")) {
/* 115 */       return getCurrentDurabilityBar(paramOfflinePlayer.getPlayer().getInventory().getItemInMainHand(), "◆", 15);
/*     */     }
/* 117 */     if (paramString.equals("durability_bar_thin"))
/* 118 */       return getCurrentDurabilityBar(paramOfflinePlayer.getPlayer().getInventory().getItemInMainHand(), "|", 20); 
/* 119 */     return null;
/*     */   }
/*     */   
/*     */   private String getCurrentDurabilityBar(ItemStack paramItemStack, String paramString, int paramInt) {
/* 123 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramItemStack);
/* 124 */     double d1 = nBTItem.getDouble("MMOITEMS_DURABILITY");
/* 125 */     double d2 = nBTItem.getDouble("MMOITEMS_MAX_DURABILITY");
/* 126 */     long l = Math.round(d1 / d2 * paramInt);
/* 127 */     StringBuilder stringBuilder = new StringBuilder("" + ChatColor.GREEN);
/* 128 */     for (byte b = 0; b < paramInt; b++)
/* 129 */       stringBuilder.append((b == l) ? ChatColor.WHITE : "").append(paramString); 
/* 130 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   private boolean hasItem(Player paramPlayer, EquipmentSlot paramEquipmentSlot) {
/* 134 */     return (paramPlayer.getInventory().getItem(paramEquipmentSlot) != null && paramPlayer.getInventory().getItem(paramEquipmentSlot).getType() != Material.AIR);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\placeholders\MMOItemsPlaceholders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */