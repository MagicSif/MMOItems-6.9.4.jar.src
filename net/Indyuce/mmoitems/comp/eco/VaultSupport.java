/*    */ package net.Indyuce.mmoitems.comp.eco;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import java.util.logging.Level;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.crafting.ConditionalDisplay;
/*    */ import net.milkbowl.vault.economy.Economy;
/*    */ import net.milkbowl.vault.permission.Permission;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.plugin.RegisteredServiceProvider;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VaultSupport
/*    */ {
/*    */   private final Economy economy;
/*    */   private final Permission permissions;
/*    */   
/*    */   public VaultSupport() {
/* 20 */     RegisteredServiceProvider registeredServiceProvider1 = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
/* 21 */     this.economy = (registeredServiceProvider1 != null) ? (Economy)registeredServiceProvider1.getProvider() : null;
/*    */     
/* 23 */     RegisteredServiceProvider registeredServiceProvider2 = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
/* 24 */     this.permissions = (registeredServiceProvider2 != null) ? (Permission)registeredServiceProvider2.getProvider() : null;
/*    */     
/* 26 */     if (this.economy == null) {
/* 27 */       MMOItems.plugin.getLogger().log(Level.SEVERE, "Could not load Economy Support (Vault)");
/*    */     } else {
/* 29 */       MMOItems.plugin.getCrafting().registerCondition("money", MoneyCondition::new, new ConditionalDisplay("&a✔ Requires $#money#", "&c✖ Requires $#money#"));
/*    */     } 
/*    */     
/* 32 */     if (this.permissions == null) {
/* 33 */       MMOItems.plugin.getLogger().log(Level.SEVERE, "Could not load Permissions Support (Vault)");
/*    */     }
/*    */     
/* 36 */     if (this.economy != null || this.permissions != null)
/* 37 */       MMOItems.plugin.getLogger().log(Level.INFO, "Hooked onto Vault"); 
/*    */   }
/*    */   
/*    */   public Permission getPermissions() {
/* 41 */     return this.permissions;
/*    */   }
/*    */   
/*    */   public Economy getEconomy() {
/* 45 */     return this.economy;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\eco\VaultSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */