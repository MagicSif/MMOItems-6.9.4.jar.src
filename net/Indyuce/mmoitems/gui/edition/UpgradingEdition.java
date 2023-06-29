/*     */ package net.Indyuce.mmoitems.gui.edition;
/*     */ 
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.item.util.NamedItemStack;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class UpgradingEdition
/*     */   extends EditionInventory
/*     */ {
/*  27 */   private static final ItemStack notAvailable = (ItemStack)new NamedItemStack(VersionMaterial.RED_STAINED_GLASS_PANE.toMaterial(), "&cNot Available");
/*     */   
/*     */   public UpgradingEdition(Player paramPlayer, MMOItemTemplate paramMMOItemTemplate) {
/*  30 */     super(paramPlayer, paramMMOItemTemplate);
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getInventory() {
/*  35 */     Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Upgrade Setup: " + this.template.getId());
/*     */     
/*  37 */     boolean bool = getEditedSection().getBoolean("upgrade.workbench");
/*  38 */     if (!this.template.getType().corresponds(Type.CONSUMABLE)) {
/*     */       
/*  40 */       ItemStack itemStack1 = new ItemStack(VersionMaterial.CRAFTING_TABLE.toMaterial());
/*  41 */       ItemMeta itemMeta1 = itemStack1.getItemMeta();
/*  42 */       itemMeta1.setDisplayName(ChatColor.GREEN + "Workbench Upgrade Only?");
/*  43 */       ArrayList<String> arrayList1 = new ArrayList();
/*  44 */       arrayList1.add(ChatColor.GRAY + "When toggled on, players must");
/*  45 */       arrayList1.add(ChatColor.GRAY + "use a crafting station recipe in");
/*  46 */       arrayList1.add(ChatColor.GRAY + "order to upgrade their weapon.");
/*  47 */       arrayList1.add("");
/*  48 */       arrayList1.add(ChatColor.GRAY + "Current Value: " + ChatColor.GOLD + bool);
/*  49 */       arrayList1.add("");
/*  50 */       arrayList1.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/*  51 */       itemMeta1.setLore(arrayList1);
/*  52 */       itemStack1.setItemMeta(itemMeta1);
/*  53 */       inventory.setItem(20, itemStack1);
/*     */       
/*  55 */       String str = getEditedSection().getString("upgrade.template");
/*  56 */       ItemStack itemStack2 = new ItemStack(VersionMaterial.OAK_SIGN.toMaterial());
/*  57 */       ItemMeta itemMeta2 = itemStack2.getItemMeta();
/*  58 */       itemMeta2.setDisplayName(ChatColor.GREEN + "Upgrade Template");
/*  59 */       ArrayList<String> arrayList2 = new ArrayList();
/*  60 */       arrayList2.add(ChatColor.GRAY + "This option dictates what stats are improved");
/*  61 */       arrayList2.add(ChatColor.GRAY + "when your item is upgraded. More info on the wiki.");
/*  62 */       arrayList2.add("");
/*  63 */       arrayList2.add(ChatColor.GRAY + "Current Value: " + (
/*  64 */           (str == null) ? (ChatColor.RED + "No template") : (ChatColor.GOLD + str)));
/*  65 */       arrayList2.add("");
/*  66 */       arrayList2.add(ChatColor.YELLOW + "►" + " Click to input the template.");
/*  67 */       arrayList2.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/*  68 */       itemMeta2.setLore(arrayList2);
/*  69 */       itemStack2.setItemMeta(itemMeta2);
/*  70 */       inventory.setItem(22, itemStack2);
/*     */       
/*  72 */       int i = getEditedSection().getInt("upgrade.max");
/*  73 */       ItemStack itemStack3 = new ItemStack(Material.BARRIER);
/*  74 */       ItemMeta itemMeta3 = itemStack3.getItemMeta();
/*  75 */       itemMeta3.setDisplayName(ChatColor.GREEN + "Max Upgrades");
/*  76 */       ArrayList<String> arrayList3 = new ArrayList();
/*  77 */       arrayList3.add(ChatColor.GRAY + "The maximum amount of upgrades your");
/*  78 */       arrayList3.add(ChatColor.GRAY + "item may receive (recipe or consumable).");
/*  79 */       arrayList3.add("");
/*  80 */       arrayList3.add(ChatColor.GRAY + "Current Value: " + ((i == 0) ? (ChatColor.RED + "No limit") : (ChatColor.GOLD + "" + i)));
/*  81 */       arrayList3.add("");
/*  82 */       arrayList3.add(ChatColor.YELLOW + "►" + " Click to chance this value.");
/*  83 */       arrayList3.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/*  84 */       itemMeta3.setLore(arrayList3);
/*  85 */       itemStack3.setItemMeta(itemMeta3);
/*  86 */       inventory.setItem(40, itemStack3);
/*     */       
/*  88 */       int j = getEditedSection().getInt("upgrade.min", 0);
/*  89 */       ItemStack itemStack4 = new ItemStack(Material.BARRIER);
/*  90 */       ItemMeta itemMeta4 = itemStack4.getItemMeta();
/*  91 */       itemMeta4.setDisplayName(ChatColor.GREEN + "Min Upgrades");
/*  92 */       ArrayList<String> arrayList4 = new ArrayList();
/*  93 */       arrayList4.add(ChatColor.GRAY + "The minimum level your item can be");
/*  94 */       arrayList4.add(ChatColor.GRAY + "downgraded to (by dying or breaking).");
/*  95 */       arrayList4.add("");
/*  96 */       arrayList4.add(ChatColor.GRAY + "Current Value: " + ((j == 0) ? (ChatColor.RED + "0") : (ChatColor.GOLD + String.valueOf(j))));
/*  97 */       arrayList4.add("");
/*  98 */       arrayList4.add(ChatColor.YELLOW + "►" + " Click to chance this value.");
/*  99 */       arrayList4.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/* 100 */       itemMeta4.setLore(arrayList4);
/* 101 */       itemStack4.setItemMeta(itemMeta4);
/* 102 */       inventory.setItem(41, itemStack4);
/*     */     } else {
/* 104 */       inventory.setItem(20, notAvailable);
/* 105 */       inventory.setItem(22, notAvailable);
/*     */     } 
/*     */     
/* 108 */     if (!bool || this.template.getType().corresponds(Type.CONSUMABLE)) {
/*     */       
/* 110 */       String str = getEditedSection().getString("upgrade.reference");
/* 111 */       ItemStack itemStack1 = new ItemStack(Material.PAPER);
/* 112 */       ItemMeta itemMeta1 = itemStack1.getItemMeta();
/* 113 */       itemMeta1.setDisplayName(ChatColor.GREEN + "Upgrade Reference");
/* 114 */       ArrayList<String> arrayList1 = new ArrayList();
/* 115 */       arrayList1.add(ChatColor.GRAY + "This option dictates what consumables can");
/* 116 */       arrayList1.add(ChatColor.GRAY + "upgrade your item. " + ChatColor.AQUA + "The consumable upgrade");
/* 117 */       arrayList1.add(ChatColor.AQUA + "reference must match your item's reference" + ChatColor.GRAY + ",");
/* 118 */       arrayList1.add(ChatColor.GRAY + "otherwise it can't upgrade it. Leave this blank");
/* 119 */       arrayList1.add(ChatColor.GRAY + "so any consumable can upgrade this item.");
/* 120 */       arrayList1.add("");
/* 121 */       arrayList1
/* 122 */         .add(ChatColor.GRAY + "Current Value: " + ((str == null) ? (ChatColor.RED + "No reference") : (ChatColor.GOLD + str)));
/* 123 */       arrayList1.add("");
/* 124 */       arrayList1.add(ChatColor.YELLOW + "►" + " Click to input the reference.");
/* 125 */       arrayList1.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/* 126 */       itemMeta1.setLore(arrayList1);
/* 127 */       itemStack1.setItemMeta(itemMeta1);
/* 128 */       inventory.setItem(38, itemStack1);
/*     */     } else {
/* 130 */       inventory.setItem(38, notAvailable);
/*     */     } 
/* 132 */     double d = getEditedSection().getDouble("upgrade.success");
/* 133 */     ItemStack itemStack = new ItemStack(VersionMaterial.EXPERIENCE_BOTTLE.toMaterial());
/* 134 */     ItemMeta itemMeta = itemStack.getItemMeta();
/* 135 */     itemMeta.setDisplayName(ChatColor.GREEN + "Success Chance");
/* 136 */     ArrayList<String> arrayList = new ArrayList();
/* 137 */     arrayList.add(ChatColor.GRAY + "The chance of successfully upgrading");
/* 138 */     arrayList.add(ChatColor.GRAY + "when using a consumable or when using");
/* 139 */     arrayList.add(ChatColor.GRAY + "a station upgrading recipe.");
/* 140 */     arrayList.add("");
/* 141 */     arrayList.add(ChatColor.GRAY + "Current Value: " + ChatColor.GOLD + ((d == 0.0D) ? "100" : ("" + d)) + "%");
/* 142 */     arrayList.add("");
/* 143 */     arrayList.add(ChatColor.YELLOW + "►" + " Left click to change this value.");
/* 144 */     arrayList.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/* 145 */     itemMeta.setLore(arrayList);
/* 146 */     itemStack.setItemMeta(itemMeta);
/* 147 */     inventory.setItem(24, itemStack);
/*     */     
/* 149 */     if (d > 0.0D && !this.template.getType().corresponds(Type.CONSUMABLE)) {
/* 150 */       ItemStack itemStack1 = new ItemStack(Material.FISHING_ROD);
/* 151 */       ItemMeta itemMeta1 = itemStack1.getItemMeta();
/* 152 */       ((Damageable)itemMeta1).setDamage(30);
/* 153 */       itemMeta1.setDisplayName(ChatColor.GREEN + "Destroy on fail?");
/* 154 */       ArrayList<String> arrayList1 = new ArrayList();
/* 155 */       arrayList1.add(ChatColor.GRAY + "When toggled on, the item will be");
/* 156 */       arrayList1.add(ChatColor.GRAY + "destroyed when failing at upgrading it.");
/* 157 */       arrayList1.add("");
/* 158 */       arrayList1.add(ChatColor.GRAY + "Current Value: " + ChatColor.GOLD + getEditedSection().getBoolean("upgrade.destroy"));
/* 159 */       arrayList1.add("");
/* 160 */       arrayList1.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/* 161 */       itemMeta1.setLore(arrayList1);
/* 162 */       itemStack1.setItemMeta(itemMeta1);
/* 163 */       inventory.setItem(42, itemStack1);
/*     */     } 
/*     */     
/* 166 */     addEditionInventoryItems(inventory, true);
/*     */     
/* 168 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 173 */     ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
/*     */     
/* 175 */     paramInventoryClickEvent.setCancelled(true);
/* 176 */     if (paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory() || !MMOUtils.isMetaItem(itemStack, false)) {
/*     */       return;
/*     */     }
/* 179 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Success Chance")) {
/* 180 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 181 */         (new StatEdition(this, ItemStats.UPGRADE, new Object[] { "rate" })).enable(new String[] { "Write in the chat the success rate you want." });
/*     */       }
/* 183 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && getEditedSection().contains("upgrade.success")) {
/* 184 */         getEditedSection().set("upgrade.success", null);
/* 185 */         registerTemplateEdition();
/* 186 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset success chance.");
/*     */       } 
/*     */     } 
/*     */     
/* 190 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Max Upgrades")) {
/* 191 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 192 */         (new StatEdition(this, ItemStats.UPGRADE, new Object[] { "max" })).enable(new String[] { "Write in the chat the number you want." });
/*     */       }
/* 194 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && getEditedSection().contains("upgrade.max")) {
/* 195 */         getEditedSection().set("upgrade.max", null);
/* 196 */         registerTemplateEdition();
/* 197 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset the number of max upgrades.");
/*     */       } 
/*     */     } 
/*     */     
/* 201 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Min Upgrades")) {
/* 202 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 203 */         (new StatEdition(this, ItemStats.UPGRADE, new Object[] { "min" })).enable(new String[] { "Write in the chat the number you want." });
/*     */       }
/* 205 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && getEditedSection().contains("upgrade.min")) {
/* 206 */         getEditedSection().set("upgrade.min", null);
/* 207 */         registerTemplateEdition();
/* 208 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset the number of min level.");
/*     */       } 
/*     */     } 
/*     */     
/* 212 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Upgrade Template")) {
/* 213 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 214 */         (new StatEdition(this, ItemStats.UPGRADE, new Object[] { "template" })).enable(new String[] { "Write in the chat the upgrade template ID you want." });
/*     */       }
/* 216 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && getEditedSection().contains("upgrade.template")) {
/* 217 */         getEditedSection().set("upgrade.template", null);
/* 218 */         registerTemplateEdition();
/* 219 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset upgrade template.");
/*     */       } 
/*     */     } 
/*     */     
/* 223 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Upgrade Reference")) {
/* 224 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 225 */         (new StatEdition(this, ItemStats.UPGRADE, new Object[] { "ref" })).enable(new String[] { "Write in the chat the upgrade reference (text) you want." });
/*     */       }
/* 227 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && getEditedSection().contains("upgrade.reference")) {
/* 228 */         getEditedSection().set("upgrade.reference", null);
/* 229 */         registerTemplateEdition();
/* 230 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset upgrade reference.");
/*     */       } 
/*     */     } 
/*     */     
/* 234 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Workbench Upgrade Only?")) {
/* 235 */       boolean bool = !getEditedSection().getBoolean("upgrade.workbench") ? true : false;
/* 236 */       getEditedSection().set("upgrade.workbench", Boolean.valueOf(bool));
/* 237 */       registerTemplateEdition();
/* 238 */       this.player.sendMessage(MMOItems.plugin.getPrefix() + (
/* 239 */           bool ? "Your item must now be upgraded via recipes." : "Your item can now be upgraded using consumables."));
/*     */     } 
/*     */     
/* 242 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Destroy on fail?")) {
/* 243 */       boolean bool = !getEditedSection().getBoolean("upgrade.destroy") ? true : false;
/* 244 */       getEditedSection().set("upgrade.destroy", Boolean.valueOf(bool));
/* 245 */       registerTemplateEdition();
/* 246 */       this.player.sendMessage(MMOItems.plugin.getPrefix() + (
/* 247 */           bool ? "Your item will be destroyed upon failing upgrade." : "Your item will not be destroyed upon failing upgrade."));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\UpgradingEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */