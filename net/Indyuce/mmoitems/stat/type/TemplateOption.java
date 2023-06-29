/*    */ package net.Indyuce.mmoitems.stat.type;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import java.util.ArrayList;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*    */ import net.Indyuce.mmoitems.stat.data.StringData;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface TemplateOption
/*    */ {
/*    */   default void whenLoaded(@NotNull ReadMMOItem mmoitem) {}
/*    */   
/*    */   @Nullable
/*    */   default StringData getLoadedNBT(@NotNull ArrayList<ItemTag> storedTags) {
/* 33 */     throw new RuntimeException("Not supported");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void whenApplied(@NotNull ItemStackBuilder item, @NotNull StringData data) {
/* 40 */     throw new RuntimeException("Not supported");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   default ArrayList<ItemTag> getAppliedNBT(@NotNull StringData data) {
/* 48 */     throw new RuntimeException("Not supported");
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\TemplateOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */