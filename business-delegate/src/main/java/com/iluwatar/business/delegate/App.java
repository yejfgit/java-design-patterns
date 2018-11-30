/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.business.delegate;

/**
 * The Business Delegate pattern adds an abstraction layer between the presentation and business
 * tiers. By using the pattern we gain loose coupling between the tiers. The Business Delegate
 * encapsulates knowledge about how to locate, connect to, and interact with the business objects
 * that make up the application.
 * 
 * <p>Some of the services the Business Delegate uses are instantiated directly, and some can be
 * retrieved through service lookups. The Business Delegate itself may contain business logic too
 * potentially tying together multiple service calls, exception handling, retrying etc.
 * 
 * <p>In this example the client ({@link Client}) utilizes a business delegate (
 * {@link BusinessDelegate}) to execute a task. The Business Delegate then selects the appropriate
 * service and makes the service call.
 * *业务委托模式添加了表示和业务之间的抽象层
 *层。通过使用模式，我们获得松散耦合之间的层。商务代表
 *封装关于如何定位、连接以及与业务对象交互的知识
 *构成应用程序。
 *
 *<p>业务委托使用的一些服务是直接实例化的，有些服务可以是
 *通过服务查找检索。业务委托本身也可能包含业务逻辑。
 *可能将多个服务调用、异常处理、重试等绑定在一起。
 *
 *<p>在这个例子中，客户机（{@链接客户机）利用业务委托（
 * {@链接业务委托}以执行一项任务。业务委托然后选择适当的
 *服务并进行服务调用。
 */
public class App {

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {

    BusinessDelegate businessDelegate = new BusinessDelegate();
    BusinessLookup businessLookup = new BusinessLookup();
    businessLookup.setEjbService(new EjbService());
    businessLookup.setJmsService(new JmsService());

    businessDelegate.setLookupService(businessLookup);
    businessDelegate.setServiceType(ServiceType.EJB);

    Client client = new Client(businessDelegate);
    client.doTask();

    businessDelegate.setServiceType(ServiceType.JMS);
    client.doTask();
  }
}
