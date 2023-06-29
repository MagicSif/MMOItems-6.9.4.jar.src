/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.gui.edition.RevisionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
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
/*     */ public class RevisionID
/*     */   extends ItemStat<NumericStatFormula, DoubleData>
/*     */   implements GemStoneStat
/*     */ {
/*     */   public RevisionID() {
/*  38 */     super("REVISION_ID", Material.ITEM_FRAME, "Revision ID", new String[] { "The Revision ID is used to determine", "if an item is outdated or not. You", "should increase this whenever", "you make changes to your item!", "", "§6The updater is smart and will apply", "§6changes to the base stats of the item,", "§6keeping gemstones intact (for example)." }, new String[] { "all" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericStatFormula whenInitialized(Object paramObject) {
/*  45 */     if (paramObject instanceof Integer) {
/*  46 */       return new NumericStatFormula(((Integer)paramObject).intValue(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*  48 */     throw new IllegalArgumentException("Must specify a whole number");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/*  53 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramDoubleData));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull DoubleData paramDoubleData) {
/*  59 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  60 */     arrayList.add(new ItemTag(getNBTPath(), Integer.valueOf((int)paramDoubleData.getValue())));
/*  61 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  66 */     (new RevisionInventory(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited())).open(paramEditionInventory.getPage());
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {}
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/*  74 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  75 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath()))
/*  76 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.INTEGER)); 
/*  77 */     DoubleData doubleData = getLoadedNBT(arrayList);
/*  78 */     if (doubleData != null) paramReadMMOItem.setData(this, (StatData)doubleData);
/*     */   
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public DoubleData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/*  84 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*  85 */     if (itemTag != null) return new DoubleData(((Integer)itemTag.getValue()).intValue()); 
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<NumericStatFormula> paramOptional) {
/*  91 */     if (paramOptional.isPresent()) {
/*  92 */       NumericStatFormula numericStatFormula = paramOptional.get();
/*  93 */       paramList.add(ChatColor.GRAY + "Current Revision ID: " + ChatColor.GREEN + (int)numericStatFormula.getBase());
/*     */     } else {
/*  95 */       paramList.add(ChatColor.GRAY + "Current Revision ID: " + ChatColor.GREEN + "1");
/*     */     } 
/*  97 */     paramList.add("");
/*  98 */     paramList.add(ChatColor.YELLOW + "►" + " Left click to increase this value.");
/*  99 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to decrease this value.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public DoubleData getClearStatData() {
/* 105 */     return new DoubleData(0.0D);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\RevisionID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */