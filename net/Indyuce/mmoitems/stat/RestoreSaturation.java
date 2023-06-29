/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.stat.type.PlayerConsumable;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RestoreSaturation
/*    */   extends DoubleStat
/*    */   implements PlayerConsumable
/*    */ {
/*    */   public RestoreSaturation() {
/* 22 */     super("RESTORE_SATURATION", Material.GOLDEN_CARROT, "Saturation Restoration", new String[] { "Saturation given when consumed." }, new String[] { "consumable" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onConsume(@NotNull VolatileMMOItem paramVolatileMMOItem, @NotNull Player paramPlayer, boolean paramBoolean) {
/* 36 */     double d1 = getSaturationRestored(paramVolatileMMOItem.getNBT().getItem());
/* 37 */     double d2 = paramVolatileMMOItem.hasData(ItemStats.RESTORE_SATURATION) ? ((DoubleData)paramVolatileMMOItem.getData(ItemStats.RESTORE_SATURATION)).getValue() : d1;
/* 38 */     d2 -= paramBoolean ? d1 : 0.0D;
/*    */ 
/*    */     
/* 41 */     if (d2 != 0.0D)
/* 42 */       MMOUtils.saturate(paramPlayer, d2); 
/*    */   }
/*    */   
/*    */   private float getSaturationRestored(ItemStack paramItemStack) {
/*    */     try {
/* 47 */       return MythicLib.plugin.getVersion().getWrapper().getSaturationRestored(paramItemStack);
/* 48 */     } catch (Exception exception) {
/*    */ 
/*    */       
/* 51 */       return 0.0F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\RestoreSaturation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */