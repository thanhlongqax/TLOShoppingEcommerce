package org.thanhlong.Midterm.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String handleError(HttpServletRequest req, Model model) {
        String errorMsg = "";
        int httpErrorCode = getErrorCode(req);
        switch (httpErrorCode) {
            case 400 -> {
                errorMsg = "Http error code: 400. Bad request";
                break;
            }
            case 401 -> {
                errorMsg = "Http error code: 401. Unauthorized";
                break;
            }
            case 404 -> {
                errorMsg = "Http error code: 404. Request not found";
                break;
            }
            case 500 -> {
                errorMsg = "Http error code: 500. Internal sever error";
                break;
            }
            default -> {
                errorMsg = "Error";
                break;
            }
        }
        model.addAttribute("errorMsg", errorMsg);
        return "404";
    }

    private int getErrorCode(HttpServletRequest req) {
        Object errorCode =  req.getAttribute(ERROR_STATUS_CODE);
        return errorCode == null ? 0 :(Integer) errorCode;
    }
}
