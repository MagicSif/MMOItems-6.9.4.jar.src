/*    */ package net.Indyuce.mmoitems.util;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ import java.util.logging.Level;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PluginUtils
/*    */ {
/*    */   public PluginUtils() {
/* 19 */     throw new IllegalStateException("Utility class");
/*    */   }
/*    */   
/*    */   public static void isDependencyPresent(@NotNull String paramString, @NotNull Consumer<Void> paramConsumer) {
/* 23 */     if (Bukkit.getPluginManager().getPlugin(paramString) != null)
/* 24 */       paramConsumer.accept(null); 
/*    */   }
/*    */   
/*    */   public static void hookDependencyIfPresent(@NotNull String paramString, @NotNull Consumer<Void> paramConsumer) {
/* 28 */     if (Bukkit.getPluginManager().getPlugin(paramString) == null)
/*    */       return; 
/* 30 */     paramConsumer.accept(null);
/* 31 */     MMOItems.plugin.getLogger().log(Level.INFO, "Hooked onto %s".formatted(new Object[] { paramString }));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitem\\util\PluginUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */