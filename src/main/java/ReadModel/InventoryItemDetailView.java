package ReadModel;

import Events.InventoryItemCreated;
import Events.InventoryItemDeactivated;
import Events.InventoryItemRenamed;
import Events.ItemsCheckedInToInventory;
import Events.ItemsRemovedFromInventory;
import FakeBus.HandleEvent;

/**
 * @author Darkness
 * @date 2017年5月10日 下午4:00:52
 * @version 1.0
 * @since 1.0
 */
public class InventoryItemDetailView {

	@HandleEvent
	public void Handle(InventoryItemCreated message) {
		BullShitDatabase.details.put(message.Id, new InventoryItemDetailsDto(message.Id, message.Name, 0, 0));
	}

	@HandleEvent
	public void Handle(InventoryItemRenamed message) {
		InventoryItemDetailsDto d = GetDetailsItem(message.Id);
		d.Name = message.NewName;
		d.Version = message.Version;
	}

	@HandleEvent
	public void Handle(ItemsRemovedFromInventory message) {
		InventoryItemDetailsDto d = GetDetailsItem(message.Id);
		d.CurrentCount -= message.Count;
		d.Version = message.Version;
	}

	@HandleEvent
	public void Handle(ItemsCheckedInToInventory message) {
		InventoryItemDetailsDto d = GetDetailsItem(message.Id);
		d.CurrentCount += message.Count;
		d.Version = message.Version;
	}

	@HandleEvent
	public void Handle(InventoryItemDeactivated message) {
		BullShitDatabase.details.remove(message.Id);
	}

	private InventoryItemDetailsDto GetDetailsItem(String id) {
		InventoryItemDetailsDto d = BullShitDatabase.details.get(id);

		if (d == null) {
			throw new RuntimeException("did not find the original inventory this shouldnt happen");
		}

		return d;
	}
}
