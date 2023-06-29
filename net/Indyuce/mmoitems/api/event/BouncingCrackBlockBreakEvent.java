/*    */ package net.Indyuce.mmoitems.api.event;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class BouncingCrackBlockBreakEvent extends BlockBreakEvent {
/*    */   public BouncingCrackBlockBreakEvent(@NotNull Block paramBlock, @NotNull Player paramPlayer) {
/* 10 */     super(paramBlock, paramPlayer);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\BouncingCrackBlockBreakEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */