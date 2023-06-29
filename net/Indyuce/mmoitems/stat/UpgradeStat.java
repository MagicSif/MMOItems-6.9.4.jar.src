/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import com.google.gson.JsonParser;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.event.item.UpgradeItemEvent;
/*     */ import net.Indyuce.mmoitems.api.interaction.Consumable;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.gui.edition.UpgradingEdition;
/*     */ import net.Indyuce.mmoitems.stat.data.UpgradeData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ConsumableItemInteraction;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class UpgradeStat extends ItemStat<UpgradeData, UpgradeData> implements ConsumableItemInteraction {
/*  46 */   private static final Random random = new Random();
/*     */   
/*     */   public UpgradeStat() {
/*  49 */     super("UPGRADE", Material.FLINT, "Item Upgrading", new String[] { "Upgrading your item improves its", "current stats. It requires either a", "consumable or a specific crafting ", "station. Upgrading may sometimes &cfail&7..." }, new String[] { "piercing", "slashing", "blunt", "catalyst", "range", "tool", "armor", "consumable", "accessory" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UpgradeData whenInitialized(Object paramObject) {
/*  57 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a config section");
/*  58 */     return new UpgradeData((ConfigurationSection)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull UpgradeData paramUpgradeData) {
/*  63 */     if (!(paramUpgradeData instanceof UpgradeData)) {
/*     */       return;
/*     */     }
/*  66 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramUpgradeData));
/*     */ 
/*     */     
/*  69 */     if (paramUpgradeData.getMaxUpgrades() > 0) {
/*  70 */       paramItemStackBuilder.getLore().insert(getPath(), new String[] { MMOItems.plugin
/*  71 */             .getLanguage().getStatFormat(getPath()).replace("{value}", String.valueOf(paramUpgradeData.getMaxUpgrades())) });
/*     */     }
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull UpgradeData paramUpgradeData) {
/*  77 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  78 */     arrayList.add(new ItemTag(getNBTPath(), paramUpgradeData.toString()));
/*  79 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  84 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  85 */       (new UpgradingEdition(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited())).open(paramEditionInventory.getPage());
/*     */     }
/*  87 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && paramEditionInventory.getEditedSection().contains("upgrade")) {
/*  88 */       paramEditionInventory.getEditedSection().set("upgrade", null);
/*  89 */       paramEditionInventory.registerTemplateEdition();
/*  90 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset the upgrading setup.");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  97 */     if (paramVarArgs[0].equals("ref")) {
/*  98 */       paramEditionInventory.getEditedSection().set("upgrade.reference", paramString);
/*  99 */       paramEditionInventory.registerTemplateEdition();
/* 100 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin
/* 101 */           .getPrefix() + "Upgrading reference successfully changed to " + ChatColor.GOLD + paramString + ChatColor.GRAY + ".");
/*     */       
/*     */       return;
/*     */     } 
/* 105 */     if (paramVarArgs[0].equals("max")) {
/* 106 */       int i = Integer.parseInt(paramString);
/* 107 */       paramEditionInventory.getEditedSection().set("upgrade.max", Integer.valueOf(i));
/* 108 */       paramEditionInventory.registerTemplateEdition();
/* 109 */       paramEditionInventory.getPlayer()
/* 110 */         .sendMessage(MMOItems.plugin.getPrefix() + "Max upgrades successfully set to " + ChatColor.GOLD + i + ChatColor.GRAY + ".");
/*     */       
/*     */       return;
/*     */     } 
/* 114 */     if (paramVarArgs[0].equals("min")) {
/* 115 */       int i = Integer.parseInt(paramString);
/* 116 */       paramEditionInventory.getEditedSection().set("upgrade.min", Integer.valueOf(i));
/* 117 */       paramEditionInventory.registerTemplateEdition();
/* 118 */       paramEditionInventory.getPlayer()
/* 119 */         .sendMessage(MMOItems.plugin.getPrefix() + "Min level successfully set to " + ChatColor.GOLD + i + ChatColor.GRAY + ".");
/*     */       
/*     */       return;
/*     */     } 
/* 123 */     if (paramVarArgs[0].equals("rate")) {
/* 124 */       double d = MMOUtils.parseDouble(paramString);
/* 125 */       paramEditionInventory.getEditedSection().set("upgrade.success", Double.valueOf(d));
/* 126 */       paramEditionInventory.registerTemplateEdition();
/* 127 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin
/* 128 */           .getPrefix() + "Upgrading rate successfully set to " + ChatColor.GOLD + d + "%" + ChatColor.GRAY + ".");
/*     */       
/*     */       return;
/*     */     } 
/* 132 */     Validate.isTrue(MMOItems.plugin.getUpgrades().hasTemplate(paramString), "Could not find any upgrade template with ID '" + paramString + "'.");
/* 133 */     paramEditionInventory.getEditedSection().set("upgrade.template", paramString);
/* 134 */     paramEditionInventory.registerTemplateEdition();
/* 135 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin
/* 136 */         .getPrefix() + "Upgrading template successfully changed to " + ChatColor.GOLD + paramString + ChatColor.GRAY + ".");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 143 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 144 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath()))
/* 145 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING)); 
/* 146 */     UpgradeData upgradeData = getLoadedNBT(arrayList);
/* 147 */     if (upgradeData != null) paramReadMMOItem.setData(this, (StatData)upgradeData);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UpgradeData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 155 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */     
/* 157 */     if (itemTag != null) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 162 */         return new UpgradeData((new JsonParser()).parse((String)itemTag.getValue()).getAsJsonObject());
/*     */       }
/* 164 */       catch (JsonSyntaxException|IllegalStateException jsonSyntaxException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<UpgradeData> paramOptional) {
/* 177 */     paramList.add(ChatColor.YELLOW + "►" + " Left click to setup upgrading.");
/* 178 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public UpgradeData getClearStatData() {
/* 183 */     return new UpgradeData(null, null, false, false, 0, 0, 0.0D);
/*     */   }
/*     */   
/*     */   public boolean handleConsumableEffect(@NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull PlayerData paramPlayerData, @NotNull Consumable paramConsumable, @NotNull NBTItem paramNBTItem, Type paramType) {
/* 187 */     VolatileMMOItem volatileMMOItem = paramConsumable.getMMOItem();
/* 188 */     Player player = paramPlayerData.getPlayer();
/*     */     
/* 190 */     if (volatileMMOItem.hasData(ItemStats.UPGRADE) && paramNBTItem.hasTag(ItemStats.UPGRADE.getNBTPath())) {
/* 191 */       if (paramNBTItem.getItem().getAmount() > 1) {
/* 192 */         Message.CANT_UPGRADED_STACK.format(ChatColor.RED, new String[0]).send(player);
/* 193 */         player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 2.0F);
/* 194 */         return false;
/*     */       } 
/*     */       
/* 197 */       LiveMMOItem liveMMOItem = new LiveMMOItem(paramNBTItem);
/* 198 */       UpgradeData upgradeData1 = (UpgradeData)liveMMOItem.getData(ItemStats.UPGRADE);
/* 199 */       if (upgradeData1.isWorkbench()) {
/* 200 */         return false;
/*     */       }
/* 202 */       if (!upgradeData1.canLevelUp()) {
/* 203 */         Message.MAX_UPGRADES_HIT.format(ChatColor.RED, new String[0]).send(player);
/* 204 */         player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 2.0F);
/* 205 */         return false;
/*     */       } 
/*     */       
/* 208 */       UpgradeData upgradeData2 = (UpgradeData)volatileMMOItem.getData(ItemStats.UPGRADE);
/* 209 */       if (!upgradeData2.matchesReference(upgradeData1)) {
/* 210 */         Message.WRONG_UPGRADE_REFERENCE.format(ChatColor.RED, new String[0]).send(player);
/* 211 */         player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 2.0F);
/* 212 */         return false;
/*     */       } 
/*     */       
/* 215 */       UpgradeItemEvent upgradeItemEvent = new UpgradeItemEvent(paramPlayerData, volatileMMOItem, (MMOItem)liveMMOItem, upgradeData2, upgradeData1);
/* 216 */       Bukkit.getPluginManager().callEvent((Event)upgradeItemEvent);
/* 217 */       if (upgradeItemEvent.isCancelled()) {
/* 218 */         return false;
/*     */       }
/* 220 */       upgradeData1.upgrade((MMOItem)liveMMOItem);
/* 221 */       NBTItem nBTItem = liveMMOItem.newBuilder().buildNBT();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 228 */       if ((MMOItems.plugin.getLanguage()).upgradeRequirementsCheck && !paramPlayerData.getRPG().canUse(nBTItem, false)) {
/* 229 */         Message.UPGRADE_REQUIREMENT_SAFE_CHECK.format(ChatColor.RED, new String[0]).send(player);
/* 230 */         player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 2.0F);
/* 231 */         return false;
/*     */       } 
/*     */       
/* 234 */       if (random.nextDouble() > upgradeData2.getSuccess() * upgradeData1.getSuccess()) {
/* 235 */         Message.UPGRADE_FAIL.format(ChatColor.RED, new String[0]).send(player);
/* 236 */         if (upgradeData1.destroysOnFail())
/* 237 */           paramInventoryClickEvent.getCurrentItem().setAmount(0); 
/* 238 */         player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 2.0F);
/* 239 */         return true;
/*     */       } 
/*     */       
/* 242 */       Message.UPGRADE_SUCCESS.format(ChatColor.YELLOW, new String[] { "#item#", MMOUtils.getDisplayName(paramInventoryClickEvent.getCurrentItem()) }).send(player);
/* 243 */       paramInventoryClickEvent.getCurrentItem().setItemMeta(nBTItem.toItem().getItemMeta());
/* 244 */       player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2.0F);
/* 245 */       return true;
/*     */     } 
/* 247 */     return false;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\UpgradeStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */