package Commands;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:53:58
 * @version 1.0
 * @since 1.0 
 */
public class CreateInventoryItem extends Command {
    public  String InventoryItemId;
    public  String Name;

    public CreateInventoryItem(String inventoryItemId, String name)
    {
        InventoryItemId = inventoryItemId;
        Name = name;
    }
}
