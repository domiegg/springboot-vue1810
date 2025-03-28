package com.project.demo.controller;

import com.project.demo.entity.HelpCenter;
import com.project.demo.service.HelpCenterService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


/**
 * 帮助中心：(HelpCenter)表控制层
 *
 */
@RestController
@RequestMapping("/help_center")
public class HelpCenterController extends BaseController<HelpCenter, HelpCenterService> {

    /**
     * 帮助中心对象
     */
    @Autowired
    public HelpCenterController(HelpCenterService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
