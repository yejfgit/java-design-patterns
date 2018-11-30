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
package com.iluwatar.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * The Caching pattern describes how to avoid expensive re-acquisition of resources by not releasing
 * the resources immediately after their use. The resources retain their identity, are kept in some
 * fast-access storage, and are re-used to avoid having to acquire them again. There are four main
 * caching strategies/techniques in this pattern; each with their own pros and cons. They are;
 * <code>write-through</code> which writes data to the cache and DB in a single transaction,
 * <code>write-around</code> which writes data immediately into the DB instead of the cache,
 * <code>write-behind</code> which writes data into the cache initially whilst the data is only
 * written into the DB when the cache is full, and <code>cache-aside</code> which pushes the
 * responsibility of keeping the data synchronized in both data sources to the application itself.
 * The <code>read-through</code> strategy is also included in the mentioned four strategies --
 * returns data from the cache to the caller <b>if</b> it exists <b>else</b> queries from DB and
 * stores it into the cache for future use. These strategies determine when the data in the cache
 * should be written back to the backing store (i.e. Database) and help keep both data sources
 * synchronized/up-to-date. This pattern can improve performance and also helps to maintain
 * consistency between data held in the cache and the data in the underlying data store.
 * <p>
 * In this example, the user account ({@link UserAccount}) entity is used as the underlying
 * application data. The cache itself is implemented as an internal (Java) data structure. It adopts
 * a Least-Recently-Used (LRU) strategy for evicting data from itself when its full. The four
 * strategies are individually tested. The testing of the cache is restricted towards saving and
 * querying of user accounts from the underlying data store ( {@link DbManager}). The main class (
 * {@link App} is not aware of the underlying mechanics of the application (i.e. save and query) and
 * whether the data is coming from the cache or the DB (i.e. separation of concern). The AppManager
 * ({@link AppManager}) handles the transaction of data to-and-from the underlying data store
 * (depending on the preferred caching policy/strategy).
 * <p>
 * <i>{@literal App --> AppManager --> CacheStore/LRUCache/CachingPolicy --> DBManager} </i>
 * </p>
 *
 * @see CacheStore
 * @see LruCache
 * @see CachingPolicy
 *
 * *缓存模式描述了如何通过不释放来避免昂贵的资源重新获取。
 *资源在使用后立即使用。资源保留它们的身份，保存在一些
 *快速访问存储，并被重新使用，以避免再次获取它们。主要有四种
 *在这种模式中的缓存策略/技术；每个都有自己的优缺点。他们是；
 *<code>write-.</code>，它在单个事务中将数据写入缓存和DB，
 *<code>write-.</code>，它直接将数据写入DB而不是缓存，
 *<code>write-.</code>，它最初将数据写入缓存，而数据仅是
 *当缓存已满时写入DB，以及<code>cache-aside</code>，这将
 *负责保持两个数据源中的数据与应用程序本身的同步。
 *代码>读出</代码>策略也包括在上述四个策略中.----
 *将数据从高速缓存返回给调用者<b>，如果</b>它存在<b>.</b>来自DB的查询，以及
 *将其存储到缓存中以备将来使用。这些策略决定缓存中的数据何时发生。
 *应该写回备份存储（即数据库）并帮助保存两个数据源
 *同步/最新。这种模式可以提高性能，也有助于维护。
 *缓存中保存的数据与底层数据存储中的数据之间的一致性。
 *< P>
 *在这个示例中，用户帐户({@link UserAccount})实体用作底层
 *应用数据。缓存本身作为内部（Java）数据结构来实现。采用
 *最近最少使用的（LRU）策略，用于当数据已满时从其本身中删除数据。四
 *战略是个别测试。缓存的测试仅限于保存和
 *从基础数据存储({@link DbManager})查询用户帐户。主要类别（主要类别）
 *{@link App}不知道应用程序的底层机制（即保存和查询）和
 *数据是来自缓存还是来自DB（即关注点分离）。应用程序管理器
 *({@link AppManager})处理与底层数据存储之间的数据事务
 *（取决于首选缓存策略/策略）。
 *< P>
 *<i>{@literal App-->AppManager-->CacheStore/LRUCache/CachingPolicy-->DBManager}</i>
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
    AppManager.initDb(false); // VirtualDB (instead of MongoDB) was used in running the JUnit tests
                              // and the App class to avoid Maven compilation errors. Set flag to
                              // true to run the tests with MongoDB (provided that MongoDB is
                              // installed and socket connection is open).
    AppManager.initCacheCapacity(3);
    App app = new App();
    app.useReadAndWriteThroughStrategy();
    app.useReadThroughAndWriteAroundStrategy();
    app.useReadThroughAndWriteBehindStrategy();
    app.useCacheAsideStategy();
  }

  /**
   * Read-through and write-through
   */
  public void useReadAndWriteThroughStrategy() {
    LOGGER.info("# CachingPolicy.THROUGH");
    AppManager.initCachingPolicy(CachingPolicy.THROUGH);

    UserAccount userAccount1 = new UserAccount("001", "John", "He is a boy.");

    AppManager.save(userAccount1);
    LOGGER.info(AppManager.printCacheContent());
    AppManager.find("001");
    AppManager.find("001");
  }

  /**
   * Read-through and write-around
   */
  public void useReadThroughAndWriteAroundStrategy() {
    LOGGER.info("# CachingPolicy.AROUND");
    AppManager.initCachingPolicy(CachingPolicy.AROUND);

    UserAccount userAccount2 = new UserAccount("002", "Jane", "She is a girl.");

    AppManager.save(userAccount2);
    LOGGER.info(AppManager.printCacheContent());
    AppManager.find("002");
    LOGGER.info(AppManager.printCacheContent());
    userAccount2 = AppManager.find("002");
    userAccount2.setUserName("Jane G.");
    AppManager.save(userAccount2);
    LOGGER.info(AppManager.printCacheContent());
    AppManager.find("002");
    LOGGER.info(AppManager.printCacheContent());
    AppManager.find("002");
  }

  /**
   * Read-through and write-behind
   */
  public void useReadThroughAndWriteBehindStrategy() {
    LOGGER.info("# CachingPolicy.BEHIND");
    AppManager.initCachingPolicy(CachingPolicy.BEHIND);

    UserAccount userAccount3 = new UserAccount("003", "Adam", "He likes food.");
    UserAccount userAccount4 = new UserAccount("004", "Rita", "She hates cats.");
    UserAccount userAccount5 = new UserAccount("005", "Isaac", "He is allergic to mustard.");

    AppManager.save(userAccount3);
    AppManager.save(userAccount4);
    AppManager.save(userAccount5);
    LOGGER.info(AppManager.printCacheContent());
    AppManager.find("003");
    LOGGER.info(AppManager.printCacheContent());
    UserAccount userAccount6 = new UserAccount("006", "Yasha", "She is an only child.");
    AppManager.save(userAccount6);
    LOGGER.info(AppManager.printCacheContent());
    AppManager.find("004");
    LOGGER.info(AppManager.printCacheContent());
  }

  /**
   * Cache-Aside
   */
  public void useCacheAsideStategy() {
    LOGGER.info("# CachingPolicy.ASIDE");
    AppManager.initCachingPolicy(CachingPolicy.ASIDE);
    LOGGER.info(AppManager.printCacheContent());

    UserAccount userAccount3 = new UserAccount("003", "Adam", "He likes food.");
    UserAccount userAccount4 = new UserAccount("004", "Rita", "She hates cats.");
    UserAccount userAccount5 = new UserAccount("005", "Isaac", "He is allergic to mustard.");
    AppManager.save(userAccount3);
    AppManager.save(userAccount4);
    AppManager.save(userAccount5);

    LOGGER.info(AppManager.printCacheContent());
    AppManager.find("003");
    LOGGER.info(AppManager.printCacheContent());
    AppManager.find("004");
    LOGGER.info(AppManager.printCacheContent());
  }
}
