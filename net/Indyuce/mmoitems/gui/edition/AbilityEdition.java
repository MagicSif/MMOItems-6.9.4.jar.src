/*     */ package net.Indyuce.mmoitems.gui.edition;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.skill.trigger.TriggerType;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.skill.RegisteredSkill;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
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
/*     */ 
/*     */ public class AbilityEdition
/*     */   extends EditionInventory
/*     */ {
/*     */   private final String configKey;
/*     */   private RegisteredSkill ability;
/*  35 */   private static final DecimalFormat MODIFIER_FORMAT = new DecimalFormat("0.###");
/*  36 */   private static final int[] slots = new int[] { 23, 24, 25, 32, 33, 34, 41, 42, 43, 50, 51, 52 };
/*     */   
/*     */   public AbilityEdition(Player paramPlayer, MMOItemTemplate paramMMOItemTemplate, String paramString) {
/*  39 */     super(paramPlayer, paramMMOItemTemplate);
/*     */     
/*  41 */     this.configKey = paramString;
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getInventory() {
/*  46 */     Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Ability Edition");
/*  47 */     byte b = 0;
/*     */     
/*  49 */     String str1 = getEditedSection().getString("ability." + this.configKey + ".type");
/*  50 */     String str2 = (str1 == null) ? "" : str1.toUpperCase().replace(" ", "_").replace("-", "_").replaceAll("[^A-Z_]", "");
/*  51 */     this.ability = MMOItems.plugin.getSkills().hasSkill(str2) ? MMOItems.plugin.getSkills().getSkill(str2) : null;
/*     */     
/*  53 */     ItemStack itemStack1 = new ItemStack(Material.BLAZE_POWDER);
/*  54 */     ItemMeta itemMeta1 = itemStack1.getItemMeta();
/*  55 */     itemMeta1.setDisplayName(ChatColor.GREEN + "Ability");
/*  56 */     ArrayList<String> arrayList = new ArrayList();
/*  57 */     arrayList.add(ChatColor.GRAY + "Choose what ability your weapon will cast.");
/*  58 */     arrayList.add("");
/*  59 */     arrayList.add(ChatColor.GRAY + "Current Value: " + (
/*  60 */         (this.ability == null) ? (ChatColor.RED + "No ability selected.") : (ChatColor.GOLD + this.ability.getName())));
/*  61 */     arrayList.add("");
/*  62 */     arrayList.add(ChatColor.YELLOW + "►" + " Left click to select.");
/*  63 */     arrayList.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/*  64 */     itemMeta1.setLore(arrayList);
/*  65 */     itemStack1.setItemMeta(itemMeta1);
/*     */     
/*  67 */     if (this.ability != null) {
/*  68 */       TriggerType triggerType; String str3 = getEditedSection().getString("ability." + this.configKey + ".mode");
/*     */       
/*  70 */       String str4 = (str3 == null) ? "" : str3.toUpperCase().replace(" ", "_").replace("-", "_").replaceAll("[^A-Z0-9_]", "");
/*     */       
/*     */       try {
/*  73 */         triggerType = TriggerType.valueOf(str4);
/*  74 */       } catch (RuntimeException runtimeException) {
/*  75 */         triggerType = null;
/*     */       } 
/*     */       
/*  78 */       ItemStack itemStack = new ItemStack(Material.ARMOR_STAND);
/*  79 */       ItemMeta itemMeta = itemStack.getItemMeta();
/*  80 */       itemMeta.setDisplayName(ChatColor.GREEN + "Trigger");
/*  81 */       ArrayList<String> arrayList1 = new ArrayList();
/*  82 */       arrayList1.add(ChatColor.GRAY + "Choose what action the player needs to");
/*  83 */       arrayList1.add(ChatColor.GRAY + "perform in order to cast your ability.");
/*  84 */       arrayList1.add("");
/*  85 */       arrayList1.add(ChatColor.GRAY + "Current Value: " + (
/*  86 */           (triggerType == null) ? (ChatColor.RED + "No trigger selected.") : (ChatColor.GOLD + triggerType.getName())));
/*  87 */       arrayList1.add("");
/*  88 */       arrayList1.add(ChatColor.YELLOW + "►" + " Left click to select.");
/*  89 */       arrayList1.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/*  90 */       itemMeta.setLore(arrayList1);
/*  91 */       itemStack.setItemMeta(itemMeta);
/*     */       
/*  93 */       inventory.setItem(30, itemStack);
/*     */     } 
/*     */     
/*  96 */     if (this.ability != null) {
/*  97 */       ConfigurationSection configurationSection = getEditedSection().getConfigurationSection("ability." + this.configKey);
/*  98 */       for (String str : this.ability.getHandler().getModifiers()) {
/*  99 */         ItemStack itemStack = VersionMaterial.GRAY_DYE.toItem();
/* 100 */         ItemMeta itemMeta = itemStack.getItemMeta();
/* 101 */         itemMeta.setDisplayName(ChatColor.GREEN + MMOUtils.caseOnWords(str.toLowerCase().replace("-", " ")));
/* 102 */         ArrayList<String> arrayList1 = new ArrayList();
/* 103 */         arrayList1.add("" + ChatColor.GRAY + ChatColor.ITALIC + "This is an ability modifier. Changing this");
/* 104 */         arrayList1.add("" + ChatColor.GRAY + ChatColor.ITALIC + "value will slightly customize the ability.");
/* 105 */         arrayList1.add("");
/*     */         
/*     */         try {
/* 108 */           arrayList1.add(ChatColor.GRAY + "Current Value: " + ChatColor.GOLD + (
/* 109 */               configurationSection.contains(str) ? (new NumericStatFormula(configurationSection.get(str))).toString() : 
/* 110 */               MODIFIER_FORMAT.format(this.ability.getDefaultModifier(str))));
/* 111 */         } catch (IllegalArgumentException illegalArgumentException) {
/* 112 */           arrayList1.add(ChatColor.GRAY + "Could not read value. Using default");
/*     */         } 
/*     */         
/* 115 */         arrayList1.add(ChatColor.GRAY + "Default Value: " + ChatColor.GOLD + MODIFIER_FORMAT.format(this.ability.getDefaultModifier(str)));
/* 116 */         arrayList1.add("");
/* 117 */         arrayList1.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/* 118 */         arrayList1.add(ChatColor.YELLOW + "►" + " Right click to reset.");
/* 119 */         itemMeta.setLore(arrayList1);
/* 120 */         itemStack.setItemMeta(itemMeta);
/*     */ 
/*     */         
/* 123 */         itemStack = MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack).addTag(new ItemTag[] { new ItemTag("abilityModifier", str) }).toItem();
/*     */         
/* 125 */         inventory.setItem(slots[b++], itemStack);
/*     */       } 
/*     */     } 
/*     */     
/* 129 */     ItemStack itemStack2 = VersionMaterial.GRAY_STAINED_GLASS_PANE.toItem();
/* 130 */     ItemMeta itemMeta2 = itemStack2.getItemMeta();
/* 131 */     itemMeta2.setDisplayName(ChatColor.RED + "- No Modifier -");
/* 132 */     itemStack2.setItemMeta(itemMeta2);
/*     */     
/* 134 */     ItemStack itemStack3 = new ItemStack(Material.BARRIER);
/* 135 */     ItemMeta itemMeta3 = itemStack3.getItemMeta();
/* 136 */     itemMeta3.setDisplayName(ChatColor.GREEN + "⇨" + " Ability List");
/* 137 */     itemStack3.setItemMeta(itemMeta3);
/*     */     
/* 139 */     while (b < slots.length) {
/* 140 */       inventory.setItem(slots[b++], itemStack2);
/*     */     }
/* 142 */     addEditionInventoryItems(inventory, false);
/* 143 */     inventory.setItem(28, itemStack1);
/* 144 */     inventory.setItem(6, itemStack3);
/*     */     
/* 146 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 151 */     ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
/*     */     
/* 153 */     paramInventoryClickEvent.setCancelled(true);
/* 154 */     if (paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory() || !MMOUtils.isMetaItem(itemStack, false)) {
/*     */       return;
/*     */     }
/* 157 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "⇨" + " Ability List")) {
/* 158 */       (new AbilityListEdition(this.player, this.template)).open(getPreviousPage());
/*     */       
/*     */       return;
/*     */     } 
/* 162 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Ability")) {
/* 163 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 164 */         (new StatEdition(this, ItemStats.ABILITIES, new Object[] { this.configKey, "ability" })).enable(new String[] { "Write in the chat the ability you want.", "You can access the ability list by typing " + ChatColor.AQUA + "/mi list ability" });
/*     */       }
/*     */       
/* 167 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/* 168 */         getEditedSection().contains("ability." + this.configKey + ".type")) {
/* 169 */         getEditedSection().set("ability." + this.configKey, null);
/*     */         
/* 171 */         if (getEditedSection().contains("ability") && getEditedSection().getConfigurationSection("ability").getKeys(false).size() == 0) {
/* 172 */           getEditedSection().set("ability", null);
/*     */         }
/* 174 */         registerTemplateEdition();
/* 175 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset the ability.");
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 181 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Trigger")) {
/* 182 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 183 */         (new StatEdition(this, ItemStats.ABILITIES, new Object[] { this.configKey, "mode" })).enable(new String[0]);
/*     */         
/* 185 */         this.player.sendMessage("");
/* 186 */         this.player.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "Available Triggers");
/* 187 */         for (TriggerType triggerType : TriggerType.values()) {
/* 188 */           this.player.sendMessage("* " + ChatColor.GREEN + triggerType.name());
/*     */         }
/*     */       } 
/* 191 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && getEditedSection().contains("ability." + this.configKey + ".mode")) {
/* 192 */         getEditedSection().set("ability." + this.configKey + ".mode", null);
/* 193 */         registerTemplateEdition();
/* 194 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset the ability trigger.");
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 199 */     String str = MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack).getString("abilityModifier");
/* 200 */     if (str.equals("")) {
/*     */       return;
/*     */     }
/* 203 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 204 */       (new StatEdition(this, ItemStats.ABILITIES, new Object[] { this.configKey, str })).enable(new String[] { "Write in the chat the value you want." });
/*     */     }
/* 206 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/* 207 */       getEditedSection().contains("ability." + this.configKey + "." + str)) {
/* 208 */       getEditedSection().set("ability." + this.configKey + "." + str, null);
/* 209 */       registerTemplateEdition();
/* 210 */       this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reset " + ChatColor.GOLD + MMOUtils.caseOnWords(str.replace("-", " ")) + ChatColor.GRAY + ".");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\AbilityEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */