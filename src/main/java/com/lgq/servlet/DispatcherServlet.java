package com.lgq.servlet;

        import com.lgq.adapter.HandlerAdapter;
        import com.lgq.handler.HandlerMapping;
        import com.lgq.ioc.BeanFactory;
        import com.lgq.model.ModelAndView;
        import javax.servlet.ServletConfig;
        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.PrintWriter;
        import java.util.List;
        import java.util.Map;

/**
 * @author lgq
 * @date 2019/10/25
 */
public class DispatcherServlet extends FrameworkServlet{

    private List<HandlerMapping> handlerMappings = null;

    private List<HandlerAdapter> handlerAdapters = null;

    /**
     * 初始化bean工厂
     * @param config servlet全局配置
     */
    private void initBeanFactory(ServletConfig config) {
        // 获取web.xml中配置的contextConfigLocation 即spring-mvc文件路径
        String contextLocation = config.getInitParameter("contextConfigLocation");
        // 初始化容器
        BeanFactory.initBeanFactory(contextLocation);
    }

    /**
     * 初始化handlerMappings
     */
    private void initHandlerMappings() {
        handlerMappings = BeanFactory.getBeansOfType(HandlerMapping.class);
    }

    /**
     * 初始化handler适配器集合
     */
    private void initHandlerAdapters() {
        handlerAdapters = BeanFactory.getBeansOfType(HandlerAdapter.class);
    }

    /**
     * 在初始化servlet的时候进行 工厂的初始化 handlerMapping初始化
     * 和 handlerAdapter的初始化
     * @param config servlet配置信息
     * @throws ServletException 异常
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        initBeanFactory(config);
        initHandlerMappings();
        initHandlerAdapters();
    }

    private Object getHandler(HttpServletRequest request) throws Exception {
        if (handlerMappings != null && handlerMappings.size() > 0) {
            // 遍历处理器映射集合并获取相应的处理器
            for (HandlerMapping handlerMapping: handlerMappings) {
                Object handler = handlerMapping.getHandler(request);
                if (handler != null) {
                    return handler;
                }
            }
        }
        return null;
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        System.out.println(handlerAdapters.size());
        if (handlerAdapters != null && handlerAdapters.size() > 0) {
            // 遍历处理器适配器集合获取能够适配处理器的适配器
            for (HandlerAdapter handlerAdapter: handlerAdapters) {
                if (handlerAdapter.support(handler)) {
                    return handlerAdapter;
                }
            }
        }
        return null;
    }

    @Override
    public void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取处理器
        Object handler = getHandler(request);
        if (handler != null) {
            // 获取处理器对应的处理器适配器
            HandlerAdapter handlerAdapter = getHandlerAdapter(handler);
            if (handlerAdapter != null) {
                ModelAndView modelAndView = handlerAdapter.handleRequest(request, response, handler);
                if (modelAndView != null) {
                    Map<String, Object> model = modelAndView.getModel();
                    PrintWriter writer = response.getWriter();
                    for (String string: model.keySet()) {
                        writer.write(string);
                    }
                    writer.close();
                }

            }
        }
    }

}
