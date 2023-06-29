/*    */ package net.Indyuce.mmoitems.stat.component.type;
/*    */ 
/*    */ import net.Indyuce.mmoitems.stat.component.StatComponent;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public abstract class AbstractObjectComponent extends StatComponent {
/*    */   public AbstractObjectComponent(String paramString) {
/*  8 */     super(paramString);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public StatComponent findComponent(String paramString) {
/* 13 */     String[] arrayOfString = paramString.split("\\.");
/*    */     
/* 15 */     AbstractObjectComponent abstractObjectComponent = this;
/* 16 */     int i = arrayOfString.length - 1;
/* 17 */     for (byte b = 0; b < i; b++) {
/* 18 */       StatComponent statComponent = getComponent(arrayOfString[b]);
/* 19 */       if (statComponent == null || !(statComponent instanceof AbstractObjectComponent)) {
/* 20 */         return null;
/*    */       }
/* 22 */       abstractObjectComponent = (AbstractObjectComponent)statComponent;
/*    */     } 
/*    */     
/* 25 */     return abstractObjectComponent.getComponent(arrayOfString[i]);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public abstract StatComponent getComponent(String paramString);
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\component\type\AbstractObjectComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */