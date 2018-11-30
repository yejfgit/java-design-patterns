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
package com.iluwatar.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Composite pattern is a partitioning design pattern. The Composite pattern describes that a
 * group of objects is to be treated in the same way as a single instance of an object. The intent
 * of a composite is to "compose" objects into tree structures to represent part-whole hierarchies.
 * Implementing the Composite pattern lets clients treat individual objects and compositions
 * uniformly.
 * <p>
 * In this example we have sentences composed of words composed of letters. All of the objects can
 * be treated through the same interface ({@link LetterComposite}).
 *复合模式是分区设计模式。复合模式描述了
 *对象组以与对象的单个实例相同的方式进行处理。意图
 *复合材料是将对象“组合”成树结构来表示部分整个层次结构。
 *实现复合模式允许客户处理单个对象和组合
 *一致。
 *< P>
 *在这个例子中，我们有由字母组成的句子。所有的对象都可以
 *通过相同的接口进行处理（{@ Link LeTeLeope}）。
 */
public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  /**
   * Program entry point
   * 
   * @param args command line args
   */
  public static void main(String[] args) {
    LOGGER.info("Message from the orcs: ");

    LetterComposite orcMessage = new Messenger().messageFromOrcs();
    orcMessage.print();

    LOGGER.info("\nMessage from the elves: ");

    LetterComposite elfMessage = new Messenger().messageFromElves();
    elfMessage.print();
  }
}
