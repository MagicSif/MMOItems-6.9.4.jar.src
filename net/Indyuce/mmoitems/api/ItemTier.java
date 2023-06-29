/*     */ package net.Indyuce.mmoitems.api;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.UtilityMethods;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.droptable.DropTable;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemTier
/*     */ {
/*     */   private final String id;
/*     */   private final String name;
/*     */   private final String unparsedName;
/*     */   private final UnidentificationInfo unidentificationInfo;
/*     */   @Nullable
/*     */   private final DropTable deconstructTable;
/*     */   @Nullable
/*     */   private final ChatColor glowColor;
/*     */   private final boolean itemHint;
/*     */   @Nullable
/*     */   private final NumericStatFormula capacity;
/*     */   private final double chance;
/*     */   @NotNull
/*  41 */   private static final Random RANDOM = new Random();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemTier(@NotNull ConfigurationSection paramConfigurationSection) {
/*  50 */     this.id = paramConfigurationSection.getName().toUpperCase().replace("-", "_");
/*  51 */     this.unparsedName = paramConfigurationSection.getString("name");
/*  52 */     this.name = MythicLib.plugin.parseColors(this.unparsedName);
/*     */ 
/*     */     
/*  55 */     this.deconstructTable = paramConfigurationSection.contains("deconstruct-item") ? new DropTable(paramConfigurationSection.getConfigurationSection("deconstruct-item")) : null;
/*     */     
/*  57 */     ConfigurationSection configurationSection = paramConfigurationSection.getConfigurationSection("unidentification");
/*  58 */     this.unidentificationInfo = (configurationSection == null) ? UnidentificationInfo.DEFAULT : new UnidentificationInfo(configurationSection);
/*     */     
/*  60 */     if (paramConfigurationSection.contains("item-glow")) {
/*  61 */       this.itemHint = paramConfigurationSection.getBoolean("item-glow.hint");
/*  62 */       this.glowColor = ChatColor.valueOf(UtilityMethods.enumName(paramConfigurationSection.getString("item-glow.color", "WHITE")));
/*     */     } else {
/*  64 */       this.itemHint = false;
/*  65 */       this.glowColor = null;
/*     */     } 
/*     */ 
/*     */     
/*  69 */     this.chance = paramConfigurationSection.getDouble("generation.chance");
/*  70 */     this.capacity = paramConfigurationSection.contains("generation.capacity") ? new NumericStatFormula(paramConfigurationSection.get("generation.capacity")) : null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getId() {
/*  75 */     return this.id;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/*  80 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean hasDropTable() {
/*  84 */     return (this.deconstructTable != null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public DropTable getDropTable() {
/*  89 */     return this.deconstructTable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ItemStack> getDeconstructedLoot(@NotNull PlayerData paramPlayerData) {
/*  99 */     return hasDropTable() ? this.deconstructTable.read(paramPlayerData, false) : new ArrayList<>();
/*     */   }
/*     */   
/*     */   public boolean hasColor() {
/* 103 */     return (this.glowColor != null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ChatColor getColor() {
/* 108 */     return this.glowColor;
/*     */   }
/*     */   
/*     */   public boolean isHintEnabled() {
/* 112 */     return this.itemHint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getGenerationChance() {
/* 119 */     return this.chance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCapacity() {
/* 127 */     return (this.capacity != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public NumericStatFormula getModifierCapacity() {
/* 137 */     return this.capacity;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public UnidentificationInfo getUnidentificationInfo() {
/* 142 */     return this.unidentificationInfo;
/*     */   }
/*     */   @NotNull
/*     */   public String getUnparsedName() {
/* 146 */     return this.unparsedName;
/*     */   }
/*     */   
/*     */   public static class UnidentificationInfo { @NotNull
/*     */     private final String name;
/*     */     @NotNull
/*     */     private final String prefix;
/*     */     private final int range;
/*     */     public static final String DEFAULT_NAME = "Unidentified Item";
/*     */     public static final String DEFAULT_PREFIX = "Unknown";
/* 156 */     public static final UnidentificationInfo DEFAULT = new UnidentificationInfo("Unidentified Item", "Unknown", 0);
/*     */     
/*     */     public UnidentificationInfo(@NotNull ConfigurationSection param1ConfigurationSection) {
/* 159 */       this(param1ConfigurationSection.getString("name", "Unidentified Item"), param1ConfigurationSection.getString("prefix", "Unknown"), param1ConfigurationSection.getInt("range"));
/*     */     }
/*     */     
/*     */     public UnidentificationInfo(@NotNull String param1String1, @NotNull String param1String2, int param1Int) {
/* 163 */       this.name = param1String1;
/* 164 */       this.prefix = param1String2;
/* 165 */       this.range = param1Int;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public String getPrefix() {
/* 170 */       return this.prefix;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public String getDisplayName() {
/* 175 */       return this.name;
/*     */     }
/*     */     
/*     */     public int[] calculateRange(int param1Int) {
/* 179 */       int i = (int)Math.max(1.0D, param1Int - this.range * ItemTier.RANDOM.nextDouble());
/* 180 */       return new int[] { i, i + this.range };
/*     */     } }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static ItemTier ofItem(NBTItem paramNBTItem) {
/* 186 */     String str = paramNBTItem.getString("MMOITEMS_TIER");
/* 187 */     return (str == null) ? null : MMOItems.plugin.getTiers().get(str);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ItemTier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */