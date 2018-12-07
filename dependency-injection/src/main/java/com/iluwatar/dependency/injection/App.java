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
package com.iluwatar.dependency.injection;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Dependency Injection pattern deals with how objects handle their dependencies. The pattern
 * implements so called inversion of control principle. Inversion of control has two specific rules:
 * - High-level modules should not depend on low-level modules. Both should depend on abstractions.
 * - Abstractions should not depend on details. Details should depend on abstractions.
 * <p>
 * In this example we show you three different wizards. The first one ({@link SimpleWizard}) is a
 * naive implementation violating the inversion of control principle. It depends directly on a
 * concrete implementation which cannot be changed.
 * <p>
 * The second and third wizards({@link AdvancedWizard} and {@link AdvancedSorceress}) are more flexible.
 * They do not depend on any concrete implementation but abstraction. They utilizes Dependency Injection
 * pattern allowing their {@link Tobacco} dependency to be injected through constructor ({@link AdvancedWizard})
 * or setter ({@link AdvancedSorceress}). This way, handling the dependency is no longer the wizard's
 * responsibility. It is resolved outside the wizard class.
 * <p>
 * The fourth example takes the pattern a step further. It uses Guice framework for Dependency
 * Injection. {@link TobaccoModule} binds a concrete implementation to abstraction. Injector is then
 * used to create {@link GuiceWizard} object with correct dependencies.
 *
 * *依赖注入模式处理对象如何处理它们的依赖关系。模式
 *实现所谓的反转控制原理。控制反转有两个特定的规则：
 *高级模块不应依赖于低级模块。两者都应该依赖于抽象。
 *抽象不应依赖于细节。细节应该依赖于抽象。
 *< P>
 *在这个例子中，我们展示了三个不同的向导。第一个（{@链接SimeWalth}）是
 ＊违反控制原理反演的幼稚实现。它直接取决于
 *不能改变的具体实施。
 *< P>
 *第二和第三向导({@link AdvancedWizard}和{@link AdvancedSorceress})更加灵活。
 *它们不依赖于任何具体的实现，而是抽象。它们利用依赖注入
 *允许通过构造函数（{@link AdvancedWizard}）注入它们的{@link Tobacco}依赖项的模式
 *或设置器（{链接高级巫师}）。这样，处理依赖关系不再是向导的。
 *责任。它在向导类之外解决。
 *< P>
 *第四示例将模式进一步进行了一步。它使用Guice框架进行依赖
 *注射。{@链接toBoCCOMulule}将具体实现绑定到抽象。然后喷射器
 *用于创建具有正确相关性的{@ Link Guice向导}对象。
 */
public class App {

  /**
   * Program entry point
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    SimpleWizard simpleWizard = new SimpleWizard();
    simpleWizard.smoke();

    AdvancedWizard advancedWizard = new AdvancedWizard(new SecondBreakfastTobacco());
    advancedWizard.smoke();

    AdvancedSorceress advancedSorceress = new AdvancedSorceress();
    advancedSorceress.setTobacco(new SecondBreakfastTobacco());
    advancedSorceress.smoke();

    Injector injector = Guice.createInjector(new TobaccoModule());
    GuiceWizard guiceWizard = injector.getInstance(GuiceWizard.class);
    guiceWizard.smoke();
  }
}
