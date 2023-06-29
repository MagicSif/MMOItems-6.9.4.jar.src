/*    */ package net.Indyuce.mmoitems.comp.mmocore.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import net.Indyuce.mmocore.api.player.attribute.PlayerAttribute;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import net.Indyuce.mmoitems.api.util.message.Message;
/*    */ import net.Indyuce.mmoitems.comp.mmocore.MMOCoreHook;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemRestriction;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Sound;
/*    */ 
/*    */ public class RequiredAttribute
/*    */   extends DoubleStat implements ItemRestriction, GemStoneStat {
/*    */   private final PlayerAttribute attribute;
/*    */   
/*    */   public RequiredAttribute(PlayerAttribute paramPlayerAttribute) {
/* 20 */     super("REQUIRED_" + paramPlayerAttribute.getId().toUpperCase().replace("-", "_"), VersionMaterial.GRAY_DYE.toMaterial(), paramPlayerAttribute.getName() + " Requirement (MMOCore)", new String[] { "Amount of " + paramPlayerAttribute.getName() + " points the", "player needs to use the item." }, new String[] { "!block", "all" }, new org.bukkit.Material[0]);
/*    */     
/* 22 */     this.attribute = paramPlayerAttribute;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse(RPGPlayer paramRPGPlayer, NBTItem paramNBTItem, boolean paramBoolean) {
/* 27 */     MMOCoreHook.MMOCoreRPGPlayer mMOCoreRPGPlayer = (MMOCoreHook.MMOCoreRPGPlayer)paramRPGPlayer;
/* 28 */     if (mMOCoreRPGPlayer.getData().getAttributes().getAttribute(this.attribute) < paramNBTItem.getStat(getId())) {
/* 29 */       if (paramBoolean) {
/* 30 */         Message.NOT_ENOUGH_ATTRIBUTE.format(ChatColor.RED, new String[] { "#attribute#", this.attribute.getName() }).send(paramRPGPlayer.getPlayer());
/* 31 */         paramRPGPlayer.getPlayer().playSound(paramRPGPlayer.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.5F);
/*    */       } 
/* 33 */       return false;
/*    */     } 
/* 35 */     return true;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\stat\RequiredAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */