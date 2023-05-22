package top.zengfk.mydb.backend.dm.page;

/**
 * @Author: ZengFK
 * @Date: 2023/5/9 15:25
 */

public interface Page {
    void lock();
    void unlock();
    void release();
    void setDirty(boolean dirty);
    boolean isDirty();
    int getPageNumber();
    byte[] getData();
}
