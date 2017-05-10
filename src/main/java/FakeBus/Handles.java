package FakeBus;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:58:59
 * @version 1.0
 * @since 1.0 
 */
public interface Handles<T>
{
    void Handle(T message);
}
