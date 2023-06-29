/*     */ package net.Indyuce.mmoitems.gui.edition;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ConfigFile;
/*     */ import net.Indyuce.mmoitems.api.item.ItemReference;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.item.template.TemplateModifier;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.gui.PluginInventory;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EditionInventory
/*     */   extends PluginInventory
/*     */ {
/*     */   protected MMOItemTemplate template;
/*     */   private final ConfigFile configFile;
/*     */   @Deprecated
/*     */   private TemplateModifier editedModifier;
/*     */   private ItemStack cachedItem;
/*     */   int previousPage;
/*     */   @NotNull
/*     */   final FriendlyFeedbackProvider ffp;
/*     */   
/*     */   public EditionInventory(@NotNull Player paramPlayer, @NotNull MMOItemTemplate paramMMOItemTemplate) {
/*  61 */     super(paramPlayer);
/*     */ 
/*     */     
/*  64 */     this.ffp = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/*  65 */     this.ffp.activatePrefix(true, "Edition");
/*     */ 
/*     */     
/*  68 */     this.template = paramMMOItemTemplate;
/*  69 */     this.configFile = paramMMOItemTemplate.getType().getConfigFile();
/*  70 */     paramPlayer.getOpenInventory();
/*  71 */     if (paramPlayer.getOpenInventory().getTopInventory().getHolder() instanceof EditionInventory)
/*  72 */       this.cachedItem = ((EditionInventory)paramPlayer.getOpenInventory().getTopInventory().getHolder()).cachedItem; 
/*     */   }
/*     */   
/*     */   public MMOItemTemplate getEdited() {
/*  76 */     return this.template;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConfigurationSection getEditedSection() {
/*  86 */     ConfigurationSection configurationSection = this.configFile.getConfig().getConfigurationSection(this.template.getId());
/*  87 */     Validate.notNull(configurationSection, "Could not find config section associated to the template '" + this.template.getType().getId() + "." + this.template.getId() + "': make sure the config section name is in capital letters");
/*     */     
/*  89 */     return configurationSection.getConfigurationSection((this.editedModifier == null) ? "base" : ("modifiers." + this.editedModifier.getId() + ".stats"));
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
/*     */ 
/*     */ 
/*     */   
/*     */   public <R extends net.Indyuce.mmoitems.stat.data.random.RandomStatData<S>, S extends net.Indyuce.mmoitems.stat.data.type.StatData> Optional<R> getEventualStatData(ItemStat<R, S> paramItemStat) {
/* 106 */     Map map = (this.editedModifier != null) ? this.editedModifier.getItemData() : this.template.getBaseItemData();
/* 107 */     return map.containsKey(paramItemStat) ? Optional.<R>of((R)map.get(paramItemStat)) : Optional.<R>empty();
/*     */   }
/*     */   
/*     */   public void registerTemplateEdition() {
/* 111 */     this.configFile.registerTemplateEdition((ItemReference)this.template);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     this.template = MMOItems.plugin.getTemplates().getTemplate(this.template.getType(), this.template.getId());
/* 122 */     this.editedModifier = (this.editedModifier != null) ? this.template.getModifier(this.editedModifier.getId()) : null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     Bukkit.getScheduler().runTask((Plugin)MMOItems.plugin, () -> {
/*     */           updateCachedItem();
/*     */           open();
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateCachedItem() {
/* 140 */     this.cachedItem = this.template.newBuilder(PlayerData.get((OfflinePlayer)getPlayer()).getRPG()).build().newBuilder().buildSilently();
/*     */   }
/*     */   
/*     */   public ItemStack getCachedItem() {
/* 144 */     if (this.cachedItem != null) {
/* 145 */       return this.cachedItem;
/*     */     }
/* 147 */     updateCachedItem();
/* 148 */     return this.cachedItem;
/*     */   }
/*     */   
/*     */   public void addEditionInventoryItems(Inventory paramInventory, boolean paramBoolean) {
/* 152 */     ItemStack itemStack = new ItemStack(Material.CHEST);
/* 153 */     ItemMeta itemMeta = itemStack.getItemMeta();
/* 154 */     itemMeta.addItemFlags(ItemFlag.values());
/* 155 */     itemMeta.setDisplayName(ChatColor.GREEN + "✤" + " Get the Item! " + "✤");
/* 156 */     ArrayList<String> arrayList = new ArrayList();
/* 157 */     arrayList.add(ChatColor.GRAY + "");
/* 158 */     arrayList.add(ChatColor.GRAY + "You may also use /mi give " + this.template.getType().getId() + " " + this.template.getId());
/* 159 */     arrayList.add(ChatColor.GRAY + "");
/* 160 */     arrayList.add(ChatColor.YELLOW + "▸" + " Left click to get the item.");
/* 161 */     arrayList.add(ChatColor.YELLOW + "▸" + " Right click to reroll its stats.");
/* 162 */     itemMeta.setLore(arrayList);
/* 163 */     itemStack.setItemMeta(itemMeta);
/*     */     
/* 165 */     if (paramBoolean) {
/* 166 */       ItemStack itemStack1 = new ItemStack(Material.BARRIER);
/* 167 */       ItemMeta itemMeta1 = itemStack1.getItemMeta();
/* 168 */       itemMeta1.setDisplayName(ChatColor.GREEN + "⇨" + " Back");
/* 169 */       itemStack1.setItemMeta(itemMeta1);
/*     */       
/* 171 */       paramInventory.setItem(6, itemStack1);
/*     */     } 
/*     */     
/* 174 */     paramInventory.setItem(2, itemStack);
/* 175 */     paramInventory.setItem(4, getCachedItem());
/*     */   }
/*     */   
/*     */   public void open(int paramInt) {
/* 179 */     this.previousPage = paramInt;
/* 180 */     open();
/*     */   }
/*     */   
/*     */   public int getPreviousPage() {
/* 184 */     return this.previousPage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public FriendlyFeedbackProvider getFFP() {
/* 192 */     return this.ffp;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\EditionInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */