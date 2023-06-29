/*     */ package net.Indyuce.mmoitems.gui.edition;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.particle.api.ParticleType;
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
/*     */ public class ParticlesEdition
/*     */   extends EditionInventory
/*     */ {
/*     */   public ParticlesEdition(Player paramPlayer, MMOItemTemplate paramMMOItemTemplate) {
/*  31 */     super(paramPlayer, paramMMOItemTemplate);
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getInventory() {
/*  36 */     Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Particles E.: " + this.template.getId());
/*  37 */     int[] arrayOfInt = { 37, 38, 39, 40, 41, 42, 43 };
/*  38 */     byte b = 0;
/*     */     
/*  40 */     ParticleType particleType = null;
/*     */     try {
/*  42 */       particleType = ParticleType.valueOf(getEditedSection().getString("item-particles.type"));
/*  43 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/*  46 */     ItemStack itemStack1 = VersionMaterial.PINK_STAINED_GLASS.toItem();
/*  47 */     ItemMeta itemMeta1 = itemStack1.getItemMeta();
/*  48 */     itemMeta1.setDisplayName(ChatColor.GREEN + "Particle Pattern");
/*  49 */     ArrayList<String> arrayList1 = new ArrayList();
/*  50 */     arrayList1.add(ChatColor.GRAY + "The particle pattern defines how");
/*  51 */     arrayList1.add(ChatColor.GRAY + "particles behave, what pattern they follow");
/*  52 */     arrayList1.add(ChatColor.GRAY + "when displayed or what shape they form.");
/*  53 */     arrayList1.add("");
/*  54 */     arrayList1.add(ChatColor.GRAY + "Current Value: " + (
/*  55 */         (particleType == null) ? (ChatColor.RED + "No type selected.") : (ChatColor.GOLD + particleType.getDefaultName())));
/*  56 */     if (particleType != null) {
/*  57 */       arrayList1.add("" + ChatColor.GRAY + ChatColor.ITALIC + particleType.getDescription());
/*     */     }
/*  59 */     arrayList1.add("");
/*  60 */     arrayList1.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/*  61 */     arrayList1.add(ChatColor.YELLOW + "►" + " Right click to change this value.");
/*  62 */     itemMeta1.setLore(arrayList1);
/*  63 */     itemStack1.setItemMeta(itemMeta1);
/*     */     
/*  65 */     if (particleType != null) {
/*  66 */       ConfigurationSection configurationSection = getEditedSection().getConfigurationSection("item-particles");
/*  67 */       for (String str : particleType.getModifiers()) {
/*  68 */         ItemStack itemStack = VersionMaterial.GRAY_DYE.toItem();
/*  69 */         ItemMeta itemMeta = itemStack.getItemMeta();
/*  70 */         itemMeta.setDisplayName(ChatColor.GREEN + MMOUtils.caseOnWords(str.toLowerCase().replace("-", " ")));
/*  71 */         ArrayList<String> arrayList = new ArrayList();
/*  72 */         arrayList.add("" + ChatColor.GRAY + ChatColor.ITALIC + "This is a pattern modifier.");
/*  73 */         arrayList.add("" + ChatColor.GRAY + ChatColor.ITALIC + "Changing this value will slightly");
/*  74 */         arrayList.add("" + ChatColor.GRAY + ChatColor.ITALIC + "customize the particle pattern.");
/*  75 */         arrayList.add("");
/*  76 */         arrayList.add(ChatColor.GRAY + "Current Value: " + ChatColor.GOLD + (
/*  77 */             configurationSection.contains(str) ? configurationSection.getDouble(str) : particleType.getModifier(str)));
/*  78 */         itemMeta.setLore(arrayList);
/*  79 */         itemStack.setItemMeta(itemMeta);
/*     */         
/*  81 */         itemStack = NBTItem.get(itemStack).addTag(new ItemTag[] { new ItemTag("patternModifierId", str) }).toItem();
/*     */         
/*  83 */         inventory.setItem(arrayOfInt[b++], itemStack);
/*     */       } 
/*     */     } 
/*     */     
/*  87 */     Particle particle = null;
/*     */     try {
/*  89 */       particle = Particle.valueOf(getEditedSection().getString("item-particles.particle"));
/*  90 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/*  93 */     ItemStack itemStack2 = new ItemStack(Material.BLAZE_POWDER);
/*  94 */     ItemMeta itemMeta2 = itemStack2.getItemMeta();
/*  95 */     itemMeta2.setDisplayName(ChatColor.GREEN + "Particle");
/*  96 */     ArrayList<String> arrayList2 = new ArrayList();
/*  97 */     arrayList2.add(ChatColor.GRAY + "Defines what particle is used");
/*  98 */     arrayList2.add(ChatColor.GRAY + "in the particle effect.");
/*  99 */     arrayList2.add("");
/* 100 */     arrayList2.add(ChatColor.GRAY + "Current Value: " + ((particle == null) ? (ChatColor.RED + "No particle selected.") : (
/* 101 */         ChatColor.GOLD + MMOUtils.caseOnWords(particle.name().toLowerCase().replace("_", " ")))));
/* 102 */     arrayList2.add("");
/* 103 */     arrayList2.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/* 104 */     arrayList2.add(ChatColor.YELLOW + "►" + " Right click to change this value.");
/* 105 */     itemMeta2.setLore(arrayList2);
/* 106 */     itemStack2.setItemMeta(itemMeta2);
/*     */     
/* 108 */     if (particle != null && MMOUtils.isColorable(particle)) {
/* 109 */       int i = getEditedSection().getInt("item-particles.color.red");
/* 110 */       int j = getEditedSection().getInt("item-particles.color.green");
/* 111 */       int k = getEditedSection().getInt("item-particles.color.blue");
/*     */       
/* 113 */       ItemStack itemStack = VersionMaterial.RED_DYE.toItem();
/* 114 */       ItemMeta itemMeta = itemStack.getItemMeta();
/* 115 */       itemMeta.setDisplayName(ChatColor.GREEN + "Particle Color");
/* 116 */       ArrayList<String> arrayList = new ArrayList();
/* 117 */       arrayList.add(ChatColor.GRAY + "The RGB color of your particle.");
/* 118 */       arrayList.add("");
/* 119 */       arrayList.add(ChatColor.GRAY + "Current Value (R-G-B):");
/* 120 */       arrayList.add("" + ChatColor.RED + ChatColor.BOLD + i + ChatColor.GRAY + " - " + ChatColor.GREEN + ChatColor.BOLD + j + ChatColor.GRAY + " - " + ChatColor.BLUE + ChatColor.BOLD + k);
/*     */       
/* 122 */       arrayList.add("");
/* 123 */       arrayList.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/* 124 */       arrayList.add(ChatColor.YELLOW + "►" + " Right click to change this value.");
/* 125 */       itemMeta.setLore(arrayList);
/* 126 */       itemStack.setItemMeta(itemMeta);
/*     */       
/* 128 */       inventory.setItem(25, itemStack);
/*     */     } 
/*     */     
/* 131 */     ItemStack itemStack3 = VersionMaterial.GRAY_STAINED_GLASS_PANE.toItem();
/* 132 */     ItemMeta itemMeta3 = itemStack3.getItemMeta();
/* 133 */     itemMeta3.setDisplayName(ChatColor.RED + "- No Modifier -");
/* 134 */     itemStack3.setItemMeta(itemMeta3);
/*     */     
/* 136 */     while (b < arrayOfInt.length) {
/* 137 */       inventory.setItem(arrayOfInt[b++], itemStack3);
/*     */     }
/* 139 */     addEditionInventoryItems(inventory, true);
/* 140 */     inventory.setItem(21, itemStack1);
/* 141 */     inventory.setItem(23, itemStack2);
/*     */     
/* 143 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 148 */     ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
/*     */     
/* 150 */     paramInventoryClickEvent.setCancelled(true);
/* 151 */     if (paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory() || !MMOUtils.isMetaItem(itemStack, false)) {
/*     */       return;
/*     */     }
/* 154 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Particle")) {
/* 155 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 156 */         (new StatEdition(this, ItemStats.ITEM_PARTICLES, new Object[] { "particle" })).enable(new String[] { "Write in the chat the particle you want." });
/*     */       }
/* 158 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/* 159 */         getEditedSection().contains("item-particles.particle")) {
/* 160 */         getEditedSection().set("item-particles.particle", null);
/* 161 */         registerTemplateEdition();
/* 162 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset the particle.");
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 167 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Particle Color")) {
/* 168 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 169 */         (new StatEdition(this, ItemStats.ITEM_PARTICLES, new Object[] { "particle-color" })).enable(new String[] { "Write in the chat the RGB color you want.", ChatColor.AQUA + "Format: [RED] [GREEN] [BLUE]" });
/*     */       }
/*     */       
/* 172 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/* 173 */         getEditedSection().contains("item-particles.color")) {
/* 174 */         getEditedSection().set("item-particles.color", null);
/* 175 */         registerTemplateEdition();
/* 176 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset the particle color.");
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 181 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Particle Pattern")) {
/* 182 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 183 */         (new StatEdition(this, ItemStats.ITEM_PARTICLES, new Object[] { "particle-type" })).enable(new String[] { "Write in the chat the particle type you want." });
/* 184 */         this.player.sendMessage("");
/* 185 */         this.player.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Available Particles Patterns");
/* 186 */         for (ParticleType particleType : ParticleType.values()) {
/* 187 */           this.player.sendMessage("* " + ChatColor.GREEN + particleType.name());
/*     */         }
/*     */       } 
/* 190 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/* 191 */         getEditedSection().contains("item-particles.type")) {
/* 192 */         getEditedSection().set("item-particles.type", null);
/*     */ 
/*     */         
/* 195 */         for (String str1 : getEditedSection().getConfigurationSection("item-particles").getKeys(false)) {
/* 196 */           if (!str1.equals("particle"))
/* 197 */             getEditedSection().set("item-particles." + str1, null); 
/*     */         } 
/* 199 */         registerTemplateEdition();
/* 200 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset the particle pattern.");
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 205 */     String str = NBTItem.get(itemStack).getString("patternModifierId");
/* 206 */     if (str.equals("")) {
/*     */       return;
/*     */     }
/* 209 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 210 */       (new StatEdition(this, ItemStats.ITEM_PARTICLES, new Object[] { str })).enable(new String[] { "Write in the chat the value you want." });
/*     */     }
/* 212 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/* 213 */       getEditedSection().contains("item-particles." + str)) {
/* 214 */       getEditedSection().set("item-particles." + str, null);
/* 215 */       registerTemplateEdition();
/* 216 */       this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset " + ChatColor.GOLD + str + ChatColor.GRAY + ".");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\ParticlesEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */