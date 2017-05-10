package Events;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:56:53
 * @version 1.0
 * @since 1.0 
 */
public class ItemsCheckedInToInventory extends Event
{
    public String Id;
    public  int Count;

    public ItemsCheckedInToInventory(String id, int count) {
        Id = id;
        Count = count;
    }
}
