package esp.lbelmar.customfactions.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class CheckFactionChest implements Listener {

    @EventHandler
    public void catchChestOpen(InventoryOpenEvent event)
    {

        if(event.getInventory().getType().equals(InventoryType.CHEST))
        {

            //Do Stuff

        }

    }
}
