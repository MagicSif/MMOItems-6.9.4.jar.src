/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
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
/*    */ 
/*    */ @Deprecated
/*    */ public class HideDye
/*    */   extends BooleanStat
/*    */ {
/*    */   public HideDye() {
/* 24 */     super("HIDE_DYE", Material.CYAN_DYE, "Hide Dyed", new String[] { "Enable to hide the 'Dyed' tag from the item." }, new String[] { "all" }, new Material[] { Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, VersionMaterial.LEATHER_HORSE_ARMOR
/* 25 */           .toMaterial() });
/*    */     
/*    */     try {
/* 28 */       ItemFlag.valueOf("HIDE_DYE");
/* 29 */     } catch (IllegalArgumentException illegalArgumentException) {
/* 30 */       disable();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull BooleanData paramBooleanData) {
/* 36 */     if (paramBooleanData.isEnabled()) {
/* 37 */       paramItemStackBuilder.getMeta().addItemFlags(new ItemFlag[] { ItemFlag.HIDE_DYE });
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ArrayList<ItemTag> getAppliedNBT(@NotNull BooleanData paramBooleanData) {
/* 46 */     return new ArrayList<>();
/*    */   }
/*    */   
/*    */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 50 */     if (paramReadMMOItem.getNBT().getItem().getItemMeta().hasItemFlag(ItemFlag.HIDE_DYE)) {
/* 51 */       paramReadMMOItem.setData(ItemStats.HIDE_DYE, (StatData)new BooleanData(true));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BooleanData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 61 */     return null;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\HideDye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */