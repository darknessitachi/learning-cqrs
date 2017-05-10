package FakeBus;

/**
 * @author Darkness
 * @date 2017年5月10日 下午5:06:07
 * @version 1.0
 * @since 1.0 
 */
public class BaseEvent<T> {
	
	private String name;
	private String description;
	private T source;

	public BaseEvent(String name) {
		this.name = name;
	}
	
	public BaseEvent(String name, T source) {
		this.name = name;
		this.source = source;
	}
	
	public BaseEvent(String name, String description, T source) {
		this.name = name;
		this.description = description;
		this.source = source;
	}

	public T getSource() {
		return source;
	}
	
	public String name() {
		return this.name;
	}
	
	public String description() {
		return this.description;
	}
}
