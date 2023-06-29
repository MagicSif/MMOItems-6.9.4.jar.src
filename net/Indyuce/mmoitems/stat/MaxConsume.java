/*    */ package net.Indyuce.mmoitems.stat;
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import org.bukkit.Material;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class MaxConsume extends DoubleStat {
/*    */   public MaxConsume() {
/* 13 */     super("MAX_CONSUME", Material.BLAZE_POWDER, "Max Consume", new String[] { "Max amount of usage before", "item disappears." }, new String[] { "consumable" }, new Material[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/* 18 */     int i = (int)paramDoubleData.getValue();
/*    */     
/* 20 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag(getNBTPath(), Integer.valueOf(i)) });
/*    */     
/* 22 */     String str = MMOItems.plugin.getLanguage().getStatFormat("max-consume").replace("{value}", String.valueOf(i));
/* 23 */     paramItemStackBuilder.getLore().insert("max-consume", new String[] { str });
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\MaxConsume.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */