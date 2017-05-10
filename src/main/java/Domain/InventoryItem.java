package Domain;

import Events.InventoryItemCreated;
import Events.InventoryItemDeactivated;
import Events.InventoryItemRenamed;
import Events.ItemsCheckedInToInventory;
import Events.ItemsRemovedFromInventory;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:55:02
 * @version 1.0
 * @since 1.0 
 */
public class InventoryItem extends AggregateRoot
{
    private boolean _activated;
    private String _id;

    private void Apply(InventoryItemCreated e)
    {
        _id = e.Id;
        _activated = true;
    }

    private void Apply(InventoryItemDeactivated e)
    {
        _activated = false;
    }

    public void ChangeName(String newName)
    {
        if (newName == null || "".equals(newName.trim())) throw new RuntimeException("newName");
        ApplyChange(new InventoryItemRenamed(_id, newName));
    }

    public void Remove(int count)
    {
        if (count <= 0) throw new RuntimeException("cant remove negative count from inventory");
        ApplyChange(new ItemsRemovedFromInventory(_id, count));
    }


    public void CheckIn(int count)
    {
        if(count <= 0) throw new RuntimeException("must have a count greater than 0 to add to inventory");
        ApplyChange(new ItemsCheckedInToInventory(_id, count));
    }

    public void Deactivate()
    {
        if(!_activated) throw new RuntimeException("already deactivated");
        ApplyChange(new InventoryItemDeactivated(_id));
    }

    public  String getId()
    {
         return _id; 
    }

    public InventoryItem()
    {
        // used to create in repository ... many ways to avoid this, eg making private constructor
    }

    public InventoryItem(String id, String name)
    {
        ApplyChange(new InventoryItemCreated(id, name));
    }
}
