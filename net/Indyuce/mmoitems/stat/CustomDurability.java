/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.stat.type.InternalStat;
/*    */ import org.bukkit.Material;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public class CustomDurability
/*    */   extends DoubleStat
/*    */   implements InternalStat
/*    */ {
/*    */   public CustomDurability() {
/* 26 */     super("DURABILITY", Material.SHEARS, "Custom Durability", new String[0], new String[] { "!block", "all" }, new Material[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/* 31 */     int i = (int)paramDoubleData.getValue();
/* 32 */     if (i != 0)
/*    */     {
/*    */       
/* 35 */       paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag(getNBTPath(), Integer.valueOf(i)) });
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\CustomDurability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */