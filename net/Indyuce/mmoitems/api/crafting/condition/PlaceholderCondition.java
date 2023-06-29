/*    */ package net.Indyuce.mmoitems.api.crafting.condition;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import java.util.logging.Level;
/*    */ import me.clip.placeholderapi.PlaceholderAPI;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ 
/*    */ public class PlaceholderCondition
/*    */   extends GenericCondition {
/*    */   private final String expression1;
/*    */   
/*    */   public PlaceholderCondition(MMOLineConfig paramMMOLineConfig) {
/* 15 */     super("placeholder", paramMMOLineConfig);
/*    */     
/* 17 */     paramMMOLineConfig.validateKeys(new String[] { "placeholder" });
/* 18 */     String[] arrayOfString = paramMMOLineConfig.getString("placeholder").split("~");
/* 19 */     Validate.isTrue((arrayOfString.length == 3), "Please use exactly twice ~");
/* 20 */     this.expression1 = arrayOfString[0];
/* 21 */     this.comparator = arrayOfString[1];
/* 22 */     this.expression2 = arrayOfString[2];
/*    */   }
/*    */   private final String comparator; private final String expression2;
/* 25 */   private static final double EQUALITY_THRESHOLD = Math.pow(10.0D, -5.0D);
/*    */ 
/*    */   
/*    */   public boolean isMet(PlayerData paramPlayerData) {
/* 29 */     String str1 = PlaceholderAPI.setPlaceholders(paramPlayerData.getPlayer(), this.expression1);
/* 30 */     String str2 = PlaceholderAPI.setPlaceholders(paramPlayerData.getPlayer(), this.expression2);
/*    */     try {
/* 32 */       switch (this.comparator) {
/*    */         case "<":
/* 34 */           return (Double.parseDouble(str1) < Double.parseDouble(str2));
/*    */         case "<=":
/* 36 */           return (Double.parseDouble(str1) <= Double.parseDouble(str2));
/*    */         case ">":
/* 38 */           return (Double.parseDouble(str1) > Double.parseDouble(str2));
/*    */         case ">=":
/* 40 */           return (Double.parseDouble(str1) >= Double.parseDouble(str2));
/*    */         case "==":
/*    */         case "=":
/* 43 */           return (Math.abs(Double.parseDouble(str1) - Double.parseDouble(str2)) <= EQUALITY_THRESHOLD);
/*    */         case "!=":
/* 45 */           return (Math.abs(Double.parseDouble(str1) - Double.parseDouble(str2)) > EQUALITY_THRESHOLD);
/*    */         case "equals":
/* 47 */           return str1.equals(str2);
/*    */       } 
/* 49 */       throw new RuntimeException("Comparator not recognized");
/*    */     }
/* 51 */     catch (RuntimeException runtimeException) {
/* 52 */       MMOItems.plugin.getLogger().log(Level.WARNING, "Could not evaluate placeholder condition expression: " + runtimeException.getMessage());
/* 53 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {}
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\condition\PlaceholderCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */