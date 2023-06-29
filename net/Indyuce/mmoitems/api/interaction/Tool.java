/*    */ package net.Indyuce.mmoitems.api.interaction;
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.comp.flags.CustomFlag;
/*    */ import java.util.Map;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.event.BouncingCrackBlockBreakEvent;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class Tool extends UseItem {
/*    */   public Tool(Player paramPlayer, NBTItem paramNBTItem) {
/* 22 */     super(paramPlayer, paramNBTItem);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkItemRequirements() {
/* 27 */     return (MythicLib.plugin.getFlags().isFlagAllowed(this.player, CustomFlag.MI_TOOLS) && this.playerData.getRPG().canUse(getNBTItem(), true));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean miningEffects(final Block block) {
/* 35 */     boolean bool = false;
/*    */     
/* 37 */     if (getNBTItem().getBoolean("MMOITEMS_AUTOSMELT")) {
/* 38 */       Map map = MythicLib.plugin.getVersion().getWrapper().getOreDrops();
/* 39 */       Material material = (Material)map.get(block.getType());
/* 40 */       if (material != null) {
/* 41 */         UtilityMethods.dropItemNaturally(block.getLocation(), new ItemStack(material));
/* 42 */         block.getWorld().spawnParticle(Particle.CLOUD, block.getLocation().add(0.5D, 0.5D, 0.5D), 0);
/* 43 */         block.setType(Material.AIR);
/* 44 */         bool = true;
/*    */       } 
/*    */     } 
/*    */     
/* 48 */     if (getNBTItem().getBoolean("MMOITEMS_BOUNCING_CRACK") && !getPlayerData().isOnCooldown(PlayerData.CooldownType.BOUNCING_CRACK)) {
/* 49 */       getPlayerData().applyCooldown(PlayerData.CooldownType.BOUNCING_CRACK, 1.0D);
/* 50 */       (new BukkitRunnable() {
/* 51 */           final Vector v = Tool.this.player.getEyeLocation().getDirection().multiply(0.5D);
/* 52 */           final Location loc = block.getLocation().clone().add(0.5D, 0.5D, 0.5D);
/* 53 */           int j = 0;
/*    */           
/*    */           public void run() {
/* 56 */             if (this.j++ > 10) {
/* 57 */               cancel();
/*    */             }
/* 59 */             this.loc.add(this.v);
/* 60 */             Block block = this.loc.getBlock();
/* 61 */             if (block.getType() == Material.AIR || MMOItems.plugin.getLanguage().isBlacklisted(block.getType())) {
/*    */               return;
/*    */             }
/* 64 */             BouncingCrackBlockBreakEvent bouncingCrackBlockBreakEvent = new BouncingCrackBlockBreakEvent(block, Tool.this.player);
/* 65 */             Bukkit.getPluginManager().callEvent((Event)bouncingCrackBlockBreakEvent);
/* 66 */             if (bouncingCrackBlockBreakEvent.isCancelled()) {
/* 67 */               cancel();
/*    */               
/*    */               return;
/*    */             } 
/* 71 */             block.breakNaturally(Tool.this.getItem());
/* 72 */             this.loc.getWorld().playSound(this.loc, Sound.BLOCK_GRAVEL_BREAK, 1.0F, 1.0F);
/*    */           }
/* 74 */         }).runTaskTimer((Plugin)MMOItems.plugin, 0L, 1L);
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 84 */     return bool;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\Tool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */