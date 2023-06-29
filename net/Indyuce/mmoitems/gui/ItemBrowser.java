/*     */ package net.Indyuce.mmoitems.gui;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import io.lumine.mythic.lib.util.AdventureUtils;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.gui.edition.ItemEdition;
/*     */ import net.Indyuce.mmoitems.stat.BrowserDisplayIDX;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class ItemBrowser extends PluginInventory {
/*  33 */   private final Map<String, ItemStack> cached = new LinkedHashMap<>();
/*     */   
/*     */   private final Type type;
/*     */   
/*     */   private boolean deleteMode;
/*     */   
/*  39 */   private static final int[] slots = new int[] { 10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34 };
/*  40 */   private static final int[] slotsAlt = new int[] { 1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34 };
/*     */   
/*     */   private static final String CUSTOM_RP_DOWNLOAD_LINK = "https://www.dropbox.com/s/90w9pvdbfeyxu94/MICustomBlockPack.zip?dl=1";
/*     */   
/*     */   public ItemBrowser(Player paramPlayer) {
/*  45 */     this(paramPlayer, (Type)null);
/*     */   }
/*     */   
/*     */   public ItemBrowser(Player paramPlayer, Type paramType) {
/*  49 */     super(paramPlayer);
/*     */     
/*  51 */     this.type = paramType;
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
/*     */   @NotNull
/*     */   public Inventory getInventory() {
/*  66 */     if (this.type == null) {
/*     */       
/*  68 */       int[] arrayOfInt1 = slots;
/*  69 */       int i1 = (this.page - 1) * arrayOfInt1.length;
/*  70 */       int i2 = this.page * arrayOfInt1.length;
/*  71 */       byte b1 = 0;
/*     */ 
/*     */       
/*  74 */       Inventory inventory1 = Bukkit.createInventory(this, 54, "Item Explorer");
/*     */ 
/*     */       
/*  77 */       ArrayList<Type> arrayList1 = new ArrayList(MMOItems.plugin.getTypes().getAll());
/*  78 */       for (int i3 = i1; i3 < Math.min(i2, arrayList1.size()); i3++) {
/*     */ 
/*     */         
/*  81 */         Type type = arrayList1.get(i3);
/*     */ 
/*     */         
/*  84 */         int i4 = MMOItems.plugin.getTemplates().getTemplates(type).size();
/*     */ 
/*     */         
/*  87 */         ItemStack itemStack = type.getItem();
/*  88 */         itemStack.setAmount(Math.max(1, Math.min(64, i4)));
/*  89 */         ItemMeta itemMeta = itemStack.getItemMeta();
/*  90 */         AdventureUtils.setDisplayName(itemMeta, "&a%s&8 (lick to browse)".formatted(new Object[] { type.getName() }));
/*  91 */         itemMeta.addItemFlags(ItemFlag.values());
/*  92 */         ArrayList<String> arrayList2 = new ArrayList();
/*  93 */         arrayList2.add("&7&oThere %s %s &7&oitem%s in that currentType.".formatted(new Object[] { (i4 == 1) ? "is" : "are", (i4 < 1) ? "&c&ono" : "&6&o%d".formatted(new Object[] { Integer.valueOf(i4) }), (i4 == 1) ? "" : "s" }));
/*  94 */         AdventureUtils.setLore(itemMeta, arrayList2);
/*  95 */         itemStack.setItemMeta(itemMeta);
/*     */ 
/*     */         
/*  98 */         inventory1.setItem(slots[b1++], NBTItem.get(itemStack).addTag(new ItemTag[] { new ItemTag("typeId", type.getId()) }).toItem());
/*     */       } 
/*     */ 
/*     */       
/* 102 */       ItemStack itemStack8 = VersionMaterial.GRAY_STAINED_GLASS_PANE.toItem();
/* 103 */       ItemMeta itemMeta8 = itemStack8.getItemMeta();
/* 104 */       itemMeta8.setDisplayName(ChatColor.RED + "- No type -");
/* 105 */       itemStack8.setItemMeta(itemMeta8);
/*     */ 
/*     */       
/* 108 */       ItemStack itemStack9 = new ItemStack(Material.ARROW);
/* 109 */       ItemMeta itemMeta9 = itemStack9.getItemMeta();
/* 110 */       itemMeta9.setDisplayName(ChatColor.GREEN + "Next Page");
/* 111 */       itemStack9.setItemMeta(itemMeta9);
/*     */ 
/*     */       
/* 114 */       ItemStack itemStack10 = new ItemStack(Material.ARROW);
/* 115 */       ItemMeta itemMeta10 = itemStack10.getItemMeta();
/* 116 */       itemMeta10.setDisplayName(ChatColor.GREEN + "Previous Page");
/* 117 */       itemStack10.setItemMeta(itemMeta10);
/*     */ 
/*     */       
/* 120 */       while (b1 < slots.length) {
/* 121 */         inventory1.setItem(slots[b1++], itemStack8);
/*     */       }
/* 123 */       inventory1.setItem(18, (this.page > 1) ? itemStack10 : null);
/* 124 */       inventory1.setItem(26, (i2 >= MMOItems.plugin.getTypes().getAll().size()) ? null : itemStack9);
/*     */ 
/*     */       
/* 127 */       return inventory1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     Inventory inventory = Bukkit.createInventory(this, 54, (this.deleteMode ? "Delete Mode: " : "Item Explorer: ") + MythicLib.plugin.getAdventureParser().stripColors(this.type.getName()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     ItemStack itemStack1 = VersionMaterial.RED_STAINED_GLASS_PANE.toItem();
/* 143 */     ItemMeta itemMeta1 = itemStack1.getItemMeta();
/* 144 */     itemMeta1.setDisplayName(ChatColor.RED + "- Error -");
/* 145 */     ArrayList<String> arrayList = new ArrayList();
/* 146 */     arrayList.add("§§oAn error occurred while");
/* 147 */     arrayList.add("§§otrying to generate that item.");
/* 148 */     itemMeta1.setLore(arrayList);
/* 149 */     itemStack1.setItemMeta(itemMeta1);
/*     */     
/* 151 */     ItemStack itemStack2 = VersionMaterial.GRAY_STAINED_GLASS_PANE.toItem();
/* 152 */     ItemMeta itemMeta2 = itemStack2.getItemMeta();
/* 153 */     itemMeta2.setDisplayName(ChatColor.RED + "- No Item -");
/* 154 */     itemStack2.setItemMeta(itemMeta2);
/*     */     
/* 156 */     ItemStack itemStack3 = new ItemStack(Material.ARROW);
/* 157 */     ItemMeta itemMeta3 = itemStack3.getItemMeta();
/* 158 */     itemMeta3.setDisplayName(ChatColor.GREEN + "Next Page");
/* 159 */     itemStack3.setItemMeta(itemMeta3);
/*     */     
/* 161 */     ItemStack itemStack4 = new ItemStack(Material.ARROW);
/* 162 */     ItemMeta itemMeta4 = itemStack4.getItemMeta();
/* 163 */     itemMeta4.setDisplayName(ChatColor.GREEN + "⇨" + " Back");
/* 164 */     itemStack4.setItemMeta(itemMeta4);
/*     */     
/* 166 */     ItemStack itemStack5 = new ItemStack(VersionMaterial.WRITABLE_BOOK.toMaterial());
/* 167 */     ItemMeta itemMeta5 = itemStack5.getItemMeta();
/* 168 */     itemMeta5.setDisplayName(ChatColor.GREEN + "Create New");
/* 169 */     itemStack5.setItemMeta(itemMeta5);
/*     */     
/* 171 */     ItemStack itemStack6 = new ItemStack(VersionMaterial.CAULDRON.toMaterial());
/* 172 */     ItemMeta itemMeta6 = itemStack6.getItemMeta();
/* 173 */     itemMeta6.setDisplayName(ChatColor.RED + (this.deleteMode ? "Cancel Deletion" : "Delete Item"));
/* 174 */     itemStack6.setItemMeta(itemMeta6);
/*     */     
/* 176 */     ItemStack itemStack7 = new ItemStack(Material.ARROW);
/* 177 */     ItemMeta itemMeta7 = itemStack7.getItemMeta();
/* 178 */     itemMeta7.setDisplayName(ChatColor.GREEN + "Previous Page");
/* 179 */     itemStack7.setItemMeta(itemMeta7);
/*     */     
/* 181 */     if (this.type == Type.BLOCK) {
/* 182 */       ItemStack itemStack = new ItemStack(Material.HOPPER);
/* 183 */       ItemMeta itemMeta = itemStack.getItemMeta();
/* 184 */       itemMeta.setDisplayName(ChatColor.GREEN + "Download Default Resourcepack");
/* 185 */       itemMeta.setLore(Arrays.asList(new String[] { ChatColor.LIGHT_PURPLE + "Only seeing stone blocks?", "", ChatColor.RED + "By downloading the default resourcepack you can", ChatColor.RED + "edit the blocks however you want.", ChatColor.RED + "You will still have to add it to your server!" }));
/*     */ 
/*     */       
/* 188 */       itemStack.setItemMeta(itemMeta);
/* 189 */       inventory.setItem(45, itemStack);
/*     */     } 
/*     */ 
/*     */     
/* 193 */     HashMap hashMap = BrowserDisplayIDX.select(MMOItems.plugin.getTemplates().getTemplates(this.type));
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
/* 205 */     int[] arrayOfInt = this.type.isFourGUIMode() ? slotsAlt : slots;
/* 206 */     int i = (this.page - 1) * arrayOfInt.length;
/* 207 */     int j = this.page * arrayOfInt.length;
/* 208 */     int k = 0;
/*     */     
/* 210 */     byte b = this.type.isFourGUIMode() ? 4 : 3;
/* 211 */     int m = 0;
/*     */     
/* 213 */     for (Map.Entry entry : hashMap.entrySet()) {
/*     */ 
/*     */       
/* 216 */       int i1 = ((ArrayList)entry.getValue()).size();
/* 217 */       while (i1 > 0) {
/* 218 */         m += b;
/* 219 */         i1 -= b;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 226 */     for (int n = i; n < Math.min(j, m); n++) {
/* 227 */       MMOItemTemplate mMOItemTemplate = BrowserDisplayIDX.getAt(n, hashMap);
/*     */ 
/*     */       
/* 230 */       if (mMOItemTemplate == null) {
/*     */ 
/*     */         
/* 233 */         inventory.setItem(arrayOfInt[k], itemStack2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 243 */         k += true;
/* 244 */         if (k >= arrayOfInt.length) {
/* 245 */           k -= 7 * b;
/* 246 */           k++;
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 252 */         ItemStack itemStack = mMOItemTemplate.newBuilder(this.playerData.getRPG(), true).build().newBuilder().build();
/* 253 */         if (itemStack == null || itemStack.getType().isAir() || !itemStack.getType().isItem() || itemStack.getItemMeta() == null) {
/*     */ 
/*     */           
/* 256 */           this.cached.put(mMOItemTemplate.getId(), itemStack1);
/* 257 */           inventory.setItem(arrayOfInt[k], itemStack1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 267 */           k += 7;
/* 268 */           if (k >= arrayOfInt.length) {
/* 269 */             k -= 7 * b;
/* 270 */             k++;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 275 */           ItemMeta itemMeta = itemStack.getItemMeta();
/* 276 */           List<String> list = itemMeta.getLore();
/* 277 */           if (list == null) {
/* 278 */             list = new ArrayList();
/*     */           }
/* 280 */           list.add("");
/*     */ 
/*     */           
/* 283 */           if (this.deleteMode) {
/* 284 */             list.add(ChatColor.RED + "✖" + " CLICK TO DELETE " + "✖");
/* 285 */             itemMeta.setDisplayName(ChatColor.RED + "DELETE: " + (itemMeta.hasDisplayName() ? itemMeta.getDisplayName() : MMOUtils.getDisplayName(itemStack)));
/*     */           }
/*     */           else {
/*     */             
/* 289 */             list.add(ChatColor.YELLOW + "▸" + " Left click to obtain this item.");
/* 290 */             list.add(ChatColor.YELLOW + "▸" + " Right click to edit this item.");
/*     */           } 
/*     */           
/* 293 */           itemMeta.setLore(list);
/* 294 */           itemStack.setItemMeta(itemMeta);
/*     */ 
/*     */           
/* 297 */           this.cached.put(mMOItemTemplate.getId(), itemStack);
/* 298 */           inventory.setItem(arrayOfInt[k], this.cached.get(mMOItemTemplate.getId()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 308 */           k += 7;
/* 309 */           if (k >= arrayOfInt.length) {
/* 310 */             k -= 7 * b;
/* 311 */             k++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 316 */     if (!this.deleteMode) {
/* 317 */       inventory.setItem(51, itemStack5);
/*     */     }
/* 319 */     inventory.setItem(47, itemStack6);
/* 320 */     inventory.setItem(49, itemStack4);
/* 321 */     inventory.setItem(18, (this.page > 1) ? itemStack7 : null);
/* 322 */     inventory.setItem(26, (j >= m) ? null : itemStack3);
/* 323 */     for (int i1 : arrayOfInt) {
/* 324 */       if (SilentNumbers.isAir(inventory.getItem(i1))) {
/* 325 */         inventory.setItem(i1, itemStack2);
/*     */       }
/*     */     } 
/* 328 */     return inventory;
/*     */   }
/*     */   
/*     */   public Type getType() {
/* 332 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 337 */     paramInventoryClickEvent.setCancelled(true);
/* 338 */     if (paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory()) {
/*     */       return;
/*     */     }
/* 341 */     ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
/* 342 */     if (MMOUtils.isMetaItem(itemStack, false)) {
/* 343 */       if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Next Page")) {
/* 344 */         this.page++;
/* 345 */         open();
/* 346 */       } else if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Previous Page")) {
/* 347 */         this.page--;
/* 348 */         open();
/* 349 */       } else if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "⇨" + " Back")) {
/* 350 */         (new ItemBrowser(getPlayer())).open();
/*     */       }
/* 352 */       else if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.RED + "Cancel Deletion")) {
/* 353 */         this.deleteMode = false;
/* 354 */         open();
/* 355 */       } else if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Create New")) {
/* 356 */         (new NewItemEdition(this)).enable(new String[] { "Write in the chat the text you want." });
/*     */       }
/* 358 */       else if (this.type != null && itemStack.getItemMeta().getDisplayName().equals(ChatColor.RED + "Delete Item")) {
/* 359 */         this.deleteMode = true;
/* 360 */         open();
/* 361 */       } else if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Download Default Resourcepack")) {
/* 362 */         MythicLib.plugin.getVersion().getWrapper().sendJson(getPlayer(), "[{\"text\":\"Click to download!\",\"color\":\"green\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.dropbox.com/s/90w9pvdbfeyxu94/MICustomBlockPack.zip?dl=1\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\"Click to download via Dropbox\",\"italic\":true,\"color\":\"white\"}]}}]");
/*     */         
/* 364 */         getPlayer().closeInventory();
/* 365 */       } else if (this.type == null && !itemStack.getItemMeta().getDisplayName().equals(ChatColor.RED + "- No type -")) {
/* 366 */         (new ItemBrowser(getPlayer(), MMOItems.plugin.getTypes().get(NBTItem.get(itemStack).getString("typeId")))).open();
/*     */       } 
/*     */     }
/* 369 */     String str = NBTItem.get(itemStack).getString("MMOITEMS_ITEM_ID");
/* 370 */     if (str.equals("")) {
/*     */       return;
/*     */     }
/* 373 */     if (this.deleteMode) {
/* 374 */       MMOItems.plugin.getTemplates().deleteTemplate(this.type, str);
/* 375 */       this.deleteMode = false;
/* 376 */       open();
/*     */     } else {
/*     */       
/* 379 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 380 */         getPlayer().getInventory().addItem(new ItemStack[] { MMOItems.plugin.getItem(this.type, str, this.playerData) });
/* 381 */         getPlayer().playSound(getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 2.0F);
/*     */       } 
/*     */       
/* 384 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF)
/* 385 */         (new ItemEdition(getPlayer(), MMOItems.plugin.getTemplates().getTemplate(this.type, str))).open(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\ItemBrowser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */