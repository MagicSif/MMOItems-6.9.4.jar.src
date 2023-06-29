/*    */ package net.Indyuce.mmoitems.api.crafting;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.crafting.uifilters.VanillaUIFilter;
/*    */ import io.lumine.mythic.lib.api.crafting.uimanager.UIFilterCountermatch;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VanillaMMOItemCountermatch
/*    */   implements UIFilterCountermatch
/*    */ {
/*    */   public static void enable() {
/* 22 */     VanillaUIFilter.get().addMatchOverride(new VanillaMMOItemCountermatch());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean preventsMatching(@NotNull ItemStack paramItemStack, @Nullable FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/* 29 */     return NBTItem.get(paramItemStack).hasType();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\VanillaMMOItemCountermatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */