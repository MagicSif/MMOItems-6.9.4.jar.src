/*    */ package net.Indyuce.mmoitems.comp.enchants;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import me.badbones69.crazyenchantments.Methods;
/*    */ import me.badbones69.crazyenchantments.api.CrazyEnchantments;
/*    */ import me.badbones69.crazyenchantments.api.objects.CEnchantment;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.InternalStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import org.apache.commons.lang.NotImplementedException;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrazyEnchantsStat
/*    */   extends ItemStat<RandomStatData<CrazyEnchantsData>, CrazyEnchantsData>
/*    */   implements InternalStat
/*    */ {
/*    */   public CrazyEnchantsStat() {
/* 32 */     super("CRAZY_ENCHANTS", Material.BOOK, "Advanced Enchants", new String[0], new String[] { "all" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull CrazyEnchantsData paramCrazyEnchantsData) {
/* 41 */     Map<CEnchantment, Integer> map = paramCrazyEnchantsData.getEnchants();
/*    */     
/* 43 */     for (Map.Entry<CEnchantment, Integer> entry : map.entrySet()) {
/* 44 */       CEnchantment cEnchantment = (CEnchantment)entry.getKey();
/* 45 */       int i = ((Integer)entry.getValue()).intValue();
/* 46 */       paramItemStackBuilder.getLore().insert(0, Methods.color(cEnchantment.getColor() + cEnchantment.getCustomName() + " " + CrazyEnchantments.getInstance().convertLevelString(i)));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 52 */     Map<CEnchantment, Integer> map = CrazyEnchantments.getInstance().getEnchantments(paramReadMMOItem.getNBT().getItem());
/* 53 */     if (map.size() > 0) {
/* 54 */       paramReadMMOItem.setData(this, new CrazyEnchantsData(map));
/*    */     }
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public RandomStatData<CrazyEnchantsData> whenInitialized(Object paramObject) {
/* 60 */     throw new NotImplementedException();
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/* 65 */     throw new NotImplementedException();
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 70 */     throw new NotImplementedException();
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenDisplayed(List<String> paramList, Optional<RandomStatData<CrazyEnchantsData>> paramOptional) {
/* 75 */     throw new NotImplementedException();
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public CrazyEnchantsData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 81 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ArrayList<ItemTag> getAppliedNBT(@NotNull CrazyEnchantsData paramCrazyEnchantsData) {
/* 87 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public CrazyEnchantsData getClearStatData() {
/* 92 */     return new CrazyEnchantsData(new HashMap<>());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\enchants\CrazyEnchantsStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */