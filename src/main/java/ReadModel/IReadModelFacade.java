package ReadModel;

import java.util.List;

/**
 * @author Darkness
 * @date 2017年5月10日 下午4:00:00
 * @version 1.0
 * @since 1.0
 */
public interface IReadModelFacade {
	List<InventoryItemListDto> GetInventoryItems();

	InventoryItemDetailsDto GetInventoryItemDetails(String id);
}
