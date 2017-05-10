package ReadModel;

import java.util.List;

/**
 * @author Darkness
 * @date 2017年5月10日 下午4:01:06
 * @version 1.0
 * @since 1.0
 */
public class ReadModelFacade implements IReadModelFacade {
	public List<InventoryItemListDto> GetInventoryItems() {
		return BullShitDatabase.list;
	}

	public InventoryItemDetailsDto GetInventoryItemDetails(String id) {
		return BullShitDatabase.details.get(id);
	}
}