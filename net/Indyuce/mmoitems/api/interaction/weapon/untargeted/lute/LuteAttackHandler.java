/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted.lute;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import java.util.Random;
/*    */ import net.Indyuce.mmoitems.api.util.SoundReader;
/*    */ import net.Indyuce.mmoitems.stat.data.ProjectileParticlesData;
/*    */ import org.bukkit.util.Vector;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public interface LuteAttackHandler
/*    */ {
/* 13 */   public static final Random RANDOM = new Random();
/*    */   
/*    */   void handle(PlayerMetadata paramPlayerMetadata, double paramDouble1, NBTItem paramNBTItem, double paramDouble2, @NotNull Vector paramVector, @NotNull SoundReader paramSoundReader, @NotNull ProjectileParticlesData paramProjectileParticlesData);
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\lute\LuteAttackHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */