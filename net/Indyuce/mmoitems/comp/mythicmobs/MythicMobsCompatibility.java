/*    */ package net.Indyuce.mmoitems.comp.mythicmobs;
/*    */ 
/*    */ import io.lumine.mythic.api.mobs.MythicMob;
/*    */ import io.lumine.mythic.api.skills.ISkillMechanic;
/*    */ import io.lumine.mythic.bukkit.MythicBukkit;
/*    */ import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
/*    */ import io.lumine.mythic.bukkit.events.MythicReloadedEvent;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.comp.mythicmobs.mechanics.MMOItemsArrowVolleyMechanic;
/*    */ import net.Indyuce.mmoitems.comp.mythicmobs.mechanics.MMOItemsOnShootAura;
/*    */ import net.Indyuce.mmoitems.comp.mythicmobs.stat.FactionDamage;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class MythicMobsCompatibility
/*    */   implements Listener {
/*    */   public MythicMobsCompatibility() {
/*    */     try {
/* 29 */       for (String str : getFactions())
/* 30 */         MMOItems.plugin.getStats().register((ItemStat)new FactionDamage(str)); 
/* 31 */     } catch (NullPointerException nullPointerException) {}
/*    */ 
/*    */     
/* 34 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)MMOItems.plugin);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGH)
/*    */   public void b(MythicMechanicLoadEvent paramMythicMechanicLoadEvent) {
/* 42 */     switch (paramMythicMechanicLoadEvent.getMechanicName().toLowerCase()) {
/*    */       case "mmoitemsvolley":
/* 44 */         paramMythicMechanicLoadEvent.register((ISkillMechanic)new MMOItemsArrowVolleyMechanic(paramMythicMechanicLoadEvent.getContainer().getManager(), paramMythicMechanicLoadEvent.getContainer().getConfigLine(), paramMythicMechanicLoadEvent.getConfig()));
/*    */         break;
/*    */       case "onmmoitemuse":
/* 47 */         paramMythicMechanicLoadEvent.register((ISkillMechanic)new MMOItemsOnShootAura(paramMythicMechanicLoadEvent.getContainer().getManager(), paramMythicMechanicLoadEvent.getContainer().getConfigLine(), paramMythicMechanicLoadEvent.getConfig()));
/*    */         break;
/*    */     } 
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
/*    */   @EventHandler(priority = EventPriority.HIGH)
/*    */   public void a(MythicReloadedEvent paramMythicReloadedEvent) {
/* 62 */     MMOItems.plugin.getSkills().initialize(true);
/*    */ 
/*    */     
/* 65 */     for (Player player : Bukkit.getOnlinePlayers()) {
/* 66 */       PlayerData playerData = PlayerData.get((OfflinePlayer)player);
/* 67 */       playerData.updateInventory();
/*    */     } 
/*    */   }
/*    */   
/*    */   private Set<String> getFactions() {
/* 72 */     HashSet<String> hashSet = new HashSet();
/*    */ 
/*    */     
/* 75 */     ArrayList arrayList = new ArrayList(MythicBukkit.inst().getMobManager().getVanillaTypes());
/* 76 */     arrayList.addAll(MythicBukkit.inst().getMobManager().getMobTypes());
/*    */ 
/*    */     
/* 79 */     for (MythicMob mythicMob : arrayList) {
/*    */       
/* 81 */       if (mythicMob.hasFaction())
/* 82 */         hashSet.add(mythicMob.getFaction()); 
/*    */     } 
/* 84 */     return hashSet;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mythicmobs\MythicMobsCompatibility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */