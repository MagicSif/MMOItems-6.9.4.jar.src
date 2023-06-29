/*    */ package net.Indyuce.mmoitems.comp.mmocore.crafting;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmocore.MMOCore;
/*    */ import net.Indyuce.mmocore.api.player.PlayerData;
/*    */ import net.Indyuce.mmocore.experience.EXPSource;
/*    */ import net.Indyuce.mmocore.experience.Profession;
/*    */ import net.Indyuce.mmoitems.api.crafting.trigger.Trigger;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ 
/*    */ public class ExperienceCraftingTrigger extends Trigger {
/*    */   private final Profession profession;
/*    */   private final int amount;
/*    */   
/*    */   public ExperienceCraftingTrigger(MMOLineConfig paramMMOLineConfig) {
/* 17 */     super("exp");
/*    */     
/* 19 */     paramMMOLineConfig.validate(new String[] { "profession", "amount" });
/*    */     
/* 21 */     this.amount = paramMMOLineConfig.getInt("amount");
/*    */     
/* 23 */     String str = paramMMOLineConfig.getString("profession").toLowerCase().replace("_", "-");
/* 24 */     if (!str.equalsIgnoreCase("main")) {
/* 25 */       Validate.isTrue(MMOCore.plugin.professionManager.has(str), "Could not find profession " + str);
/* 26 */       this.profession = MMOCore.plugin.professionManager.get(str);
/*    */     } else {
/* 28 */       this.profession = null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {
/* 33 */     if (this.profession == null) {
/* 34 */       PlayerData.get(paramPlayerData.getUniqueId()).giveExperience(this.amount, EXPSource.SOURCE);
/*    */     } else {
/* 36 */       PlayerData.get(paramPlayerData.getUniqueId()).getCollectionSkills().giveExperience(this.profession, this.amount, EXPSource.SOURCE);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\crafting\ExperienceCraftingTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */