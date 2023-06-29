/*    */ package net.Indyuce.mmoitems.stat;
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.staff.NetherSpirit;
/*    */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.staff.StaffAttackHandler;
/*    */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.staff.VoidSpirit;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.stat.data.StringData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.StringStat;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class StaffSpiritStat extends StringStat {
/*    */   public StaffSpiritStat() {
/* 19 */     super("STAFF_SPIRIT", VersionMaterial.BONE_MEAL.toMaterial(), "Staff Spirit", new String[] { "Spirit changes the texture", "of the magic attack.", "&9Tip: /mi list spirit" }, new String[] { "staff", "wand" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*    */     try {
/* 26 */       StaffSpirit staffSpirit = StaffSpirit.valueOf(paramString.toUpperCase().replace(" ", "_").replace("-", "_"));
/* 27 */       paramEditionInventory.getEditedSection().set("staff-spirit", staffSpirit.name());
/* 28 */       paramEditionInventory.registerTemplateEdition();
/* 29 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Staff Spirit successfully changed to " + staffSpirit.getName() + ".");
/* 30 */     } catch (IllegalArgumentException illegalArgumentException) {
/* 31 */       throw new IllegalArgumentException(illegalArgumentException.getMessage() + " (See all Staff Spirits here: /mi list spirit).");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringData paramStringData) {
/* 37 */     StaffSpirit staffSpirit = StaffSpirit.valueOf(paramStringData.toString().toUpperCase().replace(" ", "_").replace("-", "_"));
/* 38 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag("MMOITEMS_STAFF_SPIRIT", staffSpirit.name()) });
/* 39 */     paramItemStackBuilder.getLore().insert("staff-spirit", new String[] { staffSpirit.getName() });
/*    */   }
/*    */   
/*    */   public enum StaffSpirit {
/* 43 */     NETHER_SPIRIT("Shoots fire beams.", new NetherSpirit()),
/* 44 */     VOID_SPIRIT("Shoots shulker missiles.", new VoidSpirit()),
/* 45 */     MANA_SPIRIT("Summons mana bolts.", new ManaSpirit()),
/* 46 */     LIGHTNING_SPIRIT("Summons lightning bolts.", new LightningSpirit()),
/* 47 */     XRAY_SPIRIT("Fires piercing & powerful X-rays.", new XRaySpirit()),
/* 48 */     THUNDER_SPIRIT("Fires AoE damaging thunder strikes.", new ThunderSpirit()),
/* 49 */     SUNFIRE_SPIRIT("Fires AoE damaging fire comets.", new SunfireSpirit());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     @NotNull
/* 58 */     private String name = UtilityMethods.caseOnWords(name().toLowerCase().replace("_", " ")); private final String defaultLore; private final StaffAttackHandler handler;
/*    */     
/*    */     StaffSpirit(String param1String1, StaffAttackHandler param1StaffAttackHandler) {
/* 61 */       this.defaultLore = param1String1;
/* 62 */       this.handler = param1StaffAttackHandler;
/*    */     }
/*    */     
/*    */     @Nullable
/*    */     public static StaffSpirit get(NBTItem param1NBTItem) {
/*    */       try {
/* 68 */         return valueOf(param1NBTItem.getString("MMOITEMS_STAFF_SPIRIT"));
/* 69 */       } catch (Exception exception) {
/* 70 */         return null;
/*    */       } 
/*    */     }
/*    */     
/*    */     public String getName() {
/* 75 */       return this.name;
/*    */     }
/*    */     
/*    */     public boolean hasLore() {
/* 79 */       return (this.defaultLore != null);
/*    */     }
/*    */     
/*    */     public String getDefaultLore() {
/* 83 */       return this.defaultLore;
/*    */     }
/*    */     
/*    */     public StaffAttackHandler getAttack() {
/* 87 */       return this.handler;
/*    */     }
/*    */     
/*    */     public void setName(String param1String) {
/* 91 */       this.name = param1String;
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\StaffSpiritStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */