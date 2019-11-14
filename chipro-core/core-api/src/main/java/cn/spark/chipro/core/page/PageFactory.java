
package cn.spark.chipro.core.page;


import cn.spark.chipro.core.util.HttpContext;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * Layui Table默认的分页参数创建
 *
 * @author fengshuonan
 * @date 2017-04-05 22:25
 */
public class PageFactory {

    /**
     * 获取layui table的分页参数
     *
     * @author fengshuonan
     * @Date 2019/1/25 22:13
     */
    public static Page defaultPage() {
        HttpServletRequest request = HttpContext.getRequest();

        //每页多少条数据
        int limit = Integer.valueOf(request.getParameter("limit"));

        //第几页
        int page = Integer.valueOf(request.getParameter("page"));

        return new Page(page, limit);
    }

    /**
     * 创建layui能识别的分页响应参数
     *
     * @author fengshuonan
     * @Date 2019/1/25 22:14
     */
    public static PageInfo createPageInfo(IPage page) {
        PageInfo result = new PageInfo();
        result.setCount(page.getTotal());
        result.setDatas(page.getRecords());
        return result;
    }
}
