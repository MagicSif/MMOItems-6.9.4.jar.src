/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import java.util.ArrayList;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.meta.Damageable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemDamage
/*    */   extends DoubleStat
/*    */   implements GemStoneStat
/*    */ {
/*    */   public ItemDamage() {
/* 30 */     super("ITEM_DAMAGE", Material.FISHING_ROD, "Item Damage", new String[] { "Default item damage. This does &cNOT", "impact the item's max durability." }, new String[] { "!block", "all" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/* 36 */     if (paramItemStackBuilder.getMeta() instanceof Damageable)
/* 37 */       ((Damageable)paramItemStackBuilder.getMeta()).setDamage((int)paramDoubleData.getValue()); 
/*    */   }
/*    */   
/*    */   public void whenPreviewed(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData, @NotNull NumericStatFormula paramNumericStatFormula) {
/* 41 */     whenApplied(paramItemStackBuilder, paramDoubleData);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ArrayList<ItemTag> getAppliedNBT(@NotNull DoubleData paramDoubleData) {
/* 49 */     return new ArrayList<>();
/*    */   }
/*    */   
/*    */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 53 */     if (paramReadMMOItem.getNBT().getItem().getItemMeta() instanceof Damageable) {
/* 54 */       paramReadMMOItem.setData(ItemStats.ITEM_DAMAGE, (StatData)new DoubleData(((Damageable)paramReadMMOItem.getNBT().getItem().getItemMeta()).getDamage()));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public DoubleData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 63 */     return null;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\ItemDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */