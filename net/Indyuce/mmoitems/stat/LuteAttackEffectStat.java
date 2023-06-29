/*    */ package net.Indyuce.mmoitems.stat;
/*    */ import io.lumine.mythic.lib.UtilityMethods;
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import java.util.Objects;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.lute.BruteLuteAttack;
/*    */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.lute.LuteAttackHandler;
/*    */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.lute.SimpleLuteAttack;
/*    */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.lute.SlashLuteAttack;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.stat.data.StringData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import net.Indyuce.mmoitems.stat.type.StringStat;
/*    */ import org.bukkit.event.inventory.InventoryAction;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class LuteAttackEffectStat extends StringStat implements GemStoneStat {
/*    */   public LuteAttackEffectStat() {
/* 25 */     super("LUTE_ATTACK_EFFECT", VersionMaterial.DIAMOND_HORSE_ARMOR.toMaterial(), "Lute Attack Effect", new String[] { "Changes how your lute behaves", "when right clicked.", "&9Tip: /mi list lute" }, new String[] { "lute" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/* 31 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/* 32 */       paramEditionInventory.getEditedSection().set("lute-attack-effect", null);
/* 33 */       paramEditionInventory.registerTemplateEdition();
/* 34 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed the lute attack effect.");
/*    */     } else {
/* 36 */       (new StatEdition(paramEditionInventory, (ItemStat)this, new Object[0])).enable(new String[] { "Write in the chat the text you want." });
/*    */     } 
/*    */   }
/*    */   
/*    */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 41 */     LuteAttackEffect luteAttackEffect = LuteAttackEffect.valueOf(paramString.toUpperCase().replace(" ", "_").replace("-", "_"));
/* 42 */     paramEditionInventory.getEditedSection().set("lute-attack-effect", luteAttackEffect.name());
/* 43 */     paramEditionInventory.registerTemplateEdition();
/* 44 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Lute attack effect successfully changed to " + luteAttackEffect.getName() + ".");
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringData paramStringData) {
/* 49 */     LuteAttackEffect luteAttackEffect = LuteAttackEffect.valueOf(paramStringData.toString().toUpperCase().replace(" ", "_").replace("-", "_"));
/* 50 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag("MMOITEMS_LUTE_ATTACK_EFFECT", luteAttackEffect.name()) });
/* 51 */     paramItemStackBuilder.getLore().insert("lute-attack-effect", new String[] { luteAttackEffect.getName() });
/*    */   }
/*    */   
/*    */   public enum LuteAttackEffect {
/* 55 */     WAVE((String)new WaveLuteAttack()),
/* 56 */     CIRCULAR((String)new CircularLuteAttack()),
/* 57 */     SIMPLE((String)new SimpleLuteAttack()),
/* 58 */     BRUTE((String)new BruteLuteAttack()),
/* 59 */     SLASH((String)new SlashLuteAttack());
/*    */ 
/*    */ 
/*    */     
/*    */     @NotNull
/* 64 */     private String name = UtilityMethods.caseOnWords(name().toLowerCase().replace("_", " ")); private final LuteAttackHandler handler;
/*    */     
/*    */     LuteAttackEffect(LuteAttackHandler param1LuteAttackHandler) {
/* 67 */       this.handler = param1LuteAttackHandler;
/*    */     }
/*    */     
/*    */     public LuteAttackHandler getAttack() {
/* 71 */       return this.handler;
/*    */     }
/*    */     
/*    */     @Deprecated
/*    */     public String getDefaultName() {
/* 76 */       return name().charAt(0) + name().substring(1).toLowerCase();
/*    */     }
/*    */     
/*    */     public String getName() {
/* 80 */       return this.name;
/*    */     }
/*    */     
/*    */     public void setName(@NotNull String param1String) {
/* 84 */       this.name = Objects.<String>requireNonNull(param1String);
/*    */     }
/*    */     
/*    */     public static LuteAttackEffect get(NBTItem param1NBTItem) {
/*    */       try {
/* 89 */         return valueOf(param1NBTItem.getString("MMOITEMS_LUTE_ATTACK_EFFECT"));
/* 90 */       } catch (IllegalArgumentException illegalArgumentException) {
/* 91 */         return null;
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\LuteAttackEffectStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */