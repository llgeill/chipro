
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
        int limit;
        int page;
        try{
            //每页多少条数据
            limit = Integer.parseInt(request.getParameter("limit"));
            //第几页
            page = Integer.parseInt(request.getParameter("page"));
        }catch (NumberFormatException nb){
            return new Page();
        }

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
