package Events;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:56:42
 * @version 1.0
 * @since 1.0 
 */
public class InventoryItemRenamed extends Event {
    public  String Id;
    public  String NewName;

    public InventoryItemRenamed(String id, String newName)
    {
        Id = id;
        NewName = newName;
    }
}
