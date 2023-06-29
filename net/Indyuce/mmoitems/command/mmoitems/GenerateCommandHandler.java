/*    */ package net.Indyuce.mmoitems.command.mmoitems;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class GenerateCommandHandler {
/*  7 */   private final Set<String> arguments = new HashSet<>();
/*    */   
/*    */   public GenerateCommandHandler(String... paramVarArgs) {
/* 10 */     for (String str : paramVarArgs)
/* 11 */       this.arguments.add(str.toLowerCase()); 
/*    */   }
/*    */   
/*    */   public boolean hasArgument(String paramString) {
/* 15 */     for (String str : this.arguments) {
/* 16 */       if (str.startsWith("-" + paramString))
/* 17 */         return true; 
/* 18 */     }  return false;
/*    */   }
/*    */   
/*    */   public String getValue(String paramString) {
/* 22 */     for (String str : this.arguments) {
/* 23 */       if (str.startsWith("-" + paramString + ":"))
/* 24 */         return str.substring(paramString.length() + 2); 
/*    */     } 
/* 26 */     throw new IllegalArgumentException("Command has no argument '" + paramString + "'");
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\GenerateCommandHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */