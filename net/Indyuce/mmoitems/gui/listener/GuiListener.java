/*    */ package net.Indyuce.mmoitems.gui.listener;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.gui.ItemBrowser;
/*    */ import net.Indyuce.mmoitems.gui.PluginInventory;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.gui.edition.ItemEdition;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.RecipeBrowserGUI;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.RecipeListGUI;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.InventoryAction;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class GuiListener
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void a(InventoryClickEvent paramInventoryClickEvent) {
/* 26 */     Player player = (Player)paramInventoryClickEvent.getWhoClicked();
/* 27 */     ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
/*    */     
/* 29 */     if (paramInventoryClickEvent.getInventory().getHolder() instanceof PluginInventory) {
/* 30 */       PluginInventory pluginInventory = (PluginInventory)paramInventoryClickEvent.getInventory().getHolder();
/* 31 */       pluginInventory.whenClicked(paramInventoryClickEvent);
/*    */ 
/*    */ 
/*    */       
/* 35 */       if (!(pluginInventory instanceof EditionInventory) || paramInventoryClickEvent.getInventory() != paramInventoryClickEvent.getClickedInventory() || !MMOUtils.isMetaItem(itemStack, false) || 
/* 36 */         !itemStack.getItemMeta().getDisplayName().startsWith(ChatColor.GREEN + "")) {
/*    */         return;
/*    */       }
/* 39 */       if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "✤" + " Get the Item! " + "✤"))
/*    */       {
/*    */         
/* 42 */         if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 43 */           for (ItemStack itemStack1 : player.getInventory().addItem(new ItemStack[] { paramInventoryClickEvent.getInventory().getItem(4) }).values()) {
/* 44 */             player.getWorld().dropItemNaturally(player.getLocation(), itemStack1);
/*    */           }
/*    */           
/* 47 */           if (NBTItem.get(paramInventoryClickEvent.getInventory().getItem(4)).getBoolean("MMOITEMS_UNSTACKABLE")) {
/* 48 */             ((EditionInventory)pluginInventory).updateCachedItem();
/* 49 */             paramInventoryClickEvent.getInventory().setItem(4, ((EditionInventory)pluginInventory).getCachedItem());
/*    */           
/*    */           }
/*    */         
/*    */         }
/* 54 */         else if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/* 55 */           for (ItemStack itemStack1 : player.getInventory().addItem(new ItemStack[] { paramInventoryClickEvent.getInventory().getItem(4) }).values()) {
/* 56 */             player.getWorld().dropItemNaturally(player.getLocation(), itemStack1);
/*    */           }
/* 58 */           ((EditionInventory)pluginInventory).updateCachedItem();
/* 59 */           paramInventoryClickEvent.getInventory().setItem(4, ((EditionInventory)pluginInventory).getCachedItem());
/*    */         } 
/*    */       }
/*    */       
/* 63 */       MMOItemTemplate mMOItemTemplate = ((EditionInventory)pluginInventory).getEdited();
/* 64 */       if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "⇨" + " Back"))
/*    */       {
/*    */         
/* 67 */         if (pluginInventory instanceof ItemEdition) { (new ItemBrowser(player, mMOItemTemplate.getType())).open();
/*    */            }
/*    */         
/* 70 */         else if (pluginInventory instanceof RecipeListGUI) { (new RecipeBrowserGUI(player, mMOItemTemplate)).open(((EditionInventory)pluginInventory).getPreviousPage());
/*    */            }
/*    */         
/* 73 */         else if (pluginInventory instanceof RecipeMakerGUI) { (new RecipeListGUI(player, mMOItemTemplate, ((RecipeMakerGUI)pluginInventory).getRecipeRegistry())).open(((EditionInventory)pluginInventory).getPreviousPage()); }
/*    */         else
/*    */         
/* 76 */         { (new ItemEdition(player, mMOItemTemplate)).onPage(((EditionInventory)pluginInventory).getPreviousPage()).open(); }
/*    */       
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\listener\GuiListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */