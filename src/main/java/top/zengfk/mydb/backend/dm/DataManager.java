package top.zengfk.mydb.backend.dm;

import top.zengfk.mydb.backend.dm.dataItem.DataItem;
import top.zengfk.mydb.backend.dm.logger.Logger;
import top.zengfk.mydb.backend.dm.page.PageOne;
import top.zengfk.mydb.backend.dm.pageCache.PageCache;
import top.zengfk.mydb.backend.tm.TransactionManager;

/**
 * @Author: ZengFK
 * @Date: 2023/5/19 14:53
 */

public interface DataManager {
    DataItem read(long uid) throws Exception;
    long insert(long xid, byte[] data) throws Exception;
    void close();

    public static DataManager create(String path, long mem, TransactionManager tm) {
        PageCache pc = PageCache.create(path, mem);
        Logger lg = Logger.create(path);

        DataManagerImpl dm = new DataManagerImpl(pc, lg, tm);
        dm.initPageOne();
        return dm;
    }

    public static DataManager open(String path, long mem, TransactionManager tm) {
        PageCache pc = PageCache.open(path, mem);
        Logger lg = Logger.open(path);
        DataManagerImpl dm = new DataManagerImpl(pc, lg, tm);
        if(!dm.loadCheckPageOne()) {
            Recover.recover(tm, lg, pc);
        }
        dm.fillPageIndex();
        PageOne.setVcOpen(dm.pageOne);
        dm.pc.flushPage(dm.pageOne);

        return dm;
    }
}
