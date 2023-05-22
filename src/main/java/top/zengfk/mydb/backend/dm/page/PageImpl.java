package top.zengfk.mydb.backend.dm.page;

import top.zengfk.mydb.backend.dm.pageCache.PageCache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: ZengFK
 * @Date: 2023/5/9 15:26
 */

public class PageImpl implements Page{
    private int pageNumber;         // 页面的页号，从1开始
    private byte[] data;            // 页实际包含的字节数据
    private boolean dirty;          // 标志页是否是脏页面(已修改但未被写回主存或磁盘)
    private Lock lock;

    private PageCache pc;           // 方便快速对页面的缓存进行释放操作

    public PageImpl(int pageNumber, byte[] data, PageCache pc) {
        this.pageNumber = pageNumber;
        this.data = data;
        this.pc = pc;
        lock = new ReentrantLock();
    }

    @Override
    public void lock() {
        lock.lock();
    }

    @Override
    public void unlock() {
        lock.unlock();
    }

    @Override
    public void release() {
        pc.release(this);
    }

    @Override
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public byte[] getData() {
        return data;
    }
}
