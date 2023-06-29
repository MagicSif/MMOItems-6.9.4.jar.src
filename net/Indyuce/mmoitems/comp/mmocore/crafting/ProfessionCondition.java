/*    */ package net.Indyuce.mmoitems.comp.mmocore.crafting;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmocore.MMOCore;
/*    */ import net.Indyuce.mmocore.api.player.PlayerData;
/*    */ import net.Indyuce.mmocore.experience.Profession;
/*    */ import net.Indyuce.mmoitems.api.crafting.condition.Condition;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ 
/*    */ public class ProfessionCondition extends Condition {
/*    */   private final Profession profession;
/*    */   
/*    */   public ProfessionCondition(MMOLineConfig paramMMOLineConfig) {
/* 15 */     super("profession");
/*    */     
/* 17 */     paramMMOLineConfig.validate(new String[] { "profession", "level" });
/*    */     
/* 19 */     this.level = paramMMOLineConfig.getInt("level");
/*    */     
/* 21 */     String str = paramMMOLineConfig.getString("profession").toLowerCase().replace("_", "-");
/* 22 */     Validate.isTrue(MMOCore.plugin.professionManager.has(str), "Could not find profession " + str);
/* 23 */     this.profession = MMOCore.plugin.professionManager.get(str);
/*    */   }
/*    */   
/*    */   private final int level;
/*    */   
/*    */   public String formatDisplay(String paramString) {
/* 29 */     return paramString.replace("#level#", "" + this.level).replace("#profession#", this.profession.getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMet(PlayerData paramPlayerData) {
/* 34 */     return (PlayerData.get(paramPlayerData.getUniqueId()).getCollectionSkills().getLevel(this.profession) >= this.level);
/*    */   }
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {}
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\crafting\ProfessionCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */