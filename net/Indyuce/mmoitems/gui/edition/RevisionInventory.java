/*     */ package net.Indyuce.mmoitems.gui.edition;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.util.ItemFactory;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public class RevisionInventory
/*     */   extends EditionInventory
/*     */ {
/*     */   static ItemStack name;
/*     */   static ItemStack lore;
/*     */   static ItemStack enchantments;
/*     */   static ItemStack upgrades;
/*     */   static ItemStack gemstones;
/*     */   static ItemStack soulbind;
/*     */   static ItemStack external;
/*     */   static ItemStack revisionID;
/*     */   static final String REVISION = "§6Revision ID";
/*     */   
/*     */   public RevisionInventory(@NotNull Player paramPlayer, @NotNull MMOItemTemplate paramMMOItemTemplate) {
/*  47 */     super(paramPlayer, paramMMOItemTemplate);
/*     */ 
/*     */     
/*  50 */     if (revisionID == null) {
/*     */ 
/*     */ 
/*     */       
/*  54 */       name = ItemFactory.of(Material.NAME_TAG).name("§3Name").lore(SilentNumbers.chop("The display name of the old item will be transferred to the new one", 40, "§7")).build();
/*     */ 
/*     */ 
/*     */       
/*  58 */       lore = ItemFactory.of(VersionMaterial.WRITABLE_BOOK.toMaterial()).name("§dLore").lore(SilentNumbers.chop("Specifically keeps lore lines that begin with the color code §n&7", 40, "§7")).build();
/*     */ 
/*     */ 
/*     */       
/*  62 */       enchantments = ItemFactory.of(Material.EXPERIENCE_BOTTLE).name("§bEnchantments").lore(SilentNumbers.chop("This keeps specifically enchantments that are not accounted for in upgrades nor gem stones (presumably added by the player).", 40, "§7")).build();
/*     */ 
/*     */ 
/*     */       
/*  66 */       upgrades = ItemFactory.of(Material.NETHER_STAR).name("§aUpgrades").lore(SilentNumbers.chop("Will this item retain the upgrade level after updating? Only the Upgrade Level is kept (as long as it does not exceed the new max).", 40, "§7")).build();
/*     */ 
/*     */ 
/*     */       
/*  70 */       gemstones = ItemFactory.of(Material.EMERALD).name("§eGem Stones").lore(SilentNumbers.chop("Will the item retain its gem stones when updating? (Note that this allows gemstone overflow - will keep ALL old gemstones even if you reduced the gem sockets)", 40, "§7")).build();
/*     */ 
/*     */ 
/*     */       
/*  74 */       soulbind = ItemFactory.of(Material.ENDER_EYE).name("§cSoulbind").lore(SilentNumbers.chop("If the old item is soulbound, updating will transfer the soulbind to the new item.", 40, "§7")).build();
/*     */ 
/*     */ 
/*     */       
/*  78 */       external = ItemFactory.of(Material.SPRUCE_SIGN).name("§9External SH").lore(SilentNumbers.chop("Data registered onto the item's StatHistory by external plugins (like GemStones but not removable)", 40, "§7")).build();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  84 */       revisionID = ItemFactory.of(Material.ITEM_FRAME).name("§6Revision ID").lore(SilentNumbers.chop("The updater is always active, increasing this number will update all instances of this MMOItem without further action.", 40, "§7")).build();
/*     */     } 
/*     */   }
/*     */   @NotNull
/*     */   public Inventory getInventory() {
/*  89 */     Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Revision Manager");
/*     */ 
/*     */     
/*  92 */     for (byte b = 0; b < inventory.getSize(); b++) {
/*     */ 
/*     */       
/*  95 */       ItemStack itemStack = null;
/*  96 */       Boolean bool = null;
/*  97 */       Integer integer = null;
/*  98 */       switch (b) {
/*     */         case 19:
/* 100 */           itemStack = name.clone();
/* 101 */           bool = Boolean.valueOf((MMOItems.plugin.getLanguage()).revisionOptions.shouldKeepName());
/*     */           break;
/*     */         case 20:
/* 104 */           itemStack = lore.clone();
/* 105 */           bool = Boolean.valueOf((MMOItems.plugin.getLanguage()).revisionOptions.shouldKeepLore());
/*     */           break;
/*     */         case 21:
/* 108 */           itemStack = enchantments.clone();
/* 109 */           bool = Boolean.valueOf((MMOItems.plugin.getLanguage()).revisionOptions.shouldKeepEnchantments());
/*     */           break;
/*     */         case 22:
/* 112 */           itemStack = external.clone();
/* 113 */           bool = Boolean.valueOf((MMOItems.plugin.getLanguage()).revisionOptions.shouldKeepExternalSH());
/*     */           break;
/*     */         case 28:
/* 116 */           itemStack = upgrades.clone();
/* 117 */           bool = Boolean.valueOf((MMOItems.plugin.getLanguage()).revisionOptions.shouldKeepUpgrades());
/*     */           break;
/*     */         case 29:
/* 120 */           itemStack = gemstones.clone();
/* 121 */           bool = Boolean.valueOf((MMOItems.plugin.getLanguage()).revisionOptions.shouldKeepGemStones());
/*     */           break;
/*     */         case 30:
/* 124 */           itemStack = soulbind.clone();
/* 125 */           bool = Boolean.valueOf((MMOItems.plugin.getLanguage()).revisionOptions.shouldKeepSoulBind());
/*     */           break;
/*     */         case 33:
/* 128 */           integer = Integer.valueOf(getEditedSection().getInt(ItemStats.REVISION_ID.getPath(), 1));
/* 129 */           itemStack = revisionID.clone();
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 136 */       if (itemStack != null)
/*     */       {
/*     */         
/* 139 */         if (bool != null) {
/*     */ 
/*     */           
/* 142 */           inventory.setItem(b, addLore(itemStack, new String[] { "", "§8Enabled (in config)? §6" + bool.toString() }));
/*     */         
/*     */         }
/* 145 */         else if (integer != null) {
/*     */ 
/*     */           
/* 148 */           inventory.setItem(b, addLore(itemStack, new String[] { "", "§8Current Value: §6" + integer }));
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 154 */           inventory.setItem(b, itemStack);
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 160 */     addEditionInventoryItems(inventory, true);
/*     */     
/* 162 */     return inventory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 168 */     ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
/* 169 */     paramInventoryClickEvent.setCancelled(true);
/*     */ 
/*     */     
/* 172 */     if (paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory() || !MMOUtils.isMetaItem(itemStack, false)) {
/*     */       return;
/*     */     }
/* 175 */     if (itemStack.getItemMeta().getDisplayName().equals("§6Revision ID")) {
/*     */ 
/*     */       
/* 178 */       int i = getEditedSection().getInt(ItemStats.REVISION_ID.getPath(), 1);
/*     */ 
/*     */       
/* 181 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/*     */ 
/*     */         
/* 184 */         i = Math.max(i - 1, 1);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 190 */         i = Math.min(i + 1, 2147483647);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 195 */       getEditedSection().set(ItemStats.REVISION_ID.getPath(), Integer.valueOf(i));
/* 196 */       registerTemplateEdition();
/*     */ 
/*     */       
/* 199 */       paramInventoryClickEvent.setCurrentItem(addLore(revisionID.clone(), new String[] { "", "§8Current Value: §6" + i }));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   ItemStack addLore(@NotNull ItemStack paramItemStack, String... paramVarArgs) {
/* 206 */     ArrayList arrayList = new ArrayList();
/* 207 */     ItemMeta itemMeta = paramItemStack.getItemMeta();
/* 208 */     if (itemMeta != null && itemMeta.getLore() != null) arrayList = new ArrayList(itemMeta.getLore());
/*     */ 
/*     */     
/* 211 */     arrayList.addAll(Arrays.asList(paramVarArgs));
/*     */ 
/*     */     
/* 214 */     itemMeta.setLore(arrayList);
/*     */ 
/*     */     
/* 217 */     paramItemStack.setItemMeta(itemMeta);
/*     */ 
/*     */     
/* 220 */     return paramItemStack;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\RevisionInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */