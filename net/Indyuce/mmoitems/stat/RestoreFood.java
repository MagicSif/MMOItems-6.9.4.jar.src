/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.stat.type.PlayerConsumable;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RestoreFood
/*    */   extends DoubleStat
/*    */   implements PlayerConsumable
/*    */ {
/*    */   public RestoreFood() {
/* 23 */     super("RESTORE_FOOD", VersionMaterial.PORKCHOP.toMaterial(), "Food Restoration", new String[] { "Food units given when consumed." }, new String[] { "consumable" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onConsume(@NotNull VolatileMMOItem paramVolatileMMOItem, @NotNull Player paramPlayer, boolean paramBoolean) {
/* 30 */     if (!paramVolatileMMOItem.hasData(ItemStats.RESTORE_FOOD)) {
/*    */       return;
/*    */     }
/*    */     
/* 34 */     DoubleData doubleData = (DoubleData)paramVolatileMMOItem.getData(ItemStats.RESTORE_FOOD);
/* 35 */     int i = SilentNumbers.ceil(doubleData.getValue() - (paramBoolean ? getFoodRestored(paramVolatileMMOItem.getNBT().getItem()) : false));
/*    */ 
/*    */     
/* 38 */     if (i != 0)
/* 39 */       MMOUtils.feed(paramPlayer, i); 
/*    */   }
/*    */   
/*    */   private int getFoodRestored(ItemStack paramItemStack) {
/*    */     try {
/* 44 */       return MythicLib.plugin.getVersion().getWrapper().getFoodRestored(paramItemStack);
/* 45 */     } catch (Exception exception) {
/*    */ 
/*    */       
/* 48 */       return 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\RestoreFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */