/*    */ package net.Indyuce.mmoitems.comp.rpg;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import net.Indyuce.mmoitems.comp.mmocore.MMOCoreHook;
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
/*    */ 
/*    */ 
/*    */ public interface RPGHandler
/*    */ {
/*    */   RPGPlayer getInfo(PlayerData paramPlayerData);
/*    */   
/*    */   void refreshStats(PlayerData paramPlayerData);
/*    */   
/*    */   public enum PluginEnum
/*    */   {
/* 32 */     MMOCORE("MMOCore", MMOCoreHook.class),
/* 33 */     HEROES("Heroes", HeroesHook.class),
/* 34 */     PROSKILLAPI("ProSkillAPI", ProSkillAPIHook.class),
/* 35 */     SKILLAPI("SkillAPI", SkillAPIHook.class),
/* 36 */     RPGPLAYERLEVELING("RPGPlayerLeveling", RPGPlayerLevelingHook.class),
/* 37 */     RACESANDCLASSES("RacesAndClasses", RacesAndClassesHook.class),
/* 38 */     BATTLELEVELS("BattleLevels", BattleLevelsHook.class),
/* 39 */     MCMMO("mcMMO", McMMOHook.class),
/* 40 */     MCRPG("McRPG", McRPGHook.class),
/* 41 */     SKILLS("Skills", SkillsHook.class),
/* 42 */     AURELIUM_SKILLS("AureliumSkills", AureliumSkillsHook.class),
/* 43 */     SKILLSPRO("SkillsPro", SkillsProHook.class);
/*    */     
/*    */     private final Class<? extends RPGHandler> pluginClass;
/*    */     private final String name;
/*    */     
/*    */     PluginEnum(String param1String1, Class<? extends RPGHandler> param1Class) {
/* 49 */       this.pluginClass = param1Class;
/* 50 */       this.name = param1String1;
/*    */     }
/*    */     
/*    */     public RPGHandler load() {
/* 54 */       return this.pluginClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/*    */     }
/*    */     
/*    */     public String getName() {
/* 58 */       return this.name;
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\rpg\RPGHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */