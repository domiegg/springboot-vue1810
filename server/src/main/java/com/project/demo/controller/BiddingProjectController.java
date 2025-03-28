package com.project.demo.controller;

import com.project.demo.entity.BiddingProject;
import com.project.demo.service.BiddingProjectService;
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
 * 招标项目：(BiddingProject)表控制层
 *
 */
@RestController
@RequestMapping("/bidding_project")
public class BiddingProjectController extends BaseController<BiddingProject, BiddingProjectService> {

    /**
     * 招标项目对象
     */
    @Autowired
    public BiddingProjectController(BiddingProjectService service) {
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
