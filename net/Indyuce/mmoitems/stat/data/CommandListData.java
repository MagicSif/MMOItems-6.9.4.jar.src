/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class CommandListData
/*    */   implements StatData, Mergeable, RandomStatData<CommandListData> {
/*    */   @NotNull
/*    */   private final List<CommandData> commands;
/*    */   
/*    */   public CommandListData(@NotNull List<CommandData> paramList) {
/* 18 */     this.commands = paramList;
/*    */   }
/*    */   
/*    */   public CommandListData(CommandData... paramVarArgs) {
/* 22 */     this(new ArrayList<>());
/*    */     
/* 24 */     add(paramVarArgs);
/*    */   }
/*    */   
/*    */   public void add(CommandData... paramVarArgs) {
/* 28 */     for (CommandData commandData : paramVarArgs)
/* 29 */       this.commands.add(commandData); 
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public List<CommandData> getCommands() {
/* 34 */     return this.commands;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 39 */     if (this == paramObject) return true; 
/* 40 */     if (paramObject == null || getClass() != paramObject.getClass()) return false; 
/* 41 */     CommandListData commandListData = (CommandListData)paramObject;
/* 42 */     return this.commands.equals(commandListData.commands);
/*    */   }
/*    */ 
/*    */   
/*    */   public void merge(StatData paramStatData) {
/* 47 */     Validate.isTrue(paramStatData instanceof CommandListData, "Cannot merge two different stat data types");
/* 48 */     this.commands.addAll(((CommandListData)paramStatData).commands);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public StatData cloneData() {
/* 53 */     return new CommandListData(this.commands);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 58 */     return this.commands.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandListData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 63 */     return new CommandListData(new ArrayList<>(this.commands));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\CommandListData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */