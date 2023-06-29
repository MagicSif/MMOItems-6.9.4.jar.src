/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.stat.data.StringListData;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.StringListStat;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.event.inventory.InventoryAction;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class Lore extends StringListStat implements GemStoneStat {
/*    */   public Lore() {
/* 26 */     super("LORE", VersionMaterial.WRITABLE_BOOK.toMaterial(), "Lore", new String[] { "The item lore." }, new String[] { "all" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public StringListData whenInitialized(Object paramObject) {
/* 32 */     Validate.isTrue(paramObject instanceof List, "Must specify a string list");
/* 33 */     return new StringListData((List)paramObject);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringListData paramStringListData) {
/* 40 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramStringListData));
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/* 45 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/* 46 */       (new StatEdition(paramEditionInventory, ItemStats.LORE, new Object[0])).enable(new String[] { "Write in the chat the lore line you want to add." });
/*    */     }
/* 48 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && paramEditionInventory.getEditedSection().contains("lore")) {
/* 49 */       List<String> list = paramEditionInventory.getEditedSection().getStringList("lore");
/* 50 */       if (list.isEmpty()) {
/*    */         return;
/*    */       }
/* 53 */       String str = list.get(list.size() - 1);
/* 54 */       list.remove(str);
/* 55 */       paramEditionInventory.getEditedSection().set("lore", list.isEmpty() ? null : list);
/* 56 */       paramEditionInventory.registerTemplateEdition();
/* 57 */       paramEditionInventory.getPlayer()
/* 58 */         .sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed '" + MythicLib.plugin.parseColors(str) + ChatColor.GRAY + "'.");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 64 */     List<String> list = paramEditionInventory.getEditedSection().contains("lore") ? paramEditionInventory.getEditedSection().getStringList("lore") : new ArrayList();
/* 65 */     list.add(paramString);
/* 66 */     paramEditionInventory.getEditedSection().set("lore", list);
/* 67 */     paramEditionInventory.registerTemplateEdition();
/* 68 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Lore successfully added.");
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenDisplayed(List<String> paramList, Optional<StringListData> paramOptional) {
/* 73 */     paramOptional.ifPresentOrElse(paramStringListData -> {
/*    */           paramList.add(ChatColor.GRAY + "Current Value:");
/*    */ 
/*    */           
/*    */           paramList.addAll(MythicLib.plugin.parseColors(paramStringListData.getList().stream().map(()).toList()));
/*    */         }() -> paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None"));
/*    */ 
/*    */     
/* 81 */     paramList.add("");
/* 82 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add a line.");
/* 83 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last line.");
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\Lore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */