package FakeBus;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Action;

import Commands.Command;
import Events.Event;
import simplecqrs.Message;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:58:44
 * @version 1.0
 * @since 1.0
 */
public class FakeBus implements ICommandSender, IEventPublisher {
	/** KEY：事件类的类名，值：所有监听此事件的处理类实例 */
	private static Map<String, List<LisenerInfo>> listeners = new LinkedHashMap<String, List<LisenerInfo>>();

	// private Map<Type, List<Action<Message>>> _routes = new HashMap<Type,
	// List<Action<Message>>>();

	public void RegisterHandler(Object handler) {
		// 注意这里不能使用listener.getClass()方法，因此方法返回的只是SPRING的代理类，此代理类的方法没有注解信息

		// Method[] methods = listener.getRealClass().getDeclaredMethods();

		Method[] methods = getDeclaredMethods(handler);

		// List<Action<Message>> handlers;
		//
		// if(!_routes.TryGetValue(typeof(T), out handlers))
		// {
		// handlers = new List<Action<Message>>();
		// _routes.Add(typeof(T), handlers);
		// }
		//
		// handlers.Add((x => handler((T)x)));
		for (Method method : methods) {
			// 判断方法中是否有指定注解类型的注解

			boolean hasAnnotation = method.isAnnotationPresent(HandleEvent.class);
			if (hasAnnotation) {
				// 根据注解类型返回方法的指定类型注解

				HandleEvent annotation = method.getAnnotation(HandleEvent.class);

				{// 处理EventClass

					Class<?>[] events = method.getParameterTypes();
					if (events != null && events.length > 0) {// 这里过滤掉没有真正实现事件监听的业务类

						Class<?> event = events[0];
						String methodName = method.getName();

						Method listenEventMethod = null;
						try {
							listenEventMethod = handler.getClass().getMethod(methodName, method.getParameterTypes());
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("初始化事件监听器时出错：", e);
						}

						List<LisenerInfo> listenerInfos = listeners.get(event.getName());
						if (listenerInfos == null) {
							listenerInfos = new ArrayList<>();
							put(event.getName(), listenerInfos);
						}
						// 注意这里要用代理类的方法，即listener.getClass().getMethod(method.getName())，不能直接使用method变量，下同

						listenerInfos.add(new LisenerInfo(handler, listenEventMethod));
					}
				}

			}
		}
	}

	private static void put(String eventName, List<LisenerInfo> listenerInfos) {
		listeners.put(eventName, listenerInfos);
	}

	public <T extends Command> void Send(T command) {
		// List<Action<Message>> handlers;
		//
		// if (_routes.TryGetValue(typeof(T), out handlers))
		// {
		// if (handlers.Count != 1) throw new InvalidOperationException("cannot
		// send to more than one handler");
		// handlers[0](command);
		// }
		// else
		// {
		// throw new InvalidOperationException("no handler registered");
		// }
	}

	public <T extends Event> void Publish(T event) {
		// List<Action<Message>> handlers;
		//
		// if (!_routes.TryGetValue(@event.GetType(), out handlers)) return;
		//
		// foreach(var handler in handlers)
		// {
		// //dispatch on thread pool for added awesomeness
		// var handler1 = handler;
		// ThreadPool.QueueUserWorkItem(x => handler1(@event));
		// }
	}

	/**
	 * 
	 * 获取对象中定义的所有public方法，包含父类中的方法
	 * 
	 * 
	 * 
	 * @author Darkness
	 * 
	 * @date 2012-9-19 下午8:33:55
	 * 
	 * @version V1.0
	 * 
	 */
	public static Method[] getDeclaredMethods(Object object) {

		List<Method> methods = new ArrayList<>();

		for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				Method[] _methods = clazz.getDeclaredMethods();
				for (int i = 0; i < _methods.length; i++) {
					methods.add(_methods[i]);
				}
			} catch (Exception e) {
				// 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。

				// 如果这里的异常打印或者往外抛，则就不会执行clazz =

				// clazz.getSuperclass(),最后就不会进入到父类中了
			}
		}

		return methods.toArray(new Method[0]);
	}

	// 此类记录目标方法和目标类
	class LisenerInfo {

		private Method method;// 目标方法
		private Object service;// 业务类实例

		public LisenerInfo(Object service, Method method) {
			this.method = method;
			this.service = service;
		}

		public Method getMethod() {
			return method;
		}

		public Object getService() {
			return service;
		}
	}
}
