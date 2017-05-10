package Events;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:56:22
 * @version 1.0
 * @since 1.0 
 */
public class InventoryItemDeactivated extends Event {
    public  String Id;

    public InventoryItemDeactivated(String id)
    {
        Id = id;
    }
}
