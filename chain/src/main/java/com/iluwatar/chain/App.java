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
package com.iluwatar.chain;

/**
 * 
 * The Chain of Responsibility pattern is a design pattern consisting of command objects and a
 * series of processing objects. Each processing object contains logic that defines the types of
 * command objects that it can handle; the rest are passed to the next processing object in the
 * chain. A mechanism also exists for adding new processing objects to the end of this chain.
 * <p>
 * In this example we organize the request handlers ({@link RequestHandler}) into a chain where each
 * handler has a chance to act on the request on its turn. Here the king ({@link OrcKing}) makes
 * requests and the military orcs ({@link OrcCommander}, {@link OrcOfficer}, {@link OrcSoldier})
 * form the handler chain.
 *
 * *责任链模式是由命令对象和
 ＊系列处理对象。每个处理对象包含定义类型的逻辑。
 *它可以处理的命令对象；其余的传递给
 *链。还存在一种机制，用于向该链的末尾添加新的处理对象。
 *< P>
 *在这个例子中，我们将请求处理程序（{@link RequestHandler}）组织成一个链，其中每个都包含
 *处理程序有机会根据请求进行操作。在这里，国王（{ Link OrcKing }）
 *请求和军事兽人（{@link OrcCommander}，{@link OrcOfficer}，{@link OrcSoldier}）
 *形成处理程序链。
 * 
 */
public class App {

  /**
   * Program entry point
   * 
   * @param args command line args
   */
  public static void main(String[] args) {

    OrcKing king = new OrcKing();
    king.makeRequest(new Request(RequestType.DEFEND_CASTLE, "defend castle"));
    king.makeRequest(new Request(RequestType.TORTURE_PRISONER, "torture prisoner"));
    king.makeRequest(new Request(RequestType.COLLECT_TAX, "collect tax"));

  }
}
