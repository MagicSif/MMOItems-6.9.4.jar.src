/*    */ package net.Indyuce.mmoitems.comp.mythicmobs.crafting;
/*    */ 
/*    */ import io.lumine.mythic.bukkit.BukkitAdapter;
/*    */ import io.lumine.mythic.bukkit.MythicBukkit;
/*    */ import io.lumine.mythic.core.items.MythicItem;
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import java.util.Optional;
/*    */ import net.Indyuce.mmoitems.api.crafting.ingredient.Ingredient;
/*    */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.PlayerIngredient;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class MythicItemIngredient
/*    */   extends Ingredient<MythicItemPlayerIngredient>
/*    */ {
/*    */   private final MythicItem mythicitem;
/*    */   private final String display;
/*    */   
/*    */   public MythicItemIngredient(MMOLineConfig paramMMOLineConfig) {
/* 28 */     super("mythicitem", paramMMOLineConfig);
/*    */     
/* 30 */     paramMMOLineConfig.validate(new String[] { "item" });
/* 31 */     Optional<MythicItem> optional = MythicBukkit.inst().getItemManager().getItem(paramMMOLineConfig.getString("item"));
/* 32 */     Validate.isTrue(optional.isPresent(), "Could not find MM Item with ID '" + paramMMOLineConfig.getString("item") + "'");
/*    */     
/* 34 */     this.display = paramMMOLineConfig.contains("display") ? paramMMOLineConfig.getString("display") : ((MythicItem)optional.get()).getDisplayName();
/* 35 */     this.mythicitem = optional.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getKey() {
/* 40 */     return "mythicitem:" + this.mythicitem.getInternalName().toLowerCase();
/*    */   }
/*    */ 
/*    */   
/*    */   public String formatDisplay(String paramString) {
/* 45 */     return paramString.replace("#item#", this.display).replace("#amount#", "" + getAmount());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(MythicItemPlayerIngredient paramMythicItemPlayerIngredient) {
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack generateItemStack(@NotNull RPGPlayer paramRPGPlayer, boolean paramBoolean) {
/* 56 */     return BukkitAdapter.adapt(this.mythicitem.generateItemStack(getAmount()));
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     return getKey();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mythicmobs\crafting\MythicItemIngredient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */