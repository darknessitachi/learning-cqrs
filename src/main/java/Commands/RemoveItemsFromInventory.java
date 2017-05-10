package Commands;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:54:35
 * @version 1.0
 * @since 1.0 
 */
public class RemoveItemsFromInventory extends Command {
    public String InventoryItemId;
    public  int Count;
    public  int OriginalVersion;

    public RemoveItemsFromInventory(String inventoryItemId, int count, int originalVersion)
    {
        InventoryItemId = inventoryItemId;
        Count = count;
        OriginalVersion = originalVersion;
    }
}
