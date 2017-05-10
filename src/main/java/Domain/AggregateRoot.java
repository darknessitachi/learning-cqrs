package Domain;

import java.util.ArrayList;
import java.util.List;

import Events.Event;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:55:23
 * @version 1.0
 * @since 1.0 
 */
public abstract class AggregateRoot
{
    private  List<Event> _changes = new ArrayList<>();

    private int version;
    
    public abstract String getId();
    
    public int getVersion() {
    	return version;
    }
    
     void setVersion(int version ) {
    	this.version = version;
    }
    

    public List<Event> GetUncommittedChanges()
    {
        return _changes;
    }

    public void MarkChangesAsCommitted()
    {
        _changes.clear();
    }

    public void LoadsFromHistory(List<Event> history)
    {
        for (Event e : history) ApplyChange(e, false);
    }

    protected void ApplyChange(Event event)
    {
        ApplyChange(event, true);
    }

    // push atomic aggregate changes to local history for further processing (EventStore.SaveEvents)
    private void ApplyChange(Event event, boolean isNew)
    {
//        this.AsDynamic().Apply(event);
        if(isNew) _changes.add(event);
    }
}