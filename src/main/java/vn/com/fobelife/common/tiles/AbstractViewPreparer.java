package vn.com.fobelife.common.tiles;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.jsp.JspRequest;
import org.apache.tiles.request.servlet.ServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractViewPreparer implements ViewPreparer {

    protected HttpServletRequest getServletRequest(Request viewRequest) {
        if (viewRequest != null) {
            if (viewRequest instanceof ServletRequest) {
                return ((ServletRequest) viewRequest).getRequest();
            }
            if (viewRequest instanceof JspRequest) {
                PageContext pageContext = ((JspRequest) viewRequest).getPageContext();
                if ((pageContext != null) && (pageContext.getRequest() instanceof HttpServletRequest)) {
                    return ((HttpServletRequest) pageContext.getRequest());
                }
            }
        }
        return getServletRequest();
    }

    protected HttpServletResponse getServletResponse(Request viewRequest) {
        if (viewRequest != null) {
            if (viewRequest instanceof ServletRequest) {
                return ((ServletRequest) viewRequest).getResponse();
            }
            if (viewRequest instanceof JspRequest) {
                PageContext pageContext = ((JspRequest) viewRequest).getPageContext();
                if ((pageContext != null) && (pageContext.getResponse() instanceof HttpServletResponse)) {
                    return ((HttpServletResponse) pageContext.getResponse());
                }
            }
        }
        return null;
    }

    protected HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    @SuppressWarnings("unchecked")
    protected <M> M getModel(HttpServletRequest request, String modelAttribute,
                                                     Class<? extends M> modelClazz) {
        if ((request == null) || (modelAttribute == null) || (modelClazz == null) || (modelClazz.isInterface())) {
            return null;
        }
        Object model = request.getAttribute(modelAttribute);
        if (model == null) {
            try {
                model = modelClazz.newInstance();
                request.setAttribute(modelAttribute, model);
            } catch (Exception e) {
                log.error("AbstractViewPreparer Error: ", e);
            }
        }
        return model != null && modelClazz.isAssignableFrom(model.getClass()) ? (M) model : null;
    }

}
