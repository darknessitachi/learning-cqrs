package Events;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:57:03
 * @version 1.0
 * @since 1.0 
 */
public class ItemsRemovedFromInventory extends Event
{
    public String Id;
    public  int Count;

    public ItemsRemovedFromInventory(String id, int count) {
        Id = id;
        Count = count;
    }
}
