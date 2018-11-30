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
package com.iluwatar.command;

/**
 * 
 * The Command pattern is a behavioral design pattern in which an object is used to encapsulate all
 * information needed to perform an action or trigger an event at a later time. This information
 * includes the method name, the object that owns the method and values for the method parameters.
 * <p>
 * Four terms always associated with the command pattern are command, receiver, invoker and client.
 * A command object (spell) knows about the receiver (target) and invokes a method of the receiver.
 * Values for parameters of the receiver method are stored in the command. The receiver then does
 * the work. An invoker object (wizard) knows how to execute a command, and optionally does
 * bookkeeping about the command execution. The invoker does not know anything about a concrete
 * command, it knows only about command interface. Both an invoker object and several command
 * objects are held by a client object (app). The client decides which commands to execute at which
 * points. To execute a command, it passes the command object to the invoker object.
 * <p>
 * In other words, in this example the wizard casts spells on the goblin. The wizard keeps track of
 * the previous spells cast, so it is easy to undo them. In addition, the wizard keeps track of the
 * spells undone, so they can be redone.
 *
 *
 * The Command pattern is a behavioral design pattern in which an object is used to encapsulate all
 * information needed to perform an action or trigger an event at a later time. This information
 * includes the method name, the object that owns the method and values for the method parameters.
 * <p>
 * Four terms always associated with the command pattern are command, receiver, invoker and client.
 * A command object (spell) knows about the receiver (target) and invokes a method of the receiver.
 * Values for parameters of the receiver method are stored in the command. The receiver then does
 * the work. An invoker object (wizard) knows how to execute a command, and optionally does
 * bookkeeping about the command execution. The invoker does not know anything about a concrete
 * command, it knows only about command interface. Both an invoker object and several command
 * objects are held by a client object (app). The client decides which commands to execute at which
 * points. To execute a command, it passes the command object to the invoker object.
 * <p>
 * In other words, in this example the wizard casts spells on the goblin. The wizard keeps track of
 * the previous spells cast, so it is easy to undo them. In addition, the wizard keeps track of the
 * spells undone, so they can be redone.

 *命令模式是一种行为设计模式，其中对象用于封装所有
 *执行动作或在稍后触发事件所需的信息。这些信息
 *包括方法名称、拥有方法的对象以及方法参数的值。
 *< P>
 *与命令模式始终关联的四个术语是命令、接收器、调用器和客户端。
 *命令对象（拼写）知道接收器（目标）并调用接收器的方法。
 *接收器方法参数的值存储在命令中。然后接收器
 *这项工作。调用器对象（向导）知道如何执行命令，并且可选地执行。
 *关于命令执行的簿记。调用程序不知道具体的内容。
 *命令，它只知道命令接口。调用器对象和多个命令
 *对象由客户端对象（app）保存。客户端决定执行哪些命令
 *点。为了执行命令，它将命令对象传递给调用器对象。
 *< P>
 换句话说，在这个例子中，向导在妖精上施放咒语。向导跟踪
 *前面的法术，所以很容易撤消它们。此外，向导还跟踪
 *符咒撤消，所以他们可以重做。
 * 
 */
public class App {

  /**
   * Program entry point
   * 
   * @param args command line args
   */
  public static void main(String[] args) {
    Wizard wizard = new Wizard();
    Goblin goblin = new Goblin();

    goblin.printStatus();

    wizard.castSpell(new ShrinkSpell(), goblin);
    goblin.printStatus();

    wizard.castSpell(new InvisibilitySpell(), goblin);
    goblin.printStatus();

    wizard.undoLastSpell();
    goblin.printStatus();

    wizard.undoLastSpell();
    goblin.printStatus();

    wizard.redoLastSpell();
    goblin.printStatus();

    wizard.redoLastSpell();
    goblin.printStatus();
  }
}
