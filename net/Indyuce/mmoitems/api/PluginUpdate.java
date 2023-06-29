/*    */ package net.Indyuce.mmoitems.api;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.util.Consumer;
/*    */ 
/*    */ public class PluginUpdate
/*    */ {
/*    */   private final int id;
/*    */   private final Consumer<CommandSender> handler;
/*    */   private final List<String> description;
/*    */   
/*    */   public PluginUpdate(int paramInt, String[] paramArrayOfString, Consumer<CommandSender> paramConsumer) {
/* 16 */     Validate.notNull(paramConsumer, "Update handler must not be null");
/* 17 */     Validate.notNull(paramArrayOfString, "Update description must not be null");
/*    */     
/* 19 */     this.id = paramInt;
/* 20 */     this.handler = paramConsumer;
/* 21 */     this.description = Arrays.asList(paramArrayOfString);
/*    */   }
/*    */   
/*    */   public int getId() {
/* 25 */     return this.id;
/*    */   }
/*    */   
/*    */   public void apply(CommandSender paramCommandSender) {
/* 29 */     this.handler.accept(paramCommandSender);
/*    */   }
/*    */   
/*    */   public List<String> getDescription() {
/* 33 */     return this.description;
/*    */   }
/*    */   
/*    */   public boolean hasDescription() {
/* 37 */     return !this.description.isEmpty();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\PluginUpdate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */