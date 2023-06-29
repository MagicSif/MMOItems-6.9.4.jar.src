/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.stat.type.PlayerConsumable;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class RestoreHealth
/*    */   extends DoubleStat
/*    */   implements PlayerConsumable
/*    */ {
/*    */   public RestoreHealth() {
/* 20 */     super("RESTORE_HEALTH", VersionMaterial.RED_DYE.toMaterial(), "Health Restoration", new String[] { "Health given when consumed." }, new String[] { "consumable" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onConsume(@NotNull VolatileMMOItem paramVolatileMMOItem, @NotNull Player paramPlayer, boolean paramBoolean) {
/* 28 */     if (!paramVolatileMMOItem.hasData(ItemStats.RESTORE_HEALTH)) {
/*    */       return;
/*    */     }
/*    */     
/* 32 */     DoubleData doubleData = (DoubleData)paramVolatileMMOItem.getData(ItemStats.RESTORE_HEALTH);
/*    */ 
/*    */     
/* 35 */     if (doubleData.getValue() != 0.0D)
/* 36 */       MMOUtils.heal((LivingEntity)paramPlayer, doubleData.getValue()); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\RestoreHealth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */