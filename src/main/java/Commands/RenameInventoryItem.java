package Commands;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:54:11
 * @version 1.0
 * @since 1.0 
 */
public class RenameInventoryItem extends Command {
    public  String InventoryItemId;
    public  String NewName;
    public  int OriginalVersion;

    public RenameInventoryItem(String inventoryItemId, String newName, int originalVersion)
    {
        InventoryItemId = inventoryItemId;
        NewName = newName;
        OriginalVersion = originalVersion;
    }
}
