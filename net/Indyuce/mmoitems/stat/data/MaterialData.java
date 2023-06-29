/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MaterialData
/*    */   implements StatData, RandomStatData<MaterialData>
/*    */ {
/*    */   private Material material;
/*    */   
/*    */   public MaterialData(Material paramMaterial) {
/* 18 */     Validate.notNull(paramMaterial, "Material must not be null");
/* 19 */     this.material = paramMaterial;
/*    */   }
/*    */   
/*    */   public void setMaterial(Material paramMaterial) {
/* 23 */     Validate.notNull(paramMaterial, "Material must not be null");
/* 24 */     this.material = paramMaterial;
/*    */   }
/*    */   
/*    */   public Material getMaterial() {
/* 28 */     return this.material;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 33 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public MaterialData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 38 */     return this;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\MaterialData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */