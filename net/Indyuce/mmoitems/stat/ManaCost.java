/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import net.Indyuce.mmoitems.api.util.message.Message;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemRestriction;
/*    */ import net.Indyuce.mmoitems.stat.type.PlayerConsumable;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ManaCost
/*    */   extends DoubleStat
/*    */   implements ItemRestriction, PlayerConsumable
/*    */ {
/*    */   public ManaCost() {
/* 27 */     super("MANA_COST", VersionMaterial.LAPIS_LAZULI.toMaterial(), "Mana Cost", new String[] { "Mana spent by your weapon to be used." }, new String[] { "piercing", "slashing", "blunt", "range" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse(RPGPlayer paramRPGPlayer, NBTItem paramNBTItem, boolean paramBoolean) {
/* 34 */     if (!paramNBTItem.hasTag(ItemStats.MANA_COST.getNBTPath()))
/* 35 */       return true; 
/* 36 */     double d = paramNBTItem.getDouble(ItemStats.MANA_COST.getNBTPath());
/* 37 */     boolean bool = (d > 0.0D && paramRPGPlayer.getMana() >= d) ? true : false;
/* 38 */     if (!bool)
/* 39 */       Message.NOT_ENOUGH_MANA.format(ChatColor.RED, new String[0]).send(paramRPGPlayer.getPlayer()); 
/* 40 */     return bool;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onConsume(@NotNull VolatileMMOItem paramVolatileMMOItem, @NotNull Player paramPlayer, boolean paramBoolean) {
/* 46 */     if (!paramVolatileMMOItem.hasData(ItemStats.MANA_COST)) {
/*    */       return;
/*    */     }
/* 49 */     DoubleData doubleData = (DoubleData)paramVolatileMMOItem.getData(ItemStats.MANA_COST);
/* 50 */     if (doubleData.getValue() > 0.0D) {
/* 51 */       RPGPlayer rPGPlayer = PlayerData.get((OfflinePlayer)paramPlayer).getRPG();
/* 52 */       rPGPlayer.setMana(rPGPlayer.getMana() - doubleData.getValue());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\ManaCost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */