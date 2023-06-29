/*     */ package net.Indyuce.mmoitems.gui.edition;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class ItemEdition extends EditionInventory {
/*  27 */   private static final int[] slots = new int[] { 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43 };
/*     */   
/*     */   public ItemEdition(Player paramPlayer, MMOItemTemplate paramMMOItemTemplate) {
/*  30 */     super(paramPlayer, paramMMOItemTemplate);
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getInventory() {
/*  35 */     int i = (this.page - 1) * slots.length;
/*  36 */     int j = this.page * slots.length;
/*  37 */     byte b = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  44 */     List<ItemStat> list = (new ArrayList(getEdited().getType().getAvailableStats())).stream().filter(paramItemStat -> (paramItemStat.hasValidMaterial(getCachedItem()) && !(paramItemStat instanceof net.Indyuce.mmoitems.stat.type.InternalStat))).toList();
/*     */     
/*  46 */     Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Item Edition: " + getEdited().getId());
/*  47 */     for (int k = i; k < Math.min(list.size(), j); k++) {
/*  48 */       ItemStat<RandomStatData, StatData> itemStat = list.get(k);
/*  49 */       ItemStack itemStack = new ItemStack(itemStat.getDisplayMaterial());
/*  50 */       ItemMeta itemMeta = itemStack.getItemMeta();
/*  51 */       itemMeta.addItemFlags(ItemFlag.values());
/*  52 */       itemMeta.setDisplayName(ChatColor.GREEN + itemStat.getName());
/*  53 */       List<String> list1 = MythicLib.plugin.parseColors(Arrays.<String>stream(itemStat.getLore()).map(paramString -> ChatColor.GRAY + paramString).toList());
/*  54 */       list1.add("");
/*     */       
/*  56 */       itemStat.whenDisplayed(list1, getEventualStatData(itemStat));
/*     */       
/*  58 */       itemMeta.setLore(list1);
/*  59 */       itemStack.setItemMeta(itemMeta);
/*  60 */       inventory.setItem(slots[b++], MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack).addTag(new ItemTag[] { new ItemTag("guiStat", itemStat.getId()) }).toItem());
/*     */     } 
/*     */     
/*  63 */     ItemStack itemStack1 = VersionMaterial.GRAY_STAINED_GLASS_PANE.toItem();
/*  64 */     ItemMeta itemMeta1 = itemStack1.getItemMeta();
/*  65 */     itemMeta1.setDisplayName(ChatColor.RED + "- No Item Stat -");
/*  66 */     itemStack1.setItemMeta(itemMeta1);
/*     */     
/*  68 */     ItemStack itemStack2 = new ItemStack(Material.ARROW);
/*  69 */     ItemMeta itemMeta2 = itemStack2.getItemMeta();
/*  70 */     itemMeta2.setDisplayName(ChatColor.GREEN + "Next Page");
/*  71 */     itemStack2.setItemMeta(itemMeta2);
/*     */     
/*  73 */     ItemStack itemStack3 = new ItemStack(Material.ARROW);
/*  74 */     ItemMeta itemMeta3 = itemStack3.getItemMeta();
/*  75 */     itemMeta3.setDisplayName(ChatColor.GREEN + "Previous Page");
/*  76 */     itemStack3.setItemMeta(itemMeta3);
/*     */     
/*  78 */     addEditionInventoryItems(inventory, true);
/*     */     
/*  80 */     while (b < slots.length)
/*  81 */       inventory.setItem(slots[b++], itemStack1); 
/*  82 */     inventory.setItem(27, (this.page > 1) ? itemStack3 : null);
/*  83 */     inventory.setItem(35, (list.size() > j) ? itemStack2 : null);
/*     */     
/*  85 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/*  90 */     paramInventoryClickEvent.setCancelled(true);
/*  91 */     if (paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory()) {
/*     */       return;
/*     */     }
/*  94 */     ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
/*  95 */     if (!MMOUtils.isMetaItem(itemStack, false) || paramInventoryClickEvent.getInventory().getItem(4) == null) {
/*     */       return;
/*     */     }
/*  98 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Next Page")) {
/*  99 */       this.page++;
/* 100 */       open();
/*     */     } 
/*     */     
/* 103 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Previous Page")) {
/* 104 */       this.page--;
/* 105 */       open();
/*     */     } 
/*     */     
/* 108 */     String str = NBTItem.get(itemStack).getString("guiStat");
/* 109 */     if (str.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 113 */     ItemStat itemStat = MMOItems.plugin.getStats().get(str);
/* 114 */     if (MMOItems.plugin.hasPermissions() && (MMOItems.plugin.getLanguage()).opStatsEnabled && 
/* 115 */       (MMOItems.plugin.getLanguage()).opStats.contains(itemStat.getId()) && 
/* 116 */       !MMOItems.plugin.getVault().getPermissions().has((Player)paramInventoryClickEvent.getWhoClicked(), "mmoitems.edit.op")) {
/* 117 */       paramInventoryClickEvent.getWhoClicked().sendMessage(ChatColor.RED + "You are lacking permission to edit this stat.");
/*     */       
/*     */       return;
/*     */     } 
/* 121 */     itemStat.whenClicked(this, paramInventoryClickEvent);
/*     */   }
/*     */   
/*     */   public ItemEdition onPage(int paramInt) {
/* 125 */     this.page = paramInt;
/* 126 */     return this;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\ItemEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */