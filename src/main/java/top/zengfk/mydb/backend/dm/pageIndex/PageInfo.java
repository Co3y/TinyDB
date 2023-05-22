package top.zengfk.mydb.backend.dm.pageIndex;

/**
 * @Author: ZengFK
 * @Date: 2023/5/19 13:41
 */

public class PageInfo {
    public int pgno;
    public int freeSpace;

    public PageInfo(int pgno, int freeSpace) {
        this.pgno = pgno;
        this.freeSpace = freeSpace;
    }
}
