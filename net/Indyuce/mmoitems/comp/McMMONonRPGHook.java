/*    */ package net.Indyuce.mmoitems.comp;
/*    */ 
/*    */ import com.gmail.nossr50.events.skills.repair.McMMOPlayerRepairCheckEvent;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
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
/*    */ public class McMMONonRPGHook
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler(ignoreCancelled = true)
/*    */   public void handleNoMcMMORepair(McMMOPlayerRepairCheckEvent paramMcMMOPlayerRepairCheckEvent) {
/* 23 */     NBTItem nBTItem = NBTItem.get(paramMcMMOPlayerRepairCheckEvent.getRepairedObject());
/* 24 */     if (nBTItem.hasType() && nBTItem.getBoolean("MMOITEMS_DISABLE_MCMMO_REPAIR"))
/* 25 */       paramMcMMOPlayerRepairCheckEvent.setCancelled(true); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\McMMONonRPGHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */