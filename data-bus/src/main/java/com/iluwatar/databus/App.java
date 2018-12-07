/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.iluwatar.databus;

import com.iluwatar.databus.data.MessageData;
import com.iluwatar.databus.data.StartingData;
import com.iluwatar.databus.data.StoppingData;
import com.iluwatar.databus.members.MessageCollectorMember;
import com.iluwatar.databus.members.StatusMember;

import java.time.LocalDateTime;

/**
 * The Data Bus pattern
 * <p>
 * @see <a href="http://wiki.c2.com/?DataBusPattern">http://wiki.c2.com/?DataBusPattern</a>
 * <p>The Data-Bus pattern provides a method where different parts of an application may
 * pass messages between each other without needing to be aware of the other's existence.</p>
 * <p>Similar to the {@code ObserverPattern}, members register themselves with the {@link DataBus}
 * and may then receive each piece of data that is published to the Data-Bus. The member
 * may react to any given message or not.</p>
 * <p>It allows for Many-to-Many distribution of data, as there may be any number of
 * publishers to a Data-Bus, and any number of members receiving the data. All members
 * will receive the same data, the order each receives a given piece of data, is an
 * implementation detail.</p>
 * <p>Members may unsubscribe from the Data-Bus to stop receiving data.</p>
 * <p>This example of the pattern implements a Synchronous Data-Bus, meaning that
 * when data is published to the Data-Bus, the publish method will not return until
 * all members have received the data and returned.</p>
 * <p>The {@link DataBus} class is a Singleton.</p>
 * <p>Members of the Data-Bus must implement the {@link Member} interface.</p>
 * <p>Data to be published via the Data-Bus must implement the {@link DataType} interface.</p>
 * <p>The {@code data} package contains example {@link DataType} implementations.</p>
 * <p>The {@code members} package contains example {@link Member} implementations.</p>
 * <p>The {@link StatusMember} demonstrates using the DataBus to publish a message
 * to the Data-Bus when it receives a message.</p>
＊数据总线模式
 *< P>
 *@参见“HeRF=”“HTTP//WKI.C2.COM/？”“数据模式”> HTTP:/Wik.C2.COM/？DATABUS模式< / A>
 *<P>数据总线模式提供了一种方法，其中应用程序的不同部分可以
 *在彼此之间传递消息而不需要知道对方的存在。</P>
 *<P>类似于{@代码ObServStudio }，成员用{@链接DATABUS }注册它们自己。
 *然后可以接收发布到数据总线的每一个数据。成员
 *可以对任何给定的消息作出反应或不。</P>
 *>它允许对数据进行多对多的分布，因为可能有任意数量的数据。
 *发布服务器到数据总线，以及接收数据的任何成员。所有成员
 *将接收相同的数据，每个接收到给定数据的顺序是
 *实现细节。</P>
 *> P>成员可以退订数据总线以停止接收数据。
 *P>此模式的示例实现同步数据总线，这意味着
 *当数据发布到数据总线时，发布方法将不返回到
 *所有成员已收到数据并返回。</P>
 *< P> {@链接DATABOS}类是一个单独的。< /P>
 *< P>数据总线成员必须实现{@链接成员}接口。< /P>
 *> P>通过数据总线发布的数据必须实现{@链接数据类型}接口。</P>
 *@ p> {@代码数据}包包含示例{@链接数据类型}实现。</P>
 *< P> {@代码成员}包包含示例{@链接成员}实现。</P>
 *< p> {@链接状态成员}演示如何使用DATABUS发布消息
 *当数据总线接收到消息时。
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
class App {

  public static void main(String[] args) {
    final DataBus bus = DataBus.getInstance();
    bus.subscribe(new StatusMember(1));
    bus.subscribe(new StatusMember(2));
    final MessageCollectorMember foo = new MessageCollectorMember("Foo");
    final MessageCollectorMember bar = new MessageCollectorMember("Bar");
    bus.subscribe(foo);
    bus.publish(StartingData.of(LocalDateTime.now()));
    bus.publish(MessageData.of("Only Foo should see this"));
    bus.subscribe(bar);
    bus.publish(MessageData.of("Foo and Bar should see this"));
    bus.unsubscribe(foo);
    bus.publish(MessageData.of("Only Bar should see this"));
    bus.publish(StoppingData.of(LocalDateTime.now()));
  }
}
