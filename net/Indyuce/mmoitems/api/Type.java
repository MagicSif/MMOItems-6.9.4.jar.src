/*     */ package net.Indyuce.mmoitems.api;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.UtilityMethods;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.player.modifier.ModifierSource;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nullable;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.util.identify.UnidentifiedItem;
/*     */ import net.Indyuce.mmoitems.manager.TypeManager;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Type
/*     */ {
/*  26 */   public static final Type SWORD = new Type(TypeSet.SLASHING, "SWORD", true, ModifierSource.MELEE_WEAPON);
/*     */ 
/*     */   
/*  29 */   public static final Type DAGGER = new Type(TypeSet.PIERCING, "DAGGER", true, ModifierSource.MELEE_WEAPON);
/*  30 */   public static final Type SPEAR = new Type(TypeSet.PIERCING, "SPEAR", true, ModifierSource.MELEE_WEAPON);
/*     */ 
/*     */   
/*  33 */   public static final Type HAMMER = new Type(TypeSet.BLUNT, "HAMMER", true, ModifierSource.MELEE_WEAPON);
/*  34 */   public static final Type GAUNTLET = new Type(TypeSet.BLUNT, "GAUNTLET", true, ModifierSource.MELEE_WEAPON);
/*     */ 
/*     */   
/*  37 */   public static final Type WHIP = new Type(TypeSet.RANGE, "WHIP", true, ModifierSource.RANGED_WEAPON);
/*  38 */   public static final Type STAFF = new Type(TypeSet.RANGE, "STAFF", true, ModifierSource.RANGED_WEAPON);
/*  39 */   public static final Type BOW = new Type(TypeSet.RANGE, "BOW", true, ModifierSource.RANGED_WEAPON);
/*  40 */   public static final Type CROSSBOW = new Type(TypeSet.RANGE, "CROSSBOW", false, ModifierSource.RANGED_WEAPON);
/*  41 */   public static final Type MUSKET = new Type(TypeSet.RANGE, "MUSKET", true, ModifierSource.RANGED_WEAPON);
/*  42 */   public static final Type LUTE = new Type(TypeSet.RANGE, "LUTE", true, ModifierSource.RANGED_WEAPON);
/*     */ 
/*     */   
/*  45 */   public static final Type CATALYST = new Type(TypeSet.CATALYST, "CATALYST", false, ModifierSource.HAND_ITEM);
/*  46 */   public static final Type OFF_CATALYST = new Type(TypeSet.CATALYST, "OFF_CATALYST", false, ModifierSource.OFFHAND_ITEM);
/*  47 */   public static final Type MAIN_CATALYST = new Type(TypeSet.CATALYST, "MAIN_CATALYST", false, ModifierSource.MAINHAND_ITEM);
/*     */ 
/*     */   
/*  50 */   public static final Type ORNAMENT = new Type(TypeSet.EXTRA, "ORNAMENT", false, ModifierSource.VOID);
/*     */ 
/*     */   
/*  53 */   public static final Type ARMOR = new Type(TypeSet.EXTRA, "ARMOR", false, ModifierSource.ARMOR);
/*  54 */   public static final Type TOOL = new Type(TypeSet.EXTRA, "TOOL", false, ModifierSource.MELEE_WEAPON);
/*  55 */   public static final Type CONSUMABLE = new Type(TypeSet.EXTRA, "CONSUMABLE", false, ModifierSource.MAINHAND_ITEM);
/*  56 */   public static final Type MISCELLANEOUS = new Type(TypeSet.EXTRA, "MISCELLANEOUS", false, ModifierSource.MAINHAND_ITEM);
/*  57 */   public static final Type GEM_STONE = new Type(TypeSet.EXTRA, "GEM_STONE", false, ModifierSource.VOID);
/*  58 */   public static final Type SKIN = new Type(TypeSet.EXTRA, "SKIN", false, ModifierSource.VOID);
/*  59 */   public static final Type ACCESSORY = new Type(TypeSet.EXTRA, "ACCESSORY", false, ModifierSource.ACCESSORY);
/*  60 */   public static final Type BLOCK = new Type(TypeSet.EXTRA, "BLOCK", false, ModifierSource.VOID);
/*     */ 
/*     */   
/*     */   private final String id;
/*     */ 
/*     */   
/*     */   private final TypeSet set;
/*     */ 
/*     */   
/*     */   private final ModifierSource modifierSource;
/*     */ 
/*     */   
/*     */   private final boolean weapon;
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private String loreFormat;
/*     */ 
/*     */   
/*     */   private ItemStack item;
/*     */ 
/*     */   
/*     */   private Type parent;
/*     */ 
/*     */   
/*     */   private UnidentifiedItem unidentifiedTemplate;
/*     */ 
/*     */   
/*  91 */   private final List<ItemStat> available = new ArrayList<>();
/*     */   
/*     */   @Deprecated
/*     */   public Type(TypeSet paramTypeSet, String paramString, boolean paramBoolean1, ModifierSource paramModifierSource, boolean paramBoolean2) {
/*  95 */     this(paramTypeSet, paramString, paramBoolean1, paramModifierSource);
/*     */   }
/*     */   
/*     */   public Type(TypeSet paramTypeSet, String paramString, boolean paramBoolean, ModifierSource paramModifierSource) {
/*  99 */     this.set = paramTypeSet;
/* 100 */     this.id = paramString.toUpperCase().replace("-", "_").replace(" ", "_");
/* 101 */     this.modifierSource = paramModifierSource;
/* 102 */     this.weapon = paramBoolean;
/* 103 */     this.loreFormat = null;
/*     */   }
/*     */   
/*     */   public Type(@NotNull TypeManager paramTypeManager, @NotNull ConfigurationSection paramConfigurationSection) {
/* 107 */     this.id = paramConfigurationSection.getName().toUpperCase().replace("-", "_").replace(" ", "_");
/*     */     
/* 109 */     this.parent = paramTypeManager.get(paramConfigurationSection.getString("parent", "").toUpperCase().replace("-", "_").replace(" ", "_"));
/*     */     
/* 111 */     this.set = (this.parent != null) ? this.parent.set : TypeSet.EXTRA;
/* 112 */     this.weapon = (this.parent != null && this.parent.weapon);
/* 113 */     this.modifierSource = (this.parent != null) ? this.parent.modifierSource : ModifierSource.OTHER;
/* 114 */     this.loreFormat = paramConfigurationSection.getString("LoreFormat", (this.parent != null) ? this.parent.loreFormat : null);
/*     */   }
/*     */   
/*     */   public void load(ConfigurationSection paramConfigurationSection) {
/* 118 */     Validate.notNull(paramConfigurationSection, "Could not find config for " + getId());
/*     */     
/* 120 */     this.name = paramConfigurationSection.getString("name", this.name);
/* 121 */     this.item = read(paramConfigurationSection.getString("display", (this.item == null) ? Material.STONE.toString() : this.item.getType().toString()));
/*     */     
/* 123 */     (this.unidentifiedTemplate = new UnidentifiedItem(this)).update(paramConfigurationSection.getConfigurationSection("unident-item"));
/*     */ 
/*     */     
/* 126 */     this.loreFormat = paramConfigurationSection.getString("LoreFormat", (this.parent != null) ? this.parent.loreFormat : this.loreFormat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public String name() {
/* 135 */     return getId();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 144 */     return this.id;
/*     */   }
/*     */   
/*     */   public TypeSet getItemSet() {
/* 148 */     return this.set;
/*     */   }
/*     */   
/*     */   public boolean isWeapon() {
/* 152 */     return this.weapon;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 156 */     return this.name;
/*     */   }
/*     */   
/*     */   public ModifierSource getModifierSource() {
/* 160 */     return this.modifierSource;
/*     */   }
/*     */   
/*     */   public ItemStack getItem() {
/* 164 */     return this.item.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean isSubtype() {
/* 172 */     return (this.parent != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Type getParent() {
/* 180 */     return this.parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFourGUIMode() {
/* 187 */     return ((getSupertype()).modifierSource == ModifierSource.ARMOR);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getLoreFormat() {
/* 195 */     return this.loreFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getSupertype() {
/* 202 */     Type type = this;
/* 203 */     while (type.parent != null)
/* 204 */       type = type.parent; 
/* 205 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean corresponds(Type paramType) {
/* 213 */     return getSupertype().equals(paramType);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean corresponds(TypeSet paramTypeSet) {
/* 218 */     return (getSupertype().getItemSet() == paramTypeSet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ItemStat> getAvailableStats() {
/* 227 */     return this.available;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConfigFile getConfigFile() {
/* 235 */     return new ConfigFile("/item", getId().toLowerCase());
/*     */   }
/*     */   
/*     */   public UnidentifiedItem getUnidentifiedTemplate() {
/* 239 */     return this.unidentifiedTemplate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean canHave(ItemStat paramItemStat) {
/* 249 */     return paramItemStat.isCompatible(this);
/*     */   }
/*     */   
/*     */   private ItemStack read(String paramString) {
/* 253 */     Validate.notNull(paramString, "Input must not be null");
/*     */     
/* 255 */     String[] arrayOfString = paramString.split(":");
/* 256 */     Material material = Material.valueOf(arrayOfString[0]);
/* 257 */     return (arrayOfString.length > 1) ? MythicLib.plugin.getVersion().getWrapper().textureItem(material, Integer.parseInt(arrayOfString[1])) : new ItemStack(material);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 262 */     if (this == paramObject) return true; 
/* 263 */     if (paramObject == null || getClass() != paramObject.getClass()) return false; 
/* 264 */     Type type = (Type)paramObject;
/* 265 */     return this.id.equals(type.id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 270 */     return Objects.hash(new Object[] { this.id });
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 275 */     return "Type{id='" + this.id + '\'' + '}';
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
/*     */   @Nullable
/*     */   public static Type get(@Nullable ItemStack paramItemStack) {
/* 288 */     if (paramItemStack == null) {
/* 289 */       return null;
/*     */     }
/* 291 */     return get(NBTItem.get(paramItemStack).getType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Type get(@Nullable String paramString) {
/* 302 */     if (paramString == null) return null;
/*     */     
/* 304 */     String str = UtilityMethods.enumName(paramString);
/* 305 */     return MMOItems.plugin.getTypes().has(str) ? MMOItems.plugin.getTypes().get(str) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isValid(@Nullable String paramString) {
/* 315 */     return (paramString != null && MMOItems.plugin.getTypes().has(paramString.toUpperCase().replace("-", "_").replace(" ", "_")));
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\Type.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */