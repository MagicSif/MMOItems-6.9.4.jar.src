/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import java.util.ArrayList;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ItemSet;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.stat.data.StringData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.StringStat;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.event.inventory.InventoryAction;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ItemSetStat extends StringStat implements GemStoneStat {
/*    */   public ItemSetStat() {
/* 22 */     super("SET", Material.LEATHER_CHESTPLATE, "Item Set", new String[] { "Item sets can give to the player extra", "bonuses that depend on how many items", "from the same set your wear." }, new String[] { "!gem_stone", "!consumable", "!material", "!block", "!miscellaneous", "all" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/* 29 */     super.whenClicked(paramEditionInventory, paramInventoryClickEvent);
/* 30 */     if (paramInventoryClickEvent.getAction() != InventoryAction.PICKUP_HALF) {
/* 31 */       paramEditionInventory.getPlayer().sendMessage(ChatColor.GREEN + "Available Item Sets:");
/* 32 */       StringBuilder stringBuilder = new StringBuilder();
/* 33 */       for (ItemSet itemSet : MMOItems.plugin.getSets().getAll())
/* 34 */         stringBuilder.append(ChatColor.GREEN).append(itemSet.getId()).append(ChatColor.GRAY)
/* 35 */           .append(" (").append(itemSet.getName()).append(ChatColor.GRAY).append("), "); 
/* 36 */       if (stringBuilder.length() > 1)
/* 37 */         stringBuilder.setLength(stringBuilder.length() - 2); 
/* 38 */       paramEditionInventory.getPlayer().sendMessage(stringBuilder.toString());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringData paramStringData) {
/* 46 */     ItemSet itemSet = MMOItems.plugin.getSets().get(paramStringData.toString());
/*    */ 
/*    */     
/* 49 */     if (itemSet != null) {
/* 50 */       paramItemStackBuilder.getLore().insert("set", itemSet.getLoreTag());
/*    */     }
/*    */     
/* 53 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramStringData));
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ArrayList<ItemTag> getAppliedNBT(@NotNull StringData paramStringData) {
/* 59 */     ItemSet itemSet = MMOItems.plugin.getSets().get(paramStringData.toString());
/* 60 */     Validate.notNull(itemSet, "Could not find item set with ID '%s'".formatted(new Object[] { paramStringData }));
/*    */ 
/*    */     
/* 63 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*    */ 
/*    */     
/* 66 */     arrayList.add(new ItemTag(getNBTPath(), paramStringData.toString()));
/*    */ 
/*    */     
/* 69 */     return arrayList;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getNBTPath() {
/* 75 */     return "MMOITEMS_ITEM_SET";
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 80 */     ItemSet itemSet = MMOItems.plugin.getSets().get(paramString);
/* 81 */     Validate.notNull(itemSet, "Couldn't find the set named '%s'.".formatted(new Object[] { paramString }));
/* 82 */     super.whenInput(paramEditionInventory, paramString, paramVarArgs);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\ItemSetStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */