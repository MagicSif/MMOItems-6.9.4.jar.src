/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import org.apache.commons.lang.Validate;
/*    */ 
/*    */ public class CommandData
/*    */ {
/*    */   private final String command;
/*    */   private final double delay;
/*    */   
/*    */   public CommandData(String paramString, double paramDouble, boolean paramBoolean1, boolean paramBoolean2) {
/* 11 */     Validate.notNull(paramString, "Command cannot be null");
/*    */     
/* 13 */     this.command = paramString;
/* 14 */     this.delay = paramDouble;
/* 15 */     this.console = paramBoolean1;
/* 16 */     this.op = paramBoolean2;
/*    */   }
/*    */   private final boolean console; private final boolean op;
/*    */   public String getCommand() {
/* 20 */     return this.command;
/*    */   }
/*    */   
/*    */   public double getDelay() {
/* 24 */     return this.delay;
/*    */   }
/*    */   
/*    */   public boolean hasDelay() {
/* 28 */     return (this.delay > 0.0D);
/*    */   }
/*    */   
/*    */   public boolean isConsoleCommand() {
/* 32 */     return this.console;
/*    */   }
/*    */   
/*    */   public boolean hasOpPerms() {
/* 36 */     return this.op;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 41 */     if (this == paramObject) return true; 
/* 42 */     if (paramObject == null || getClass() != paramObject.getClass()) return false; 
/* 43 */     CommandData commandData = (CommandData)paramObject;
/* 44 */     return (Double.compare(commandData.delay, this.delay) == 0 && this.console == commandData.console && this.op == commandData.op && this.command.equals(commandData.command));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\CommandData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */