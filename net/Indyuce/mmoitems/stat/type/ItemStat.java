/*     */ package net.Indyuce.mmoitems.stat.type;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ItemStat<R extends RandomStatData<S>, S extends StatData>
/*     */ {
/*     */   @NotNull
/*     */   private final String id;
/*     */   @NotNull
/*     */   private final String name;
/*     */   @NotNull
/*     */   private final String configPath;
/*     */   @NotNull
/*     */   private final String nbtPath;
/*     */   @NotNull
/*     */   private final Material material;
/*     */   private final String[] lore;
/*     */   private final List<String> compatibleTypes;
/*     */   private final List<Material> compatibleMaterials;
/*     */   private boolean enabled = true;
/*     */   
/*     */   public ItemStat(@NotNull String paramString1, @NotNull Material paramMaterial, @NotNull String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, Material... paramVarArgs) {
/*  51 */     this.id = paramString1;
/*  52 */     this.material = paramMaterial;
/*  53 */     this.lore = (paramArrayOfString1 == null) ? new String[0] : paramArrayOfString1;
/*  54 */     this.compatibleTypes = (paramArrayOfString2 == null) ? new ArrayList<>() : Arrays.<String>asList(paramArrayOfString2);
/*  55 */     this.name = paramString2;
/*  56 */     this.compatibleMaterials = Arrays.asList(paramVarArgs);
/*     */     
/*  58 */     this.configPath = paramString1.toLowerCase().replace("_", "-");
/*  59 */     this.nbtPath = "MMOITEMS_" + paramString1;
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
/*     */   public abstract R whenInitialized(Object paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull S paramS);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public abstract ArrayList<ItemTag> getAppliedNBT(@NotNull S paramS);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem);
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
/*     */   public abstract S getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void whenDisplayed(List<String> paramList, Optional<R> paramOptional);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/* 153 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getId() {
/* 163 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public String name() {
/* 174 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getPath() {
/* 182 */     return this.configPath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getNBTPath() {
/* 192 */     return this.nbtPath;
/*     */   }
/*     */   
/*     */   public Material getDisplayMaterial() {
/* 196 */     return this.material;
/*     */   }
/*     */   
/*     */   public boolean isEnabled() {
/* 200 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public String[] getLore() {
/* 204 */     return this.lore;
/*     */   }
/*     */   
/*     */   public List<String> getCompatibleTypes() {
/* 208 */     return this.compatibleTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCompatible(Type paramType) {
/* 216 */     String str = paramType.getId().toLowerCase();
/* 217 */     return paramType.isSubtype() ? isCompatible(paramType.getParent()) : (
/* 218 */       (!this.compatibleTypes.contains("!" + str) && (this.compatibleTypes.contains("all") || this.compatibleTypes.contains(str) || this.compatibleTypes
/* 219 */       .contains(paramType.getItemSet().getName().toLowerCase()))));
/*     */   }
/*     */   
/*     */   public boolean hasValidMaterial(ItemStack paramItemStack) {
/* 223 */     return (this.compatibleMaterials.size() == 0 || this.compatibleMaterials.contains(paramItemStack.getType()));
/*     */   }
/*     */   
/*     */   public void disable() {
/* 227 */     this.enabled = false;
/*     */   }
/*     */   
/*     */   public String formatNumericStat(double paramDouble, String... paramVarArgs) {
/* 231 */     String str = MMOItems.plugin.getLanguage().getStatFormat(getPath()).replace("<plus>", (paramDouble > 0.0D) ? "+" : "");
/* 232 */     for (byte b = 0; b < paramVarArgs.length; b += 2) {
/* 233 */       str = str.replace(paramVarArgs[b], paramVarArgs[b + 1]);
/*     */     }
/* 235 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 240 */     if (this == paramObject) return true; 
/* 241 */     if (paramObject == null || getClass() != paramObject.getClass()) return false; 
/* 242 */     ItemStat itemStat = (ItemStat)paramObject;
/* 243 */     return this.id.equals(itemStat.id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 248 */     return Objects.hash(new Object[] { this.id });
/*     */   }
/*     */   
/*     */   public static String translate(String paramString) {
/* 252 */     String str = MMOItems.plugin.getLanguage().getStatFormat(paramString);
/* 253 */     return (str == null) ? ("<TranslationNotFound:" + paramString + ">") : str;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public abstract S getClearStatData();
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\ItemStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */