package FakeBus;

import Events.Event;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:59:22
 * @version 1.0
 * @since 1.0
 */
public interface IEventPublisher {
	<T extends Event> void Publish(T event);
}
