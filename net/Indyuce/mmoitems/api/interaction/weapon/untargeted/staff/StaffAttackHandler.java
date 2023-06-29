/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted.staff;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import java.util.Random;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ public interface StaffAttackHandler
/*    */ {
/* 11 */   public static final Random RANDOM = new Random();
/*    */   
/*    */   void handle(PlayerMetadata paramPlayerMetadata, double paramDouble1, NBTItem paramNBTItem, EquipmentSlot paramEquipmentSlot, double paramDouble2);
/*    */   
/*    */   default Location getGround(Location loc) {
/* 16 */     for (int j = 0; j < 20; j++) {
/* 17 */       if (loc.getBlock().getType().isSolid())
/* 18 */         return loc; 
/* 19 */       loc.add(0.0D, -1.0D, 0.0D);
/*    */     } 
/* 21 */     return loc;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\staff\StaffAttackHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */