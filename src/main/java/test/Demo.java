package test;

import java.util.List;
import java.util.UUID;

import Commands.CheckInItemsToInventory;
import Commands.CreateInventoryItem;
import Commands.DeactivateInventoryItem;
import Commands.RemoveItemsFromInventory;
import Commands.RenameInventoryItem;
import Domain.InventoryItem;
import Domain.Repository;
import EventStore.EventStore;
import FakeBus.FakeBus;
import ReadModel.InventoryItemDetailView;
import ReadModel.InventoryItemDetailsDto;
import ReadModel.InventoryItemListDto;
import ReadModel.InventoryListView;
import ReadModel.ReadModelFacade;
import simplecqrs.InventoryCommandHandlers;

/**
 * @author Darkness
 * @date 2017年5月10日 下午5:01:39
 * @version 1.0
 * @since 1.0
 */
public class Demo {

	private FakeBus _bus;
	private ReadModelFacade _readmodel;

	public Demo() {
		init();
		_bus = ServiceLocator.Bus;
        _readmodel = new ReadModelFacade();
        
	}

	public List<InventoryItemListDto> Index()
    {
        return _readmodel.GetInventoryItems();
    }

    public InventoryItemDetailsDto Details(String id)
    {
        return _readmodel.GetInventoryItemDetails(id);
    }

    public void Add(String name)
    {
        _bus.Send(new CreateInventoryItem(UUID.randomUUID().toString(), name));
    }

    public InventoryItemDetailsDto ChangeName(String id)
    {
        return _readmodel.GetInventoryItemDetails(id);
    }

    public void ChangeName(String id, String name, int version)
    {
    	RenameInventoryItem command = new RenameInventoryItem(id, name, version);
        _bus.Send(command);
    }

    public InventoryItemDetailsDto Deactivate(String id)
    {
        return _readmodel.GetInventoryItemDetails(id);
    }

    public void Deactivate(String id, int version)
    {
        _bus.Send(new DeactivateInventoryItem(id, version));
    }

    public InventoryItemDetailsDto CheckIn(String id)
    {
       return _readmodel.GetInventoryItemDetails(id);
    }

    public void CheckIn(String id, int number, int version)
    {
        _bus.Send(new CheckInItemsToInventory(id, number, version));
    }

    public InventoryItemDetailsDto Remove(String id)
    {
        return  _readmodel.GetInventoryItemDetails(id);
    }

    public void Remove(String id, int number, int version)
    {
        _bus.Send(new RemoveItemsFromInventory(id, number, version));
    }
    
	public void init() {
		FakeBus bus = new FakeBus();

		EventStore storage = new EventStore(bus);
		Repository rep = new Repository<InventoryItem>(storage);

		InventoryCommandHandlers commands = new InventoryCommandHandlers(rep);
		bus.RegisterHandler(commands);

		InventoryItemDetailView detail = new InventoryItemDetailView();
		bus.RegisterHandler(detail);

		InventoryListView list = new InventoryListView();
		bus.RegisterHandler(list);

		ServiceLocator.Bus = bus;
	}

	public static void main(String[] args) {

	}
}
