/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.stat.data.StringListData;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.StringListStat;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.event.inventory.InventoryAction;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GrantedPermissions
/*    */   extends StringListStat
/*    */   implements GemStoneStat
/*    */ {
/*    */   public GrantedPermissions() {
/* 34 */     super("GRANTED_PERMISSIONS", Material.NAME_TAG, "Granted Permissions", new String[] { "A list of permissions that will,", "be granted by the item." }, new String[] { "all" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StringListData whenInitialized(Object paramObject) {
/* 41 */     Validate.isTrue(paramObject instanceof List, "Must specify a string list");
/* 42 */     return new StringListData((List)paramObject);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/* 47 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 48 */       (new StatEdition(paramEditionInventory, ItemStats.GRANTED_PERMISSIONS, new Object[0])).enable(new String[] { "Write in the chat the permission you want to add." });
/*    */     }
/* 50 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && paramEditionInventory.getEditedSection().contains(getPath())) {
/* 51 */       List<String> list = paramEditionInventory.getEditedSection().getStringList(getPath());
/* 52 */       if (list.isEmpty()) {
/*    */         return;
/*    */       }
/* 55 */       String str = list.get(list.size() - 1);
/* 56 */       list.remove(str);
/* 57 */       paramEditionInventory.getEditedSection().set(getPath(), list.isEmpty() ? null : list);
/* 58 */       paramEditionInventory.registerTemplateEdition();
/* 59 */       paramEditionInventory.getPlayer()
/* 60 */         .sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed '" + MythicLib.plugin.parseColors(str) + ChatColor.GRAY + "'.");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 66 */     List<String> list = paramEditionInventory.getEditedSection().contains(getPath()) ? paramEditionInventory.getEditedSection().getStringList(getPath()) : new ArrayList();
/* 67 */     list.add(paramString);
/* 68 */     paramEditionInventory.getEditedSection().set(getPath(), list);
/* 69 */     paramEditionInventory.registerTemplateEdition();
/* 70 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Permission successfully added.");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenDisplayed(List<String> paramList, Optional<StringListData> paramOptional) {
/* 76 */     if (paramOptional.isPresent()) {
/* 77 */       paramList.add(ChatColor.GRAY + "Current Value:");
/* 78 */       StringListData stringListData = paramOptional.get();
/* 79 */       stringListData.getList().forEach(paramString -> paramList.add(ChatColor.GRAY + paramString));
/*    */     } else {
/*    */       
/* 82 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*    */     } 
/* 84 */     paramList.add("");
/* 85 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add a permission.");
/* 86 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last permission.");
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\GrantedPermissions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */