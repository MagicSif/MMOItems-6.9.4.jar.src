package net.Indyuce.mmoitems.api.edition;

import net.Indyuce.mmoitems.gui.PluginInventory;

public interface Edition {
  boolean processInput(String paramString);
  
  PluginInventory getInventory();
  
  void enable(String... paramVarArgs);
  
  boolean shouldGoBack();
}


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\edition\Edition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */