/*    */ package net.Indyuce.mmoitems.api.player;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.api.stat.StatInstance;
/*    */ import io.lumine.mythic.lib.api.stat.StatMap;
/*    */ import io.lumine.mythic.lib.api.stat.modifier.StatModifier;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import io.lumine.mythic.lib.player.modifier.ModifierSource;
/*    */ import io.lumine.mythic.lib.player.modifier.ModifierType;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.player.inventory.EquippedItem;
/*    */ import net.Indyuce.mmoitems.stat.type.AttackWeaponStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ 
/*    */ public class PlayerStats {
/*    */   private final PlayerData playerData;
/*    */   
/*    */   public PlayerStats(PlayerData paramPlayerData) {
/* 19 */     this.playerData = paramPlayerData;
/*    */   }
/*    */   
/*    */   public PlayerData getData() {
/* 23 */     return this.playerData;
/*    */   }
/*    */   
/*    */   public StatMap getMap() {
/* 27 */     return this.playerData.getMMOPlayerData().getStatMap();
/*    */   }
/*    */   
/*    */   public double getStat(ItemStat<?, ?> paramItemStat) {
/* 31 */     return getMap().getInstance(paramItemStat.getId()).getTotal();
/*    */   }
/*    */   
/*    */   public StatInstance getInstance(ItemStat<?, ?> paramItemStat) {
/* 35 */     return getMap().getInstance(paramItemStat.getId());
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
/*    */   public PlayerMetadata newTemporary(EquipmentSlot paramEquipmentSlot) {
/* 48 */     return this.playerData.getMMOPlayerData().getStatMap().cache(paramEquipmentSlot);
/*    */   }
/*    */   
/*    */   public void updateStats() {
/* 52 */     for (ItemStat<?, ?> itemStat : (Iterable<ItemStat<?, ?>>)MMOItems.plugin.getStats().getNumericStats()) {
/*    */ 
/*    */       
/* 55 */       StatInstance.ModifierPacket modifierPacket = getInstance(itemStat).newPacket();
/*    */ 
/*    */       
/* 58 */       modifierPacket.removeIf(paramString -> paramString.startsWith("MMOItem"));
/*    */ 
/*    */       
/* 61 */       if (this.playerData.hasSetBonuses() && this.playerData.getSetBonuses().hasStat(itemStat)) {
/* 62 */         modifierPacket.addModifier(new StatModifier("MMOItemSetBonus", itemStat.getId(), this.playerData.getSetBonuses().getStat(itemStat), ModifierType.FLAT, EquipmentSlot.OTHER, ModifierSource.OTHER));
/*    */       }
/*    */       
/* 65 */       byte b = 0;
/*    */       
/* 67 */       for (EquippedItem equippedItem : this.playerData.getInventory().getEquipped()) {
/* 68 */         double d = equippedItem.getNBT().getStat(itemStat.getId());
/*    */         
/* 70 */         if (d != 0.0D) {
/* 71 */           ModifierSource modifierSource = equippedItem.getCached().getType().getModifierSource();
/*    */ 
/*    */           
/* 74 */           if (modifierSource.isWeapon() && itemStat instanceof AttackWeaponStat) {
/* 75 */             d -= ((AttackWeaponStat)itemStat).getOffset(this.playerData);
/*    */           }
/* 77 */           modifierPacket.addModifier(new StatModifier("MMOItem-" + b++, itemStat.getId(), d, ModifierType.FLAT, equippedItem.getSlot(), modifierSource));
/*    */         } 
/*    */       } 
/*    */ 
/*    */       
/* 82 */       modifierPacket.runUpdate();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\player\PlayerStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */