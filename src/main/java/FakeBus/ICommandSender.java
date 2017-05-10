package FakeBus;

import Commands.Command;

/**
 * @author Darkness
 * @date 2017年5月10日 下午3:59:11
 * @version 1.0
 * @since 1.0 
 */
public interface ICommandSender
{
  <T extends Command>  void Send(T command) ;

}
