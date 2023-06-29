/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class StringListData
/*    */   implements StatData, RandomStatData<StringListData>, Mergeable<StringListData>
/*    */ {
/*    */   @NotNull
/*    */   private final List<String> list;
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 23 */     if (!(paramObject instanceof StringListData)) return false; 
/* 24 */     if (((StringListData)paramObject).getList().size() != getList().size()) return false; 
/* 25 */     return SilentNumbers.hasAll(((StringListData)paramObject).getList(), getList());
/*    */   }
/*    */   
/*    */   public StringListData() {
/* 29 */     this(new ArrayList<>());
/*    */   }
/*    */   
/*    */   public StringListData(@NotNull String[] paramArrayOfString) {
/* 33 */     this(Arrays.asList(paramArrayOfString));
/*    */   }
/*    */   
/*    */   public StringListData(@NotNull JsonArray paramJsonArray) {
/* 37 */     this();
/*    */     
/* 39 */     paramJsonArray.forEach(paramJsonElement -> this.list.add(paramJsonElement.getAsString()));
/*    */   }
/*    */   
/*    */   public StringListData(@NotNull List<String> paramList) {
/* 43 */     this.list = paramList;
/*    */   }
/*    */   @NotNull
/*    */   public List<String> getList() {
/* 47 */     return this.list;
/*    */   }
/*    */ 
/*    */   
/*    */   public StringListData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 52 */     return new StringListData(new ArrayList<>(this.list));
/*    */   }
/*    */ 
/*    */   
/*    */   public void merge(StringListData paramStringListData) {
/* 57 */     this.list.addAll(paramStringListData.list);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public StringListData cloneData() {
/* 62 */     return new StringListData(new ArrayList<>(getList()));
/*    */   }
/*    */   public boolean isEmpty() {
/* 65 */     return this.list.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     StringBuilder stringBuilder = new StringBuilder("§7");
/* 71 */     for (String str : getList()) {
/* 72 */       if (stringBuilder.length() > 0) stringBuilder.append("§8;§7 "); 
/* 73 */       stringBuilder.append(str);
/*    */     } 
/*    */     
/* 76 */     return stringBuilder.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public boolean remove(@Nullable String paramString) {
/* 87 */     return this.list.remove(paramString);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\StringListData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */