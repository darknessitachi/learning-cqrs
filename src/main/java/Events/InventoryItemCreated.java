package Events;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:56:32
 * @version 1.0
 * @since 1.0 
 */
public class InventoryItemCreated extends Event {
    public  String Id;
    public  String Name;
    public InventoryItemCreated(String id, String name) {
        Id = id;
        Name = name;
    }
}
