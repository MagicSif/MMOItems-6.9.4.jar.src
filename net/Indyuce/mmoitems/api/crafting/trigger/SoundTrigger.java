/*    */ package net.Indyuce.mmoitems.api.crafting.trigger;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.Sound;
/*    */ 
/*    */ public class SoundTrigger
/*    */   extends Trigger {
/*    */   private final Sound sound;
/*    */   
/*    */   public SoundTrigger(MMOLineConfig paramMMOLineConfig) {
/* 12 */     super("sound");
/*    */     
/* 14 */     paramMMOLineConfig.validate(new String[] { "sound" });
/*    */     
/* 16 */     this.sound = Sound.valueOf(paramMMOLineConfig.getString("sound").toUpperCase().replace("-", "_"));
/* 17 */     this.vol = (float)paramMMOLineConfig.getDouble("volume", 1.0D);
/* 18 */     this.pitch = (float)paramMMOLineConfig.getDouble("pitch", 1.0D);
/*    */   }
/*    */   private final float vol; private final float pitch;
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {
/* 23 */     if (!paramPlayerData.isOnline())
/* 24 */       return;  paramPlayerData.getPlayer().playSound(paramPlayerData.getPlayer().getLocation(), this.sound, this.vol, this.pitch);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\trigger\SoundTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */