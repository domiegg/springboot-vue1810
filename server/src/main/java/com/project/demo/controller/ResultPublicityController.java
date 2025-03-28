package com.project.demo.controller;

import com.project.demo.entity.ResultPublicity;
import com.project.demo.service.ResultPublicityService;
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
 * 结果公示：(ResultPublicity)表控制层
 *
 */
@RestController
@RequestMapping("/result_publicity")
public class ResultPublicityController extends BaseController<ResultPublicity, ResultPublicityService> {

    /**
     * 结果公示对象
     */
    @Autowired
    public ResultPublicityController(ResultPublicityService service) {
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
