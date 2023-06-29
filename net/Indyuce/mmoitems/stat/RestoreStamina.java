/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.stat.type.PlayerConsumable;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class RestoreStamina
/*    */   extends DoubleStat
/*    */   implements PlayerConsumable
/*    */ {
/*    */   public RestoreStamina() {
/* 20 */     super("RESTORE_STAMINA", VersionMaterial.LIGHT_GRAY_DYE.toMaterial(), "Restore Stamina", new String[] { "The amount of stamina/power", "your consumable restores." }, new String[] { "consumable" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onConsume(@NotNull VolatileMMOItem paramVolatileMMOItem, @NotNull Player paramPlayer, boolean paramBoolean) {
/* 27 */     if (!paramVolatileMMOItem.hasData(ItemStats.RESTORE_STAMINA)) {
/*    */       return;
/*    */     }
/* 30 */     DoubleData doubleData = (DoubleData)paramVolatileMMOItem.getData(ItemStats.RESTORE_STAMINA);
/*    */ 
/*    */     
/* 33 */     if (doubleData.getValue() != 0.0D)
/* 34 */       PlayerData.get((OfflinePlayer)paramPlayer).getRPG().giveStamina(doubleData.getValue()); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\RestoreStamina.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */