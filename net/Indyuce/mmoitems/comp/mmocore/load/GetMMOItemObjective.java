/*    */ package net.Indyuce.mmoitems.comp.mmocore.load;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmocore.api.quest.ObjectiveProgress;
/*    */ import net.Indyuce.mmocore.api.quest.QuestProgress;
/*    */ import net.Indyuce.mmocore.api.quest.objective.Objective;
/*    */ import net.Indyuce.mmocore.comp.citizens.CitizenInteractEvent;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ public class GetMMOItemObjective extends Objective {
/*    */   private final Type type;
/*    */   private final String id;
/*    */   private final int required;
/*    */   private final int npcId;
/*    */   
/*    */   public GetMMOItemObjective(ConfigurationSection paramConfigurationSection, MMOLineConfig paramMMOLineConfig) {
/* 24 */     super(paramConfigurationSection);
/*    */     
/* 26 */     paramMMOLineConfig.validate(new String[] { "type", "id", "npc" });
/*    */     
/* 28 */     String str = paramMMOLineConfig.getString("type").toUpperCase().replace("-", "_").replace(" ", "_");
/* 29 */     Validate.isTrue(MMOItems.plugin.getTypes().has(str), "Could not find item type " + str);
/* 30 */     this.type = MMOItems.plugin.getTypes().get(str);
/*    */     
/* 32 */     this.id = paramMMOLineConfig.getString("id");
/* 33 */     this.required = paramMMOLineConfig.contains("amount") ? Math.max(paramMMOLineConfig.getInt("amount"), 1) : 1;
/* 34 */     this.npcId = paramMMOLineConfig.getInt("npc");
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectiveProgress newProgress(QuestProgress paramQuestProgress) {
/* 39 */     return new GotoProgress(paramQuestProgress, this);
/*    */   }
/*    */   
/*    */   public class GotoProgress extends ObjectiveProgress implements Listener {
/*    */     public GotoProgress(QuestProgress param1QuestProgress, Objective param1Objective) {
/* 44 */       super(param1QuestProgress, param1Objective);
/*    */     }
/*    */     
/*    */     @EventHandler
/*    */     public void a(CitizenInteractEvent param1CitizenInteractEvent) {
/* 49 */       Player player = param1CitizenInteractEvent.getPlayer();
/* 50 */       if (!getQuestProgress().getPlayer().isOnline())
/* 51 */         return;  if (player.equals(getQuestProgress().getPlayer().getPlayer()) && param1CitizenInteractEvent.getNPC().getId() == GetMMOItemObjective.this.npcId && player.getInventory().getItemInMainHand() != null) {
/* 52 */         NBTItem nBTItem = NBTItem.get(player.getInventory().getItemInMainHand());
/*    */         int i;
/* 54 */         if (nBTItem.getString("MMOITEMS_ITEM_TYPE").equals(GetMMOItemObjective.this.type.getId()) && nBTItem.getString("MMOITEMS_ITEM_ID").equals(GetMMOItemObjective.this.id) && (i = player.getInventory().getItemInMainHand().getAmount()) >= GetMMOItemObjective.this.required) {
/* 55 */           if (i <= GetMMOItemObjective.this.required) {
/* 56 */             player.getInventory().setItemInMainHand(null);
/*    */           } else {
/* 58 */             player.getInventory().getItemInMainHand().setAmount(i - GetMMOItemObjective.this.required);
/* 59 */           }  getQuestProgress().completeObjective();
/*    */         } 
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/*    */     public String formatLore(String param1String) {
/* 66 */       return param1String;
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\load\GetMMOItemObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */