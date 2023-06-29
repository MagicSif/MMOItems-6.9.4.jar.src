/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.InternalStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.apache.commons.lang.NotImplementedException;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class ItemLevel extends ItemStat<NumericStatFormula, DoubleData> implements InternalStat {
/*     */   public ItemLevel() {
/*  25 */     super("ITEM_LEVEL", VersionMaterial.EXPERIENCE_BOTTLE.toMaterial(), "Item Level", new String[] { "The item level" }, new String[] { "all" }, new org.bukkit.Material[0]);
/*     */   }
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/*  29 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramDoubleData));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public NumericStatFormula whenInitialized(Object paramObject) {
/*  34 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  39 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  44 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<NumericStatFormula> paramOptional) {
/*  49 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull DoubleData paramDoubleData) {
/*  57 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/*  60 */     arrayList.add(new ItemTag(getNBTPath(), Double.valueOf(paramDoubleData.getValue())));
/*     */     
/*  62 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/*  69 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  70 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/*  71 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.DOUBLE));
/*     */     }
/*     */     
/*  74 */     DoubleData doubleData = getLoadedNBT(arrayList);
/*     */     
/*  76 */     if (doubleData != null) paramReadMMOItem.setData(this, (StatData)doubleData);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public DoubleData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/*  84 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/*  87 */     if (itemTag != null)
/*     */     {
/*     */       
/*  90 */       return new DoubleData(((Double)itemTag.getValue()).doubleValue());
/*     */     }
/*     */ 
/*     */     
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public DoubleData getClearStatData() {
/* 100 */     return new DoubleData(0.0D);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\ItemLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */