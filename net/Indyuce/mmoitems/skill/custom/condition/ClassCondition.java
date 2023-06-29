/*    */ package net.Indyuce.mmoitems.skill.custom.condition;
/*    */ 
/*    */ import io.lumine.mythic.lib.script.condition.Condition;
/*    */ import io.lumine.mythic.lib.skill.SkillMetadata;
/*    */ import io.lumine.mythic.lib.util.configobject.ConfigObject;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ 
/*    */ @Deprecated
/*    */ public class ClassCondition extends Condition {
/*    */   private final List<String> classes;
/*    */   
/*    */   public ClassCondition(ConfigObject paramConfigObject) {
/* 16 */     super(paramConfigObject);
/*    */     
/* 18 */     paramConfigObject.validateKeys(new String[] { "list" });
/* 19 */     this.classes = Arrays.asList(paramConfigObject.getString("list").split(","));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMet(SkillMetadata paramSkillMetadata) {
/* 24 */     PlayerData playerData = PlayerData.get((OfflinePlayer)paramSkillMetadata.getCaster().getPlayer());
/* 25 */     return this.classes.contains(playerData.getRPG().getClassName());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\skill\custom\condition\ClassCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */