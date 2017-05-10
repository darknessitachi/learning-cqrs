package EventStore;

import java.util.List;

import Events.Event;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:57:24
 * @version 1.0
 * @since 1.0 
 */
public interface IEventStore
{
    void SaveEvents(String aggregateId, List<Event> events, int expectedVersion);
    List<Event> GetEventsForAggregate(String aggregateId);
}
