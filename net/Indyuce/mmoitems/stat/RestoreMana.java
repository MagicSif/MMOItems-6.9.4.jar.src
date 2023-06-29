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
/*    */ public class RestoreMana
/*    */   extends DoubleStat
/*    */   implements PlayerConsumable
/*    */ {
/*    */   public RestoreMana() {
/* 20 */     super("RESTORE_MANA", VersionMaterial.LAPIS_LAZULI.toMaterial(), "Restore Mana", new String[] { "The amount of mana", "your consumable restores." }, new String[] { "consumable" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onConsume(@NotNull VolatileMMOItem paramVolatileMMOItem, @NotNull Player paramPlayer, boolean paramBoolean) {
/* 27 */     if (!paramVolatileMMOItem.hasData(ItemStats.RESTORE_MANA)) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     DoubleData doubleData = (DoubleData)paramVolatileMMOItem.getData(ItemStats.RESTORE_MANA);
/*    */ 
/*    */     
/* 34 */     if (doubleData.getValue() != 0.0D)
/* 35 */       PlayerData.get((OfflinePlayer)paramPlayer).getRPG().giveMana(doubleData.getValue()); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\RestoreMana.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */