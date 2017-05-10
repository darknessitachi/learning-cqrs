package Domain;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import EventStore.IEventStore;
import Events.Event;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:55:46
 * @version 1.0
 * @since 1.0 
 */
public class Repository<T extends AggregateRoot> implements IRepository<T> 
{
	private Class<T> genericClass;
	
    private  IEventStore _storage;

    public Repository(IEventStore storage)
    {
        _storage = storage;
    }
    
    @SuppressWarnings("unchecked")
	protected Class<T> getGenericClass() {

		if (genericClass == null) {
			Type type = getClass().getGenericSuperclass();
			Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
			genericClass = (Class<T>) trueType;
		}
		return genericClass;
	}

    public void Save(AggregateRoot aggregate, int expectedVersion)
    {
        _storage.SaveEvents(aggregate.getId(), aggregate.GetUncommittedChanges(), expectedVersion);
    }

    public T GetById(String id)
    {
       T obj = null;
       try {
		obj = getGenericClass().newInstance();
	} catch (InstantiationException | IllegalAccessException e1) {
		e1.printStackTrace();
	}
         List<Event> e = _storage.GetEventsForAggregate(id);
        obj.LoadsFromHistory(e);
        return obj;
    }
}
