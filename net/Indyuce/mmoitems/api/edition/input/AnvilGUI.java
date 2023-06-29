/*    */ package net.Indyuce.mmoitems.api.edition.input;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.edition.Edition;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnvilGUI
/*    */   extends PlayerInputHandler
/*    */   implements Listener
/*    */ {
/*    */   private final int containerId;
/*    */   private final Inventory inventory;
/*    */   private boolean open;
/*    */   
/*    */   public AnvilGUI(Edition paramEdition) {
/* 30 */     super(paramEdition);
/*    */     
/* 32 */     ItemStack itemStack = new ItemStack(Material.PAPER);
/* 33 */     ItemMeta itemMeta = itemStack.getItemMeta();
/* 34 */     itemMeta.setDisplayName("Input text..");
/* 35 */     itemStack.setItemMeta(itemMeta);
/*    */     
/* 37 */     MythicLib.plugin.getVersion().getWrapper().handleInventoryCloseEvent(getPlayer());
/* 38 */     MythicLib.plugin.getVersion().getWrapper().setActiveContainerDefault(getPlayer());
/*    */     
/* 40 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)MMOItems.plugin);
/*    */     
/* 42 */     Object object = MythicLib.plugin.getVersion().getWrapper().newContainerAnvil(getPlayer());
/*    */     
/* 44 */     this.inventory = MythicLib.plugin.getVersion().getWrapper().toBukkitInventory(object);
/* 45 */     this.inventory.setItem(0, itemStack);
/*    */     
/* 47 */     this.containerId = MythicLib.plugin.getVersion().getWrapper().getNextContainerId(getPlayer());
/* 48 */     MythicLib.plugin.getVersion().getWrapper().sendPacketOpenWindow(getPlayer(), this.containerId);
/* 49 */     MythicLib.plugin.getVersion().getWrapper().setActiveContainer(getPlayer(), object);
/* 50 */     MythicLib.plugin.getVersion().getWrapper().setActiveContainerId(object, this.containerId);
/* 51 */     MythicLib.plugin.getVersion().getWrapper().addActiveContainerSlotListener(object, getPlayer());
/*    */     
/* 53 */     this.open = true;
/*    */   }
/*    */   
/*    */   public Inventory getInventory() {
/* 57 */     return this.inventory;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 62 */     if (!this.open)
/*    */       return; 
/* 64 */     this.open = false;
/*    */     
/* 66 */     MythicLib.plugin.getVersion().getWrapper().handleInventoryCloseEvent(getPlayer());
/* 67 */     MythicLib.plugin.getVersion().getWrapper().setActiveContainerDefault(getPlayer());
/* 68 */     MythicLib.plugin.getVersion().getWrapper().sendPacketCloseWindow(getPlayer(), this.containerId);
/*    */     
/* 70 */     HandlerList.unregisterAll(this);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void a(InventoryClickEvent paramInventoryClickEvent) {
/* 75 */     if (paramInventoryClickEvent.getInventory().equals(this.inventory)) {
/* 76 */       paramInventoryClickEvent.setCancelled(true);
/* 77 */       if (paramInventoryClickEvent.getRawSlot() == 2) {
/* 78 */         ItemStack itemStack = this.inventory.getItem(paramInventoryClickEvent.getRawSlot());
/* 79 */         if (itemStack != null && itemStack.getType() != Material.AIR)
/* 80 */           registerInput(itemStack.hasItemMeta() ? itemStack.getItemMeta().getDisplayName() : itemStack.getType().toString()); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void b(InventoryCloseEvent paramInventoryCloseEvent) {
/* 87 */     if (this.open && paramInventoryCloseEvent.getInventory().equals(this.inventory))
/* 88 */       close(); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\edition\input\AnvilGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */