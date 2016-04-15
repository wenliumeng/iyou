package org.iyou.common.model;

/**
 * Created by seyMour on 2016/4/14.
 * ☞120465271@qq.com☜
 */
public class Pager {
    public static int DEFALUT_PAGESIZE = 20;
    private static int DEFALUT_PAGEINDEX = 1;
    public static final int QUERY_TYPE_ALL = 0;
    public static final int QUERY_TYPE_LIST = 1;
    public static final int QUERY_TYPE_COUNT = 2;
    public static final String EXPORT_TYPE_XLS = "xls";
    public static final String EXPORT_TYPE_PDF = "pdf";
    public static final String EXPORT_TYPE_PRINT = "print";
    private int pageSize;
    private int pageIndex;
    private int pageType;
    private int startIndex;
    private int counts;
    private String export;
    private String[] exportHeaders;
    private String[] exportProperties;

    public Pager(String pageSize, String pageIndex, String pageType)
    {
        try
        {
            this.pageSize = new Integer(pageSize).intValue();
        }
        catch (NumberFormatException e)
        {
            this.pageSize = DEFALUT_PAGESIZE;
        }
        try
        {
            this.pageIndex = new Integer(pageIndex).intValue();
        }
        catch (NumberFormatException e)
        {
            this.pageIndex = DEFALUT_PAGEINDEX;
        }
        try
        {
            this.pageType = new Integer(pageType).intValue();
        }
        catch (NumberFormatException e)
        {
            this.pageType = 0;
        }
        initPager();
    }

    public Pager(int pageSize, int pageIndex, int pageType)
    {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pageType = pageType;
        initPager();
    }

    private void initPager()
    {
        this.pageIndex = (this.pageIndex < 1 ? 1 : this.pageIndex);
        this.startIndex = ((this.pageIndex - 1) * this.pageSize);
    }

    public int getCounts()
    {
        return this.counts;
    }

    public void setCounts(int counts)
    {
        this.counts = counts;
        int maxPage = getMaxPage();
        if ((maxPage > 0) && (this.pageIndex > maxPage)) {
            this.pageIndex = maxPage;
        }
        initPager();
    }

    public int getPageIndex()
    {
        return this.pageIndex;
    }

    public int getMaxPage()
    {
        double maxPage = Math.ceil(new Double(this.counts).doubleValue() / this.pageSize);
        return Double.valueOf(maxPage).intValue();
    }

    public void setPageIndex(int pageIndex)
    {
        this.pageIndex = pageIndex;
    }

    public int getPageSize()
    {
        return this.pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getStartIndex()
    {
        return this.startIndex;
    }

    public void setStartIndex(int startIndex)
    {
        this.startIndex = startIndex;
    }

    public int getPageType()
    {
        return this.pageType;
    }

    public void setPageType(int pageType)
    {
        this.pageType = pageType;
    }

    public String getExport()
    {
        return this.export;
    }

    public void setExport(String export)
    {
        this.export = export;
    }

    public String[] getExportHeaders()
    {
        return this.exportHeaders;
    }

    public void setExportHeaders(String[] exportHeaders)
    {
        this.exportHeaders = exportHeaders;
    }

    public String[] getExportProperties()
    {
        return this.exportProperties;
    }

    public void setExportProperties(String[] exportProperties)
    {
        this.exportProperties = exportProperties;
    }
}
