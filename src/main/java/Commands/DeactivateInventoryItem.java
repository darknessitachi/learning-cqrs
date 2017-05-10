package Commands;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:53:45
 * @version 1.0
 * @since 1.0 
 */
public class DeactivateInventoryItem extends Command {
    public  String InventoryItemId;
    public  int OriginalVersion;

    public DeactivateInventoryItem(String inventoryItemId, int originalVersion)
    {
        InventoryItemId = inventoryItemId;
        OriginalVersion = originalVersion;
    }
}
