/*     */ package net.Indyuce.mmoitems.gui.edition;
/*     */ 
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ public class ArrowParticlesEdition
/*     */   extends EditionInventory
/*     */ {
/*     */   public ArrowParticlesEdition(Player paramPlayer, MMOItemTemplate paramMMOItemTemplate) {
/*  28 */     super(paramPlayer, paramMMOItemTemplate);
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getInventory() {
/*  33 */     Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Arrow Particles: " + this.template.getId());
/*     */ 
/*     */ 
/*     */     
/*  37 */     Particle particle = null;
/*     */     try {
/*  39 */       particle = Particle.valueOf(getEditedSection().getString("arrow-particles.particle"));
/*  40 */     } catch (Exception exception) {}
/*     */     
/*  42 */     ItemStack itemStack1 = new ItemStack(Material.BLAZE_POWDER);
/*  43 */     ItemMeta itemMeta1 = itemStack1.getItemMeta();
/*  44 */     itemMeta1.setDisplayName(ChatColor.GREEN + "Particle");
/*  45 */     ArrayList<String> arrayList1 = new ArrayList();
/*  46 */     arrayList1.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "The particle which is displayed around the");
/*  47 */     arrayList1.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "arrow. Fades away when the arrow lands.");
/*  48 */     arrayList1.add("");
/*  49 */     arrayList1.add(ChatColor.GRAY + "Current Value: " + ((particle == null) ? (ChatColor.RED + "No particle selected.") : (
/*  50 */         ChatColor.GOLD + MMOUtils.caseOnWords(particle.name().toLowerCase().replace("_", " ")))));
/*  51 */     arrayList1.add("");
/*  52 */     arrayList1.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/*  53 */     arrayList1.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/*  54 */     itemMeta1.setLore(arrayList1);
/*  55 */     itemStack1.setItemMeta(itemMeta1);
/*     */     
/*  57 */     ItemStack itemStack2 = VersionMaterial.GRAY_DYE.toItem();
/*  58 */     ItemMeta itemMeta2 = itemStack2.getItemMeta();
/*  59 */     itemMeta2.setDisplayName(ChatColor.GREEN + "Amount");
/*  60 */     ArrayList<String> arrayList2 = new ArrayList();
/*  61 */     arrayList2.add("");
/*  62 */     arrayList2.add(ChatColor.GRAY + "Current Value: " + ChatColor.GOLD + getEditedSection().getInt("arrow-particles.amount"));
/*  63 */     arrayList2.add("");
/*  64 */     arrayList2.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/*  65 */     arrayList2.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/*  66 */     itemMeta2.setLore(arrayList2);
/*  67 */     itemStack2.setItemMeta(itemMeta2);
/*     */     
/*  69 */     ItemStack itemStack3 = VersionMaterial.GRAY_DYE.toItem();
/*  70 */     ItemMeta itemMeta3 = itemStack3.getItemMeta();
/*  71 */     itemMeta3.setDisplayName(ChatColor.GREEN + "Offset");
/*  72 */     ArrayList<String> arrayList3 = new ArrayList();
/*  73 */     arrayList3.add("");
/*  74 */     arrayList3.add(ChatColor.GRAY + "Current Value: " + ChatColor.GOLD + getEditedSection().getDouble("arrow-particles.offset"));
/*  75 */     arrayList3.add("");
/*  76 */     arrayList3.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/*  77 */     arrayList3.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/*  78 */     itemMeta3.setLore(arrayList3);
/*  79 */     itemStack3.setItemMeta(itemMeta3);
/*     */     
/*  81 */     if (particle != null) {
/*  82 */       ConfigurationSection configurationSection = getEditedSection().getConfigurationSection("arrow-particles");
/*  83 */       if (MMOUtils.isColorable(particle)) {
/*  84 */         int i = configurationSection.getInt("color.red");
/*  85 */         int j = configurationSection.getInt("color.green");
/*  86 */         int k = configurationSection.getInt("color.blue");
/*     */         
/*  88 */         ItemStack itemStack = VersionMaterial.GRAY_DYE.toItem();
/*  89 */         ItemMeta itemMeta = itemStack.getItemMeta();
/*  90 */         itemMeta.setDisplayName(ChatColor.GREEN + "Particle Color");
/*  91 */         ArrayList<String> arrayList = new ArrayList();
/*  92 */         arrayList.add("");
/*  93 */         arrayList.add(ChatColor.GRAY + "Current Value (R-G-B):");
/*  94 */         arrayList.add("" + ChatColor.RED + ChatColor.BOLD + i + ChatColor.GRAY + " - " + ChatColor.GREEN + ChatColor.BOLD + j + ChatColor.GRAY + " - " + ChatColor.BLUE + ChatColor.BOLD + k);
/*     */         
/*  96 */         arrayList.add("");
/*  97 */         arrayList.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/*  98 */         arrayList.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/*  99 */         itemMeta.setLore(arrayList);
/* 100 */         itemStack.setItemMeta(itemMeta);
/*     */         
/* 102 */         inventory.setItem(41, itemStack);
/*     */       } else {
/* 104 */         ItemStack itemStack = VersionMaterial.GRAY_DYE.toItem();
/* 105 */         ItemMeta itemMeta = itemStack.getItemMeta();
/* 106 */         itemMeta.setDisplayName(ChatColor.GREEN + "Speed");
/* 107 */         ArrayList<String> arrayList = new ArrayList();
/* 108 */         arrayList.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "The speed at which your particle");
/* 109 */         arrayList.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "flies off in random directions.");
/* 110 */         arrayList.add("");
/* 111 */         arrayList.add(ChatColor.GRAY + "Current Value: " + ChatColor.GOLD + configurationSection.getDouble("speed"));
/* 112 */         arrayList.add("");
/* 113 */         arrayList.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/* 114 */         arrayList.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/* 115 */         itemMeta.setLore(arrayList);
/* 116 */         itemStack.setItemMeta(itemMeta);
/*     */         
/* 118 */         inventory.setItem(41, itemStack);
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     addEditionInventoryItems(inventory, true);
/* 123 */     inventory.setItem(30, itemStack1);
/* 124 */     inventory.setItem(23, itemStack2);
/* 125 */     inventory.setItem(32, itemStack3);
/*     */     
/* 127 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 132 */     ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
/*     */     
/* 134 */     paramInventoryClickEvent.setCancelled(true);
/* 135 */     if (paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory() || !MMOUtils.isMetaItem(itemStack, false)) {
/*     */       return;
/*     */     }
/* 138 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Particle")) {
/* 139 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 140 */         (new StatEdition(this, ItemStats.ARROW_PARTICLES, new Object[] { "particle" })).enable(new String[] { "Write in the chat the particle you want." });
/*     */       }
/* 142 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/* 143 */         getEditedSection().contains("arrow-particles.particle")) {
/* 144 */         getEditedSection().set("arrow-particles", null);
/* 145 */         registerTemplateEdition();
/* 146 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset the particle.");
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 151 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Particle Color")) {
/* 152 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 153 */         (new StatEdition(this, ItemStats.ARROW_PARTICLES, new Object[] { "color" })).enable(new String[] { "Write in the chat the RGB color you want.", ChatColor.AQUA + "Format: [RED] [GREEN] [BLUE]" });
/*     */       }
/*     */       
/* 156 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/* 157 */         getEditedSection().contains("arrow-particles.color")) {
/* 158 */         getEditedSection().set("arrow-particles.color", null);
/* 159 */         registerTemplateEdition();
/* 160 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset the particle color.");
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 165 */     for (String str : new String[] { "amount", "offset", "speed" }) {
/* 166 */       if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + MMOUtils.caseOnWords(str))) {
/* 167 */         if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 168 */           (new StatEdition(this, ItemStats.ARROW_PARTICLES, new Object[] { str })).enable(new String[] { "Write in the chat the " + str + " you want." });
/*     */         }
/* 170 */         if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/* 171 */           getEditedSection().contains("arrow-particles." + str)) {
/* 172 */           getEditedSection().set("arrow-particles." + str, null);
/* 173 */           registerTemplateEdition();
/* 174 */           this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset the " + str + ".");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\ArrowParticlesEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */