/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.rba;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.util.ItemFactory;
/*    */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.type.RBA_BooleanButton;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.inventory.meta.KnowledgeBookMeta;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RBA_HideFromBook
/*    */   extends RBA_BooleanButton
/*    */ {
/*    */   public static final String BOOK_HIDDEN = "hidden";
/*    */   @NotNull
/*    */   final ItemStack booleanButton;
/*    */   
/*    */   public RBA_HideFromBook(@NotNull RecipeMakerGUI paramRecipeMakerGUI) {
/* 30 */     super(paramRecipeMakerGUI);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 62 */     this.booleanButton = RecipeMakerGUI.addLore(ItemFactory.of(Material.KNOWLEDGE_BOOK).name("§cHide from Crafting Book").lore(SilentNumbers.chop("Even if the crafting book is enabled, this recipe wont be automatically unlocked by players.", 65, "§7"))
/*    */         
/* 64 */         .build(), SilentNumbers.toArrayList((Object[])new String[] { "" }));
/*    */   } @NotNull public String getBooleanConfigPath() { return "hidden"; } @NotNull
/* 66 */   public ItemStack getBooleanButton() { return this.booleanButton; }
/*    */   public boolean runSecondary() { ItemStack itemStack = new ItemStack(Material.KNOWLEDGE_BOOK); ItemMeta itemMeta = itemStack.getItemMeta(); if (itemMeta instanceof KnowledgeBookMeta)
/*    */       ((KnowledgeBookMeta)itemMeta).addRecipe(new NamespacedKey[] { MMOItems.plugin.getRecipes().getRecipeKey(getInv().getEdited().getType(), getInv().getEdited().getId(), getInv().getRecipeRegistry().getRecipeConfigPath(), getInv().getRecipeName()) });  itemStack.setItemMeta(itemMeta);
/*    */     getInv().getPlayer().getInventory().addItem(new ItemStack[] { itemStack });
/*    */     getInv().getPlayer().playSound(getInv().getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 2.0F);
/* 71 */     return true; } @NotNull public ItemStack getButton() { String str = isEnabled() ? "§cNO§8, it's hidden." : "§aYES§8, it's shown.";
/*    */ 
/*    */     
/* 74 */     return RecipeMakerGUI.addLore(getBooleanButton().clone(), 
/* 75 */         SilentNumbers.toArrayList((Object[])new String[] { "", "§7Currently in Book? " + str, "", ChatColor.YELLOW + "►" + " Right click to generate recipe unlock book.", ChatColor.YELLOW + "►" + " Left click to toggle this option." })); }
/*    */ 
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\rba\RBA_HideFromBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */