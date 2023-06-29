/*     */ package net.Indyuce.mmoitems.gui.edition;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.element.Element;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomElementListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.util.ElementStatType;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import net.Indyuce.mmoitems.util.Pair;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class ElementsEdition extends EditionInventory {
/*  27 */   private final List<Element> elements = new ArrayList<>();
/*     */   private final int maxPage;
/*  29 */   private final Map<Integer, Pair<Element, ElementStatType>> editableStats = new HashMap<>();
/*     */   
/*  31 */   private int page = 1;
/*     */   
/*  33 */   private static final int[] INIT_SLOTS = new int[] { 19, 28, 37 };
/*     */   private static final int ELEMENTS_PER_PAGE = 3;
/*     */   
/*     */   public ElementsEdition(Player paramPlayer, MMOItemTemplate paramMMOItemTemplate) {
/*  37 */     super(paramPlayer, paramMMOItemTemplate);
/*     */     
/*  39 */     this.elements.addAll(MythicLib.plugin.getElements().getAll());
/*  40 */     this.maxPage = 1 + (MythicLib.plugin.getElements().getAll().size() - 1) / 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getInventory() {
/*  45 */     Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Elements: " + this.template.getId());
/*     */     
/*  47 */     Optional<RandomStatData> optional = getEventualStatData(ItemStats.ELEMENTS);
/*     */     
/*  49 */     ItemStack itemStack1 = new ItemStack(Material.ARROW);
/*  50 */     ItemMeta itemMeta1 = itemStack1.getItemMeta();
/*  51 */     itemMeta1.setDisplayName(ChatColor.GREEN + "Previous Page");
/*  52 */     itemStack1.setItemMeta(itemMeta1);
/*  53 */     inventory.setItem(25, itemStack1);
/*     */     
/*  55 */     ItemStack itemStack2 = new ItemStack(Material.ARROW);
/*  56 */     ItemMeta itemMeta2 = itemStack2.getItemMeta();
/*  57 */     itemMeta2.setDisplayName(ChatColor.GREEN + "Next Page");
/*  58 */     itemStack2.setItemMeta(itemMeta2);
/*  59 */     inventory.setItem(43, itemStack2);
/*     */     
/*  61 */     this.editableStats.clear();
/*     */     
/*  63 */     int i = (this.page - 1) * 3;
/*  64 */     for (byte b = 0; b < 3; b++) {
/*  65 */       int j = i + b;
/*  66 */       if (j >= this.elements.size()) {
/*     */         break;
/*     */       }
/*  69 */       Element element = this.elements.get(j);
/*  70 */       byte b1 = 0;
/*  71 */       for (ElementStatType elementStatType : ElementStatType.values()) {
/*  72 */         ItemStack itemStack = new ItemStack(element.getIcon());
/*  73 */         ItemMeta itemMeta = itemStack.getItemMeta();
/*  74 */         itemMeta.setDisplayName(ChatColor.GREEN + element.getName() + " " + elementStatType.getName());
/*  75 */         ArrayList<String> arrayList = new ArrayList();
/*  76 */         arrayList.add(ChatColor.GRAY + "Current Value: " + ChatColor.GREEN + ((
/*  77 */             optional.isPresent() && ((RandomElementListData)optional.get()).hasStat(element, elementStatType)) ? 
/*  78 */             (String)((RandomElementListData)optional.get()).getStat(element, elementStatType) : 
/*  79 */             "---"));
/*  80 */         arrayList.add("");
/*  81 */         arrayList.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/*  82 */         arrayList.add(ChatColor.YELLOW + "►" + " Right click to remove this value.");
/*  83 */         itemMeta.setLore(arrayList);
/*  84 */         itemStack.setItemMeta(itemMeta);
/*     */         
/*  86 */         int k = INIT_SLOTS[b] + b1;
/*  87 */         inventory.setItem(k, itemStack);
/*  88 */         this.editableStats.put(Integer.valueOf(k), Pair.of(element, elementStatType));
/*  89 */         b1++;
/*     */       } 
/*     */     } 
/*     */     
/*  93 */     addEditionInventoryItems(inventory, true);
/*     */     
/*  95 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 100 */     ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
/*     */     
/* 102 */     paramInventoryClickEvent.setCancelled(true);
/* 103 */     if (paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory() || !MMOUtils.isMetaItem(itemStack, false)) {
/*     */       return;
/*     */     }
/* 106 */     if (this.page > 1 && itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Previous Page")) {
/* 107 */       this.page--;
/* 108 */       open();
/*     */       
/*     */       return;
/*     */     } 
/* 112 */     if (this.page < this.maxPage && itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Next Page")) {
/* 113 */       this.page++;
/* 114 */       open();
/*     */       
/*     */       return;
/*     */     } 
/* 118 */     Pair pair = this.editableStats.get(Integer.valueOf(paramInventoryClickEvent.getSlot()));
/* 119 */     if (pair == null) {
/*     */       return;
/*     */     }
/* 122 */     String str = ((ElementStatType)pair.getValue()).getConcatenatedConfigPath((Element)pair.getKey());
/*     */     
/* 124 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 125 */       (new StatEdition(this, ItemStats.ELEMENTS, new Object[] { str })).enable(new String[] { "Write in the value you want." });
/*     */     }
/* 127 */     else if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/* 128 */       getEditedSection().set("element." + str, null);
/*     */ 
/*     */       
/* 131 */       String str1 = ((Element)pair.getKey()).getId();
/* 132 */       if (getEditedSection().contains("element." + str1) && 
/* 133 */         getEditedSection().getConfigurationSection("element." + str1).getKeys(false).isEmpty()) {
/* 134 */         getEditedSection().set("element." + str1, null);
/* 135 */         if (getEditedSection().getConfigurationSection("element").getKeys(false).isEmpty()) {
/* 136 */           getEditedSection().set("element", null);
/*     */         }
/*     */       } 
/* 139 */       registerTemplateEdition();
/* 140 */       this.player.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + ((Element)pair.getKey()).getName() + " " + ((ElementStatType)pair.getValue()).getName() + ChatColor.GRAY + " successfully removed.");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\ElementsEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */