/*    */ package net.Indyuce.mmoitems.comp.mythicmobs.crafting;
/*    */ 
/*    */ import io.lumine.mythic.api.skills.Skill;
/*    */ import io.lumine.mythic.bukkit.MythicBukkit;
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Optional;
/*    */ import net.Indyuce.mmoitems.api.crafting.trigger.Trigger;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class MythicMobsSkillTrigger
/*    */   extends Trigger {
/*    */   private final Skill skill;
/*    */   
/*    */   public MythicMobsSkillTrigger(MMOLineConfig paramMMOLineConfig) {
/* 19 */     super("mmskill");
/*    */     
/* 21 */     paramMMOLineConfig.validate(new String[] { "id" });
/* 22 */     String str = paramMMOLineConfig.getString("id");
/* 23 */     Optional<Skill> optional = MythicBukkit.inst().getSkillManager().getSkill(str);
/* 24 */     Validate.isTrue(optional.isPresent(), "Could not find MM skill " + str);
/* 25 */     this.skill = optional.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {
/* 30 */     if (!paramPlayerData.isOnline())
/* 31 */       return;  ArrayList<Player> arrayList = new ArrayList();
/* 32 */     arrayList.add(paramPlayerData.getPlayer());
/* 33 */     MythicBukkit.inst().getAPIHelper().castSkill((Entity)paramPlayerData.getPlayer(), this.skill.getInternalName(), (Entity)paramPlayerData.getPlayer(), paramPlayerData.getPlayer().getEyeLocation(), arrayList, null, 1.0F);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mythicmobs\crafting\MythicMobsSkillTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */