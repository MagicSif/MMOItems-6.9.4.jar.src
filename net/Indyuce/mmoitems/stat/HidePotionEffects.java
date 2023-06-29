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
/*    */ 
/*    */ @Deprecated
/*    */ public class HidePotionEffects
/*    */   extends BooleanStat
/*    */ {
/*    */   public HidePotionEffects() {
/* 23 */     super("HIDE_POTION_EFFECTS", Material.POTION, "Hide Potion Effects", new String[] { "Hides potion effects & 'No Effects'", "from your item lore." }, new String[] { "all" }, new Material[] { Material.POTION, Material.SPLASH_POTION, Material.LINGERING_POTION, Material.TIPPED_ARROW });
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull BooleanData paramBooleanData) {
/* 28 */     if (paramBooleanData.isEnabled()) {
/* 29 */       paramItemStackBuilder.getMeta().addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
/*    */     }
/*    */   }
/*    */   
/*    */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 34 */     if (paramReadMMOItem.getNBT().getItem().getItemMeta().hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS)) {
/* 35 */       paramReadMMOItem.setData(ItemStats.HIDE_POTION_EFFECTS, (StatData)new BooleanData(true));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ArrayList<ItemTag> getAppliedNBT(@NotNull BooleanData paramBooleanData) {
/* 44 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BooleanData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 52 */     return null;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\HidePotionEffects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */