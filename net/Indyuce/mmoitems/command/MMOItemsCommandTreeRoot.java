/*    */ package net.Indyuce.mmoitems.command;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeExplorer;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import io.lumine.mythic.lib.command.api.Parameter;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.command.mmoitems.GiveAllCommandTreeNode;
/*    */ import net.Indyuce.mmoitems.command.mmoitems.ReloadCommandTreeNode;
/*    */ import net.Indyuce.mmoitems.command.mmoitems.TakeCommandTreeNode;
/*    */ import net.Indyuce.mmoitems.command.mmoitems.item.ItemCommandTreeNode;
/*    */ import net.Indyuce.mmoitems.command.mmoitems.list.ListCommandTreeNode;
/*    */ 
/*    */ public class MMOItemsCommandTreeRoot extends CommandTreeRoot {
/* 16 */   public static final Parameter TYPE = new Parameter("<type>", (paramCommandTreeExplorer, paramList) -> MMOItems.plugin.getTypes().getAll().forEach(())); public static final Parameter ID_2;
/*    */   static {
/* 18 */     ID_2 = new Parameter("<id>", (paramCommandTreeExplorer, paramList) -> {
/*    */           try {
/*    */             Type type = Type.get(paramCommandTreeExplorer.getArguments()[1]);
/*    */             MMOItems.plugin.getTemplates().getTemplates(type).forEach(());
/* 22 */           } catch (Exception exception) {}
/*    */         });
/*    */   }
/*    */   
/*    */   public MMOItemsCommandTreeRoot() {
/* 27 */     super("mmoitems", "mmoitems.admin");
/*    */     
/* 29 */     addChild((CommandTreeNode)new CreateCommandTreeNode((CommandTreeNode)this));
/* 30 */     addChild((CommandTreeNode)new DeleteCommandTreeNode((CommandTreeNode)this));
/* 31 */     addChild((CommandTreeNode)new EditCommandTreeNode((CommandTreeNode)this));
/* 32 */     addChild((CommandTreeNode)new CopyCommandTreeNode((CommandTreeNode)this));
/* 33 */     addChild((CommandTreeNode)new GiveCommandTreeNode((CommandTreeNode)this));
/* 34 */     addChild((CommandTreeNode)new TakeCommandTreeNode((CommandTreeNode)this));
/*    */     
/* 36 */     addChild((CommandTreeNode)new GenerateCommandTreeNode((CommandTreeNode)this));
/*    */     
/* 38 */     addChild((CommandTreeNode)new BrowseCommandTreeNode((CommandTreeNode)this));
/* 39 */     addChild((CommandTreeNode)new UpdateCommandTreeNode((CommandTreeNode)this));
/* 40 */     addChild((CommandTreeNode)new DebugCommandTreeNode((CommandTreeNode)this));
/* 41 */     addChild((CommandTreeNode)new ReloadCommandTreeNode((CommandTreeNode)this));
/* 42 */     addChild((CommandTreeNode)new StationsCommandTreeNode((CommandTreeNode)this));
/* 43 */     addChild((CommandTreeNode)new AllItemsCommandTreeNode((CommandTreeNode)this));
/* 44 */     addChild((CommandTreeNode)new ListCommandTreeNode((CommandTreeNode)this));
/* 45 */     addChild((CommandTreeNode)new DropCommandTreeNode((CommandTreeNode)this));
/* 46 */     addChild((CommandTreeNode)new AbilityCommandTreeNode((CommandTreeNode)this));
/* 47 */     addChild((CommandTreeNode)new GiveAllCommandTreeNode((CommandTreeNode)this));
/* 48 */     addChild((CommandTreeNode)new ItemListCommandTreeNode((CommandTreeNode)this));
/* 49 */     addChild((CommandTreeNode)new RevisionIDCommandTreeNode((CommandTreeNode)this));
/*    */     
/* 51 */     addChild((CommandTreeNode)new ItemCommandTreeNode((CommandTreeNode)this));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\MMOItemsCommandTreeRoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */