/*    */ package net.Indyuce.mmoitems.stat.component.type;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.function.Consumer;
/*    */ import net.Indyuce.mmoitems.stat.component.StatComponent;
/*    */ import net.Indyuce.mmoitems.stat.component.Upgradable;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class ObjectComponent
/*    */   extends AbstractObjectComponent implements Upgradable {
/* 13 */   private final Map<String, StatComponent> components = new HashMap<>();
/*    */   
/*    */   public ObjectComponent(String paramString) {
/* 16 */     super(paramString);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public StatComponent getComponent(String paramString) {
/* 22 */     return this.components.get(paramString);
/*    */   }
/*    */   
/*    */   public void forEachComponent(Consumer<StatComponent> paramConsumer) {
/* 26 */     for (StatComponent statComponent : this.components.values())
/* 27 */       paramConsumer.accept(statComponent); 
/*    */   }
/*    */   
/*    */   public Set<String> getComponentKeys() {
/* 31 */     return this.components.keySet();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\component\type\ObjectComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */