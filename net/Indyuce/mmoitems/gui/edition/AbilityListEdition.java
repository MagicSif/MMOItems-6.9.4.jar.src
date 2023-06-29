/*     */ package net.Indyuce.mmoitems.gui.edition;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.UtilityMethods;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.skill.trigger.TriggerType;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.skill.RegisteredSkill;
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
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ public class AbilityListEdition
/*     */   extends EditionInventory
/*     */ {
/*  29 */   private static final int[] slots = new int[] { 19, 20, 21, 22, 23, 24, 25 };
/*     */   
/*     */   public AbilityListEdition(Player paramPlayer, MMOItemTemplate paramMMOItemTemplate) {
/*  32 */     super(paramPlayer, paramMMOItemTemplate);
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getInventory() {
/*  37 */     Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Ability List");
/*  38 */     byte b = 0;
/*     */     
/*  40 */     if (getEditedSection().contains("ability")) {
/*  41 */       for (String str1 : getEditedSection().getConfigurationSection("ability").getKeys(false)) {
/*  42 */         TriggerType triggerType; String str2 = getEditedSection().getString("ability." + str1 + ".type");
/*     */ 
/*     */ 
/*     */         
/*  46 */         RegisteredSkill registeredSkill = (str2 != null && MMOItems.plugin.getSkills().hasSkill(str2 = str2.toUpperCase().replace(" ", "_").replace("-", "_"))) ? MMOItems.plugin.getSkills().getSkill(str2) : null;
/*     */ 
/*     */         
/*     */         try {
/*  50 */           triggerType = TriggerType.valueOf(UtilityMethods.enumName(getEditedSection().getString("ability." + str1 + ".mode")));
/*  51 */         } catch (RuntimeException runtimeException) {
/*  52 */           triggerType = null;
/*     */         } 
/*     */         
/*  55 */         ItemStack itemStack = new ItemStack(Material.BLAZE_POWDER);
/*  56 */         ItemMeta itemMeta = itemStack.getItemMeta();
/*  57 */         itemMeta.setDisplayName((registeredSkill != null) ? (ChatColor.GREEN + registeredSkill.getName()) : (ChatColor.RED + "! No Ability Selected !"));
/*  58 */         ArrayList<String> arrayList = new ArrayList();
/*  59 */         arrayList.add("");
/*  60 */         arrayList.add(ChatColor.GRAY + "Cast Mode: " + (
/*  61 */             (triggerType != null) ? (ChatColor.GOLD + triggerType.getName()) : (ChatColor.RED + "Not Selected")));
/*  62 */         arrayList.add("");
/*     */         
/*  64 */         boolean bool = false;
/*  65 */         if (registeredSkill != null)
/*  66 */           for (String str : getEditedSection().getConfigurationSection("ability." + str1).getKeys(false)) {
/*  67 */             if (!str.equals("type") && !str.equals("mode") && registeredSkill.getHandler().getModifiers().contains(str))
/*     */               try {
/*  69 */                 arrayList.add(ChatColor.GRAY + "* " + 
/*  70 */                     MMOUtils.caseOnWords(str.toLowerCase().replace("-", " ")) + ": " + ChatColor.GOLD + (new NumericStatFormula(
/*  71 */                       getEditedSection().get("ability." + str1 + "." + str))).toString());
/*  72 */                 bool = true;
/*  73 */               } catch (IllegalArgumentException illegalArgumentException) {
/*  74 */                 arrayList.add(ChatColor.GRAY + "* " + MMOUtils.caseOnWords(str.toLowerCase().replace("-", " ")) + ": " + ChatColor.GOLD + "Unreadable");
/*     */               }  
/*     */           }  
/*  77 */         if (bool) {
/*  78 */           arrayList.add("");
/*     */         }
/*  80 */         arrayList.add(ChatColor.YELLOW + "►" + " Left click to edit.");
/*  81 */         arrayList.add(ChatColor.YELLOW + "►" + " Right click to remove.");
/*  82 */         itemMeta.setLore(arrayList);
/*  83 */         itemStack.setItemMeta(itemMeta);
/*     */         
/*  85 */         itemStack = MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack).addTag(new ItemTag[] { new ItemTag("configKey", str1) }).toItem();
/*     */         
/*  87 */         inventory.setItem(slots[b++], itemStack);
/*     */       } 
/*     */     }
/*  90 */     ItemStack itemStack1 = VersionMaterial.GRAY_STAINED_GLASS_PANE.toItem();
/*  91 */     ItemMeta itemMeta1 = itemStack1.getItemMeta();
/*  92 */     itemMeta1.setDisplayName(ChatColor.RED + "- No Ability -");
/*  93 */     itemStack1.setItemMeta(itemMeta1);
/*     */     
/*  95 */     ItemStack itemStack2 = new ItemStack(VersionMaterial.WRITABLE_BOOK.toMaterial());
/*  96 */     ItemMeta itemMeta2 = itemStack2.getItemMeta();
/*  97 */     itemMeta2.setDisplayName(ChatColor.GREEN + "Add an ability...");
/*  98 */     itemStack2.setItemMeta(itemMeta2);
/*     */     
/* 100 */     inventory.setItem(40, itemStack2);
/* 101 */     while (b < slots.length)
/* 102 */       inventory.setItem(slots[b++], itemStack1); 
/* 103 */     addEditionInventoryItems(inventory, true);
/*     */     
/* 105 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/* 110 */     ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
/*     */     
/* 112 */     paramInventoryClickEvent.setCancelled(true);
/* 113 */     if (paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory() || !MMOUtils.isMetaItem(itemStack, false)) {
/*     */       return;
/*     */     }
/* 116 */     if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Add an ability...")) {
/* 117 */       if (!getEditedSection().contains("ability")) {
/* 118 */         getEditedSection().createSection("ability.ability1");
/* 119 */         registerTemplateEdition();
/*     */         
/*     */         return;
/*     */       } 
/* 123 */       if (getEditedSection().getConfigurationSection("ability").getKeys(false).size() > 6) {
/* 124 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "You've hit the 7 abilities per item limit.");
/*     */         
/*     */         return;
/*     */       } 
/* 128 */       for (byte b = 1; b < 8; b++) {
/* 129 */         if (!getEditedSection().getConfigurationSection("ability").contains("ability" + b)) {
/* 130 */           getEditedSection().createSection("ability.ability" + b);
/* 131 */           registerTemplateEdition();
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 136 */     String str = MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack).getString("configKey");
/* 137 */     if (str.equals("")) {
/*     */       return;
/*     */     }
/* 140 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 141 */       (new AbilityEdition(this.player, this.template, str)).open(getPreviousPage());
/*     */     }
/* 143 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/* 144 */       getEditedSection().contains("ability") && getEditedSection().getConfigurationSection("ability").contains(str)) {
/* 145 */       getEditedSection().set("ability." + str, null);
/* 146 */       registerTemplateEdition();
/* 147 */       this.player.sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + ChatColor.GOLD + str + ChatColor.DARK_GRAY + " (Internal ID)" + ChatColor.GRAY + ".");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\AbilityListEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */