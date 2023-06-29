/*    */ package net.Indyuce.mmoitems.util;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class Pair<K, V>
/*    */ {
/*    */   private final K key;
/*    */   private final V value;
/*    */   
/*    */   public Pair(@NotNull K paramK, @NotNull V paramV) {
/* 12 */     this.key = paramK;
/* 13 */     this.value = paramV;
/*    */   }
/*    */   
/*    */   public static <K, V> Pair<K, V> of(@NotNull K paramK, @NotNull V paramV) {
/* 17 */     return new Pair<>(paramK, paramV);
/*    */   }
/*    */   
/*    */   public K getKey() {
/* 21 */     return this.key;
/*    */   }
/*    */   
/*    */   public V getValue() {
/* 25 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 30 */     if (this == paramObject) return true; 
/* 31 */     if (paramObject == null || getClass() != paramObject.getClass()) return false; 
/* 32 */     Pair pair = (Pair)paramObject;
/* 33 */     return (this.key.equals(pair.key) && this.value.equals(pair.value));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 38 */     return Objects.hash(new Object[] { this.key, this.value });
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitem\\util\Pair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */