/**
 *
 */

package org.example.springmvc.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class R {
    private Integer code;
    private Object result;
    private String msg;

    public static R ok() {
        return new R().setCode(200);
    }

    public static R err(String msg) {
        return new R().setCode(500).setMsg(msg);
    }

    public static R failLogin() {
        return new R().setCode(500);
    }

    public static R failLogin(String msg) {
        return new R().setCode(500).setMsg(msg);
    }

    public static R failAccess(String msg) {
        return new R().setCode(401).setMsg(msg);
    }

    public static R ok(Object data) {
        return new R().setCode(200).setResult(data);
    }

    public static R unauthorized() {
        return new R().setCode(401).setMsg("Unauthorized");
    }

//    public static R Unauthorized(HttpServletResponse response){
//        response.getWriter().write(JsonUtils.toJson(R.unauthorized()));
//    }
}
