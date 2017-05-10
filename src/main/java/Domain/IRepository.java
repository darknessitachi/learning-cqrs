package Domain;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:55:36
 * @version 1.0
 * @since 1.0 
 */
public interface IRepository<T extends AggregateRoot> 
{
    void Save(AggregateRoot aggregate, int expectedVersion);
    T GetById(String id);
}
