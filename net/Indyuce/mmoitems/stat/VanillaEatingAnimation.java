/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.BooleanData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.BooleanStat;
/*    */ import org.bukkit.Material;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class VanillaEatingAnimation
/*    */   extends BooleanStat {
/*    */   public VanillaEatingAnimation() {
/* 14 */     super("VANILLA_EATING", Material.COOKED_BEEF, "Vanilla Eating Animation", new String[] { "When enabled, players have to wait", "for the vanilla eating animation", "in order to eat the consumable.", "", "Only works on items that", "can normally be eaten." }, new String[] { "consumable" }, new Material[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull BooleanData paramBooleanData) {
/* 19 */     if (paramBooleanData.isEnabled())
/* 20 */       paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag("MMOITEMS_VANILLA_EATING", Boolean.valueOf(true)) }); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\VanillaEatingAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */