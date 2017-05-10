package simplecqrs;

import Commands.CheckInItemsToInventory;
import Commands.CreateInventoryItem;
import Commands.DeactivateInventoryItem;
import Commands.RemoveItemsFromInventory;
import Commands.RenameInventoryItem;
import Domain.IRepository;
import Domain.InventoryItem;
import FakeBus.HandleEvent;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:52:35
 * @version 1.0
 * @since 1.0
 */
public class InventoryCommandHandlers {
	private IRepository<InventoryItem> _repository;

	public InventoryCommandHandlers(IRepository<InventoryItem> repository) {
		_repository = repository;
	}

	@HandleEvent
	public void Handle(CreateInventoryItem message) {
		InventoryItem item = new InventoryItem(message.InventoryItemId, message.Name);
		_repository.Save(item, -1);
	}

	@HandleEvent
	public void Handle(DeactivateInventoryItem message) {
		InventoryItem item = _repository.GetById(message.InventoryItemId);
		item.Deactivate();
		_repository.Save(item, message.OriginalVersion);
	}

	@HandleEvent
	public void Handle(RemoveItemsFromInventory message) {
		InventoryItem item = _repository.GetById(message.InventoryItemId);
		item.Remove(message.Count);
		_repository.Save(item, message.OriginalVersion);
	}

	@HandleEvent
	public void Handle(CheckInItemsToInventory message) {
		InventoryItem item = _repository.GetById(message.InventoryItemId);
		item.CheckIn(message.Count);
		_repository.Save(item, message.OriginalVersion);
	}

	@HandleEvent
	public void Handle(RenameInventoryItem message) {
		InventoryItem item = _repository.GetById(message.InventoryItemId);
		item.ChangeName(message.NewName);
		_repository.Save(item, message.OriginalVersion);
	}
}
