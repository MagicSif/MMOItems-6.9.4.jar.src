package net.Indyuce.mmoitems.gui.edition.recipe.interpreter;

import io.lumine.mythic.lib.api.crafting.uimanager.ProvidedUIFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RMG_RecipeInterpreter {
  void editInput(@NotNull ProvidedUIFilter paramProvidedUIFilter, int paramInt);
  
  void editOutput(@NotNull ProvidedUIFilter paramProvidedUIFilter, int paramInt);
  
  void deleteInput(int paramInt);
  
  void deleteOutput(int paramInt);
  
  @Nullable
  ProvidedUIFilter getInput(int paramInt);
  
  @Nullable
  ProvidedUIFilter getOutput(int paramInt);
}


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\interpreter\RMG_RecipeInterpreter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */