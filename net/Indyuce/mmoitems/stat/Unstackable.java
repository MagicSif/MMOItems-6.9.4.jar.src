/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import java.util.UUID;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.BooleanData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.BooleanStat;
/*    */ import org.bukkit.Material;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class Unstackable
/*    */   extends BooleanStat
/*    */ {
/*    */   public Unstackable() {
/* 16 */     super("UNSTACKABLE", Material.CHEST_MINECART, "Unstackable", new String[] { "This will make the item unable", "to be stacked with itself." }, new String[] { "all" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull BooleanData paramBooleanData) {
/* 22 */     if (paramBooleanData.isEnabled()) {
/* 23 */       paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag(getNBTPath(), Boolean.valueOf(true)) });
/* 24 */       paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag(getNBTPath() + "_UUID", UUID.randomUUID().toString()) });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\Unstackable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */