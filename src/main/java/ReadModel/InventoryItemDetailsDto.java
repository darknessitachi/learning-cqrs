package ReadModel;

/**
 * @author Darkness
 * @date 2017年5月10日 下午4:00:14
 * @version 1.0
 * @since 1.0 
 */
public class InventoryItemDetailsDto
{
    public String Id;
    public String Name;
    public int CurrentCount;
    public int Version;

    public InventoryItemDetailsDto(String id, String name, int currentCount, int version)
    {
        Id = id;
        Name = name;
        CurrentCount = currentCount;
        Version = version;
    }
}
