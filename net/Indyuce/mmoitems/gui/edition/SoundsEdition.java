/*     */ package net.Indyuce.mmoitems.gui.edition;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.CustomSound;
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
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class SoundsEdition
/*     */   extends EditionInventory
/*     */ {
/*  26 */   public static final Map<Integer, String> CORRESPONDING_SLOT = new HashMap<>();
/*     */   
/*     */   static {
/*  29 */     for (CustomSound customSound : CustomSound.values())
/*  30 */       CORRESPONDING_SLOT.put(Integer.valueOf(customSound.getSlot()), customSound.name().replace("_", "-").toLowerCase()); 
/*     */   }
/*     */   
/*     */   public SoundsEdition(Player paramPlayer, MMOItemTemplate paramMMOItemTemplate) {
/*  34 */     super(paramPlayer, paramMMOItemTemplate);
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getInventory() {
/*  39 */     Inventory inventory = Bukkit.createInventory((InventoryHolder)this, 54, "Custom Sounds: " + this.template.getId());
/*  40 */     byte b = 0;
/*     */     
/*  42 */     for (CustomSound customSound : CustomSound.values()) {
/*  43 */       ItemStack itemStack = customSound.getItem().clone();
/*  44 */       ItemMeta itemMeta = itemStack.getItemMeta();
/*  45 */       itemMeta.addItemFlags(ItemFlag.values());
/*  46 */       itemMeta.setDisplayName(ChatColor.GREEN + customSound.getName());
/*  47 */       ArrayList<String> arrayList = new ArrayList();
/*  48 */       for (String str : customSound.getLore())
/*  49 */         arrayList.add(ChatColor.GRAY + str); 
/*  50 */       arrayList.add("");
/*  51 */       String str1 = customSound.getName().replace(" ", "-").toLowerCase();
/*  52 */       String str2 = getEditedSection().getString("sounds." + str1 + ".sound");
/*  53 */       if (str2 != null) {
/*  54 */         arrayList.add(ChatColor.GRAY + "Current Values:");
/*  55 */         arrayList.add(ChatColor.GRAY + " - Sound Name: '" + ChatColor.GREEN + 
/*  56 */             getEditedSection().getString("sounds." + str1 + ".sound") + ChatColor.GRAY + "'");
/*  57 */         arrayList.add(ChatColor.GRAY + " - Volume: " + ChatColor.GREEN + 
/*  58 */             getEditedSection().getDouble("sounds." + str1 + ".volume"));
/*  59 */         arrayList.add(ChatColor.GRAY + " - Pitch: " + ChatColor.GREEN + getEditedSection().getDouble("sounds." + str1 + ".pitch"));
/*     */       } else {
/*  61 */         arrayList.add(ChatColor.GRAY + "Current Values: " + ChatColor.RED + "None");
/*  62 */       }  arrayList.add("");
/*  63 */       arrayList.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/*  64 */       arrayList.add(ChatColor.YELLOW + "►" + " Right click to remove this value.");
/*  65 */       itemMeta.setLore(arrayList);
/*  66 */       itemStack.setItemMeta(itemMeta);
/*     */       
/*  68 */       inventory.setItem(customSound.getSlot(), itemStack);
/*  69 */       b++;
/*     */     } 
/*     */     
/*  72 */     addEditionInventoryItems(inventory, true);
/*     */     
/*  74 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(InventoryClickEvent paramInventoryClickEvent) {
/*  79 */     ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
/*     */     
/*  81 */     paramInventoryClickEvent.setCancelled(true);
/*  82 */     if (paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory() || !MMOUtils.isMetaItem(itemStack, false)) {
/*     */       return;
/*     */     }
/*  85 */     if (CORRESPONDING_SLOT.containsKey(Integer.valueOf(paramInventoryClickEvent.getSlot()))) {
/*  86 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  87 */         (new StatEdition(this, ItemStats.CUSTOM_SOUNDS, new Object[] { CORRESPONDING_SLOT.get(Integer.valueOf(paramInventoryClickEvent.getSlot())) })).enable(new String[] { "Write in the chat the custom sound you want to add.", ChatColor.AQUA + "Format: [SOUND NAME] [VOLUME] [PITCH]", ChatColor.AQUA + "Example: entity.generic.drink 1 1" });
/*     */       }
/*     */ 
/*     */       
/*  91 */       if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/*  92 */         String str = CORRESPONDING_SLOT.get(Integer.valueOf(paramInventoryClickEvent.getSlot()));
/*  93 */         getEditedSection().set("sounds." + str, null);
/*     */ 
/*     */         
/*  96 */         if (getEditedSection().contains("sounds." + str) && 
/*  97 */           getEditedSection().getConfigurationSection("sounds." + str).getKeys(false).isEmpty()) {
/*  98 */           getEditedSection().set("sounds." + str, null);
/*  99 */           if (getEditedSection().getConfigurationSection("sounds").getKeys(false).isEmpty()) {
/* 100 */             getEditedSection().set("sounds", null);
/*     */           }
/*     */         } 
/* 103 */         registerTemplateEdition();
/* 104 */         this.player.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + MMOUtils.caseOnWords(str.replace("-", " ")) + " Sound" + ChatColor.GRAY + " successfully removed.");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\SoundsEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */