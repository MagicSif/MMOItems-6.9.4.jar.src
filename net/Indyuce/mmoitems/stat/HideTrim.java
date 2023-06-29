/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import java.util.ArrayList;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*    */ import net.Indyuce.mmoitems.stat.data.BooleanData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.BooleanStat;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemFlag;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class HideTrim
/*    */   extends BooleanStat
/*    */ {
/*    */   public HideTrim() {
/* 22 */     super("HIDE_ARMOR_TRIM", Material.LEATHER_CHESTPLATE, "Hide Armor Trim", new String[] { "Hides armor trim from item lore." }, new String[] { "armor" }, new Material[0]);
/*    */     
/*    */     try {
/* 25 */       ItemFlag.valueOf("HIDE_ARMOR_TRIM");
/* 26 */     } catch (IllegalArgumentException illegalArgumentException) {
/* 27 */       disable();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull BooleanData paramBooleanData) {
/* 33 */     if (paramBooleanData.isEnabled()) paramItemStackBuilder.getMeta().addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ARMOR_TRIM });
/*    */   
/*    */   }
/*    */   
/*    */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 38 */     if (paramReadMMOItem.getNBT().getItem().getItemMeta().hasItemFlag(ItemFlag.HIDE_ARMOR_TRIM)) {
/* 39 */       paramReadMMOItem.setData(ItemStats.HIDE_ARMOR_TRIM, (StatData)new BooleanData(true));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ArrayList<ItemTag> getAppliedNBT(@NotNull BooleanData paramBooleanData) {
/* 49 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BooleanData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 59 */     return null;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\HideTrim.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */