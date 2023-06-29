/*     */ package net.Indyuce.mmoitems.api.item.util;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import io.lumine.mythic.lib.util.AdventureUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class ConfigItem
/*     */ {
/*     */   private final String id;
/*     */   private final ItemStack icon;
/*     */   private String name;
/*     */   private List<String> lore;
/*     */   private ItemStack item;
/*     */   @Nullable
/*     */   protected Material material;
/*     */   @Nullable
/*     */   protected Integer customModelData;
/*     */   
/*     */   public ConfigItem(String paramString, Material paramMaterial) {
/*  34 */     this(paramString, paramMaterial, null, new String[0]);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConfigItem(String paramString1, Material paramMaterial, String paramString2, String... paramVarArgs) {
/* 166 */     this.material = null;
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
/* 183 */     this.customModelData = null; Validate.notNull(paramString1, "ID cannot be null"); Validate.notNull(paramMaterial, "Material cannot be null"); this.id = paramString1; this.icon = new ItemStack(paramMaterial); this.name = paramString2; this.lore = Arrays.asList(paramVarArgs); } public ConfigItem(ConfigurationSection paramConfigurationSection) { this.material = null; this.customModelData = null;
/*     */     Validate.notNull(paramConfigurationSection, "Config cannot be null");
/*     */     this.id = paramConfigurationSection.getName();
/*     */     Validate.isTrue(paramConfigurationSection.contains("material"), "Could not find material");
/*     */     this.icon = MMOUtils.readIcon(paramConfigurationSection.getString("material"));
/*     */     this.name = paramConfigurationSection.getString("name", "");
/*     */     this.lore = paramConfigurationSection.getStringList("lore");
/*     */     updateItem(); }
/*     */ 
/*     */   
/*     */   public String getId() {
/*     */     return this.id;
/*     */   }
/*     */   
/*     */   public void setup(ConfigurationSection paramConfigurationSection) {
/*     */     paramConfigurationSection.set("name", getName());
/*     */     paramConfigurationSection.set("lore", getLore());
/*     */   }
/*     */   
/*     */   public void update(ConfigurationSection paramConfigurationSection) {
/*     */     Validate.notNull(paramConfigurationSection, "Config cannot be null");
/*     */     setName(paramConfigurationSection.getString("name", ""));
/*     */     setLore(paramConfigurationSection.contains("lore") ? paramConfigurationSection.getStringList("lore") : new ArrayList<>());
/*     */     String str = paramConfigurationSection.getString("material");
/*     */     if (str != null && !str.isEmpty())
/*     */       try {
/*     */         Material material = Material.valueOf(paramConfigurationSection.getString("material"));
/*     */         if (material.isItem())
/*     */           setMaterial(material); 
/*     */       } catch (IllegalArgumentException illegalArgumentException) {} 
/*     */     setModel(SilentNumbers.IntegerParse(paramConfigurationSection.getString("model")));
/*     */     updateItem();
/*     */   }
/*     */   
/*     */   public void updateItem() {
/*     */     setItem(this.icon);
/*     */     if (this.icon.getType() == Material.AIR)
/*     */       return; 
/*     */     ItemMeta itemMeta = this.item.getItemMeta();
/*     */     AdventureUtils.setDisplayName(itemMeta, this.name);
/*     */     itemMeta.addItemFlags(ItemFlag.values());
/*     */     if (hasLore())
/*     */       AdventureUtils.setLore(itemMeta, (List)getLore().stream().map(paramString -> ChatColor.GRAY + paramString).collect(Collectors.toList())); 
/*     */     this.item.setItemMeta(itemMeta);
/*     */     this.item = MythicLib.plugin.getVersion().getWrapper().getNBTItem(this.item).addTag(new ItemTag[] { new ItemTag("ItemId", this.id) }).toItem();
/*     */   }
/*     */   
/*     */   public String getName() {
/*     */     return this.name;
/*     */   }
/*     */   
/*     */   public List<String> getLore() {
/*     */     return this.lore;
/*     */   }
/*     */   
/*     */   public boolean hasLore() {
/*     */     return (this.lore != null);
/*     */   }
/*     */   
/*     */   public ItemStack getItem() {
/*     */     return this.item;
/*     */   }
/*     */   
/*     */   public ItemStack getNewItem() {
/*     */     return this.item.clone();
/*     */   }
/*     */   
/*     */   protected void setName(String paramString) {
/*     */     this.name = paramString;
/*     */   }
/*     */   
/*     */   protected void setLore(List<String> paramList) {
/*     */     this.lore = paramList;
/*     */   }
/*     */   
/*     */   protected void setItem(ItemStack paramItemStack) {
/*     */     this.item = paramItemStack;
/*     */   }
/*     */   
/*     */   protected void setMaterial(@Nullable Material paramMaterial) {
/*     */     this.material = paramMaterial;
/*     */   }
/*     */   
/*     */   protected void setModel(@Nullable Integer paramInteger) {
/*     */     this.customModelData = paramInteger;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ite\\util\ConfigItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */