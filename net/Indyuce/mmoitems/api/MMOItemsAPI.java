/*    */ package net.Indyuce.mmoitems.api;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.api.player.MMOPlayerData;
/*    */ import io.lumine.mythic.lib.damage.AttackMetadata;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import io.lumine.mythic.lib.skill.result.SkillResult;
/*    */ import io.lumine.mythic.lib.skill.trigger.TriggerMetadata;
/*    */ import io.lumine.mythic.lib.skill.trigger.TriggerType;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import net.Indyuce.mmoitems.skill.RegisteredSkill;
/*    */ import net.Indyuce.mmoitems.stat.data.AbilityData;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class MMOItemsAPI
/*    */ {
/*    */   private final JavaPlugin plugin;
/*    */   
/*    */   public MMOItemsAPI(JavaPlugin paramJavaPlugin) {
/* 31 */     this.plugin = paramJavaPlugin;
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
/*    */ 
/*    */   
/*    */   public void registerSkill(RegisteredSkill paramRegisteredSkill) {
/* 46 */     MMOItems.plugin.getSkills().registerSkill(paramRegisteredSkill);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RegisteredSkill getSkillById(String paramString) {
/* 53 */     return Objects.<RegisteredSkill>requireNonNull(MMOItems.plugin.getSkills().getSkill(paramString), "Could not find skill with ID '" + paramString + "'");
/*    */   }
/*    */   
/*    */   public PlayerData getPlayerData(Player paramPlayer) {
/* 57 */     return PlayerData.get((OfflinePlayer)paramPlayer);
/*    */   }
/*    */   
/*    */   public RPGPlayer getRPGInfo(Player paramPlayer) {
/* 61 */     return PlayerData.get((OfflinePlayer)paramPlayer).getRPG();
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SkillResult castSkill(Player paramPlayer, RegisteredSkill paramRegisteredSkill, @NotNull Map<String, Double> paramMap, @Nullable LivingEntity paramLivingEntity, @Nullable AttackMetadata paramAttackMetadata) {
/* 80 */     AbilityData abilityData = new AbilityData(paramRegisteredSkill, TriggerType.CAST);
/* 81 */     paramMap.forEach((paramString, paramDouble) -> paramAbilityData.setModifier(paramString, paramDouble.doubleValue()));
/*    */     
/* 83 */     PlayerMetadata playerMetadata = MMOPlayerData.get((OfflinePlayer)paramPlayer).getStatMap().cache(EquipmentSlot.MAIN_HAND);
/* 84 */     return abilityData.cast(new TriggerMetadata(playerMetadata, (Entity)paramLivingEntity, paramAttackMetadata));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\MMOItemsAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */