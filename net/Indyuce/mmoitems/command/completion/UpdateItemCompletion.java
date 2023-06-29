/*    */ package net.Indyuce.mmoitems.command.completion;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.command.TabCompleter;
/*    */ 
/*    */ 
/*    */ public class UpdateItemCompletion
/*    */   implements TabCompleter
/*    */ {
/*    */   public List<String> onTabComplete(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString) {
/* 17 */     if (!paramCommandSender.hasPermission("mmoitems.update")) {
/* 18 */       return null;
/*    */     }
/* 20 */     ArrayList<String> arrayList = new ArrayList();
/*    */     
/* 22 */     if (paramArrayOfString.length == 1)
/* 23 */       for (Type type : MMOItems.plugin.getTypes().getAll()) {
/* 24 */         arrayList.add(type.getId());
/*    */       } 
/* 26 */     if (paramArrayOfString.length == 2 && Type.isValid(paramArrayOfString[0])) {
/* 27 */       Type.get(paramArrayOfString[0]).getConfigFile().getConfig().getKeys(false).forEach(paramString -> paramList.add(paramString.toUpperCase()));
/*    */     }
/* 29 */     return paramArrayOfString[paramArrayOfString.length - 1].isEmpty() ? arrayList : (List<String>)arrayList.stream().filter(paramString -> paramString.toLowerCase().startsWith(paramArrayOfString[paramArrayOfString.length - 1].toLowerCase())).collect(Collectors.toList());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\completion\UpdateItemCompletion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */