/*    */ package net.Indyuce.mmoitems.util;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ConfigFile;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LanguageFile
/*    */   extends ConfigFile
/*    */ {
/*    */   private boolean change;
/*    */   
/*    */   public LanguageFile(String paramString) {
/* 19 */     super((Plugin)MMOItems.plugin, "/language", paramString);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public String computeTranslation(String paramString, Provider<String> paramProvider) {
/* 24 */     String str = getConfig().getString(paramString);
/* 25 */     if (str == null) {
/* 26 */       this.change = true;
/* 27 */       getConfig().set(paramString, str = paramProvider.get());
/* 28 */       MMOItems.plugin.getLogger().log(Level.INFO, "Could not find translation for '" + paramString + "', generating it");
/*    */     } 
/*    */     
/* 31 */     return str;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void save() {
/* 39 */     if (this.change)
/* 40 */       super.save(); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitem\\util\LanguageFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */