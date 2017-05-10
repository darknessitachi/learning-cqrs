package ReadModel;

import Events.InventoryItemCreated;
import Events.InventoryItemDeactivated;
import Events.InventoryItemRenamed;
import FakeBus.HandleEvent;
import FakeBus.Handles;

/**
 * @author Darkness
 * @date 2017年5月10日 下午4:00:39
 * @version 1.0
 * @since 1.0
 */
public class InventoryListView {

	@HandleEvent
	public void Handle(InventoryItemCreated message) {
		BullShitDatabase.list.add(new InventoryItemListDto(message.Id, message.Name));
	}

	@HandleEvent
	public void Handle(InventoryItemRenamed message) {
		InventoryItemListDto item = BullShitDatabase.list.stream().filter((x) -> x.Id == message.Id).findFirst().get();
		item.Name = message.NewName;
	}

	@HandleEvent
	public void Handle(InventoryItemDeactivated message) {
		BullShitDatabase.list.removeIf((x) -> x.Id == message.Id);
	}
}
