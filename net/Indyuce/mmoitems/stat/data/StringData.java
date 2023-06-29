/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class StringData implements StatData, RandomStatData<StringData>, Mergeable<StringData> {
/*    */   @Nullable
/*    */   private String value;
/*    */   
/*    */   public StringData(@Nullable String paramString) {
/* 15 */     this.value = paramString;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getString() {
/* 20 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setString(@Nullable String paramString) {
/* 24 */     this.value = paramString;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 29 */     return String.valueOf(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public StringData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 34 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 39 */     return (this.value == null || this.value.isEmpty());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void merge(@Nullable StringData paramStringData) {
/* 46 */     this.value = paramStringData.getString();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public StringData cloneData() {
/* 52 */     return new StringData(this.value);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\StringData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */