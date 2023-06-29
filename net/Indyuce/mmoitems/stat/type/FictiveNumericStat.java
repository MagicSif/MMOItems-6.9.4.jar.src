/*    */ package net.Indyuce.mmoitems.stat.type;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.element.Element;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.util.ElementStatType;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class FictiveNumericStat
/*    */   extends DoubleStat
/*    */   implements InternalStat
/*    */ {
/*    */   public FictiveNumericStat(Element paramElement, ElementStatType paramElementStatType) {
/* 32 */     super(paramElementStatType.getConcatenatedTagPath(paramElement), Material.BARRIER, "Fictive Stat", new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public NumericStatFormula whenInitialized(Object paramObject) {
/* 37 */     throw new RuntimeException("Fictive item stat");
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/* 42 */     throw new RuntimeException("Fictive item stat");
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ArrayList<ItemTag> getAppliedNBT(@NotNull DoubleData paramDoubleData) {
/* 48 */     throw new RuntimeException("Fictive item stat");
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/* 53 */     throw new RuntimeException("Fictive item stat");
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 58 */     throw new RuntimeException("Fictive item stat");
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public DoubleData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 64 */     throw new RuntimeException("Fictive item stat");
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenDisplayed(List<String> paramList, Optional<NumericStatFormula> paramOptional) {
/* 69 */     throw new RuntimeException("Fictive item stat");
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\FictiveNumericStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */