package EventStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Events.Event;
import FakeBus.IEventPublisher;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:57:38
 * @version 1.0
 * @since 1.0
 */
public class EventStore implements IEventStore {
	private IEventPublisher _publisher;

	private class EventDescriptor {

		public Event EventData;
		public String Id;
		public int Version;

		public EventDescriptor(String id, Event eventData, int version) {
			EventData = eventData;
			Version = version;
			Id = id;
		}
	}

	public EventStore(IEventPublisher publisher) {
		_publisher = publisher;
	}

	private Map<String, List<EventDescriptor>> _current = new HashMap<>();

	public void SaveEvents(String aggregateId, List<Event> events, int expectedVersion) {
		List<EventDescriptor> eventDescriptors = _current.get(aggregateId);

		// try to get event descriptors list for given aggregate id
		// otherwise -> create empty dictionary
		if (!_current.containsKey(aggregateId)) {
			eventDescriptors = new ArrayList<>();
			_current.put(aggregateId, eventDescriptors);
		}
		// check whether latest event version matches current aggregate version
		// otherwise -> throw exception
		else if (eventDescriptors.get(eventDescriptors.size() - 1).Version != expectedVersion
				&& expectedVersion != -1) {
			throw new ConcurrencyException();
		}
		int i = expectedVersion;

		// iterate through current aggregate events increasing version with each
		// processed event
		for (Event event : events) {
			i++;
			event.Version = i;

			// push event to the event descriptors list for current aggregate
			eventDescriptors.add(new EventDescriptor(aggregateId, event, i));

			// publish current event to the bus for further processing by
			// subscribers
			_publisher.Publish(event);
		}
	}

	// collect all processed events for given aggregate and return them as a
	// list
	// used to build up an aggregate from its history (Domain.LoadsFromHistory)
	public  List<Event> GetEventsForAggregate(String aggregateId)
    {
        List<EventDescriptor> eventDescriptors = _current.get(aggregateId);

        if (eventDescriptors == null)
        {
            throw new AggregateNotFoundException();
        }

        return eventDescriptors.stream().map((desc)->{
        	return desc.EventData;
        }).collect(Collectors.toList());
    }
}
