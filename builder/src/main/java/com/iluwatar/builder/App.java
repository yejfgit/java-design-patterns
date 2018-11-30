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
package com.iluwatar.builder;

import com.iluwatar.builder.Hero.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * The intention of the Builder pattern is to find a solution to the telescoping constructor
 * anti-pattern. The telescoping constructor anti-pattern occurs when the increase of object
 * constructor parameter combination leads to an exponential list of constructors. Instead of using
 * numerous constructors, the builder pattern uses another object, a builder, that receives each
 * initialization parameter step by step and then returns the resulting constructed object at once.
 * <p>
 * The Builder pattern has another benefit. It can be used for objects that contain flat data (html
 * code, SQL query, X.509 certificate...), that is to say, data that can't be easily edited. This
 * type of data cannot be edited step by step and must be edited at once. The best way to construct
 * such an object is to use a builder class.
 * <p>
 * In this example we have the Builder pattern variation as described by Joshua Bloch in Effective
 * Java 2nd Edition.
 * <p>
 * We want to build {@link Hero} objects, but its construction is complex because of the many
 * parameters needed. To aid the user we introduce {@link Builder} class. {@link Hero.Builder}
 * takes the minimum parameters to build {@link Hero} object in its constructor. After that
 * additional configuration for the {@link Hero} object can be done using the fluent
 * {@link Builder} interface. When configuration is ready the build method is called to receive
 * the final {@link Hero} object.
 *Builder模式的目的是找到伸缩构造函数的解决方案。
 *反模式。当对象增加时会出现伸缩构造函数反模式。
 *构造函数参数组合导致构造函数的指数列表。代替使用
 *许多构造函数，构建器模式使用另一个对象，一个构建器，它接收每个
 *逐步初始化参数，然后立即返回所得到的构造对象。
 *< P>
 *构造器模式还有另一个好处。它可以用于包含平面数据的对象（HTML）
 *代码，SQL查询，X.509证书…），也就是说，数据不容易编辑。这个
 *数据类型不能一步一步地编辑，必须立即编辑。最佳施工方法
 *这样的对象是使用生成器类。
 *< P>
 *在这个例子中，我们使用了Joshua Bloch在《有效》中描述的Builder模式变化
 * Java第二版。
 *< P>
 *我们想构建{@link Hero}对象，但是它的构造很复杂，因为许多
 *需要的参数。为了帮助用户，我们引入{@ Link Builder }类。{@链接英雄.Builder }
 *在构造函数中使用最小参数来构建{@链接英雄}对象。之后
 *可以使用fluent对{@link Hero}对象进行附加配置
 *{Link Builder }接口。配置完成后，调用生成方法来接收
 *最后的{@链接英雄}对象。
 * 
 */
public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  /**
   * Program entry point
   * 
   * @param args command line args
   */
  public static void main(String[] args) {

    Hero mage =
        new Hero.Builder(Profession.MAGE, "Riobard").withHairColor(HairColor.BLACK)
            .withWeapon(Weapon.DAGGER).build();
    LOGGER.info(mage.toString());

    Hero warrior =
        new Hero.Builder(Profession.WARRIOR, "Amberjill").withHairColor(HairColor.BLOND)
            .withHairType(HairType.LONG_CURLY).withArmor(Armor.CHAIN_MAIL).withWeapon(Weapon.SWORD)
            .build();
    LOGGER.info(warrior.toString());

    Hero thief =
        new Hero.Builder(Profession.THIEF, "Desmond").withHairType(HairType.BALD)
            .withWeapon(Weapon.BOW).build();
    LOGGER.info(thief.toString());

  }
}
