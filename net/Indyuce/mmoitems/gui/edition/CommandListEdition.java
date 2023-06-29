/*     */ package net.Indyuce.mmoitems.gui.edition;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class CommandListEdition
/*     */   extends EditionInventory
/*     */ {
/*  26 */   private static final int[] slots = new int[] { 19, 20, 21, 22, 23, 24, 25, 28, 29, 33, 34, 37, 38, 42, 43 };
/*     */   
/*     */   public CommandListEdition(Player paramPlayer, MMOItemTemplate paramMMOItemTemplate) {
/*  29 */     super(paramPlayer, paramMMOItemTemplate);
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getInventory() {
/*  34 */     Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Command List");
/*  35 */     byte b = 0;
/*     */     
/*  37 */     if (getEditedSection().contains("commands")) {
/*  38 */       for (String str1 : getEditedSection().getConfigurationSection("commands").getKeys(false)) {
/*     */         
/*  40 */         String str2 = getEditedSection().getString("commands." + str1 + ".format");
/*  41 */         double d = getEditedSection().getDouble("commands." + str1 + ".delay");
/*  42 */         boolean bool1 = getEditedSection().getBoolean("commands." + str1 + ".console");
/*  43 */         boolean bool2 = getEditedSection().getBoolean("commands." + str1 + ".op");
/*     */         
/*  45 */         ItemStack itemStack = new ItemStack(VersionMaterial.COMPARATOR.toMaterial());
/*  46 */         ItemMeta itemMeta = itemStack.getItemMeta();
/*  47 */         itemMeta.setDisplayName((str2 == null || str2.equals("")) ? (ChatColor.RED + "No Format") : (ChatColor.GREEN + str2));
/*  48 */         ArrayList<String> arrayList = new ArrayList();
/*  49 */         arrayList.add("");
/*  50 */         arrayList.add(ChatColor.GRAY + "Command Delay: " + ChatColor.RED + d);
/*  51 */         arrayList.add(ChatColor.GRAY + "Sent by Console: " + ChatColor.RED + bool1);
/*  52 */         arrayList.add(ChatColor.GRAY + "Sent w/ OP perms: " + ChatColor.RED + bool2);
/*  53 */         arrayList.add("");
/*  54 */         arrayList.add(ChatColor.YELLOW + "►" + " Right click to remove.");
/*  55 */         itemMeta.setLore(arrayList);
/*  56 */         itemStack.setItemMeta(itemMeta);
/*     */         
/*  58 */         inventory.setItem(slots[b++], NBTItem.get(itemStack).addTag(new ItemTag[] { new ItemTag("configKey", str1) }).toItem());
/*     */       } 
/*     */     }
/*  61 */     ItemStack itemStack1 = VersionMaterial.GRAY_STAINED_GLASS_PANE.toItem();
/*  62 */     ItemMeta itemMeta1 = itemStack1.getItemMeta();
/*  63 */     itemMeta1.setDisplayName(ChatColor.RED + "- No Command -");
/*  64 */     itemStack1.setItemMeta(itemMeta1);
/*     */     
/*  66 */     ItemStack itemStack2 = new ItemStack(VersionMaterial.WRITABLE_BOOK.toMaterial());
/*  67 */     ItemMeta itemMeta2 = itemStack2.getItemMeta();
/*  68 */     itemMeta2.setDisplayName(ChatColor.GREEN + "Register a command...");
/*  69 */     itemStack2.setItemMeta(itemMeta2);
/*     */     
/*  71 */     inventory.setItem(40, itemStack2);
/*  72 */     while (b < slots.length)
/*  73 */       inventory.setItem(slots[b++], itemStack1); 
/*  74 */     addEditionInventoryItems(inventory, true);
/*     */     
/*  76 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/*  81 */     ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
/*     */     
/*  83 */     paramInventoryClickEvent.setCancelled(true);
/*  84 */     if (paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory() || !MMOUtils.isMetaItem(itemStack, false)) {
/*     */       return;
/*     */     }
/*  87 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Register a command...")) {
/*  88 */       (new StatEdition(this, ItemStats.COMMANDS, new Object[0])).enable(new String[] { "Write in the chat the command you want to add.", "", "To add a delay, use " + ChatColor.RED + "-d:<delay>", "To make the command cast itself w/ console, use " + ChatColor.RED + "-c", "To make the command cast w/ OP perms, use " + ChatColor.RED + "-op", "", ChatColor.YELLOW + "Ex: -d:10.3 -op bc Hello, this is a test command." });
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  94 */     String str = MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack).getString("configKey");
/*  95 */     if (str.equals("")) {
/*     */       return;
/*     */     }
/*  98 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/*  99 */       getEditedSection().contains("commands") && getEditedSection().getConfigurationSection("commands").contains(str)) {
/* 100 */       getEditedSection().set("commands." + str, null);
/* 101 */       registerTemplateEdition();
/* 102 */       this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + ChatColor.GOLD + str + ChatColor.DARK_GRAY + " (Internal ID)" + ChatColor.GRAY + ".");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\CommandListEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */