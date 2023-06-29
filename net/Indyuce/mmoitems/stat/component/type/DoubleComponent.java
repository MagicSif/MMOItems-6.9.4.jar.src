/*    */ package net.Indyuce.mmoitems.stat.component.type;
/*    */ 
/*    */ import net.Indyuce.mmoitems.stat.component.Mergeable;
/*    */ import net.Indyuce.mmoitems.stat.component.StatComponent;
/*    */ 
/*    */ public class DoubleComponent extends StatComponent implements Mergeable<DoubleComponent> {
/*    */   private double value;
/*    */   
/*    */   public DoubleComponent(String paramString) {
/* 10 */     super(paramString);
/*    */   }
/*    */ 
/*    */   
/*    */   public void merge(DoubleComponent paramDoubleComponent) {
/* 15 */     this.value += paramDoubleComponent.value;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\component\type\DoubleComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */