/*    */ package net.Indyuce.mmoitems.api.crafting.condition;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ 
/*    */ public class ClassCondition
/*    */   extends Condition {
/*    */   private final List<String> classes;
/*    */   
/*    */   public ClassCondition(MMOLineConfig paramMMOLineConfig) {
/* 13 */     super("class");
/*    */     
/* 15 */     paramMMOLineConfig.validate(new String[] { "list" });
/* 16 */     this.classes = Arrays.asList(paramMMOLineConfig.getString("list").split(","));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMet(PlayerData paramPlayerData) {
/* 21 */     return this.classes.contains(paramPlayerData.getRPG().getClassName());
/*    */   }
/*    */ 
/*    */   
/*    */   public String formatDisplay(String paramString) {
/* 26 */     return paramString.replace("#class#", String.join(", ", (Iterable)this.classes));
/*    */   }
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {}
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\condition\ClassCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */