/*    */ package net.Indyuce.mmoitems.api.item.template.explorer;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Optional;
/*    */ import java.util.Random;
/*    */ import java.util.function.Predicate;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TemplateExplorer
/*    */ {
/* 22 */   private final Random random = new Random();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   private final Collection<MMOItemTemplate> all = MMOItems.plugin.getTemplates().collectTemplates();
/*    */   
/*    */   public int count() {
/* 31 */     return this.all.size();
/*    */   }
/*    */   
/*    */   public TemplateExplorer applyFilter(Predicate<MMOItemTemplate> paramPredicate) {
/* 35 */     this.all.removeIf(not(paramPredicate));
/* 36 */     return this;
/*    */   }
/*    */   
/*    */   public Optional<MMOItemTemplate> rollLoot() {
/* 40 */     switch (count()) {
/*    */       case 0:
/* 42 */         return Optional.empty();
/*    */       case 1:
/* 44 */         return this.all.stream().findFirst();
/*    */     } 
/* 46 */     return this.all.stream().skip(this.random.nextInt(count())).findFirst();
/*    */   }
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
/*    */   public Optional<MMOItem> rollItem(RPGPlayer paramRPGPlayer) {
/* 59 */     return rollLoot().map(paramMMOItemTemplate -> paramMMOItemTemplate.newBuilder(paramRPGPlayer).build());
/*    */   }
/*    */   
/*    */   private <T> Predicate<T> not(Predicate<T> paramPredicate) {
/* 63 */     return paramObject -> !paramPredicate.test(paramObject);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\template\explorer\TemplateExplorer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */